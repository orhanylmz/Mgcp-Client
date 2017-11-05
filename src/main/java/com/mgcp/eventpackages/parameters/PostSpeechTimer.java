package com.mgcp.eventpackages.parameters;

public class PostSpeechTimer extends BaseEventParameter {
	public PostSpeechTimer() {
		super("pst");
	}

	@Override
	public String getCommentRFC2897() {
		return "The amount of silence necessary after the end of the last speech segment for the recording to be considered complete.  Specified in units of 100 milliseconds.  Defaults to 20 (2 seconds)";
	}

}
