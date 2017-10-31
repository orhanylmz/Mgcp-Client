package com.mgcp.message.parameter.requestedEvents;

import com.mgcp.exceptions.MGCPParseException;

public class EventParameters {
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	public static EventParameters parse(String text) throws MGCPParseException {
		return new EventParameters();
	}
}
