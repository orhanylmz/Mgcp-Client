package com.mgcp.ui;

import java.net.InetAddress;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.configuration.GeneralConfiguration;
import com.mgcp.message.response.MGCPResponse;
import com.mgcp.transport.MGCPTransportLayer;
import com.mgcp.transport.MgcpSession;
import com.mgcp.transport.MgcpSessionInterface;
import com.noyan.Base;
import com.noyan.util.log.Log;

public class MGCPTest implements Base {

	public static void main(String[] args) throws Exception {
		Logger.getRootLogger().setLevel(Level.ALL);
		Logger.getRootLogger().addAppender(Log.createConsoleAppender(null));
		
		MGCPTransportLayer.createAndStartMgcpTransportLayer(GeneralConfiguration.localPort);
		
		MGCPTransportLayer.getMgcpTransportLayer().setMediaServerAddress(InetAddress.getByName(GeneralConfiguration.mediaServerAddress));
		MGCPTransportLayer.getMgcpTransportLayer().setMediaServerPort(GeneralConfiguration.mediaServerPort);
		MGCPTransportLayer.getMgcpTransportLayer().setIvrEndpointID(GeneralConfiguration.ivrEndpointID);
		
		MgcpSessionInterface mgcpSessionInterface=new MgcpSessionInterface() {
			
			@Override
			public void processReceiveMessage(MGCPResponse mgcpResponse) {
				System.out.println("RECEIVED " + mgcpResponse);				
			}
			
			@Override
			public void processException(Exception exception) {
				exception.printStackTrace();
			}
		};
		
		MgcpSession mgcpSession=new MgcpSession(mgcpSessionInterface);
		
		mgcpSession.create();
		
		Thread.sleep(10000);
		mgcpSession.request();
	}

	@Override
	public String getPrefix() {
		return "MGCPTest";
	}
}
