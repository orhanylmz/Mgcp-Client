package com.mgcp.eventpackages.parameters;

public class FirstDigitTimer extends BaseEventParameter {
	public FirstDigitTimer() {
		super("fdt");
	}

	@Override
	public String getCommentRFC2897() {
		return "The amount of time allowed for the user to enter the first digit. Specified in units of 100 milliseconds.  50 (5 seconds)";
	}

}
