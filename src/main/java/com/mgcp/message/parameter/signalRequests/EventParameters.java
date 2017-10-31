package com.mgcp.message.parameter.signalRequests;

import java.util.ArrayList;
import java.util.Arrays;

import com.mgcp.exceptions.MGCPParseException;
import com.noyan.util.NullUtil;

public class EventParameters {
	private ArrayList<EventParameter> eventParameters = new ArrayList<>();

	public EventParameters(ArrayList<EventParameter> eventParameters) {
		this.eventParameters = eventParameters;
	}

	public EventParameters(EventParameter... eventParameters) {
		this.eventParameters = new ArrayList<>(Arrays.asList(eventParameters));
	}

	public ArrayList<EventParameter> getEventParameters() {
		return eventParameters;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(eventParameters.get(0).toString());
		if (eventParameters.size() == 1) {
			return builder.toString();
		}

		for (int i = 1; i < eventParameters.size(); i++) {
			builder.append(", " + eventParameters.get(i));
		}

		return builder.toString();
	}

	public static EventParameters parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();

		String[] parts = text.split("[,]");
		if (NullUtil.isNull(parts)) {
			throw new MGCPParseException("parts can not be null");
		}

		ArrayList<EventParameter> contents = new ArrayList<>();
		for (int i = 0; i < parts.length; i++) {
			EventParameter eventParameter = EventParameter.parse(parts[i]);
			contents.add(eventParameter);
		}

		return new EventParameters(contents);
	}
}
