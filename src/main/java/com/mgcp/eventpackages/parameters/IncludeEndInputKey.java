package com.mgcp.eventpackages.parameters;

public class IncludeEndInputKey extends BaseEventParameter {
	public IncludeEndInputKey() {
		super("iek");
	}

	@Override
	public String getCommentRFC2897() {
		return "By default the End Input Key is not included in the collected digits returned to the call agent.  If this parameter is set to \"true\" then the End Input Key will be returned with the collected digits returned to the call agent.  Default is \"false\"";
	}

}
