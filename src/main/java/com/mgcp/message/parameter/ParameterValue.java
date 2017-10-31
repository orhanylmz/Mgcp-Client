package com.mgcp.message.parameter;

import com.mgcp.exceptions.MGCPParseException;
import com.noyan.util.NullUtil;

public abstract class ParameterValue {
	protected String shortname;
	protected ParameterValueContent value;

	public ParameterValue() {
	}

	public ParameterValue(ParameterValueContent value) {
		this.value = value;
	}

	public String getShortname() {
		return shortname;
	}

	public ParameterValueContent getValue() {
		return value;
	}

	@Override
	public String toString() {
		return shortname + ":" + value.toString();
	}

	protected String getValue(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();

		if (text.startsWith(shortname)) {
			text = text.substring(shortname.length());
			text = text.trim();
		}

		if (text.startsWith(":")) {
			text = text.substring(1);
			text = text.trim();
		}

		return text;
	};

	public abstract ParameterValue parse(String text) throws MGCPParseException;

	public abstract boolean isMapped(String text) throws MGCPParseException;
}
