package com.mgcp.message.parameter.connectionMode;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.MGCPParameter;
import com.mgcp.message.parameter.ParameterValue;
import com.mgcp.message.parameter.connectionMode.ConnectionMode.ConnectionModes;
import com.noyan.util.NullUtil;

public class ConnectionModeParameterValue extends ParameterValue {
	private static final String finalShortname = "M";

	public ConnectionModeParameterValue() {
		this(null);
	}

	public ConnectionModeParameterValue(ConnectionMode content) {
		super(content);
		this.shortname = finalShortname;
	}

	@Override
	public ConnectionMode getValue() {
		return (ConnectionMode) super.getValue();
	}

	@Override
	public ConnectionModeParameterValue parse(String text) throws MGCPParseException {
		text = getValue(text);
		return new ConnectionModeParameterValue(ConnectionMode.parse(text));
	}

	public static MGCPParameter generate(ConnectionModes connectionMode) {
		if (connectionMode.equals(ConnectionModes.extensionConnectionMode)) {
			return null;
		}

		return new MGCPParameter(new ConnectionModeParameterValue(new ConnectionMode(connectionMode)));
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
