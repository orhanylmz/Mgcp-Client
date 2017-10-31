package com.mgcp.message.parameter.capabilities.supportedModes;

import java.util.ArrayList;
import java.util.Arrays;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.connectionMode.ConnectionMode;
import com.noyan.util.NullUtil;

public class SupportedModes {
	private ArrayList<ConnectionMode> supportedModes = new ArrayList<>();

	public SupportedModes(ArrayList<ConnectionMode> supportedModes) {
		this.supportedModes = supportedModes;
	}

	public SupportedModes(ConnectionMode... supportedModes) {
		this.supportedModes = new ArrayList<>(Arrays.asList(supportedModes));
	}

	public ArrayList<ConnectionMode> getSupportedModes() {
		return supportedModes;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(supportedModes.get(0).toString());
		if (supportedModes.size() == 1) {
			return builder.toString();
		}

		for (int i = 1; i < supportedModes.size(); i++) {
			builder.append(";" + supportedModes.get(i));
		}

		return builder.toString();
	}

	public static SupportedModes parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();

		String[] parts = text.split("[;]");
		if (NullUtil.isNull(parts)) {
			throw new MGCPParseException("parts can not be null");
		}

		ArrayList<ConnectionMode> contents = new ArrayList<>();
		for (int i = 0; i < parts.length; i++) {
			ConnectionMode connectionMode = ConnectionMode.parse(parts[i]);
			contents.add(connectionMode);
		}

		return new SupportedModes(contents);
	}

}
