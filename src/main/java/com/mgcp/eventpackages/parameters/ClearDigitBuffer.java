package com.mgcp.eventpackages.parameters;

public class ClearDigitBuffer extends BaseEventParameter {
	public ClearDigitBuffer() {
		super("cb");
	}

	@Override
	public String getCommentRFC2897() {
		return "If set to true, clears the digit buffer before playing the initial prompt.  Defaults to false.  Valid values are the text strings \"true\" and \"false\"";
	}

}
