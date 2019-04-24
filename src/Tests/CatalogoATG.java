package Tests;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



import PageMetodos.MetodosCatalogoATG;

public class CatalogoATG extends MetodosCatalogoATG{
	
	private WebDriver driver;
	
	String nombreCaso;
	String archivoLineas="dataInput/";
	String rutaCaptura;
	String modulo="CatalogoATG";

	@BeforeClass (alwaysRun = true)
	public void beforeClass() throws IOException {
		rutaCaptura=reportDirectory(modulo);
	}
	
	@BeforeMethod (alwaysRun = true)
		public void before(){
		driver = setup();	
	}
	
	@AfterMethod (alwaysRun = true)
	public void after(){
		tomarCaptura(driver,nombreCaso,rutaCaptura);
		try {
			logoutCatalogoATG();
			driver.close();
		}catch(Exception ex1){	driver.close();
		}
	}
	
	
	@Test (groups = "CatalogoATG", priority = 0)
	public void EntidadesMaestrasProductosValidarPagina (){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		loginCatalogoATG();
		buscarYClick(driver.findElements(By.cssSelector(".mdl-button.mdl-js-button.mdl-button--raised.mdl-js-ripple-effect.dropdown-toggle")),"equals","Entidades Maestras");
		driver.findElement(By.xpath("//a[contains(text(),'Productos')]")).click();
		WaitForElement(driver, "id", "panel_table_productos");
		Assert.assertTrue(driver.findElement(By.id("panel_table_productos")).isDisplayed());
	}
	
	@Test (groups = "CatalogoATG", priority = 0)
	public void EntidadesMaestrasPromocionesValidarPagina (){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		loginCatalogoATG();
		buscarYClick(driver.findElements(By.cssSelector(".mdl-button.mdl-js-button.mdl-button--raised.mdl-js-ripple-effect.dropdown-toggle")),"equals","Entidades Maestras");
		driver.findElement(By.xpath("//a[contains(text(),'Promociones')]")).click();
		WaitForElement(driver, "id", "panel_table_promo");
		Assert.assertTrue(driver.findElement(By.id("panel_table_promo")).isDisplayed());
	}
	
	@Test (groups = "CatalogoATG", priority = 0)
	public void EntidadesMaestrasZonaGeograficaValidarPagina (){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		loginCatalogoATG();
		buscarYClick(driver.findElements(By.cssSelector(".mdl-button.mdl-js-button.mdl-button--raised.mdl-js-ripple-effect.dropdown-toggle")),"equals","Entidades Maestras");
		driver.findElement(By.xpath("//a[contains(text(),'Zona Geográfica')]")).click();
		WaitForElement(driver, "id", "panel_zona_geografica");
		Assert.assertTrue(driver.findElement(By.id("panel_zona_geografica")).isDisplayed());
	}
	
	@Test (groups = "CatalogoATG", priority = 0)
	public void PoliticaComercialOfertaDePeciosValidarPagina (){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		loginCatalogoATG();
		buscarYClick(driver.findElements(By.cssSelector(".dropdown-toggle.mdl-button.mdl-js-button.mdl-button--raised.mdl-js-ripple-effect.dropdown-toggle")),"equals","Política comercial");
		driver.findElement(By.xpath("//a[contains(text(),'Oferta de Precios')]")).click();
		WaitForElement(driver, "id", "panel_table_ofprecio");
		Assert.assertTrue(driver.findElement(By.id("panel_table_ofprecio")).isDisplayed());
	}
	
	@Test (groups = "CatalogoATG", priority = 0)
	public void PoliticaComercialOfertaDePromocionesValidarPagina (){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		loginCatalogoATG();
		buscarYClick(driver.findElements(By.cssSelector(".dropdown-toggle.mdl-button.mdl-js-button.mdl-button--raised.mdl-js-ripple-effect.dropdown-toggle")),"equals","Política comercial");
		driver.findElement(By.xpath("//a[contains(text(),'Oferta de Promociones')]")).click();
		WaitForElement(driver, "id", "panel_table_ofpromo");
		Assert.assertTrue(driver.findElement(By.id("panel_table_ofpromo")).isDisplayed());
	}
	
	
}