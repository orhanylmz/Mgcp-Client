package com.mgcp.eventpackages.parameters.restcomm;

import com.mgcp.eventpackages.parameters.BaseEventParameter;

public class MaximumRecognitionTime extends BaseEventParameter {
	public MaximumRecognitionTime() {
		super("mrt");
	}

	@Override
	public String getCommentRFC2897() {
		return "Not found in RFC";
	}
}
