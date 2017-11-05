package com.mgcp.eventpackages.parameters;

public class DigitPattern extends BaseEventParameter {
	public DigitPattern() {
		super("dp");
	}

	@Override
	public String getCommentRFC2897() {
		return "A legal digit map as described in section 7.1.14 of the Megaco protocol [6] using the DTMF mappings associated with the Megaco DTMF Detection Package described in the Megaco protocol document [6].  This parameter should not be specified if one or both of the Minimum # Of Digits parameter and the Maximum Number Of Digits parameter is present";
	}

}
