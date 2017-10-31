package com.mgcp.message.parameter.localConnectionOptions;

import java.util.ArrayList;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.MGCPParameter;
import com.mgcp.message.parameter.ParameterValue;
import com.mgcp.message.parameter.localConnectionOptions.LocalOptionValue.LocalOptionValueEnum;
import com.mgcp.message.parameter.localConnectionOptions.compressionAlgorithm.CompressionAlgorithm;
import com.mgcp.message.parameter.localConnectionOptions.packatizationPeriod.PacketizationPeriod;
import com.noyan.util.NullUtil;

public class LocalConnectionOptionsParameterValue extends ParameterValue {
	private static final String finalShortname = "L";

	public LocalConnectionOptionsParameterValue() {
		this(null);
	}

	public LocalConnectionOptionsParameterValue(LocalConnectionOptions localConnectionOptions) {
		super(localConnectionOptions);
		this.shortname = finalShortname;
	}

	@Override
	public LocalConnectionOptions getValue() {
		return (LocalConnectionOptions) super.getValue();
	}

	@Override
	public LocalConnectionOptionsParameterValue parse(String text) throws MGCPParseException {
		text = getValue(text);
		return new LocalConnectionOptionsParameterValue(LocalConnectionOptions.parse(text));
	}

	public static MGCPParameter generate(int ptime, String... compressionAlgorithms) {
		ArrayList<LocalOptionValue> localOptionValues = new ArrayList<>();
		localOptionValues.add(new LocalOptionValue(LocalOptionValueEnum.p, new PacketizationPeriod(ptime + "")));
		localOptionValues.add(new LocalOptionValue(LocalOptionValueEnum.a, new CompressionAlgorithm(compressionAlgorithms)));

		return new MGCPParameter(new LocalConnectionOptionsParameterValue(new LocalConnectionOptions(localOptionValues)));
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
