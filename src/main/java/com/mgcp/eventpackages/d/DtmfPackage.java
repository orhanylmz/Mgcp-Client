package com.mgcp.eventpackages.d;

import com.mgcp.eventpackages.BasePackage;
import com.mgcp.eventpackages.BaseSignal;
import com.mgcp.eventpackages.d.signal.Tone0;
import com.mgcp.eventpackages.d.signal.Tone1;
import com.mgcp.eventpackages.d.signal.Tone2;
import com.mgcp.eventpackages.d.signal.Tone3;
import com.mgcp.eventpackages.d.signal.Tone4;
import com.mgcp.eventpackages.d.signal.Tone5;
import com.mgcp.eventpackages.d.signal.Tone6;
import com.mgcp.eventpackages.d.signal.Tone7;
import com.mgcp.eventpackages.d.signal.Tone8;
import com.mgcp.eventpackages.d.signal.Tone9;
import com.mgcp.eventpackages.d.signal.ToneStar;
import com.mgcp.eventpackages.d.signal.ToneHash;
import com.mgcp.eventpackages.d.signal.ToneA;
import com.mgcp.eventpackages.d.signal.ToneB;
import com.mgcp.eventpackages.d.signal.ToneC;
import com.mgcp.eventpackages.d.signal.ToneD;
import com.mgcp.eventpackages.d.signal.OperationCompleted;
import com.mgcp.eventpackages.d.signal.OperationFailed;

public class DtmfPackage extends BasePackage {
	private DtmfPackage(BaseSignal signal) {
		super("D", signal);
	}

	public static DtmfPackage generateTone0D() {
		return new DtmfPackage(new Tone0());
	}

	public static DtmfPackage generateTone1D() {
		return new DtmfPackage(new Tone1());
	}

	public static DtmfPackage generateTone2D() {
		return new DtmfPackage(new Tone2());
	}

	public static DtmfPackage generateTone3D() {
		return new DtmfPackage(new Tone3());
	}

	public static DtmfPackage generateTone4D() {
		return new DtmfPackage(new Tone4());
	}

	public static DtmfPackage generateTone5D() {
		return new DtmfPackage(new Tone5());
	}

	public static DtmfPackage generateTone6D() {
		return new DtmfPackage(new Tone6());
	}

	public static DtmfPackage generateTone7D() {
		return new DtmfPackage(new Tone7());
	}

	public static DtmfPackage generateTone8D() {
		return new DtmfPackage(new Tone8());
	}

	public static DtmfPackage generateTone9D() {
		return new DtmfPackage(new Tone9());
	}

	public static DtmfPackage generateToneAD() {
		return new DtmfPackage(new ToneA());
	}

	public static DtmfPackage generateToneBD() {
		return new DtmfPackage(new ToneB());
	}

	public static DtmfPackage generateToneCD() {
		return new DtmfPackage(new ToneC());
	}

	public static DtmfPackage generateToneDD() {
		return new DtmfPackage(new ToneD());
	}

	public static DtmfPackage generateToneStarD() {
		return new DtmfPackage(new ToneStar());
	}

	public static DtmfPackage generateToneHashD() {
		return new DtmfPackage(new ToneHash());
	}

	public static DtmfPackage generateOperationCompletedD() {
		return new DtmfPackage(new OperationCompleted());
	}

	public static DtmfPackage generateOperationFailedD() {
		return new DtmfPackage(new OperationFailed());
	}
}
