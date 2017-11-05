package com.mgcp.eventpackages.parameters;

public class SuccessAnnouncement extends BaseEventParameter {
	public SuccessAnnouncement(String value) {
		super("sa",value);
	}

	@Override
	public String getCommentRFC2897() {
		return "Played when data collection has succeeded.  Consists of one or more audio segments.  No default";
	}

}
