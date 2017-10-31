package com.mgcp.message.parameter.requestedEvents;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.ParameterValue;
import com.noyan.util.NullUtil;

public class RequestedEventsParameterValue extends ParameterValue {
	private static final String finalShortname = "R";

	public RequestedEventsParameterValue() {
		this(null);
	}

	public RequestedEventsParameterValue(RequestedEvents requestedEvents) {
		super(requestedEvents);
		this.shortname = finalShortname;
	}

	@Override
	public RequestedEvents getValue() {
		return (RequestedEvents) super.getValue();
	}

	@Override
	public RequestedEventsParameterValue parse(String text) throws MGCPParseException {
		text = getValue(text);
		return new RequestedEventsParameterValue(RequestedEvents.parse(text));
	}

	@Override
	public boolean isMapped(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();

		if (!text.startsWith(finalShortname)) {
			return false;
		}

		text = text.substring(finalShortname.length()).trim();
		if (!text.startsWith(":")) {
			return false;
		}

		return true;
	}
}
