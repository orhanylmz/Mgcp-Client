package com.mgcp.message.parameter.requestIdentifier;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.MGCPParameter;
import com.mgcp.message.parameter.ParameterValue;
import com.mgcp.message.parameter.SimpleValueContent;
import com.noyan.util.NullUtil;
import com.noyan.util.RandomUtil;

public class RequestIdentifierParameterValue extends ParameterValue {
	private static final String finalShortname = "X";

	public RequestIdentifierParameterValue() {
		this(null);
	}

	public RequestIdentifierParameterValue(SimpleValueContent contents) {
		super(contents);
		this.shortname = finalShortname;
	}

	@Override
	public SimpleValueContent getValue() {
		return (SimpleValueContent) super.getValue();
	}

	@Override
	public RequestIdentifierParameterValue parse(String text) throws MGCPParseException {
		text = getValue(text);
		return new RequestIdentifierParameterValue(SimpleValueContent.parse(text));
	}

	public static MGCPParameter generate() {
		return generate(RandomUtil.generateHexDigit32());
	}

	public static MGCPParameter generate(String value) {
		return new MGCPParameter(new RequestIdentifierParameterValue(new SimpleValueContent(value)));
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
