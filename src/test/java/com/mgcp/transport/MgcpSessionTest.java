package com.mgcp.transport;

import java.net.InetAddress;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.configuration.GeneralConfiguration;
import com.mgcp.message.command.commandLine.MGCPCommandLine.MGCPVerb;
import com.mgcp.message.command.commandLine.endpointName.EndpointName;
import com.mgcp.message.response.MGCPResponse;
import com.noyan.util.log.Log;

public class MgcpSessionTest {
	public static void main(String[] args) throws Exception {
		Logger.getRootLogger().setLevel(Level.ALL);
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
		mgcpSession.createBRIDGE(GeneralConfiguration.remoteSDP);
		Thread.sleep(5000);
		EndpointName endpointName = mgcpSession.getSpecificEndpointName();

		MgcpSession mgcpSession2 = new MgcpSession(mgcpSessionInterface);
		mgcpSession2.createBRIDGEwithEndpointName(endpointName);
		// System.out.println("#####" +
		// mgcpSession.getTalks().get(0).getMgcpResponse().getSpecificEndpointId().getValue().toString()
		// + "#####");
		Thread.sleep(5000);
		mgcpSession2.modify(GeneralConfiguration.remoteSDP2);
		Thread.sleep(5000);

		// mgcpSession.delete();
		// mgcpSession.modify();
		// Thread.sleep(5000);
		// mgcpSession.request("C:\\temp\\b.wav,it=1");
		// mgcpSession.request("C:\\temp\\b.wav");
		// mgcpSession.request("C:\\temp\\b.wav", "C:\\temp\\d.wav",
		// "C:\\temp\\c.wav");
		// Thread.sleep(5000);
		// mgcpSession.delete();
	}
}
