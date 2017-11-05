package com.mgcp.eventpackages.parameters;

public class NoSpeechReprompt extends BaseEventParameter {
	public NoSpeechReprompt() {
		super("ns");
	}

	@Override
	public String getCommentRFC2897() {
		return "Played after the user has failed to speak during a PlayRecord event.  Consists of one or more audio segments.  Defaults to the Reprompt";
	}

}
