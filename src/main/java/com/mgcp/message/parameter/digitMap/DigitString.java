package com.mgcp.message.parameter.digitMap;

import com.mgcp.exceptions.MGCPParseException;
import com.noyan.util.NullUtil;

public class DigitString {
	private DigitPosition digitPosition;
	private boolean dot = false;

	public DigitString(DigitPosition digitPosition) {
		this(digitPosition, false);
	}

	public DigitString(DigitPosition digitPosition, boolean dot) {
		this.digitPosition = digitPosition;
		this.dot = dot;
	}

	public DigitPosition getDigitPosition() {
		return digitPosition;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(digitPosition.toString());
		if (dot) {
			builder.append(".");
		}

		return builder.toString();
	}

	public static DigitString parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		boolean dot = false;
		if (text.endsWith(".")) {
			dot = true;
			text = text.substring(0, text.length() - 1);
		}

		text = text.trim();
		DigitPosition digitPosition = DigitPosition.parse(text);
		if (NullUtil.isNull(digitPosition)) {
			throw new MGCPParseException("digitPosition not found");
		}

		return new DigitString(digitPosition, dot);
	}

	public static void main(String[] args) {
		System.out.println("ALÝ.".endsWith("."));
	}

}
