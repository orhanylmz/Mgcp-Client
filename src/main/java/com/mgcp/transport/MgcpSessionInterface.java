package com.mgcp.transport;

import java.util.regex.Matcher;

import com.mgcp.message.command.commandLine.MGCPCommandLine.MGCPVerb;
import com.mgcp.message.response.MGCPResponse;
import com.mgcp.message.response.responseCode.ResponseCodePatterns;

public interface MgcpSessionInterface {
	default void processReceiveMessage(MGCPResponse mgcpResponse, MGCPVerb verb) {
		String responseCode = mgcpResponse.getResponseLine().getResponseCode();
		Matcher matcher = ResponseCodePatterns.PATTERN_SUCCESSFUL.matcher(responseCode);
		if (matcher.matches()) {
			onSuccess(mgcpResponse, verb);
			return;
		}

		onError(mgcpResponse, verb);
	};

	public void processException(Exception exception);

	public void onSuccess(MGCPResponse mgcpResponse, MGCPVerb verb);

	public void onError(MGCPResponse mgcpResponse, MGCPVerb verb);
}
