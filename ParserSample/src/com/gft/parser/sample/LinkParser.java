package com.gft.parser.sample;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinkParser {
	private String originalInput;
	private List<String> patternsInsideInput;
	private Map<String, String> links;
	private String formattedInput;

	private LinkParser(String input) {
		this.originalInput = input;
		
		this.obtainPattersInside();
		this.buildLinks();
		this.format();
	}

	public static LinkParser compile(String input) {
		return new LinkParser(input);
	}
	
	public Map<String, String> getLinks() {
		return this.links;
	}
	
	public String getFormatted() {
		return this.formattedInput;
	}
		
	private void buildLinks() {
		this.links = new TreeMap<>();
		for (String patternString : this.patternsInsideInput) {
			String rawLineWithoutBrackets = patternString.substring(1, patternString.length()-1);
			String[] textToShow = rawLineWithoutBrackets.split("\\|");
			if (textToShow.length > 1) {
				this.links.put(textToShow[0], textToShow[1].replaceAll("dialog::", ""));
			}
		}
	}
	
	private void obtainPattersInside() {
		Pattern p = Pattern.compile("\\[(.*?)\\]");
		Matcher m = p.matcher(this.originalInput);
		
		this.patternsInsideInput = new ArrayList<String>(m.groupCount());
		
		while(m.find()) {
			this.patternsInsideInput.add(m.group());
		}
	}
	
	private void format() {
		String toFormat = new String(this.originalInput);
		List<String> keyList = new ArrayList<String>(this.links.keySet());
		
		for(int pos = 0; pos < this.patternsInsideInput.size(); pos ++) {
			toFormat = toFormat.replace(this.patternsInsideInput.get(pos), keyList.get(pos));
		}
		
		this.formattedInput = toFormat;
	}
}