package com.mgcp.transport;

import com.mgcp.message.response.MGCPResponse;
import com.noyan.Base;
import com.noyan.util.NullUtil;

public class SessionFounder extends Thread implements Base {
	private String message;

	public SessionFounder(byte[] receiveData, MGCPTransportLayer mgcpTransportLayer) {
		this.message = new String(receiveData);
	}

	@Override
	public void run() {
		try {
			MGCPResponse mgcpResponse = MGCPResponse.parse(message);

			int transactionId = mgcpResponse.getResponseLine().getTransactionId();

			MgcpSession session = (MgcpSession) MGCPTransportLayer.getMgcpTransportLayer().getSessionFromTransaction(transactionId);
			if (NullUtil.isNull(session)) {
				throw new Exception("Session not found for transactionId: " + transactionId);
			}

			session.receive(mgcpResponse);
		} catch (Exception e) {
		}

	}

	@Override
	public String getPrefix() {
		return "SessionFounder";
	}

}
