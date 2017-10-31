package com.mgcp.message.parameter.capabilities;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.ParameterValueContent;
import com.mgcp.message.parameter.capabilities.supportedModes.SupportedModes;
import com.mgcp.message.parameter.capabilities.supportedPackages.SupportedPackages;
import com.mgcp.message.parameter.localConnectionOptions.LocalOptionValue;
import com.noyan.util.NullUtil;

public class CapabilityValue implements ParameterValueContent {
	private LocalOptionValue localOptionValue;
	private CapabilityValueEnum capabilityValueEnum;
	private SupportedModes supportedModes;
	private SupportedPackages supportedPackages;

	public CapabilityValue(LocalOptionValue localOptionValue) {
		this.capabilityValueEnum = CapabilityValueEnum.localOptionValue;
		this.localOptionValue = localOptionValue;
	}

	public CapabilityValue(SupportedModes supportedModes) {
		this.capabilityValueEnum = CapabilityValueEnum.m;
		this.supportedModes = supportedModes;
	}

	public CapabilityValue(SupportedPackages supportedPackages) {
		this.capabilityValueEnum = CapabilityValueEnum.v;
		this.supportedPackages = supportedPackages;
	}

	public CapabilityValueEnum getCapabilityValueEnum() {
		return capabilityValueEnum;
	}

	public LocalOptionValue getLocalOptionValue() {
		return localOptionValue;
	}

	public SupportedModes getSupportedModes() {
		return supportedModes;
	}

	public SupportedPackages getSupportedPackages() {
		return supportedPackages;
	}

	@Override
	public String toString() {
		if (capabilityValueEnum.equals(CapabilityValueEnum.localOptionValue)) {
			return localOptionValue.toString();
		}

		StringBuilder builder = new StringBuilder(capabilityValueEnum.toString() + ":");

		if (capabilityValueEnum.equals(CapabilityValueEnum.v)) {
			builder.append(supportedPackages.toString());
		} else {
			builder.append(supportedModes.toString());
		}

		return builder.toString();
	}

	public static CapabilityValue parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();

		String[] parts = text.split("[:]");
		if (parts.length == 1) {
			return new CapabilityValue(LocalOptionValue.parse(text));
		}

		try {
			CapabilityValueEnum capabilityValueEnum = CapabilityValueEnum.valueOf(parts[0].trim());
			if (capabilityValueEnum.equals(CapabilityValueEnum.v)) {
				return new CapabilityValue(SupportedPackages.parse(parts[1]));
			}

			if (capabilityValueEnum.equals(CapabilityValueEnum.m)) {
				return new CapabilityValue(SupportedModes.parse(parts[1]));
			}
		} catch (IllegalArgumentException ignored) {
		}

		return new CapabilityValue(LocalOptionValue.parse(parts[1]));
	}

	public enum CapabilityValueEnum {
		localOptionValue, // extends
		v, // supportedPackages
		m,// supportedModes
	}
}
