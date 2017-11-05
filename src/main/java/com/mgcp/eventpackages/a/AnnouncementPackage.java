package com.mgcp.eventpackages.a;

import com.mgcp.eventpackages.BasePackage;
import com.mgcp.eventpackages.BaseSignal;
import com.mgcp.eventpackages.a.signal.OperationCompleted;
import com.mgcp.eventpackages.a.signal.OperationFailed;
import com.mgcp.eventpackages.a.signal.PlayAnnouncement;

public class AnnouncementPackage extends BasePackage {
	private AnnouncementPackage(BaseSignal signal) {
		super("A", signal);
	}

	public static AnnouncementPackage generateOperationCompletedA() {
		return new AnnouncementPackage(new OperationCompleted());
	}

	public static AnnouncementPackage generateOperationFailedA() {
		return new AnnouncementPackage(new OperationFailed());
	}

	public static AnnouncementPackage generatePlayAnnouncementA() {
		return new AnnouncementPackage(new PlayAnnouncement());
	}
}
