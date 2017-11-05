package com.mgcp.eventpackages.parameters;

public class InterDigitTimer extends BaseEventParameter {
	public InterDigitTimer() {
		super("idt");
	}

	@Override
	public String getCommentRFC2897() {
		return "The amount of time allowed for the user to enter each subsequent digit. Specified units of 100 milliseconds seconds.  Defaults to 30 (3 seconds)";
	}

}
