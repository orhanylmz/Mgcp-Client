package com.mgcp.message.parameter.secondEndpointId;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.command.commandLine.endpointName.EndpointName;
import com.mgcp.message.parameter.ParameterValue;
import com.noyan.util.NullUtil;

public class SecondEndpointIDParameterValue extends ParameterValue {
	private static final String finalShortname = "Z2";

	public SecondEndpointIDParameterValue() {
		this(null);
	}

	public SecondEndpointIDParameterValue(EndpointName secondEndpointId) {
		super(secondEndpointId);
		this.shortname = finalShortname;
	}

	@Override
	public EndpointName getValue() {
		return (EndpointName) super.getValue();
	}

	@Override
	public SecondEndpointIDParameterValue parse(String text) throws MGCPParseException {
		text = getValue(text);
		return new SecondEndpointIDParameterValue(EndpointName.parse(text));
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
