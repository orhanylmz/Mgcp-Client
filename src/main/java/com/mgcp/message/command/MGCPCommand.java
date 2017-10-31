package com.mgcp.message.command;

import java.util.ArrayList;
import java.util.Arrays;

import com.configuration.Constants;
import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.MGCPMessage;
import com.mgcp.message.command.commandLine.MGCPCommandLine;
import com.mgcp.message.command.commandLine.MGCPCommandLine.MGCPVerb;
import com.mgcp.message.parameter.MGCPParameter;
import com.noyan.util.LogUtil;
import com.noyan.util.NullUtil;

public class MGCPCommand extends MGCPMessage {
	protected MGCPCommandLine commandLine;

	public MGCPCommand(MGCPCommandLine commandLine) {
		this.commandLine = commandLine;
	}

	public MGCPCommand(MGCPCommandLine commandLine, String sdpInformation) {
		this.commandLine = commandLine;
		this.sdpInformation = sdpInformation;
	}

	public MGCPCommand(MGCPCommandLine commandLine, MGCPParameter... parameters) {
		this(commandLine, null, new ArrayList<>(Arrays.asList(parameters)));
	}

	public MGCPCommand(MGCPCommandLine commandLine, String sdpInformation, MGCPParameter... parameters) {
		this(commandLine, sdpInformation, new ArrayList<>(Arrays.asList(parameters)));
	}

	public MGCPCommand(MGCPCommandLine commandLine, ArrayList<MGCPParameter> parameters) {
		this(commandLine, null, parameters);
	}

	public MGCPCommand(MGCPCommandLine commandLine, String sdpInformation, ArrayList<MGCPParameter> parameters) {
		this.commandLine = commandLine;
		this.parameters = parameters;
		this.sdpInformation = sdpInformation;
	}

	public MGCPCommandLine getCommandLine() {
		return commandLine;
	}

	public void setCommandLine(MGCPCommandLine commandLine) {
		this.commandLine = commandLine;
	}

	public MGCPVerb getCommand() {
		return commandLine.getMgcpVerb();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(commandLine.toString());

		for (int i = 0; i < parameters.size(); i++) {
			builder.append(parameters.get(i).toString());
		}

		if (NullUtil.isNotNull(sdpInformation)) {
			builder.append(Constants.EOL + sdpInformation);
		}

		return builder.toString();
	}

	public static MGCPCommand parse(String text) throws Exception {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}
		long beginDate = System.currentTimeMillis();

		text = text.trim();
		String sdpInformation = null;

		String[] parts = text.split(Constants.EOL + Constants.EOL);
		if (parts.length > 2) {
			sdpInformation = parts[parts.length - 1].trim();
			text = text.substring(0, text.indexOf(Constants.EOL + Constants.EOL)).trim();
		}

		parts = text.split(Constants.EOL);
		MGCPCommandLine commandLine = MGCPCommandLine.parse(parts[0]);

		if (parts.length < 2) {
			LogUtil.logMessage(MGCPCommand.class, "Parsed MgcpCommand Duration: " + (System.currentTimeMillis() - beginDate) + " ms");
			return new MGCPCommand(commandLine, sdpInformation);
		}

		ArrayList<MGCPParameter> mgcpParameters = new ArrayList<>();
		for (int i = 1; i < parts.length; i++) {
			MGCPParameter mgcpParameter = MGCPParameter.parse(parts[i]);
			mgcpParameters.add(mgcpParameter);
		}

		LogUtil.logMessage(MGCPCommand.class, "Parsed MgcpCommand Duration: " + (System.currentTimeMillis() - beginDate) + " ms");
		return new MGCPCommand(commandLine, sdpInformation, mgcpParameters);
	}
}
