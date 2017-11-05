package com.mgcp.eventpackages.parameters;

public class PositionKey extends BaseEventParameter {
	public PositionKey() {
		super("psk");
	}

	@Override
	public String getCommentRFC2897() {
		return "Defines a key with the following action.  Stop playing the current announcement and resume playing at the beginning of the first, last, previous, next, or the current segment of the announcement. No default.  The actions for the position key are fst, lst, prv, nxt, and cur";
	}

}
