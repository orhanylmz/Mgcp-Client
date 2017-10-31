package com.mgcp.message.parameter.digitMap;

import com.mgcp.exceptions.MGCPParseException;
import com.noyan.util.NullUtil;

public class DigitMapLetter {
	private DigitMapLetterEnum digitMapLetterEnum;
	private String value;

	public DigitMapLetter(DigitMapLetterEnum digitMapLetterEnum) {
		this.digitMapLetterEnum = digitMapLetterEnum;
	}

	public DigitMapLetter(String value) {
		this.digitMapLetterEnum = DigitMapLetterEnum.DIGIT;
		this.value = value;
	}

	@Override
	public String toString() {
		switch (digitMapLetterEnum) {
		case DIGIT:
			return value;
		case SHARP:
			return "#";
		case STAR:
			return "*";
		default:
			return digitMapLetterEnum.toString();
		}
	}

	public static DigitMapLetter parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();
		if (text.equals("*")) {
			return new DigitMapLetter(DigitMapLetterEnum.STAR);
		}

		if (text.equals("#")) {
			return new DigitMapLetter(DigitMapLetterEnum.SHARP);
		}

		try {
			DigitMapLetterEnum digitMapLetterEnum = DigitMapLetterEnum.valueOf(text);
			return new DigitMapLetter(digitMapLetterEnum);
		} catch (IllegalArgumentException ignored) {
		}

		return new DigitMapLetter(text);
	}

	public enum DigitMapLetterEnum {
		DIGIT, SHARP, STAR, A, B, C, D, T, X, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, U, V, W, Y, Z
	}
}
