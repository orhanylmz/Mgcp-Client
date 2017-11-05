package com.mgcp.eventpackages.parameters;

public class MinimumDigits extends BaseEventParameter {
	public MinimumDigits() {
		super("mn");
	}

	@Override
	public String getCommentRFC2897() {
		return "The minimum number of digits to collect.  Defaults to one.  This parameter should not be specified if the Digit Pattern parameter is present";
	}

}
