package com.mgcp.old.transport;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import com.configuration.GeneralConfiguration;
import com.mgcp.old.manager.MgcpConnectionManager;
import com.mgcp.old.parameters.MgcpSharedParams;

public class MgcpUdpManager extends Thread {

	private DatagramSocket serverSocket;
	private MgcpConnectionManager mgcpConnectionManager;

	public MgcpUdpManager(MgcpConnectionManager mgcpConnectionManager) {
		try {
			serverSocket = new DatagramSocket(GeneralConfiguration.mediaServerPort, InetAddress.getByName(GeneralConfiguration.localAddress));
			this.mgcpConnectionManager = mgcpConnectionManager;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			DatagramPacket receivePacket;
			while (true) {
				try {
					receivePacket = new DatagramPacket(new byte[GeneralConfiguration.MAXPACKETLENGTH], GeneralConfiguration.MAXPACKETLENGTH);
					serverSocket.receive(receivePacket);
					processMessage(receivePacket.getData());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendMessage(String message) {
		try {
			byte[] sendData = message.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName(GeneralConfiguration.mediaServerAddress), GeneralConfiguration.mediaServerPort);
			serverSocket.send(sendPacket);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void processMessage(byte[] message) {
		String responseMessage = new String(message);
		System.out.println("Incoming message " + responseMessage);
		mgcpConnectionManager.recieveMessage(responseMessage);
	}

}
