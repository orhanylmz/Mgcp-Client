package com.mgcp.eventpackages.au.signal;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.mgcp.eventpackages.BaseSignal;
import com.mgcp.eventpackages.parameters.BaseEventParameter;
import com.mgcp.eventpackages.parameters.EndInputKey;
import com.mgcp.eventpackages.parameters.FailureAnnouncement;
import com.mgcp.eventpackages.parameters.InitialPrompt;
import com.mgcp.eventpackages.parameters.PostSpeechTimer;
import com.mgcp.eventpackages.parameters.SuccessAnnouncement;
import com.mgcp.eventpackages.parameters.restcomm.Driver;
import com.mgcp.eventpackages.parameters.restcomm.Hotwords;
import com.mgcp.eventpackages.parameters.restcomm.Language;
import com.mgcp.eventpackages.parameters.restcomm.MaximumRecognitionTime;
import com.mgcp.eventpackages.parameters.restcomm.PartialResults;
import com.mgcp.eventpackages.parameters.restcomm.WaitingInputTime;
import com.noyan.util.NullUtil;

public class AutomaticSpeechRecognition extends BaseSignal {
	private Driver driver;
	private InitialPrompt initialPrompt;
	private FailureAnnouncement failureAnnouncement;
	private SuccessAnnouncement successAnnouncement;
	private EndInputKey endInputKey;
	private MaximumRecognitionTime maximumRecognitionTime;
	private WaitingInputTime waitingInputTime;
	private PostSpeechTimer postSpeechTimer;
	private PartialResults partialResults;
	private Hotwords hotwords;
	private Language language;

	public AutomaticSpeechRecognition() {
		super("asr");
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public InitialPrompt getInitialPrompt() {
		return initialPrompt;
	}

	public void setInitialPrompt(InitialPrompt initialPrompt) {
		this.initialPrompt = initialPrompt;
	}

	public FailureAnnouncement getFailureAnnouncement() {
		return failureAnnouncement;
	}

	public void setFailureAnnouncement(FailureAnnouncement failureAnnouncement) {
		this.failureAnnouncement = failureAnnouncement;
	}

	public SuccessAnnouncement getSuccessAnnouncement() {
		return successAnnouncement;
	}

	public void setSuccessAnnouncement(SuccessAnnouncement successAnnouncement) {
		this.successAnnouncement = successAnnouncement;
	}

	public EndInputKey getEndInputKey() {
		return endInputKey;
	}

	public void setEndInputKey(EndInputKey endInputKey) {
		this.endInputKey = endInputKey;
	}

	public MaximumRecognitionTime getMaximumRecognitionTime() {
		return maximumRecognitionTime;
	}

	public void setMaximumRecognitionTime(MaximumRecognitionTime maximumRecognitionTime) {
		this.maximumRecognitionTime = maximumRecognitionTime;
	}

	public WaitingInputTime getWaitingInputTime() {
		return waitingInputTime;
	}

	public void setWaitingInputTime(WaitingInputTime waitingInputTime) {
		this.waitingInputTime = waitingInputTime;
	}

	public PostSpeechTimer getPostSpeechTimer() {
		return postSpeechTimer;
	}

	public void setPostSpeechTimer(PostSpeechTimer postSpeechTimer) {
		this.postSpeechTimer = postSpeechTimer;
	}

	public PartialResults getPartialResults() {
		return partialResults;
	}

	public void setPartialResults(PartialResults partialResults) {
		this.partialResults = partialResults;
	}

	public Hotwords getHotwords() {
		return hotwords;
	}

	public void setHotwords(Hotwords hotwords) {
		this.hotwords = hotwords;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	@Override
	public String toString() {
		ArrayList<BaseEventParameter> parameters = new ArrayList<>();
		parameters.add(driver);
		parameters.add(initialPrompt);
		parameters.add(failureAnnouncement);
		parameters.add(successAnnouncement);
		parameters.add(endInputKey);
		parameters.add(maximumRecognitionTime);
		parameters.add(waitingInputTime);
		parameters.add(postSpeechTimer);
		parameters.add(partialResults);
		parameters.add(hotwords);
		parameters.add(language);

		String parameterString = parameters.stream().filter(NullUtil::isNotNull).map(p -> p.toString()).collect(Collectors.joining(","));
		return getShortcut() + "(" + parameterString + ")";
	}
}
