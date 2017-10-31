package com.mgcp.message.parameter.packageList;

import java.util.ArrayList;
import java.util.Arrays;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.ParameterValueContent;
import com.noyan.util.NullUtil;

public class PackageList implements ParameterValueContent {
	private ArrayList<PkgNameAndVers> pkgNameAndVers = new ArrayList<>();

	public PackageList(ArrayList<PkgNameAndVers> pkgNameAndVers) {
		this.pkgNameAndVers = pkgNameAndVers;
	}

	public PackageList(PkgNameAndVers... pkgNameAndVers) {
		this.pkgNameAndVers = new ArrayList<>(Arrays.asList(pkgNameAndVers));
	}

	public ArrayList<PkgNameAndVers> getPkgNameAndVers() {
		return pkgNameAndVers;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(pkgNameAndVers.get(0).toString());
		if (pkgNameAndVers.size() == 1) {
			return builder.toString();
		}

		for (int i = 1; i < pkgNameAndVers.size(); i++) {
			builder.append(", " + pkgNameAndVers.get(i));
		}

		return builder.toString();
	}

	public static PackageList parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();

		String[] parts = text.split("[,]");
		if (NullUtil.isNull(parts)) {
			throw new MGCPParseException("parts can not be null");
		}

		ArrayList<PkgNameAndVers> contents = new ArrayList<>();
		for (int i = 0; i < parts.length; i++) {
			PkgNameAndVers pkgNameAndVers = PkgNameAndVers.parse(parts[i]);
			contents.add(pkgNameAndVers);
		}

		return new PackageList(contents);
	}
}
