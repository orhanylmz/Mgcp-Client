package com.mgcp.transport;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.configuration.GeneralConfiguration;
import com.mgcp.eventpackages.au.AdvancedAudioPackage;
import com.mgcp.eventpackages.au.signal.PlayCollect;
import com.mgcp.eventpackages.parameters.InitialPrompt;
import com.mgcp.eventpackages.parameters.RePrompt;
import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.MGCPMessage.MgcpParametersEnum;
import com.mgcp.message.command.MGCPCommand;
import com.mgcp.message.command.commandLine.MGCPCommandLine;
import com.mgcp.message.command.commandLine.MGCPCommandLine.MGCPVerb;
import com.mgcp.message.command.commandLine.endpointName.EndpointName;
import com.mgcp.message.command.commandLine.endpointName.localEndpointName.LocalEndpointName.Endpoint;
import com.mgcp.message.parameter.MGCPParameter;
import com.mgcp.message.parameter.callId.CallIdParameterValue;
import com.mgcp.message.parameter.connectionMode.ConnectionModeParameterValue;
import com.mgcp.message.parameter.connectionMode.ConnectionMode.ConnectionModes;
import com.mgcp.message.parameter.general.EventName;
import com.mgcp.message.parameter.localConnectionOptions.LocalConnectionOptionsParameterValue;
import com.mgcp.message.parameter.requestIdentifier.RequestIdentifierParameterValue;
import com.mgcp.message.parameter.requestedEvents.RequestedEvent;
import com.mgcp.message.parameter.requestedEvents.RequestedEvents;
import com.mgcp.message.parameter.requestedEvents.RequestedEventsParameterValue;
import com.mgcp.message.parameter.signalRequests.SignalRequest;
import com.mgcp.message.parameter.signalRequests.SignalRequests;
import com.mgcp.message.parameter.signalRequests.SignalRequestsParameterValue;
import com.mgcp.message.response.MGCPResponse;
import com.noyan.Base;
import com.noyan.util.NullUtil;
import com.noyan.util.log.Log;

public class MgcpSession implements Base {
	private long transactionId = -1;
	private MGCPTalk mgcpTalk;

	private ArrayList<MGCPTalk> talks = new ArrayList<>();

	private MgcpSessionInterface mgcpSessionInterface;

	public MgcpSession(MgcpSessionInterface mgcpSessionInterface) throws Exception {
		this.mgcpSessionInterface = mgcpSessionInterface;
		this.transactionId = getMgcpTransportLayer().generateTransactionId();
		getMgcpTransportLayer().addSession(this);
	}

	public void createIVR(String sdp) {
		create(sdp, Endpoint.IVR);
	}

	public void createBRIDGE() {
		create(null, Endpoint.BRIDGE);
	}

	public void createBRIDGE(String sdp) {
		create(sdp, Endpoint.BRIDGE);
	}

	public void createCONFERENCE() {
		create(null, Endpoint.CONFERENCE);
	}

	public void createCONFERENCE(String sdp) {
		create(sdp, Endpoint.CONFERENCE);
	}

	public void create(String sdp, Endpoint endpoint) {
		try {
			String endpointId = getMgcpTransportLayer().getEndpointID(endpoint);
			if (NullUtil.isNull(endpointId)) {
				fatal("Endpoint not found for " + endpoint);
				return;
			}

			MGCPCommandLine commandLine = new MGCPCommandLine(MGCPVerb.CRCX, transactionId, EndpointName.parse(endpointId + "@" + getMgcpTransportLayer().getMediaServerAddressWithPort()));

			MGCPCommand commandCRCX = new MGCPCommand(commandLine);
			commandCRCX.addParameter(CallIdParameterValue.generate());
			commandCRCX.addParameter(LocalConnectionOptionsParameterValue.generate(20, "PCMU", "PCMA"));
			commandCRCX.addParameter(ConnectionModeParameterValue.generate(ConnectionModes.sendrecv));

			if (NullUtil.isNotNull(sdp)) {
				commandCRCX.setSdpInformation(sdp);
			}

			send(commandCRCX);
			info("Sended CRCX command");
		} catch (Exception e) {
			mgcpSessionInterface.processException(e);
		}
	}

