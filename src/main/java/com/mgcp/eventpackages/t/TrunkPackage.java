package com.mgcp.eventpackages.t;

import com.mgcp.eventpackages.BasePackage;
import com.mgcp.eventpackages.BaseSignal;
import com.mgcp.eventpackages.t.signal.Continuity1;
import com.mgcp.eventpackages.t.signal.Continuity2;
import com.mgcp.eventpackages.t.signal.ContinuityTranspoder;
import com.mgcp.eventpackages.t.signal.Loopback;
import com.mgcp.eventpackages.t.signal.OperationCompleted;
import com.mgcp.eventpackages.t.signal.OperationFailed;

public class TrunkPackage extends BasePackage {
	private TrunkPackage(BaseSignal signal) {
		super("T", signal);
	}

	public static TrunkPackage generateContinuity1T() {
		return new TrunkPackage(new Continuity1());
	}

	public static TrunkPackage generateContinuity2T() {
		return new TrunkPackage(new Continuity2());
	}

	public static TrunkPackage generateContinuityTranspoderT() {
		return new TrunkPackage(new ContinuityTranspoder());
	}

	public static TrunkPackage generateLoopbackT() {
		return new TrunkPackage(new Loopback());
	}

	public static TrunkPackage generateOperationCompletedT() {
		return new TrunkPackage(new OperationCompleted());
	}

	public static TrunkPackage generateOperationFailedT() {
		return new TrunkPackage(new OperationFailed());
	}
}
