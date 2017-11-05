package com.mgcp.eventpackages.parameters;

public class Speed extends BaseEventParameter {
	public Speed() {
		super("sp");
	}

	@Override
	public String getCommentRFC2897() {
		return "The relative playback speed of announcement specifiable as a positive or negative percentage of the original playback speed";
	}

}
