package com.mgcp.eventpackages.parameters;

public class ExtraDigitTimer extends BaseEventParameter {
	public ExtraDigitTimer() {
		super("edt");
	}

	@Override
	public String getCommentRFC2897() {
		return "The amount of time to wait for a user to enter a final digit once the maximum expected amount of digits have been entered. Typically this timer is used to wait for a terminating key in applications where a specific key has been defined to terminate input.  Specified in units of 100 milliseconds. If not specified, this timer is not activated";
	}

}
