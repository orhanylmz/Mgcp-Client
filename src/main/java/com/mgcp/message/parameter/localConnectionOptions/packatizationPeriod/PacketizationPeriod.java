package com.mgcp.message.parameter.localConnectionOptions.packatizationPeriod;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.localConnectionOptions.BaseLocalOptionValue;
import com.noyan.util.NullUtil;

public class PacketizationPeriod extends BaseLocalOptionValue {
	private String periodPart01;
	private String periodPart02;

	public PacketizationPeriod(String periodPart01) {
		this(periodPart01, null);
	}

	public PacketizationPeriod(String periodPart01, String periodPart02) {
		this.periodPart01 = periodPart01;
		this.periodPart02 = periodPart02;
	}

	public String getPeriodPart01() {
		return periodPart01;
	}

	public String getPeriodPart02() {
		return periodPart02;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(periodPart01);
		if (NullUtil.isNotNull(periodPart02)) {
			builder.append("-" + periodPart02);
		}

		return builder.toString();
	}

	public static PacketizationPeriod parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();
		String[] parts = text.split("[-]");

		if (parts.length > 1) {
			return new PacketizationPeriod(parts[0].trim(), parts[1].trim());
		}

		return new PacketizationPeriod(parts[0].trim());
	}
}
