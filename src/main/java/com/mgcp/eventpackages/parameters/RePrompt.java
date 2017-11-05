package com.mgcp.eventpackages.parameters;

public class RePrompt extends BaseEventParameter {
	public RePrompt(String value) {
		super("rp",value);
	}

	@Override
	public String getCommentRFC2897() {
		return "Played after the user has made an error such as entering an invalid digit pattern or not speaking.  Consists of one or more audio segments.  Defaults to the Initial Prompt";
	}

}
