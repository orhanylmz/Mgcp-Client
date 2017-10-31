package com.mgcp.message.parameter.restartDelay;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.ParameterValue;
import com.mgcp.message.parameter.SimpleValueContent;
import com.noyan.util.NullUtil;

public class RestartDelayParameterValue extends ParameterValue {
	private static final String finalShortname = "RD";

	public RestartDelayParameterValue() {
		this(null);
	}

	public RestartDelayParameterValue(SimpleValueContent restartDelay) {
		super(restartDelay);
		this.shortname = finalShortname;
	}

	@Override
	public SimpleValueContent getValue() {
		return (SimpleValueContent) super.getValue();
	}

	@Override
	public RestartDelayParameterValue parse(String text) throws MGCPParseException {
		text = getValue(text);
		return new RestartDelayParameterValue(SimpleValueContent.parse(text));
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
