package com.mgcp.message.parameter.signalRequests;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.ParameterValue;
import com.noyan.util.NullUtil;

public class SignalRequestsParameterValue extends ParameterValue {
	private static final String finalShortname = "S";

	public SignalRequestsParameterValue() {
		this(null);
	}

	public SignalRequestsParameterValue(SignalRequests signalRequests) {
		super(signalRequests);
		this.shortname = finalShortname;
	}

	@Override
	public SignalRequests getValue() {
		return (SignalRequests) super.getValue();
	}

	@Override
	public SignalRequestsParameterValue parse(String text) throws MGCPParseException {
		text = getValue(text);
		return new SignalRequestsParameterValue(SignalRequests.parse(text));
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
