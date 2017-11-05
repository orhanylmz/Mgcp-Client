package com.mgcp.eventpackages.parameters;

public class StopKey extends BaseEventParameter {
	public StopKey() {
		super("stk");
	}

	@Override
	public String getCommentRFC2897() {
		return "Defines a key with the following action.  Terminate playback of the announcement.  No default";
	}

}
