package com.mgcp.message.parameter.extension;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.ParameterValue;
import com.mgcp.message.parameter.ParameterValueContent;
import com.mgcp.message.parameter.requestedInfo.ExtensionParameter;
import com.noyan.util.NullUtil;

public class ExtensionParameterValue extends ParameterValue {
	private ExtensionParameter extensionParameter;
	private String parameterString;

	public ExtensionParameterValue() {
	}

	public ExtensionParameterValue(ExtensionParameter extensionParameter, String parameterString) {
		this.extensionParameter = extensionParameter;
		this.parameterString = parameterString;
	}

	public ExtensionParameter getExtensionParameter() {
		return extensionParameter;
	}

	public String getParameterString() {
		return parameterString;
	}

	@Override
	public ParameterValueContent getValue() {
		// TODO: FIX ME
		return null;
	}

	@Override
	public String toString() {
		return extensionParameter.toString() + ":" + parameterString;
	}

	@Override
	public ExtensionParameterValue parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();

		String[] parts = text.split("[:]");
		if (parts.length < 2) {
			throw new MGCPParseException("parts length must be 2");
		}

		return new ExtensionParameterValue(ExtensionParameter.parse(parts[0]), parts[1].trim());
	}

	@Override
	public boolean isMapped(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();

		return text.contains(":");
	}
}
