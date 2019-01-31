package Tests;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
	String imagen;

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
		tomarCaptura(driver,imagen);
		driver.findElement(By.id("tpi-user")).click();
		sleep(7000);
		driver.findElement(By.id("tpi-form-logoff")).click();
		sleep(7000);
		driver.close();
	}
	
	@Test (groups = "AutogestionIndividuosWeb")
	public void Comprar_Packs_Compra_de_Packs_MIX(){
		imagen = "Comprar_Packs_Compra_de_Packs_MIX";
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
		imagen = "Comprar_Packs_Compra_de_Packs_PRE";
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
		imagen = "Comprar_Packs_Consultar_Comprobantes_MIX";
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
		imagen = "Comprar_Packs_Consultar_Comprobantes_PRE";
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
		imagen = "Consumos_Detalles_de_Consumos_MIX";
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
		imagen = "Consumos_Packs_Activos_MIX";
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
		imagen = "Consumos_Packs_Activos_PRE";
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
		imagen = "Mi_Linea_Baja_de_Suscripciones_MIX";
		loginPorLinea("MIX");
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
	
	@Test (groups = "AutogestionIndividuosWeb")
	public void Mi_Linea_Baja_de_Suscripciones_PRE(){
		imagen = "Mi_Linea_Baja_de_Suscripciones_PRE";
		loginPorLinea("Pre");
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
	
	@Test (groups = "AutogestionIndividuosWeb")
	public void Mi_Linea_Baja_de_Suscripciones_POS(){
		imagen = "Mi_Linea_Baja_de_Suscripciones_POS";
		loginPorLinea("Pos");
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
	
	@Test (groups = "AutogestionIndividuosWeb")
	public void Consumos_Detalles_de_Consumos_POS() {
		imagen = "Consumos_Detalles_de_Consumos_POS";
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
		imagen = "Consumos_Detalles_de_Consumos_PRE";
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
		imagen = "Recargas_Gestiones_y_Consultas_Recargar_Ahora_MIX";
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
	
	@Test (groups = "AutogestionIndividuosWeb")
	public void Recargas_Gestiones_y_Consultas_Recargar_Ahora_PRE() {
		imagen = "Recargas_Gestiones_y_Consultas_Recargar_Ahora_PRE";
		loginPorLinea("Pre");
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
	
	@Test (groups = "AutogestionIndividuosWeb")
	public void Recargas_Gestiones_y_Consultas_Ultimas_Recargas_MIX() {
		imagen = "Recargas_Gestiones_y_Consultas_Ultimas_Recargas_MIX";
		loginPorLinea("MIX");
		irA("recargas");
		driver.findElement(By.cssSelector(".card.card-lg.padding-bottom-0")).click();
		sleep(5000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".list-store.detalle-consumo")).isDisplayed());
	}
	
	@Test (groups = "AutogestionIndividuosWeb")
	public void Recargas_Gestiones_y_Consultas_Ultimas_Recargas_PRE() {
		imagen = "Recargas_Gestiones_y_Consultas_Ultimas_Recargas_PRE";
		loginPorLinea("Pre");
		irA("recargas");
		driver.findElement(By.cssSelector(".card.card-lg.padding-bottom-0")).click();
		sleep(5000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".list-store.detalle-consumo")).isDisplayed());
	}
	
	@Test (groups = "AutogestionIndividuosWeb")
	public void Mis_Datos_Mi_Perfil_Cambiar_Contrase�a_MIX() {
		imagen = "Mis_Datos_Mi_Perfil_Cambiar_Contrase�a_MIX";
		loginPorLinea("MIX");
		Assert.assertTrue(cambiarClave("1357"));
	}
	
	@Test (groups = "AutogestionIndividuosWeb")
	public void Mis_Datos_Mi_Perfil_Cambiar_Contrase�a_PRE() {
		imagen = "Mis_Datos_Mi_Perfil_Cambiar_Contrase�a_PRE";
		loginPorLinea("Pre");
		Assert.assertTrue(cambiarClave("1357"));
	}
	
	@Test (groups = "AutogestionIndividuosWeb")
	public void Mis_Datos_Mi_Perfil_Cambiar_Contrase�a_POS() {
		imagen = "Mis_Datos_Mi_Perfil_Cambiar_Contrase�a_POS";
		loginPorLinea("Pos");
		Assert.assertTrue(cambiarClave("1357"));
	}
	
	@Test
	public void Facturacion_Gestiones_y_Consultas_Distribucion_de_Factura_MIX() {
		imagen = "Facturacion_Gestiones_y_Consultas_Distribucion_de_Factura_MIX";
		loginPorLinea("MIX");
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
	
	@Test
	public void Facturacion_Gestiones_y_Consultas_Distribucion_de_Factura_POS() {
		imagen = "Facturacion_Gestiones_y_Consultas_Distribucion_de_Factura_POS";
		loginPorLinea("Pos");
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
	@Test (groups = "AutogestionIndividuosWeb")
	public void Mi_Linea_Claves_PIN_y_PUK_PRE(){
		imagen = "Mi_Linea_Claves_PIN_y_PUK_PRE";
		loginPorLinea("Pre");
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
	
	@Test (groups = "AutogestionIndividuosWeb")
	public void Mi_Linea_Claves_PIN_y_PUK_POS(){
		imagen = "Mi_Linea_Claves_PIN_y_PUK_POS";
		loginPorLinea("Pos");
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
	
	@Test (groups = "AutogestionIndividuosWeb")
	public void Mi_Linea_Claves_PIN_y_PUK_MIX(){
		imagen = "Mi_Linea_Claves_PIN_y_PUK_MIX";
		loginPorLinea("MIX");
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
	
	@Test (groups = "AutogestionIndividuosWeb")
	public void Recargas_Recarga_SOS_MIX(){
		imagen = "Recargas_Recarga_SOS_MIX";
		loginPorLinea("MIX");
		irA("recargas");
		sleep(10000);
		buscarYClick(driver.findElements(By.cssSelector(".list-group-item-full.padding-top-0")),"contains","recarga s.o.s.");
		sleep(5000);
		buscarYClick(driver.findElements(By.cssSelector(".pull-right.btn.btn-lg.btn-responsive.btn-primary")),"contains","recarg\u00e1 ahora");
		sleep(8000);
		Assert.assertTrue(driver.findElement(By.id("divRecargasExitoAbono")).isDisplayed());
		driver.findElement(By.id("lnkAtrasModulo")).click();
		sleep(10000);
		irA("recargas");
		buscarYClick(driver.findElements(By.cssSelector(".list-group-item-full.padding-top-0")),"contains","recarga s.o.s.");
		sleep(5000);
		buscarYClick(driver.findElements(By.cssSelector(".pull-right.btn.btn-lg.btn-responsive.btn-primary")),"contains","recarg\u00e1 ahora");
		sleep(8000);
		Assert.assertTrue(driver.findElement(By.id("lblErrorGlobal")).getText().toLowerCase().equals("la recarga s.o.s no pudo completarse porque ten\u00e9s pendiente el pago de la anterior."));
	}
	
	@Test (groups = "AutogestionIndividuosWeb")
	public void Recargas_Recarga_SOS_PRE(){
		imagen = "Recargas_Recarga_SOS_PRE";
		loginPorLinea("MIX");
		irA("recargas");
		sleep(10000);
		buscarYClick(driver.findElements(By.cssSelector(".list-group-item-full.padding-top-0")),"contains","recarga s.o.s.");
		sleep(5000);
		buscarYClick(driver.findElements(By.cssSelector(".pull-right.btn.btn-lg.btn-responsive.btn-primary")),"contains","recarg\u00e1 ahora");
		sleep(8000);
		Assert.assertTrue(driver.findElement(By.id("divRecargasExitoAbono")).isDisplayed());
		driver.findElement(By.id("lnkAtrasModulo")).click();
		sleep(10000);
		irA("recargas");
		buscarYClick(driver.findElements(By.cssSelector(".list-group-item-full.padding-top-0")),"contains","recarga s.o.s.");
		sleep(5000);
		buscarYClick(driver.findElements(By.cssSelector(".pull-right.btn.btn-lg.btn-responsive.btn-primary")),"contains","recarg\u00e1 ahora");
		sleep(8000);
		Assert.assertTrue(driver.findElement(By.id("lblErrorGlobal")).getText().toLowerCase().equals("la recarga s.o.s no pudo completarse porque ten\u00e9s pendiente el pago de la anterior."));
	}
	
	@Test (groups = "AutogestionIndividuosWeb")
	public void Mi_Linea_MI_Plan_MIX(){
		imagen = "Mi_Linea_MI_Plan_MIX";
		loginPorLinea("MIX");
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
	
	@Test (groups = "AutogestionIndividuosWeb")
	public void Mi_Linea_MI_Plan_POS(){
		imagen = "Mi_Linea_MI_Plan_POS";
		loginPorLinea("Pos");
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
	
	@Test (groups = "AutogestionIndividuosWeb")
	public void Mi_Linea_MI_Plan_POS_Plan_Black(){
		imagen = "Mi_Linea_MI_Plan_POS_Plan_Black";
		loginPorLinea("Pos");
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
	
	@Test (groups = "AutogestionIndividuosWeb")
	public void Facturacion_Ver_Formas_de_Pago_MIX(){
		imagen="Facturacion_Ver_Formas_de_Pago_MIX";
		loginPorLinea("MIX");
		irA("facturaci\u00f3n");
		buscarYClick(driver.findElements(By.cssSelector(".dev-item-menu.list-group-item-full")),"equals","formas y lugares de pago");
		sleep(15000);
	    ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs2.get(1));
	    Assert.assertTrue(driver.findElement(By.id("mat-input-0")).isDisplayed());
	    driver.close();
	    driver.switchTo().window(tabs2.get(0));
	}
	
	@Test (groups = "AutogestionIndividuosWeb")
	public void Facturacion_Ver_Formas_de_Pago_POS(){
		imagen="Facturacion_Ver_Formas_de_Pago_POS";
		loginPorLinea("Pos");
		irA("facturaci\u00f3n");
		buscarYClick(driver.findElements(By.cssSelector(".dev-item-menu.list-group-item-full")),"equals","formas y lugares de pago");
		sleep(15000);
	    ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs2.get(1));
	    Assert.assertTrue(driver.findElement(By.id("mat-input-0")).isDisplayed());
	    driver.close();
	    driver.switchTo().window(tabs2.get(0));
	}
	
	@Test (groups = "AutogestionIndividuosWeb")
	public void Mis_Consumos_Detalle_de_credito_MIX(){
		imagen = "Mis_Consumos_Detalle_de_credito_MIX";
		loginPorLinea("MIX");
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
	
	@Test (groups = "AutogestionIndividuosWeb")
	public void Mis_Consumos_Detalle_de_credito_PRE(){
		imagen = "Mis_Consumos_Detalle_de_credito_PRE";
		loginPorLinea("Pre");
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
	
	@Test (groups = {"AutogestionIndividuosWeb"}, dependsOnMethods="Recargas_Gestiones_y_Consultas_Ultimas_Recargas_MIX")
	public void Mis_Consumos_Detalle_de_credito_Historial_de_recargas_MIX(){
		imagen = "Mis_Consumos_Detalle_de_credito_MIX";
		loginPorLinea("MIX");
		Assert.assertTrue(true);
		sleep(3000);
	}
	
	@Test (groups = {"AutogestionIndividuosWeb"}, dependsOnMethods="Recargas_Gestiones_y_Consultas_Ultimas_Recargas_PRE")
	public void Mis_Consumos_Detalle_de_credito_Historial_de_recargas_PRE(){
		imagen = "Mis_Consumos_Detalle_de_credito_PRE";
		loginPorLinea("Pre");
		Assert.assertTrue(true);
		sleep(3000);
	}
	
	@Test (groups = "AutogestionIndividuosWeb")
	public void Mis_Consumos_Compra_Internet_MIX(){
		imagen = "Mis_Consumos_Compra_Internet_MIX";
		loginPorLinea("MIX");
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
	
	@Test (groups = "AutogestionIndividuosWeb")
	public void Mis_Consumos_Compra_Internet_PRE(){
		imagen = "Mis_Consumos_Compra_Internet_PRE";
		loginPorLinea("Pre");
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
	
	@Test (groups = "AutogestionIndividuosWeb")
	public void Mis_Consumos_Internet_de_mi_Plan_MIX(){
		imagen = "Mis_Consumos_Internet_de_mi_Plan_MIX";
		loginPorLinea("MIX");
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
	
	@Test (groups = "AutogestionIndividuosWeb")
	public void Mis_Consumos_Internet_Adicional_MIX(){
		imagen = "Mis_Consumos_Internet_Adicional_MIX";
		loginPorLinea("MIX");
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
	
	@Test (groups = "AutogestionIndividuosWeb")
	public void Mis_Consumos_Internet_Adicional_PRE(){
		imagen = "Mis_Consumos_Internet_Adicional_PRE";
		loginPorLinea("Pre");
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
	
	@Test (groups = "AutogestionIndividuosWeb")
	public void Mis_Consumos_SMS_Disponibles_MIX(){
		imagen = "Mis_Consumos_SMS_Disponibles_MIX";
		loginPorLinea("MIX");
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
	
	@Test (groups = "AutogestionIndividuosWeb")
	public void Mis_Datos_Mi_Perfil_Cambio_de_Mail() {
		imagen = "Mis_Datos_Mi_Perfil_Cambio_de_Mail";
		loginPorLinea("MIX");
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
	
	@Test (groups = "AutogestionIndividuosWeb")
	public void Facturacion_Facturacion_Digital_Suscripcion_a_Factura_Online_MIX() {
		imagen = "Facturacion_Facturacion_Digital_Suscripcion_a_Factura_Online_MIX";
		loginPorLinea("MIX");
		irA("facturaci\\u00f3n");
		driver.findElement(By.className("fo-text")).click();
		sleep(15000);
		driver.findElement(By.id("inputEmail")).sendKeys("alejandromza@gmail.com");
		//driver.findElement(By.id("btn_Adherirme")).click();
		//Se necesita el acceso a la base para obtener el id de confirmacion del mail 
		Assert.assertTrue(false);
	}
	
	@Test (groups = "AutogestionIndividuosWeb")
	public void Facturacion_Facturacion_Digital_Suscripcion_a_Factura_Online_POS() {
		imagen = "Facturacion_Facturacion_Digital_Suscripcion_a_Factura_Online_MIX";
		loginPorLinea("Pos");
		irA("facturaci\\u00f3n");
		driver.findElement(By.className("fo-text")).click();
		sleep(15000);
		driver.findElement(By.id("inputEmail")).sendKeys("alejandromza@gmail.com");
		//driver.findElement(By.id("btn_Adherirme")).click();
		//Se necesita el acceso a la base para obtener el id de confirmacion del mail 
		Assert.assertTrue(false);
	}
	
	@Test (groups = {"AutogestionIndividuosWeb"}, dependsOnMethods="Facturacion_Facturacion_Digital_Suscripcion_a_Factura_Online_MIX")
	public void Facturacion_Facturacion_Digital_Desuscripcion_a_Factura_Online_MIX(){
		imagen = "Facturacion_Facturacion_Digital_Desuscripcion_a_Factura_Online_MIX";
		loginPorLinea("MIX");
	    //SIN TERMINAR - NO SE PUEDE COMPLETAR HASTA QUE FUNCIONE LA ADHESION
	}
	
	@Test (groups = {"AutogestionIndividuosWeb"}, dependsOnMethods="Facturacion_Facturacion_Digital_Suscripcion_a_Factura_Online_POS")
	public void Facturacion_Facturacion_Digital_Desuscripcion_a_Factura_Online_POS(){
		imagen = "Facturacion_Facturacion_Digital_Desuscripcion_a_Factura_Online_POS";
		loginPorLinea("pos");
	    //SIN TERMINAR - NO SE PUEDE COMPLETAR HASTA QUE FUNCIONE LA ADHESION
	}
	
	
	@Test (groups = "AutogestionIndividuosWeb")
	public void Mis_Consumos_SMS_Disponibles_PRE(){
		imagen = "Mis_Consumos_SMS_Disponibles_PRE";
		loginPorLinea("Pre");
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
	
	@Test (groups = "AutogestionIndividuosWeb")
	public void Mis_Consumos_Minutos_Disponibles_MIX(){
		imagen = "Mis_Consumos_SMS_Disponibles_MIX";
		loginPorLinea("MIX");
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
	
	@Test (groups = "AutogestionIndividuosWeb")
	public void Mis_Consumos_Minutos_Disponibles_PRE(){
		imagen = "Mis_Consumos_SMS_Disponibles_PRE";
		loginPorLinea("Pre");
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
	
	@Test (groups = "AutogestionIndividuosWeb")
	public void Mi_Linea_Carga_Formulario_Baja_Servicio(){
		imagen = "Mi_Linea_Carga_Formulario_Baja_Servicio";
		loginPorLinea("MIX");
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
	
	@Test (groups = "AutogestionIndividuosWeb")
	public void Mi_Linea_Carga_Formulario_Cambio_Titularidad(){
		imagen = "Mi_Linea_Carga_Formulario_Cambio_Titularidad";
		loginPorLinea("MIX");
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
		Assert.assertTrue(driver.findElements(By.cssSelector("text-center")).contains("La carga de datos fue exitosa"));
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary")),"contains","finalizar");
		
	}
	
	@Test (groups = "AutogestionIndividuosWeb")
	public void Mi_Linea_Carga_Formulario_Autorizado(){
		imagen = "Mi_Linea_Carga_Formulario_Autorizado";
		loginPorLinea("MIX");
		irA("mi l\u00ednea");
		sleep(10000);
		buscarYClick(driver.findElements(By.cssSelector(".dev-item-menu.list-group-item-full")),"equals","autorizar l\u00edneas no titulares");
		sleep(10000);
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-lg.btn-primary.btnMedia")),"equals","agregar autorizado");
		sleep(8000);
		Assert.assertTrue(false);
	}
	
	
}