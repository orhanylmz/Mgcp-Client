package com.mgcp.message.parameter.localConnectionOptions.typeOfNetwork;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.localConnectionOptions.BaseLocalOptionValue;
import com.noyan.util.NullUtil;

public class TypeOfNetwork extends BaseLocalOptionValue {
	private TypeOfNetworkEnum typeOfNetworkEnum;
	private String otherNetworkType;

	public TypeOfNetwork(TypeOfNetworkEnum typeOfNetworkEnum) {
		this.typeOfNetworkEnum = typeOfNetworkEnum;
	}

	public TypeOfNetwork(String otherNetworkType) {
		this.typeOfNetworkEnum = TypeOfNetworkEnum.OTHER;
		this.otherNetworkType = otherNetworkType;
	}

	public TypeOfNetworkEnum getTypeOfNetworkEnum() {
		return typeOfNetworkEnum;
	}

	public String getOtherNetworkType() {
		return otherNetworkType;
	}

	@Override
	public String toString() {
		if (typeOfNetworkEnum != TypeOfNetworkEnum.OTHER) {
			return typeOfNetworkEnum.toString();
		}

		return otherNetworkType;
	}

	public static TypeOfNetwork parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();
		try {
			TypeOfNetworkEnum typeOfNetworkEnum = TypeOfNetworkEnum.valueOf(text);
			new TypeOfNetwork(typeOfNetworkEnum);
		} catch (IllegalArgumentException ignored) {
		}

		return new TypeOfNetwork(text);
	}

	public enum TypeOfNetworkEnum {
		IN, ATM, LOCAL, OTHER
	}
}
