package com.mgcp.eventpackages.parameters;

public class NoDigitsReprompt extends BaseEventParameter {
	public NoDigitsReprompt() {
		super("nd");
	}

	@Override
	public String getCommentRFC2897() {
		return "Played after the user has failed to enter a valid digit pattern during a PlayCollect event.  Consists of one or more audio segments.  Defaults to the Reprompt";
	}

}
