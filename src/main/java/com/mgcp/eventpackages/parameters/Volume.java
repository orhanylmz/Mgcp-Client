package com.mgcp.eventpackages.parameters;

public class Volume extends BaseEventParameter {
	public Volume() {
		super("vl");
	}

	@Override
	public String getCommentRFC2897() {
		return "The relative playback volume of announcement specifiable as a positive or negative decibel variation from the original play-back volume";
	}

}
