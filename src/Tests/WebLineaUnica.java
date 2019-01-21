package Tests;

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
	public void Mis_Datos_Mi_Perfil_Cambiar_Contraseña_MIX() {
		imagen = "Mis_Datos_Mi_Perfil_Cambiar_Contraseña_MIX";
		loginPorLinea("MIX");
		Assert.assertTrue(cambiarClave("1357"));
	}
	
	@Test (groups = "AutogestionIndividuosWeb")
	public void Mis_Datos_Mi_Perfil_Cambiar_Contraseña_PRE() {
		imagen = "Mis_Datos_Mi_Perfil_Cambiar_Contraseña_PRE";
		loginPorLinea("Pre");
		Assert.assertTrue(cambiarClave("1357"));
	}
	
	@Test (groups = "AutogestionIndividuosWeb")
	public void Mis_Datos_Mi_Perfil_Cambiar_Contraseña_POS() {
		imagen = "Mis_Datos_Mi_Perfil_Cambiar_Contraseña_POS";
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
		sleep(5000);
		//System.out.println("Tu ciclo de facturación cierra todos los días 27 de cada mes.");
		WebElement cuadro = driver.findElement(By.className("cuando-llega-tu-factura"));
		Assert.assertTrue(cuadro.getText().matches(".*Tu\\sciclo\\sde\\sfacturaci\u00f3n\\scierra\\stodos\\slos\\sd\u00edas\\s\\d{1,2}\\sde\\scada\\smes\\..*"));
		
		//Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"mvc-container\"]/section/div/div[4]/div[2]/div[2]")).getText().matches("^\\d{2}/\\d{2}/\\d{4}$"));
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
}