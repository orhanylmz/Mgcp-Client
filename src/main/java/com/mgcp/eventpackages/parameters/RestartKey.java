package com.mgcp.eventpackages.parameters;

public class RestartKey extends BaseEventParameter {
	public RestartKey() {
		super("rsk");
	}

	@Override
	public String getCommentRFC2897() {
		return "Defines a key sequence consisting of a command key optionally followed by zero or more keys.  This key sequence has the following action:  discard any digits collected or recording in progress, replay the prompt, and resume digit collection or recording.  No default.  An application that defines more than one command key sequence, will typically use the same command key for all command key sequences.  If more than one command key sequence is defined, then all key sequences must consist of a command key plus at least one other key";
	}

}
