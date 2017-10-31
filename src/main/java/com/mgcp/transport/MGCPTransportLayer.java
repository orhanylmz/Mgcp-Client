package com.mgcp.transport;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Properties;

import com.configuration.GeneralConfiguration;
import com.mgcp.message.command.MGCPCommand;
import com.noyan.Base;
import com.noyan.network.socket.ServerSocketAdapter;
import com.noyan.network.socket.udp.UdpServerSocket;
import com.noyan.util.NullUtil;

public class MGCPTransportLayer implements Base, ServerSocketAdapter {
	private static MGCPTransportLayer mgcpTransportLayer;
	private UdpServerSocket serverSocket;

	private InetAddress mediaServerAddress;
	private int mediaServerPort;
	private String ivrEndpointID;

	long lastGeneratedTransactionId = 0;
	private Properties sessions = new Properties();

	public MGCPTransportLayer(int localPort) throws Exception {
		this(null, localPort);
	}

	public MGCPTransportLayer(InetAddress localAddress, int localPort) throws Exception {
		serverSocket = new UdpServerSocket(this, localAddress, localPort);
	}

	public MgcpSession getSession(long transactionId) {
		return (MgcpSession) sessions.get(transactionId);
	}

	public void addSession(MgcpSession mgcpSession) {
		if (NullUtil.isNull(mgcpSession)) {
			return;
		}

		synchronized (sessions) {
			sessions.put(mgcpSession.getTransactionId(), mgcpSession);
		}
	}

	public void removeSession(MgcpSession mgcpSession) {
		if (NullUtil.isNull(mgcpSession)) {
			return;
		}

		synchronized (sessions) {
			sessions.remove(mgcpSession.getTransactionId());
		}
	}

	public void send(MGCPCommand mgcpCommand) throws IOException {
		byte[] commandBytes = mgcpCommand.toString().getBytes();

		String domainName = mgcpCommand.getCommandLine().getEndpointName().getDomainName().getDomainName();
		int port = mgcpCommand.getCommandLine().getEndpointName().getPort();

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

	public MGCPTransportLayer setMediaServerAddress(InetAddress mediaServerAddress) {
		this.mediaServerAddress = mediaServerAddress;
		return this;
	}

	public int getMediaServerPort() {
		return mediaServerPort;
	}

	public MGCPTransportLayer setMediaServerPort(int mediaServerPort) {
		this.mediaServerPort = mediaServerPort;
		return this;
	}

	public String getIvrEndpointID() {
		return ivrEndpointID;
	}

	public MGCPTransportLayer setIvrEndpointID(String ivrEndpointID) {
		this.ivrEndpointID = ivrEndpointID;
		return this;
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

	public long generateTransactionId() {
		lastGeneratedTransactionId += 1;
		if (lastGeneratedTransactionId > GeneralConfiguration.maxTransactionId) {
			lastGeneratedTransactionId = 1;
		}

		return lastGeneratedTransactionId;
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
