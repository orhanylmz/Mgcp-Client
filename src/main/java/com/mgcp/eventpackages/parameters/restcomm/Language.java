package com.mgcp.eventpackages.parameters.restcomm;

import com.mgcp.eventpackages.parameters.BaseEventParameter;

public class Language extends BaseEventParameter {
	public Language() {
		super("ln");
	}

	@Override
	public String getCommentRFC2897() {
		return "Not found in RFC";
	}
}
