package com.mgcp.message.parameter.secondConnectionId;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.ParameterValue;
import com.mgcp.message.parameter.connectionId.ConnectionId;
import com.noyan.util.NullUtil;

public class SecondConnectionIdParameterValue extends ParameterValue {
	private static final String finalShortname = "I2";
	
	public SecondConnectionIdParameterValue() {
		this(null);
	}

	public SecondConnectionIdParameterValue(ConnectionId connectionId) {
		super(connectionId);
		this.shortname = finalShortname;
	}
	
	@Override
	public ConnectionId getValue() {
		return (ConnectionId) super.getValue();
	}

	@Override
	public SecondConnectionIdParameterValue parse(String text) throws MGCPParseException {
		text = getValue(text);
		return new SecondConnectionIdParameterValue(ConnectionId.parse(text));
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