	public void request(String... filePaths) {
		try {
			if (talks.isEmpty()) {
				mgcpSessionInterface.processException(new Exception("Transaction is not started"));
				return;
			}

			if (NullUtil.isNull(filePaths)) {
				mgcpSessionInterface.processException(new Exception("filePaths can not be null"));
				return;
			}

			List<String> filePathList = Arrays.asList(filePaths);
			String initialPrompt = filePathList.stream().map(path -> "file:" + path).collect(Collectors.joining(","));

			AdvancedAudioPackage advancedAudioPackage = AdvancedAudioPackage.generatePlayCollectAU();
			PlayCollect playCollect = (PlayCollect) advancedAudioPackage.getSignal();
			playCollect.setInitialPrompt(new InitialPrompt(initialPrompt));

			if (filePathList.size() > 1) {
				String rePrompt = initialPrompt.substring(initialPrompt.indexOf(",") + 1);
				playCollect.setReprompt(new RePrompt(rePrompt));
			}

			MGCPCommand commandRQNT = generateCommandFromSession(MGCPVerb.RQNT);
			commandRQNT.addParameter(RequestIdentifierParameterValue.generate());
			commandRQNT.addParameter(new MGCPParameter(new SignalRequestsParameterValue(new SignalRequests(SignalRequest.parse(advancedAudioPackage.toString())))));

			send(commandRQNT);
			info("Sended RQNT command");
		} catch (Exception e) {
			mgcpSessionInterface.processException(e);
		}
	}

	public void modify() {
		modify(null);
	}

	public void modify(String sdp) {
		try {
			if (talks.isEmpty()) {
				mgcpSessionInterface.processException(new Exception("Transaction is not started"));
				return;
			}

			MGCPCommand commandMDCX = generateCommandFromSession(MGCPVerb.MDCX);

			commandMDCX.addParameter(RequestIdentifierParameterValue.generate());
			commandMDCX.addParameter(ConnectionModeParameterValue.generate(ConnectionModes.sendrecv));
			commandMDCX.addParameter(new MGCPParameter(new SignalRequestsParameterValue(new SignalRequests(new SignalRequest(new EventName("G", "rt"))))));
			commandMDCX.addParameter(new MGCPParameter(new RequestedEventsParameterValue(new RequestedEvents(new RequestedEvent(new EventName("L", "hu"))))));

			if (NullUtil.isNotNull(sdp)) {
				commandMDCX.setSdpInformation(sdp);
			}

			send(commandMDCX);
			info("Sended MDCX command");
		} catch (Exception e) {
			mgcpSessionInterface.processException(e);
		}
	}

	public void delete() {
		try {
			if (talks.isEmpty()) {
				mgcpSessionInterface.processException(new Exception("Transaction is not started"));
				return;
			}

			MGCPCommand commandDLCX = generateCommandFromSession(MGCPVerb.DLCX);

			send(commandDLCX);
			info("Sended DLCX command");
		} catch (Exception e) {
			mgcpSessionInterface.processException(e);
		}
	}

	private MGCPCommand generateCommandFromSession(MGCPVerb verb) throws Exception {
		MGCPCommandLine commandLine = new MGCPCommandLine(verb, getTransactionId(), EndpointName.parse(getSpecificEndpointId().getParameterValue().getValue().toString() + ":" + getMgcpTransportLayer().getMediaServerPort()));

		MGCPCommand command = new MGCPCommand(commandLine);
		command.addParameter(getSpecificEndpointId());
		command.addParameter(getCallId());
		command.addParameter(getConnectionId());
		return command;
	}

	public void send(MGCPCommand mgcpCommand) throws Exception {
		if (NullUtil.isNotNull(mgcpTalk)) {
			throw new Exception(getPrefix() + "New message can not be sent without receiving answer");
		}

		mgcpTalk = new MGCPTalk(mgcpCommand);

		getMgcpTransportLayer().send(mgcpCommand);
	}

	public void receive(MGCPResponse mgcpResponse) throws Exception {
		if (NullUtil.isNull(mgcpTalk)) {
			throw new Exception(getPrefix() + "New message can not be receive without sending message");
		}

		MGCPVerb verb = mgcpTalk.getMgcpCommand().getCommandLine().getMgcpVerb();

		mgcpTalk.setMgcpResponse(mgcpResponse);
		talks.add(mgcpTalk);
		mgcpTalk = null;

		info("Code detail: " + getMgcpTransportLayer().getResponseCodeDetail(mgcpResponse.getResponseLine().getResponseCode()));
		mgcpSessionInterface.processReceiveMessage(mgcpResponse, verb);
	}

