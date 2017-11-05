package com.mgcp.eventpackages.parameters;

public class MaximumDigits extends BaseEventParameter {
	public MaximumDigits() {
		super("mx");
	}

	@Override
	public String getCommentRFC2897() {
		return "The maximum number of digits to collect.  Defaults to one.  This parameter should not be specified if the Digit Pattern parameter is present";
	}

}
