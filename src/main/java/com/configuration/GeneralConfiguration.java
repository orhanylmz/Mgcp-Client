package com.configuration;

import com.mgcp.message.parameter.bearerInformation.BearerInformationParameterValue;
import com.mgcp.message.parameter.callId.CallIdParameterValue;
import com.mgcp.message.parameter.capabilities.CapabilitiesParameterValue;
import com.mgcp.message.parameter.connectionId.ConnectionIdParameterValue;
import com.mgcp.message.parameter.connectionMode.ConnectionModeParameterValue;
import com.mgcp.message.parameter.connectionParameters.ConnectionParametersParameterValue;
import com.mgcp.message.parameter.detectEvents.DetectEventsParameterValue;
import com.mgcp.message.parameter.digitMap.DigitMapParameterValue;
import com.mgcp.message.parameter.eventStates.EventStatesParameterValue;
import com.mgcp.message.parameter.extension.ExtensionParameterValue;
import com.mgcp.message.parameter.localConnectionOptions.LocalConnectionOptionsParameterValue;
import com.mgcp.message.parameter.maxMgcpDatagram.MaxMGCPDatagramParameterValue;
import com.mgcp.message.parameter.notifiedEntity.NotifiedEntityParameterValue;
import com.mgcp.message.parameter.observedEvents.ObservedEventsParameterValue;
import com.mgcp.message.parameter.packageList.PackageListParameterValue;
import com.mgcp.message.parameter.quarantineHandling.QuarantineHandlingParameterValue;
import com.mgcp.message.parameter.reasonCode.ReasonCodeParameterParameterValue;
import com.mgcp.message.parameter.requestIdentifier.RequestIdentifierParameterValue;
import com.mgcp.message.parameter.requestedEvents.RequestedEventsParameterValue;
import com.mgcp.message.parameter.requestedInfo.RequestedInfoParameterValue;
import com.mgcp.message.parameter.responseAck.ResponseAckParameterValue;
import com.mgcp.message.parameter.restartDelay.RestartDelayParameterValue;
import com.mgcp.message.parameter.restartMethod.RestartMethodParameterValue;
import com.mgcp.message.parameter.secondConnectionId.SecondConnectionIdParameterValue;
import com.mgcp.message.parameter.secondEndpointId.SecondEndpointIDParameterValue;
import com.mgcp.message.parameter.signalRequests.SignalRequestsParameterValue;
import com.mgcp.message.parameter.specificEndpointId.SpecificEndpointIDParameterValue;

public class GeneralConfiguration {
	public static boolean logEnable = true;

	public static Class<?>[] priorityOfParameters = new Class<?>[] { //
			ResponseAckParameterValue.class, // 1
			BearerInformationParameterValue.class, // 2
			CallIdParameterValue.class, // 3
			ConnectionIdParameterValue.class, // 4
			NotifiedEntityParameterValue.class, // 5
			RequestIdentifierParameterValue.class, // 6
			LocalConnectionOptionsParameterValue.class, // 7
			ConnectionModeParameterValue.class, // 8
			RequestedEventsParameterValue.class, // 9
			SignalRequestsParameterValue.class, // 10
			DigitMapParameterValue.class, // 11
			ObservedEventsParameterValue.class, // 12
			ConnectionParametersParameterValue.class, // 13
			ReasonCodeParameterParameterValue.class, // 14
			SpecificEndpointIDParameterValue.class, // 15
			SecondEndpointIDParameterValue.class, // 16
			SecondConnectionIdParameterValue.class, // 17
			RequestedInfoParameterValue.class, // 18
			QuarantineHandlingParameterValue.class, // 19
			DetectEventsParameterValue.class, // 20
			RestartMethodParameterValue.class, // 21
			RestartDelayParameterValue.class, // 22
			CapabilitiesParameterValue.class, // 23
			EventStatesParameterValue.class, // 24
			PackageListParameterValue.class, // 25
			MaxMGCPDatagramParameterValue.class, // 26
			ExtensionParameterValue.class // 27
	};

	// PACKET
	public static int MAXPACKETLENGTH = 1024;

	public static int maxSendCommandCount = 30;

	public static int waitingResponseTimeout = 5000;
	public static int waitingCommandTimeout = 60 * 60 * 1000;
	
	
	public static String mediaServerAddress = "192.168.1.103";
	public static int mediaServerPort = 2427;
	public static String localAddress = "192.168.1.103";
	public static int localPort = 2727;

	public static String ivrEndpointID = "mobicents/ivr/$";

	// Time
	public static int messageTimeout = 2000;
	
	public static int maxTransactionId= 999999999;

	public static String remoteSDP = "v=0\r\n" + "o=1002 559 930 IN IP4 " + localAddress + "\r\n" + "s=mizudroid\r\n" + "c=IN IP4 " + localAddress + "\r\n" + "t=0 0\r\n" + "m=audio 17970 RTP/AVP 105 0 97 3 101\r\n" + "a=rtpmap:105 speex/16000\r\n" + "a=fmtp:105 mode=8;mode=any\r\n"
			+ "a=rtpmap:0 PCMU/8000\r\n" + "a=rtpmap:97 iLBC/8000\r\n" + "a=fmtp:97 mode=30\r\n" + "a=rtpmap:101 telephone-event/8000\r\n" + "a=fmtp:101 0-16\r\n" + "a=sendrecv";
}
