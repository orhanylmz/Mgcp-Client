package com.mgcp.message.parameter;

import com.mgcp.exceptions.MGCPParseException;
import com.noyan.util.NullUtil;

public class SimpleValueContent implements ParameterValueContent {
	private String value;

	public SimpleValueContent(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return value;
	}

	public static SimpleValueContent parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();

		return new SimpleValueContent(text);
	}

}
