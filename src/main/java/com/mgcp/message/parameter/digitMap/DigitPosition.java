package com.mgcp.message.parameter.digitMap;

import com.mgcp.exceptions.MGCPParseException;
import com.noyan.util.NullUtil;

public class DigitPosition {
	private DigitMapLetter digitMapLetter;
	private DigitMapRange digitMapRange;

	public DigitPosition(DigitMapLetter digitMapLetter) {
		this.digitMapLetter = digitMapLetter;
	}

	public DigitPosition(DigitMapRange digitMapRange) {
		this.digitMapRange = digitMapRange;
	}

	public DigitMapLetter getDigitMapLetter() {
		return digitMapLetter;
	}

	public DigitMapRange getDigitMapRange() {
		return digitMapRange;
	}

	@Override
	public String toString() {
		if (NullUtil.isNotNull(digitMapLetter)) {
			return digitMapLetter.toString();
		}

		return digitMapRange.toString();
	}

	public static DigitPosition parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();

		try {
			DigitMapLetter digitMapLetter = DigitMapLetter.parse(text);
			return new DigitPosition(digitMapLetter);
		} catch (MGCPParseException e) {
		}

		DigitMapRange digitMapRange = DigitMapRange.parse(text);
		return new DigitPosition(digitMapRange);
	}
}
