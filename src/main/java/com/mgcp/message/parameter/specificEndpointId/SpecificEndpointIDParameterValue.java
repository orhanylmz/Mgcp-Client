package com.mgcp.message.parameter.specificEndpointId;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.command.commandLine.endpointName.EndpointName;
import com.mgcp.message.parameter.ParameterValue;
import com.noyan.util.NullUtil;

public class SpecificEndpointIDParameterValue extends ParameterValue {
	private static final String finalShortname = "Z";

	public SpecificEndpointIDParameterValue() {
		this(null);
	}

	public SpecificEndpointIDParameterValue(EndpointName specificEndpointId) {
		super(specificEndpointId);
		this.shortname = finalShortname;
	}

	@Override
	public EndpointName getValue() {
		return (EndpointName) super.getValue();
	}

	@Override
	public SpecificEndpointIDParameterValue parse(String text) throws MGCPParseException {
		text = getValue(text);
		return new SpecificEndpointIDParameterValue(EndpointName.parse(text));
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
