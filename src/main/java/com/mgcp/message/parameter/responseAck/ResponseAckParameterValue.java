package com.mgcp.message.parameter.responseAck;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.ParameterValue;
import com.noyan.util.NullUtil;

public class ResponseAckParameterValue extends ParameterValue {
	private static final String finalShortname = "K";

	public ResponseAckParameterValue() {
		this(null);
	}

	public ResponseAckParameterValue(ResponseAck responseAck) {
		super(responseAck);
		this.shortname = finalShortname;
	}

	@Override
	public ResponseAck getValue() {
		return (ResponseAck) super.getValue();
	}

	@Override
	public ResponseAckParameterValue parse(String text) throws MGCPParseException {
		text = getValue(text);
		return new ResponseAckParameterValue(ResponseAck.parse(text));
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
