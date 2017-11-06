package com.mgcp.transport;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Properties;

import com.configuration.GeneralConfiguration;
import com.mgcp.message.command.MGCPCommand;
import com.mgcp.message.command.commandLine.endpointName.localEndpointName.LocalEndpointName.Endpoint;
import com.mgcp.message.response.responseCode.ResponseCodeDetails;
import com.noyan.Base;
import com.noyan.network.socket.ServerSocketAdapter;
import com.noyan.network.socket.udp.UdpServerSocket;
import com.noyan.util.NullUtil;

public class MGCPTransportLayer implements Base, ServerSocketAdapter {
	private static MGCPTransportLayer mgcpTransportLayer;
	private UdpServerSocket serverSocket;
	private ResponseCodeDetails responseCodeDetails;

	private InetAddress mediaServerAddress;
	private int mediaServerPort;
	private String ivrEndpointID;
	private String bridgeEndpointID;
	private String conferenceEndpointID;

	long lastGeneratedTransactionId = 0;
	private Properties sessions = new Properties();
	private Properties transactions = new Properties();

	public MGCPTransportLayer(int localPort) throws Exception {
		this(null, localPort);
	}

	public MGCPTransportLayer(InetAddress localAddress, int localPort) throws Exception {
		serverSocket = new UdpServerSocket(this, localAddress, localPort);
		responseCodeDetails = new ResponseCodeDetails();
	}

	public MgcpSession getSession(String callId) {
		return (MgcpSession) sessions.get(callId);
	}

	public void addSession(MgcpSession mgcpSession) {
		if (NullUtil.isNull(mgcpSession)) {
			return;
		}

		synchronized (sessions) {
			sessions.put(mgcpSession.getCallId(), mgcpSession);
		}
	}

	public void removeSession(MgcpSession mgcpSession) {
		if (NullUtil.isNull(mgcpSession)) {
			return;
		}

		synchronized (sessions) {
			sessions.remove(mgcpSession.getCallId());
		}
	}

	public MgcpSession getSessionFromTransaction(long transactionId) {
		return (MgcpSession) transactions.get(transactionId);
	}

	public void addSessionFromTransaction(long transactionId, MgcpSession mgcpSession) {
		if (NullUtil.isNull(mgcpSession)) {
			return;
		}

		synchronized (transactions) {
			transactions.put(transactionId, mgcpSession);
		}
	}

	public void removeSessionFromTransaction(long transactionId) {
		synchronized (transactions) {
			transactions.remove(transactionId);
		}
	}

	public void send(MGCPCommand mgcpCommand) throws IOException {
		byte[] commandBytes = mgcpCommand.toString().getBytes();

		String domainName = mgcpCommand.getCommandLine().getEndpointName().getDomainName().getDomainName();
		int port = mgcpCommand.getCommandLine().getEndpointName().getPort();
		if (port <= 0) {
			port = getMediaServerPort();
		}

		serverSocket.send(commandBytes, InetAddress.getByName(domainName), port);
		trace("Sended ->\n" + mgcpCommand.toString());
	}

	@Override
	public void processRecieveData(byte[] data, InetAddress recieveAddress, int recievePort) {
		trace("Receive message <- " + recieveAddress.getHostAddress() + ":" + recievePort + " ->\n" + new String(data));
		new SessionFounder(data, this).start();
	}

	public InetAddress getMediaServerAddress() {
		return mediaServerAddress;
	}

	public void setMediaServerAddress(InetAddress mediaServerAddress) {
		this.mediaServerAddress = mediaServerAddress;
	}

	public int getMediaServerPort() {
		return mediaServerPort;
	}

	public void setMediaServerPort(int mediaServerPort) {
		this.mediaServerPort = mediaServerPort;
	}

	public String getMediaServerAddressWithPort() {
		return mediaServerAddress.getHostAddress() + ":" + mediaServerPort;
	}

	public String getEndpointID(Endpoint endpoint) {
		if (NullUtil.isNull(endpoint)) {
			return null;
		}

		String endpointName = null;

		switch (endpoint) {
		case IVR:
			endpointName = ivrEndpointID;
			break;
		case CONFERENCE:
			endpointName = conferenceEndpointID;
			break;
		case BRIDGE:
			endpointName = bridgeEndpointID;
			break;
		default:
			break;
		}

		if (NullUtil.isNull(endpointName)) {
			error("Endpoint: " + endpoint + " is not configured");
		}

		return endpointName;
	}

	public void setIvrEndpointID(String ivrEndpointID) {
		this.ivrEndpointID = ivrEndpointID;
	}

	public void setConferenceEndpointID(String conferenceEndpointID) {
		this.conferenceEndpointID = conferenceEndpointID;
	}

	public void setBridgeEndpointID(String bridgeEndpointID) {
		this.bridgeEndpointID = bridgeEndpointID;
	}

	public UdpServerSocket getServerSocket() {
		return serverSocket;
	}

	public static void createAndStartMgcpTransportLayer(int localPort) throws Exception {
		createAndStartMgcpTransportLayer(null, localPort);
	}

	public static void createAndStartMgcpTransportLayer(InetAddress localAddress, int localPort) throws Exception {
		if (NullUtil.isNotNull(mgcpTransportLayer)) {
			mgcpTransportLayer.fatal("MgcpTransportLayer already running");
			return;
		}

		mgcpTransportLayer = new MGCPTransportLayer(localAddress, localPort);
		mgcpTransportLayer.getServerSocket().start();
	}

	public static MGCPTransportLayer getMgcpTransportLayer() throws Exception {
		if (NullUtil.isNull(mgcpTransportLayer)) {
			throw new Exception("MgcpTransportLayer is not running");
		}

		return mgcpTransportLayer;
	}

	public synchronized long generateTransactionId() {
		lastGeneratedTransactionId += 1;
		if (lastGeneratedTransactionId > GeneralConfiguration.maxTransactionId) {
			lastGeneratedTransactionId = 1;
		}

		return lastGeneratedTransactionId;
	}

	public String getResponseCodeDetail(String responseCode) {
		return responseCodeDetails.getCodeDetail(responseCode);
	}

	@Override
	public String getPrefix() {
		return "MGCPTransportLayer";
	}

	@Override
	public void processException(Exception exception) {
		exception.printStackTrace();
	}
}
