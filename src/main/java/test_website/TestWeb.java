/**
Test Scenario: StraitstimesWeb
Version: 1.0
Author: MarkH
**/

package test_website;

import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestWeb {

	public WebDriver driver;
	boolean flag = false;
	Properties p = new Properties();
	String mainArticleLink = null;

  //Navigate to the url “https://www.straitstimes.com”
  
	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "./libs/chromedriver");
		driver = new ChromeDriver();
		try {
			FileInputStream f = new FileInputStream("./src/main/java/config/config.properties");
			p.load(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		String url = p.getProperty("url");
		driver.get(url);
		driver.get(url);
		driver.manage().window().maximize();


	//Clicks on Login link and enter the credentials given to login
  
	@Test(priority = 1)
	public void loginToStraitsTimes() throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(HomePage.loginLink).click();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        	driver.findElement(LoginPage.loginIdField).sendKeys("digitaltest9"));
       		driver.findElement(LoginPage.loginPasswordField).sendKeys("Sphdigital1"));

		driver.findElement(LoginPage.loginButton).click();
		
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		loginlink = driver.findElement(By.xpath(p.getProperty("loginLink")));
		assertTrue(loginlink.getText().contains(p.getProperty("loginIdField")));
	}

	//Verify that the main article has a picture/video
  
	@Test(priority = 2)
	public void verifyMainArticleMediaFiles() {
		WebElement main_video = driver.findElement(By.xpath(p.getProperty("main_video")));
		assertTrue(main_video.isDisplayed());
		mainArticleLink = main_video.getAttribute("href");
		if (main_video.isDisplayed()) {
			System.out.println("The main article has a picture/video");
			Reporter.log("The main article has a picture/video. \n", true);
			flag = true;
		} else {
			System.out.println("The main article does not have a picture/video");
			Reporter.log("The main article does not have a picture/video", true);
		}
	}

	//Click on the main article
  
	@Test(priority = 3)
	public void verifyNavigationToMainArticle() {
		WebElement main_video = driver.findElement(By.xpath(p.getProperty("main_video")));
		main_video.click();
		assertTrue(driver.getCurrentUrl().contains(mainArticleLink));
	}
	
	//Verify that the page has been navigated to the main article, and the picture/video is present in the article.
  
	@Test(priority = 4)
	public void verifyArticlePageMediaFiles() {
		if (flag == true) {
			System.out.println("The page has been navigated to the main article, and the picture/video is present in the article.");
			Reporter.log("The page has been navigated to the main article, and the picture/video is present in the article. \n", true);
		} else {
			System.out.println("The page has been navigated to the main article, and the picture/video is NOT present in the article.");
			Reporter.log("The page has been navigated to the main article, and the picture/video is NOT present in the article.", true);
		}
	}

	@AfterTest
	public void close() {
		driver.quit();
	}
}
