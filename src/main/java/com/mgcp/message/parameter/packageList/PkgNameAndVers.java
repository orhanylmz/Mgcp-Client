package com.mgcp.message.parameter.packageList;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.ParameterValueContent;
import com.noyan.util.NullUtil;

public class PkgNameAndVers implements ParameterValueContent {
	private String packageName;
	private String packageVersion;

	public PkgNameAndVers(String packageName, String packageVersion) {
		this.packageName = packageName;
		this.packageVersion = packageVersion;
	}

	@Override
	public String toString() {
		return packageName + ":" + packageVersion;
	}

	public static PkgNameAndVers parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		String[] parts = text.split(":");

		if (parts.length != 2) {
			throw new MGCPParseException("parts length must be 2");
		}

		return new PkgNameAndVers(parts[0].trim(), parts[1].trim());
	}

}
