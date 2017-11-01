package com.mgcp.transport;

import java.util.regex.Matcher;

import com.mgcp.message.response.MGCPResponse;
import com.mgcp.message.response.responseCode.ResponseCodePatterns;

public interface MgcpSessionInterface {
	default void processReceiveMessage(MGCPResponse mgcpResponse) {
		String responseCode = mgcpResponse.getResponseLine().getResponseCode();
		Matcher matcher = ResponseCodePatterns.PATTERN_SUCCESSFUL.matcher(responseCode);
		if (matcher.matches()) {
			onSuccess(mgcpResponse);
			return;
		}

		onError(mgcpResponse);
	};

	public void processException(Exception exception);

	public void onSuccess(MGCPResponse mgcpResponse);

	public void onError(MGCPResponse mgcpResponse);
}
