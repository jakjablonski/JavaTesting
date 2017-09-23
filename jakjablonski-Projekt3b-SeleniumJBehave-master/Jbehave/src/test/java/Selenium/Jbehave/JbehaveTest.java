package Selenium.Jbehave;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.jbehave.core.annotations.*;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JbehaveTest {
	
	private WebDriverProvider driver;
	private WebElement q;
	private Wait<WebDriver> wait;
	
	public JbehaveTest(WebDriverProvider driver){
		super();
		this.driver = driver;
	}
	
	@Given("Otwieram strone automationpractise")
	public void openPageAutomationPractise(){
		driver.get().get("http://automationpractice.com/index.php");
		driver.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 wait = new WebDriverWait(driver.get(), 10);
	}
	// Niepoprawne logowanie
	@When("przechodze to panelu logowania")
	public void goToLoginPage(){
		driver.get().findElement(By.className("login")).click();
	}
	
	@When("wpisze niepoprawny login i haslo")
	public void whenEnterWrongEmailandPass(){
		wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));
		 driver.get().findElement(By.id("email")).sendKeys("javatest123456@javatest.pl");
	     driver.get().findElement(By.id("passwd")).sendKeys("1234567");
	     driver.get().findElement(By.id("SubmitLogin")).click();
	}
	
	@Then("wyswietli sie komunikat bledu")
	public void thenErrorShows(){
		assertTrue(driver.get().findElement(By.xpath("/html/body[@id='authentication']/div[@id='page']/div[@class='columns-container']/div[@id='columns']/div[@class='row'][2]/div[@id='center_column']/div[@class='alert alert-danger']/ol/li")).getText().contains("Authentication failed."));      
		
	}
	
	
	//poprawne logowanie
	
	
	@When("wpisze poprawny login i haslo")
	public void whenEnterCorrectEmailandPass(){
		wait.until(ExpectedConditions.elementToBeClickable(By.id("email")));
		 driver.get().findElement(By.id("email")).sendKeys("javatest524@javatest.pl");
	     driver.get().findElement(By.id("passwd")).sendKeys("javatest");
	     driver.get().findElement(By.id("SubmitLogin")).click();
	}
	
	@Then("zostane zalogowany")
	public void thenLoginCorrect(){
		assertEquals(driver.get().getTitle(), "My account - My Store");
		assertTrue(driver.get().findElement(By.className("account")).getText().contains("Testnazwisko"));
		
	}
	
	@Then("wyloguje sie")
	public void thenLogout(){
		driver.get().findElement(By.className("logout")).click();
		
	}
	
	// Test szukania z parametrem
	 
	@When("Wyszukam <przedmiot>")
	public void WhenSearchProduct(@Named("przedmiot") String przedmiot){
		 driver.get().findElement(By.name("search_query")).sendKeys(przedmiot);
		  driver.get().findElement(By.cssSelector(".btn.btn-default.button-search")).click();
	}
	
	@Then("znajde <wynik> produktow")
	public void FindResult(@Named("wynik") String wynik){
		   assertTrue( driver.get().findElement(By.className("heading-counter")).getText().contains(wynik));
	
	}
	
	//Test Dodawania do koszyka
	@When("wyszukuje produkt")
	public void FindProduct(){
		 driver.get().findElement(By.name("search_query")).sendKeys("t-shirt");
	     driver.get().findElement(By.cssSelector(".btn.btn-default.button-search")).click();
	     
	}
	
	@When("dodaje do koszyka")
	public void whenAddToCart(){
		  driver.get().findElement(By.cssSelector(".button.ajax_add_to_cart_button")).click();
	}

	@Then("sprawdzam czy dodano")
	public void CheckAdd(){
		 WebDriverWait myWait = new WebDriverWait(driver.get(),5);
	      myWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("layer_cart")));
      assertTrue(driver.get().findElement(By.id("layer_cart")).getText().contains("Product successfully added to your shopping cart"));
	
	}
	
	//usuwanie z koszyka
	
	@Then("przechodze do koszyka")
	public void GoToCart(){
		WebDriverWait myWait = new WebDriverWait(driver.get(),5);
	      myWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("layer_cart")));
	      
	   driver.get().findElement(By.cssSelector("a.btn.btn-default.button.button-medium")).click();
	   assertEquals(driver.get().getTitle(), "Order - My Store");
	}
	
	@Then("usuwam z koszyka")
	public void DeleteformCart(){
		 driver.get().findElement(By.cssSelector("i.icon-trash")).click();
		   
		 WebDriverWait myWait = new WebDriverWait(driver.get(),5);
		   myWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("p.alert.alert-warning")));
	       
		   assertEquals("Your shopping cart is empty.",driver.get().findElement(By.cssSelector("p.alert.alert-warning")).getText());
	}
}
