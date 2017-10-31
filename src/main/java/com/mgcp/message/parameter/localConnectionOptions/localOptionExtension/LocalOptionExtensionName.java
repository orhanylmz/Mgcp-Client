package com.mgcp.message.parameter.localConnectionOptions.localOptionExtension;

import com.mgcp.exceptions.MGCPParseException;
import com.noyan.util.NullUtil;

public class LocalOptionExtensionName {
	private LocalOptionExtensionNameEnum localOptionExtensionNameEnum;
	private boolean negative = false;
	private String packageName;
	private String character;

	public LocalOptionExtensionName(Boolean negative, String character) {
		this.localOptionExtensionNameEnum = LocalOptionExtensionNameEnum.VendorLCOExtensionName;
		this.negative = negative;
		this.character = character;
	}

	public LocalOptionExtensionName(String packageName, String character) {
		this.localOptionExtensionNameEnum = LocalOptionExtensionNameEnum.PackageLCOExtensionName;
		this.packageName = packageName;
		this.character = character;
	}

	public LocalOptionExtensionName(String character) {
		this.localOptionExtensionNameEnum = LocalOptionExtensionNameEnum.OtherLCOExtensionName;
		this.character = character;
	}

	public LocalOptionExtensionNameEnum getLocalOptionExtensionNameEnum() {
		return localOptionExtensionNameEnum;
	}

	public boolean isNegative() {
		return negative;
	}

	public String getPackageName() {
		return packageName;
	}

	public String getCharacter() {
		return character;
	}

	@Override
	public String toString() {
		if (localOptionExtensionNameEnum.equals(LocalOptionExtensionNameEnum.VendorLCOExtensionName)) {
			return "x" + (negative ? "-" : "+") + character;
		}

		if (localOptionExtensionNameEnum.equals(LocalOptionExtensionNameEnum.PackageLCOExtensionName)) {
			return packageName + "/" + character;
		}

		return character;
	}

	public static LocalOptionExtensionName parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();
		if (text.startsWith("x")) {
			text = text.substring(1).trim();

			boolean negative = false;
			if (text.startsWith("-")) {
				negative = true;
			}
			text = text.substring(1).trim();
			String character = text;
			return new LocalOptionExtensionName(negative, character);
		}

		if (!text.contains("/")) {
			return new LocalOptionExtensionName(text);
		}

		String[] parts = text.split("[/]");
		if (parts.length < 2) {
			throw new MGCPParseException("parts length must be gt 1");
		}

		return new LocalOptionExtensionName(parts[0].trim(), parts[1].trim());
	}

	public enum LocalOptionExtensionNameEnum {
		VendorLCOExtensionName, PackageLCOExtensionName, OtherLCOExtensionName
	}
}
