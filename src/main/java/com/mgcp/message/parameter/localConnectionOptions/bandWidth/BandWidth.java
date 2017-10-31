package com.mgcp.message.parameter.localConnectionOptions.bandWidth;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.localConnectionOptions.BaseLocalOptionValue;
import com.noyan.util.NullUtil;

public class BandWidth extends BaseLocalOptionValue {
	private String bandWidthPart01;
	private String bandWidthPart02;

	public BandWidth(String bandWidthPart01) {
		this(bandWidthPart01, null);
	}

	public BandWidth(String bandWidthPart01, String bandWidthPart02) {
		this.bandWidthPart01 = bandWidthPart01;
		this.bandWidthPart02 = bandWidthPart02;
	}

	public String getBandWidthPart01() {
		return bandWidthPart01;
	}

	public String getBandWidthPart02() {
		return bandWidthPart02;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(bandWidthPart01);
		if (NullUtil.isNotNull(bandWidthPart02)) {
			builder.append("-" + bandWidthPart02);
		}

		return builder.toString();
	}

	public static BandWidth parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();
		String[] parts = text.split("[-]");

		if (parts.length > 1) {
			return new BandWidth(parts[0].trim(), parts[1].trim());
		}

		return new BandWidth(parts[0].trim());
	}
}
