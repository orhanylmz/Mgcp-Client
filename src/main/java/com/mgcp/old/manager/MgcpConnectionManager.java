package com.mgcp.old.manager;

import java.util.Properties;

import com.configuration.GeneralConfiguration;
import com.mgcp.old.manager.session.IvrSession;
import com.mgcp.old.manager.session.MediaIvrSession;
import com.mgcp.old.manager.session.MediaSessionEvent;
import com.mgcp.old.parameters.MgcpSharedParams;
import com.mgcp.old.transport.MgcpUdpManager;

public class MgcpConnectionManager {

	private MgcpUdpManager mgcpUdpManager;

	private Properties transactions = new Properties();

	public MgcpConnectionManager() {
		mgcpUdpManager = new MgcpUdpManager(this);
		mgcpUdpManager.start();
	}

	public void createIvrConnection(MediaSessionEvent mediaSessionEvent, String sdpContent) {
		MediaIvrSession mediaIvrSession = new MediaIvrSession(this, mediaSessionEvent, sdpContent);
		mediaIvrSession.start();
	}

	public void sendMessage(int messageID, String message, IvrSession ivrSession) {
		try {
			getTransactions().put(messageID, ivrSession);
			mgcpUdpManager.sendMessage(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void recieveMessage(String message) {
		try {
//			int messageID = OldMessage.getMessageId(message);
//			if (messageID == 911) {
//				throw new Exception();
//			}
//			IvrSession ivrSession = (IvrSession) transactions.get(messageID);
//			if (ivrSession == null) {
//				throw new Exception();
//			}
//			ivrSession.processResponse(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public MgcpUdpManager getMgcpUdpManager() {
		return mgcpUdpManager;
	}

	public void setMgcpUdpManager(MgcpUdpManager mgcpUdpManager) {
		this.mgcpUdpManager = mgcpUdpManager;
	}

	public Properties getTransactions() {
		return transactions;
	}

	public static void main(String[] args) {
		String remoteSDP = "v=0\r\n" + "o=1002 559 930 IN IP4 " + GeneralConfiguration.localAddress + "\r\n" + "s=mizudroid\r\n" + "c=IN IP4" + GeneralConfiguration.localAddress + "\r\n" + "t=0 0\r\n"
				+ "m=audio 17970 RTP/AVP 105 0 97 3 101\r\n" + "a=rtpmap:105 speex/16000\r\n" + "a=fmtp:105 mode=8;mode=any\r\n" + "a=rtpmap:0 PCMU/8000\r\n" + "a=rtpmap:97 iLBC/8000\r\n"
				+ "a=fmtp:97 mode=30\r\n" + "a=rtpmap:101 telephone-event/8000\r\n" + "a=fmtp:101 0-16\r\n" + "a=sendrecv";

		MediaSessionEvent event = new MediaSessionEvent() {

			@Override
			public void timeout() {
				System.out.println("Timeout Detected Closing Session");
			}

			@Override
			public void connected(String content) {
				System.out.println("Connection Successfully Content:" + content);
			}
		};

		MgcpConnectionManager mgcpConnectionManager = new MgcpConnectionManager();
		mgcpConnectionManager.createIvrConnection(event, remoteSDP);

		while (true) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
