package com.mgcp.message.parameter.connectionMode;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.ParameterValueContent;
import com.noyan.util.NullUtil;

public class ConnectionMode implements ParameterValueContent {
	private ConnectionModes connectionMode;
	private String packageName;
	private String other;

	public ConnectionMode(ConnectionModes connectionMode) {
		this.connectionMode = connectionMode;
	}

	public ConnectionMode(String packageName, String other) {
		this.connectionMode = ConnectionModes.extensionConnectionMode;
		this.packageName = packageName;
		this.other = other;
	}

	@Override
	public String toString() {
		if (connectionMode.equals(ConnectionModes.extensionConnectionMode)) {
			return packageName + "/" + other;
		}

		return connectionMode.toString();
	}

	public ConnectionModes getConnectionMode() {
		return connectionMode;
	}

	public String getPackageName() {
		return packageName;
	}

	public String getOther() {
		return other;
	}

	public static ConnectionMode parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();

		try {
			ConnectionModes connectionMode = ConnectionModes.valueOf(text);
			return new ConnectionMode(connectionMode);
		} catch (IllegalArgumentException ignored) {
		}

		String[] parts = text.split("[/]");
		if (NullUtil.isNull(parts)) {
			throw new MGCPParseException("parts can not be null");
		}

		if (parts.length != 2) {
			throw new MGCPParseException("parts length must be 2");
		}

		return new ConnectionMode(parts[0].trim(), parts[1].trim());

	}

	public enum ConnectionModes {
		sendonly, recvonly, sendrecv, confrnce, inactive, loopback, conttest, netwloop, netwtest, extensionConnectionMode
	}
}
