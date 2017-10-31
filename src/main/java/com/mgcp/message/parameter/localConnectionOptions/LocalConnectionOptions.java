package com.mgcp.message.parameter.localConnectionOptions;

import java.util.ArrayList;
import java.util.Arrays;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.ParameterValueContent;
import com.noyan.util.NullUtil;

public class LocalConnectionOptions implements ParameterValueContent {
	private ArrayList<LocalOptionValue> localOptionValues = new ArrayList<>();

	public LocalConnectionOptions(ArrayList<LocalOptionValue> localOptionValues) {
		this.localOptionValues = localOptionValues;
	}

	public LocalConnectionOptions(LocalOptionValue... localOptionValues) {
		this.localOptionValues = new ArrayList<>(Arrays.asList(localOptionValues));
	}

	public ArrayList<LocalOptionValue> getLocalOptionValues() {
		return localOptionValues;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(localOptionValues.get(0).toString());
		if (localOptionValues.size() == 1) {
			return builder.toString();
		}

		for (int i = 1; i < localOptionValues.size(); i++) {
			builder.append(", " + localOptionValues.get(i));
		}

		return builder.toString();
	}

	public static LocalConnectionOptions parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();

		String[] parts = text.split("[,]");

		ArrayList<LocalOptionValue> contents = new ArrayList<>();
		for (int i = 0; i < parts.length; i++) {
			LocalOptionValue localOptionValue = LocalOptionValue.parse(parts[i]);
			contents.add(localOptionValue);
		}

		return new LocalConnectionOptions(contents);
	}
}
