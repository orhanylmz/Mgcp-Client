package com.mgcp.eventpackages.au.signal;

import com.mgcp.eventpackages.BaseSignal;
import com.mgcp.eventpackages.parameters.restcomm.Signal;
import com.noyan.util.NullUtil;

public class EndSignal extends BaseSignal {
	private Signal signal;

	public EndSignal() {
		super("es");
	}

	public Signal getSignal() {
		return signal;
	}

	public void setSignal(Signal signal) {
		this.signal = signal;
	}

	@Override
	public String toString() {
		if (NullUtil.isNull(signal)) {
			return null;
		}

		return getShortcut() + "(" + signal.toString() + ")";
	}
}
