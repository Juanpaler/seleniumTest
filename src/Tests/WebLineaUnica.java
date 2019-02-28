package Tests;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import PageMetodos.Metodos;

public class WebLineaUnica extends Metodos{
	
	private WebDriver driver;
	private WebDriver driverf;
	private WebDriver driveri;
	String imagen;
			

	//@BeforeClass (alwaysRun = true)
	public void apis(){
		driver = setup();
		driver.get("https://resourcesuat.telecom.com.ar/styles/v1/css/tpstyle.css");
		sleep(5000);
		((JavascriptExecutor) driver).executeScript("window.open('https://loginuat.telecom.com.ar/v1/sdk.js');");
		sleep(5000);
		driverf.quit();
	}
	
	@BeforeMethod (alwaysRun = true)
	public void before(){
		//Chrome
		driver = setup();
		
		//Firefox
		//driverf = setup2();
		
		//InternetExplorer
		//driveri = setup3();
		
	}
	
	//@AfterMethod (alwaysRun = true)
	public void after(){
		tomarCaptura(driver,imagen);
		try {
			driver.findElement(By.id("tpi-user")).click();
			sleep(7000);
			driver.findElement(By.id("tpi-form-logoff")).click();
			sleep(7000);
			driver.close();
		}catch(Exception ex1){	driver.close();
		}
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","packs"}, dataProvider="MIX")
	public void Comprar_Packs_Compra_de_Packs_MIX(String sLinea){
		imagen = "Comprar_Packs_Compra_de_Packs_MIX";
		loginPorLinea(sLinea);
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
	
	@Test (groups ={ "AutogestionIndividuosWeb","packs"}, dataProvider="PRE")
	public void Comprar_Packs_Compra_de_Packs_PRE(String sLinea){
		imagen = "Comprar_Packs_Compra_de_Packs_PRE";
		loginPorLinea(sLinea);
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
	
	@Test (groups ={ "AutogestionIndividuosWeb","packs"}, dataProvider="MIX")
	public void Comprar_Packs_Consultar_Comprobantes_MIX(String sLinea) {
		imagen = "Comprar_Packs_Consultar_Comprobantes_MIX";
		loginPorLinea(sLinea);
		irA("packs");
		sleep(15000);
		driver.findElement(By.id("btnConsultarDesk")).click();
		sleep(7000);
		driver.findElement(By.cssSelector(".item-cell-body.text-center")).findElement(By.cssSelector(".tpicon.font-30.tpicon-descargar")).click();;
		Assert.assertTrue(false);
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","packs"}, dataProvider="PRE")
	public void Comprar_Packs_Consultar_Comprobantes_PRE(String sLinea) {
		imagen = "Comprar_Packs_Consultar_Comprobantes_PRE";
		loginPorLinea(sLinea);
		irA("ahorros");
		driver.findElement(By.cssSelector(".card.card-lg.packs.ng-scope")).click();
		sleep(7000);
		driver.findElement(By.id("btnConsultarDesk")).click();
		sleep(7000);
		driver.findElement(By.cssSelector(".item-cell-body.text-center")).findElement(By.cssSelector(".tpicon.font-30.tpicon-descargar"));
		Assert.assertTrue(false);
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","consumos"}, dataProvider="MIX")
	public void Consumos_Detalles_de_Consumos_MIX(String sLinea) {
		imagen = "Consumos_Detalles_de_Consumos_MIX";
		boolean tabla = false, detalles = false;
		loginPorLinea(sLinea);
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
	
	@Test (groups ={ "AutogestionIndividuosWeb","consumos"}, dataProvider="MIX")
	public void Consumos_Packs_Activos_MIX(String sLinea){
		imagen = "Consumos_Packs_Activos_MIX";
		loginPorLinea(sLinea);
		irA("consumos");
		sleep(12000);
		buscarYClick(driver.findElements(By.cssSelector(".padding-right-pack.ng-scope")),"equals","packs activos");
		sleep(15000);
		WebElement tabla = driver.findElement(By.cssSelector(".list-store.margin-bottom-50"));
		Assert.assertTrue(tabla.isDisplayed());
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","consumos"}, dataProvider="PRE")
	public void Consumos_Packs_Activos_PRE(String sLinea){
		imagen = "Consumos_Packs_Activos_PRE";
		loginPorLinea(sLinea);
		irA("consumos");
		sleep(12000);
		buscarYClick(driver.findElements(By.cssSelector(".padding-right-pack.ng-scope")),"equals","packs activos");
		sleep(15000);
		WebElement tabla = driver.findElement(By.cssSelector(".list-store.margin-bottom-50"));
		Assert.assertTrue(tabla.isDisplayed());
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","mi linea"}, dataProvider="MIX")
	public void Mi_Linea_Baja_de_Suscripciones_MIX(String sLinea){
		imagen = "Mi_Linea_Baja_de_Suscripciones_MIX";
		loginPorLinea(sLinea);
		irA("mi l\u00ednea");
		sleep(12000);
		buscarYClick(driver.findElements(By.cssSelector(".dev-item-menu.list-group-item-full")),"equals","mis suscripciones");
		sleep(12000);
		driver.findElement(By.id("btnEliminar")).click();
		sleep(7000);
		driver.findElement(By.id("btnAceptar")).click();
		sleep(12000);
		WebElement alert = driver.findElement(By.id("divSinSuscripciones"));
		System.out.println(alert.getText());
		Assert.assertTrue(alert.isDisplayed());
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","mi linea"}, dataProvider="PRE")
	public void Mi_Linea_Baja_de_Suscripciones_PRE(String sLinea){
		imagen = "Mi_Linea_Baja_de_Suscripciones_PRE";
		loginPorLinea(sLinea);
		irA("mi l\u00ednea");
		sleep(12000);
		buscarYClick(driver.findElements(By.cssSelector(".dev-item-menu.list-group-item-full")),"equals","mis suscripciones");
		sleep(12000);
		driver.findElement(By.id("btnEliminar")).click();
		sleep(7000);
		driver.findElement(By.id("btnAceptar")).click();
		sleep(12000);
		WebElement alert = driver.findElement(By.id("divSinSuscripciones"));
		System.out.println(alert.getText());
		Assert.assertTrue(alert.isDisplayed());
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","mi linea"}, dataProvider="POS")
	public void Mi_Linea_Baja_de_Suscripciones_POS(String sLinea){
		imagen = "Mi_Linea_Baja_de_Suscripciones_POS";
		loginPorLinea(sLinea);
		irA("mi l\u00ednea");
		sleep(12000);
		buscarYClick(driver.findElements(By.cssSelector(".dev-item-menu.list-group-item-full")),"equals","mis suscripciones");
		sleep(12000);
		driver.findElement(By.id("btnEliminar")).click();
		sleep(7000);
		driver.findElement(By.id("btnAceptar")).click();
		sleep(12000);
		WebElement alert = driver.findElement(By.id("divSinSuscripciones"));
		System.out.println(alert.getText());
		Assert.assertTrue(alert.isDisplayed());
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","consumos"}, dataProvider="POS")
	public void Consumos_Detalles_de_Consumos_POS(String sLinea) {
		imagen = "Consumos_Detalles_de_Consumos_POS";
		boolean tabla = false;
		loginPorLinea(sLinea);
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
	
	@Test (groups ={ "AutogestionIndividuosWeb","consumos"}, dataProvider="PRE")
	public void Consumos_Detalles_de_Consumos_PRE(String sLinea) {
		imagen = "Consumos_Detalles_de_Consumos_PRE";
		boolean tabla = false, detalles = false;
		loginPorLinea(sLinea);
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
	
	@Test (groups ={ "AutogestionIndividuosWeb","recargas"}, dataProvider="MIX")
	public void Recargas_Gestiones_y_Consultas_Recargar_Ahora_MIX(String sLinea) {
		imagen = "Recargas_Gestiones_y_Consultas_Recargar_Ahora_MIX";
		loginPorLinea(sLinea);
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
		driver.findElement(By.id("lnkFlechaIzqTC")).click();
		sleep(3000);
		driver.findElement(By.id("btnRecargaPuntosClub")).click();
		sleep(5000);
		driver.findElements(By.className("panel-heading")).get(2).click();
		sleep(3000);
		driver.findElement(By.cssSelector(".panel-body.list-group")).findElement(By.tagName("div")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector(".btn.btn-lg.btn-primary.pull-right.disabled")).isDisplayed());
		for (int i=0; i<3; i++) {
			for (WebElement x : driver.findElements(By.cssSelector(".card.card-lg.recarga-puntosclub")))
				if (x.getText().toLowerCase().contains("puntos club disponibles")) {
					x.findElement(By.cssSelector(".tpicon.tpicon-flechaizquierda")).click();
				i++;
			}
		}
		driver.findElement(By.id("btnRecargaOtrosMedios")).click();
		sleep(7000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".aivohc-search-container.mat-card")).isDisplayed());
		driver.navigate().back();
		sleep(5000);
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","recargas"}, dataProvider="PRE")
	public void Recargas_Gestiones_y_Consultas_Recargar_Ahora_PRE(String sLinea) {
		imagen = "Recargas_Gestiones_y_Consultas_Recargar_Ahora_PRE";
		loginPorLinea(sLinea);
		irA("recargas");
		sleep(10000);
		driver.findElement(By.id("btnRecargaTarjeta")).click();
		sleep(5000);
		Assert.assertTrue(driver.findElement(By.id("divPin")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("divCaptcha")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("btnRecargaQuemaPin")).isDisplayed());
		obligarclick(driver.findElement(By.id("lnkFlechaIzqQP")));//.findElement(By.cssSelector(".tpicon.tpicon-flechaizquierda")).click();
		sleep(3000);
		driver.findElement(By.id("btRecargas20")).click();
		sleep(5000);
		Assert.assertTrue(driver.findElement(By.id("divNuevaTC")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("divIngresoVto")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("divIngresoCodSeguridad")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector(".card.card-lg.recarga-tarjcred")).findElement(By.id("divCaptcha")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector(".card.card-lg.recarga-tarjcred")).findElement(By.id("btnContinuarTC")).isDisplayed());
		driver.findElement(By.id("lnkFlechaIzqTC")).click();
		sleep(3000);
		driver.findElement(By.id("btnRecargaPuntosClub")).click();
		sleep(5000);
		driver.findElements(By.className("panel-heading")).get(2).click();
		sleep(3000);
		driver.findElement(By.cssSelector(".panel-body.list-group")).findElement(By.tagName("div")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector(".btn.btn-lg.btn-primary.pull-right.disabled")).isDisplayed());
		for (int i=0; i<3; i++) {
			for (WebElement x : driver.findElements(By.cssSelector(".card.card-lg.recarga-puntosclub")))
				if (x.getText().toLowerCase().contains("puntos club disponibles")) {
					x.findElement(By.cssSelector(".tpicon.tpicon-flechaizquierda")).click();
				i++;
			}
		}
		driver.findElement(By.id("btnRecargaOtrosMedios")).click();
		sleep(7000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".aivohc-search-container.mat-card")).isDisplayed());
		driver.navigate().back();
		sleep(5000);
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","recargas"}, dataProvider="MIX")
	public void Recargas_Gestiones_y_Consultas_Ultimas_Recargas_MIX(String sLinea) {
		imagen = "Recargas_Gestiones_y_Consultas_Ultimas_Recargas_MIX";
		loginPorLinea(sLinea);
		irA("recargas");
		sleep(5000);
		driver.findElement(By.cssSelector(".card.card-lg.padding-bottom-0")).click();
		sleep(5000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".list-store.detalle-consumo")).isDisplayed());
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","recargas"}, dataProvider="PRE")
	public void Recargas_Gestiones_y_Consultas_Ultimas_Recargas_PRE(String sLinea) {
		imagen = "Recargas_Gestiones_y_Consultas_Ultimas_Recargas_PRE";
		loginPorLinea(sLinea);
		irA("recargas");
		sleep(5000);
		driver.findElement(By.cssSelector(".card.card-lg.padding-bottom-0")).click();
		sleep(5000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".list-store.detalle-consumo")).isDisplayed());
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","mis datos"}, dataProvider="MIX")
	public void Mis_Datos_Mi_Perfil_Cambiar_Contrasenia_MIX(String sLinea) {
		imagen = "Mis_Datos_Mi_Perfil_Cambiar_Contrasenia_MIX";
		loginPorLinea(sLinea);
		Assert.assertTrue(cambiarClave("1357"));
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","mis datos"}, dataProvider="PRE")
	public void Mis_Datos_Mi_Perfil_Cambiar_Contrasenia_PRE(String sLinea) {
		imagen = "Mis_Datos_Mi_Perfil_Cambiar_Contrasena_PRE";
		loginPorLinea(sLinea);
		Assert.assertTrue(cambiarClave("1357"));
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","mis datos"}, dataProvider="POS")
	public void Mis_Datos_Mi_Perfil_Cambiar_Contrasenia_POS(String sLinea) {
		imagen = "Mis_Datos_Mi_Perfil_Cambiar_Contrasenia_POS";
		loginPorLinea(sLinea);
		Assert.assertTrue(cambiarClave("1357"));
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","facturacion"}, dataProvider="MIX")
	public void Facturacion_Gestiones_y_Consultas_Distribucion_de_Factura_MIX(String sLinea) {
		imagen = "Facturacion_Gestiones_y_Consultas_Distribucion_de_Factura_MIX";
		loginPorLinea(sLinea);
		irA("facturaci\u00f3n");
		try {
			for (WebElement x : driver.findElement(By.id("lista-menu-derecha")).findElements(By.tagName("li"))) {
				if (x.getText().toLowerCase().contains("cu\u00e1ndo llega mi factura"))
					x.click();
			}
		} catch(Exception e) {}
		sleep(7000);
		WebElement ciclo = driver.findElement(By.className("cuando-llega-tu-factura")).findElement(By.className("row")).findElements(By.tagName("div")).get(1).findElements(By.tagName("div")).get(1).findElement(By.tagName("b"));
		WebElement dist = driver.findElement(By.className("cuando-llega-tu-factura")).findElements(By.className("row")).get(1).findElements(By.tagName("div")).get(1).findElements(By.tagName("div")).get(1).findElement(By.tagName("b"));
		WebElement venc = driver.findElement(By.className("cuando-llega-tu-factura")).findElements(By.className("row")).get(2).findElements(By.tagName("div")).get(1).findElements(By.tagName("div")).get(1).findElement(By.tagName("b"));
		Assert.assertTrue(ciclo.getText().matches("^\\d{2}$"));
		Assert.assertTrue(dist.getText().matches("^\\d{2}/\\d{2}/\\d{4}$"));
		Assert.assertTrue(venc.getText().matches("^\\d{2}/\\d{2}/\\d{4}$"));
		
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","facturacion"}, dataProvider="POS")
	public void Facturacion_Gestiones_y_Consultas_Distribucion_de_Factura_POS(String sLinea) {
		imagen = "Facturacion_Gestiones_y_Consultas_Distribucion_de_Factura_POS";
		loginPorLinea(sLinea);
		irA("facturaci\u00f3n");
		try {
			for (WebElement x : driver.findElement(By.id("lista-menu-derecha")).findElements(By.tagName("li"))) {
				if (x.getText().toLowerCase().contains("cu\u00e1ndo llega mi factura"))
					x.click();
			}
		} catch(Exception e) {}
		sleep(7000);
		WebElement ciclo = driver.findElement(By.className("cuando-llega-tu-factura")).findElement(By.className("row")).findElements(By.tagName("div")).get(1).findElements(By.tagName("div")).get(1).findElement(By.tagName("b"));
		WebElement dist = driver.findElement(By.className("cuando-llega-tu-factura")).findElements(By.className("row")).get(1).findElements(By.tagName("div")).get(1).findElements(By.tagName("div")).get(1).findElement(By.tagName("b"));
		WebElement venc = driver.findElement(By.className("cuando-llega-tu-factura")).findElements(By.className("row")).get(2).findElements(By.tagName("div")).get(1).findElements(By.tagName("div")).get(1).findElement(By.tagName("b"));
		Assert.assertTrue(ciclo.getText().matches("^\\d{2}$"));
		Assert.assertTrue(dist.getText().matches("^\\d{2}/\\d{2}/\\d{4}$"));
		Assert.assertTrue(venc.getText().matches("^\\d{2}/\\d{2}/\\d{4}$"));
		
	}
	@Test (groups ={ "AutogestionIndividuosWeb","mi linea"}, dataProvider="PRE")
	public void Mi_Linea_Claves_PIN_y_PUK_PRE(String sLinea){
		imagen = "Mi_Linea_Claves_PIN_y_PUK_PRE";
		loginPorLinea(sLinea);
		irA("mi l\u00ednea");
		sleep(12000);
		buscarYClick(driver.findElements(By.cssSelector(".dev-item-menu.list-group-item")),"equals","claves pin y puk");
		sleep(12000);
		WebElement pin = driver.findElements(By.cssSelector(".well.text-center")).get(0);
		WebElement puk = driver.findElements(By.cssSelector(".well.text-center")).get(1);
		System.out.println(pin.findElement(By.tagName("h1")).getText());
		System.out.println(puk.findElement(By.tagName("h1")).getText());
		Assert.assertTrue(pin.getText().toLowerCase().contains("tu pin es:") && puk.getText().toLowerCase().contains("tu puk es:"));
		Assert.assertTrue(pin.isDisplayed());
		Assert.assertTrue(puk.isDisplayed());
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","mi linea"}, dataProvider="POS")
	public void Mi_Linea_Claves_PIN_y_PUK_POS(String sLinea){
		imagen = "Mi_Linea_Claves_PIN_y_PUK_POS";
		loginPorLinea(sLinea);
		irA("mi l\u00ednea");
		sleep(12000);
		buscarYClick(driver.findElements(By.cssSelector(".dev-item-menu.list-group-item")),"equals","claves pin y puk");
		sleep(12000);
		WebElement pin = driver.findElements(By.cssSelector(".well.text-center")).get(0);
		WebElement puk = driver.findElements(By.cssSelector(".well.text-center")).get(1);
		System.out.println(pin.findElement(By.tagName("h1")).getText());
		System.out.println(puk.findElement(By.tagName("h1")).getText());
		Assert.assertTrue(pin.getText().toLowerCase().contains("tu pin es:") && puk.getText().toLowerCase().contains("tu puk es:"));
		Assert.assertTrue(pin.isDisplayed());
		Assert.assertTrue(puk.isDisplayed());
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","mi linea"}, dataProvider="MIX")
	public void Mi_Linea_Claves_PIN_y_PUK_MIX(String sLinea){
		imagen = "Mi_Linea_Claves_PIN_y_PUK_MIX";
		loginPorLinea(sLinea);
		irA("mi l\u00ednea");
		sleep(12000);
		buscarYClick(driver.findElements(By.cssSelector(".dev-item-menu.list-group-item")),"equals","claves pin y puk");
		sleep(12000);
		WebElement pin = driver.findElements(By.cssSelector(".well.text-center")).get(0);
		WebElement puk = driver.findElements(By.cssSelector(".well.text-center")).get(1);
		System.out.println(pin.findElement(By.tagName("h1")).getText());
		System.out.println(puk.findElement(By.tagName("h1")).getText());
		Assert.assertTrue(pin.getText().toLowerCase().contains("tu pin es:") && puk.getText().toLowerCase().contains("tu puk es:"));
		Assert.assertTrue(pin.isDisplayed());
		Assert.assertTrue(puk.isDisplayed());
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","recargas"}, dataProvider="MIX")
	public void Recargas_Recarga_SOS_MIX(String sLinea){
		imagen = "Recargas_Recarga_SOS_MIX";
		loginPorLinea(sLinea);
		irA("recargas");
		sleep(10000);
		buscarYClick(driver.findElements(By.cssSelector(".list-group-item-full.padding-top-0")),"contains","recarga s.o.s.");
		sleep(5000);
		buscarYClick(driver.findElements(By.cssSelector(".pull-right.btn.btn-lg.btn-responsive.btn-primary")),"contains","recarg\u00e1 ahora");
		sleep(10000);
		driver.findElement(By.id("lnkAtrasModulo")).click();
		sleep(10000);
		irA("recargas");
		buscarYClick(driver.findElements(By.cssSelector(".list-group-item-full.padding-top-0")),"contains","recarga s.o.s.");
		sleep(5000);
		buscarYClick(driver.findElements(By.cssSelector(".pull-right.btn.btn-lg.btn-responsive.btn-primary")),"contains","recarg\u00e1 ahora");
		sleep(8000);
		Assert.assertTrue(driver.findElement(By.id("lblErrorGlobal")).getText().toLowerCase().equals("la recarga s.o.s no pudo completarse porque ten\u00e9s pendiente el pago de la anterior."));
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","recargas"}, dataProvider="PRE")
	public void Recargas_Recarga_SOS_PRE(String sLinea){
		imagen = "Recargas_Recarga_SOS_PRE";
		loginPorLinea(sLinea);
		irA("recargas");
		sleep(10000);
		buscarYClick(driver.findElements(By.cssSelector(".list-group-item-full.padding-top-0")),"contains","recarga s.o.s.");
		sleep(5000);
		buscarYClick(driver.findElements(By.cssSelector(".pull-right.btn.btn-lg.btn-responsive.btn-primary")),"contains","recarg\u00e1 ahora");
		sleep(8000);
		driver.findElement(By.id("lnkAtrasModulo")).click();
		sleep(10000);
		irA("recargas");
		buscarYClick(driver.findElements(By.cssSelector(".list-group-item-full.padding-top-0")),"contains","recarga s.o.s.");
		sleep(5000);
		buscarYClick(driver.findElements(By.cssSelector(".pull-right.btn.btn-lg.btn-responsive.btn-primary")),"contains","recarg\u00e1 ahora");
		sleep(8000);
		Assert.assertTrue(driver.findElement(By.id("lblErrorGlobal")).getText().toLowerCase().equals("la recarga s.o.s no pudo completarse porque ten\u00e9s pendiente el pago de la anterior."));
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","mi linea"}, dataProvider="MIX")
	public void Mi_Linea_MI_Plan_MIX(String sLinea){
		imagen = "Mi_Linea_MI_Plan_MIX";
		loginPorLinea(sLinea);
		irA("mi l\u00ednea");
		sleep(10000);
		buscarYClick(driver.findElements(By.cssSelector(".dev-item-menu.list-group-item-full")),"equals","mi plan");
		sleep(8000);
		buscarYClick(driver.findElements(By.cssSelector(".col-xs-12.col-sm-12.col-md-1.item-cell.item-cell-last")),"contains","ver detalle");
		Assert.assertTrue(driver.findElement(By.id("section-ui-view")).isDisplayed());
		driver.findElements(By.cssSelector(".glyphicon.glyphicon-chevron-down")).get(6).click();
		sleep(3000);
		Assert.assertTrue(driver.findElement(By.id("legalDP")).findElement(By.tagName("div")).isDisplayed());
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","mi linea"}, dataProvider="POS")
	public void Mi_Linea_MI_Plan_POS(String sLinea){
		imagen = "Mi_Linea_MI_Plan_POS";
		loginPorLinea(sLinea);
		irA("mi l\u00ednea");
		sleep(10000);
		buscarYClick(driver.findElements(By.cssSelector(".dev-item-menu.list-group-item-full")),"equals","mi plan");
		sleep(8000);
		buscarYClick(driver.findElements(By.cssSelector(".col-xs-12.col-sm-12.col-md-1.item-cell.item-cell-last")),"contains","ver detalle");
		Assert.assertTrue(driver.findElement(By.id("section-ui-view")).isDisplayed());
		driver.findElements(By.cssSelector(".glyphicon.glyphicon-chevron-down")).get(6).click();
		sleep(3000);
		Assert.assertTrue(driver.findElement(By.id("legalDP")).findElement(By.tagName("div")).isDisplayed());
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","mi linea"}, dataProvider="POS")
	public void Mi_Linea_MI_Plan_POS_Plan_Black(String sLinea){
		imagen = "Mi_Linea_MI_Plan_POS_Plan_Black";
		loginPorLinea(sLinea);
		irA("mi l\u00ednea");
		sleep(10000);
		buscarYClick(driver.findElements(By.cssSelector(".dev-item-menu.list-group-item")),"equals","reserva de turno");
		sleep(8000);
		completarDatos("Compra de Equipo/L\u00ednea","Buenos Aires","San Justo","Dr. Ignacio Arieta 3169","111111111","a@a.com","20/02/2019");
		Assert.assertTrue(driver.findElement(By.id("lblMensajeExito")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("lblMensajeExito")).getText().contains("Reservaste con \u00e9xito el turno para asistir a una oficina comercial"));
		sleep(3000);
		driver.findElement(By.id("btnCancelar")).click();
		sleep(2500);
		driver.findElement(By.id("btnSi")).click();
		sleep(5000);
		Assert.assertTrue(driver.findElement(By.id("btnPedirOtroTurno")).isDisplayed());
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","facturacion"}, dataProvider="MIX")
	public void Facturacion_Ver_Formas_de_Pago_MIX(String sLinea){
		imagen="Facturacion_Ver_Formas_de_Pago_MIX";
		loginPorLinea(sLinea);
		irA("facturaci\u00f3n");
		buscarYClick(driver.findElements(By.cssSelector(".dev-item-menu.list-group-item-full")),"equals","formas y lugares de pago");
		sleep(15000);
	    ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs2.get(1));
	    Assert.assertTrue(driver.findElement(By.id("mat-input-0")).isDisplayed());
	    driver.close();
	    driver.switchTo().window(tabs2.get(0));
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","facturacion"}, dataProvider="POS")
	public void Facturacion_Ver_Formas_de_Pago_POS(String sLinea){
		imagen="Facturacion_Ver_Formas_de_Pago_POS";
		loginPorLinea(sLinea);
		irA("facturaci\u00f3n");
		buscarYClick(driver.findElements(By.cssSelector(".dev-item-menu.list-group-item-full")),"equals","formas y lugares de pago");
		sleep(15000);
	    ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs2.get(1));
	    Assert.assertTrue(driver.findElement(By.id("mat-input-0")).isDisplayed());
	    driver.close();
	    driver.switchTo().window(tabs2.get(0));
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","consumos"}, dataProvider="MIX")
	public void Mis_Consumos_Detalle_de_credito_MIX(String sLinea){
		imagen = "Mis_Consumos_Detalle_de_credito_MIX";
		loginPorLinea(sLinea);
		irA("consumos");
		boolean check = true;
		List<WebElement> cardconsu = driver.findElements(By.cssSelector(".card-flipper.card-flipper-md"));
			for(WebElement c : cardconsu){
				if(c.getText().toLowerCase().contains("informaci\u00f3n no disponible")){
					System.out.println(c.getText());
					check = false;
				}
			}
		Assert.assertTrue(check);
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","consumos"}, dataProvider="PRE")
	public void Mis_Consumos_Detalle_de_credito_PRE(String sLinea){
		imagen = "Mis_Consumos_Detalle_de_credito_PRE";
		loginPorLinea(sLinea);
		irA("consumos");
		boolean check = true;
		List<WebElement> cardconsu = driver.findElements(By.cssSelector(".card-flipper.card-flipper-md"));
			for(WebElement c : cardconsu){
				if(c.getText().toLowerCase().contains("informaci\u00f3n no disponible")){
					System.out.println(c.getText());
					check = false;
				}
			}
		Assert.assertTrue(check);
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","consumos"}, dataProvider="MIX", dependsOnMethods="Recargas_Gestiones_y_Consultas_Ultimas_Recargas_MIX")
	public void Mis_Consumos_Detalle_de_credito_Historial_de_recargas_MIX(String sLinea){
		imagen = "Mis_Consumos_Detalle_de_credito_MIX";
		loginPorLinea(sLinea);
		Assert.assertTrue(true);
		sleep(3000);
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","consumos"}, dataProvider="PRE", dependsOnMethods="Recargas_Gestiones_y_Consultas_Ultimas_Recargas_PRE")
	public void Mis_Consumos_Detalle_de_credito_Historial_de_recargas_PRE(String sLinea){
		imagen = "Mis_Consumos_Detalle_de_credito_PRE";
		loginPorLinea(sLinea);
		Assert.assertTrue(true);
		sleep(3000);
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","consumos"}, dataProvider="MIX")
	public void Mis_Consumos_Compra_Internet_MIX(String sLinea){
		imagen = "Mis_Consumos_Compra_Internet_MIX";
		loginPorLinea(sLinea);
		irA("consumos");
		boolean check = true;
		List<WebElement> inetconsu = driver.findElements(By.cssSelector(".card.card-md.card-front.ng-scope"));
			for(WebElement i : inetconsu){
				if(i.getText().toLowerCase().contains("se produjo un error, int\u00e9ntalo de nuevo m\u00e1s tarde.")){
					System.out.println(i.getText());
					check = false;
				}
			}
		Assert.assertTrue(check);
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","consumos"}, dataProvider="PRE")
	public void Mis_Consumos_Compra_Internet_PRE(String sLinea){
		imagen = "Mis_Consumos_Compra_Internet_PRE";
		loginPorLinea(sLinea);
		irA("consumos");
		boolean check = true;
		List<WebElement> inetconsu = driver.findElements(By.cssSelector(".card.card-md.card-front.ng-scope"));
			for(WebElement i : inetconsu){
				if(i.getText().toLowerCase().contains("se produjo un error, int\u00e9ntalo de nuevo m\u00e1s tarde.")){
					System.out.println(i.getText());
					check = false;
				}
			}
		Assert.assertTrue(check);
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","consumos"}, dataProvider="MIX")
	public void Mis_Consumos_Internet_de_mi_Plan_MIX(String sLinea){
		imagen = "Mis_Consumos_Internet_de_mi_Plan_MIX";
		loginPorLinea(sLinea);
		irA("consumos");
		sleep(8000);
		driver.findElement(By.id("divCardFlipInternet")).findElement(By.cssSelector(".list-group-item-full.arrow-ic.adicional")).click();
		sleep(8000);
		boolean a = false;
		List <WebElement> inetmiplan =driver.findElements(By.cssSelector(".card.card-md.ng-scope.ng-isolate-scope"));
			for(WebElement i : inetmiplan){
				if(i.getText().toLowerCase().contains("internet de mi plan")){
					i.isDisplayed();
					a = true;
				}
			}
		Assert.assertTrue(a);	
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","consumos"}, dataProvider="MIX")
	public void Mis_Consumos_Internet_Adicional_MIX(String sLinea){
		imagen = "Mis_Consumos_Internet_Adicional_MIX";
		loginPorLinea(sLinea);
		irA("consumos");
		sleep(8000);
		driver.findElement(By.id("divCardFlipInternet")).findElement(By.cssSelector(".list-group-item-full.arrow-ic.adicional")).click();
		sleep(8000);
		boolean a = false;
		List <WebElement> inetmiplan =driver.findElements(By.cssSelector(".card.ng-scope"));
			for(WebElement i : inetmiplan){
				if(i.getText().toLowerCase().contains("internet adicional")){
					i.isDisplayed();
					a = true;
				}
			}
		Assert.assertTrue(a);	
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","consumos"}, dataProvider="PRE")
	public void Mis_Consumos_Internet_Adicional_PRE(String sLinea){
		imagen = "Mis_Consumos_Internet_Adicional_PRE";
		loginPorLinea(sLinea);
		irA("consumos");
		sleep(8000);
		driver.findElement(By.id("divCardFlipInternet")).findElement(By.cssSelector(".list-group-item-full.arrow-ic")).click();
		sleep(8000);
		boolean a = false;
		List <WebElement> inetmiplan =driver.findElements(By.cssSelector(".card.ng-scope"));
			for(WebElement i : inetmiplan){
				if(i.getText().toLowerCase().contains("internet de mi plan")){
					i.isDisplayed();
					a = true;
				}
			}
		Assert.assertTrue(a);	
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","consumos"}, dataProvider="MIX")
	public void Mis_Consumos_SMS_Disponibles_MIX(String sLinea){
		imagen = "Mis_Consumos_SMS_Disponibles_MIX";
		loginPorLinea(sLinea);
		irA("consumos");
		sleep(8000);
		boolean rompe = true;
		List <WebElement> sms = driver.findElements(By.cssSelector(".card.card-xs.card-min.ng-scope"));
			for(WebElement s : sms){
				s.getText().toLowerCase().contains("sms");
				if(s.findElement(By.tagName("span")).getText().equals("-")){
					rompe = false;
				}
			}
		Assert.assertTrue(rompe);
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","mis datos"}, dataProvider="MIX")
	public void Mis_Datos_Mi_Perfil_Cambio_de_Mail(String sLinea) {
		imagen = "Mis_Datos_Mi_Perfil_Cambio_de_Mail";
		loginPorLinea(sLinea);
		driver.findElement(By.id("tpi-user")).click();
		sleep(3000);
		driver.findElement(By.cssSelector(".tpi-user-link.tpi-fix-micuenta")).click();
		sleep(15000);
		driver.findElement(By.id("lnkModificaMail")).click();
		sleep(15000);
		driver.findElement(By.cssSelector(".tpicon.tpicon-editar.form-control-feedback")).click();
		sleep(1000);
		driver.findElement(By.id("inputEmail")).sendKeys("alejandromza@gmail.com");
		driver.findElement(By.id("btnGuardarMail")).click();
		sleep(15000);
		//Se necesita el acceso a la base para obtener el id de confirmacion 
		Assert.assertTrue(false);
		
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","facturacion"}, dataProvider="MIX")
	public void Facturacion_Facturacion_Digital_Suscripcion_a_Factura_Online_MIX(String sLinea) {
		imagen = "Facturacion_Facturacion_Digital_Suscripcion_a_Factura_Online_MIX";
		loginPorLinea(sLinea);
		irA("facturaci\\u00f3n");
		driver.findElement(By.className("fo-text")).click();
		sleep(15000);
		driver.findElement(By.id("inputEmail")).sendKeys("alejandromza@gmail.com");
		//driver.findElement(By.id("btn_Adherirme")).click();
		//Se necesita el acceso a la base para obtener el id de confirmacion del mail 
		Assert.assertTrue(false);
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","facturacion"}, dataProvider="POS")
	public void Facturacion_Facturacion_Digital_Suscripcion_a_Factura_Online_POS(String sLinea) {
		imagen = "Facturacion_Facturacion_Digital_Suscripcion_a_Factura_Online_MIX";
		loginPorLinea(sLinea);
		irA("facturaci\\u00f3n");
		driver.findElement(By.className("fo-text")).click();
		sleep(15000);
		driver.findElement(By.id("inputEmail")).sendKeys("alejandromza@gmail.com");
		//driver.findElement(By.id("btn_Adherirme")).click();
		//Se necesita el acceso a la base para obtener el id de confirmacion del mail 
		Assert.assertTrue(false);
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","facturacion"}, dataProvider="MIX", dependsOnMethods="Facturacion_Facturacion_Digital_Suscripcion_a_Factura_Online_MIX")
	public void Facturacion_Facturacion_Digital_Desuscripcion_a_Factura_Online_MIX(String sLinea){
		imagen = "Facturacion_Facturacion_Digital_Desuscripcion_a_Factura_Online_MIX";
		loginPorLinea(sLinea);
	    //SIN TERMINAR - NO SE PUEDE COMPLETAR HASTA QUE FUNCIONE LA ADHESION
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","facturacion"}, dataProvider="POS", dependsOnMethods="Facturacion_Facturacion_Digital_Suscripcion_a_Factura_Online_POS")
	public void Facturacion_Facturacion_Digital_Desuscripcion_a_Factura_Online_POS(String sLinea){
		imagen = "Facturacion_Facturacion_Digital_Desuscripcion_a_Factura_Online_POS";
		loginPorLinea(sLinea);
	    //SIN TERMINAR - NO SE PUEDE COMPLETAR HASTA QUE FUNCIONE LA ADHESION
	}
	
	
	@Test (groups ={ "AutogestionIndividuosWeb","consumos"}, dataProvider="PRE")
	public void Mis_Consumos_SMS_Disponibles_PRE(String sLinea){
		imagen = "Mis_Consumos_SMS_Disponibles_PRE";
		loginPorLinea(sLinea);
		irA("consumos");
		sleep(8000);
		boolean rompe = true;
		List <WebElement> sms = driver.findElements(By.cssSelector(".card.card-xs.card-min.ng-scope"));
			for(WebElement s : sms){
				s.getText().toLowerCase().contains("sms");
				if(s.findElement(By.tagName("span")).getText().equals("-")){
					rompe = false;
				}
			}
		Assert.assertTrue(rompe);
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","consumos"}, dataProvider="MIX")
	public void Mis_Consumos_Minutos_Disponibles_MIX(String sLinea){
		imagen = "Mis_Consumos_SMS_Disponibles_MIX";
		loginPorLinea(sLinea);
		irA("consumos");
		sleep(8000);
		boolean rompe = true;
		List <WebElement> sms = driver.findElements(By.cssSelector(".card.card-xs.card-min.ng-scope"));
			for(WebElement s : sms){
				System.out.println(s.getText());
				s.getText().toLowerCase().contains("sms");
				if(s.findElement(By.tagName("span")).getText().equals("-")){
					rompe = false;
				}
			}
		Assert.assertTrue(rompe);
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","consumos"}, dataProvider="PRE")
	public void Mis_Consumos_Minutos_Disponibles_PRE(String sLinea){
		imagen = "Mis_Consumos_SMS_Disponibles_PRE";
		loginPorLinea(sLinea);
		irA("consumos");
		sleep(8000);
		boolean rompe = true;
		List <WebElement> sms = driver.findElements(By.cssSelector(".card.card-xs.card-min.ng-scope"));
			for(WebElement s : sms){
				System.out.println(s.getText());
				s.getText().toLowerCase().contains("sms");
				if(s.findElement(By.tagName("span")).getText().equals("-")){
					rompe = false;
				}
			}
		Assert.assertTrue(rompe);
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","mi linea"}, dataProvider="MIX")
	public void Mi_Linea_Carga_Formulario_Baja_Servicio(String sLinea){
		imagen = "Mi_Linea_Carga_Formulario_Baja_Servicio";
		loginPorLinea(sLinea);
		irA("mi l\u00ednea");
		sleep(10000);
		buscarYClick(driver.findElements(By.cssSelector(".dev-item-menu.list-group-item")),"equals","baja del servicio");
		sleep(10000);
		Select doc = new Select(driver.findElement(By.id("tipodoc")));
		doc.selectByVisibleText("DNI");
		driver.findElement(By.id("numberdoc")).sendKeys("11232843");
		driver.findElement(By.id("nombre")).sendKeys("pepe");
		driver.findElement(By.id("apellido")).sendKeys("perez");
		driver.findElement(By.id("email")).sendKeys("a@a.com");
		driver.findElement(By.cssSelector(".form-control.areaCode")).sendKeys("11");
		driver.findElement(By.cssSelector(".form-control.telephone1.celForm")).sendKeys("62735148");
		Select dias = new Select(driver.findElement(By.id("diascontacto")));
		dias.selectByVisibleText("Lunes");
		Select hora = new Select(driver.findElement(By.id("horacontacto")));
		hora.selectByIndex(1);
		Select baja = new Select(driver.findElement(By.id("motivobaja")));
		baja.selectByIndex(1);
		driver.findElement(By.id("submit_fef510d6-2fbe-4f16-ac62-d4fc398de2c3")).click();
		sleep(39000);
		Assert.assertTrue(driver.findElement(By.id("tformtagBajaLinea")).isDisplayed());
		
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","mi linea"}, dataProvider="MIX")
	public void Mi_Linea_Carga_Formulario_Cambio_Titularidad(String sLinea){
		imagen = "Mi_Linea_Carga_Formulario_Cambio_Titularidad";
		loginPorLinea(sLinea);
		irA("mi l\u00ednea");
		sleep(10000);
		buscarYClick(driver.findElements(By.cssSelector(".dev-item-menu.list-group-item")),"equals","cambio de titularidad");
		sleep(14000);
		driver.findElements(By.cssSelector(".btn.btn-lg.btn-primary.hidden-xs.hidden-sm")).get(1).click();
		sleep(8000);
		driver.findElement(By.id("txtNombre")).sendKeys("pepe");
		driver.findElement(By.id("txtApellido")).sendKeys("perez");
		Select doc = new Select(driver.findElement(By.id("slcTipoDni")));
		doc.selectByVisibleText("DNI");
		driver.findElement(By.id("txtNroDoc")).sendKeys("11232843");
		driver.findElement(By.id("txtFecNac-dia")).sendKeys("10");
		Select mes = new Select(driver.findElement(By.id("txtFecNac-mes")));
		mes.selectByVisibleText("Octubre");
		driver.findElement(By.id("txtFecNac-anio")).sendKeys("1979");
		driver.findElement(By.id("chkGenero")).findElement(By.tagName("input")).click();
		driver.findElement(By.id("txtDireccion")).sendKeys("falsa");
		driver.findElement(By.id("txtNumeroCalle")).sendKeys("1234");
		driver.findElement(By.id("txtPiso")).sendKeys("1");
		driver.findElement(By.id("txtDepto")).sendKeys("a");
		driver.findElement(By.id("txtCodPostal")).sendKeys("1428");
		Select prov = new Select(driver.findElement(By.id("prov-prov")));
		prov.selectByVisibleText("Buenos Aires");
		sleep(3000);
		Select loca = new Select (driver.findElement(By.id("prov-loc")));
		loca.selectByIndex(1);
		Select iva = new Select (driver.findElement(By.id("txtIVA")));
		iva.selectByIndex(1);
		driver.findElement(By.id("txtEmail")).sendKeys("a@a.com");
		driver.findElement(By.cssSelector(".form-control.areaCode")).sendKeys("11");
		driver.findElement(By.cssSelector(".form-control.telephone1.celForm")).sendKeys("62735148");
		driver.findElement(By.id("btnConfirm")).click();
		sleep(8000);
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-lg.btn-primary.hidden-xs.hidden-sm")),"equals","confirmar");
		sleep(30000);
		boolean textoencontrado = false;
		List<WebElement> exito = driver.findElements(By.cssSelector(".text-center"));
			for(WebElement e : exito){
				if (e.getText().toLowerCase().contains("la carga de datos fue exitosa")){
					textoencontrado = true;
				}
			}
		Assert.assertTrue(textoencontrado);
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary")),"contains","finalizar");
		
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","mi linea"}, dataProvider="MIX")
	public void Mi_Linea_Carga_Formulario_Autorizado(String sLinea){
		imagen = "Mi_Linea_Carga_Formulario_Autorizado";
		loginPorLineaF(sLinea);
		irA("mi l\u00ednea");
		sleep(10000);
		buscarYClick(driverf.findElements(By.cssSelector(".dev-item-menu.list-group-item-full")),"equals","autorizar l\u00edneas no titulares");
		sleep(10000);
		buscarYClick(driverf.findElements(By.cssSelector(".btn.btn-lg.btn-primary.btnMedia")),"equals","agregar autorizado");
		sleep(8000);
		Assert.assertTrue(false);
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","facturacion"}, dataProvider="MIX")
	public void Facturacion_Links(String sLinea){
		imagen = "Facturacion_Links";
		loginPorLinea(sLinea);
		irA("facturaci\u00f3n");
		buscarYClick(driver.findElements(By.cssSelector(".col-sm-6.col-md-12.col-lg-6")),"equals","consultas generales de facturaci\u00f3n");
		sleep(10000);
	    ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs.get(1));
	    Assert.assertTrue(driver.getCurrentUrl().equals("https://personal.aivohelp.com/tag/c-facturacion-y-pagos/17487"));
		driver.close();
		driver.switchTo().window(tabs.get(0));
		sleep(5000);
		buscarYClick(driver.findElements(By.cssSelector(".col-sm-6.col-md-12.col-lg-6")),"equals","pagos");
		sleep(10000);
	    tabs = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs.get(1));
	    Assert.assertTrue(driver.getCurrentUrl().equals("https://personal.aivohelp.com/tag/c-facturacion-y-pagos/17487"));
		driver.close();
		driver.switchTo().window(tabs.get(0));
		sleep(5000);
		buscarYClick(driver.findElements(By.cssSelector(".col-sm-6.col-md-12.col-lg-6")),"equals","suscripciones y trivias");
		sleep(10000);
	    tabs = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs.get(1));
	    Assert.assertTrue(driver.getCurrentUrl().equals("https://personal.aivohelp.com/tag/c-facturacion-y-pagos/17487"));
		driver.close();
		driver.switchTo().window(tabs.get(0));
		sleep(5000);
		buscarYClick(driver.findElements(By.cssSelector(".col-sm-6.col-md-12.col-lg-6")),"equals","consumos");
		sleep(10000);
	    tabs = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs.get(1));
	    Assert.assertTrue(driver.getCurrentUrl().equals("https://personal.aivohelp.com/article/que-es-el-limite-de-consumo-asignado/208372"));
		driver.close();
		driver.switchTo().window(tabs.get(0));
		sleep(5000);
	}
		@Test (groups ={ "Usabilidad","navegacion"}, dataProvider="MIX")
		public void DIGITAL_WEB_CHR_IND_USABILIDAD_NAVEGACION_PRINCIPAL(String sLinea){
			imagen = "DIGITAL_WEB_CHR_IND_USABILIDAD_NAVEGACION_PRINCIPAL";
			loginPorLinea(sLinea);
			irA("consumos");
			Assert.assertTrue(driver.findElement(By.id("section-ui-view")).getText().toLowerCase().contains("cr\u00e9dito"));
			irA("recargas");
			Assert.assertTrue(driver.findElement(By.id("divDashboard")).getText().toLowerCase().contains("recarg\u00e1 ahora"));
			irA("packs");
			sleep(3000);
			boolean a = false;
			List<WebElement> roam = driver.findElements(By.cssSelector(".list-group-item.dev-categorias.ng-scope"));
				for(WebElement r : roam){
					System.out.println(r.getText());
					if(r.getText().toLowerCase().equals("roaming")){
						a = true;	
					}
				}
			irA("facturaci\u00f3n");
			sleep(3000);
			Assert.assertTrue(driver.findElement(By.id("adhesionfol")).getText().toLowerCase().contains("adherite a factura online"));
			boolean b = false;
			irA("mi l\u00ednea");
			List<WebElement> linea = driver.findElements(By.className("card-title"));
				for(WebElement l : linea){
					System.out.println("el otro" + l.getText());
					if(l.getText().toLowerCase().contains("estado de l\u00ednea")){
					b=true;	
					}
				}
			Assert.assertTrue(a);
			Assert.assertTrue(b);
		}
	
		@Test (groups ={ "Usabilidad","navegacion","Iexplorer"}, dataProvider="MIX")
		public void DIGITAL_WEB_IEX_IND_USABILIDAD_NAVEGACION_PRINCIPAL(String sLinea){
			imagen="DIGITAL_WEB_IEX_IND_USABILIDAD_NAVEGACION_PRINCIPAL";
			driver.close();
			sleep(5000);
			driveri = setup3();
			loginPorLineaI(sLinea);
			irA("consumos");
			Assert.assertTrue(driver.findElement(By.id("section-ui-view")).getText().toLowerCase().contains("cr\u00e9dito"));
			irA("recargas");
			Assert.assertTrue(driver.findElement(By.id("divDashboard")).getText().toLowerCase().contains("recarg\u00e1 ahora"));
			irA("packs");
			sleep(3000);
			boolean a = false;
			List<WebElement> roam = driver.findElements(By.cssSelector(".list-group-item.dev-categorias.ng-scope"));
				for(WebElement r : roam){
					System.out.println(r.getText());
					if(r.getText().toLowerCase().equals("roaming")){
						a = true;	
					}
				}
			irA("facturaci\u00f3n");
			sleep(3000);
			Assert.assertTrue(driver.findElement(By.id("adhesionfol")).getText().toLowerCase().contains("adherite a factura online"));
			boolean b = false;
			irA("mi l\u00ednea");
			List<WebElement> linea = driver.findElements(By.className("card-title"));
				for(WebElement l : linea){
					System.out.println("el otro" + l.getText());
					if(l.getText().toLowerCase().contains("estado de l\u00ednea")){
					b=true;	
					}
				}
			Assert.assertTrue(a);
			Assert.assertTrue(b);
		}
		

		@Test (groups ={ "Usabilidad","navegacion"}, dataProvider="MIX")
		public void DIGITAL_WEB_CHR_IND_USABILIDAD_NAVEGACION_MI_CUENTA_HOME_PERSONAL_CLUB_PERSONAL(String sLinea){
			imagen="DIGITAL_WEB_CHR_IND_USABILIDAD_NAVEGACION_MI_CUENTA_HOME_PERSONAL_CLUB_PERSONAL";
			loginPorLinea(sLinea);
			obligarclick(driver.findElement(By.id("tpi-user")));
			driver.findElement(By.id("tpi-logo")).click();
			sleep(5000);
			driver.findElement(By.id("tpi-user")).click();
			sleep(2000);
			Assert.assertTrue(driver.findElement(By.id("tpi-user-info")).getText().contains(sLinea));
			buscarYClick(driver.findElements(By.cssSelector(".tpi-navbar-item-link")),"equals","club personal");
			sleep(5000);
			System.out.println(driver.findElement(By.id("tpi-user-info")).getText());
			driver.findElement(By.id("tpi-user")).click();
			sleep(2000);
			Assert.assertTrue(driver.findElement(By.id("tpi-user-info")).getText().contains(sLinea));
			
		}
		
		@Test (groups ={ "Usabilidad","navegacionIe","Iexplorer"}, dataProvider="MIX")
		public void DIGITAL_WEB_IEX_IND_USABILIDAD_NAVEGACION_MI_CUENTA_HOME_PERSONAL_CLUB_PERSONAL(String sLinea){
			imagen="DIGITAL_WEB_IEX_IND_USABILIDAD_NAVEGACION_MI_CUENTA_HOME_PERSONAL_CLUB_PERSONAL";
			driver.close();
			sleep(5000);
			driveri = setup3();
			loginPorLineaI(sLinea);
			driver.findElement(By.id("tpi-logo")).click();
			sleep(5000);
			Assert.assertTrue(driver.findElement(By.className("contacto-telefono-numero")).isDisplayed());
			buscarYClick(driver.findElements(By.cssSelector(".tpi-navbar-item-link")),"equals","club personal");
			sleep(3000);
			Assert.assertTrue(driver.findElement(By.className("about")).getText().toLowerCase().contains("club personal"));
		}
		
		@Test (groups ={ "Usabilidad","navegacion"}, dataProvider="MIX")
		public void DIGITAL_WEB_IND_USABILIDAD_IDENTIDAD_LOGO(String sLinea){
			imagen="DIGITAL_WEB_IND_USABILIDAD_IDENTIDAD_LOGO";
			loginPorLinea(sLinea);
			Assert.assertTrue(driver.findElement(By.id("tpi-logo")).isDisplayed());
		}
				
		@Test (groups ={ "Robustez","Inputs","Debito"}, dataProvider="Input_mail")
		public void DIGITAL_WEB_IND_FACTURACION_ADHESION_DEBITO_DIRECTO_MIX_CBU_INPUT_NUM_CARACT_LETRA(String sLinea){
			imagen="DIGITAL_WEB_IND_FACTURACION_ADHESION_DEBITO_DIRECTO_MIX_CBU_INPUT_NUM_CARACT_LETRA";
			loginPorLinea(sLinea);
			irA("facturaci\u00f3n");
			IngresarCBU("asdfa");
			Assert.assertTrue(driver.findElement(By.id("inputCBU")).getAttribute("value").isEmpty());
		}
		
		@Test (groups ={ "Robustez","Inputs","Debito"}, dataProvider="Input_mail")
		public void DIGITAL_WEB_IND_FACTURACION_ADHESION_DEBITO_DIRECTO_MIX_CBU_INPUT_NUM_CARACT_COMBIN(String sLinea){
			imagen="DIGITAL_WEB_IND_FACTURACION_ADHESION_DEBITO_DIRECTO_MIX_CBU_INPUT_NUM_CARACT_COMBIN";
			loginPorLinea(sLinea);
			irA("facturaci\u00f3n");
			IngresarCBU("12&41%");
			System.out.println(driver.findElement(By.id("inputCBU")).getAttribute("value"));
			Assert.assertTrue(driver.findElement(By.id("inputCBU")).getAttribute("value").equals("1241"));
			Assert.assertTrue(driver.findElement(By.cssSelector(".control-label.dev-alert-danger")).getText().toLowerCase().equals("el cbu debe tener 22 d\u00edgitos y ser num\u00e9rico."));
			
		}
		
		@Test (groups ={ "Robustez","Inputs","Debito"}, dataProvider="Input_mail")
		public void DIGITAL_WEB_IND_FACTURACION_ADHESION_DEBITO_DIRECTO_MIX_CBU_INPUT_NUM_CARACT_NOESTAND(String sLinea){
			imagen="DIGITAL_WEB_IND_FACTURACION_ADHESION_DEBITO_DIRECTO_MIX_CBU_INPUT_NUM_CARACT_NOESTAND";
			loginPorLinea(sLinea);
			irA("facturaci\u00f3n");
			IngresarCBU("'&^*");
			Assert.assertTrue(driver.findElement(By.id("inputCBU")).getAttribute("value").isEmpty());
		}
		
		@Test (groups ={ "Robustez","Inputs","Debito"}, dataProvider="Input_mail")
		public void DIGITAL_WEB_IND_FACTURACION_ADHESION_DEBITO_DIRECTO_MIX_CBU_INPUT_NUM_CARACT_ESTAND(String sLinea){
			imagen="DIGITAL_WEB_IND_FACTURACION_ADHESION_DEBITO_DIRECTO_MIX_CBU_INPUT_NUM_CARACT_ESTAND";
			loginPorLinea(sLinea);
			irA("facturaci\u00f3n");
			IngresarCBU(".,.");
			Assert.assertTrue(driver.findElement(By.id("inputCBU")).getAttribute("value").isEmpty());
		}
		
		@Test (groups ={ "Robustez","Inputs","Debito"}, dataProvider="Input_mail")
		public void DIGITAL_WEB_IND_FACTURACION_ADHESION_DEBITO_DIRECTO_MIX_CBU_INPUT_NUM_OBLIGAT_OBLIGATORIO(String sLinea){
			imagen="DIGITAL_WEB_IND_FACTURACION_ADHESION_DEBITO_DIRECTO_MIX_CBU_INPUT_NUM_OBLIGAT_OBLIGATORIO";
			loginPorLinea(sLinea);
			irA("facturaci\u00f3n");
			IngresarCBU("");
			driver.findElement(By.id("btnAdherir")).click();
			sleep(3000);
			System.out.println(driver.findElement(By.cssSelector(".control-label.dev-alert-danger")).getText());
			Assert.assertTrue(driver.findElement(By.cssSelector(".control-label.dev-alert-danger")).getText().toLowerCase().equals("ingres\u00e1 el n\u00famero de cbu."));
		}
		
		@Test (groups ={ "Robustez","Inputs"}, dataProvider="MIX")
		public void DIGITAL_WEB_IND_FACTURACION_GENERAR_CUPON_PAGO_MIX_IMPORTE_INPUT_MONEDA_OBLIGAT_OBLIGATORIO(String sLinea){
			imagen="DIGITAL_WEB_IND_FACTURACION_GENERAR_CUPON_PAGO_MIX_IMPORTE_INPUT_MONEDA_OBLIGAT_OBLIGATORIO";
			loginPorLinea(sLinea);
			irA("facturaci\u00f3n");
			IngresarCuponPago(" ");
			Assert.assertTrue(driver.findElement(By.id("divImporte")).findElement(By.cssSelector(".control-label.dev-alert-danger")).getText().toLowerCase().equals("ingrese un importe"));
		}
		
		@Test (groups ={ "Robustez","Inputs"}, dataProvider="MIX")
		public void DIGITAL_WEB_IND_FACTURACION_GENERAR_CUPON_PAGO_MIX_IMPORTE_INPUT_MONEDA_CARACT_DECIMAL_Con_separador_coma(String sLinea){
			imagen="DIGITAL_WEB_IND_FACTURACION_GENERAR_CUPON_PAGO_MIX_IMPORTE_INPUT_MONEDA_CARACT_DECIMAL_Con_separador_coma";
			loginPorLinea(sLinea);
			irA("facturaci\u00f3n");
			IngresarCuponPago("12,25");
			System.out.println(driver.findElement(By.id("divImporte")).findElement(By.cssSelector(".control-label.dev-alert-danger")).getText());
			Assert.assertFalse(driver.findElement(By.id("divImporte")).findElement(By.cssSelector(".control-label.dev-alert-danger")).getText().toLowerCase().contains("ingrese un importe v\u00e1lido."));
		}
		
		@Test (groups ={ "Robustez","Inputs"}, dataProvider="Input_mail")
		public void DIGITAL_WEB_IND_FACTURACION_GENERAR_CUPON_PAGO_MIX_IMPORTE_INPUT_MONEDA_CARACT_DECIMAL_Con_separador_punto(String sLinea){
			imagen="DIGITAL_WEB_IND_FACTURACION_GENERAR_CUPON_PAGO_MIX_IMPORTE_INPUT_MONEDA_CARACT_DECIMAL_Con_separador_punto";
			loginPorLinea(sLinea);
			irA("facturaci\u00f3n");
			IngresarCuponPago("12.25");
			Assert.assertTrue(false);
			// =================== Falta validar cuando funcione la pagina siguiente de generar el cupon de pago ================= 
		}
		
		@Test (groups ={ "Robustez","Inputs"}, dataProvider="Input_mail")
		public void DIGITAL_WEB_IND_FACTURACION_GENERAR_CUPON_PAGO_MIX_IMPORTE_INPUT_MONEDA_CARACT_NEUTRO_Valor_Neutro_0(String sLinea){
			imagen="DIGITAL_WEB_IND_FACTURACION_GENERAR_CUPON_PAGO_MIX_IMPORTE_INPUT_MONEDA_CARACT_NEUTRO_Valor_Neutro_0";
			loginPorLinea(sLinea);
			irA("facturaci\u00f3n");
			IngresarCuponPago("0");
			Assert.assertTrue(driver.findElement(By.id("divImporte")).findElement(By.cssSelector(".control-label.dev-alert-danger")).isDisplayed());
		}
		
		@Test (groups ={ "Robustez","Inputs"}, dataProvider="MIX")
		public void DIGITAL_WEB_IND_FACTURACION_GENERAR_CUPON_PAGO_MIX_IMPORTE_INPUT_MONEDA_CARACT_NEGAT_Valores_Negativos(String sLinea){
			imagen="DIGITAL_WEB_IND_FACTURACION_GENERAR_CUPON_PAGO_MIX_IMPORTE_INPUT_MONEDA_CARACT_NEGAT_Valores_Negativos";
			loginPorLinea(sLinea);
			irA("facturaci\u00f3n");
			IngresarCuponPago("-2");
			Assert.assertTrue(driver.findElement(By.id("divMuestraMsj")).isDisplayed());
			// en el resultado esperado dice que acepta valores negativos.
		}
		
		@Test (groups ={ "Robustez","Inputs"}, dataProvider="MIX")
		public void DIGITAL_WEB_IND_FACTURACION_GENERAR_CUPON_PAGO_MIX_IMPORTE_INPUT_MONEDA_CARACT_COMBIN(String sLinea){
			imagen="DIGITAL_WEB_IND_FACTURACION_GENERAR_CUPON_PAGO_MIX_IMPORTE_INPUT_MONEDA_CARACT_COMBIN";
			loginPorLinea(sLinea);
			irA("facturaci\u00f3n");
			IngresarCuponPago("2A0.35");
			Assert.assertTrue(driver.findElement(By.id("divImporte")).findElement(By.cssSelector(".control-label.dev-alert-danger")).getText().toLowerCase().equals("ingrese un importe v\u00e1lido."));
		}
		
		@Test (groups ={ "Robustez","Inputs"}, dataProvider="MIX")
		public void DIGITAL_WEB_IND_FACTURACION_INFORMAR_PAGO_MIX_IMPORTE_INPUT_MONEDA_CARACT_COMBIN(String sLinea){
			imagen="DIGITAL_WEB_IND_FACTURACION_INFORMAR_PAGO_MIX_IMPORTE_INPUT_MONEDA_CARACT_COMBIN";
			loginPorLinea(sLinea);
			irA("facturaci\u00f3n");
			InformarPago("20.%0");
			Assert.assertTrue(driver.findElement(By.id("inputError")).findElement(By.cssSelector(".control-label.dev-alert-danger")).getText().toLowerCase().equals("el formato es incorrecto. debe ser xx,xx"));
		}
		
		@Test (groups ={ "Robustez","Inputs"}, dataProvider="MIX")
		public void DIGITAL_WEB_IND_FACTURACION_INFORMAR_PAGO_MIX_IMPORTE_INPUT_MONEDA_CARACT_NEGAT_Valores_Negarivos(String sLinea){
			imagen="DIGITAL_WEB_IND_FACTURACION_INFORMAR_PAGO_MIX_IMPORTE_INPUT_MONEDA_CARACT_NEGAT_Valores_Negarivos";
			loginPorLinea(sLinea);
			irA("facturaci\u00f3n");
			InformarPago("-2");
			Assert.assertTrue(driver.findElement(By.id("divMuestraMsj")).isDisplayed());
		}
		
		@Test (groups ={ "Robustez","Inputs"}, dataProvider="MIX")
		public void DIGITAL_WEB_IND_FACTURACION_INFORMAR_PAGO_MIX_IMPORTE_INPUT_MONEDA_CARACT_NEUTRO_Valor_Neutro_cero(String sLinea){
			imagen="DIGITAL_WEB_IND_FACTURACION_INFORMAR_PAGO_MIX_IMPORTE_INPUT_MONEDA_CARACT_NEUTRO_Valor_Neutro_cero";
			loginPorLinea(sLinea);
			irA("facturaci\u00f3n");
			InformarPago("0");
			Assert.assertTrue(driver.findElement(By.id("divMuestraMsj")).isDisplayed());
		}
		
		@Test (groups ={ "Robustez","Inputs"}, dataProvider="MIX")
		public void DIGITAL_WEB_IND_FACTURACION_INFORMAR_PAGO_MIX_IMPORTE_INPUT_MONEDA_CARACT_DECIMAL_Con_separador_punto_Entero_Decimal(String sLinea){
			imagen="DIGITAL_WEB_IND_FACTURACION_INFORMAR_PAGO_MIX_IMPORTE_INPUT_MONEDA_CARACT_DECIMAL_Con_separador_punto_Entero_Decimal";
			loginPorLinea(sLinea);
			irA("facturaci\u00f3n");
			InformarPago("12.78");
			Assert.assertTrue(driver.findElement(By.id("divMuestraMsj")).isDisplayed());
		}
		
		@Test (groups ={ "Robustez","Inputs"}, dataProvider="MIX")
		public void DIGITAL_WEB_IND_FACTURACION_INFORMAR_PAGO_MIX_IMPORTE_INPUT_MONEDA_CARACT_DECIMAL_Con_separador_coma_Entero_Decimal(String sLinea){
			imagen="DIGITAL_WEB_IND_FACTURACION_INFORMAR_PAGO_MIX_IMPORTE_INPUT_MONEDA_CARACT_DECIMAL_Con_separador_coma_Entero_Decimal";
			loginPorLinea(sLinea);
			irA("facturaci\u00f3n");
			InformarPago("12,78");
			Assert.assertTrue(driver.findElement(By.id("divMuestraMsj")).isDisplayed());
		}
		
		@Test (groups ={ "Robustez","Inputs"}, dataProvider="MIX")
		public void DIGITAL_WEB_IND_FACTURACION_INFORMAR_PAGO_MIX_IMPORTE_INPUT_MONEDA_OBLIGAT_OBLIGATORIO(String sLinea){
			imagen="DIGITAL_WEB_IND_FACTURACION_INFORMAR_PAGO_MIX_IMPORTE_INPUT_MONEDA_OBLIGAT_OBLIGATORIO";
			loginPorLinea(sLinea);
			irA("facturaci\u00f3n");
			InformarPago("");
			Assert.assertTrue(driver.findElement(By.id("inputError")).findElement(By.cssSelector(".control-label.dev-alert-danger")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.id("inputError")).findElement(By.cssSelector(".control-label.dev-alert-danger")).getText().toLowerCase().equals("ingrese un importe"));
		}
		
		@Test (groups ={ "Robustez","Inputs"}, dataProvider="Input_mail")
		public void DIGITAL_WEB_IND_FACTURACION_ADHESION_DEBITO_DIRECTO_MIX_CBU_INPUT_NUM_CARACT_MAX_Mas_de_22_digitos(String sLinea){
			imagen="DIGITAL_WEB_IND_FACTURACION_ADHESION_DEBITO_DIRECTO_MIX_CBU_INPUT_NUM_CARACT_MAX_Mas_de_22_digitos";
			String a = "12345678901234567890123"; 
			loginPorLinea(sLinea);
			irA("facturaci\u00f3n");
			IngresarCBU(a);
			System.out.println(driver.findElement(By.id("inputCBU")).getAttribute("value"));
			Assert.assertFalse(driver.findElement(By.id("inputCBU")).getAttribute("value").equals(a));
			
		}
		
		@Test (groups ={ "Robustez","Inputs"}, dataProvider="Input_mail")
		public void DIGITAL_WEB_IND_FACTURACION_ADHESION_DEBITO_DIRECTO_MIX_CBU_INPUT_NUM_CARACT_MAX_Menos_de_22_digitos(String sLinea){
			imagen="DIGITAL_WEB_IND_FACTURACION_ADHESION_DEBITO_DIRECTO_MIX_CBU_INPUT_NUM_CARACT_MAX_Menos_de_22_digitos";
			loginPorLinea(sLinea);
			irA("facturaci\u00f3n");
			IngresarCBU("1234567890123456789");
			Assert.assertTrue(driver.findElement(By.cssSelector(".control-label.dev-alert-danger")).getText().toLowerCase().equals("el cbu debe tener 22 d\u00edgitos y ser num\u00e9rico."));
		}
}
