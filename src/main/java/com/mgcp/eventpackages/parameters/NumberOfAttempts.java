package com.mgcp.eventpackages.parameters;

public class NumberOfAttempts extends BaseEventParameter {
	public NumberOfAttempts() {
		super("na");
	}

	@Override
	public String getCommentRFC2897() {
		return "The number of attempts the user needed to enter a valid digit pattern or to make a recording.  Defaults to 1.  Also used as a return parameter to indicate the number of attempts the user made";
	}

}
