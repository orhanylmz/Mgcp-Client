package com.mgcp.eventpackages.parameters.restcomm;

import com.mgcp.eventpackages.parameters.BaseEventParameter;

public class Signal extends BaseEventParameter {
	public Signal() {
		super("sg");
	}

	@Override
	public String getCommentRFC2897() {
		return "Not found in RFC";
	}
}
