package com.mgcp.message.parameter.capabilities.supportedPackages;

import java.util.ArrayList;
import java.util.Arrays;

import com.mgcp.exceptions.MGCPParseException;
import com.noyan.util.NullUtil;

public class SupportedPackages {
	private ArrayList<String> supportedPackageNames = new ArrayList<>();

	public SupportedPackages(String... supportedPackageNames) {
		this.supportedPackageNames = new ArrayList<>(Arrays.asList(supportedPackageNames));
	}

	public SupportedPackages(ArrayList<String> supportedPackageNames) {
		this.supportedPackageNames = new ArrayList<>(supportedPackageNames);
	}

	public void addSupportedPackage(String supportedPackageName) {
		supportedPackageNames.add(supportedPackageName);
	}

	public ArrayList<String> getSupportedPackageNames() {
		return supportedPackageNames;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(supportedPackageNames.get(0));
		for (int i = 1; i < supportedPackageNames.size(); i++) {
			builder.append(";" + supportedPackageNames.get(i));
		}
		return builder.toString();
	}

	public static SupportedPackages parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();
		return new SupportedPackages(text.split("[;]"));
	}

}
