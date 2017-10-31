package com.mgcp.message.command.commandLine.endpointName;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.command.commandLine.endpointName.domainName.DomainName;
import com.mgcp.message.command.commandLine.endpointName.localEndpointName.LocalEndpointName;
import com.mgcp.message.parameter.ParameterValueContent;
import com.noyan.util.ConverterUtil;
import com.noyan.util.NullUtil;

public class EndpointName implements ParameterValueContent {
	private LocalEndpointName localEndpointName;
	private DomainName domainName;
	private int port;

	public EndpointName(LocalEndpointName localEndpointName, DomainName domainName) {
		this(localEndpointName, domainName, -1);
	}

	public EndpointName(LocalEndpointName localEndpointName, DomainName domainName, int port) {
		this.localEndpointName = localEndpointName;
		this.domainName = domainName;
		this.port = port;
	}

	public LocalEndpointName getLocalEndpointName() {
		return localEndpointName;
	}

	public DomainName getDomainName() {
		return domainName;
	}

	public int getPort() {
		return port;
	}

	@Override
	public String toString() {
		if (port > 0) {
			return localEndpointName + "@" + domainName + ":" + port;
		}

		return localEndpointName + "@" + domainName;
	}

	public static EndpointName parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();
		if (!text.contains("@")) {
			throw new MGCPParseException("text is not contains '@'");
		}

		String[] parts = text.split("@");
		if (NullUtil.isNull(parts)) {
			throw new MGCPParseException("parts can not be null");
		}

		if (parts.length != 2) {
			throw new MGCPParseException("parts length must be 2");
		}

		int port = -1;
		String domainName = parts[1];
		LocalEndpointName localEndpointName = LocalEndpointName.parse(parts[0]);

		if (parts[1].contains(":")) {
			String[] domainParts = parts[1].split(":");
			port = ConverterUtil.toInt(domainParts[1]);
			domainName = domainParts[0];
		}

		return new EndpointName(localEndpointName, DomainName.parse(domainName), port);
	}
}
