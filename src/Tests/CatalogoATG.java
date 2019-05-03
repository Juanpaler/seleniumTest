package Tests;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
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
		String nombre_corto="AU_T_"+sprint+"_";
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
		
		buscarProductoPorId(nroProductoSecuencial, true);
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

		buscarProductoPorId(nroProductoSecuencial, true);
	
		driver.findElement(By.name("nombre")).sendKeys("_MOD");
		driver.findElement(By.name("nombre_base_instalada")).sendKeys("_MOD");
		driver.findElement(By.name("nombre_corto")).sendKeys("_MOD");
		driver.findElement(By.name("nombre_largo")).sendKeys("_MOD");
		driver.findElement(By.name("nombre_crm")).sendKeys("_MOD");
		driver.findElement(By.name("sku")).sendKeys("_MOD");
		driver.findElement(By.name("id_categoria")).sendKeys("_MOD");
		driver.findElement(By.xpath("//*[@id='collapseNuevoProducto']/div/div[1]/div/div[2]/div/div[4]/div[2]/input")).sendKeys("_MOD");			
		
		String nombre = driver.findElement(By.name("nombre")).getAttribute("value"); 
		String nombre_base_instalada = driver.findElement(By.name("nombre_corto")).getAttribute("value");
		String nombre_corto = driver.findElement(By.name("nombre_base_instalada")).getAttribute("value");
		String nombre_largo = driver.findElement(By.name("nombre_largo")).getAttribute("value");
		String nombre_crm = driver.findElement(By.name("nombre_crm")).getAttribute("value");
		String id_open = driver.findElement(By.name("id_open")).getAttribute("value");
		String sku = driver.findElement(By.name("sku")).getAttribute("value");
		String id_categoria = driver.findElement(By.name("id_categoria")).getAttribute("value");
		String legales = driver.findElement(By.xpath("//*[@id='collapseNuevoProducto']/div/div[1]/div/div[2]/div/div[4]/div[2]/input")).getAttribute("value");		
		
		WaitForElement(driver, "xpath", "//*[@id=\"collapseNuevoProducto\"]/div/div[3]/div/fieldset/div/button[2]");
		driver.findElement(By.xpath("//*[@id=\"collapseNuevoProducto\"]/div/div[3]/div/fieldset/div/button[2]")).click();
		WaitForElement(driver, "xpath", "//*[@id=\"warning\"]/div/div/div[3]/button[2]");
		driver.findElement(By.xpath("//*[@id=\"warning\"]/div/div/div[3]/button[2]")).click();
		Assert.assertTrue(ElementCreatedUni(driver, "xpath", "//*[@id='success']/div/div/div[3]/button[3]",5));				
		driver.findElement(By.xpath("//*[@id='success']/div/div/div[3]/button[3]")).click();
		
		buscarProductoPorId(nroProductoSecuencial, true);

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
	public void EntidadesMaestrasProductosEnviar (){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();

		loadTestConfig();
		String nroProductoSecuencial = testConfig.getProperty("nroProductoSecuencial");
		
		loginCatalogoATG();
		buscarYClick(driver.findElements(By.cssSelector(".mdl-button.mdl-js-button.mdl-button--raised.mdl-js-ripple-effect.dropdown-toggle")),"equals","Entidades Maestras");
		driver.findElement(By.xpath("//a[contains(text(),'Productos')]")).click();		

		buscarProductoPorId(nroProductoSecuencial, false);
	
		driver.findElement(By.xpath("//*[@id='panel_table_productos']/article[2]/div/div[3]/button")).click();		

		buscarYClick(driver.findElements(By.cssSelector(".btn-Cata-base.btn-VerResult")),"equals","Confirmar");
		List<WebElement> botonesAceptar =  driver.findElements(By.xpath("//button[@ng-click='global.aceptar()']"));
		WebElement boton = GetElementoVisible(botonesAceptar);
		boton.click();
		buscarProductoPorId(nroProductoSecuencial, false);
		String enviado = driver.findElement(By.xpath("//*[@id='panel_table_productos']/article[1]/table/tbody/tr/td[15]/div")).getText();		
		
		Assert.assertTrue(enviado.equals("Enviado"));

	}
	
	@Test (groups = "CatalogoATG", priority = 0)
	public void EntidadesMaestrasProductosAnular (){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();

		loadTestConfig();
		String nroProductoSecuencial = testConfig.getProperty("nroProductoSecuencial");
		
		loginCatalogoATG();
		buscarYClick(driver.findElements(By.cssSelector(".mdl-button.mdl-js-button.mdl-button--raised.mdl-js-ripple-effect.dropdown-toggle")),"equals","Entidades Maestras");
		driver.findElement(By.xpath("//a[contains(text(),'Productos')]")).click();		

		buscarProductoPorId(nroProductoSecuencial, false);
	
		driver.findElement(By.xpath("//*[@id='panel_table_productos']/article[2]/div/div[2]/button[4]")).click();		

		buscarYClick(driver.findElements(By.cssSelector(".btn-Cata-base.btn-VerResult")),"equals","Confirmar");
		
		buscarProductoPorId(nroProductoSecuencial, false);
		String enviado = driver.findElement(By.xpath("//*[@id='panel_table_productos']/article[1]/table/tbody/tr/td[15]/div")).getText();		
		
		Assert.assertTrue(enviado.equals("Anulado"));

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
		sleep(2000);
		WaitForElement(driver, "xpath", "//*[@id='modal-large']/div/div/div[2]/div/div/div/div[2]/div/div[1]/table/tbody/tr[1]");
		driver.findElement(By.xpath("//*[@id='modal-large']/div/div/div[2]/div/div/div/div[2]/div/div[1]/table/tbody/tr[1]")).click();
		buscarYClick(driver.findElements(By.cssSelector(".btn-Cata-base.btn-Guardar")),"equals","ACEPTAR");
		botones.get(2).click();
		sleep(2000);
		List<WebElement> elementos = driver.findElements(By.xpath("//*[@id='modal-large']/div/div/div[2]/div/div/div/div[2]/article/table/tbody/tr[1]/td[1]/div/label"));
		WebElement elemento = GetElementoVisible(elementos);
		elemento.click();
		buscarYClick(driver.findElements(By.cssSelector(".btn-Cata-base.btn-Guardar")),"equals","ACEPTAR");
		driver.findElement(By.id("guardar")).click();
		buscarYClick(driver.findElements(By.cssSelector(".btn-Cata-base.btn-VerResult")),"equals","Confirmar");
		Assert.assertTrue(ElementCreatedUni(driver, "xpath", "//*[@id='success']/div/div/div[2]/div[1]/ul/li", 5));				
		List<WebElement> botonesAceptar =  driver.findElements(By.xpath("//button[@ng-click='global.aceptar()']"));
		WebElement boton = GetElementoVisible(botonesAceptar);
		boton.click();

	}
	
	@Test (groups = "CatalogoATG", priority = 0)
	public void FactibilidadComercialModificacionGrupo() {
		nombreCaso = new Object() {
		}.getClass().getEnclosingMethod().getName();
		loginCatalogoATG();
		buscarYClick(driver.findElements(By.cssSelector(
				".dropdown-toggle.mdl-button.mdl-js-button.mdl-button--raised.mdl-js-ripple-effect.dropdown-toggle")),
				"equals", "Factibilidad");
		driver.findElement(By.xpath("//a[contains(text(),'Factibilidad Comercial')]")).click();
		WaitForElement(driver, "id", "panel_opciones_factibilidad");
		String grupo;
		Boolean encontrado = false;
		while (!encontrado) {
			for (int i = 1; i < 7; i++) {
				grupo = driver.findElement(By.xpath(
						"//*[@id=\"panel_opciones_factibilidad\"]/div[2]/div[2]/div/div/table/tbody/tr["+i+"]/td[3]"))
						.getText();
				if (grupo.equals("AutoGroup2")) {
					driver.findElement(By.xpath(
							"//*[@id=\"panel_opciones_factibilidad\"]/div[2]/div[2]/div/div/table/tbody/tr["+i+"]/td[3]"))
							.click();
					encontrado = true;
					break;
				}
			}
			if(encontrado.equals(false)) {
				buscarYClick(driver.findElements(By.cssSelector(".mdl-button.mdl-button-paginado")), "equals", "»");
			}
		}
		buscarYClick(driver.findElements(By.cssSelector(
				".mdl-button.mdl-js-button.mdl-js-ripple-effect")),
				"equals", "Modificar");
		driver.findElement(By.xpath("//*[@id=\"collapseNuevaFactibilidad\"]/div/div/form/div[1]/div/div[1]/div[1]/div/input")).clear();
		driver.findElement(By.xpath("//*[@id=\"collapseNuevaFactibilidad\"]/div/div/form/div[1]/div/div[1]/div[1]/div/input")).sendKeys("AutoGroup2Mdificado");
		buscarYClick(driver.findElements(By.id("guardar")),"equals","GUARDAR");
		buscarYClick(driver.findElements(By.cssSelector(".btn-Cata-base.btn-VerResult")),"equals","Confirmar");
		List<WebElement> botonesAceptar =  driver.findElements(By.xpath("//button[@ng-click='global.aceptar()']"));
		WebElement boton = GetElementoVisible(botonesAceptar);
		boton.click();
	}
	
	@Test (groups = "CatalogoATG", priority = 0)
	public void FactibilidadComercialEliminarGrupo() {
		nombreCaso = new Object() {
		}.getClass().getEnclosingMethod().getName();
		loginCatalogoATG();
		buscarYClick(driver.findElements(By.cssSelector(
				".dropdown-toggle.mdl-button.mdl-js-button.mdl-button--raised.mdl-js-ripple-effect.dropdown-toggle")),
				"equals", "Factibilidad");
		driver.findElement(By.xpath("//a[contains(text(),'Factibilidad Comercial')]")).click();
		WaitForElement(driver, "id", "panel_opciones_factibilidad");
		String grupo;
		Boolean encontrado = false;
		while (!encontrado) {
			for (int i = 1; i < 7; i++) {
				grupo = driver.findElement(By.xpath(
						"//*[@id=\"panel_opciones_factibilidad\"]/div[2]/div[2]/div/div/table/tbody/tr["+i+"]/td[3]"))
						.getText();
				if (grupo.equals("AutoGroup")) {
					driver.findElement(By.xpath(
							"//*[@id=\"panel_opciones_factibilidad\"]/div[2]/div[2]/div/div/table/tbody/tr["+i+"]/td[3]"))
							.click();
					encontrado = true;
					break;
				}
			}
			if(encontrado.equals(false)) {
				buscarYClick(driver.findElements(By.cssSelector(".mdl-button.mdl-button-paginado")), "equals", "»");
			}
		}
		buscarYClick(driver.findElements(By.cssSelector(
				".mdl-button.mdl-js-button.mdl-js-ripple-effect")),
				"equals", "Eliminar");
		Assert.assertTrue(false);	//No funciona el boton eliminar	
	}
	
	@Test (groups = "CatalogoATG", priority = 0)
	public void PoliticaComercialOfertaDePromocionesAlta (){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		loginCatalogoATG();
		buscarYClick(driver.findElements(By.cssSelector(".dropdown-toggle.mdl-button.mdl-js-button.mdl-button--raised.mdl-js-ripple-effect.dropdown-toggle")),"equals","Política comercial");
		driver.findElement(By.xpath("//a[contains(text(),'Oferta de Promociones')]")).click();
		WaitForElement(driver, "id", "panel_table_ofpromo");
		buscarYClick(driver.findElements(By.cssSelector(".mdl-button.mdl-js-button.mdl-js-ripple-effect")),"equals","Nuevo");
		driver.findElement(By.xpath("//*[@id=\"collapseNuevaOfPromocion\"]/div/form/div/div[1]/div[1]/div/div[1]/div[1]/button")).click();
		sleep(2000);
		List<WebElement> botonesAceptar =  driver.findElements(By.xpath("//*[@id=\"table_collapse\"]/div/article/div[2]/div/div/div[1]/div/label"));
		WebElement boton = GetElementoVisible(botonesAceptar);
		boton.click();
		buscarYClick(driver.findElements(By.cssSelector(".btn-Cata-base.btn-Guardar")),"equals","Aceptar");
		driver.findElement(By.xpath("//*[@id=\"collapseNuevaOfPromocion\"]/div/form/div/div[1]/div[1]/div/div[2]/div[1]/button[1]")).click();
		sleep(2000);
		botonesAceptar =  driver.findElements(By.xpath("//*[@id=\"panel_table_pais\"]/table/tbody/tr[1]/td[1]/div/label"));
		boton = GetElementoVisible(botonesAceptar);
		boton.click();
		buscarYClick(driver.findElements(By.cssSelector(".btn-Cata-base.btn-Guardar")),"equals","Aceptar");
		driver.findElement(By.xpath("//*[@id=\"collapseNuevaOfPromocion\"]/div/form/div/div[1]/div[1]/div/div[3]/div[1]/button[1]")).click(); 
		sleep(2000); 
		botonesAceptar = driver.findElements(By.xpath("//*[@id=\"panel_table_provincias\"]/article/table/tbody/tr[2]/td[1]/div/label")); 
		boton = GetElementoVisible(botonesAceptar); 
		boton.click();
		buscarYClick(driver.findElements(By.cssSelector(".btn-Cata-base.btn-Guardar")),"equals","Aceptar"); 
        driver.findElement(By.xpath("//*[@id=\"collapseNuevaOfPromocion\"]/div/form/div/div[1]/div[1]/div/div[4]/div[1]/button")).click(); 
        sleep(2000); buscarYClick(driver.findElements(By.cssSelector(".btn.btn-default.btn-filter.dropdown-toggle.ng-binding")),"equals","PARTIDO"); 
        botonesAceptar = driver.findElements(By.xpath("//*[@id=\"modal-large\"]/div/div/div[2]/div/div/div/div[2]/article/table/thead/tr/th[2]/div/ul/li[5]/div[4]/div/input")); 
        boton = GetElementoVisible(botonesAceptar); boton.sendKeys("ROQUE PEREZ");
		sleep(2000);
		buscarYClick(driver.findElements(By.cssSelector(".ng-binding.ng-scope")),"equals","ROQUE PEREZ"); 
		botonesAceptar = driver.findElements(By.xpath("//*[@id=\"modal-large\"]/div/div/div[2]/div/div/div/div[2]/article/table/tbody/tr[1]/td[1]/div/label")); 
		boton = GetElementoVisible(botonesAceptar); 
		boton.click();
		buscarYClick(driver.findElements(By.cssSelector(".btn-Cata-base.btn-Guardar")),"equals","Aceptar"); 
		buscarYClick(driver.findElements(By.cssSelector(".btn-Cata-base.btn-Guardar")),"equals","GUARDAR");
		buscarYClick(driver.findElements(By.cssSelector(".btn-Cata-base.btn-VerResult")),"equals","Confirmar");
		botonesAceptar =  driver.findElements(By.xpath("//button[@ng-click='global.aceptar()']"));
		boton = GetElementoVisible(botonesAceptar);
		boton.click();
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-default.btn-filter.dropdown-toggle.ng-binding")),"equals","PROMOCION");
		sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"home\"]/article[1]/table/thead/tr/th[3]/div/ul/li[5]/div[2]/div/input")).sendKeys("CV HD");
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-default.btn-filter.dropdown-toggle.ng-binding")),"equals","PROMOCION");
		String promo=driver.findElement(By.xpath("//*[@id=\"home\"]/article[1]/table/tbody/tr/td[3]/div")).getText();
		Assert.assertTrue(promo.equals("CV HD + 25MB WIFI 12Mx70%"));
		String pais=driver.findElement(By.xpath("//*[@id=\"home\"]/article[1]/table/tbody/tr/td[4]/div")).getText();
		Assert.assertTrue(pais.equals("ARGENTINA"));
		String provincia=driver.findElement(By.xpath("//*[@id=\"home\"]/article[1]/table/tbody/tr/td[5]/div")).getText();
		Assert.assertTrue(provincia.equals("BUENOS AIRES"));
		String localidad=driver.findElement(By.xpath("//*[@id=\"home\"]/article[1]/table/tbody/tr/td[6]/div")).getText();
		Assert.assertTrue(localidad.equals("ROQUE PEREZ"));
	}
	
	
	@Test (groups = "CatalogoATG", priority = 0)
	public void PoliticaComercialOfertaDePromocionesModificacion(){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		loginCatalogoATG();
		buscarYClick(driver.findElements(By.cssSelector(".dropdown-toggle.mdl-button.mdl-js-button.mdl-js-ripple-effect")),"equals","Política comercial");                                               
		driver.findElement(By.xpath("//a[contains(text(),'Oferta de Promociones')]")).click();
		WaitForElement(driver, "id", "panel_table_ofpromo");
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-default.btn-filter.dropdown-toggle.ng-binding")),"equals","PROMOCION");
		sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"home\"]/article[1]/table/thead/tr/th[3]/div/ul/li[5]/div[2]/div/input")).sendKeys("CV HD");
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-default.btn-filter.dropdown-toggle.ng-binding")),"equals","PROMOCION");
		String promo=driver.findElement(By.xpath("//*[@id=\"home\"]/article[1]/table/tbody/tr/td[3]/div")).getText();
		Assert.assertTrue(promo.equals("CV HD + 25MB WIFI 12Mx70%"));
		String pais=driver.findElement(By.xpath("//*[@id=\"home\"]/article[1]/table/tbody/tr/td[4]/div")).getText();
		Assert.assertTrue(pais.equals("ARGENTINA"));
		String provincia=driver.findElement(By.xpath("//*[@id=\"home\"]/article[1]/table/tbody/tr/td[5]/div")).getText();
		Assert.assertTrue(provincia.equals("BUENOS AIRES"));
		String localidad=driver.findElement(By.xpath("//*[@id=\"home\"]/article[1]/table/tbody/tr/td[6]/div")).getText();
		Assert.assertTrue(localidad.equals("ROQUE PEREZ"));
		buscarYClick(driver.findElements(By.cssSelector(".mdl-button.mdl-js-button.mdl-js-ripple-effect")),"equals","Eliminar");
		driver.findElement(By.xpath("//*[@id=\"eliminarOfertaPromocion\"]/div/form/div/div[1]/div/div/div[1]/div[1]/button")).click();
		List<WebElement> botonesAceptar =  driver.findElements(By.xpath("//*[@id=\"table_collapse\"]/div/article/div[2]/div/div/div[1]/div/label"));
		WebElement boton = GetElementoVisible(botonesAceptar);
		boton.click();
		buscarYClick(driver.findElements(By.cssSelector(".btn-Cata-base.btn-Guardar")),"equals","Aceptar");
		driver.findElement(By.xpath("//*[@id=\"eliminarOfertaPromocion\"]/div/form/div/div[1]/div/div/div[2]/div[1]/button")).click();
        sleep(2000); 
        buscarYClick(driver.findElements(By.cssSelector(".btn.btn-default.btn-filter.dropdown-toggle.ng-binding")),"equals","PARTIDO"); 
        botonesAceptar = driver.findElements(By.xpath("//*[@id=\"modal-large\"]/div/div/div[2]/div/div/div/div[2]/article/table/thead/tr/th[2]/div/ul/li[5]/div[4]/div/input")); 
        boton = GetElementoVisible(botonesAceptar); 
        boton.sendKeys("ROQUE PEREZ");
		sleep(2000);
		buscarYClick(driver.findElements(By.cssSelector(".ng-binding.ng-scope")),"equals","ROQUE PEREZ"); 
		botonesAceptar = driver.findElements(By.xpath("//*[@id=\"modal-large\"]/div/div/div[2]/div/div/div/div[2]/article/table/tbody/tr[1]/td[1]/div/label")); 
		boton = GetElementoVisible(botonesAceptar); 
		boton.click();
		buscarYClick(driver.findElements(By.cssSelector(".btn-Cata-base.btn-Guardar")),"equals","Aceptar");
		buscarYClick(driver.findElements(By.cssSelector(".btn-Cata-base.btn-Guardar")),"equals","GUARDAR");
		buscarYClick(driver.findElements(By.cssSelector(".btn-Cata-base.btn-VerResult")),"equals","Confirmar");
		botonesAceptar =  driver.findElements(By.xpath("//button[@ng-click='global.aceptar()']"));
		boton = GetElementoVisible(botonesAceptar);
		boton.click();
		sleep(2000);
		Boolean isPresent = driver.findElements(By.xpath("//*[@id=\"home\"]/article[1]/table/tbody/tr/td[3]/div")).size()> 0;
		Assert.assertFalse(isPresent);
		
	}
	
	@Test (groups = "CatalogoATG", priority = 0)
	public void EntidadesMaestrasLegalesAlta (){
		
		loadTestConfig();
		String nroLegalSecuencial = testConfig.getProperty("nroLegalSecuencial");
		int nroSeq = Integer.parseInt(nroLegalSecuencial);
		nroSeq = nroSeq + 1;
		nroLegalSecuencial = Integer.toString(nroSeq);
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		loginCatalogoATG();
		buscarYClick(driver.findElements(By.cssSelector(".mdl-button.mdl-js-button.mdl-js-ripple-effect")),"equals","Entidades Maestras");
		driver.findElement(By.xpath("//a[contains(text(),'Legales')]")).click();
		WaitForElement(driver, "id", "panel_table_legales");
		buscarYClick(driver.findElements(By.cssSelector(".mdl-button.mdl-js-button.mdl-js-ripple-effect")),"equals","Nuevo");
		driver.findElement(By.xpath("//*[@id=\"collapseNuevoLegal\"]/form/div/div[1]/div/div[1]/div/div[2]/div/select")).sendKeys("WEB");
		driver.findElement(By.xpath("//*[@id=\"collapseNuevoLegal\"]/form/div/div[1]/div/div[1]/div/div[3]/div/input")).sendKeys("Legales"+nroSeq);
		driver.findElement(By.name("descripcion")).sendKeys("Descripcion");
		buscarYClick(driver.findElements(By.cssSelector(".btn-Cata-base.btn-Guardar")),"equals","GUARDAR");
		buscarYClick(driver.findElements(By.cssSelector(".btn-Cata-base.btn-VerResult")),"equals","Confirmar");
		List<WebElement> botonesAceptar =  driver.findElements(By.xpath("//button[@ng-click='global.aceptar()']"));
		WebElement boton = GetElementoVisible(botonesAceptar);
		boton.click();
		saveTestConfig("nroLegalSecuencial", nroLegalSecuencial);
		sleep(2000);
        buscarYClick(driver.findElements(By.cssSelector(".btn.btn-default.btn-filter.dropdown-toggle.ng-binding")),"equals","NOMBRE"); 
        botonesAceptar = driver.findElements(By.xpath("//*[@id=\"panel_table_legales\"]/article[1]/table/thead/tr/th[4]/div/ul/div[3]/div/input")); 
        boton = GetElementoVisible(botonesAceptar); 
        boton.sendKeys("Legales"+nroSeq);
		sleep(2000);
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-default.btn-filter.dropdown-toggle.ng-binding")),"equals","NOMBRE"); 
		String Legales=driver.findElement(By.xpath("//*[@id=\"panel_table_legales\"]/article[1]/table/tbody/tr/td[4]/div")).getText();
		Assert.assertTrue(Legales.equals("Legales"+nroSeq));
		String descripcion=driver.findElement(By.xpath("//*[@id=\"panel_table_legales\"]/article[1]/table/tbody/tr/td[5]/div")).getText();
		Assert.assertTrue(descripcion.equals("Descripcion"));
		
	}
	
	@Test (groups = "CatalogoATG", priority = 0)
	public void EntidadesMaestrasLegalesModificar (){
		loadTestConfig();
		String nroLegalSecuencial = testConfig.getProperty("nroLegalSecuencial");
		int nroSeq = Integer.parseInt(nroLegalSecuencial);
		nroLegalSecuencial = Integer.toString(nroSeq);
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		loginCatalogoATG();
		buscarYClick(driver.findElements(By.cssSelector(".mdl-button.mdl-js-button.mdl-js-ripple-effect")),"equals","Entidades Maestras");
		driver.findElement(By.xpath("//a[contains(text(),'Legales')]")).click();
		WaitForElement(driver, "id", "panel_table_legales");
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-default.btn-filter.dropdown-toggle.ng-binding")),"equals","NOMBRE"); 
		List<WebElement> botonesAceptar = driver.findElements(By.xpath("//*[@id=\"panel_table_legales\"]/article[1]/table/thead/tr/th[4]/div/ul/div[3]/div/input")); 
		WebElement boton = GetElementoVisible(botonesAceptar); 
        boton.sendKeys("Legales"+nroSeq);
		sleep(2000);
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-default.btn-filter.dropdown-toggle.ng-binding")),"equals","NOMBRE"); 
		driver.findElement(By.xpath("//*[@id=\"panel_table_legales\"]/article[1]/table/tbody/tr/td[1]/div/label")).click();
		buscarYClick(driver.findElements(By.cssSelector(".mdl-button.mdl-js-button.mdl-js-ripple-effect")),"equals","Modificar");
		driver.findElement(By.name("descripcion")).clear();
		driver.findElement(By.name("descripcion")).sendKeys("DescripcionMofidicada");
		buscarYClick(driver.findElements(By.cssSelector(".btn-Cata-base.btn-Guardar")),"equals","GUARDAR");
		buscarYClick(driver.findElements(By.cssSelector(".btn-Cata-base.btn-VerResult")),"equals","Confirmar");
		botonesAceptar =  driver.findElements(By.xpath("//button[@ng-click='global.aceptar()']"));
		boton = GetElementoVisible(botonesAceptar);
		boton.click();
		String Legales=driver.findElement(By.xpath("//*[@id=\"panel_table_legales\"]/article[1]/table/tbody/tr/td[4]/div")).getText();
		Assert.assertTrue(Legales.equals("Legales"+nroSeq));
		String descripcion=driver.findElement(By.xpath("//*[@id=\"panel_table_legales\"]/article[1]/table/tbody/tr/td[5]/div")).getText();
		Assert.assertTrue(descripcion.equals("DescripcionMofidicada"));
	}
}
