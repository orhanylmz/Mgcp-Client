package com.mgcp.transport;

import java.net.InetAddress;
import java.util.ArrayList;

import com.configuration.GeneralConfiguration;
import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.MGCPMessage.MgcpParametersEnum;
import com.mgcp.message.command.MGCPCommand;
import com.mgcp.message.command.commandLine.MGCPCommandLine;
import com.mgcp.message.command.commandLine.MGCPCommandLine.MGCPVerb;
import com.mgcp.message.command.commandLine.endpointName.EndpointName;
import com.mgcp.message.parameter.MGCPParameter;
import com.mgcp.message.parameter.callId.CallIdParameterValue;
import com.mgcp.message.parameter.connectionMode.ConnectionModeParameterValue;
import com.mgcp.message.parameter.connectionMode.ConnectionMode.ConnectionModes;
import com.mgcp.message.parameter.general.EventName;
import com.mgcp.message.parameter.localConnectionOptions.LocalConnectionOptionsParameterValue;
import com.mgcp.message.parameter.requestIdentifier.RequestIdentifierParameterValue;
import com.mgcp.message.parameter.signalRequests.EventParameters;
import com.mgcp.message.parameter.signalRequests.SignalRequest;
import com.mgcp.message.parameter.signalRequests.SignalRequests;
import com.mgcp.message.parameter.signalRequests.SignalRequestsParameterValue;
import com.mgcp.message.response.MGCPResponse;
import com.noyan.Base;
import com.noyan.util.NullUtil;

public class MgcpSession implements Base {
	private long transactionId = -1;
	private MGCPTalk mgcpTalk;

	private ArrayList<MGCPTalk> talks = new ArrayList<>();

	private MgcpSessionInterface mgcpSessionInterface;

	public MgcpSession(MgcpSessionInterface mgcpSessionInterface) throws Exception {
		this.mgcpSessionInterface = mgcpSessionInterface;
		this.transactionId = MGCPTransportLayer.getMgcpTransportLayer().generateTransactionId();
		MGCPTransportLayer.getMgcpTransportLayer().addSession(this);
	}

	public void create() throws Exception {
		create(null);
	}

	public void create(String remoteSdp) {
		try {
			String endpointId = MGCPTransportLayer.getMgcpTransportLayer().getIvrEndpointID();
			InetAddress serverAddress = MGCPTransportLayer.getMgcpTransportLayer().getMediaServerAddress();
			int serverPort = MGCPTransportLayer.getMgcpTransportLayer().getMediaServerPort();

			MGCPCommandLine commandLine = new MGCPCommandLine(MGCPVerb.CRCX, transactionId, EndpointName.parse(endpointId + "@" + serverAddress.getHostAddress() + ":" + serverPort));

			MGCPCommand commandCRCX = new MGCPCommand(commandLine);
			commandCRCX.addParameter(CallIdParameterValue.generate());
			commandCRCX.addParameter(LocalConnectionOptionsParameterValue.generate(20, "PCMU", "PCMA"));
			commandCRCX.addParameter(ConnectionModeParameterValue.generate(ConnectionModes.sendrecv));

			if (NullUtil.isNotNull(remoteSdp)) {
				commandCRCX.setSdpInformation(GeneralConfiguration.remoteSDP);
			}

			send(commandCRCX);
		} catch (Exception e) {
			mgcpSessionInterface.processException(e);
		}
	}

	public void request() {
		try {
			if(talks.isEmpty()){
				mgcpSessionInterface.processException(new Exception("Transaction is not started"));
				return;
			}
			
			MGCPCommandLine commandLine = new MGCPCommandLine(MGCPVerb.RQNT, getTransactionId(), EndpointName.parse(getTalks().get(0).getMgcpResponse().getSpecificEndpointId().getValue().toString() + ":" + MGCPTransportLayer.getMgcpTransportLayer().getMediaServerPort()));

			MGCPCommand commandRQNT = new MGCPCommand(commandLine);
			commandRQNT.addParameter(getSpecificEndpointId());
			commandRQNT.addParameter(getCallId());
			commandRQNT.addParameter(getConnectionId());
			commandRQNT.addParameter(RequestIdentifierParameterValue.generate());
			commandRQNT.addParameter(new MGCPParameter(new SignalRequestsParameterValue(new SignalRequests(new SignalRequest(new EventName("AU", "pa"), EventParameters.parse("an=file:C:\\temp\\b.wav,it=1"))))));

			send(commandRQNT);
		} catch (Exception e) {
			mgcpSessionInterface.processException(e);
		}
	}

	public void modify(String sdp) {
		try {
			// FODO: FIX ME
		} catch (Exception e) {
			mgcpSessionInterface.processException(e);
		}
	}

	public void send(MGCPCommand mgcpCommand) throws Exception {
		if (NullUtil.isNotNull(mgcpTalk)) {
			throw new Exception("mgcpTalk must be null for send new command");
		}

		mgcpTalk = new MGCPTalk(mgcpCommand);

		MGCPTransportLayer.getMgcpTransportLayer().send(mgcpCommand);
		trace("Sended Message:\n" + mgcpCommand.toString());
	}

	public void receive(MGCPResponse mgcpResponse) throws Exception {
		if (NullUtil.isNull(mgcpTalk)) {
			throw new Exception("currentCommand must be notnull for receive new response");
		}

		mgcpTalk.setMgcpResponse(mgcpResponse);
		talks.add(mgcpTalk);
		mgcpTalk = null;

		trace("Received Message:\n" + mgcpResponse.toString());
		mgcpSessionInterface.processReceiveMessage(mgcpResponse);
	}

	public MGCPCommand getCurrentCommand() {
		if (NullUtil.isNull(mgcpTalk)) {
			return null;
		}

		return mgcpTalk.getMgcpCommand();
	}

	public MGCPTalk getLastMgcpTalk() {
		if (NullUtil.isNull(talks)) {
			return null;
		}

		for (int i = (talks.size() - 1); i >= 0; i--) {
			if (talks.get(i).isCompleted()) {
				return talks.get(i);
			}
		}

		return null;
	}

	public long getTransactionId() {
		return transactionId;
	}

	public ArrayList<MGCPTalk> getTalks() {
		return talks;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("TransactionId: " + transactionId);
		for (int i = 0; i < talks.size(); i++) {
			builder.append(talks.get(i).toString());
		}

		return builder.toString();
	}

	@Override
	public String getPrefix() {
		return "MgcpSession";
	}
}
