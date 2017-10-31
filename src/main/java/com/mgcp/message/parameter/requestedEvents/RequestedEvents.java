package com.mgcp.message.parameter.requestedEvents;

import java.util.ArrayList;
import java.util.Arrays;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.ParameterValueContent;
import com.noyan.util.NullUtil;

public class RequestedEvents implements ParameterValueContent {
	private ArrayList<RequestedEvent> requestedEvents = new ArrayList<>();

	public RequestedEvents(ArrayList<RequestedEvent> requestedEvents) {
		this.requestedEvents = requestedEvents;
	}

	public RequestedEvents(RequestedEvent... requestedEvents) {
		this.requestedEvents = new ArrayList<>(Arrays.asList(requestedEvents));
	}

	public ArrayList<RequestedEvent> getRequestedEvents() {
		return requestedEvents;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(requestedEvents.get(0).toString());
		if (requestedEvents.size() == 1) {
			return builder.toString();
		}

		for (int i = 1; i < requestedEvents.size(); i++) {
			builder.append(", " + requestedEvents.get(i));
		}

		return builder.toString();
	}

	public static RequestedEvents parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();

		String[] parts = text.split("[,]");
		if (NullUtil.isNull(parts)) {
			throw new MGCPParseException("parts can not be null");
		}

		ArrayList<RequestedEvent> contents = new ArrayList<>();
		for (int i = 0; i < parts.length; i++) {
			RequestedEvent requestedEvent = RequestedEvent.parse(parts[i]);
			contents.add(requestedEvent);
		}

		return new RequestedEvents(contents);
	}
}
