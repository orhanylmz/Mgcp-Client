package com.mgcp.message.parameter.localConnectionOptions.encryptionData;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.localConnectionOptions.BaseLocalOptionValue;
import com.noyan.util.NullUtil;

public class EncryptionData extends BaseLocalOptionValue {
	private EncryptionDataEnum encryptionDataEnum;
	private String value;

	public EncryptionData(EncryptionDataEnum encryptionDataEnum) {
		this(encryptionDataEnum, null);
	}

	public EncryptionData(EncryptionDataEnum encryptionDataEnum, String value) {
		this.encryptionDataEnum = encryptionDataEnum;
		this.value = value;
	}

	public EncryptionDataEnum getEncryptionDataEnum() {
		return encryptionDataEnum;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		if (encryptionDataEnum.equals(EncryptionDataEnum.prompt)) {
			return encryptionDataEnum.toString();
		}

		return encryptionDataEnum.toString() + ":" + value;
	}

	public static EncryptionData parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();

		String[] parts = text.split("[:]");
		EncryptionDataEnum encryptionDataEnum = EncryptionDataEnum.valueOf(parts[0].trim());

		if (parts.length < 2) {
			return new EncryptionData(encryptionDataEnum);
		}

		return new EncryptionData(encryptionDataEnum, parts[1].trim());
	}

	public enum EncryptionDataEnum {
		clear, base64, uri, prompt
	}
}
