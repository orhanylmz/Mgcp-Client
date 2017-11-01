package com.mgcp.message.response.responseCode;
/**
 * RFC 3435
 */

import java.util.Properties;

import com.noyan.Base;
import com.noyan.util.NullUtil;

public class ResponseCodeDetails implements Base {
	Properties details = new Properties();

	public ResponseCodeDetails() {
		details.put("000", "Response Acknowledgement");
		details.put("100", "The transaction is currently being executed.  An actual completion message will follow later");
		details.put("101", "The transaction has been queued for execution.  An actual completion message will follow later");
		details.put("200", "The requested transaction was executed normally.  This return code can be used for a successful response to any command");
		details.put("250", "The connection was deleted.  This return code can only be used for a successful response to a DeleteConnection command");
		details.put("250", "The connection was deleted.  This return code can only be used for a successful response to a DeleteConnection command");
		details.put("400", "The transaction could not be executed, due to some unspecified transient error");
		details.put("401", "The phone is already off hook");
		details.put("402", "The phone is already on hook");
		details.put("403", "The transaction could not be executed, because the endpoint does not have sufficient resources at this time");
		details.put("404", "Insufficient bandwidth at this time");
		details.put("405", "The transaction could not be executed, because the endpoint is \"restarting\"");
		details.put("406", "Transaction time-out.  The transaction did not complete in a reasonable period of time and has been aborted");
		details.put("407", "Transaction aborted.  The transaction was aborted by some external action, e.g., a ModifyConnection command aborted by a DeleteConnection command");
		details.put("409", "The transaction could not be executed because of internal overload");
		details.put("410", "No endpoint available.  A valid \"any of\" wildcard was used, however there was no endpoint available to satisfy the request");
		details.put("500", "The transaction could not be executed, because the endpoint is unknown");
		details.put("501", "The transaction could not be executed, because the endpoint is not ready.  This includes the case where the endpoint is out-of-service");
		details.put("502", "The transaction could not be executed, because the endpoint does not have sufficient resources (permanent condition)");
		details.put("503", "\"All of\" wildcard too complicated");
		details.put("504", "Unknown or unsupported command");
		details.put("505", "Unsupported RemoteConnectionDescriptor.  This SHOULD be used when one or more mandatory parameters or values in the RemoteConnectionDescriptor is not supported");
		details.put("506", "Unable to satisfy both LocalConnectionOptions and RemoteConnectionDescriptor.  This SHOULD be used when the LocalConnectionOptions and RemoteConnectionDescriptor contain one or more mandatory parameters or values that conflict with each other and/or cannot be supported at the same time (except for codec negotiation failure - see error code 534)");
		details.put("507", "Unsupported functionality. Some unspecified functionality required to carry out the command is not supported. Note that several other error codes have been defined for specific areas of unsupported functionality (e.g. 508, 511, etc.), and this error code SHOULD only be used if there is no other more specific error code for the unsupported functionality");
		details.put("508", "Unknown or unsupported quarantine handling");
		details.put("509", "Error in RemoteConnectionDescriptor.  This SHOULD be used when there is a syntax or semantic error in the RemoteConnectionDescriptor");
		details.put("510", "The transaction could not be executed, because some unspecified protocol error was detected.  Automatic recovery from such an error will be very difficult, and hence this code SHOULD only be used as a last resort");
		details.put("511", "The transaction could not be executed, because the command contained an unrecognized extension.  This code SHOULD be used for unsupported critical parameter extensions (\"X+\")");
		details.put("512", "The transaction could not be executed, because the gateway is not equipped to detect one of the requested events");
		details.put("513", "The transaction could not be executed, because the gateway is not equipped to generate one of the requested signals");
		details.put("514", "The transaction could not be executed, because the gateway cannot send the specified announcement");
		details.put("515", "The transaction refers to an incorrect connection-id (may have been already deleted)");
		details.put("516", "The transaction refers to an unknown call-id, or the call-id supplied is incorrect (e.g., connection-id not associated with this call-id)");
		details.put("517", "Unsupported or invalid mode");
		details.put("518", "Unsupported or unknown package.  It is RECOMMENDED to include a PackageList parameter with the list of supported packages in the response, especially if the response is generated by the Call Agent");
		details.put("519", "Endpoint does not have a digit map");
		details.put("520", "The transaction could not be executed, because the endpoint is \"restarting\".  In most cases this would be a transient error, in which case, error code 405 SHOULD be used instead.  The error code is only included here for backwards compatibility");
		details.put("521", "Endpoint redirected to another Call Agent.  The associated redirection behavior is only well-defined when this response is issued for a RestartInProgress command");
		details.put("522", "No such event or signal.  The request referred to an event or signal that is not defined in the relevant package (which could be the default package)");
		details.put("523", "Unknown action or illegal combination of actions");
		details.put("524", "Internal inconsistency in LocalConnectionOptions");
		details.put("525", "Unknown extension in LocalConnectionOptions.  This code SHOULD be used for unsupported mandatory vendor extensions (\"x+\")");
		details.put("526", "Insufficient bandwidth.  In cases where this is a transient error, error code 404 SHOULD be used instead");
		details.put("527", "Missing RemoteConnectionDescriptor");
		details.put("528", "Incompatible protocol version");
		details.put("529", "Internal hardware failure");
		details.put("530", "CAS signaling protocol error");
		details.put("531", "Failure of a grouping of trunks (e.g., facility failure)");
		details.put("532", "Unsupported value(s) in LocalConnectionOptions");
		details.put("533", "Response too large");
		details.put("534", "Codec negotiation failure");
		details.put("535", "Packetization period not supported");
		details.put("536", "Unknown or unsupported RestartMethod");
		details.put("537", "Unknown or unsupported digit map extension");
		details.put("538", "Event/signal parameter error (e.g., missing, erroneous, unsupported, unknown, etc.)");
		details.put("539", "Invalid or unsupported command parameter. This code SHOULD only be used when the parameter is neither a package or vendor extension parameter");
		details.put("540", "Per endpoint connection limit exceeded");
		details.put("541", "Invalid or unsupported LocalConnectionOptions. This code SHOULD only be used when the LocalConnectionOptions is neither a package nor a vendor extension LocalConnectionOptions");
	}

	public String getCodeDetail(String responseCode) {
		String detail = details.getProperty(responseCode);
		if (NullUtil.isNull(detail)) {
			fatal("Unknown response code " + responseCode);
			return "UNKNOWN";
		}

		return detail;
	}

}
