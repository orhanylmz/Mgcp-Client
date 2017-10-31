package com.mgcp.message.parameter.digitMap;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.ParameterValue;
import com.noyan.util.NullUtil;

public class DigitMapParameterValue extends ParameterValue {
	private static final String finalShortname = "D";

	public DigitMapParameterValue() {
		this(null);
	}

	public DigitMapParameterValue(DigitMap digitMap) {
		super(digitMap);
		this.shortname = finalShortname;
	}

	@Override
	public DigitMap getValue() {
		return (DigitMap) super.getValue();
	}

	public DigitMapParameterValue parse(String text) throws MGCPParseException {
		text = getValue(text);
		return new DigitMapParameterValue(DigitMap.parse(text));
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
