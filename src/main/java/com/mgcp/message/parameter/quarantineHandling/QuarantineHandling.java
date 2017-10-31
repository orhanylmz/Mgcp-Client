package com.mgcp.message.parameter.quarantineHandling;

import com.mgcp.exceptions.MGCPParseException;
import com.mgcp.message.parameter.ParameterValueContent;
import com.noyan.util.NullUtil;

public class QuarantineHandling implements ParameterValueContent {
	private LoopControl loopControl;
	private ProcessControl processControl;

	public QuarantineHandling(LoopControl loopControl) {
		this.loopControl = loopControl;
	}

	public QuarantineHandling(ProcessControl processControl) {
		this.processControl = processControl;
	}

	public QuarantineHandling(LoopControl loopControl, ProcessControl processControl) {
		this.loopControl = loopControl;
		this.processControl = processControl;
	}

	public LoopControl getLoopControl() {
		return loopControl;
	}

	public ProcessControl getProcessControl() {
		return processControl;
	}

	@Override
	public String toString() {
		if (!NullUtil.isAnyNull(loopControl, processControl)) {
			return loopControl.toString() + ", " + processControl.toString();
		}

		if (NullUtil.isNotNull(loopControl)) {
			return loopControl.toString();
		}

		return processControl.toString();
	}

	public static QuarantineHandling parse(String text) throws MGCPParseException {
		if (NullUtil.isNull(text)) {
			throw new MGCPParseException("text can not be null");
		}

		text = text.trim();
		String[] parts = text.split(",");

		if (parts.length > 1) {
			return new QuarantineHandling(LoopControl.valueOf(parts[0].trim()), ProcessControl.valueOf(parts[1].trim()));
		}

		try {
			LoopControl loopControl = LoopControl.valueOf(parts[0].trim());
			return new QuarantineHandling(loopControl);
		} catch (IllegalArgumentException ignored) {
		}

		return new QuarantineHandling(ProcessControl.valueOf(parts[0].trim()));
	}

	public enum LoopControl {
		step, loop
	}

	public enum ProcessControl {
		process, discard
	}

}
