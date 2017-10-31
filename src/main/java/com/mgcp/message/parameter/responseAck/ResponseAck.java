package com.mgcp.message.parameter.responseAck;

import java.util.ArrayList;
import java.util.Arrays;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.ParameterValueContent;
import com.noyan.util.NullUtil;

public class ResponseAck implements ParameterValueContent {
	private ArrayList<ConfirmedTransactionIdRange> confirmedTransactionIdRanges = new ArrayList<>();

	public ResponseAck(ArrayList<ConfirmedTransactionIdRange> confirmedTransactionIdRanges) {
		this.confirmedTransactionIdRanges = confirmedTransactionIdRanges;
	}

	public ResponseAck(ConfirmedTransactionIdRange... values) {
		this.confirmedTransactionIdRanges = new ArrayList<>(Arrays.asList(values));
	}

	public ArrayList<ConfirmedTransactionIdRange> getConfirmedTransactionIdRanges() {
		return confirmedTransactionIdRanges;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(confirmedTransactionIdRanges.get(0).toString());
		if (confirmedTransactionIdRanges.size() == 1) {
			return builder.toString();
		}

		for (int i = 1; i < confirmedTransactionIdRanges.size(); i++) {
			builder.append(", " + confirmedTransactionIdRanges.get(i));
		}

		return builder.toString();
	}

	public static ResponseAck parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();

		String[] parts = text.split("[,]");

		ArrayList<ConfirmedTransactionIdRange> contents = new ArrayList<>();
		for (int i = 0; i < parts.length; i++) {
			ConfirmedTransactionIdRange confirmedTransactionIdRange = ConfirmedTransactionIdRange.parse(parts[i]);
			contents.add(confirmedTransactionIdRange);
		}

		return new ResponseAck(contents);
	}
}
