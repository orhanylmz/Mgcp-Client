package com.mgcp.message.parameter.detectEvents;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.ParameterValue;
import com.mgcp.message.parameter.signalRequests.SignalRequests;
import com.noyan.util.NullUtil;

public class DetectEventsParameterValue extends ParameterValue {
	private static final String finalShortname = "T";

	public DetectEventsParameterValue() {
		this(null);
	}

	public DetectEventsParameterValue(SignalRequests detectEvents) {
		super(detectEvents);
		this.shortname = finalShortname;
	}

	@Override
	public SignalRequests getValue() {
		return (SignalRequests) super.getValue();
	}

	@Override
	public DetectEventsParameterValue parse(String text) throws MGCPParseException {
		text = getValue(text);
		return new DetectEventsParameterValue(SignalRequests.parse(text));
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
