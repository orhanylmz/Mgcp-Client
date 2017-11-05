package com.mgcp.eventpackages.parameters.restcomm;

import com.mgcp.eventpackages.parameters.BaseEventParameter;

public class WaitingInputTime extends BaseEventParameter {
	public WaitingInputTime() {
		super("wit");
	}

	@Override
	public String getCommentRFC2897() {
		return "Not found in RFC";
	}
}
