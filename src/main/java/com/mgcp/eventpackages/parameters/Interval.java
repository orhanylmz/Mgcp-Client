package com.mgcp.eventpackages.parameters;

public class Interval extends BaseEventParameter {
	public Interval() {
		super("iv");
	}

	@Override
	public String getCommentRFC2897() {
		return "The interval of silence to be inserted between iterative plays. Specified in units of 100 milliseconds.  Defaults to 10 (1 second)";
	}

}
