package com.mgcp.message.response.responseLine;

import com.configuration.Constants;
import com.mgcp.exceptions.MGCPParseException;
import com.noyan.util.ConverterUtil;
import com.noyan.util.NullUtil;

public class MGCPResponseLine {
	private String responseCode;
	private int transactionId;
	private String packageName;
	private String responseString;

	public MGCPResponseLine(String responseCode, int transactionId) {
		this(responseCode, transactionId, null, null);
	}

	public MGCPResponseLine(String responseCode, int transactionId, String packageName, String responseString) {
		this.responseCode = responseCode;
		this.transactionId = transactionId;
		this.packageName = packageName;
		this.responseString = responseString;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public int getTransactionId() {
		return transactionId;
	}

	public String getPackageName() {
		return packageName;
	}

	public String getResponseString() {
		return responseString;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(responseCode + " " + transactionId);
		if (NullUtil.isNotNull(packageName)) {
			builder.append(" /" + packageName);
		}

		if (NullUtil.isNotNull(responseString)) {
			builder.append(" " + responseString);
		}

		builder.append(Constants.EOL);
		return builder.toString();
	}

	public static MGCPResponseLine parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();

		String responseCode = text.substring(0, text.indexOf(" "));
		text = text.substring(text.indexOf(" ") + 1).trim();

		if (!text.contains(" ")) {
			return new MGCPResponseLine(responseCode, ConverterUtil.toInt(text));
		}

		String transactionIdString = text.substring(0, text.indexOf(" "));
		int transactionId = ConverterUtil.toInt(transactionIdString);
		text = text.substring(text.indexOf(" ") + 1).trim();

		if (text.length() <= 0) {
			return new MGCPResponseLine(responseCode, transactionId);
		}

		String packageName = null;
		String responseString = null;
		String[] parts = text.split(" ");
		if (parts.length > 1) {
			packageName = parts[0].trim();
			if (packageName.startsWith("/")) {
				packageName = packageName.substring(1).trim();
			}

			responseString = parts[1].trim();
			return new MGCPResponseLine(responseCode, transactionId, packageName, responseString);
		}

		if (text.startsWith("/")) {
			packageName = text.substring(1).trim();
			return new MGCPResponseLine(responseCode, transactionId, packageName, null);
		}

		responseString = text;
		return new MGCPResponseLine(responseCode, transactionId, null, responseString);
	}
}
