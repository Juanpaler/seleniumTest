import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import PageMetodos.Metodos;

public class WebLineaUnica extends Metodos{
	
	private WebDriver driver;
	

	//@BeforeClass (alwaysRun = true)
	public void apis(){
		driver = setup();
		driver.get("https://resourcesuat.telecom.com.ar/styles/v1/css/tpstyle.css");
		sleep(5000);
		((JavascriptExecutor) driver).executeScript("window.open('https://loginuat.telecom.com.ar/v1/sdk.js');");
		sleep(5000);
		driver.quit();
	}
	
	@BeforeMethod (alwaysRun = true)
	public void before(){
		driver = setup();
	}
	
	//@AfterMethod (alwaysRun = true)
	public void after(){
		driver.findElement(By.id("tpi-user")).click();
		sleep(7000);
		driver.findElement(By.id("tpi-form-logoff")).click();
		sleep(7000);
		driver.close();
	}
	
	@Test (groups = "AutogestionIndividuosWeb")
	public void Comprar_Packs_Compra_de_Packs_MIX(){
		loginPorLinea("MIX");
		irA("packs");
		sleep(15000);
		buscarYClick(driver.findElements(By.cssSelector(".list-group-item.dev-categorias.ng-scope")),"equals","roaming");
		sleep(15000);
		buscarYClick(driver.findElements(By.cssSelector(".list-group-item.ng-scope")),"contains","mundial");
		sleep(15000);
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-lg.btn-responsive.btn-primary")),"equals","confirmar compra");
		sleep(15000);
		Assert.assertTrue(driver.findElement(By.id("principalExito")).isDisplayed());
			
	}
	
	@Test (groups = "AutogestionIndividuosWeb")
	public void Comprar_Packs_Compra_de_Packs_PRE(){
		loginPorLinea("Pre");
		irA("ahorros");
		sleep(15000);
		buscarYClick(driver.findElements(By.cssSelector(".card-body.ng-scope")),"equals","packs destacados");
		sleep(15000);
		buscarYClick(driver.findElements(By.cssSelector(".list-group-item.dev-categorias.ng-scope")),"equals","roaming");
		sleep(15000);
		buscarYClick(driver.findElements(By.cssSelector(".list-group-item.ng-scope")),"contains","mundial");
		sleep(15000);
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-lg.btn-responsive.btn-primary")),"equals","confirmar compra");
		sleep(15000);
		Assert.assertTrue(driver.findElement(By.id("principalExito")).isDisplayed());
			
	}
	
	@Test (groups = "AutogestionIndividuosWeb")
	public void Comprar_Packs_Consultar_Comprobantes_MIX() {
		loginPorLinea("MIX");
		irA("packs");
		sleep(15000);
		driver.findElement(By.id("btnConsultarDesk")).click();
		sleep(7000);
		driver.findElement(By.cssSelector(".item-cell-body.text-center")).findElement(By.cssSelector(".tpicon.font-30.tpicon-descargar")).click();;
		Assert.assertTrue(false);
	}
	
	@Test (groups = "AutogestionIndividuosWeb")
	public void Comprar_Packs_Consultar_Comprobantes_PRE() {
		loginPorLinea("Pre");
		irA("ahorros");
		driver.findElement(By.cssSelector(".card.card-lg.packs.ng-scope")).click();
		sleep(7000);
		driver.findElement(By.id("btnConsultarDesk")).click();
		sleep(7000);
		driver.findElement(By.cssSelector(".item-cell-body.text-center")).findElement(By.cssSelector(".tpicon.font-30.tpicon-descargar"));
		Assert.assertTrue(false);
	}
	
	@Test (groups = "AutogestionIndividuosWeb")
	public void Consumos_Detalles_de_Consumos_MIX() {
		boolean tabla = false, detalles = false;
		loginPorLinea("MIX");
		irA("consumos");
		driver.findElement(By.className("card-footer")).click();
		sleep(5000);
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary.btn-block")), "contains", "consultar");
		if (driver.findElement(By.id("tbodyMoviminetos")).isDisplayed())
			tabla = true;
		driver.findElement(By.id("tbodyMoviminetos")).findElement(By.className("col-detalle")).click();
		sleep(3000);
		if (driver.findElement(By.cssSelector(".card.col-sm-7.col-sm-offset-3.col-lg-offset-3")).isDisplayed())
			detalles = true;
		Assert.assertTrue(tabla && detalles);
	}
	
	@Test (groups = "AutogestionIndividuosWeb")
	public void Consumos_Packs_Activos_MIX(){
		loginPorLinea("MIX");
		irA("consumos");
		sleep(12000);
		buscarYClick(driver.findElements(By.cssSelector(".padding-right-pack.ng-scope")),"equals","packs activos");
		sleep(15000);
		WebElement tabla = driver.findElement(By.cssSelector(".list-store.margin-bottom-50"));
		Assert.assertTrue(tabla.isDisplayed());
	}
	
	@Test (groups = "AutogestionIndividuosWeb")
	public void Consumos_Packs_Activos_PRE(){
		loginPorLinea("Pre");
		irA("consumos");
		sleep(12000);
		buscarYClick(driver.findElements(By.cssSelector(".padding-right-pack.ng-scope")),"equals","packs activos");
		sleep(15000);
		WebElement tabla = driver.findElement(By.cssSelector(".list-store.margin-bottom-50"));
		Assert.assertTrue(tabla.isDisplayed());
	}
	
