package com.mgcp.message.parameter.localConnectionOptions.localOptionExtension;

import java.util.ArrayList;
import java.util.Arrays;

import com.mgcp.exceptions.MGCPParseException;
import com.noyan.util.NullUtil;

public class LocalOptionExtensionValue {
	private ArrayList<String> extensionValues = new ArrayList<>();

	public LocalOptionExtensionValue(String... extensionValues) {
		this.extensionValues = new ArrayList<>(Arrays.asList(extensionValues));
	}

	public LocalOptionExtensionValue(ArrayList<String> extensionValues) {
		this.extensionValues = new ArrayList<>(extensionValues);
	}

	public ArrayList<String> getExtensionValues() {
		return extensionValues;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(extensionValues.get(0).toString());
		if (extensionValues.size() == 1) {
			return builder.toString();
		}

		for (int i = 1; i < extensionValues.size(); i++) {
			builder.append(";" + extensionValues.get(i));
		}

		return builder.toString();
	}

	public static LocalOptionExtensionValue parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();

		return new LocalOptionExtensionValue(text.split("[;]"));
	}
}
