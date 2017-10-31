package com.mgcp.message.parameter.packageList;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.ParameterValue;
import com.noyan.util.NullUtil;

public class PackageListParameterValue extends ParameterValue {
	private static final String finalShortname = "PL";

	public PackageListParameterValue() {
		this(null);
	}

	public PackageListParameterValue(PackageList packageList) {
		super(packageList);
		this.shortname = finalShortname;
	}

	@Override
	public PackageList getValue() {
		return (PackageList) super.getValue();
	}

	@Override
	public PackageListParameterValue parse(String text) throws MGCPParseException {
		text = getValue(text);
		return new PackageListParameterValue(PackageList.parse(text));
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
