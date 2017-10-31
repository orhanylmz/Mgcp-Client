package com.mgcp.message.parameter.requestedEvents;

import com.mgcp.exceptions.MGCPParseException;
import com.noyan.util.NullUtil;

public class RequestedAction {
	private RequestedActionEnum requestedActionEnum;
	private EmbeddedRequest embeddedRequest;
	private ExtensionAction extensionAction;

	public RequestedAction(RequestedActionEnum requestedActionEnum) {
		this.requestedActionEnum = requestedActionEnum;
	}

	public RequestedAction(EmbeddedRequest embeddedRequest) {
		this.requestedActionEnum = RequestedActionEnum.E;
		this.embeddedRequest = embeddedRequest;
	}

	public RequestedAction(ExtensionAction extensionAction) {
		this.requestedActionEnum = RequestedActionEnum.extensionAction;
		this.extensionAction = extensionAction;
	}

	@Override
	public String toString() {
		if (requestedActionEnum.equals(RequestedActionEnum.extensionAction)) {
			return extensionAction.toString();
		}

		StringBuilder builder = new StringBuilder(requestedActionEnum.toString());
		if (requestedActionEnum.equals(RequestedActionEnum.E)) {
			builder.append("(" + embeddedRequest.toString() + ")");
		}

		return builder.toString();
	}

	public static RequestedAction parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();

		try {
			RequestedActionEnum requestedActionEnum = RequestedActionEnum.valueOf(text);
			return new RequestedAction(requestedActionEnum);
		} catch (IllegalArgumentException e) {
			// ýgnored
		}

		if (text.startsWith(RequestedActionEnum.E.toString())) {
			text = text.substring(1).trim();
			return new RequestedAction(EmbeddedRequest.parse(text));
		}

		return new RequestedAction(ExtensionAction.parse(text));
	}

	public enum RequestedActionEnum {
		N, A, D, S, I, K, E, extensionAction
	}

}
