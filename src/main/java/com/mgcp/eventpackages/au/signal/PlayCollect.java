package com.mgcp.eventpackages.au.signal;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.mgcp.eventpackages.BaseSignal;
import com.mgcp.eventpackages.parameters.BaseEventParameter;
import com.mgcp.eventpackages.parameters.DigitPattern;
import com.mgcp.eventpackages.parameters.EndInputKey;
import com.mgcp.eventpackages.parameters.FailureAnnouncement;
import com.mgcp.eventpackages.parameters.FirstDigitTimer;
import com.mgcp.eventpackages.parameters.IncludeEndInputKey;
import com.mgcp.eventpackages.parameters.InitialPrompt;
import com.mgcp.eventpackages.parameters.InterDigitTimer;
import com.mgcp.eventpackages.parameters.MaximumDigits;
import com.mgcp.eventpackages.parameters.MinimumDigits;
import com.mgcp.eventpackages.parameters.NoDigitsReprompt;
import com.mgcp.eventpackages.parameters.NonInterruptiblePlay;
import com.mgcp.eventpackages.parameters.NumberOfAttempts;
import com.mgcp.eventpackages.parameters.ReInputKey;
import com.mgcp.eventpackages.parameters.RePrompt;
import com.mgcp.eventpackages.parameters.RestartKey;
import com.mgcp.eventpackages.parameters.StartInputKey;
import com.mgcp.eventpackages.parameters.SuccessAnnouncement;
import com.noyan.util.NullUtil;

public class PlayCollect extends BaseSignal {
	private InitialPrompt initialPrompt;
	private RePrompt reprompt;
	private NoDigitsReprompt noDigitsReprompt;
	private FailureAnnouncement failureAnnouncement;
	private SuccessAnnouncement successAnnouncement;
	private NonInterruptiblePlay nonInterruptiblePlay;
	private MaximumDigits maximumDigits;
	private MinimumDigits minimumDigits;
	private DigitPattern digitPattern;
	private FirstDigitTimer firstDigitTimer;
	private InterDigitTimer interDigitTimer;
	private RestartKey restartKey;
	private ReInputKey reInputKey;
	private StartInputKey startInputKey;
	private EndInputKey endInputKey;
	private IncludeEndInputKey includeEndInputKey;
	private NumberOfAttempts numberOfAttempts;

	public PlayCollect() {
		super("pc");
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

	public NoDigitsReprompt getNoDigitsReprompt() {
		return noDigitsReprompt;
	}

	public void setNoDigitsReprompt(NoDigitsReprompt noDigitsReprompt) {
		this.noDigitsReprompt = noDigitsReprompt;
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

	public MaximumDigits getMaximumDigits() {
		return maximumDigits;
	}

	public void setMaximumDigits(MaximumDigits maximumDigits) {
		this.maximumDigits = maximumDigits;
	}

	public MinimumDigits getMinimumDigits() {
		return minimumDigits;
	}

	public void setMinimumDigits(MinimumDigits minimumDigits) {
		this.minimumDigits = minimumDigits;
	}

	public DigitPattern getDigitPattern() {
		return digitPattern;
	}

	public void setDigitPattern(DigitPattern digitPattern) {
		this.digitPattern = digitPattern;
	}

	public FirstDigitTimer getFirstDigitTimer() {
		return firstDigitTimer;
	}

	public void setFirstDigitTimer(FirstDigitTimer firstDigitTimer) {
		this.firstDigitTimer = firstDigitTimer;
	}

	public InterDigitTimer getInterDigitTimer() {
		return interDigitTimer;
	}

	public void setInterDigitTimer(InterDigitTimer interDigitTimer) {
		this.interDigitTimer = interDigitTimer;
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

	public StartInputKey getStartInputKey() {
		return startInputKey;
	}

	public void setStartInputKey(StartInputKey startInputKey) {
		this.startInputKey = startInputKey;
	}

	public EndInputKey getEndInputKey() {
		return endInputKey;
	}

	public void setEndInputKey(EndInputKey endInputKey) {
		this.endInputKey = endInputKey;
	}

	public IncludeEndInputKey getIncludeEndInputKey() {
		return includeEndInputKey;
	}

	public void setIncludeEndInputKey(IncludeEndInputKey includeEndInputKey) {
		this.includeEndInputKey = includeEndInputKey;
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
		parameters.add(noDigitsReprompt);
		parameters.add(failureAnnouncement);
		parameters.add(successAnnouncement);
		parameters.add(nonInterruptiblePlay);
		parameters.add(maximumDigits);
		parameters.add(minimumDigits);
		parameters.add(digitPattern);
		parameters.add(firstDigitTimer);
		parameters.add(interDigitTimer);
		parameters.add(restartKey);
		parameters.add(reInputKey);
		parameters.add(startInputKey);
		parameters.add(endInputKey);
		parameters.add(includeEndInputKey);
		parameters.add(numberOfAttempts);

		String parameterString = parameters.stream().filter(NullUtil::isNotNull).map(p -> p.toString()).collect(Collectors.joining(","));
		return getShortcut() + "(" + parameterString + ")";
	}
}
