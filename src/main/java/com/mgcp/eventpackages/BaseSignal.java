package com.mgcp.eventpackages;

import com.noyan.Base;

public class BaseSignal implements Base {
	private String shortcut;

	public BaseSignal(String shortcut) {
		this.shortcut = shortcut;
	}

	public String getShortcut() {
		return shortcut;
	}

	@Override
	public String getPrefix() {
		return "[Event-\"" + shortcut + "\"] -> ";
	}
}
