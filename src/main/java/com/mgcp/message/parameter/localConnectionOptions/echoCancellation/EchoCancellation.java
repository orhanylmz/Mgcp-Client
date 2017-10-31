package com.mgcp.message.parameter.localConnectionOptions.echoCancellation;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.localConnectionOptions.BaseLocalOptionValue;
import com.noyan.util.NullUtil;

public class EchoCancellation extends BaseLocalOptionValue {
	private EchoCancellationEnum cancellationEnum;

	public EchoCancellation(EchoCancellationEnum cancellationEnum) {
		this.cancellationEnum = cancellationEnum;
	}

	public EchoCancellationEnum getValue() {
		return cancellationEnum;
	}

	@Override
	public String toString() {
		return cancellationEnum.toString();
	}

	public static EchoCancellation parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();

		return new EchoCancellation(EchoCancellationEnum.valueOf(text));
	}

	public enum EchoCancellationEnum {
		on, off
	}
}
