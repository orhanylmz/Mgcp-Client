package com.mgcp.message.parameter.connectionParameters;

import com.mgcp.exceptions.MGCPParseException;
import com.noyan.util.NullUtil;

public class ConnectionParameterExtensionName {
	private ConnectionParameterExtensionNameEnum connectionParameterExtensionNameEnum;

	private String packageName;
	private String CPName;
	private String alpha;

	public ConnectionParameterExtensionName(String alpha) {
		this.connectionParameterExtensionNameEnum = ConnectionParameterExtensionNameEnum.VendorCPExtensionName;
		this.alpha = alpha;
	}

	public ConnectionParameterExtensionName(String packageName, String CPName) {
		this.connectionParameterExtensionNameEnum = ConnectionParameterExtensionNameEnum.PackageCPExtensionName;
		this.packageName = packageName;
		this.CPName = CPName;
	}

	public String getAlpha() {
		return alpha;
	}

	public String getPackageName() {
		return packageName;
	}

	public String getCPName() {
		return CPName;
	}

	public ConnectionParameterExtensionNameEnum getConnectionParameterExtensionNameEnum() {
		return connectionParameterExtensionNameEnum;
	}

	@Override
	public String toString() {
		if (connectionParameterExtensionNameEnum.equals(ConnectionParameterExtensionNameEnum.VendorCPExtensionName)) {
			return "X" + "-" + alpha;
		}

		return packageName + "/" + CPName;
	}

	public static ConnectionParameterExtensionName parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();
		if (text.startsWith("X")) {
			text.substring(1);
			text = text.trim();
			if (text.startsWith("-")) {
				text.substring(1);
				text = text.trim();
			}

			return new ConnectionParameterExtensionName(text);
		}

		String[] parts = text.split("[/]");

		if (parts.length < 2) {
			throw new MGCPParseException("parts length must be 2");
		}

		return new ConnectionParameterExtensionName(parts[0].trim(), parts[1].trim());
	}

	public enum ConnectionParameterExtensionNameEnum {
		VendorCPExtensionName, PackageCPExtensionName
	}
}
