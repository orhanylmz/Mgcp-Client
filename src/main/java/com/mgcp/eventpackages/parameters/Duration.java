package com.mgcp.eventpackages.parameters;

public class Duration extends BaseEventParameter {
	public Duration() {
		super("du");
	}

	@Override
	public String getCommentRFC2897() {
		return "The maximum amount of time to play and possibly replay an announcement. Takes precedence over iteration and interval. Specified in units of 100 milliseconds.  No default";
	}

}
