package com.mgcp.message.parameter.localConnectionOptions.gainControl;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.localConnectionOptions.BaseLocalOptionValue;
import com.noyan.util.NullUtil;

public class GainControl extends BaseLocalOptionValue {
	private boolean auto;
	private boolean negative = false;
	private String value;

	public GainControl() {
		this.auto = true;
	}

	public GainControl(String value) {
		this(false, value);
	}

	public GainControl(Boolean negative, String value) {
		this.negative = negative;
		this.value = value;
	}

	public boolean isAuto() {
		return auto;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		if (auto) {
			return "auto";
		}

		if (negative) {
			return "-" + value;
		}

		return value;
	}

	public static GainControl parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();
		if (text.equals("auto")) {
			return new GainControl();
		}

		if (text.startsWith("-")) {
			return new GainControl(true, text.substring(1).trim());
		}

		return new GainControl(text);
	}
}
