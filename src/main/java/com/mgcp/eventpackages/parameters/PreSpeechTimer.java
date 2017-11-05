package com.mgcp.eventpackages.parameters;

public class PreSpeechTimer extends BaseEventParameter {
	public PreSpeechTimer() {
		super("prt");
	}

	@Override
	public String getCommentRFC2897() {
		return "The amount of time to wait for the user to initially speak. Specified in units of 100 milliseconds.  Defaults to 30 (3 seconds)";
	}

}
