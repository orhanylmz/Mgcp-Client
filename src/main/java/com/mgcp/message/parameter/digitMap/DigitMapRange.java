package com.mgcp.message.parameter.digitMap;

import com.mgcp.exceptions.MGCPParseException;
import com.noyan.util.NullUtil;

public class DigitMapRange {
	private DigitLetter digitLetter;

	public DigitMapRange(DigitLetter digitLetter) {
		this.digitLetter = digitLetter;
	}

	public DigitLetter getDigitLetter() {
		return digitLetter;
	}

	@Override
	public String toString() {
		return "[" + digitLetter.toString() + "]";
	}

	public static DigitMapRange parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();
		if (text.startsWith("[")) {
			text = text.substring(1);
		}

		if (text.endsWith("]")) {
			text = text.substring(0, text.length() - 1);
		}

		DigitLetter digitLetter = DigitLetter.parse(text);
		if (NullUtil.isNull(digitLetter)) {
			throw new MGCPParseException("digitLetter can not be null");
		}

		return new DigitMapRange(digitLetter);
	}
}
