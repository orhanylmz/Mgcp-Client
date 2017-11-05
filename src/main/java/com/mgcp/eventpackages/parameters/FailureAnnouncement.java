package com.mgcp.eventpackages.parameters;

public class FailureAnnouncement extends BaseEventParameter {
	public FailureAnnouncement() {
		super("fa");
	}

	@Override
	public String getCommentRFC2897() {
		return "Played when all data entry attempts have failed.  Consists of one or more audio segments.  No default";
	}

}
