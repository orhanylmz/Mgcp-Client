package com.mgcp.message.parameter.quarantineHandling;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.ParameterValue;
import com.noyan.util.NullUtil;

public class QuarantineHandlingParameterValue extends ParameterValue {
	private static final String finalShortname = "Q";

	public QuarantineHandlingParameterValue() {
		this(null);
	}

	public QuarantineHandlingParameterValue(QuarantineHandling quarantineHandling) {
		super(quarantineHandling);
		this.shortname = finalShortname;
	}

	@Override
	public QuarantineHandling getValue() {
		return (QuarantineHandling) super.getValue();
	}

	@Override
	public QuarantineHandlingParameterValue parse(String text) throws MGCPParseException {
		text = getValue(text);
		return new QuarantineHandlingParameterValue(QuarantineHandling.parse(text));
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
