import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import PageMetodos.Metodos;

public class WebLineaUnica extends Metodos{
	
	private WebDriver driver;

	@BeforeClass
	public void apis(){
		driver = setup();
		driver.get("https://resourcesuat.telecom.com.ar/styles/v1/css/tpstyle.css");
		sleep(5000);
		((JavascriptExecutor) driver).executeScript("window.open('https://loginuat.telecom.com.ar/v1/sdk.js');");
		sleep(5000);
		driver.quit();
	}
	
	@BeforeMethod
	public void before(){
		driver = setup();
	}
	
	@AfterMethod
	public void after(){
		driver.findElement(By.id("tpi-user")).click();
		sleep(2500);
		driver.findElement(By.id("tpi-form-logoff")).click();
		driver.close();
	}
	
	@Test
	public void asd (){
		loginPorLinea("MIX");
	}
	
	@Test
	public void asdd(){
		loginPorLinea("Pre");
	}
}