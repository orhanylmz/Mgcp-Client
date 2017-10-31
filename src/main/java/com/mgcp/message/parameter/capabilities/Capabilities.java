package com.mgcp.message.parameter.capabilities;

import java.util.ArrayList;
import java.util.Arrays;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.ParameterValueContent;
import com.noyan.util.NullUtil;

public class Capabilities implements ParameterValueContent {
	private ArrayList<CapabilityValue> capabilityValues = new ArrayList<>();

	public Capabilities(ArrayList<CapabilityValue> capabilityValues) {
		this.capabilityValues = capabilityValues;
	}

	public Capabilities(CapabilityValue... capabilityValues) {
		this.capabilityValues = new ArrayList<>(Arrays.asList(capabilityValues));
	}

	public ArrayList<CapabilityValue> getCapabilityValues() {
		return capabilityValues;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(capabilityValues.get(0).toString());
		if (capabilityValues.size() == 1) {
			return builder.toString();
		}

		for (int i = 1; i < capabilityValues.size(); i++) {
			builder.append(", " + capabilityValues.get(i));
		}

		return builder.toString();
	}

	public static Capabilities parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();

		String[] parts = text.split("[,]");
		if (NullUtil.isNull(parts)) {
			throw new MGCPParseException("parts can not be null");
		}

		ArrayList<CapabilityValue> contents = new ArrayList<>();
		for (int i = 0; i < parts.length; i++) {
			CapabilityValue capabilityValue = CapabilityValue.parse(parts[i]);
			contents.add(capabilityValue);
		}

		return new Capabilities(contents);
	}
}
