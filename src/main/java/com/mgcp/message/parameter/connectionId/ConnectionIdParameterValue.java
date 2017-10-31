package com.mgcp.message.parameter.connectionId;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.MGCPParameter;
import com.mgcp.message.parameter.ParameterValue;
import com.mgcp.message.parameter.SimpleValueContent;
import com.noyan.util.NullUtil;
import com.noyan.util.RandomUtil;

public class ConnectionIdParameterValue extends ParameterValue {
	private static final String finalShortname = "I";

	public ConnectionIdParameterValue() {
		this(null);
	}

	public ConnectionIdParameterValue(ConnectionId connectionId) {
		super(connectionId);
		this.shortname = finalShortname;
	}

	@Override
	public ConnectionId getValue() {
		return (ConnectionId) super.getValue();
	}

	@Override
	public ConnectionIdParameterValue parse(String text) throws MGCPParseException {
		text = getValue(text);
		return new ConnectionIdParameterValue(ConnectionId.parse(text));
	}

	public static MGCPParameter generate() {
		return new MGCPParameter(new ConnectionIdParameterValue(new ConnectionId(new SimpleValueContent(RandomUtil.generateHexDigit32()))));
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
