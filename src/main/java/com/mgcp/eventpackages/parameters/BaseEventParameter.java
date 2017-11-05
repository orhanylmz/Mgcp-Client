package com.mgcp.eventpackages.parameters;

import com.noyan.Base;

public abstract class BaseEventParameter implements Base {
	private String shortcut;
	private String value;

	public BaseEventParameter(String shortcut) {
		this(shortcut, null);
	}

	public BaseEventParameter(String shortcut, String value) {
		this.shortcut = shortcut;
		this.value = value;
	}

	public String getShortcut() {
		return shortcut;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public abstract String getCommentRFC2897();

	@Override
	public String toString() {
		return shortcut + "=" + value;
	}
}