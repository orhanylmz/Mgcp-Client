package com.mgcp.eventpackages.au;

import com.mgcp.eventpackages.BasePackage;
import com.mgcp.eventpackages.BaseSignal;
import com.mgcp.eventpackages.au.signal.AutomaticSpeechRecognition;
import com.mgcp.eventpackages.au.signal.EndSignal;
import com.mgcp.eventpackages.au.signal.PlayAnnouncement;
import com.mgcp.eventpackages.au.signal.PlayCollect;
import com.mgcp.eventpackages.au.signal.PlayRecord;

public class AdvancedAudioPackage extends BasePackage {
	private AdvancedAudioPackage(BaseSignal signal) {
		super("AU", signal);
	}

	public static AdvancedAudioPackage generateAutomaticSpeechRecognitionAU() {
		return new AdvancedAudioPackage(new AutomaticSpeechRecognition());
	}

	public static AdvancedAudioPackage generateEndSignalAU() {
		return new AdvancedAudioPackage(new EndSignal());
	}

	public static AdvancedAudioPackage generatePlayAnnouncementAU() {
		return new AdvancedAudioPackage(new PlayAnnouncement());
	}

	public static AdvancedAudioPackage generatePlayCollectAU() {
		return new AdvancedAudioPackage(new PlayCollect());
	}

	public static AdvancedAudioPackage generatePlayRecordAU() {
		return new AdvancedAudioPackage(new PlayRecord());
	}
}
