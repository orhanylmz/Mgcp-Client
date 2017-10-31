package com.mgcp.message.parameter.eventStates;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.ParameterValue;
import com.mgcp.message.parameter.signalRequests.SignalRequests;
import com.noyan.util.NullUtil;

public class EventStatesParameterValue extends ParameterValue {
	private static final String finalShortname = "ES";

	public EventStatesParameterValue() {
		this(null);
	}

	public EventStatesParameterValue(SignalRequests eventStates) {
		super(eventStates);
		this.shortname = finalShortname;
	}

	@Override
	public SignalRequests getValue() {
		return (SignalRequests) super.getValue();
	}

	@Override
	public EventStatesParameterValue parse(String text) throws MGCPParseException {
		text = getValue(text);
		return new EventStatesParameterValue(SignalRequests.parse(text));
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
