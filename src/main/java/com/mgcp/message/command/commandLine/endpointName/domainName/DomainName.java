package com.mgcp.message.command.commandLine.endpointName.domainName;

import com.mgcp.exceptions.MGCPParseException;

public class DomainName {
	private String domainName;

	public DomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getDomainName() {
		return domainName;
	}

	@Override
	public String toString() {
		return domainName;
	}

	public static DomainName parse(String domainNameText) throws MGCPParseException {
		return new DomainName(domainNameText);
	}
}
