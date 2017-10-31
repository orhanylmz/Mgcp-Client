package com.mgcp.message.parameter.connectionId;

import java.util.ArrayList;
import java.util.Arrays;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.ParameterValueContent;
import com.mgcp.message.parameter.SimpleValueContent;
import com.noyan.util.NullUtil;

public class ConnectionId implements ParameterValueContent {
	private ArrayList<SimpleValueContent> connectionIds = new ArrayList<>();

	public ConnectionId(ArrayList<SimpleValueContent> connectionIds) {
		this.connectionIds = connectionIds;
	}

	public ConnectionId(SimpleValueContent... connectionIds) {
		this.connectionIds = new ArrayList<>(Arrays.asList(connectionIds));
	}

	public ArrayList<SimpleValueContent> getConnectionIds() {
		return connectionIds;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(connectionIds.get(0).toString());
		if (connectionIds.size() == 1) {
			return builder.toString();
		}

		for (int i = 1; i < connectionIds.size(); i++) {
			builder.append(", " + connectionIds.get(i));
		}

		return builder.toString();
	}

	public static ConnectionId parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();

		String[] parts = text.split("[,]");
		if (NullUtil.isNull(parts)) {
			throw new MGCPParseException("parts can not be null");
		}

		ArrayList<SimpleValueContent> contents = new ArrayList<>();
		for (int i = 0; i < parts.length; i++) {
			SimpleValueContent connectionId = SimpleValueContent.parse(parts[i]);
			contents.add(connectionId);
		}

		return new ConnectionId(contents);
	}

}
