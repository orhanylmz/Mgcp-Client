package com.mgcp.message.parameter.connectionParameters;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.ParameterValue;
import com.noyan.util.NullUtil;

public class ConnectionParametersParameterValue extends ParameterValue {
	private static final String finalShortname = "P";

	public ConnectionParametersParameterValue() {
		this(null);
	}

	public ConnectionParametersParameterValue(ConnectionParameters connectionParameters) {
		super(connectionParameters);
		this.shortname = finalShortname;
	}

	@Override
	public ConnectionParameters getValue() {
		return (ConnectionParameters) super.getValue();
	}

	@Override
	public ConnectionParametersParameterValue parse(String text) throws MGCPParseException {
		text = getValue(text);
		return new ConnectionParametersParameterValue(ConnectionParameters.parse(text));
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
