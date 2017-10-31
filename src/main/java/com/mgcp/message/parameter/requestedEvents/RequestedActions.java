package com.mgcp.message.parameter.requestedEvents;

import java.util.ArrayList;
import java.util.Arrays;

import com.mgcp.exceptions.MGCPParseException;
import com.noyan.util.NullUtil;

public class RequestedActions {
	ArrayList<RequestedAction> requestedActions = new ArrayList<>();

	public RequestedActions(RequestedAction... requestedActions) {
		this.requestedActions = new ArrayList<>(Arrays.asList(requestedActions));
	}

	public RequestedActions(ArrayList<RequestedAction> requestedActions) {
		this.requestedActions = new ArrayList<>(requestedActions);
	}

	public ArrayList<RequestedAction> getRequestedActions() {
		return requestedActions;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(requestedActions.get(0).toString());
		for (int i = 1; i < requestedActions.size(); i++) {
			builder.append("," + requestedActions.get(i));
		}

		return builder.toString();
	}

	public static RequestedActions parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();
		String[] parts = text.split(",");
		if (NullUtil.isNull(parts)) {
			throw new MGCPParseException("parts can not be null");
		}

		ArrayList<RequestedAction> requestedActions = new ArrayList<>();
		for (int i = 0; i < parts.length; i++) {
			RequestedAction requestedAction = RequestedAction.parse(parts[i].trim());
			if (NullUtil.isNotNull(requestedAction)) {
				requestedActions.add(requestedAction);
			}
		}

		return new RequestedActions(requestedActions);
	}

}
