package Tests;

import java.awt.AWTException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import PageMetodos.Metodos;


public class EComerce extends Metodos{
	
	private WebDriver driver;
	
	String imagen;

	@BeforeMethod (alwaysRun = true)
		public void before(){
		driver = setup();
		driver.get("https://personaluat.vtexcommercestable.com.br/");
		sleep(8000);
		try {
			driver.findElement(By.id("vtexIdUI-google-plus")).click();
			sleep(5000);
			ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
			driver.switchTo().window(tabs2.get(1));
			driver.findElement(By.id("identifierId")).sendKeys("ecomerce.testing@gmail.com");
			driver.findElement(By.id("identifierNext")).click();
			sleep(5000);
			driver.findElement(By.name("password")).sendKeys("Telecom*123");
			driver.findElement(By.id("passwordNext")).click();
			sleep(10000);
			driver.switchTo().window(tabs2.get(0));
		}catch(Exception ex1) {
			}
		
	}
	
	//@AfterMethod (alwaysRun = true)
	public void after(){
		tomarCaptura(driver,imagen);
		try{
			driver.findElement(By.id("vtex-callcenter__user-logout")).click();
			sleep(5000);
			driver.quit();
		}catch(Exception ex1){
			driver.quit();
		}
	}
	
	@Test (groups ={"Home Tienda","Visualizacion de Productos"}) 
	public void TS001(){
		imagen = "TS001";
		Boolean precio = false;
		List<WebElement> precios = driver.findElements(By.cssSelector(".product-list__price--leyend"));
			for(WebElement p : precios){
				if(p.getText().contains("\u0024")){
				precio = true;	
				}
			}
		Assert.assertTrue(precio);
	}
	
	@Test (groups ={"Home Tienda","Visualizacion de Productos"}) 
	public void TS002(){
		imagen = "TS002";
		Boolean precio = false;
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary.btn-lg.btn-block")),"contains","ver todos los equipos");
		sleep(5000);
		List<WebElement> precios = driver.findElements(By.cssSelector(".font-platform-regular.text-primary"));
			for(WebElement p : precios){
				if(p.getText().contains("\u0024")){
				precio = true;	
				}
			}
		Assert.assertTrue(precio);
	}
	
	
	
	@Test (groups ={"Home Tienda","Barra Navegacion"}) 
	public void TS003(){
		imagen="TS003";
		sleep(10000);
		WebElement searchBtn = driver.findElement(By.id("tpi-navbar-item2"));
		Actions action = new Actions(driver);
		action.moveToElement(searchBtn).perform();
		sleep(3000);
		driver.findElement(By.linkText("Pasate a Personal")).click();
		sleep(3000);
		Assert.assertTrue(driver.getCurrentUrl().equals("https://www.personal.com.ar/portabilidad/"));
	}
	
	@Test (groups ={"Home Tienda","Barra Navegacion"}) 
	public void TS004(){
		imagen="TS004";
		sleep(10000);
		WebElement searchBtn = driver.findElement(By.id("tpi-navbar-item3"));
		Actions action = new Actions(driver);
		action.moveToElement(searchBtn).perform();
		sleep(3000);
		driver.findElement(By.linkText("Comprar packs")).click();
		sleep(3000);
		Assert.assertTrue(driver.getCurrentUrl().equals("https://autogestionuat.personal.com.ar/login/"));
	}
	
	@Test (groups ={"Home Tienda","Barra Navegacion"}) 
	public void TS005(){
		imagen="TS005";;
		sleep(10000);
		WebElement searchBtn = driver.findElement(By.id("tpi-navbar-item5"));
		Actions action = new Actions(driver);
		action.moveToElement(searchBtn).perform();
		sleep(3000);
		driver.findElement(By.xpath("//strong[contains(text(),'Temas de ayuda')]")).click();
	}
	
	@Test (groups ={"Home Tienda","Barra Navegacion"}) 
	public void TS006(){
		imagen="TS006";;
		sleep(10000);
		WebElement searchBtn = driver.findElement(By.id("tpi-navbar-item5"));
		Actions action = new Actions(driver);
		action.moveToElement(searchBtn).perform();
		sleep(3000);
		driver.findElement(By.xpath("//strong[contains(text(),'Soporte tecnico')]")).click();
	}
	
	@Test (groups ={"Home Tienda","Barra Navegacion"}) 
	public void TS007(){
		imagen="TS007";;
		sleep(10000);
		WebElement searchBtn = driver.findElement(By.id("tpi-navbar-item5"));
		Actions action = new Actions(driver);
		action.moveToElement(searchBtn).perform();
		sleep(3000);
		driver.findElement(By.linkText("Lineas")).click();
		sleep(3000);
		Assert.assertTrue(driver.getCurrentUrl().equals("https://uat.personal.com.ar/ayudaysoporte/#/individuos/soporte/lineas"));
	}
	
	@Test (groups ={"Home Tienda","Barra Navegacion"}) 
	public void TS008(){
		imagen="TS008";;
		sleep(10000);
		driver.findElement(By.cssSelector(".tpi-search")).click();
		sleep(3000);
		driver.findElement(By.id("searchable1")).sendKeys("linea");
		sleep(3000);
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Tienda Online'])[1]/following::p[1]")).click();
		sleep(3000);
		Assert.assertTrue(driver.getCurrentUrl().equals("https://tienda.personal.com.ar/"));
	}
	
	@Test (groups ={"Home Tienda","Barra Navegacion"}) 
	public void TS009(){
		imagen="TS009";;
		sleep(10000);
		driver.findElement(By.cssSelector(".tpi-search")).click();
		sleep(3000);
		driver.findElement(By.id("searchable1")).sendKeys("cambio de equipo");
		sleep(3000);
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Tienda Online'])[1]/following::p[1]")).click();
		sleep(3000);
		Assert.assertTrue(driver.getCurrentUrl().equals("https://tienda.personal.com.ar/"));
	}
	
	@Test (groups ={"Home Tienda","Filtros"}) 
	public void TS010(){
		imagen="TS010";;
		sleep(10000);
		driver.findElement(By.cssSelector(".filter__item--mark.filter__item--mark-Todas.las.marcas")).click();
		sleep(3000);
		driver.findElement(By.cssSelector(".filter__item.filter__item--mark.filter__item--mark-samsung")).click();
		sleep(10000);
		List<WebElement> elementos = driver.findElements(By.cssSelector(".shelf-product-list"));
		Boolean elementoEncontraado = true;		
		for (WebElement elemento : elementos) {
		    if(!elemento.getText().contains("Samsung"))
		    {
		    	elementoEncontraado = false;
		    }		    
		}
		Assert.assertTrue(elementoEncontraado); 		
	}
	
	@Test (groups ={"Home Tienda","Filtros"}) 
	public void TS012(){
		imagen="TS012";;
		sleep(10000);
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary.btn-lg.btn-block")),"contains","ver todos los equipos");
		sleep(10000);                                      
		List<WebElement> elementos = driver.findElements(By.cssSelector(".comparator__checkbox.icn.checkbox"));
		for (WebElement elemento : elementos) {
		    elemento.click();
		    sleep(2000);
		}
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary")),"contains","comparar");
		sleep(10000); 
		elementos = driver.findElements(By.cssSelector(".buy-button.btn.btn-danger"));
		Boolean elementoEncontraado = true;		
		for (WebElement elemento : elementos) {
		    if(!elemento.getText().contains("COMPRAR"))
		    {
		    	elementoEncontraado = false;
		    }		    
		}
		Assert.assertTrue(elementoEncontraado);
		Assert.assertTrue(elementos.size()==4); 	
	}
	
	

}