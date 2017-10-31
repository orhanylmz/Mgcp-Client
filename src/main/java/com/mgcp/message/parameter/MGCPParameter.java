package com.mgcp.message.parameter;

import com.configuration.Constants;
import com.configuration.GeneralConfiguration;
import com.mgcp.exceptions.MGCPParseException;
import com.noyan.util.NullUtil;

public class MGCPParameter {
	private ParameterValue parameterValue;

	public MGCPParameter(ParameterValue parameterValue) {
		this.parameterValue = parameterValue;
	}

	public ParameterValue getParameterValue() {
		return parameterValue;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(parameterValue.toString());
		if (!builder.toString().endsWith(Constants.EOL)) {
			builder.append(Constants.EOL);
		}

		return builder.toString();
	}

	public static MGCPParameter parse(String text) throws Exception {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		for (int i = 0; i < GeneralConfiguration.priorityOfParameters.length; i++) {
			Class<?> clazz = GeneralConfiguration.priorityOfParameters[i];
			ParameterValue parserObject = (ParameterValue) clazz.newInstance();

			if (!parserObject.isMapped(text)) {
				continue;
			}

			return new MGCPParameter(parserObject.parse(text));
		}

		throw new MGCPParseException("MGCPParameter:\n--\n" + text + "\n--Failed to Parse--\n");
	}
}