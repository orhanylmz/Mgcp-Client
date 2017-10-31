package com.mgcp.message.command.commandLine.endpointName.localEndpointName;

import com.mgcp.exceptions.MGCPParseException;
import com.noyan.util.NullUtil;

public class LocalNamePart {
	private String name;
	private LocalNamePartEnum localNamePartEnum;

	public LocalNamePart(LocalNamePartEnum localNamePartEnum) {
		this.localNamePartEnum = localNamePartEnum;
	}

	public LocalNamePart(String name) {
		this.localNamePartEnum = LocalNamePartEnum.NameString;
		this.name = name;
	}

	@Override
	public String toString() {
		switch (localNamePartEnum) {
		case AnyName:
			return "$";
		case AllName:
			return "*";
		default:
			return name;
		}
	}

	public static LocalNamePart parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();
		if (text.equals("$")) {
			return new LocalNamePart(LocalNamePartEnum.AnyName);
		}

		if (text.equals("*")) {
			return new LocalNamePart(LocalNamePartEnum.AllName);
		}

		return new LocalNamePart(text);

	}

	public enum LocalNamePartEnum {
		AnyName, AllName, NameString
	}

}
