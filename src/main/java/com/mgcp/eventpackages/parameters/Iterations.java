package com.mgcp.eventpackages.parameters;

public class Iterations extends BaseEventParameter {
	public Iterations(String value) {
		super("it",value);
	}

	@Override
	public String getCommentRFC2897() {
		return "The maximum number of times an announcement is to be played.  A value of minus one (-1) indicates the announcement is to be repeated forever. Defaults to one (1)";
	}

}
