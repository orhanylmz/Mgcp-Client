package com.mgcp.message.parameter.requestedEvents;

import com.mgcp.exceptions.MGCPParseException;
import com.noyan.util.NullUtil;

public class ExtensionAction {
	private String packageName;
	private String action;
	private EventParameters actionParameters;

	public ExtensionAction(String packageName, String action) {
		this(packageName, action, null);
	}

	public ExtensionAction(String packageName, String action, EventParameters actionParameters) {
		this.packageName = packageName;
		this.action = action;
		this.actionParameters = actionParameters;
	}

	public String getPackageName() {
		return packageName;
	}

	public String getAction() {
		return action;
	}

	public EventParameters getActionParameters() {
		return actionParameters;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(packageName + "/" + action);
		if (NullUtil.isNotNull(actionParameters)) {
			builder.append("(" + actionParameters.toString() + ")");
		}

		return builder.toString();
	}

	public static ExtensionAction parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();
		text = text.replaceAll("[(]", "|");
		text = text.replaceAll("[)]", "|");
		text = text.replaceAll("[/]", "|");

		String[] parts = text.split("[|]");
		if (NullUtil.isNull(parts)) {
			throw new MGCPParseException("parts can not be null");
		}

		if (parts.length < 2) {
			throw new MGCPParseException("parts length must be gt 1");
		}

		String packageName = parts[0].trim();
		String action = parts[1].trim();
		EventParameters actionParameters = null;

		if (parts.length > 2) {
			actionParameters = EventParameters.parse(parts[2].trim());
		}

		return new ExtensionAction(packageName, action, actionParameters);
	}
}
