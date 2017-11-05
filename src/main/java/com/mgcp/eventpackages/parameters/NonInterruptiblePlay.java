package com.mgcp.eventpackages.parameters;

public class NonInterruptiblePlay extends BaseEventParameter {
	public NonInterruptiblePlay() {
		super("ni");
	}

	@Override
	public String getCommentRFC2897() {
		return "If set to true, initial prompt is not interruptible by either voice or digits.  Defaults to false.  Valid values are the text strings \"true\" and \"false\"";
	}

}
