package com.mgcp.message.response.responseCode;

import java.util.regex.Pattern;

public class ResponseCodePatterns {
	public static Pattern PATTERN_ACKNOWLEDGEMENT = Pattern.compile("0..");
	public static Pattern PATTERN_PROVISIONAL = Pattern.compile("1..");
	public static Pattern PATTERN_SUCCESSFUL = Pattern.compile("2..");
	public static Pattern PATTERN_TRANSIENTERROR = Pattern.compile("4..");
	public static Pattern PATTERN_PERMANENTERROR = Pattern.compile("5..");
	public static Pattern PATTERN_SPECIFIC = Pattern.compile("8..");
}
