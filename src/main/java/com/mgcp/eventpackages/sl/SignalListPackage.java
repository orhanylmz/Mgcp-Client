package com.mgcp.eventpackages.sl;

import com.mgcp.eventpackages.BasePackage;
import com.mgcp.eventpackages.BaseSignal;
import com.mgcp.eventpackages.sl.signal.ListOfSignals;
import com.mgcp.eventpackages.sl.signal.OperationCompleted;
import com.mgcp.eventpackages.sl.signal.OperationFailed;

public class SignalListPackage extends BasePackage {
	public SignalListPackage(BaseSignal signal) {
		super("SL", signal);
	}

	public static SignalListPackage generateListOfSignalsSL() {
		return new SignalListPackage(new ListOfSignals());
	}
	
	public static SignalListPackage generateOperationCompletedSL() {
		return new SignalListPackage(new OperationCompleted());
	}

	public static SignalListPackage generateOperationFailedSL() {
		return new SignalListPackage(new OperationFailed());
	}
}
