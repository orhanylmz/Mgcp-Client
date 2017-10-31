package com.mgcp.message.parameter.digitMap;

import java.util.ArrayList;
import java.util.Arrays;

import com.mgcp.exceptions.MGCPParseException;
import com.noyan.util.NullUtil;

public class DigitStringList {
	private ArrayList<DigitString> digitStrings = new ArrayList<>();

	public DigitStringList(DigitString... digitStrings) {
		this.digitStrings = new ArrayList<>(Arrays.asList(digitStrings));
	}

	public DigitStringList(ArrayList<DigitString> digitStrings) {
		this.digitStrings = new ArrayList<>(digitStrings);
	}

	public DigitStringList(DigitString digitString) {
		addDigitString(digitString);
	}

	public void addDigitString(DigitString digitString) {
		digitStrings.add(digitString);
	}

	public ArrayList<DigitString> getDigitStrings() {
		return digitStrings;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(digitStrings.get(0).toString());
		for (int i = 1; i < digitStrings.size(); i++) {
			builder.append("|" + digitStrings.get(i));
		}

		return builder.toString();
	}

	public static DigitStringList parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();
		if (text.startsWith("(")) {
			text = text.substring(1);
		}

		if (text.endsWith(")")) {
			text = text.substring(0, text.length() - 1);
		}

		String[] parts = text.split("[|]");

		ArrayList<DigitString> digitStrings = new ArrayList<>();
		for (int i = 0; i < parts.length; i++) {
			String digitStringText = parts[i];
			DigitString digitString = DigitString.parse(digitStringText);
			if (NullUtil.isNotNull(digitString)) {
				digitStrings.add(digitString);
			}
		}

		return new DigitStringList(digitStrings);
	}
}
