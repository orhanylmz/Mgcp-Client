package com.mgcp.message.parameter.bearerInformation;

import java.util.ArrayList;
import java.util.Arrays;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.ParameterValueContent;
import com.noyan.util.NullUtil;

public class BearerInformation implements ParameterValueContent {
	private ArrayList<BearerAttribute> bearerAttributes = new ArrayList<>();

	public BearerInformation(ArrayList<BearerAttribute> bearerAttributes) {
		this.bearerAttributes = bearerAttributes;
	}

	public BearerInformation(BearerAttribute... bearerAttributes) {
		this.bearerAttributes = new ArrayList<>(Arrays.asList(bearerAttributes));
	}

	public ArrayList<BearerAttribute> getBearerAttributes() {
		return bearerAttributes;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(bearerAttributes.get(0).toString());
		if (bearerAttributes.size() == 1) {
			return builder.toString();
		}

		for (int i = 1; i < bearerAttributes.size(); i++) {
			builder.append(", " + bearerAttributes.get(i));
		}

		return builder.toString();
	}

	public static BearerInformation parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();

		String[] parts = text.split("[,]");
		if (NullUtil.isNull(parts)) {
			throw new MGCPParseException("parts can not be null");
		}

		ArrayList<BearerAttribute> contents = new ArrayList<>();
		for (int i = 0; i < parts.length; i++) {
			BearerAttribute bearerAttribute = BearerAttribute.parse(parts[i]);
			contents.add(bearerAttribute);
		}

		return new BearerInformation(contents);
	}
}
