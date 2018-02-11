package com.gft.parser.sample;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LinkParserTest {
	private String inputWithoutPatterns;
	private String fidorInput;
	private String inputWithMultiplePatterns;

	@Before
	public void setup() {
		this.fidorInput = "Hiermit akzeptiere ich die [Datenschutzhinweise|dialog::https://api.xs2a.com/privacy/I6bAsBBaGzs9f16NQ8AHjnba21KTthgiFdpyvi9t] und erteile ausdrücklich meine [Einwilligung|dialog::https://api.xs2a.com/privacy/I6bAsBBaGzs9f16NQ8AHjnba21KTthgiFdpyvi9t#agreement] in die dort genannte Erhebung, Verarbeitung und Nutzung meiner personenbezogenen Daten.";
		this.inputWithoutPatterns = "Esto no tiene patterns";
		this.inputWithMultiplePatterns = "Hola [Jose|Valor] como [estas?|Valor], alguna [novedad?|Valor]";
	}
	
	@Test
	public void test1() {
		Assert.assertEquals(2, LinkParser.compile(this.fidorInput).getLinks().size());	
	}
	
	@Test
	public void test2() {
		Assert.assertEquals(0, LinkParser.compile(this.inputWithoutPatterns).getLinks().size());	
	}
	
	@Test
	public void test3() {
		String expected = "Hiermit akzeptiere ich die Datenschutzhinweise und erteile ausdrücklich meine Einwilligung in die dort genannte Erhebung, Verarbeitung und Nutzung meiner personenbezogenen Daten.";
		
		Assert.assertEquals(expected, LinkParser.compile(this.fidorInput).getFormatted());	
	}
	
	@Test
	public void test4() {
		String expected = "Esto no tiene patterns";
		Assert.assertEquals(expected, LinkParser.compile(this.inputWithoutPatterns).getFormatted());	
	}
	
	@Test
	public void test5() {
		Assert.assertEquals(3, LinkParser.compile(this.inputWithMultiplePatterns).getLinks().size());	
	}
	
	@Test
	public void test6() {
		String expected = "Hola Jose como estas?, alguna novedad?";
		Assert.assertEquals(expected, LinkParser.compile(this.inputWithMultiplePatterns).getFormatted());	
	}

}
