package com.mgcp.old.manager.session;

import java.util.Random;
import java.util.UUID;

import com.configuration.Constants;
import com.configuration.GeneralConfiguration;
import com.mgcp.old.manager.MgcpConnectionManager;
import com.mgcp.old.parameters.MgcpSharedParams;

public class MediaIvrSession extends IvrSession {

	private int responseCode = 911;
	private boolean recievedResponse = false;

	public MediaIvrSession(MgcpConnectionManager mgcpConnectionManager, MediaSessionEvent mediaSessionEvent, String sdpContent) {
		super(mgcpConnectionManager, mediaSessionEvent, sdpContent);
	}

	@Override
	public void processSession() {
		try {
			int messageID = (new Random()).nextInt(Integer.MAX_VALUE);
			String message = createCRCX(messageID);
			getMgcpConnectionManager().sendMessage(messageID, message, this);
			String lockValue = UUID.randomUUID().toString();
			lockProperties.put(messageID, lockValue);
			synchronized (lockValue) {
				lockValue.wait(GeneralConfiguration.messageTimeout);
			}
			if (!recievedResponse) {
				getMediaSessionEvent().timeout();
				return;
			}
			if (responseCode == 911) {
				throw new Exception();
			}

			if (responseCode != 200) {
				System.out.println("Response Code:" + responseCode);
				return;
			}
			getMediaSessionEvent().connected(getLocalSdpContent());
			message = createRQNT(++messageID);
			getMgcpConnectionManager().sendMessage(messageID, message, this);
			lockValue = UUID.randomUUID().toString();
			lockProperties.put(messageID, lockValue);
			recievedResponse = false;
			synchronized (lockValue) {
				lockValue.wait(GeneralConfiguration.messageTimeout);
			}
			if (!recievedResponse) {
				getMediaSessionEvent().timeout();
				return;
			}
			if (responseCode == 911) {
				throw new Exception();
			}
			if (responseCode != 200) {
				System.out.println("Response Code:" + responseCode);
				return;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private String createRQNT(int messageID) {
		String message = "RQNT " + messageID + " " + getSpecificEndpointID() + " MGCP 1.0" + Constants.LineSeparator;
//		message += "N:" + MgcpSharedParams.mediaServerAddress + ":" + MgcpSharedParams.mediaServerPort + Constants.LineSeparator;
		message += "X:1" + Constants.LineSeparator;
		message += "R:" + "AU/oc(N),AU/of(N)" + Constants.LineSeparator;
//		message += "R:" + "A/oc(N),A/of(N),D/dtmf0(N),D/dtmf1(N),D/dtmf2(N),D/dtmf3(N),D/dtmf4(N),D/dtmf5(N),D/dtmf6(N),D/dtmf7(N),D/dtmf8(N),D/dtmf9(N),D/dtmfA(N),D/dtmfB(N),D/dtmfC(N),D/dtmfD(N),D/dtmfStar(N),D/dtmfHash(N)" + Constants.LineSeparator;
//		message += "S:" + "AU/pa(an=http://www.music.helsinki.fi/tmt/opetus/uusmedia/esim/a2002011001-e02.wav it=1)" + Constants.LineSeparator;
//		message += "S:" + "AU/pa(an=http://www.signalogic.com/melp/EngSamples/Orig/male.wav it=1)" + Constants.LineSeparator;
		message += "S:" + "AU/pa(an=file:/home/emreyalcin/a.wav it=1)" + Constants.LineSeparator;
		return message;
	}

	private String createCRCX(int messageID) {
		String message = "CRCX " + messageID + " " + GeneralConfiguration.ivrEndpointID + "@" + GeneralConfiguration.mediaServerAddress + ":" + GeneralConfiguration.mediaServerPort + " MGCP 1.0" + Constants.LineSeparator;
		message += "N:" + GeneralConfiguration.mediaServerAddress + ":" + GeneralConfiguration.mediaServerPort + Constants.LineSeparator;
		message += "C:" + Integer.toHexString((new Random()).nextInt(Integer.MAX_VALUE)).toUpperCase() + Constants.LineSeparator;
		message += "L:p:20,a:PCMU;PCMA" + Constants.LineSeparator;
		message += "M:sendrecv" + Constants.LineSeparator; 
		if (getSdpContent() != null) {
			message += Constants.LineSeparator + getSdpContent();
		}
		return message;
	}

	@Override
	public void processResponse(String message) {
		try {
//			int messageID = OldMessage.getMessageId(message);
//			if (messageID == 911) {
//				throw new Exception();
//			}
//			String lockID = (String) lockProperties.get(messageID);
//			if (lockID == null) {
//				return;
//			}
//
//			String[] lines = message.split(Constants.LineSeparator);
//			if (lines == null || lines.length <= 1) {
//
//			}
//			for (int i = 1; i < lines.length; i++) {
//				if (lines[i].contains("v=0")) {
//					String localContent = "";
//					for (int j = i; j < lines.length; j++) {
//						localContent += lines[j] + Constants.LineSeparator;
//					}
//					setLocalSdpContent(localContent);
//					break;
//				}
//				if (lines[i].contains("I:")) {
//					setConnectionId(lines[i].substring(lines[i].indexOf("I:") + 2).trim());
//					continue;
//				}
//				if (lines[i].contains("Z:")) {
//					setSpecificEndpointID(lines[i].substring(lines[i].indexOf("Z:") + 2).trim());
//					continue;
//				}
//				System.out.println("Ignored Lines " + lines[i]);
//			}
//			responseCode = OldMessage.getResponseCode(message);
//			recievedResponse = true;
//			synchronized (lockID) {
//				lockID.notify();
//			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