	public ArrayList<MGCPTalk> getTalks() {
		return talks;
	}

	public long getTransactionId() {
		return transactionId;
	}

	public MGCPParameter getCallId() throws MGCPParseException {
		return getParameter(MgcpParametersEnum.CallId);
	}

	public MGCPParameter getSpecificEndpointId() throws MGCPParseException {
		return getParameter(MgcpParametersEnum.SpecificEndpointId);
	}

	public MGCPParameter getConnectionId() throws MGCPParseException {
		return getParameter(MgcpParametersEnum.ConnectionId);
	}

	private MGCPParameter getParameter(MgcpParametersEnum mgcpParametersEnum) throws MGCPParseException {
		if (talks.isEmpty()) {
			return null;
		}

		if (!talks.get(0).isCompleted()) {
			return null;
		}

		MGCPParameter mgcpParameter = talks.get(0).getMgcpResponse().getParameter(mgcpParametersEnum);
		if (NullUtil.isNotNull(mgcpParameter)) {
			return mgcpParameter;
		}

		return talks.get(0).getMgcpCommand().getParameter(mgcpParametersEnum);
	}

	private MGCPTransportLayer getMgcpTransportLayer() throws Exception {
		return MGCPTransportLayer.getMgcpTransportLayer();
	}

	@Override
	public String getPrefix() {
		return "MgcpSession [TransactionId:" + getTransactionId() + "] -> ";
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(getPrefix());
		for (int i = 0; i < talks.size(); i++) {
			builder.append(talks.get(i).toString());
		}

		return builder.toString();
	}

	public static void main(String[] args) throws Exception {
		Logger.getRootLogger().setLevel(Level.OFF);
		Logger.getRootLogger().addAppender(Log.createConsoleAppender(null));

		MGCPTransportLayer.createAndStartMgcpTransportLayer(GeneralConfiguration.localPort);

		MGCPTransportLayer.getMgcpTransportLayer().setMediaServerAddress(InetAddress.getByName(GeneralConfiguration.mediaServerAddress));
		MGCPTransportLayer.getMgcpTransportLayer().setMediaServerPort(GeneralConfiguration.mediaServerPort);
		MGCPTransportLayer.getMgcpTransportLayer().setIvrEndpointID(GeneralConfiguration.ivrEndpointID);
		MGCPTransportLayer.getMgcpTransportLayer().setConferenceEndpointID(GeneralConfiguration.conferenceEndpointID);
		MGCPTransportLayer.getMgcpTransportLayer().setBridgeEndpointID(GeneralConfiguration.bridgeEndpointID);

		MgcpSessionInterface mgcpSessionInterface = new MgcpSessionInterface() {
			@Override
			public void processException(Exception exception) {
				exception.printStackTrace();
			}

			@Override
			public void onSuccess(MGCPResponse mgcpResponse, MGCPVerb verb) {
				System.out.println(verb + " SUCCESS");
			}

			@Override
			public void onError(MGCPResponse mgcpResponse, MGCPVerb verb) {
				System.out.println(verb + " ERROR");

			}
		};

		MgcpSession mgcpSession = new MgcpSession(mgcpSessionInterface);

		// mgcpSession.create(GeneralConfiguration.remoteSDP, Endpoint.BRIDGE);
		mgcpSession.createBRIDGE();
		Thread.sleep(5000);
		MgcpSession mgcpSession2 = new MgcpSession(mgcpSessionInterface);
		mgcpSession2.createBRIDGE(mgcpSession.getTalks().get(0).getMgcpResponse().getSdpInformation());
		Thread.sleep(5000);
		mgcpSession.modify(mgcpSession2.getTalks().get(0).getMgcpResponse().getSdpInformation());
		Thread.sleep(5000);
		
		System.out.println("session01----------------------------------------------------");
		System.out.println(mgcpSession);
		System.out.println("session02----------------------------------------------------");
		System.out.println(mgcpSession2);
		// mgcpSession.delete();
		// mgcpSession.modify();
		// Thread.sleep(5000);
		// mgcpSession.request("C:\\temp\\b.wav,it=1");
//		mgcpSession.request("C:\\temp\\b.wav");
		// mgcpSession.request("C:\\temp\\b.wav", "C:\\temp\\d.wav",
		// "C:\\temp\\c.wav");
		// Thread.sleep(5000);
		// mgcpSession.delete();
	}
}
