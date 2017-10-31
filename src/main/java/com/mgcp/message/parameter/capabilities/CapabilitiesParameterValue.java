package com.mgcp.message.parameter.capabilities;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.ParameterValue;
import com.noyan.util.NullUtil;

public class CapabilitiesParameterValue extends ParameterValue {
	private static final String finalShortname = "A";

	public CapabilitiesParameterValue() {
		this(null);
	}

	public CapabilitiesParameterValue(Capabilities capabilities) {
		super(capabilities);
		this.shortname = finalShortname;
	}

	@Override
	public Capabilities getValue() {
		return (Capabilities) super.getValue();
	}

	@Override
	public CapabilitiesParameterValue parse(String text) throws MGCPParseException {
		text = getValue(text);
		return new CapabilitiesParameterValue(Capabilities.parse(text));
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
