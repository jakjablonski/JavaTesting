package ChromeTest;

import static org.junit.Assert.*;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShopTest {

	private static WebDriver driver;
	
	@BeforeClass
	public static void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
		driver = new ChromeDriver();
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}

	@AfterClass
	public static void tearDown() throws Exception {
		driver.quit();
	}
	
	public void getscreenshot() throws Exception 
    {
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
         //The below method will save the screen shot in d drive with name "screenshot.png"
            FileUtils.copyFile(scrFile, new File("D:\\screenshot.png"));
    }
	
	@Test
	public void SearchTest(){
		
			driver.get("http://automationpractice.com/index.php");
	       driver.findElement(By.name("search_query")).sendKeys("t-shirt");
			assertEquals(driver.getTitle(), "My Store");
	       
	       driver.findElement(By.cssSelector(".btn.btn-default.button-search")).click();
	       assertEquals("Showing 1 - 1 of 1 item", driver.findElement(By.className("product-count")).getText() );
	          
	        
		}
	
	@Test
	public void SearchNotExistTest(){
		
			driver.get("http://automationpractice.com/index.php");
	       driver.findElement(By.name("search_query")).sendKeys("asdfg");
			assertEquals(driver.getTitle(), "My Store");
	       
	       driver.findElement(By.cssSelector(".btn.btn-default.button-search")).click();       
	       assertTrue(driver.findElement(By.cssSelector(".alert.alert-warning")).getText().contains("No results were found for your search") );
	          
	        
		}
	
		@Test
		public void SearchbyCategory() throws Exception{
			
				driver.get("http://automationpractice.com/index.php?id_category=8&controller=category");
		       driver.findElement(By.id("layered_id_attribute_group_13")).click();
		       driver.findElement(By.id("layered_id_feature_11")).click();
		       
		       WebDriverWait myWait = new WebDriverWait(driver,5);
		       //Thread.sleep(1000);
		       //getscreenshot();
		       //myWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("layered_ajax_loader")));
		       myWait.until(ExpectedConditions.textToBePresentInElement(By.className("product-count"), "Showing 1 - 1 of 1 items" ));
		       myWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("layered_ajax_loader")));
		       
		       assertEquals("Showing 1 - 1 of 1 items", driver.findElement(By.className("product-count")).getText() );
		       assertEquals("Printed Summer Dress", driver.findElement(By.cssSelector("a.product-name")).getText());      
		        
			}
		
		@Test
		public void AddToCart(){
			driver.get("http://automationpractice.com/index.php");
		       driver.findElement(By.name("search_query")).sendKeys("t-shirt");
				assertEquals(driver.getTitle(), "My Store");

			       
		       driver.findElement(By.cssSelector(".btn.btn-default.button-search")).click();
		       driver.findElement(By.cssSelector(".button.ajax_add_to_cart_button")).click();
		       WebDriverWait myWait = new WebDriverWait(driver,5);
			      myWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("layer_cart")));
		       assertTrue(driver.findElement(By.id("layer_cart")).getText().contains("Product successfully added to your shopping cart"));
		       
		       
		}
		
		@Test
		public void DeleteFromCart(){
			driver.get("http://automationpractice.com/index.php");
		       driver.findElement(By.name("search_query")).sendKeys("t-shirt");
				assertEquals(driver.getTitle(), "My Store");

			       
		       driver.findElement(By.cssSelector(".btn.btn-default.button-search")).click();
		       driver.findElement(By.cssSelector(".button.ajax_add_to_cart_button")).click();
		       WebDriverWait myWait = new WebDriverWait(driver,5);
			      myWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("layer_cart")));
			      
			   driver.findElement(By.cssSelector("a.btn.btn-default.button.button-medium")).click();  
			   assertEquals(driver.getTitle(), "Order - My Store");
			   driver.findElement(By.cssSelector("i.icon-trash")).click();
			   
			   
			   myWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.alert.alert-warning")));
		       
			   assertEquals("Your shopping cart is empty.",driver.findElement(By.cssSelector("p.alert.alert-warning")).getText());
			 
		       
		}
		
		
		@Test
		public void EditinCart() throws Exception{
			driver.get("http://automationpractice.com/index.php");
		       driver.findElement(By.name("search_query")).sendKeys("t-shirt");
				assertEquals(driver.getTitle(), "My Store");

			       
		       driver.findElement(By.cssSelector(".btn.btn-default.button-search")).click();
		       driver.findElement(By.cssSelector(".button.ajax_add_to_cart_button")).click();
		       WebDriverWait myWait = new WebDriverWait(driver,5);
			      myWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("layer_cart")));
			      
			   driver.findElement(By.cssSelector("a.btn.btn-default.button.button-medium")).click();  
			   assertEquals(driver.getTitle(), "Order - My Store");
			   

			   driver.findElement(By.cssSelector("i.icon-plus")).click();
			   myWait.until(ExpectedConditions.textToBePresentInElement(By.id("summary_products_quantity"), "2 Products"));
		       //getscreenshot();
			   assertEquals("2 Products",driver.findElement(By.id("summary_products_quantity")).getText());
			 
		       
		}
		
}
