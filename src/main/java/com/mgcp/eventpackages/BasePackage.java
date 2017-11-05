package com.mgcp.eventpackages;

import com.noyan.util.NullUtil;

public abstract class BasePackage {
	private String shortcut;
	private BaseSignal signal;

	public BasePackage(String shortcut,BaseSignal signal) {
		this.shortcut = shortcut;
		this.signal = signal;
	}
	
	public String getShortcut() {
		return shortcut;
	}
	
	public BaseSignal getSignal() {
		return signal;
	}
	
	@Override
	public String toString() {
		StringBuilder builder=new StringBuilder(shortcut);
		if(NullUtil.isNotNull(signal)){
			builder.append("/"+signal);
		}
		
		return builder.toString();
	}
}
