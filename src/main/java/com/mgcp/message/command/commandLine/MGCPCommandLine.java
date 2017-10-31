package com.mgcp.message.command.commandLine;

import com.configuration.Constants;
import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.command.commandLine.endpointName.EndpointName;
import com.noyan.util.ConverterUtil;
import com.noyan.util.NullUtil;

public class MGCPCommandLine {
	private MGCPVerb mgcpVerb;
	private long transactionId;
	private EndpointName endpointName;
	private String extensionVerb;
	private String mgcpVersion;

	public MGCPCommandLine(MGCPVerb mgcpVerb, long transactionId, EndpointName endpointName) {
		this(mgcpVerb, transactionId, endpointName, Constants.MGCPversion);
	}

	public MGCPCommandLine(MGCPVerb mgcpVerb, long transactionId, EndpointName endpointName, String mgcpVersion) {
		this.mgcpVerb = mgcpVerb;
		this.transactionId = transactionId;
		this.endpointName = endpointName;
		this.mgcpVersion = mgcpVersion;
	}

	public MGCPCommandLine(String extensionVerb, long transactionId, EndpointName endpointName) {
		this(extensionVerb, transactionId, endpointName, Constants.MGCPversion);
	}

	public MGCPCommandLine(String extensionVerb, long transactionId, EndpointName endpointName, String mgcpVersion) {
		this.mgcpVerb = MGCPVerb.extensionVerb;
		this.extensionVerb = extensionVerb;
		this.transactionId = transactionId;
		this.endpointName = endpointName;
		this.mgcpVersion = mgcpVersion;
	}

	public MGCPVerb getMgcpVerb() {
		return mgcpVerb;
	}

	public long getTransactionId() {
		return transactionId;
	}

	public EndpointName getEndpointName() {
		return endpointName;
	}

	public String getMgcpVersion() {
		return mgcpVersion;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		if (mgcpVerb.equals(MGCPVerb.extensionVerb)) {
			builder.append(extensionVerb);
		} else {
			builder.append(mgcpVerb.toString());
		}

		builder.append(" " + transactionId + " " + endpointName + " " + mgcpVersion + Constants.EOL);

		return builder.toString();
	}

	public static MGCPCommandLine parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();

		String verbText = text.substring(0, text.indexOf(" "));
		String extensionVerb = null;
		MGCPVerb verb = null;
		try {
			verb = MGCPVerb.valueOf(verbText);
		} catch (Exception ignored) {
			verb = MGCPVerb.extensionVerb;
			extensionVerb = verbText;
		}

		if (NullUtil.isNull(verb)) {
			throw new MGCPParseException("MGCPVerb not found");
		}

		text = text.substring(text.indexOf(" ") + 1);
		String transactionIdText = text.substring(0, text.indexOf(" "));
		int transactionId = ConverterUtil.toInt(transactionIdText);
		if (transactionId <= 0) {
			throw new MGCPParseException("transactionId not found");
		}

		text = text.substring(text.indexOf(" ") + 1);
		String endpointNameText = text.substring(0, text.indexOf(" "));
		EndpointName endpointName = EndpointName.parse(endpointNameText);

		String mgcpVersion = text.substring(text.indexOf(" ") + 1).trim();

		if (verb.equals(MGCPVerb.extensionVerb)) {
			return new MGCPCommandLine(extensionVerb, transactionId, endpointName, mgcpVersion);
		}

		return new MGCPCommandLine(verb, transactionId, endpointName, mgcpVersion);
	}

	public enum MGCPVerb {
		EPCF, CRCX, MDCX, DLCX, RQNT, NTFY, AUEP, AUCX, RSIP, extensionVerb
	}
}
