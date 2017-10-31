package com.mgcp.message.parameter.connectionParameters;

import java.util.ArrayList;
import java.util.Arrays;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.ParameterValueContent;
import com.noyan.util.NullUtil;

public class ConnectionParameters implements ParameterValueContent {
	private ArrayList<ConnectionParameter> connectionParameters = new ArrayList<>();

	public ConnectionParameters(ArrayList<ConnectionParameter> connectionParameters) {
		this.connectionParameters = connectionParameters;
	}

	public ConnectionParameters(ConnectionParameter... connectionParameters) {
		this.connectionParameters = new ArrayList<>(Arrays.asList(connectionParameters));
	}

	public ArrayList<ConnectionParameter> getConnectionParameters() {
		return connectionParameters;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(connectionParameters.get(0).toString());
		if (connectionParameters.size() == 1) {
			return builder.toString();
		}

		for (int i = 1; i < connectionParameters.size(); i++) {
			builder.append(", " + connectionParameters.get(i));
		}

		return builder.toString();
	}

	public static ConnectionParameters parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();

		String[] parts = text.split("[,]");
		if (NullUtil.isNull(parts)) {
			throw new MGCPParseException("parts can not be null");
		}

		ArrayList<ConnectionParameter> contents = new ArrayList<>();
		for (int i = 0; i < parts.length; i++) {
			ConnectionParameter connectionParameter = ConnectionParameter.parse(parts[i]);
			contents.add(connectionParameter);
		}

		return new ConnectionParameters(contents);
	}
}
