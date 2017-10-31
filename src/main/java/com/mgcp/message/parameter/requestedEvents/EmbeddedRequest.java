package com.mgcp.message.parameter.requestedEvents;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.digitMap.DigitMap;
import com.mgcp.message.parameter.signalRequests.SignalRequests;
import com.noyan.util.NullUtil;

public class EmbeddedRequest {
	private RequestedEvents embeddedRequestList;
	private SignalRequests embeddedSignalRequest;
	private DigitMap embeddedDigitMap;

	public EmbeddedRequest(RequestedEvents embeddedRequestList) {// R
		this(embeddedRequestList, null, null);
	}

	public EmbeddedRequest(SignalRequests embeddedSignalRequest) {// S
		this(null, embeddedSignalRequest, null);
	}

	public EmbeddedRequest(DigitMap embeddedDigitMap) {// D
		this(null, null, embeddedDigitMap);
	}

	public EmbeddedRequest(RequestedEvents embeddedRequestList, SignalRequests embeddedSignalRequest) {// RS
		this(embeddedRequestList, embeddedSignalRequest, null);
	}

	public EmbeddedRequest(RequestedEvents embeddedRequestList, DigitMap embeddedDigitMap) {// RD
		this(embeddedRequestList, null, embeddedDigitMap);
	}

	public EmbeddedRequest(SignalRequests embeddedSignalRequest, DigitMap embeddedDigitMap) {// SD
		this(null, embeddedSignalRequest, embeddedDigitMap);
	}

	public EmbeddedRequest(RequestedEvents embeddedRequestList, SignalRequests embeddedSignalRequest, DigitMap embeddedDigitMap) {// RSD
		this.embeddedRequestList = embeddedRequestList;
		this.embeddedSignalRequest = embeddedSignalRequest;
		this.embeddedDigitMap = embeddedDigitMap;
	}

	public RequestedEvents getEmbeddedRequestList() {
		return embeddedRequestList;
	}

	public SignalRequests getEmbeddedSignalRequest() {
		return embeddedSignalRequest;
	}

	public DigitMap getEmbeddedDigitMap() {
		return embeddedDigitMap;
	}

	@Override
	public String toString() {
		boolean comma = false;
		StringBuilder builder = new StringBuilder();

		if (NullUtil.isNotNull(embeddedRequestList)) {
			builder.append("R(" + embeddedRequestList.toString() + ")");
			comma = true;
		}

		if (NullUtil.isNotNull(embeddedSignalRequest)) {
			if (comma) {
				builder.append(",");
			}

			builder.append("S(" + embeddedSignalRequest.toString() + ")");
			comma = true;
		}

		if (NullUtil.isNotNull(embeddedDigitMap)) {
			if (comma) {
				builder.append(",");
			}

			builder.append("D(" + embeddedDigitMap.toString() + ")");
			comma = true;
		}

		return builder.toString();
	}

	public static EmbeddedRequest parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();
		String[] parts = text.split("[,]");
		if (NullUtil.isNull(parts)) {
			throw new MGCPParseException("parts can not be null");
		}

		RequestedEvents embeddedRequestList = null;
		SignalRequests embeddedSignalRequest = null;
		DigitMap embeddedDigitMap = null;

		for (int i = 0; i < parts.length; i++) {
			String part = parts[i].trim();

			if (part.startsWith("R")) {
				part = part.substring(1);
				part = part.trim();
				embeddedRequestList = RequestedEvents.parse(part);
			}

			if (part.startsWith("S")) {
				part = part.substring(1);
				part = part.trim();
				embeddedSignalRequest = SignalRequests.parse(part);
			}

			if (part.startsWith("D")) {
				part = part.substring(1);
				part = part.trim();
				embeddedDigitMap = DigitMap.parse(part);
			}
		}

		return new EmbeddedRequest(embeddedRequestList, embeddedSignalRequest, embeddedDigitMap);
	}
}
