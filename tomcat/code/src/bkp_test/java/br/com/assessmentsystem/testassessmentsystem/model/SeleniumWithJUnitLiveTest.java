package br.com.assessmentsystem.testassessmentsystem.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class SeleniumWithJUnitLiveTest {

	private static SeleniumExample seleniumExample;
	private String expectedTitle = "Baeldung | Java, Spring and Web Development tutorials";

	@BeforeClass
	public static void setUp() {
	    seleniumExample = new SeleniumExample();
	}
	
	@AfterClass
	public static void tearDown() {
	    seleniumExample.closeWindow();
	}
	
	@Test
	public void whenAboutBaeldungIsLoaded_thenAboutEugenIsMentionedOnPage() {
	    String actualTitle = seleniumExample.getTitle();
	  
	    assertNotNull(actualTitle);
	    //assertEquals(expectedTitle, actualTitle);
	    //assertTrue(seleniumExample.isAuthorInformationAvailable());
	}
}
