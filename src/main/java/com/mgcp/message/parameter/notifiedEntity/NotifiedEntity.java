package com.mgcp.message.parameter.notifiedEntity;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.command.commandLine.endpointName.domainName.DomainName;
import com.mgcp.message.command.commandLine.endpointName.localEndpointName.LocalEndpointName;
import com.mgcp.message.parameter.ParameterValueContent;
import com.noyan.util.NullUtil;

public class NotifiedEntity implements ParameterValueContent {
	private LocalEndpointName localName;
	private DomainName domainName;
	private int portNumber = -1;

	public NotifiedEntity(DomainName domainName) {
		this(null, domainName, -1);
	}

	public NotifiedEntity(LocalEndpointName localName, DomainName domainName) {
		this(localName, domainName, -1);
	}

	public NotifiedEntity(DomainName domainName, int portNumber) {
		this(null, domainName, portNumber);
	}

	public NotifiedEntity(LocalEndpointName localName, DomainName domainName, int portNumber) {
		this.domainName = domainName;
		this.localName = localName;
		this.portNumber = portNumber;
	}

	public LocalEndpointName getLocalName() {
		return localName;
	}

	public DomainName getDomainName() {
		return domainName;
	}

	public int getPortNumber() {
		return portNumber;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (NullUtil.isNotNull(localName)) {
			builder.append(localName + "@");
		}

		builder.append(domainName);
		if (portNumber > 0) {
			builder.append(":" + portNumber);
		}

		return builder.toString();
	}

	public static NotifiedEntity parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		String[] parts = text.split("[@]");
		if (NullUtil.isNull(parts)) {
			throw new MGCPParseException("parts can not be null");
		}

		// part01@part02:part03
		String part01 = null;
		String part02 = null;
		String part03 = null;
		String part23 = null;

		if (parts.length > 1) {
			part01 = parts[0].trim();
			part23 = parts[1].trim();
		} else {
			part23 = parts[0].trim();
		}

		parts = part23.split(":");
		if (parts.length > 1) {
			part02 = parts[0].trim();
			part03 = parts[1].trim();
		} else {
			part02 = parts[0].trim();
		}

		int portNumber = -1;
		if (NullUtil.isNotNull(part03)) {
			portNumber = Integer.parseInt(part03);
		}

		if (portNumber <= 0) {
			portNumber = -1;
		}

		LocalEndpointName localEndpointName = null;
		if (NullUtil.isNotNull(part01)) {
			localEndpointName = LocalEndpointName.parse(part01);
		}

		DomainName domainName = null;
		if (NullUtil.isNotNull(part02)) {
			domainName = DomainName.parse(part02);
		}
		return new NotifiedEntity(localEndpointName, domainName, portNumber);
	}

}
