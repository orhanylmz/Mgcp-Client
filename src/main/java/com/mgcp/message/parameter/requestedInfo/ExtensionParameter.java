package com.mgcp.message.parameter.requestedInfo;

import com.mgcp.exceptions.MGCPParseException;
import com.noyan.util.NullUtil;

public class ExtensionParameter {
	private ExtensionParameterEnum extensionParameterEnum;
	private String parameter01;
	private String parameter02;
	private String value;

	public ExtensionParameter(ExtensionParameterEnum extensionParameterEnum, String parameter, String value) {
		this.extensionParameterEnum = extensionParameterEnum;

		this.value = value;

		switch (extensionParameterEnum) {
		case VendorExtensionParameter:
			this.parameter02 = parameter;
		case PackageExtensionParameter:
			this.parameter01 = parameter;
		case OtherExtensionParameter:
		default:
			break;
		}
	}

	public ExtensionParameter(String value) {
		this(value, null);
	}

	public ExtensionParameter(String value, String extensionParameterEnum) {
		this.extensionParameterEnum = ExtensionParameterEnum.OtherExtensionParameter;
		this.value = value;
	}

	public ExtensionParameterEnum getExtensionParameterEnum() {
		return extensionParameterEnum;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		switch (extensionParameterEnum) {
		case VendorExtensionParameter:
			builder.append("X" + parameter02 + value);// x(+/-)ch
			break;
		case PackageExtensionParameter:
			builder.append(parameter01 + "/" + value);// packageName/ch
			break;
		case OtherExtensionParameter:
			builder.append(value);
			break;
		default:
			return null;
		}

		return builder.toString();
	}

	public static ExtensionParameter parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();
		if (text.startsWith("x")) {

			text = text.substring("x".length());

			String parameter02 = "+";
			if (text.contains("[-]")) {
				parameter02 = "-";
			}

			String value = text.substring(text.indexOf(parameter02) + parameter02.length());
			return new ExtensionParameter(ExtensionParameterEnum.VendorExtensionParameter, parameter02, value);
		}

		if (text.contains("/")) {
			String[] parts = text.split("/");

			if (NullUtil.isNull(parts)) {
				throw new MGCPParseException("parts can not be null");
			}

			if (parts.length != 2) {
				throw new MGCPParseException("parts length must be 2");
			}

			String parameter01 = parts[0];
			String value = parts[1];

			return new ExtensionParameter(ExtensionParameterEnum.PackageExtensionParameter, parameter01, value);
		}

		return new ExtensionParameter(text);
	}

	public enum ExtensionParameterEnum {
		VendorExtensionParameter, PackageExtensionParameter, OtherExtensionParameter
	}

}
