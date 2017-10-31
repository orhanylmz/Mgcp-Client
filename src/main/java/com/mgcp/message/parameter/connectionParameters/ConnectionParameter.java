package com.mgcp.message.parameter.connectionParameters;

import com.mgcp.exceptions.MGCPParseException;
import com.noyan.util.NullUtil;

public class ConnectionParameter {
	private ConnectionParametersParameterValueEnum connectionParametersParameterValueEnum;
	private String value;

	private ConnectionParameterExtensionName connectionParameterExtensionName;
	private String connectionParameterExtensionValue;

	public ConnectionParameter(ConnectionParametersParameterValueEnum connectionParametersParameterValueEnum, String value) {
		this.connectionParametersParameterValueEnum = connectionParametersParameterValueEnum;
		this.value = value;
	}

	public ConnectionParameter(ConnectionParameterExtensionName connectionParameterExtensionName, String connectionParameterExtensionValue) {
		this.connectionParametersParameterValueEnum = ConnectionParametersParameterValueEnum.extensionName;

		this.connectionParameterExtensionName = connectionParameterExtensionName;
		this.connectionParameterExtensionValue = connectionParameterExtensionValue;
	}

	public ConnectionParametersParameterValueEnum getConnectionParametersParameterValueEnum() {
		return connectionParametersParameterValueEnum;
	}

	public ConnectionParameterExtensionName getConnectionParameterExtensionName() {
		return connectionParameterExtensionName;
	}

	public String getConnectionParameterExtensionValue() {
		return connectionParameterExtensionValue;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		if (!connectionParametersParameterValueEnum.equals(ConnectionParametersParameterValueEnum.extensionName)) {
			return connectionParametersParameterValueEnum + "=" + value;
		}

		return connectionParameterExtensionName + "=" + connectionParameterExtensionValue;
	}

	public static ConnectionParameter parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();
		String enumText = text.substring(0, 2);

		try {
			ConnectionParametersParameterValueEnum valueEnum = ConnectionParametersParameterValueEnum.valueOf(enumText);
			text = text.substring(2);
			text.trim();

			if (text.startsWith("=")) {
				text.substring(1);
			}

			return new ConnectionParameter(valueEnum, text.trim());
		} catch (IllegalArgumentException ignored) {
		}

		String[] parts = text.split("[=]");
		ConnectionParameterExtensionName connectionParameterExtensionName = ConnectionParameterExtensionName.parse(parts[0]);

		return new ConnectionParameter(connectionParameterExtensionName, parts[1]);
	}

	public enum ConnectionParametersParameterValueEnum {
		PS, OS, PR, OR, PL, JI, LA, extensionName
	}
}
