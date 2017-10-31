package com.mgcp.message.parameter.bearerInformation;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.ParameterValue;
import com.noyan.util.NullUtil;

public class BearerInformationParameterValue extends ParameterValue {
	private static final String finalShortname = "B";

	public BearerInformationParameterValue() {
		this(null);
	}

	public BearerInformationParameterValue(BearerInformation bearerInformation) {
		super(bearerInformation);
		this.shortname = finalShortname;
	}

	@Override
	public BearerInformation getValue() {
		return (BearerInformation) super.getValue();
	}

	@Override
	public BearerInformationParameterValue parse(String text) throws MGCPParseException {
		text = getValue(text);
		return new BearerInformationParameterValue(BearerInformation.parse(text));
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
