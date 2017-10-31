package com.mgcp.message.parameter.digitMap;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.ParameterValueContent;
import com.noyan.util.NullUtil;

public class DigitMap implements ParameterValueContent {
	private DigitString digitString;
	private DigitStringList digitStringList;

	public DigitMap(DigitString digitString) {
		this.digitString = digitString;
	}

	public DigitMap(DigitStringList digitStringList) {
		this.digitStringList = digitStringList;
	}

	public DigitString getDigitString() {
		return digitString;
	}

	public DigitStringList getDigitStringList() {
		return digitStringList;
	}

	@Override
	public String toString() {
		if (NullUtil.isNull(digitString)) {
			return "(" + digitStringList.toString() + ")";
		}

		return digitString.toString();
	}

	public static DigitMap parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		if (text.contains("[(]") && text.contains("[)]")) {
			return new DigitMap(DigitStringList.parse(text));
		}

		return new DigitMap(DigitString.parse(text));
	}

}
