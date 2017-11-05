package com.mgcp.eventpackages.parameters;

public class ReturnKey extends BaseEventParameter {
	public ReturnKey() {
		super("rtk");
	}

	@Override
	public String getCommentRFC2897() {
		return "Defines a key sequence consisting of a command key optionally followed by zero or more keys.  This key sequence has the following action:  terminate the current event and any queued event and return the terminating key sequence to the call processing agent.  No default.   An application that defines more than one command key sequence, will typically use the same command key for all command key sequences.  If more than one command key sequence is defined, then all key sequences must consist of a command key plus at least one other key";
	}

}
