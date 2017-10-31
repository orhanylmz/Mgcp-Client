package com.mgcp.message.parameter.general;

import com.mgcp.exceptions.MGCPParseException;
import com.noyan.util.NullUtil;

public class EventName {
	private String parameter01;// [packageName],*
	private String parameter02;// [eventId],all,[eventRange],*,#

	public EventName(String parameter02) {
		this.parameter02 = parameter02;
	}

	public EventName(String parameter01, String parameter02) {
		this.parameter01 = parameter01;
		this.parameter02 = parameter02;
	}

	public String getParameter01() {
		return parameter01;
	}

	public String getParameter02() {
		return parameter02;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (NullUtil.isNotNull(parameter01)) {
			builder.append(parameter01 + "/");
		}

		builder.append(parameter02);
		return builder.toString();
	}

	public static EventName parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();

		String[] parts = text.split("[/]");
		if (NullUtil.isNull(parts)) {
			throw new MGCPParseException("parts can not be null");
		}

		if (parts.length == 1) {
			return new EventName(parts[0].trim());
		}

		return new EventName(parts[0].trim(), parts[1].trim());
	}

}
