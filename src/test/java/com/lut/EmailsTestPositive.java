package com.lut;
import rest.GETImages;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.platform.runner.JUnitPlatform;

import java.io.File;
import java.io.IOException;
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
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmailsTestPositive {

	static WebDriver driver;
	static String userEmail;
	static String catImage;
	static String dogImage;
	static String foxImage;

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
		assertEquals("nada - Disposable Temp Email", driver.getTitle());
//		driver.findElement(By.xpath("//i[@class=\"icon-plus\"]")).click();
		driver.findElement(By.xpath("//li[@class=\"items-center\"]//button")).click();
//		String userName = driver.findElement(By.xpath("//input[@class=\"user_name\"]")).getAttribute("value");
		String userName = driver.findElement(By.xpath("//input[@id=\"grid-first-name\"]")).getAttribute("value");
		driver.findElement(By.xpath("//input[@id=\"grid-first-name\"]")).clear();
		String domain = driver.findElement(By.xpath("//select")).getAttribute("value");
		Date seconds = new Date();
		userName = userName + seconds.getTime();
//		userEmail = userName + "@getnada.com";
		userEmail = userName + "@" + domain;
		driver.findElement(By.xpath("//input[@id=\"grid-first-name\"]")).sendKeys(userName);
//		driver.findElement(By.xpath("//a[@class=\"button success\"]")).click();
		driver.findElement(By.xpath("//form/button[@type=\"button\"]")).click();
//		assertEquals(driver.findElement(By.xpath("//a[@class=\" is-active\"]//span")).getText(), userEmail);
		assertEquals(driver.findElement(By.xpath("//p[@class=\"p-3\"]")).getText(), "waiting for incoming emails for " + userEmail);
	}

	@Order(2)
	@Test
	public void testGmailLoginCreateLetterSendToGetnada() {
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
		
		GETImages urlImage = new GETImages();
		catImage = urlImage.getRandomURL("http://aws.random.cat/meow", "file");
		dogImage = urlImage.getRandomURL("https://random.dog/woof.json", "url");
		foxImage = urlImage.getRandomURL("http://randomfox.ca/floof/", "image");
		
		driver.findElement(By.xpath("//div[@class=\"Am Al editable LW-avf tS-tW\"]")).sendKeys(catImage + "\n" + 
				dogImage + "\n" + foxImage + "\n");
		driver.findElement(By.xpath("//div[text()=\"Send\"]")).click();
		new WebDriverWait(driver, 400)
		    .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()=\"Message sent.\"]")));
		
		assertEquals(driver.findElement(By.xpath("//div[@class=\"vh\"]//span[@class=\"aT\"]")).isDisplayed(),true);
	}
	
	@Order(3)
	@Test
	public void testGetnadaRecieveAndCheckEmail() {
		driver.get("https://getnada.com");
		new WebDriverWait(driver, 15)
			.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class=\"msg_item\"]")));
		driver.findElement(By.xpath("//li[@class=\"msg_item\"]")).click();
		new WebDriverWait(driver, 40)
			.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@id=\"idIframe\"]")));
		
		WebElement frameElement = driver.findElement(By.xpath("//iframe[@id=\"idIframe\"]"));
		driver.switchTo().frame(frameElement);
		new WebDriverWait(driver, 40)
			.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()=\"" + catImage + "\"]")));
		new WebDriverWait(driver, 40)
			.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()=\"" + dogImage + "\"]")));
		new WebDriverWait(driver, 40)
			.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()=\"" + foxImage + "\"]")));
		
		 String catUrl = driver.findElement(By.xpath("//div[@dir=\"ltr\"]/a")).getAttribute("href");
		 String dogUrl = driver.findElement(By.xpath("(//div[@dir=\"ltr\"]/div/a)[1]")).getAttribute("href");
		 String foxUrl = driver.findElement(By.xpath("(//div[@dir=\"ltr\"]/div/a)[2]")).getAttribute("href");
		 assertEquals(catUrl, catImage);
		 assertEquals(dogUrl, dogImage);
		 assertEquals(foxUrl, foxImage);
	}
	
	public void takeScreenShot(String url, String filename) throws WebDriverException, IOException {
		driver.get(url);
		TakesScreenshot ts = (TakesScreenshot)driver;
		File f = ts.getScreenshotAs(OutputType.FILE);
		FileHandler.copy(f, new File(filename));
	}
	
		
	@Order(4)
	@Test
	public void testSaveScreenShotsToFiles() throws WebDriverException, IOException {
		
		takeScreenShot(catImage, "catImage.png");		
		File f = new File("catImage.png");
		assertEquals(f.isFile(), true);
		
		takeScreenShot(dogImage, "dogImage.png");
		f = new File("dogImage.png");
		assertEquals(f.isFile(), true);
		
		takeScreenShot(foxImage, "foxImage.png");
		f = new File("dogImage.png");
		assertEquals(f.isFile(), true);
		
	}
}
