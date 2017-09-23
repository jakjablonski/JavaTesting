package ChromeTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class RegisterTest {
	
	private String imie = "Testname", nazwisko = "Testnazwisko", pass = "javatest",
	dzien = "1", miesiac = "2", rok = "1990", firma = "firma", adres = "Warszawska 1",
	adres2 = "5", miasto = "gdansk", zip = "80300", statee = "5",
	tel = "789456123", tel2 = "123456789",info = "test";
	
	
private static WebDriver driver;









	@BeforeClass
	public static void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}

	@AfterClass
	public static void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void RegisterEmailIsTakenTest(){
		Random generator = new Random(); 
		int i = generator.nextInt(100) + 1;
		
			driver.get("http://automationpractice.com/index.php");
	       driver.findElement(By.className("login")).click();
			assertEquals(driver.getTitle(), "Login - My Store");
	       
	       driver.findElement(By.id("email_create")).sendKeys("test@test.pl"); 
	       driver.findElement(By.id("SubmitCreate")).click();
	       
	       assertEquals("An account using this email address has already been registered. Please enter a valid password or request a new one.", driver.findElement(By.xpath("/html/body[@id='authentication']/div[@id='page']/div[@class='columns-container']/div[@id='columns']/div[@class='row'][2]/div[@id='center_column']/div[@class='row']/div[@class='col-xs-12 col-sm-6'][1]/form[@id='create-account_form']/div[@class='form_content clearfix']/div[@id='create_account_error']/ol/li")).getText() );
	          
	        
		}

	@Test
	public void RegisterEmptyTest(){
		Random generator = new Random(); 
		int i = generator.nextInt(100) + 1;
		
			driver.get("http://automationpractice.com/index.php");
	       driver.findElement(By.className("login")).click();
	       driver.findElement(By.id("email_create")).sendKeys("javatest"+i+"@javatest.pl"); 
	       driver.findElement(By.id("SubmitCreate")).click();
	       //formularz
			assertEquals(driver.getTitle(), "Login - My Store");

			driver.findElement(By.id("id_gender1")).click();
			driver.findElement(By.id("customer_firstname")).clear();
			driver.findElement(By.id("customer_lastname")).clear();
			driver.findElement(By.id("passwd")).clear();
			driver.findElement(By.id("newsletter")).click();
			driver.findElement(By.id("optin")).click();
			driver.findElement(By.id("firstname")).clear();
			driver.findElement(By.id("lastname")).clear();
			driver.findElement(By.id("company")).clear();
			driver.findElement(By.id("address1")).clear();
			driver.findElement(By.id("city")).clear();
			driver.findElement(By.id("postcode")).clear();
			Select state = new Select(driver.findElement(By.id("id_state")));
			state.selectByValue("");
			driver.findElement(By.id("other")).clear();
			driver.findElement(By.id("phone")).clear();
			driver.findElement(By.id("phone_mobile")).clear();       
	       
			
			//submit
			driver.findElement(By.id("submitAccount")).click();
			driver.findElement(By.cssSelector(".alert.alert-danger")).getText().contains("There are 8 errors");
			 
			
	       
	       
	       
	        
		}

	@Test
	public void RegisterCorrectTest(){
		Random generator = new Random(); 
		int i = generator.nextInt(1000) + 1;
		
		driver.get("http://automationpractice.com/index.php");
	       driver.findElement(By.className("login")).click();
	       driver.findElement(By.id("email_create")).sendKeys("javatest"+i+"@javatest.pl"); 
	       driver.findElement(By.id("SubmitCreate")).click();
		
		Select date = new Select(driver.findElement(By.id("days")));
		Select month = new Select(driver.findElement(By.id("months")));
		Select year = new Select(driver.findElement(By.id("years")));
		Select state = new Select(driver.findElement(By.id("id_state")));
		
		driver.findElement(By.id("id_gender1")).click();
		driver.findElement(By.id("customer_firstname")).sendKeys(imie);
		driver.findElement(By.id("customer_lastname")).sendKeys(nazwisko);
		assertEquals(driver.findElement(By.id("email")).getAttribute("value"), "javatest"+i+"@javatest.pl" );
		driver.findElement(By.id("passwd")).sendKeys(pass);
		date.selectByValue(dzien);
		month.selectByValue(miesiac);
		year.selectByValue(rok);
		driver.findElement(By.id("newsletter")).click();
		driver.findElement(By.id("optin")).click();
		driver.findElement(By.id("company")).sendKeys(firma);
		driver.findElement(By.id("address1")).sendKeys(adres);
		driver.findElement(By.id("address2")).sendKeys(adres2);
		driver.findElement(By.id("city")).sendKeys(miasto);
		driver.findElement(By.id("postcode")).sendKeys(zip);
		state.selectByValue(statee);
		driver.findElement(By.id("other")).sendKeys(info);
		driver.findElement(By.id("phone")).sendKeys(tel);
		driver.findElement(By.id("phone_mobile")).sendKeys(tel2);
		
		//submit
		driver.findElement(By.id("submitAccount")).click();	
	       
		assertEquals(driver.getTitle(), "My account - My Store");
		assertTrue(driver.findElement(By.className("account")).getText().contains(nazwisko));
		
		//logout
		driver.findElement(By.className("logout")).click();
	        
		}
	
	@Test
	public void RegisterIncorrectFieldTest(){
		Random generator = new Random(); 
		int i = generator.nextInt(100) + 1;
		
		driver.get("http://automationpractice.com/index.php");
	       driver.findElement(By.className("login")).click();
	       driver.findElement(By.id("email_create")).sendKeys("javatest"+i+"@javatest.pl"); 
	       driver.findElement(By.id("SubmitCreate")).click();
		
		Select date = new Select(driver.findElement(By.id("days")));
		Select month = new Select(driver.findElement(By.id("months")));
		Select year = new Select(driver.findElement(By.id("years")));
		Select state = new Select(driver.findElement(By.id("id_state")));
		
		driver.findElement(By.id("id_gender1")).click();
		driver.findElement(By.id("customer_firstname")).sendKeys("");
		driver.findElement(By.id("customer_lastname")).sendKeys(nazwisko);
		assertEquals(driver.findElement(By.id("email")).getAttribute("value"), "javatest"+i+"@javatest.pl" );
		driver.findElement(By.id("passwd")).sendKeys(pass);
		date.selectByValue(dzien);
		month.selectByValue(miesiac);
		year.selectByValue(rok);
		driver.findElement(By.id("newsletter")).click();
		driver.findElement(By.id("optin")).click();
		driver.findElement(By.id("company")).sendKeys(firma);
		driver.findElement(By.id("address1")).sendKeys(adres);
		driver.findElement(By.id("address2")).sendKeys(adres2);
		driver.findElement(By.id("city")).sendKeys(miasto);
		driver.findElement(By.id("postcode")).sendKeys(zip);
		state.selectByValue(statee);
		driver.findElement(By.id("other")).sendKeys(info);
		driver.findElement(By.id("phone")).sendKeys(tel);
		driver.findElement(By.id("phone_mobile")).sendKeys(tel2);
		
		//submit
		driver.findElement(By.id("submitAccount")).click();
		driver.findElement(By.cssSelector(".alert.alert-danger")).getText().contains("There are 1 error");
			       
	        
		}
	
}
