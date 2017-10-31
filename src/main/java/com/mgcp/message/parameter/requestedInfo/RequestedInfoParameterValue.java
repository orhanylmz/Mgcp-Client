package com.mgcp.message.parameter.requestedInfo;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.ParameterValue;
import com.noyan.util.NullUtil;

public class RequestedInfoParameterValue extends ParameterValue {
	private static final String finalShortname = "F";

	public RequestedInfoParameterValue() {
		this(null);
	}

	public RequestedInfoParameterValue(RequestedInfo requestedInfo) {
		super(requestedInfo);
		this.shortname = finalShortname;
	}

	@Override
	public RequestedInfo getValue() {
		return (RequestedInfo) super.getValue();
	}

	@Override
	public RequestedInfoParameterValue parse(String text) throws MGCPParseException {
		text = getValue(text);
		return new RequestedInfoParameterValue(RequestedInfo.parse(text));
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
