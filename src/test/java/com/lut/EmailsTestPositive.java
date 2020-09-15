package com.lut;

import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;

public class EmailsTestPositive {
	
	private WebDriver driver;

	@AfterEach
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void test() {
		System.out.println("Not yet implemented");
	}

}
