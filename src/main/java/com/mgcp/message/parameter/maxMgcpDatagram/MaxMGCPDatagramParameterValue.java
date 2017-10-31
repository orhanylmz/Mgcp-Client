package com.mgcp.message.parameter.maxMgcpDatagram;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.ParameterValue;
import com.mgcp.message.parameter.SimpleValueContent;
import com.noyan.util.NullUtil;

public class MaxMGCPDatagramParameterValue extends ParameterValue {
	private static final String finalShortname = "MD";// MG/MD

	public MaxMGCPDatagramParameterValue() {
		this(null);
	}

	public MaxMGCPDatagramParameterValue(SimpleValueContent maxMgcpDatagram) {
		super(maxMgcpDatagram);
		this.shortname = finalShortname; // MG/MD
	}

	@Override
	public SimpleValueContent getValue() {
		return (SimpleValueContent) super.getValue();
	}

	@Override
	public MaxMGCPDatagramParameterValue parse(String text) throws MGCPParseException {
		text = getValue(text);
		return new MaxMGCPDatagramParameterValue(SimpleValueContent.parse(text));
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
