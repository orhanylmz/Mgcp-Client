package com.mgcp.eventpackages.parameters;

public class StartInputKey extends BaseEventParameter {
	public StartInputKey() {
		super("sik");
	}

	@Override
	public String getCommentRFC2897() {
		return "Defines a set of keys that are acceptable as the first digit collected. This set of keys can be specified to interrupt a playing announcement or to not interrupt a playing announcement The default key set is 0-9. The default behavior is to interrupt a playing announcement when a Start Input Key is pressed.  This behavior can be overidden for the initial prompt only by using the ni (Non-Interruptible Play) parameter.  Specification is a list of keys with no separators, e.g.  123456789#";
	}

}
