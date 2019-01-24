package test_mobile;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;


public class AppiumDriverTest {

   private String accessKey = "sample cloud accesskey";

   protected AndroidDriver<AndroidElement> driver = null;

    DesiredCapabilities dc = new DesiredCapabilities();

 
  
    @Given("User open the straitstimes application")
    public void setUp() throws MalformedURLException {

        dc.setCapability("testName", "Test Mobile Straitstimes");
        dc.setCapability("accessKey", accessKey);
        dc.setCapability("deviceQuery", "@os='android' and @version='7.0' and @category='PHONE'");
        dc.setCapability(MobileCapabilityType.APP, "com.buuuk.st/com.sph.straitstin");
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.buuuk.st/com.sph.straitstimes.views.activities.SplashActivity");        
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), dc);

    }

    @When("Open drawer menu")
    public void openDrawerMenu() {
        driver.rotate(ScreenOrientation.PORTRAIT);
        driver.findElement(By.xpath("//*[@id='drawermenu']")).click();
       }
        
    @And("Taps on user icon and clicks on Login button")
    public void tapUserIconAndClicksOnLoginButton() {
        driver.findElement(By.xpath("//*[@id='Log In']")).click();
        }
        
    @And("User enters username and password")
    public void userEntersUsernamePassword() {
        driver.findElement(By.xpath("//*[@id='usernameTextField']")).sendKeys("digitaltest9");
        driver.hideKeyboard();
        driver.findElement(By.xpath("//*[@id='passwordTextField']")).sendKeys("Sphdigital1");
        driver.hideKeyboard();
        driver.findElement(By.xpath("//*[@id='Continue']")).click();
        }
        
     @And("Verify user logged in successfully")
     public void verifyUserLoggedInSuccessfully() {
        try {
        	Thread.sleep(2000);
        	if (driver.findElement(By.name("login-user-name")).isDisplayed()){
                System.out.println("User able to login successfully");
            } else {
                System.out.println("User login authentication failed");
                Assert.assertTrue(false);
            }
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
    }
    
      @Then("Verify user is able to click on the latest tab and first article")
      public void userTapsLatestTabAndFirstArticle() {
        driver.findElement(By.xpath("//*[@id='Latest']")).click();
        driver.findElement(By.xpath("//*[@id='FirstArticle']")).click();
        }
       
    }

}
