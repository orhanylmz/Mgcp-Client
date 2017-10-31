package com.mgcp.message.parameter.reasonCode;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.ParameterValueContent;
import com.noyan.util.NullUtil;

public class ReasonCode implements ParameterValueContent {
	private String digit;
	private String packageName;
	private String character; // like comment

	public ReasonCode(String digit) {
		this(digit, null, null);
	}

	public ReasonCode(String digit, String packageName, String character) {
		this.digit = digit;
		this.packageName = packageName;
		this.character = character;
	}

	public String getDigit() {
		return digit;
	}

	public String getPackageName() {
		return packageName;
	}

	public String getCharacter() {
		return character;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(digit);
		if (NullUtil.isNotNull(packageName)) {
			builder.append(" /" + packageName);
		}

		if (NullUtil.isNotNull(character)) {
			builder.append(" " + character);
		}

		return builder.toString();
	}

	public static ReasonCode parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();

		String digit = text.substring(0, text.indexOf(" "));
		text = text.substring(text.indexOf(" ") + 1);
		text = text.trim();

		String packageName = null;
		String chracters = null;

		if (text.startsWith("/")) {
			packageName = text.substring(1, text.indexOf(" "));
			packageName.trim();
			text = text.substring(text.indexOf(" ") + 1);
			text.trim();
		}

		if (text.length() > 0) {
			chracters = text;
		}

		return new ReasonCode(digit, packageName, chracters);
	}
}
