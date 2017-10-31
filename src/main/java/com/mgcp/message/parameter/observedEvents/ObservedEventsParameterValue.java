package com.mgcp.message.parameter.observedEvents;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.MGCPParameter;
import com.mgcp.message.parameter.ParameterValue;
import com.mgcp.message.parameter.signalRequests.SignalRequests;
import com.noyan.util.NullUtil;

public class ObservedEventsParameterValue extends ParameterValue {
	private static final String finalShortname = "O";

	public ObservedEventsParameterValue() {
		this(null);
	}

	public ObservedEventsParameterValue(SignalRequests observedEvents) {
		super(observedEvents);
		this.shortname = finalShortname;
	}

	@Override
	public SignalRequests getValue() {
		return (SignalRequests) super.getValue();
	}

	@Override
	public ObservedEventsParameterValue parse(String text) throws MGCPParseException {
		text = getValue(text);
		return new ObservedEventsParameterValue(SignalRequests.parse(text));
	}

	public static MGCPParameter generate(String encodedText) throws MGCPParseException {
		return new MGCPParameter(new ObservedEventsParameterValue(SignalRequests.parse(encodedText)));
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
