package com.mgcp.eventpackages.au.signal;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.mgcp.eventpackages.BaseSignal;
import com.mgcp.eventpackages.parameters.BaseEventParameter;
import com.mgcp.eventpackages.parameters.EndInputKey;
import com.mgcp.eventpackages.parameters.FailureAnnouncement;
import com.mgcp.eventpackages.parameters.InitialPrompt;
import com.mgcp.eventpackages.parameters.NoSpeechReprompt;
import com.mgcp.eventpackages.parameters.NonInterruptiblePlay;
import com.mgcp.eventpackages.parameters.NumberOfAttempts;
import com.mgcp.eventpackages.parameters.PostSpeechTimer;
import com.mgcp.eventpackages.parameters.PreSpeechTimer;
import com.mgcp.eventpackages.parameters.ReInputKey;
import com.mgcp.eventpackages.parameters.RecordingLengthTimer;
import com.mgcp.eventpackages.parameters.RePrompt;
import com.mgcp.eventpackages.parameters.RestartKey;
import com.mgcp.eventpackages.parameters.SuccessAnnouncement;
import com.noyan.util.NullUtil;

public class PlayRecord extends BaseSignal {
	private InitialPrompt initialPrompt;
	private RePrompt reprompt;
	private NoSpeechReprompt noSpeechReprompt;
	private FailureAnnouncement failureAnnouncement;
	private SuccessAnnouncement successAnnouncement;
	private NonInterruptiblePlay nonInterruptiblePlay;
	private PreSpeechTimer preSpeechTimer;
	private PostSpeechTimer postSpeechTimer;
	private RecordingLengthTimer recordingLengthTimer;
	private RestartKey restartKey;
	private ReInputKey reInputKey;
	private EndInputKey endInputKey;
	private NumberOfAttempts numberOfAttempts;

	public PlayRecord() {
		super("pr");
	}

	public InitialPrompt getInitialPrompt() {
		return initialPrompt;
	}

	public void setInitialPrompt(InitialPrompt initialPrompt) {
		this.initialPrompt = initialPrompt;
	}

	public RePrompt getReprompt() {
		return reprompt;
	}

	public void setReprompt(RePrompt reprompt) {
		this.reprompt = reprompt;
	}

	public NoSpeechReprompt getNoSpeechReprompt() {
		return noSpeechReprompt;
	}

	public void setNoSpeechReprompt(NoSpeechReprompt noSpeechReprompt) {
		this.noSpeechReprompt = noSpeechReprompt;
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

	public NonInterruptiblePlay getNonInterruptiblePlay() {
		return nonInterruptiblePlay;
	}

	public void setNonInterruptiblePlay(NonInterruptiblePlay nonInterruptiblePlay) {
		this.nonInterruptiblePlay = nonInterruptiblePlay;
	}

	public PreSpeechTimer getPreSpeechTimer() {
		return preSpeechTimer;
	}

	public void setPreSpeechTimer(PreSpeechTimer preSpeechTimer) {
		this.preSpeechTimer = preSpeechTimer;
	}

	public PostSpeechTimer getPostSpeechTimer() {
		return postSpeechTimer;
	}

	public void setPostSpeechTimer(PostSpeechTimer postSpeechTimer) {
		this.postSpeechTimer = postSpeechTimer;
	}

	public RecordingLengthTimer getRecordingLengthTimer() {
		return recordingLengthTimer;
	}

	public void setRecordingLengthTimer(RecordingLengthTimer recordingLengthTimer) {
		this.recordingLengthTimer = recordingLengthTimer;
	}

	public RestartKey getRestartKey() {
		return restartKey;
	}

	public void setRestartKey(RestartKey restartKey) {
		this.restartKey = restartKey;
	}

	public ReInputKey getReInputKey() {
		return reInputKey;
	}

	public void setReInputKey(ReInputKey reInputKey) {
		this.reInputKey = reInputKey;
	}

	public EndInputKey getEndInputKey() {
		return endInputKey;
	}

	public void setEndInputKey(EndInputKey endInputKey) {
		this.endInputKey = endInputKey;
	}

	public NumberOfAttempts getNumberOfAttempts() {
		return numberOfAttempts;
	}

	public void setNumberOfAttempts(NumberOfAttempts numberOfAttempts) {
		this.numberOfAttempts = numberOfAttempts;
	}

	@Override
	public String toString() {
		ArrayList<BaseEventParameter> parameters = new ArrayList<>();
		parameters.add(initialPrompt);
		parameters.add(reprompt);
		parameters.add(noSpeechReprompt);
		parameters.add(failureAnnouncement);
		parameters.add(successAnnouncement);
		parameters.add(nonInterruptiblePlay);
		parameters.add(preSpeechTimer);
		parameters.add(postSpeechTimer);
		parameters.add(recordingLengthTimer);
		parameters.add(restartKey);
		parameters.add(reInputKey);
		parameters.add(endInputKey);
		parameters.add(numberOfAttempts);

		String parameterString = parameters.stream().filter(NullUtil::isNotNull).map(p -> p.toString()).collect(Collectors.joining(","));
		return getShortcut() + "(" + parameterString + ")";
	}
}
