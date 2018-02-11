package com.gft.parser.sample;

public class Sample {

	public static void main(String[] args) {
		String input = "Hiermit akzeptiere ich die [Datenschutzhinweise|dialog::https://api.xs2a.com/privacy/I6bAsBBaGzs9f16NQ8AHjnba21KTthgiFdpyvi9t] und erteile ausdrücklich meine [Einwilligung|dialog::https://api.xs2a.com/privacy/I6bAsBBaGzs9f16NQ8AHjnba21KTthgiFdpyvi9t#agreement] in die dort genannte Erhebung, Verarbeitung und Nutzung meiner personenbezogenen Daten.";
		
		LinkParser linkParser = LinkParser.compile(input);
		
		System.out.println(linkParser.getFormatted());
		System.out.println(linkParser.getLinks().size());
	}

}
