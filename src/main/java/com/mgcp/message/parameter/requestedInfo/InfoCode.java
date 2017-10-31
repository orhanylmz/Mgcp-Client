package com.mgcp.message.parameter.requestedInfo;

import com.mgcp.exceptions.MGCPParseException;
import com.noyan.util.NullUtil;

public class InfoCode {
	public InfoCodeEnum infoCodeEnum;
	public ExtensionParameter extensionParameter;

	public InfoCode(InfoCodeEnum infoCodeEnum) {
		this.infoCodeEnum = infoCodeEnum;
	}

	public InfoCode(ExtensionParameter extensionParameter) {
		this.infoCodeEnum = InfoCodeEnum.extensionParameter;
		this.extensionParameter = extensionParameter;
	}

	@Override
	public String toString() {
		if (!infoCodeEnum.equals(extensionParameter)) {
			return infoCodeEnum.toString();
		}

		return extensionParameter.toString();
	}

	public static InfoCode parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();

		InfoCodeEnum infoCodeEnum = InfoCodeEnum.valueOf(text);
		if (NullUtil.isNotNull(infoCodeEnum)) {
			return new InfoCode(infoCodeEnum);
		}

		ExtensionParameter extensionParameter = ExtensionParameter.parse(text);
		if (NullUtil.isNull(extensionParameter)) {
			throw new MGCPParseException("extensionParameter not found");
		}
		return new InfoCode(extensionParameter);
	}

	public enum InfoCodeEnum {
		B, C, I, N, X, L, M, R, S, D, O, P, E, Z, Q, T, RC, LC, A, ES, RM, RD, PL, MD, extensionParameter
	}

}
