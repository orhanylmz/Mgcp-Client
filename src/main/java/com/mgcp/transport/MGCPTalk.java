package com.mgcp.transport;

import com.configuration.Constants;
import com.mgcp.message.command.MGCPCommand;
import com.mgcp.message.response.MGCPResponse;
import com.noyan.util.NullUtil;

public class MGCPTalk {
	private MGCPCommand mgcpCommand;
	private MGCPResponse mgcpResponse;

	public MGCPTalk(MGCPCommand mgcpCommand) {
		this(mgcpCommand, null);
	}

	public MGCPTalk(MGCPCommand mgcpCommand, MGCPResponse mgcpResponse) {
		this.mgcpCommand = mgcpCommand;
		this.mgcpResponse = mgcpResponse;
	}

	public MGCPCommand getMgcpCommand() {
		return mgcpCommand;
	}

	public MGCPResponse getMgcpResponse() {
		return mgcpResponse;
	}

	public void setMgcpResponse(MGCPResponse mgcpResponse) {
		this.mgcpResponse = mgcpResponse;
	}

	public boolean isCompleted() {
		if (NullUtil.isAnyNull(mgcpCommand, mgcpResponse)) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (NullUtil.isNotNull(mgcpCommand)) {
			builder.append(Constants.mgcpCommandLogHeader + "\n" + mgcpCommand.toString()
					+ Constants.mgcpMessageLogFooter + "\n");
		}

		if (NullUtil.isNotNull(mgcpCommand)) {
			builder.append(Constants.mgcpResponseLogHeader + "\n" + mgcpResponse.toString()
					+ Constants.mgcpMessageLogFooter + "\n");
		}

		return builder.toString();
	}
}
