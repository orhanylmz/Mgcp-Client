package com.mgcp.message.parameter.restartMethod;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.ParameterValueContent;
import com.noyan.util.NullUtil;

public class RestartMethod implements ParameterValueContent {
	private RestartMethodEnum restartMethodEnum;
	private String packageName;
	private String value;

	public RestartMethod(RestartMethodEnum restartMethodEnum) {
		this.restartMethodEnum = restartMethodEnum;
	}

	public RestartMethod(String packageName, String value) {
		this.restartMethodEnum = RestartMethodEnum.extensionRestartMethod;

		this.packageName = packageName;
		this.value = value;
	}

	@Override
	public String toString() {
		if (!restartMethodEnum.equals(RestartMethodEnum.extensionRestartMethod)) {
			return restartMethodEnum.toString();
		}

		return packageName + "/" + value;
	}

	public static RestartMethod parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();

		if (text.equals(RestartMethodEnum.cancel_graceful.toString())) {
			return new RestartMethod(RestartMethodEnum.cancel_graceful);
		}

		try {
			RestartMethodEnum restartMethodEnum = RestartMethodEnum.valueOf(text);
			System.out.println("restartMethodEnum: " + restartMethodEnum);
			return new RestartMethod(restartMethodEnum);
		} catch (IllegalArgumentException ignored) {
		}

		String[] parts = text.split("[/]");
		if (parts.length != 2) {
			throw new MGCPParseException("parts length must be 2");
		}

		return new RestartMethod(parts[0].trim(), parts[1].trim());
	}

	public enum RestartMethodEnum {
		graceful, forced, restart, disconnected, cancel_graceful, extensionRestartMethod;

		@Override
		public String toString() {
			if (this.equals(cancel_graceful)) {
				return "cancel-graceful";
			}

			return super.toString();
		}
	}
}
