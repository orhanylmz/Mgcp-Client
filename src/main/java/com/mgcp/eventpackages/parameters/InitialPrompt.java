package com.mgcp.eventpackages.parameters;

public class InitialPrompt extends BaseEventParameter {
	public InitialPrompt(String value) {
		super("ip", value);
	}

	@Override
	public String getCommentRFC2897() {
		return "The initial announcement prompting the user to either enter DTMF digits or to speak.  Consists of one or more audio segments.  If not specified (the default), the event immediately begins digit collection or recording";
	}

}
