package com.lut;
import rest.GETImages;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.platform.runner.JUnitPlatform;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;

import org.junit.runner.RunWith;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
// @RunWith(JUnitPlatform.class)
public class EmailsTestPositive {

	static WebDriver driver;
	static String userEmail;

	@BeforeClass
	static public void setUp() throws Exception {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@AfterClass
	static public void tearDown() throws Exception {
		driver.quit();
	}

	@Order(1)
	@Test
	public void testGetnadaCreateNewAccount() throws InterruptedException {
		driver.get("https://getnada.com");
		assertEquals("Nada - temp mail - fast and free", driver.getTitle());
		driver.findElement(By.xpath("//i[@class=\"icon-plus\"]")).click();
		String userName = driver.findElement(By.xpath("//input[@class=\"user_name\"]")).getAttribute("value");
		driver.findElement(By.xpath("//input[@class=\"user_name\"]")).clear();
		Date seconds = new Date();
		userName = userName + seconds.getTime();
		userEmail = userName + "@getnada.com";
		driver.findElement(By.xpath("//input[@class=\"user_name\"]")).sendKeys(userName);
		driver.findElement(By.xpath("//a[@class=\"button success\"]")).click();
		assertEquals(driver.findElement(By.xpath("//a[@class=\" is-active\"]//span")).getText(), userEmail);
	}

	@Order(2)
	@Test
	public void testGmailLoginCreateLetterSendToGetnada() {
		// open a new tab
		// driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
		// ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		// driver.switchTo().window(tabs.get(1));
		//
		driver.get("https://mail.google.com");
		String gmailUserName = System.getenv("GMAILUSERNAME");
		driver.findElement(By.xpath("//input[@id=\"identifierId\"]")).sendKeys(gmailUserName);
		driver.findElement(By.xpath("//div[@class=\"VfPpkd-RLmnJb\"]")).click();
		new WebDriverWait(driver, 40)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type=\"password\"]")));

		String gmailUserPassword = System.getenv("GMAILUSERPASSWORD");
		driver.findElement(By.xpath("//input[@class=\"whsOnd zHQkBf\"]")).sendKeys(gmailUserPassword);
		driver.findElement(By.xpath("//div[@class=\"VfPpkd-RLmnJb\"]")).click();
		new WebDriverWait(driver, 40)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@class=\"gb_va\"]")));
		WebElement element = new WebDriverWait(driver, 15)
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class=\"T-I T-I-KE L3\"]")));
		element.click();
		
		new WebDriverWait(driver, 40)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@name=\"to\"]")));
		driver.findElement(By.xpath("//textarea[@class=\"vO\"]")).sendKeys(userEmail);
		
		new WebDriverWait(driver, 40)
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name=\"subjectbox\"]")));
		driver.findElement(By.xpath("//input[@name=\"subjectbox\"]")).sendKeys("My favorite animals");
		
		//get urls 
		GETImages urlImage = new GETImages();
		String catImage = urlImage.getRandomURL("http://aws.random.cat/meow", "file");
		String dogImage = urlImage.getRandomURL("https://random.dog/woof.json", "url");
		String foxImage = urlImage.getRandomURL("http://randomfox.ca/floof/", "image");
		
		driver.findElement(By.xpath("//div[@class=\"Am Al editable LW-avf tS-tW\"]")).sendKeys(catImage + "\n" + 
				dogImage + "\n" + foxImage + "\n");
		driver.findElement(By.xpath("//div[text()=\"Send\"]")).click();
		new WebDriverWait(driver, 40)
		    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()=\"Message sent.\"]")));
		
		//assertTrue(driver.findElement(By.xpath("//div[@class=\"vh\"]//span[@class=\"aT\"]")).isDisplayed(),true);
	}
	
	@Order(3)
	@Test
	public void testGetnadaResiveAndCheckEmail() {
//		Set<String> allWindowHandles = driver.getWindowHandles(); 
//		allWindowHandles.SwitchTo("https://getnada.com");
		driver.get("https://getnada.com");
		new WebDriverWait(driver, 40)
			.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class=\"msg_item\"]")));
		driver.findElement(By.xpath("//li[@class=\"msg_item\"]")).click();
		new WebDriverWait(driver, 40)
			.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@id=\"idIframe\"]")));
		WebElement emailFrame = driver.findElement(By.xpath("//iframe[@id=\"idIframe\"]"));
		//switch windows
		new WebDriverWait(driver, 40)
			.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()=\"' + catImage + '\"]'")));
	}
}
