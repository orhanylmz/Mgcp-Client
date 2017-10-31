package com.mgcp.message.parameter.notifiedEntity;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.command.commandLine.endpointName.domainName.DomainName;
import com.mgcp.message.command.commandLine.endpointName.localEndpointName.LocalEndpointName;
import com.mgcp.message.command.commandLine.endpointName.localEndpointName.LocalNamePart;
import com.mgcp.message.parameter.MGCPParameter;
import com.mgcp.message.parameter.ParameterValue;
import com.noyan.util.NullUtil;

public class NotifiedEntityParameterValue extends ParameterValue {
	private static final String finalShortname = "N";

	public NotifiedEntityParameterValue() {
		this(null);
	}

	public NotifiedEntityParameterValue(NotifiedEntity content) {
		super(content);
		this.shortname = finalShortname;
	}

	@Override
	public NotifiedEntity getValue() {
		return (NotifiedEntity) super.getValue();
	}

	@Override
	public NotifiedEntityParameterValue parse(String text) throws MGCPParseException {
		text = getValue(text);
		return new NotifiedEntityParameterValue(NotifiedEntity.parse(text));
	}

	public static MGCPParameter generate(String domainName, int port) {
		return new MGCPParameter(new NotifiedEntityParameterValue(new NotifiedEntity(new DomainName(domainName), port)));
	}

	public static MGCPParameter generate(String localName, String domainName, int port) {
		return new MGCPParameter(new NotifiedEntityParameterValue(new NotifiedEntity(new LocalEndpointName(new LocalNamePart(localName)), new DomainName(domainName), port)));
	}

	@Override
	public boolean isMapped(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();

		if (!text.startsWith(finalShortname)) {
			return false;
		}

		text = text.substring(finalShortname.length()).trim();
		if (!text.startsWith(":")) {
			return false;
		}

		return true;
	}

}
