package com.mgcp.message.parameter.signalRequests;

import com.mgcp.exceptions.MGCPParseException;
import com.noyan.util.NullUtil;

public class EventParameter {
	private String eventParameterValue; // abc or "abc"
	private String eventParameterName;
	private EventParameter eventParameter;
	private EventParameters eventParameters;

	public EventParameter(String eventParameterValue) {
		this.eventParameterValue = eventParameterValue;
	}

	public EventParameter(String eventParameterName, EventParameter eventParameter) {
		this.eventParameterName = eventParameterName;
		this.eventParameter = eventParameter;
	}

	public EventParameter(String eventParameterName, EventParameters eventParameters) {
		this.eventParameterName = eventParameterName;
		this.eventParameters = eventParameters;
	}

	public String getEventParameterValue() {
		return eventParameterValue;
	}

	public String getEventParameterName() {
		return eventParameterName;
	}

	public EventParameter getEventParameter() {
		return eventParameter;
	}

	public EventParameters getEventParameters() {
		return eventParameters;
	}

	@Override
	public String toString() {
		if (NullUtil.isNotNull(eventParameterValue)) {
			return eventParameterValue;
		}

		if (NullUtil.isNotNull(eventParameter)) {
			return eventParameterName + "=" + eventParameter;
		}

		return eventParameterName + "(" + eventParameters + ")";
	}

	public static EventParameter parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();

		String[] parts = text.split("[=]");
		if (parts.length > 1) {
			return new EventParameter(parts[0].trim(), EventParameter.parse(parts[1]));
		}

		text.replaceAll("[)]", "(");
		parts = text.split("[(]");
		if (parts.length > 1) {
			return new EventParameter(parts[0].trim(), EventParameters.parse(parts[1]));
		}

		return new EventParameter(text);
	}
}
