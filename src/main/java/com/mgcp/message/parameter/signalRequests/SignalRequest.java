package com.mgcp.message.parameter.signalRequests;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.general.EventName;
import com.noyan.util.NullUtil;

public class SignalRequest {
	//TODO: FIX ME for event packages
	private EventName eventName;
	private EventParameters eventParameters;

	public SignalRequest(EventName eventName) {
		this(eventName, null);
	}

	public SignalRequest(EventName eventName, EventParameters eventParameters) {
		this.eventName = eventName;
		this.eventParameters = eventParameters;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(eventName.toString());
		if (NullUtil.isNotNull(eventParameters)) {
			builder.append("(" + eventParameters + ")");
		}

		return builder.toString();
	}

	public static SignalRequest parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();

		text.replaceAll("[(]", "|");
		text.replaceAll("[)]", "|");

		String[] parts = text.split("[|]");
		if (NullUtil.isNull(parts)) {
			throw new MGCPParseException("parts can not be null");
		}

		if (parts.length == 1) {
			return new SignalRequest(EventName.parse(parts[0]));
		}

		return new SignalRequest(EventName.parse(parts[0]), EventParameters.parse(parts[1]));
	}

}
