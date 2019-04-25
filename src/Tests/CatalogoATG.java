package Tests;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
		
		String canal = "WEB ARNET";
		String tipo_familia="INTERNET CABLEMODEM";
		String subtipo="Adicional";
		String nombre="AU_TEST_SP"+sprint+"_ATG_NM";
		String nombre_base_instalada="AU_TEST_SP"+sprint+"_ATG_NBI";
		String nombre_corto="AU_TEST_SP"+sprint+"_AT";
		String nombre_largo="AU_TEST_SP"+sprint+"_ATG_NL";
		String nombre_crm="AU_TEST_SP"+sprint+"_ATG_NCRM";
		String id_open=nroProductoSecuencial;
		String sku="AU_TEST_SP"+sprint+"_ATG_SKU";
		String id_categoria="cat90026";
		String legales="AU_TEST_SP"+sprint+"_ATG_LEG";
		
		driver.findElement(By.name("canal")).sendKeys(canal);
		driver.findElement(By.name("tipo_familia")).sendKeys(tipo_familia);
		driver.findElement(By.name("subtipo")).sendKeys(subtipo);
		driver.findElement(By.name("nombre")).sendKeys(nombre);
		driver.findElement(By.name("nombre_base_instalada")).sendKeys(nombre_base_instalada);
		driver.findElement(By.name("nombre_corto")).sendKeys(nombre_corto);
		driver.findElement(By.name("nombre_largo")).sendKeys(nombre_largo);
		driver.findElement(By.name("nombre_crm")).sendKeys(nombre_crm);
		driver.findElement(By.name("id_open")).sendKeys(id_open);
		driver.findElement(By.name("sku")).sendKeys(sku);
		driver.findElement(By.name("id_categoria")).sendKeys(id_categoria);
		driver.findElement(By.xpath("//*[@id='collapseNuevoProducto']/div/div[1]/div/div[2]/div/div[4]/div[2]/input")).sendKeys(legales);	
		
		WaitForElement(driver, "xpath", "//*[@id=\"collapseNuevoProducto\"]/div/div[3]/div/fieldset/div/button[2]");
		driver.findElement(By.xpath("//*[@id=\"collapseNuevoProducto\"]/div/div[3]/div/fieldset/div/button[2]")).click();
		WaitForElement(driver, "xpath", "//*[@id=\"warning\"]/div/div/div[3]/button[2]");
		driver.findElement(By.xpath("//*[@id=\"warning\"]/div/div/div[3]/button[2]")).click();		
		
		Assert.assertTrue(ElementCreatedUni(driver, "xpath", "//*[@id='success']/div/div/div[3]/button[3]",5));		
		
		saveTestConfig("nroProductoSecuencial", nroProductoSecuencial);

		driver.findElement(By.xpath("//*[@id='success']/div/div/div[3]/button[3]")).click();
		
		buscarProductoPorId(nroProductoSecuencial);
		Boolean valoresValidos = true;
		
		
		valoresValidos = (valoresValidos == nombre.equals(driver.findElement(By.name("nombre")).getAttribute("value")));
		valoresValidos = (valoresValidos == nombre_corto.equals(driver.findElement(By.name("nombre_corto")).getAttribute("value")));
		valoresValidos = (valoresValidos == nombre_base_instalada.equals(driver.findElement(By.name("nombre_base_instalada")).getAttribute("value")));
		valoresValidos = (valoresValidos == nombre_largo.equals(driver.findElement(By.name("nombre_largo")).getAttribute("value")));
		valoresValidos = (valoresValidos == nombre_crm.equals(driver.findElement(By.name("nombre_crm")).getAttribute("value")));
		valoresValidos = (valoresValidos == id_open.equals(driver.findElement(By.name("id_open")).getAttribute("value")));
		valoresValidos = (valoresValidos == sku.equals(driver.findElement(By.name("sku")).getAttribute("value")));
		valoresValidos = (valoresValidos == id_categoria.equals(driver.findElement(By.name("id_categoria")).getAttribute("value")));
		valoresValidos = (valoresValidos == legales.equals(driver.findElement(By.xpath("//*[@id='collapseNuevoProducto']/div/div[1]/div/div[2]/div/div[4]/div[2]/input")).getAttribute("value")));

		Assert.assertTrue(valoresValidos);
	}
	
	@Test (groups = "CatalogoATG", priority = 0)
	public void EntidadesMaestrasProductosModificacion (){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();

		loadTestConfig();
		String nroProductoSecuencial = testConfig.getProperty("nroProductoSecuencial");
		
		loginCatalogoATG();
		buscarYClick(driver.findElements(By.cssSelector(".mdl-button.mdl-js-button.mdl-button--raised.mdl-js-ripple-effect.dropdown-toggle")),"equals","Entidades Maestras");
		driver.findElement(By.xpath("//a[contains(text(),'Productos')]")).click();		

		buscarProductoPorId(nroProductoSecuencial);
	
		driver.findElement(By.name("nombre")).sendKeys("_MOD");
		driver.findElement(By.name("nombre_base_instalada")).sendKeys("_MOD");
		driver.findElement(By.name("nombre_corto")).sendKeys("_MOD");
		driver.findElement(By.name("nombre_largo")).sendKeys("_MOD");
		driver.findElement(By.name("nombre_crm")).sendKeys("_MOD");
		driver.findElement(By.name("sku")).sendKeys("_MOD");
		driver.findElement(By.name("id_categoria")).sendKeys("_MOD");
		driver.findElement(By.xpath("//*[@id='collapseNuevoProducto']/div/div[1]/div/div[2]/div/div[4]/div[2]/input")).sendKeys("_MOD");		
		WaitForElement(driver, "xpath", "//*[@id=\"collapseNuevoProducto\"]/div/div[3]/div/fieldset/div/button[2]");
		driver.findElement(By.xpath("//*[@id=\"collapseNuevoProducto\"]/div/div[3]/div/fieldset/div/button[2]")).click();
		WaitForElement(driver, "xpath", "//*[@id=\"warning\"]/div/div/div[3]/button[2]");
		driver.findElement(By.xpath("//*[@id=\"warning\"]/div/div/div[3]/button[2]")).click();		
		
		Assert.assertTrue(ElementCreatedUni(driver, "xpath", "//*[@id='success']/div/div/div[3]/button[3]",5));		
		
		driver.findElement(By.xpath("//*[@id='success']/div/div/div[3]/button[3]")).click();
	}
	
	@Test (groups = "CatalogoATG", priority = 0)
	public void FuncionExportar(){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		boolean  existe;
		File f = new File(System.getProperty("user.home") +"\\Downloads\\Productos.csv");
		if(f.exists() && !f.isDirectory()) {
			f.delete();
		}
		loginCatalogoATG();
		buscarYClick(driver.findElements(By.cssSelector(".mdl-button.mdl-js-button.mdl-button--raised.mdl-js-ripple-effect.dropdown-toggle")),"equals","Entidades Maestras");
		driver.findElement(By.xpath("//a[contains(text(),'Productos')]")).click();
		WaitForElement(driver, "id", "panel_table_productos");
		buscarYClick(driver.findElements(By.cssSelector(".mdl-button.mdl-js-button.mdl-button--raised.mdl-js-ripple-effect.dropdown-toggle")),"equals","Exportar");
		buscarYClick(driver.findElements(By.cssSelector(".btn-dropdown-item.btn-export")),"equals","Exportar Todos");
		sleep(5000);
		if(f.exists() && !f.isDirectory()) { 
			existe=true;
		}else {
			existe=false;
		}
		Assert.assertTrue(existe);
	}
	
	@Test (groups = "CatalogoATG", priority = 0)
	public void FactibilidadComercialAltaGrupo (){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		loginCatalogoATG();
		buscarYClick(driver.findElements(By.cssSelector(".dropdown-toggle.mdl-button.mdl-js-button.mdl-button--raised.mdl-js-ripple-effect.dropdown-toggle")),"equals","Factibilidad");
		driver.findElement(By.xpath("//a[contains(text(),'Factibilidad Comercial')]")).click();
		WaitForElement(driver, "id", "panel_opciones_factibilidad");
		buscarYClick(driver.findElements(By.cssSelector(".mdl-button.mdl-js-button.mdl-js-ripple-effect")),"equals","Nuevo");
		driver.findElement(By.xpath("//*[@id=\"collapseNuevaFactibilidad\"]/div/div/form/div[1]/div/div[1]/div[1]/div/input")).sendKeys("AutoGroup");
		driver.findElement(By.xpath("//*[@id=\"collapseNuevaFactibilidad\"]/div/div/form/div[1]/div/div[1]/div[2]/div/input")).sendKeys("Autoname");
		List<WebElement> botones =  driver.findElements(By.cssSelector(".btn-Cata-base.btn-masProducto"));	
		botones.get(1).click();
		//buscarYClick(driver.findElements(By.cssSelector(".btn-Cata-base.btn-masProducto")),"equals",". . .");
		sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"modal-large\"]/div/div/div[2]/div/div/div/div[2]/div/div[1]/table/tbody/tr[1]")).click();
		buscarYClick(driver.findElements(By.cssSelector(".btn-Cata-base.btn-Guardar")),"equals","ACEPTAR");
		botones.get(2).click();
		sleep(5000);
		driver.findElement(By.xpath("//*[@id=\"modal-large\"]/div/div/div[2]/div/div/div/div[2]/article/table/tbody/tr[2]")).click();
		buscarYClick(driver.findElements(By.cssSelector(".btn-Cata-base.btn-Guardar")),"equals","ACEPTAR");
		driver.findElement(By.id("guardar")).click();
		buscarYClick(driver.findElements(By.cssSelector(".btn-Cata-base.btn-VerResult")),"equals","Confirmar");
	}
	
	@Test (groups = "CatalogoATG", priority = 0)
	public void FactibilidadComercialModificacionGrupo (){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		loginCatalogoATG();
		buscarYClick(driver.findElements(By.cssSelector(".dropdown-toggle.mdl-button.mdl-js-button.mdl-button--raised.mdl-js-ripple-effect.dropdown-toggle")),"equals","Factibilidad");
		driver.findElement(By.xpath("//a[contains(text(),'Factibilidad Comercial')]")).click();
		WaitForElement(driver, "id", "panel_opciones_factibilidad");
	
	}
}
