package com.mgcp.message.parameter.localConnectionOptions.typeOfService;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.localConnectionOptions.BaseLocalOptionValue;
import com.noyan.util.NullUtil;

public class TypeOfService extends BaseLocalOptionValue {
	private String typeOfService;// 1*2HEX

	public TypeOfService(String typeOfService) {
		this.typeOfService = typeOfService;
	}

	public String getTypeOfService() {
		return typeOfService;
	}

	@Override
	public String toString() {
		return typeOfService;
	}

	public static TypeOfService parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		return new TypeOfService(text);
	}
}
