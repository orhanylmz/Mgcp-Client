package com.mgcp.message.command.commandLine.endpointName.localEndpointName;

import java.util.ArrayList;
import java.util.Arrays;

import com.mgcp.exceptions.MGCPParseException;
import com.noyan.util.NullUtil;

public class LocalEndpointName {
	private ArrayList<LocalNamePart> localNameParts = new ArrayList<>();

	public LocalEndpointName(LocalNamePart... localNameParts) {
		this.localNameParts = new ArrayList<>(Arrays.asList(localNameParts));
	}

	public LocalEndpointName(ArrayList<LocalNamePart> localNameParts) {
		this.localNameParts = new ArrayList<>(localNameParts);
	}

	public void addValuePart(LocalNamePart valuePart) {
		localNameParts.add(valuePart);
	}

	public ArrayList<LocalNamePart> getLocalNameParts() {
		return localNameParts;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(localNameParts.get(0).toString());
		for (int i = 1; i < localNameParts.size(); i++) {
			builder.append("/" + localNameParts.get(i));
		}

		return builder.toString();
	}

	public static LocalEndpointName parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();

		String[] parts = text.split("/");
		if (NullUtil.isNull(parts)) {
			throw new MGCPParseException("parts can not be null");
		}

		ArrayList<LocalNamePart> localNameParts = new ArrayList<>();
		for (int i = 0; i < parts.length; i++) {
			LocalNamePart localNamePart = LocalNamePart.parse(parts[i]);

			if (NullUtil.isNotNull(localNamePart)) {
				localNameParts.add(localNamePart);
			}
		}

		return new LocalEndpointName(localNameParts);
	}
}
