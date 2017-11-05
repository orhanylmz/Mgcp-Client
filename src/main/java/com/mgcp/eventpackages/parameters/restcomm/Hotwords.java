package com.mgcp.eventpackages.parameters.restcomm;

import com.mgcp.eventpackages.parameters.BaseEventParameter;

public class Hotwords extends BaseEventParameter {
	public Hotwords() {
		super("hw");
	}

	@Override
	public String getCommentRFC2897() {
		return "Not found in RFC";
	}
}
