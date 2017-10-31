package com.mgcp.message.parameter.bearerInformation;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.ParameterValueContent;
import com.noyan.util.NullUtil;

public class BearerAttribute implements ParameterValueContent {
	private BearerEncoding bearerEncoding;
	private String bearerExtensionName;
	private String bearerExtensionValue;

	public BearerAttribute(BearerEncoding bearerEncoding) {
		this.bearerEncoding = bearerEncoding;
	}

	public BearerAttribute(String bearerExtensionName) {
		this.bearerExtensionName = bearerExtensionName;
	}

	public BearerAttribute(String bearerExtensionName, String bearerExtensionValue) {
		this.bearerExtensionName = bearerExtensionName;
		this.bearerExtensionValue = bearerExtensionValue;
	}

	@Override
	public String toString() {
		if (NullUtil.isNotNull(bearerEncoding)) {
			return "e: " + bearerEncoding.toString();
		}

		StringBuilder builder = new StringBuilder(bearerExtensionName);
		if (NullUtil.isNotNull(bearerExtensionValue)) {
			builder.append(":" + bearerExtensionValue);
		}

		return builder.toString();
	}

	public static BearerAttribute parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();

		if (text.startsWith("e:")) {
			String bearerEncodingText = text.substring("e:".length());
			return new BearerAttribute(BearerEncoding.valueOf(bearerEncodingText.trim()));
		}

		String[] parts = text.split(":");
		if (NullUtil.isNull(parts)) {
			throw new MGCPParseException("parts can not be null");
		}

		String bearerExtensionName = parts[0];
		String bearerExtensionValue = null;
		if (parts.length > 1) {
			bearerExtensionValue = parts[1];
		}

		return new BearerAttribute(bearerExtensionName, bearerExtensionValue);
	}

	public enum BearerEncoding {
		A, mu
	}
}
