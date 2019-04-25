package Tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
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
	
	//@AfterMethod (alwaysRun = true)
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
	public void EntidadesMaestrasLegalesValidarPagina (){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		loginCatalogoATG();
		buscarYClick(driver.findElements(By.cssSelector(".mdl-button.mdl-js-button.mdl-button--raised.mdl-js-ripple-effect.dropdown-toggle")),"equals","Entidades Maestras");
		driver.findElement(By.xpath("//a[contains(text(),'Legales')]")).click();
		WaitForElement(driver, "id", "panel_table_legales");
		Assert.assertTrue(driver.findElement(By.id("panel_table_legales")).isDisplayed());
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
	
	@Test (groups = "CatalogoATG", priority = 0)
	public void FactibilidadComercialValidarPagina (){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		loginCatalogoATG();
		buscarYClick(driver.findElements(By.cssSelector(".dropdown-toggle.mdl-button.mdl-js-button.mdl-button--raised.mdl-js-ripple-effect.dropdown-toggle")),"equals","Factibilidad");
		driver.findElement(By.xpath("//a[contains(text(),'Factibilidad Comercial')]")).click();
		WaitForElement(driver, "id", "panel_opciones_factibilidad");
		Assert.assertTrue(driver.findElement(By.id("panel_opciones_factibilidad")).isDisplayed());
	}
	
	@Test (groups = "CatalogoATG", priority = 0)
	public void EntidadesMaestrasProductosAlta (){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();

		loadTestConfig();
		String nroProductoSecuencial = testConfig.getProperty("nroProductoSecuencial");
		String sprint = testConfig.getProperty("sprint");		
		int nroSeq = Integer.parseInt(nroProductoSecuencial);
		nroSeq = nroSeq + 1;
		nroProductoSecuencial = Integer.toString(nroSeq);				
		
		loginCatalogoATG();
		buscarYClick(driver.findElements(By.cssSelector(".mdl-button.mdl-js-button.mdl-button--raised.mdl-js-ripple-effect.dropdown-toggle")),"equals","Entidades Maestras");
		driver.findElement(By.xpath("//a[contains(text(),'Productos')]")).click();
		WaitForElement(driver, "cssSelector", ".gridContainer.tabla-article");
		driver.findElement(By.xpath("//*[@id=\"panel_table_productos\"]/article[2]/div/div[2]/button[1]")).click();

		WaitForElement(driver, "name", "canal");
		
		driver.findElement(By.name("canal")).sendKeys("WEB ARNET");
		driver.findElement(By.name("tipo_familia")).sendKeys("INTERNET CABLEMODEM");
		driver.findElement(By.name("subtipo")).sendKeys("Adicional");
		driver.findElement(By.name("nombre")).sendKeys("AU_TEST_SP"+sprint+"_ATG_NM");
		driver.findElement(By.name("nombre_base_instalada")).sendKeys("AU_TEST_SP"+sprint+"_ATG_NBI");
		driver.findElement(By.name("nombre_corto")).sendKeys("AU_TEST_SP"+sprint+"_ATG_NC");
		driver.findElement(By.name("nombre_largo")).sendKeys("AU_TEST_SP"+sprint+"_ATG_NL");
		driver.findElement(By.name("nombre_crm")).sendKeys("AU_TEST_SP"+sprint+"_ATG_NCRM");
		driver.findElement(By.name("id_open")).sendKeys(nroProductoSecuencial);
		driver.findElement(By.name("sku")).sendKeys("AU_TEST_SP"+sprint+"_ATG_SKU");
		driver.findElement(By.name("id_categoria")).sendKeys("cat90026");
		driver.findElement(By.xpath("//*[@id='collapseNuevoProducto']/div/div[1]/div/div[2]/div/div[4]/div[2]/input")).sendKeys("AU_TEST_SP"+sprint+"_ATG_LEG");		
		WaitForElement(driver, "xpath", "//*[@id=\"collapseNuevoProducto\"]/div/div[3]/div/fieldset/div/button[2]");
		driver.findElement(By.xpath("//*[@id=\"collapseNuevoProducto\"]/div/div[3]/div/fieldset/div/button[2]")).click();
		WaitForElement(driver, "xpath", "//*[@id=\"warning\"]/div/div/div[3]/button[2]");
		driver.findElement(By.xpath("//*[@id=\"warning\"]/div/div/div[3]/button[2]")).click();		
		
		Assert.assertTrue(ElementCreatedUni(driver, "xpath", "//*[@id='success']/div/div/div[3]/button[3]",5));
		
		saveTestConfig("nroProductoSecuencial", nroProductoSecuencial);

		driver.findElement(By.xpath("//*[@id='success']/div/div/div[3]/button[3]")).click();
	}
	
	@Test (groups = "CatalogoATG", priority = 0)
	public void EntidadesMaestrasProductosModificacion (){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();

		loadTestConfig();
		String nroProductoSecuencial = testConfig.getProperty("nroProductoSecuencial");
		
		loginCatalogoATG();
		buscarYClick(driver.findElements(By.cssSelector(".mdl-button.mdl-js-button.mdl-button--raised.mdl-js-ripple-effect.dropdown-toggle")),"equals","Entidades Maestras");
		driver.findElement(By.xpath("//a[contains(text(),'Productos')]")).click();
		WaitForElement(driver, "cssSelector", ".gridContainer.tabla-article");
		
		
		//driver.findElement(By.xpath("//*[@id=\"panel_table_productos\"]/article[2]/div/div[2]/button[1]")).click();

		WaitForElement(driver, "xpath", "//*[@id=\'panel_table_productos\']/article[1]/table/thead/tr/th[8]/div/button");
		driver.findElement(By.xpath("//*[@id=\'panel_table_productos\']/article[1]/table/thead/tr/th[8]/div/button")).click();

		driver.findElement(By.name("canal")).sendKeys("WEB ARNET");
		driver.findElement(By.name("tipo_familia")).sendKeys("INTERNET CABLEMODEM");
		driver.findElement(By.name("subtipo")).sendKeys("Adicional");
		driver.findElement(By.name("nombre")).sendKeys("");
		driver.findElement(By.name("nombre_base_instalada")).sendKeys("");
		driver.findElement(By.name("nombre_corto")).sendKeys("");
		driver.findElement(By.name("nombre_largo")).sendKeys("");
		driver.findElement(By.name("nombre_crm")).sendKeys("");
		driver.findElement(By.name("id_open")).sendKeys(nroProductoSecuencial);
		driver.findElement(By.name("sku")).sendKeys("");
		driver.findElement(By.name("id_categoria")).sendKeys("cat90026");
		driver.findElement(By.xpath("//*[@id='collapseNuevoProducto']/div/div[1]/div/div[2]/div/div[4]/div[2]/input")).sendKeys("");		
		WaitForElement(driver, "xpath", "//*[@id=\"collapseNuevoProducto\"]/div/div[3]/div/fieldset/div/button[2]");
		driver.findElement(By.xpath("//*[@id=\"collapseNuevoProducto\"]/div/div[3]/div/fieldset/div/button[2]")).click();
		WaitForElement(driver, "xpath", "//*[@id=\"warning\"]/div/div/div[3]/button[2]");
		driver.findElement(By.xpath("//*[@id=\"warning\"]/div/div/div[3]/button[2]")).click();		
		
		Assert.assertTrue(ElementCreatedUni(driver, "xpath", "//*[@id='success']/div/div/div[3]/button[3]",5));
		
		saveTestConfig("nroProductoSecuencial", nroProductoSecuencial);

		driver.findElement(By.xpath("//*[@id='success']/div/div/div[3]/button[3]")).click();
	}
	
}
