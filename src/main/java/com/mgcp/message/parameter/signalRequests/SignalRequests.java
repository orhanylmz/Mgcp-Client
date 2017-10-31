package com.mgcp.message.parameter.signalRequests;

import java.util.ArrayList;
import java.util.Arrays;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.ParameterValueContent;
import com.noyan.util.NullUtil;

public class SignalRequests implements ParameterValueContent {
	private ArrayList<SignalRequest> signalRequests = new ArrayList<>();

	public SignalRequests() {
	}

	public SignalRequests(ArrayList<SignalRequest> signalRequests) {
		this.signalRequests = signalRequests;
	}

	public SignalRequests(SignalRequest... signalRequests) {
		this.signalRequests = new ArrayList<>(Arrays.asList(signalRequests));
	}

	public ArrayList<SignalRequest> getSignalRequests() {
		return signalRequests;
	}

	@Override
	public String toString() {
		if (NullUtil.isNull(signalRequests)) {
			return "";
		}

		if (signalRequests.isEmpty()) {
			return "";
		}

		StringBuilder builder = new StringBuilder(signalRequests.get(0).toString());
		if (signalRequests.size() == 1) {
			return builder.toString();
		}

		for (int i = 1; i < signalRequests.size(); i++) {
			builder.append(", " + signalRequests.get(i));
		}

		return builder.toString();
	}

	public static SignalRequests parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			return new SignalRequests();
		}

		text = text.trim();

		String[] parts = text.split("[,]");
		if (NullUtil.isNull(parts)) {
			throw new MGCPParseException("parts can not be null");
		}

		ArrayList<SignalRequest> contents = new ArrayList<>();
		for (int i = 0; i < parts.length; i++) {
			SignalRequest signalRequest = SignalRequest.parse(parts[i]);
			contents.add(signalRequest);
		}

		return new SignalRequests(contents);
	}
}
