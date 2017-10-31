package com.mgcp.message.parameter.restartMethod;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.ParameterValue;
import com.noyan.util.NullUtil;

public class RestartMethodParameterValue extends ParameterValue {
	private static final String finalShortname = "RM";

	public RestartMethodParameterValue() {
		this(null);
	}

	public RestartMethodParameterValue(RestartMethod restartMethod) {
		super(restartMethod);
		this.shortname = finalShortname;
	}

	@Override
	public RestartMethod getValue() {
		return (RestartMethod) super.getValue();
	}

	@Override
	public RestartMethodParameterValue parse(String text) throws MGCPParseException {
		text = getValue(text);
		return new RestartMethodParameterValue(RestartMethod.parse(text));
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
