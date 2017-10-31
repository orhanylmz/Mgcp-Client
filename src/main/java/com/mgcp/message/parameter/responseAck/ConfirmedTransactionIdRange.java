package com.mgcp.message.parameter.responseAck;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.ParameterValueContent;
import com.noyan.util.ConverterUtil;
import com.noyan.util.NullUtil;

public class ConfirmedTransactionIdRange implements ParameterValueContent {
	private int transactionId01 = -1;
	private int transactionId02 = -1;

	public ConfirmedTransactionIdRange(int transactionId) {
		this(transactionId, -1);
	}

	public ConfirmedTransactionIdRange(int transactionId01, int transactionId02) {
		this.transactionId01 = transactionId01;
		this.transactionId02 = transactionId02;
	}

	public int getTransactionId01() {
		return transactionId01;
	}

	public int getTransactionId02() {
		return transactionId02;
	}

	@Override
	public String toString() {
		if (transactionId02 != -1) {
			return transactionId01 + "-" + transactionId02;
		}

		return transactionId01 + "";
	}

	public static ConfirmedTransactionIdRange parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();

		String[] parts = text.split("[-]");

		int transactionId01 = ConverterUtil.toInt(parts[0]);
		if (transactionId01 == 0) {
			transactionId01 = -1;
		}

		int transactionId02 = -1;
		if (parts.length > 1) {
			transactionId02 = ConverterUtil.toInt(parts[1]);
		}

		if (transactionId02 == 0) {
			transactionId02 = -1;
		}

		if (transactionId01 == -1 && transactionId02 == -1) {
			throw new MGCPParseException("transactionIds not found");
		}

		return new ConfirmedTransactionIdRange(transactionId01, transactionId02);
	}

}
