package com.mgcp.eventpackages.parameters.restcomm;

import com.mgcp.eventpackages.parameters.BaseEventParameter;

public class PartialResults extends BaseEventParameter {
	public PartialResults() {
		super("pr");
	}

	@Override
	public String getCommentRFC2897() {
		return "Not found in RFC";
	}
}
