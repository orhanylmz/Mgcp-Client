package com.mgcp.eventpackages.parameters.restcomm;

import com.mgcp.eventpackages.parameters.BaseEventParameter;

public class Driver extends BaseEventParameter {
	public Driver() {
		super("dr");
	}

	@Override
	public String getCommentRFC2897() {
		return "Not found in RFC";
	}
}
