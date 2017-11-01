package com.mgcp.message.response;

import java.util.ArrayList;
import java.util.Arrays;

import com.configuration.Constants;
import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.MGCPMessage;
import com.mgcp.message.parameter.MGCPParameter;
import com.mgcp.message.response.responseLine.MGCPResponseLine;
import com.noyan.util.NullUtil;

public class MGCPResponse extends MGCPMessage {
	private MGCPResponseLine responseLine;

	public MGCPResponse(MGCPResponseLine responseLine) {
		this.responseLine = responseLine;
	}

	public MGCPResponse(MGCPResponseLine responseLine, String sdpInformation) {
		this.responseLine = responseLine;
		this.sdpInformation = sdpInformation;
	}

	public MGCPResponse(MGCPResponseLine responseLine, MGCPParameter... parameters) {
		this(responseLine, null, new ArrayList<>(Arrays.asList(parameters)));
	}

	public MGCPResponse(MGCPResponseLine responseLine, String sdpInformation, MGCPParameter... parameters) {
		this(responseLine, sdpInformation, new ArrayList<>(Arrays.asList(parameters)));
	}

	public MGCPResponse(MGCPResponseLine responseLine, ArrayList<MGCPParameter> parameters) {
		this(responseLine, null, parameters);
	}

	public MGCPResponse(MGCPResponseLine responseLine, String sdpInformation, ArrayList<MGCPParameter> parameters) {
		this.responseLine = responseLine;
		this.parameters = parameters;
		this.sdpInformation = sdpInformation;
	}

	public MGCPResponseLine getResponseLine() {
		return responseLine;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(responseLine.toString());

		for (int i = 0; i < parameters.size(); i++) {
			builder.append(parameters.get(i).toString());
		}

		if (NullUtil.isNotNull(sdpInformation)) {
			builder.append(Constants.EOL + sdpInformation + Constants.EOL);
		}

		return builder.toString();
	}

	public static MGCPResponse parse(String text) throws Exception {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();
		String sdpInformation = null;

		String[] parts = text.split(Constants.EOL + Constants.EOL);
		if (parts.length > 1) {
			sdpInformation = parts[parts.length - 1].trim();
			text = text.substring(0, text.indexOf(Constants.EOL + Constants.EOL)).trim();
		}

		parts = text.split(Constants.EOL);
		MGCPResponseLine responseLine = MGCPResponseLine.parse(parts[0]);

		if (parts.length < 2) {
			return new MGCPResponse(responseLine, sdpInformation);
		}

		ArrayList<MGCPParameter> mgcpParameters = new ArrayList<>();
		for (int i = 1; i < parts.length; i++) {
			MGCPParameter mgcpParameter = MGCPParameter.parse(parts[i]);
			mgcpParameters.add(mgcpParameter);
		}

		return new MGCPResponse(responseLine, sdpInformation, mgcpParameters);
	}

	public static void main(String[] args) throws Exception {
		String message = "200 1211 The requested transaction was executed normally\n" + "Z:mobicents/ivr/15@192.168.1.105\n" + "I:f\n" + "\n" + "v=0\n" + "o=- 1506811015264 1 IN IP4 192.168.1.105\n"
				+ "s=Mobicents Media Server\n" + "c=IN IP4 192.168.1.105\n" + "t=0 0\n" + "m=audio 65506 RTP/AVP 97 3 18 101 8 0\n" + "c=IN IP4 192.168.1.105\n" + "a=sendrecv\n"
				+ "a=rtcp:65507 IN IP4 192.168.1.105\n" + "a=ptime:20\n" + "a=rtpmap:0 pcmu/8000\n" + "a=rtpmap:97 l16/8000\n" + "a=rtpmap:18 g729/8000\n" + "a=rtpmap:3 gsm/8000\n"
				+ "a=rtpmap:101 telephone-event/8000\n" + "a=rtpmap:8 pcma/8000\n" + "a=ssrc:3874633850 cname:mdmMidg+GFKMozPc";

		MGCPResponse mgcpResponse = MGCPResponse.parse(message);
		System.out.println(mgcpResponse.toString());
	}
}
