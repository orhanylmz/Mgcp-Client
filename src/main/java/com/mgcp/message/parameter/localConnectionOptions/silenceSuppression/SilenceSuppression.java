package com.mgcp.message.parameter.localConnectionOptions.silenceSuppression;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.localConnectionOptions.BaseLocalOptionValue;
import com.noyan.util.NullUtil;

public class SilenceSuppression extends BaseLocalOptionValue {
	private SilenceSuppressionEnum silenceSuppressionEnum;

	public SilenceSuppression(SilenceSuppressionEnum silenceSuppressionEnum) {
		this.silenceSuppressionEnum = silenceSuppressionEnum;
	}

	public SilenceSuppressionEnum getValue() {
		return silenceSuppressionEnum;
	}

	@Override
	public String toString() {
		return silenceSuppressionEnum.toString();
	}

	public static SilenceSuppression parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		return new SilenceSuppression(SilenceSuppressionEnum.valueOf(text));
	}

	public enum SilenceSuppressionEnum {
		on, off
	}
}
