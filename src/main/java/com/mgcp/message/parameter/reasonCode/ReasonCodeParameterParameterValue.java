package com.mgcp.message.parameter.reasonCode;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.ParameterValue;
import com.noyan.util.NullUtil;

public class ReasonCodeParameterParameterValue extends ParameterValue {
	private static final String finalShortname = "E";

	public ReasonCodeParameterParameterValue() {
		this(null);
	}

	public ReasonCodeParameterParameterValue(ReasonCode reasonCode) {
		super(reasonCode);
		this.shortname = finalShortname;
	}

	@Override
	public ReasonCode getValue() {
		return (ReasonCode) super.getValue();
	}

	@Override
	public ReasonCodeParameterParameterValue parse(String text) throws MGCPParseException {
		text = getValue(text);
		return new ReasonCodeParameterParameterValue(ReasonCode.parse(text));
	}

	@Override
	public boolean isMapped(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();

		if (!text.startsWith(finalShortname)) {
			return false;
		}
		
		text = text.substring(finalShortname.length()).trim();
		if (!text.startsWith(":")) {
			return false;
		}

		return true;
	}
}
