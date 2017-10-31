package com.mgcp.message.parameter.requestedInfo;

import java.util.ArrayList;
import java.util.Arrays;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.ParameterValueContent;
import com.noyan.util.NullUtil;

public class RequestedInfo implements ParameterValueContent {
	private ArrayList<InfoCode> infoCodes = new ArrayList<>();

	public RequestedInfo(ArrayList<InfoCode> infoCodes) {
		this.infoCodes = infoCodes;
	}

	public RequestedInfo(InfoCode... infoCodes) {
		this.infoCodes = new ArrayList<>(Arrays.asList(infoCodes));
	}

	public ArrayList<InfoCode> getInfoCodes() {
		return infoCodes;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(infoCodes.get(0).toString());
		if (infoCodes.size() == 1) {
			return builder.toString();
		}

		for (int i = 1; i < infoCodes.size(); i++) {
			builder.append(", " + infoCodes.get(i));
		}

		return builder.toString();
	}

	public static RequestedInfo parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();

		String[] parts = text.split("[,]");

		ArrayList<InfoCode> contents = new ArrayList<>();
		for (int i = 0; i < parts.length; i++) {
			InfoCode infoCode = InfoCode.parse(parts[i]);
			contents.add(infoCode);
		}

		return new RequestedInfo(contents);
	}
}