	@Test (groups = "AutogestionIndividuosWeb")
	public void Mi_Linea_Baja_de_Suscripciones_MIX(){
		loginPorLinea("MIX");
		irA("mi l\u00ednea");
		sleep(12000);
		buscarYClick(driver.findElements(By.cssSelector(".dev-item-menu.list-group-item-full")),"equals","mis suscripciones");
		sleep(20000);
		buscarYClick(driver.findElements(By.id("btnEliminarMobile")),"equals","cancelar suscripci\u00f3n");
		sleep(15000);
		WebElement alert = driver.findElement(By.id("divSinSuscripciones"));
		System.out.println(alert.getText());
		Assert.assertTrue(alert.isDisplayed());
	}
	
	@Test (groups = "AutogestionIndividuosWeb")
	public void Mi_Linea_Baja_de_Suscripciones_PRE(){
		loginPorLinea("Pre");
		irA("mi l\u00ednea");
		sleep(12000);
		buscarYClick(driver.findElements(By.cssSelector(".dev-item-menu.list-group-item-full")),"equals","mis suscripciones");
		sleep(20000);
		buscarYClick(driver.findElements(By.cssSelector("tpicon.tpicon-cerrar2")),"equals","cancelar suscripci\u00f3n");
		sleep(10000);
		buscarYClick(driver.findElements(By.cssSelector(".col-xs-12.col-sm-12.item-cell")),"equals","aceptar");
		sleep(12000);
		WebElement alert = driver.findElement(By.id("divSinSuscripciones"));
		System.out.println(alert.getText());
		Assert.assertTrue(alert.isDisplayed());
	}
	
	@Test (groups = "AutogestionIndividuosWeb")
	public void Mi_Linea_Baja_de_Suscripciones_POS(){
		loginPorLinea("Pos");
		irA("mi l\u00ednea");
		sleep(12000);
		buscarYClick(driver.findElements(By.cssSelector(".dev-item-menu.list-group-item-full")),"equals","mis suscripciones");
		sleep(20000);
		buscarYClick(driver.findElements(By.id("btnEliminarMobile")),"equals","cancelar suscripci\u00f3n");
		sleep(15000);
		WebElement alert = driver.findElement(By.id("divSinSuscripciones"));
		System.out.println(alert.getText());
		Assert.assertTrue(alert.isDisplayed());
	}
	
	@Test (groups = "AutogestionIndividuosWeb")
	public void Consumos_Detalles_de_Consumos_POS() {
		boolean tabla = false;
		loginPorLinea("Pos");
		irA("consumos");
		driver.findElement(By.cssSelector(".card.card-xs.detalle-consumos.dashboard")).click();
		sleep(5000);
		selectByText(driver.findElement(By.id("cmbTipoConsumo")), "Todos");
		selectByText(driver.findElement(By.id("cmbPeriodosFijos")), "M\u00e1s");
		driver.findElement(By.id("txtFechaDesde")).clear();
		driver.findElement(By.id("txtFechaDesde")).sendKeys("01/01/2019");
		driver.findElement(By.id("txtFechaHasta")).sendKeys("16/01/2019");
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary.btn-block")), "contains", "consultar");
		sleep(5000);
		if (driver.findElement(By.cssSelector(".list-store.detalle-consumo")).isDisplayed())
			tabla = true;
		Assert.assertTrue(tabla);
	}
	
	@Test (groups = "AutogestionIndividuosWeb")
	public void Consumos_Detalles_de_Consumos_PRE() {
		boolean tabla = false, detalles = false;
		loginPorLinea("Pre");
		irA("consumos");
		driver.findElement(By.className("card-footer")).click();
		sleep(5000);
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary.btn-block")), "contains", "consultar");
		if (driver.findElement(By.id("tbodyMoviminetos")).isDisplayed())
			tabla = true;
		driver.findElement(By.id("tbodyMoviminetos")).findElement(By.className("col-detalle")).click();
		sleep(3000);
		if (driver.findElement(By.cssSelector(".card.col-sm-7.col-sm-offset-3.col-lg-offset-3")).isDisplayed())
			detalles = true;
		Assert.assertTrue(tabla && detalles);
	}
	
	@Test (groups = "AutogestionIndividuosWeb")
	public void Recargas_Gestiones_y_Consultas_Recargar_Ahora_MIX() {
		loginPorLinea("MIX");
		irA("recargas");
		driver.findElement(By.id("btnRecargaTarjeta")).click();
		sleep(5000);
		Assert.assertTrue(driver.findElement(By.id("divPin")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("divCaptcha")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("btnRecargaQuemaPin")).isDisplayed());
		driver.findElement(By.id("lnkFlechaIzqQP")).click();
		sleep(3000);
		driver.findElement(By.id("btRecargas20")).click();
		sleep(5000);
		Assert.assertTrue(driver.findElement(By.id("divNuevaTC")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("divIngresoVto")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("divIngresoCodSeguridad")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector(".card.card-lg.recarga-tarjcred")).findElement(By.id("divCaptcha")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector(".card.card-lg.recarga-tarjcred")).findElement(By.id("btnContinuarTC")).isDisplayed());
		driver.findElement(By.id("lnkFlechaIzqQP")).click();
		sleep(3000);
		driver.findElement(By.id("btnRecargaPuntosClub")).click();
		sleep(5000);
		driver.findElement(By.className("panel-heading")).findElement(By.className("collapsed")).click();
		
	}
}