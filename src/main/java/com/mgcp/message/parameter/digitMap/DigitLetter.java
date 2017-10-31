package com.mgcp.message.parameter.digitMap;

import java.util.ArrayList;
import java.util.Arrays;

import com.mgcp.exceptions.MGCPParseException;

public class DigitLetter {
	private ArrayList<DigitLetterValue> digitLetterValues = new ArrayList<>();

	public DigitLetter(DigitLetterValue... digitLetterValues) {
		this.digitLetterValues = new ArrayList<>(Arrays.asList(digitLetterValues));
	}

	public ArrayList<DigitLetterValue> getDigitLetterValues() {
		return digitLetterValues;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(digitLetterValues.get(0).toString());
		for (int i = 1; i < digitLetterValues.size(); i++) {
			builder.append(" " + digitLetterValues.get(i));
		}

		return builder.toString();
	}

	public static DigitLetter parse(String text) throws MGCPParseException {
		// TODO: FIX ME
		return new DigitLetter();
	}

}
