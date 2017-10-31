package com.mgcp.transport;

import com.mgcp.message.response.MGCPResponse;

public interface MgcpSessionInterface {

	public void processReceiveMessage(MGCPResponse mgcpResponse);

	public void processException(Exception exception);
}
