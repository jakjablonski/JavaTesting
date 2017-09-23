package ChromeTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


public class LoginTest {

	
	
private static WebDriver driver;
RegisterTest rt = new RegisterTest();

	@BeforeClass
	public static void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}

	@AfterClass
	public static void tearDown() throws Exception {
		//driver.quit();
	}


	
	@Test
	public void LoginInvalidTest(){
			driver.get("http://automationpractice.com/index.php");
	       driver.findElement(By.className("login")).click();
	       driver.findElement(By.id("email")).sendKeys("javatest123456@javatest.pl");
	       driver.findElement(By.id("passwd")).sendKeys("1234567");
	       
	       driver.findElement(By.id("SubmitLogin")).click();
	       
	       assertTrue(driver.findElement(By.xpath("/html/body[@id='authentication']/div[@id='page']/div[@class='columns-container']/div[@id='columns']/div[@class='row'][2]/div[@id='center_column']/div[@class='alert alert-danger']/ol/li")).getText().contains("Authentication failed."));
	       
	        
		}
	
	@Test
	public void LoginWrongFormatTest(){
			driver.get("http://automationpractice.com/index.php");
	       driver.findElement(By.className("login")).click();
	       driver.findElement(By.id("email")).sendKeys("javatest123456javatest.pl");
	       driver.findElement(By.id("passwd")).sendKeys("1234567");
	       
	       driver.findElement(By.id("SubmitLogin")).click();
	      assertTrue( driver.findElement(By.xpath("/html/body[@id='authentication']/div[@id='page']/div[@class='columns-container']/div[@id='columns']/div[@class='row'][2]/div[@id='center_column']/div[@class='alert alert-danger']/ol/li")).getText().contains("Invalid email address."));
	       
	        
		}
	@Test
	public void LoginWrongPaswordTest(){

			driver.get("http://automationpractice.com/index.php");
	       driver.findElement(By.className("login")).click();
	       driver.findElement(By.id("email")).sendKeys("javatest524@javatest.pl");
	       driver.findElement(By.id("passwd")).sendKeys("1234567");
	       
	       driver.findElement(By.id("SubmitLogin")).click();
	      assertTrue( driver.findElement(By.xpath("/html/body[@id='authentication']/div[@id='page']/div[@class='columns-container']/div[@id='columns']/div[@class='row'][2]/div[@id='center_column']/div[@class='alert alert-danger']/ol/li")).getText().contains("Authentication failed."));
	       
	        
		}
	
	@Test
	public void LoginCorrectTest(){

			driver.get("http://automationpractice.com/index.php");
	       driver.findElement(By.className("login")).click();
	       driver.findElement(By.id("email")).sendKeys("javatest524@javatest.pl");
	       driver.findElement(By.id("passwd")).sendKeys("javatest");
	       
	       driver.findElement(By.id("SubmitLogin")).click();
	       	assertEquals(driver.getTitle(), "My account - My Store");
			assertTrue(driver.findElement(By.className("account")).getText().contains("Testnazwisko"));
			
			//logout
			driver.findElement(By.className("logout")).click();
	      
	        
		}
	
	
}
