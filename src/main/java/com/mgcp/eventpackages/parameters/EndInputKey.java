package com.mgcp.eventpackages.parameters;

public class EndInputKey extends BaseEventParameter {
	public EndInputKey() {
		super("eik");
	}

	@Override
	public String getCommentRFC2897() {
		return "Specifies a key that signals the end of digit collection or voice recording. The default end input key is the # key. To specify that no End Input Key be used the parameter is set to the string \"null\".  The default behavior not to return the End Input Key in the digits returned to the call agent.  This behavior can be overidden by the Include End Input Key (eik) parameter";
	}

}
