package com.mgcp.message.parameter.requestedEvents;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.ParameterValueContent;
import com.mgcp.message.parameter.general.EventName;
import com.noyan.util.NullUtil;

public class RequestedEvent implements ParameterValueContent {
	private EventName eventName;
	private RequestedActions requestedActions;
	private EventParameters eventParameters;

	public RequestedEvent(EventName eventName) {
		this(eventName, null, null);
	}

	public RequestedEvent(EventName eventName, RequestedActions requestedActions) {
		this(eventName, requestedActions, null);
	}

	public RequestedEvent(EventName eventName, RequestedActions requestedActions, EventParameters eventParameters) {
		this.eventName = eventName;
		this.requestedActions = requestedActions;
		this.eventParameters = eventParameters;
	}

	public EventName getEventName() {
		return eventName;
	}

	public EventParameters getEventParameters() {
		return eventParameters;
	}

	public RequestedActions getRequestedActions() {
		return requestedActions;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(eventName.toString());

		if (NullUtil.isNotNull(requestedActions)) {
			builder.append("(" + requestedActions + ")");

			if (NullUtil.isNotNull(eventParameters)) {
				builder.append("(" + eventParameters + ")");
			}
		}

		return builder.toString();
	}

	public static RequestedEvent parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();

		text.replaceAll("[(]", "|");
		text.replaceAll("[(]", "|");
		text.replaceAll("[)]", "|");

		String[] parts = text.split("[|]");
		if (NullUtil.isNull(parts)) {
			throw new MGCPParseException("parts can not be null");
		}

		if (parts.length == 1) {
			return new RequestedEvent(EventName.parse(parts[0]));
		}

		if (parts.length == 3) {
			return new RequestedEvent(EventName.parse(parts[0].trim()), RequestedActions.parse(parts[1].trim()));
		}

		if (parts.length == 5) {
			return new RequestedEvent(EventName.parse(parts[0].trim()), RequestedActions.parse(parts[1].trim()), EventParameters.parse(parts[3].trim()));
		}

		throw new MGCPParseException("ParameterValueContentPart not found text: " + text);
	}

}
