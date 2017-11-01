package com.mgcp.ui;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.noyan.Base;

public class MGCPTest implements Base {

	public static void main(String[] args) {
		Pattern p = Pattern.compile("2..");
		Matcher m = p.matcher("200");
		boolean b = m.matches();
		System.out.println(b);
	}
}
