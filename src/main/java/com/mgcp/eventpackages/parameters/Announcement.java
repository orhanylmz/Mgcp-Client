package com.mgcp.eventpackages.parameters;

public class Announcement extends BaseEventParameter {
	public Announcement(String value) {
		super("an", value);
	}

	@Override
	public String getCommentRFC2897() {
		return "An announcement to be played.  Consists of one or more audio segments";
	}

}
