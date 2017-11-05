package com.mgcp.eventpackages.au.signal;
/**
 * Plays an announcement in situations where there is no need for interaction with the user.
Because there is no need to monitor the incoming media stream this event is an efficient mechanism for treatments, informational announcements, etc.
 */

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.mgcp.eventpackages.BaseSignal;
import com.mgcp.eventpackages.parameters.Announcement;
import com.mgcp.eventpackages.parameters.BaseEventParameter;
import com.mgcp.eventpackages.parameters.Duration;
import com.mgcp.eventpackages.parameters.Interval;
import com.mgcp.eventpackages.parameters.Iterations;
import com.noyan.util.NullUtil;

public class PlayAnnouncement extends BaseSignal {
	private Announcement announcement;
	private Iterations iterations;
	private Interval interval;
	private Duration duration;

	public PlayAnnouncement() {
		super("pa");
	}

	public Announcement getAnnouncement() {
		return announcement;
	}

	public void setAnnouncement(Announcement announcement) {
		this.announcement = announcement;
	}

	public Iterations getIterations() {
		return iterations;
	}

	public void setIterations(Iterations iterations) {
		this.iterations = iterations;
	}

	public Interval getInterval() {
		return interval;
	}

	public void setInterval(Interval interval) {
		this.interval = interval;
	}

	public Duration getDuration() {
		return duration;
	}

	public void setDuration(Duration duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		ArrayList<BaseEventParameter> parameters = new ArrayList<>();
		parameters.add(announcement);
		parameters.add(iterations);
		parameters.add(interval);
		parameters.add(duration);

		String parameterString = parameters.stream().filter(NullUtil::isNotNull).map(p -> p.toString()).collect(Collectors.joining(","));
		return getShortcut() + "(" + parameterString + ")";
	}
}
