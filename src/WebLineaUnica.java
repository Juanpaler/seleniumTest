import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import PageMetodos.Metodos;
import PageMetodos.Metodos.waitFor;

public class WebLineaUnica extends Metodos{
	
	private WebDriver driver;

	//@BeforeClass
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
	
	//@AfterMethod
	public void after(){
		waitFor.click(driver.findElement(By.id("tpi-user")));
		waitFor.click(driver.findElement(By.id("tpi-form-logoff")));
		driver.close();
	}
	
	@Test
	public void TS_Comprar_Packs_Compra_de_Packs_MIX(){
		loginPorLinea("MIX");
		irA("packs");
		waitFor.click(driver.findElement(By.id("collapseOne")));
		List<WebElement> listpack = driver.findElement(By.id("collapseOne")).findElements(By.cssSelector(".list-group-item.dev-categorias.ng-scope"));
			for(WebElement l : listpack){
				if(l.getText().toLowerCase().equals("roaming")){
					l.click();
					break;
				}
			}
		List<WebElement> roamlist = driver.findElement(By.id("collapseSMSRoaming")).findElements(By.className("cursor-pointer"));
			for(WebElement r : roamlist){
				if(r.getText().toLowerCase().equals("roaming 20 sms mundial")){
					r.click();
					break;
				}
			}
		WebElement confirm = driver.findElement(By.id("divPanel_Desc_Saldo")).findElement(By.cssSelector(".btn.btn-lg.btn-responsive.btn-primary"));
			if(confirm.getText().toLowerCase().equals("confirmar compra")){
				confirm.click();
			}
		waitFor.visibilityOfAllElements(driver.findElements(By.id("principalExito")));
		Assert.assertTrue(driver.findElement(By.id("principalExito")).isDisplayed());
			
	}
	
	@Test
	public void asdd(){
		loginPorLinea("Pre");
	}
}