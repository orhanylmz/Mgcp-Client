package com.mgcp.eventpackages.parameters;

public class RecordingLengthTimer extends BaseEventParameter {
	public RecordingLengthTimer() {
		super("rlt");
	}

	@Override
	public String getCommentRFC2897() {
		return "The maximum allowable length of the recording, not including pre or post speech silence.  Specified in units of 100 milliseconds. This parameter is mandatory";
	}

}
