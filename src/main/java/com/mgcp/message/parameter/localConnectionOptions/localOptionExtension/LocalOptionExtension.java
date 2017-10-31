package com.mgcp.message.parameter.localConnectionOptions.localOptionExtension;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.localConnectionOptions.BaseLocalOptionValue;
import com.noyan.util.NullUtil;

public class LocalOptionExtension extends BaseLocalOptionValue {
	private LocalOptionExtensionName localOptionExtensionName;
	private LocalOptionExtensionValue localOptionExtensionValue;

	public LocalOptionExtension(LocalOptionExtensionName localOptionExtensionName) {
		this(localOptionExtensionName, null);
	}

	public LocalOptionExtension(LocalOptionExtensionName localOptionExtensionName, LocalOptionExtensionValue localOptionExtensionValue) {
		this.localOptionExtensionName = localOptionExtensionName;
		this.localOptionExtensionValue = localOptionExtensionValue;
	}

	public LocalOptionExtensionName getLocalOptionExtensionName() {
		return localOptionExtensionName;
	}

	public LocalOptionExtensionValue getLocalOptionExtensionValue() {
		return localOptionExtensionValue;
	}

	@Override
	public String toString() {
		if (NullUtil.isNull(localOptionExtensionValue)) {
			return localOptionExtensionName.toString();
		}

		return localOptionExtensionName.toString() + ":" + localOptionExtensionValue.toString();
	}

	public static LocalOptionExtension parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();

		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();

		String[] parts = text.split("[:]");
		if (parts.length < 2) {
			return new LocalOptionExtension(LocalOptionExtensionName.parse(parts[0]));
		}

		return new LocalOptionExtension(LocalOptionExtensionName.parse(parts[0]), LocalOptionExtensionValue.parse(parts[1]));
	}
}
