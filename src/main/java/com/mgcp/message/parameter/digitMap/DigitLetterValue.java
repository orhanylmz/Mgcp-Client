package com.mgcp.message.parameter.digitMap;

import com.mgcp.exceptions.MGCPParseException;
import com.noyan.util.NullUtil;

public class DigitLetterValue {
	private DigitMapLetter digitMapLetter;
	private String digit01;
	private String digit02;

	public DigitLetterValue(DigitMapLetter digitMapLetter) {
		this.digitMapLetter = digitMapLetter;
	}

	public DigitLetterValue(String digit01, String digit02) {
		this.digit01 = digit01;
		this.digit02 = digit02;
	}

	public DigitMapLetter getDigitMapLetter() {
		return digitMapLetter;
	}

	public String getDigit01() {
		return digit01;
	}

	public String getDigit02() {
		return digit02;
	}

	@Override
	public String toString() {
		if (NullUtil.isNull(digitMapLetter)) {
			return digit01 + "-" + digit02;
		}

		return digitMapLetter.toString();
	}

	public static DigitLetterValue parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();

		try {
			DigitMapLetter digitMapLetter = DigitMapLetter.parse(text);
			return new DigitLetterValue(digitMapLetter);
		} catch (MGCPParseException e) {
			// ignored
		}

		String[] parts = text.split("-");
		if (NullUtil.isNull(parts)) {
			throw new MGCPParseException("parts can not be null");
		}

		if (parts.length != 2) {
			throw new MGCPParseException("parts length must be 2");
		}

		return new DigitLetterValue(parts[0].trim(), parts[1].trim());
	}

	public static void main(String[] args) {
		System.out.println("a-b".split("-")[0]);
	}

}
