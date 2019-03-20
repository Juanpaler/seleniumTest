package Tests;

import java.awt.AWTException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import PageMetodos.Metodos;
import PageMetodos.PDF;

public class MiCuentaWeb extends Metodos{
	
	private WebDriver driver;
	private WebDriver driverf;
	private WebDriver driveri;
	
	String nombreCaso;
	String archivoLineas="LineasMiCuentaWeb.xlsx";

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
	
	@AfterMethod (alwaysRun = true)	
	public void after(){
		tomarCaptura(driver,nombreCaso);
		try {
			driver.findElement(By.id("tpi-user")).click();
			sleep(7000);
			driver.findElement(By.id("tpi-form-logoff")).click();
			sleep(7000);
			driver.close();
		}catch(Exception ex1){	driver.close();
		}
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","packs"})
	public void Comprar_Packs_Compra_de_Packs_MIX() throws IOException{
		
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);			
		
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
	
	@Test (groups ={ "AutogestionIndividuosWeb","packs"})
	public void Comprar_Packs_Compra_de_Packs_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

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
	
	@Test (groups ={ "AutogestionIndividuosWeb","packs"})
	public void Comprar_Packs_Consultar_Comprobantes_MIX()  throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("packs");
		sleep(15000);
		driver.findElement(By.id("btnConsultarDesk")).click();
		sleep(7000);
		driver.findElement(By.cssSelector(".item-cell-body.text-center")).findElement(By.cssSelector(".tpicon.font-30.tpicon-descargar")).click();;
		Assert.assertTrue(false);
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","packs"})
	public void Comprar_Packs_Consultar_Comprobantes_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("ahorros");
		driver.findElement(By.cssSelector(".card.card-lg.packs.ng-scope")).click();
		sleep(7000);
		driver.findElement(By.id("btnConsultarDesk")).click();
		sleep(7000);
		driver.findElement(By.cssSelector(".item-cell-body.text-center")).findElement(By.cssSelector(".tpicon.font-30.tpicon-descargar"));
		Assert.assertTrue(false);
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","consumos"})
	public void Consumos_Detalles_de_Consumos_MIX()  throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		boolean tabla = false, detalles = false;
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

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
	
	@Test (groups ={ "AutogestionIndividuosWeb","consumos"})
	public void Consumos_Packs_Activos_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("consumos");
		sleep(12000);
		buscarYClick(driver.findElements(By.cssSelector(".padding-right-pack.ng-scope")),"equals","packs activos");
		sleep(15000);
		WebElement tabla = driver.findElement(By.cssSelector(".list-store.margin-bottom-50"));
		Assert.assertTrue(tabla.isDisplayed());
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","consumos"})
	public void Consumos_Packs_Activos_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("consumos");
		sleep(12000);
		buscarYClick(driver.findElements(By.cssSelector(".padding-right-pack.ng-scope")),"equals","packs activos");
		sleep(15000);
		WebElement tabla = driver.findElement(By.cssSelector(".list-store.margin-bottom-50"));
		Assert.assertTrue(tabla.isDisplayed());
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","mi linea"})
	public void Mi_Linea_Baja_de_Suscripciones_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

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
	
	@Test (groups ={ "AutogestionIndividuosWeb","mi linea"})
	public void Mi_Linea_Baja_de_Suscripciones_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

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
	
	@Test (groups ={ "AutogestionIndividuosWeb","mi linea"})
	public void Mi_Linea_Baja_de_Suscripciones_POS() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

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
	
	@Test (groups ={ "AutogestionIndividuosWeb","consumos"})
	public void Consumos_Detalles_de_Consumos_POS()  throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		boolean tabla = false;
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

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
	
	@Test (groups ={ "AutogestionIndividuosWeb","consumos"})
	public void Consumos_Detalles_de_Consumos_PRE()  throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		boolean tabla = false, detalles = false;
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

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
	
	@Test (groups ={ "AutogestionIndividuosWeb","recargas"})
	public void Recargas_Gestiones_y_Consultas_Recargar_Ahora_MIX()  throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("recargas");
		sleep(10000);
		driver.findElement(By.id("btnRecargaTarjeta")).click();
		sleep(10000);
		Assert.assertTrue(driver.findElement(By.id("divPin")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("divCaptcha")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("btnRecargaQuemaPin")).isDisplayed());
		//obligarclick(driver.findElement(By.id("lnkFlechaIzqQP")));
		driver.findElement(By.id("lnkFlechaIzqQP")).click();			
		sleep(6000);
		driver.findElement(By.id("btRecargas20")).click();
		sleep(10000);
		//Assert.assertTrue(driver.findElement(By.id("divNuevaTC")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("divIngresoVto")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("divIngresoCodSeguridad")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector(".card.card-lg.recarga-tarjcred")).findElement(By.id("divCaptcha")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector(".card.card-lg.recarga-tarjcred")).findElement(By.id("btnContinuarTC")).isDisplayed());
		driver.findElement(By.id("lnkFlechaIzqTC")).click();
		sleep(6000);
		driver.findElement(By.id("btnRecargaOtrosMedios")).click();
		sleep(7000);
		Assert.assertTrue(driver.getCurrentUrl().equals("https://personal.aivohelp.com/tag/f-recargas-y-packs/17495")); 
		driver.navigate().back();
		sleep(5000);
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","recargas"})
	public void Recargas_Gestiones_y_Consultas_Recargar_Ahora_PRE()  throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("recargas");
		sleep(10000);
		driver.findElement(By.id("btnRecargaTarjeta")).click();
		sleep(10000);
		Assert.assertTrue(driver.findElement(By.id("divPin")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("divCaptcha")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("btnRecargaQuemaPin")).isDisplayed());
		//obligarclick(driver.findElement(By.id("lnkFlechaIzqQP")));
		driver.findElement(By.id("lnkFlechaIzqQP")).click();			
		sleep(6000);
		driver.findElement(By.id("btRecargas20")).click();
		sleep(10000);
		//Assert.assertTrue(driver.findElement(By.id("divNuevaTC")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("divIngresoVto")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("divIngresoCodSeguridad")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector(".card.card-lg.recarga-tarjcred")).findElement(By.id("divCaptcha")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector(".card.card-lg.recarga-tarjcred")).findElement(By.id("btnContinuarTC")).isDisplayed());
		driver.findElement(By.id("lnkFlechaIzqTC")).click();
		sleep(6000);
		driver.findElement(By.id("btnRecargaOtrosMedios")).click();
		sleep(7000);
		Assert.assertTrue(driver.getCurrentUrl().equals("https://personal.aivohelp.com/tag/f-recargas-y-packs/17495")); 
		driver.navigate().back();
		sleep(5000);
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","recargas"})
	public void Recargas_Gestiones_y_Consultas_Ultimas_Recargas_MIX()  throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("recargas");
		sleep(5000);
		driver.findElement(By.cssSelector(".card.card-lg.padding-bottom-0")).click();
		sleep(5000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".list-store.detalle-consumo")).isDisplayed());
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","recargas"})
	public void Recargas_Gestiones_y_Consultas_Ultimas_Recargas_PRE()  throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("recargas");
		sleep(5000);
		driver.findElement(By.cssSelector(".card.card-lg.padding-bottom-0")).click();
		sleep(5000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".list-store.detalle-consumo")).isDisplayed());
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","mis datos"})
	public void Mis_Datos_Mi_Perfil_Cambiar_Contrasenia_MIX()  throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		Assert.assertTrue(cambiarClave("1357"));
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","mis datos"})
	public void Mis_Datos_Mi_Perfil_Cambiar_Contrasenia_PRE()  throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		Assert.assertTrue(cambiarClave("1357"));
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","mis datos"})
	public void Mis_Datos_Mi_Perfil_Cambiar_Contrasenia_POS()  throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		Assert.assertTrue(cambiarClave("1357"));
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","facturacion"})
	public void Facturacion_Gestiones_y_Consultas_Distribucion_de_Factura_MIX()  throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

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
	
	@Test (groups ={ "AutogestionIndividuosWeb","facturacion"})
	public void Facturacion_Gestiones_y_Consultas_Distribucion_de_Factura_POS()  throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

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
	@Test (groups ={ "AutogestionIndividuosWeb","mi linea"})
	public void Mi_Linea_Claves_PIN_y_PUK_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

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
	
	@Test (groups ={ "AutogestionIndividuosWeb","mi linea"})
	public void Mi_Linea_Claves_PIN_y_PUK_POS() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

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
	
	@Test (groups ={ "AutogestionIndividuosWeb","mi linea"})
	public void Mi_Linea_Claves_PIN_y_PUK_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

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
	
	@Test (groups ={ "AutogestionIndividuosWeb","recargas"})
	public void Recargas_Recarga_SOS_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

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
	
	@Test (groups ={ "AutogestionIndividuosWeb","recargas"})
	public void Recargas_Recarga_SOS_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

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
	
	@Test (groups ={ "AutogestionIndividuosWeb","mi linea"})
	public void Mi_Linea_MI_Plan_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

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
	
	@Test (groups ={ "AutogestionIndividuosWeb","mi linea"})
	public void Mi_Linea_MI_Plan_POS() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

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
	
	@Test (groups ={ "AutogestionIndividuosWeb","mi linea"})
	public void Mi_Linea_Reserva_De_Turno_Plan_Black() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("mi l\u00ednea");
		sleep(10000);
		buscarYClick(driver.findElements(By.cssSelector(".dev-item-menu.list-group-item")),"equals","reserva de turno");
		sleep(8000);
		String FechaFutura = GetStringDatePlusDay(5);
		completarDatos("Compra de Equipo/L\u00ednea","Buenos Aires","San Justo","Dr. Ignacio Arieta 3169","111111111","a@a.com",FechaFutura);
		Assert.assertTrue(driver.findElement(By.id("lblMensajeExito")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("lblMensajeExito")).getText().contains("Reservaste con \u00e9xito el turno para asistir a una oficina comercial"));
		sleep(3000);
		driver.findElement(By.id("btnCancelar")).click();
		sleep(2500);
		driver.findElement(By.id("btnSi")).click();
		sleep(5000);
		Assert.assertTrue(driver.findElement(By.id("btnPedirOtroTurno")).isDisplayed());
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","facturacion"})
	public void Facturacion_Ver_Formas_de_Pago_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("facturaci\u00f3n");
		buscarYClick(driver.findElements(By.cssSelector(".dev-item-menu.list-group-item-full")),"equals","formas y lugares de pago");
		sleep(15000);
	    ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs2.get(1));
	    Assert.assertTrue(driver.findElement(By.id("mat-input-0")).isDisplayed());
	    driver.close();
	    driver.switchTo().window(tabs2.get(0));
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","facturacion"})
	public void Facturacion_Ver_Formas_de_Pago_POS() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("facturaci\u00f3n");
		buscarYClick(driver.findElements(By.cssSelector(".dev-item-menu.list-group-item-full")),"equals","formas y lugares de pago");
		sleep(15000);
	    ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs2.get(1));
	    Assert.assertTrue(driver.findElement(By.id("mat-input-0")).isDisplayed());
	    driver.close();
	    driver.switchTo().window(tabs2.get(0));
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","consumos"})
	public void Mis_Consumos_Detalle_de_credito_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

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
	
	@Test (groups ={ "AutogestionIndividuosWeb","consumos"})
	public void Mis_Consumos_Detalle_de_credito_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

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
	
	@Test (groups ={ "AutogestionIndividuosWeb","consumos"}, dependsOnMethods="Recargas_Gestiones_y_Consultas_Ultimas_Recargas_MIX")
	public void Mis_Consumos_Detalle_de_credito_Historial_de_recargas_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		Assert.assertTrue(true);
		sleep(3000);
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","consumos"}, dependsOnMethods="Recargas_Gestiones_y_Consultas_Ultimas_Recargas_PRE")
	public void Mis_Consumos_Detalle_de_credito_Historial_de_recargas_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		Assert.assertTrue(true);
		sleep(3000);
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","consumos"})
	public void Mis_Consumos_Compra_Internet_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

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
	
	@Test (groups ={ "AutogestionIndividuosWeb","consumos"})
	public void Mis_Consumos_Compra_Internet_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

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
	
	@Test (groups ={ "AutogestionIndividuosWeb","consumos"})
	public void Mis_Consumos_Internet_de_mi_Plan_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

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
	
	@Test (groups ={ "AutogestionIndividuosWeb","consumos"})
	public void Mis_Consumos_Internet_Adicional_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

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
	
	@Test (groups ={ "AutogestionIndividuosWeb","consumos"})
	public void Mis_Consumos_Internet_Adicional_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

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
	
	@Test (groups ={ "AutogestionIndividuosWeb","consumos"})
	public void Mis_Consumos_SMS_Disponibles_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

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
	
	@Test (groups ={ "AutogestionIndividuosWeb","mis datos"})
	public void Mis_Datos_Mi_Perfil_Cambio_de_Mail()  throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

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
	
	@Test (groups ={ "AutogestionIndividuosWeb","facturacion"})
	public void Facturacion_Facturacion_Digital_Suscripcion_a_Factura_Online_MIX()  throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("facturaci\\u00f3n");
		driver.findElement(By.className("fo-text")).click();
		sleep(15000);
		driver.findElement(By.id("inputEmail")).sendKeys("alejandromza@gmail.com");
		//driver.findElement(By.id("btn_Adherirme")).click();
		//Se necesita el acceso a la base para obtener el id de confirmacion del mail 
		Assert.assertTrue(false);
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","facturacion"})
	public void Facturacion_Facturacion_Digital_Suscripcion_a_Factura_Online_POS()  throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("facturaci\\u00f3n");
		driver.findElement(By.className("fo-text")).click();
		sleep(15000);
		driver.findElement(By.id("inputEmail")).sendKeys("alejandromza@gmail.com");
		//driver.findElement(By.id("btn_Adherirme")).click();
		//Se necesita el acceso a la base para obtener el id de confirmacion del mail 
		Assert.assertTrue(false);
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","facturacion"}, dependsOnMethods="Facturacion_Facturacion_Digital_Suscripcion_a_Factura_Online_MIX")
	public void Facturacion_Facturacion_Digital_Desuscripcion_a_Factura_Online_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

	    //SIN TERMINAR - NO SE PUEDE COMPLETAR HASTA QUE FUNCIONE LA ADHESION
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","facturacion"}, dependsOnMethods="Facturacion_Facturacion_Digital_Suscripcion_a_Factura_Online_POS")
	public void Facturacion_Facturacion_Digital_Desuscripcion_a_Factura_Online_POS() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

	    //SIN TERMINAR - NO SE PUEDE COMPLETAR HASTA QUE FUNCIONE LA ADHESION
	}
	
	
	@Test (groups ={ "AutogestionIndividuosWeb","consumos"})
	public void Mis_Consumos_SMS_Disponibles_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

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
	
	@Test (groups ={ "AutogestionIndividuosWeb","consumos"})
	public void Mis_Consumos_Minutos_Disponibles_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

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
	
	@Test (groups ={ "AutogestionIndividuosWeb","consumos"})
	public void Mis_Consumos_Minutos_Disponibles_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

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
	
	@Test (groups ={ "AutogestionIndividuosWeb","mi linea"})
	public void Mi_Linea_Carga_Formulario_Baja_Servicio() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

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
	
	@Test (groups ={ "AutogestionIndividuosWeb","mi linea"})
	public void Mi_Linea_Carga_Formulario_Cambio_Titularidad() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

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
	
	@Test (groups ={ "AutogestionIndividuosWeb","mi linea"})
	public void Mi_Linea_Carga_Formulario_Autorizado() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLineaF(linea);
		
		irA("mi l\u00ednea");
		sleep(10000);
		buscarYClick(driverf.findElements(By.cssSelector(".dev-item-menu.list-group-item-full")),"equals","autorizar l\u00edneas no titulares");
		sleep(10000);
		buscarYClick(driverf.findElements(By.cssSelector(".btn.btn-lg.btn-primary.btnMedia")),"equals","agregar autorizado");
		sleep(8000);
		Assert.assertTrue(false);
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","facturacion"})
	public void Facturacion_Links() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

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
	@Test (groups ={ "Usabilidad","navegacion"})
	public void DIGITAL_WEB_CHR_IND_USABILIDAD_NAVEGACION_PRINCIPAL() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

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
		List<WebElement> lineas = driver.findElements(By.className("card-title"));
			for(WebElement l : lineas){
				System.out.println("el otro" + l.getText());
				if(l.getText().toLowerCase().contains("estado de l\u00ednea")){
				b=true;	
				}
			}
		Assert.assertTrue(a);
		Assert.assertTrue(b);
	}

	@Test (groups ={ "Usabilidad","navegacion","Iexplorer"})
	public void DIGITAL_WEB_IEX_IND_USABILIDAD_NAVEGACION_PRINCIPAL() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		driver.close();
		sleep(5000);
		driveri = setup3();
		
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLineaI(linea);

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
		List<WebElement> lineas = driver.findElements(By.className("card-title"));
			for(WebElement l : lineas){
				System.out.println("el otro" + l.getText());
				if(l.getText().toLowerCase().contains("estado de l\u00ednea")){
				b=true;	
				}
			}
		Assert.assertTrue(a);
		Assert.assertTrue(b);
	}
	

	@Test (groups ={ "Usabilidad","navegacion"})
	public void DIGITAL_WEB_CHR_IND_USABILIDAD_NAVEGACION_MI_CUENTA_HOME_PERSONAL_CLUB_PERSONAL() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea= String.valueOf(retornaLinea(nombreCaso,archivoLineas));
		loginPorLinea(linea);

		obligarclick(driver.findElement(By.id("tpi-user")));
		driver.findElement(By.id("tpi-logo")).click();
		sleep(5000);
		driver.findElement(By.id("tpi-user")).click();
		sleep(2000);
		Assert.assertTrue(driver.findElement(By.id("tpi-user-info")).getText().contains(linea));
		buscarYClick(driver.findElements(By.cssSelector(".tpi-navbar-item-link")),"equals","club personal");
		sleep(5000);
		System.out.println(driver.findElement(By.id("tpi-user-info")).getText());
		driver.findElement(By.id("tpi-user")).click();
		sleep(2000);
		Assert.assertTrue(driver.findElement(By.id("tpi-user-info")).getText().contains(linea));
		
	}
	
	@Test (groups ={ "Usabilidad","navegacionIe","Iexplorer"})
	public void DIGITAL_WEB_IEX_IND_USABILIDAD_NAVEGACION_MI_CUENTA_HOME_PERSONAL_CLUB_PERSONAL() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		driver.close();
		sleep(5000);
		driveri = setup3();
		
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLineaI(linea);
		
		driver.findElement(By.id("tpi-logo")).click();
		sleep(5000);
		Assert.assertTrue(driver.findElement(By.className("contacto-telefono-numero")).isDisplayed());
		buscarYClick(driver.findElements(By.cssSelector(".tpi-navbar-item-link")),"equals","club personal");
		sleep(3000);
		Assert.assertTrue(driver.findElement(By.className("about")).getText().toLowerCase().contains("club personal"));
	}
	
	@Test (groups ={ "Usabilidad","navegacion"})
	public void DIGITAL_WEB_IND_USABILIDAD_IDENTIDAD_LOGO() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		Assert.assertTrue(driver.findElement(By.id("tpi-logo")).isDisplayed());
	}
			
	@Test (groups ={ "Robustez","Inputs","Debito"})
	public void DIGITAL_WEB_IND_FACTURACION_ADHESION_DEBITO_DIRECTO_MIX_CBU_INPUT_NUM_CARACT_LETRA() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("facturaci\u00f3n");
		IngresarCBU("asdfa");
		Assert.assertTrue(driver.findElement(By.id("inputCBU")).getAttribute("value").isEmpty());
	}
	
	@Test (groups ={ "Robustez","Inputs","Debito"})
	public void DIGITAL_WEB_IND_FACTURACION_ADHESION_DEBITO_DIRECTO_MIX_CBU_INPUT_NUM_CARACT_COMBIN() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("facturaci\u00f3n");
		IngresarCBU("12&41%");
		System.out.println(driver.findElement(By.id("inputCBU")).getAttribute("value"));
		Assert.assertTrue(driver.findElement(By.id("inputCBU")).getAttribute("value").equals("1241"));
		Assert.assertTrue(driver.findElement(By.cssSelector(".control-label.dev-alert-danger")).getText().toLowerCase().equals("el cbu debe tener 22 d\u00edgitos y ser num\u00e9rico."));
		
	}
	
	@Test (groups ={ "Robustez","Inputs","Debito"})
	public void DIGITAL_WEB_IND_FACTURACION_ADHESION_DEBITO_DIRECTO_MIX_CBU_INPUT_NUM_CARACT_NOESTAND() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("facturaci\u00f3n");
		IngresarCBU("'&^*");
		Assert.assertTrue(driver.findElement(By.id("inputCBU")).getAttribute("value").isEmpty());
	}
	
	@Test (groups ={ "Robustez","Inputs","Debito"})
	public void DIGITAL_WEB_IND_FACTURACION_ADHESION_DEBITO_DIRECTO_MIX_CBU_INPUT_NUM_CARACT_ESTAND() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("facturaci\u00f3n");
		IngresarCBU(".,.");
		Assert.assertTrue(driver.findElement(By.id("inputCBU")).getAttribute("value").isEmpty());
	}
	
	@Test (groups ={ "Robustez","Inputs","Debito"})
	public void DIGITAL_WEB_IND_FACTURACION_ADHESION_DEBITO_DIRECTO_MIX_CBU_INPUT_NUM_OBLIGAT_OBLIGATORIO() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("facturaci\u00f3n");
		IngresarCBU("");
		driver.findElement(By.id("btnAdherir")).click();
		sleep(3000);
		System.out.println(driver.findElement(By.cssSelector(".control-label.dev-alert-danger")).getText());
		Assert.assertTrue(driver.findElement(By.cssSelector(".control-label.dev-alert-danger")).getText().toLowerCase().equals("ingres\u00e1 el n\u00famero de cbu."));
	}
	
	@Test (groups ={ "Robustez","Inputs"})
	public void DIGITAL_WEB_IND_FACTURACION_GENERAR_CUPON_PAGO_MIX_IMPORTE_INPUT_MONEDA_OBLIGAT_OBLIGATORIO() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("facturaci\u00f3n");
		IngresarCuponPago(" ");
		Assert.assertTrue(driver.findElement(By.id("divImporte")).findElement(By.cssSelector(".control-label.dev-alert-danger")).getText().toLowerCase().equals("ingrese un importe"));
	}
	
	@Test (groups ={ "Robustez","Inputs"})
	public void DIGITAL_WEB_IND_FACTURACION_GENERAR_CUPON_PAGO_MIX_IMPORTE_INPUT_MONEDA_CARACT_DECIMAL_Con_separador_coma() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("facturaci\u00f3n");
		IngresarCuponPago("12,25");
		System.out.println(driver.findElement(By.id("divImporte")).findElement(By.cssSelector(".control-label.dev-alert-danger")).getText());
		Assert.assertFalse(driver.findElement(By.id("divImporte")).findElement(By.cssSelector(".control-label.dev-alert-danger")).getText().toLowerCase().contains("ingrese un importe v\u00e1lido."));
	}
	
	@Test (groups ={ "Robustez","Inputs"})
	public void DIGITAL_WEB_IND_FACTURACION_GENERAR_CUPON_PAGO_MIX_IMPORTE_INPUT_MONEDA_CARACT_DECIMAL_Con_separador_punto() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("facturaci\u00f3n");
		IngresarCuponPago("12.25");
		Assert.assertTrue(false);
		// =================== Falta validar cuando funcione la pagina siguiente de generar el cupon de pago ================= 
	}
	
	@Test (groups ={ "Robustez","Inputs"})
	public void DIGITAL_WEB_IND_FACTURACION_GENERAR_CUPON_PAGO_MIX_IMPORTE_INPUT_MONEDA_CARACT_NEUTRO_Valor_Neutro_0() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("facturaci\u00f3n");
		IngresarCuponPago("0");
		Assert.assertTrue(driver.findElement(By.id("divImporte")).findElement(By.cssSelector(".control-label.dev-alert-danger")).isDisplayed());
	}
	
	@Test (groups ={ "Robustez","Inputs"})
	public void DIGITAL_WEB_IND_FACTURACION_GENERAR_CUPON_PAGO_MIX_IMPORTE_INPUT_MONEDA_CARACT_NEGAT_Valores_Negativos() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("facturaci\u00f3n");
		IngresarCuponPago("-2");
		Assert.assertTrue(driver.findElement(By.id("divMuestraMsj")).isDisplayed());
		// en el resultado esperado dice que acepta valores negativos.
	}
	
	@Test (groups ={ "Robustez","Inputs"})
	public void DIGITAL_WEB_IND_FACTURACION_GENERAR_CUPON_PAGO_MIX_IMPORTE_INPUT_MONEDA_CARACT_COMBIN() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("facturaci\u00f3n");
		IngresarCuponPago("2A0.35");
		Assert.assertTrue(driver.findElement(By.id("divImporte")).findElement(By.cssSelector(".control-label.dev-alert-danger")).getText().toLowerCase().equals("ingrese un importe v\u00e1lido."));
	}
	
	@Test (groups ={ "Robustez","Inputs"})
	public void DIGITAL_WEB_IND_FACTURACION_INFORMAR_PAGO_MIX_IMPORTE_INPUT_MONEDA_CARACT_COMBIN() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("facturaci\u00f3n");
		InformarPago("20.%0");
		Assert.assertTrue(driver.findElement(By.id("inputError")).findElement(By.cssSelector(".control-label.dev-alert-danger")).getText().toLowerCase().equals("el formato es incorrecto. debe ser xx,xx"));
	}
	
	@Test (groups ={ "Robustez","Inputs"})
	public void DIGITAL_WEB_IND_FACTURACION_INFORMAR_PAGO_MIX_IMPORTE_INPUT_MONEDA_CARACT_NEGAT_Valores_Negarivos() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("facturaci\u00f3n");
		InformarPago("-2");
		Assert.assertTrue(driver.findElement(By.id("divMuestraMsj")).isDisplayed());
	}
	
	@Test (groups ={ "Robustez","Inputs"})
	public void DIGITAL_WEB_IND_FACTURACION_INFORMAR_PAGO_MIX_IMPORTE_INPUT_MONEDA_CARACT_NEUTRO_Valor_Neutro_cero() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("facturaci\u00f3n");
		InformarPago("0");
		Assert.assertTrue(driver.findElement(By.id("divMuestraMsj")).isDisplayed());
	}
	
	@Test (groups ={ "Robustez","Inputs"})
	public void DIGITAL_WEB_IND_FACTURACION_INFORMAR_PAGO_MIX_IMPORTE_INPUT_MONEDA_CARACT_DECIMAL_Con_separador_punto_Entero_Decimal() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("facturaci\u00f3n");
		InformarPago("12.78");
		Boolean existe = driver.findElements(By.cssSelector("control-label.dev-alert-danger")).size() >= 1;
		Assert.assertFalse(existe);
	}
	
	@Test (groups ={ "Robustez","Inputs"})
	public void DIGITAL_WEB_IND_FACTURACION_INFORMAR_PAGO_MIX_IMPORTE_INPUT_MONEDA_CARACT_DECIMAL_Con_separador_coma_Entero_Decimal() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("facturaci\u00f3n");
		InformarPago("12,78");
		Assert.assertTrue(driver.findElement(By.id("divMuestraMsj")).isDisplayed());
	}
	
	@Test (groups ={ "Robustez","Inputs"})
	public void DIGITAL_WEB_IND_FACTURACION_INFORMAR_PAGO_MIX_IMPORTE_INPUT_MONEDA_OBLIGAT_OBLIGATORIO() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("facturaci\u00f3n");
		InformarPago("");
		Assert.assertTrue(driver.findElement(By.id("inputError")).findElement(By.cssSelector(".control-label.dev-alert-danger")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("inputError")).findElement(By.cssSelector(".control-label.dev-alert-danger")).getText().toLowerCase().equals("ingrese un importe"));
	}
	
	@Test (groups ={ "Robustez","Inputs"})
	public void DIGITAL_WEB_IND_FACTURACION_ADHESION_DEBITO_DIRECTO_MIX_CBU_INPUT_NUM_CARACT_MAX_Mas_de_22_digitos() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String a = "12345678901234567890123"; 
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("facturaci\u00f3n");
		IngresarCBU(a);
		System.out.println(driver.findElement(By.id("inputCBU")).getAttribute("value"));
		Assert.assertFalse(driver.findElement(By.id("inputCBU")).getAttribute("value").equals(a));
		
	}
	
	@Test (groups ={ "Robustez","Inputs"})
	public void DIGITAL_WEB_IND_FACTURACION_ADHESION_DEBITO_DIRECTO_MIX_CBU_INPUT_NUM_CARACT_MAX_Menos_de_22_digitos() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("facturaci\u00f3n");
		IngresarCBU("1234567890123456789");
		Assert.assertTrue(driver.findElement(By.cssSelector(".control-label.dev-alert-danger")).getText().toLowerCase().equals("el cbu debe tener 22 d\u00edgitos y ser num\u00e9rico."));
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","mi linea"}) 
	public void Mi_Linea_Transferencia_de_Llamadas_POS() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);
		
		irA("mi l\u00ednea");
		sleep(12000);
		buscarYClick(driver.findElements(By.cssSelector(".dev-item-menu.list-group-item")),"equals","transferencia de llamadas");
		sleep(12000);
		Select transf = new Select (driver.findElement(By.id("select")));
		transf.selectByVisibleText("Transferir");
		driver.findElement(By.id("inputCaracteristica")).sendKeys("11");
		driver.findElement(By.id("inputNumero")).sendKeys("62735148");
		sleep(1500);
		driver.findElement(By.id("btnTransferir")).click();
		sleep(6000);
		WebElement msj = driver.findElement(By.id("pMensaje"));
		Assert.assertTrue(msj.getText().toLowerCase().equals("la transferencia se realizo existosamente. muchas gracias."));
		Assert.assertTrue(msj.isDisplayed());
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","mi linea"})
	public void Mi_Linea_Destransferencia_de_Llamadas_POS() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("mi l\u00ednea");
		sleep(12000);
		buscarYClick(driver.findElements(By.cssSelector(".dev-item-menu.list-group-item")),"equals","transferencia de llamadas");
		sleep(12000);
		Select transf = new Select (driver.findElement(By.id("select")));
		transf.selectByVisibleText("Destranferir");
		driver.findElement(By.id("btnDestranferir")).click();
		sleep(6000);
		WebElement msj = driver.findElement(By.id("pMensaje"));
		Assert.assertTrue(msj.getText().toLowerCase().equals("la destransferencia se realizo existosamente. muchas gracias."));
		Assert.assertTrue(msj.isDisplayed());
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","mi linea"}) 
	public void MI_Linea_Roaming_y_LDI_habilitado_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("mi l\u00ednea");
		sleep(12000);
		buscarYClick(driver.findElements(By.cssSelector(".dev-item-menu.list-group-item-full")),"equals","roaming y larga distancia internacional");
		sleep(10000);
		WebElement roam = driver.findElements(By.className("card-flip-container")).get(0);
		WebElement lid  = driver.findElements(By.className("card-flip-container")).get(1);
		System.out.println("este es uno" + roam.getText());
		System.out.println("Este es el otro " + lid.getText());
		Assert.assertTrue(roam.getText().toLowerCase().contains("habilitado"));
		Assert.assertTrue(lid.getText().toLowerCase().contains("habilitado"));
	}
	
	@Test(groups ={ "AutogestionIndividuosWeb","mi linea"}) 
	public void MI_Linea_Roaming_y_LDI_habilitado_POS() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("mi l\u00ednea");
		sleep(12000);
		buscarYClick(driver.findElements(By.cssSelector(".dev-item-menu.list-group-item-full")),"equals","roaming y larga distancia internacional");
		sleep(10000);
		WebElement roam = driver.findElements(By.className("card-flip-container")).get(0);
		WebElement lid  = driver.findElements(By.className("card-flip-container")).get(1);
		System.out.println("este es uno" + roam.getText());
		System.out.println("Este es el otro " + lid.getText());
		Assert.assertTrue(roam.getText().toLowerCase().contains("habilitado"));
		Assert.assertTrue(lid.getText().toLowerCase().contains("habilitado"));
	}
	
	@Test(groups ={ "AutogestionIndividuosWeb","mi linea"}) 
	public void MI_Linea_Roaming_y_LDI_habilitado_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("mi l\u00ednea");
		sleep(12000);
		buscarYClick(driver.findElements(By.cssSelector(".dev-item-menu.list-group-item-full")),"equals","roaming y larga distancia internacional");
		sleep(10000);
		WebElement roam = driver.findElements(By.className("card-flip-container")).get(0);
		WebElement lid  = driver.findElements(By.className("card-flip-container")).get(1);
		System.out.println("este es uno" + roam.getText());
		System.out.println("Este es el otro " + lid.getText());
		Assert.assertTrue(roam.getText().toLowerCase().contains("habilitado"));
		Assert.assertTrue(lid.getText().toLowerCase().contains("habilitado"));
	}
	
	@Test(groups ={ "AutogestionIndividuosWeb","mi linea"}) 
	public void Mi_Linea_Numeros_Amigos_para_Hablar_SMS_Activacion_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String ln = "1162735148";
		ln = ln.substring(ln.length()-8);
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("mi l\u00ednea");
		sleep(12000);
		buscarYClick(driver.findElements(By.cssSelector(".card-footer")),"equals","gestionar n\u00fameros gratis");
		sleep(8000);
			if(driver.findElement(By.id("divListNroSMS")).findElement(By.className("text-muted")).isDisplayed()){
				driver.findElement(By.id("divListNroSMS")).findElement(By.cssSelector(".text-destacado.text-destacado-lg")).click();
			}
			else{  
				driver.findElement(By.xpath("//*[@id='divListNroSMS']/div[2]/table/tbody/tr[2]/td[1]/a")).click();
				}
		sleep(5000);
		driver.findElement(By.id("editSms")).findElement(By.id("inputCarct")).sendKeys("11");
		driver.findElement(By.id("editSms")).findElement(By.id("inputNumb")).sendKeys(ln);
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-lg.btn-primary")),"equals","guardar");
		sleep(8000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".alert.alert-dismissable.alert-success.ng-binding")).getText().toLowerCase().contains(" registraste el n\u00famero gratis con \u00e9xito"));
	}
	
	@Test(groups ={ "AutogestionIndividuosWeb","mi linea"}) 
	public void Mi_Linea_Numeros_Amigos_para_Hablar_SMS_Activacion_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String ln = "1162735148";
		ln = ln.substring(ln.length()-8);
		
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("mi l\u00ednea");
		sleep(12000);
		buscarYClick(driver.findElements(By.cssSelector(".card-footer")),"equals","gestionar n\u00fameros gratis");
		sleep(8000);
			if(driver.findElement(By.id("divListNroSMS")).findElement(By.className("text-muted")).isDisplayed()){
				driver.findElement(By.id("divListNroSMS")).findElement(By.cssSelector(".text-destacado.text-destacado-lg")).click();
			}
			else{  
				driver.findElement(By.xpath("//*[@id='divListNroSMS']/div[2]/table/tbody/tr[2]/td[1]/a")).click();
				}
		sleep(5000);
		driver.findElement(By.id("editSms")).findElement(By.id("inputCarct")).sendKeys("11");
		driver.findElement(By.id("editSms")).findElement(By.id("inputNumb")).sendKeys(ln);
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-lg.btn-primary")),"equals","guardar");
		sleep(8000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".alert.alert-dismissable.alert-success.ng-binding")).getText().toLowerCase().contains(" registraste el n\u00famero gratis con \u00e9xito"));
	}
	
	@Test(groups ={ "AutogestionIndividuosWeb","mi linea"}) 
	public void Mi_Linea_Numeros_Amigos_para_Hablar_SMS_Eliminacion_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
				
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("mi l\u00ednea");
		sleep(12000);
		buscarYClick(driver.findElements(By.cssSelector(".card-footer")),"equals","gestionar n\u00fameros gratis");
		sleep(8000);
		driver.findElement(By.id("divListNroSMS")).findElement(By.cssSelector(".tpicon.tpicon-cerrar2.text-danger")).click();
		sleep(5000);
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-lg.btn-primary")),"equals","confirmar");
		sleep(8000);
		Assert.assertTrue(driver.findElement(By.id("divListNroSMS")).findElement(By.className("text-muted")).isDisplayed());
	}
	
	@Test(groups ={ "AutogestionIndividuosWeb","mi linea"}) 
	public void Mi_Linea_Numeros_Amigos_para_Hablar_SMS_Eliminacion_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("mi l\u00ednea");
		sleep(12000);
		buscarYClick(driver.findElements(By.cssSelector(".card-footer")),"equals","gestionar n\u00fameros gratis");
		sleep(8000);
		driver.findElement(By.id("divListNroSMS")).findElement(By.cssSelector(".tpicon.tpicon-cerrar2.text-danger")).click();
		sleep(5000);
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-lg.btn-primary")),"equals","confirmar");
		sleep(8000);
		Assert.assertTrue(driver.findElement(By.id("divListNroSMS")).findElement(By.className("text-muted")).isDisplayed());
	}
	
	@Test(groups ={ "AutogestionIndividuosWeb","mi linea"}) 
	public void Mi_Linea_Seguimineto_de_Gestiones_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("mi l\u00ednea");
		sleep(12000);
		buscarYClick(driver.findElements(By.cssSelector(".dev-item-menu.list-group-item-full")),"equals","seguimiento de gestiones");
		sleep(8000);
		Assert.assertTrue(driver.findElement(By.id("itemContainer")).isDisplayed());
		driver.findElement(By.id("imgVerDetalle")).click();
		sleep(5000);
		Assert.assertTrue(false);
	}
	
	@Test(groups ={ "AutogestionIndividuosWeb","mi linea"}) 
	public void Mi_Linea_Seguimineto_de_Gestiones_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("mi l\u00ednea");
		sleep(12000);
		buscarYClick(driver.findElements(By.cssSelector(".dev-item-menu.list-group-item-full")),"equals","seguimiento de gestiones");
		sleep(8000);
		Assert.assertTrue(driver.findElement(By.id("itemContainer")).isDisplayed());
		driver.findElement(By.id("imgVerDetalle")).click();
		sleep(5000);
		Assert.assertTrue(false);
	}
	
	@Test(groups ={ "AutogestionIndividuosWeb","mi linea"}) 
	public void Mi_Linea_Seguimineto_de_Gestiones_POS() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("mi l\u00ednea");
		sleep(12000);
		buscarYClick(driver.findElements(By.cssSelector(".dev-item-menu.list-group-item-full")),"equals","seguimiento de gestiones");
		sleep(10000);
		Assert.assertTrue(driver.findElement(By.id("itemContainer")).isDisplayed());
		driver.findElement(By.id("imgVerDetalle")).click();
		sleep(5000);
		Assert.assertTrue(false);
	}
	
	@Test(groups ={ "AutogestionIndividuosWeb","consumos"}) 
	public void Mis_Consumos_DataSharing_Alta_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String num = "64480754";
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("consumos");
		sleep(10000);
		try {
			driver.findElement(By.className("acess-ds")).click();
			sleep(5000);
			driver.findElement(By.className("agregar")).click();;
		} catch (Exception e) {}
			buscarYClick(driver.findElements(By.cssSelector(".card.card-xs.data-sharing.ng-scope")),"contains","gigas compartidos");
			sleep(5000);
			buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary.btn-lg.btn-block")),"equals","comenzar");
			sleep(8000);
		driver.findElement(By.name("inp_CodArea")).sendKeys("11");
		driver.findElement(By.id("inputNumb")).sendKeys(num);
		driver.findElement(By.name("inp_Limite")).sendKeys("1");
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-lg.btn-primary.ng-binding")),"equals","agregar");
		sleep(8000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".card.card-lg.adm-i-compartido")).isDisplayed());
		
	}
	
	@Test(groups ={ "AutogestionIndividuosWeb","consumos"}) 
	public void Mis_Consumos_DataSharing_Modificacion_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("consumos");
		sleep(10000);
		driver.findElement(By.className("acess-ds")).click();
		sleep(5000);
		driver.findElement(By.cssSelector(".tpicon.tpicon-editar.edit")).click();
		sleep(5000);
		WebElement gigas = driver.findElement(By.id("inputCarct"));
		gigas.clear();
			if(gigas.getAttribute("value").equals("1")){
				gigas.sendKeys("2");
			}else{
				gigas.getAttribute("value").equals("2");
				driver.findElement(By.id("inputCarct")).sendKeys("1");
		}
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-lg.btn-primary.ng-binding")),"equals","guardar");
		sleep(15000);
		Assert.assertTrue(driver.findElements(By.id("divExito")).get(1).getText().toLowerCase().contains("el l\u00edmite se modific\u00f3 con \u00e9xito"));
	}
	
	@Test(groups ={ "AutogestionIndividuosWeb","consumos"}) 
	public void Mis_Consumos_DataSharing_Baja_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("consumos");
		sleep(10000);
		driver.findElement(By.className("acess-ds")).click();
		sleep(5000);
		driver.findElement(By.cssSelector(".tpicon.tpicon-cerrar2.delete")).click();
		sleep(5000);
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-lg.btn-primary.ng-binding")),"equals","eliminar");
		sleep(15000);
		Assert.assertTrue(driver.findElements(By.id("divExito")).get(1).getText().toLowerCase().contains("el n\u00famero se elimin\u00f3 con \u00e9xito"));
	}
	
	@Test(groups ={ "AutogestionIndividuosWeb","facturacion"}) 
	public void Facturacion_Imprimir_cupon_de_pago_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("facturaci\u00f3n");
		sleep(10000);
		driver.findElement(By.id("lnk-descargar-cupon-pagos")).click();
		sleep(10000);
		driver.findElement(By.id("inputImporte")).sendKeys("100");
		sleep(2000);
		driver.findElement(By.id("btnDescargar")).click();
		// ===========  FALTA TERMINAR  ===============
		Assert.assertTrue(false);
	
	}
	
	@Test(groups ={ "AutogestionIndividuosWeb","facturacion"})  
	public void Facturacion_Imprimir_cupon_de_pago_POS() throws AWTException, IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("facturaci\u00f3n");
		sleep(10000);
		driver.findElement(By.id("lnk-descargar-cupon-pagos")).click();
		sleep(10000);
		driver.findElement(By.id("inputImporte")).sendKeys("100");
		sleep(2000);
		driver.findElement(By.id("btnDescargar")).click();
		
		// ===========  FALTA TERMINAR  ===============
		Assert.assertTrue(false);
	}
	
	@Test(groups ={ "AutogestionIndividuosWeb","facturacion"}) 
	public void Facturacion_Ver_Facturas_MIX() throws AWTException, IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("facturaci\u00f3n");
		sleep(10000);
		driver.findElement(By.id("lnk-ver-todo-mis-facturas")).click();
		sleep(8000);
		String Fecha = driver.findElement(By.id("itemContainer")).findElements(By.cssSelector(".col-xs-12.col-md-2.item-cell.ng-scope")).get(0).findElement(By.cssSelector(".item-cell-body.padding-left-10-xs.ng-binding")).getText();
		String NumFact = driver.findElement(By.id("itemContainer")).findElements(By.cssSelector(".col-xs-12.col-md-2.item-cell.ng-scope")).get(1).findElement(By.cssSelector(".item-cell-body.padding-left-10-xs.ng-binding")).getText();
		Fecha = Fecha.replace("/", "-");
		String Fecha2 = Fecha;
			if(Fecha.charAt(3)=='0')
				Fecha2= Fecha.substring(0,3)+Fecha.substring(4,Fecha.length());
			if(Fecha.charAt(0)=='0')
				Fecha2= Fecha2.substring(1,Fecha2.length());
		driver.findElement(By.id("itemContainer")).findElement(By.cssSelector(".col-xs-12.col-md-2.item-cell.item-cell-last")).findElement(By.tagName("a")).click();
		sleep(15000);
		boolean exito = false;
		AbrirTab(driver);
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		sleep(5000);
		try {
			driver.switchTo().window(tabs2.get(1));
			String pathDownloads = GetUserDownloadPath();

			driver.get("file:///"+pathDownloads+NumFact+"-"+Fecha2+".pdf");
			exito = true;
			PDF pdf =  new PDF();
			Assert.assertTrue(pdf.ContenidoPDF(pathDownloads+NumFact+"-"+Fecha2+".pdf").contains(NumFact));
			sleep(12000);
			driver.close();
			driver.switchTo().window(tabs2.get(0));
			Assert.assertTrue(exito);
		}catch(Exception ex1) {
			System.out.println("Fallo la descarga de la factura");
			driver.close();
			driver.switchTo().window(tabs2.get(0));
			Assert.assertTrue(false);
			
		}
		
	}
	
	@Test(groups ={ "AutogestionIndividuosWeb","facturacion"}) 
	public void Facturacion_Ver_Facturas_POS() throws AWTException, IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("facturaci\u00f3n");
		sleep(10000);
		driver.findElement(By.id("lnk-ver-todo-mis-facturas")).click();
		sleep(8000);
		String Fecha = driver.findElement(By.id("itemContainer")).findElements(By.cssSelector(".col-xs-12.col-md-2.item-cell.ng-scope")).get(0).findElement(By.cssSelector(".item-cell-body.padding-left-10-xs.ng-binding")).getText();
		String NumFact = driver.findElement(By.id("itemContainer")).findElements(By.cssSelector(".col-xs-12.col-md-2.item-cell.ng-scope")).get(1).findElement(By.cssSelector(".item-cell-body.padding-left-10-xs.ng-binding")).getText();
		Fecha = Fecha.replace("/", "-");
		String Fecha2 = Fecha;
			if(Fecha.charAt(3)=='0')
				Fecha2= Fecha.substring(0,3)+Fecha.substring(4,Fecha.length());
			if(Fecha.charAt(0)=='0')
				Fecha2= Fecha2.substring(1,Fecha2.length());
		driver.findElement(By.id("itemContainer")).findElement(By.cssSelector(".col-xs-12.col-md-2.item-cell.item-cell-last")).findElement(By.tagName("a")).click();
		sleep(15000);
		boolean exito = false;
		AbrirTab(driver);
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		sleep(5000);
		try {
			driver.switchTo().window(tabs2.get(1));
			String pathDownloads = GetUserDownloadPath();
			driver.get("file:///"+pathDownloads+NumFact+"-"+Fecha2+".pdf");
			exito = true;
			PDF pdf =  new PDF();
			Assert.assertTrue(pdf.ContenidoPDF(pathDownloads+NumFact+"-"+Fecha2+".pdf").contains(NumFact));
			sleep(12000);
			driver.close();
			driver.switchTo().window(tabs2.get(0));
			Assert.assertTrue(exito);
		}catch(Exception ex1) {
			System.out.println("Fallo la descarga de la factura");
			driver.close();
			driver.switchTo().window(tabs2.get(0));
			Assert.assertTrue(false);
		}
	}
	
	@Test(groups ={ "AutogestionIndividuosWeb","facturacion"}) 
	public void Facturacion_Alarmas_POS() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("facturaci\u00f3n");
		sleep(10000);
		buscarYClick(driver.findElements(By.cssSelector(".dev-item-menu.list-group-item")),"equals","configurar alarmas");
		sleep(8000);
		driver.findElement(By.className("chkAlarmaDispoSMS")).click();
		sleep(1000);
		driver.findElement(By.className("chkAlarmaProxVtoSMS")).click();
		sleep(1000);
		driver.findElement(By.id("btnGuardar")).click();
		sleep(5000);
		Assert.assertTrue(driver.findElement(By.id("lblMensajeExito")).getText().contains("Modificaste tus alarmas con \u00e9xito"));
	}
	
	@Test(groups ={ "AutogestionIndividuosWeb","facturacion"}) 
	public void Facturacion_Alarmas_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("facturaci\u00f3n");
		sleep(10000);
		buscarYClick(driver.findElements(By.cssSelector(".dev-item-menu.list-group-item")),"equals","configurar alarmas");
		sleep(8000);
		driver.findElement(By.className("chkAlarmaDispoSMS")).click();
		sleep(1000);
		driver.findElement(By.className("chkAlarmaProxVtoSMS")).click();
		sleep(1000);
		driver.findElement(By.id("btnGuardar")).click();
		sleep(5000);
		Assert.assertTrue(driver.findElement(By.id("lblMensajeExito")).getText().contains("Modificaste tus alarmas con \u00e9xito"));
	}
		
	@Test(groups ={ "AutogestionIndividuosWeb","login"}) 
	public void Login_Iniciar_Sesion_con_Linea_Inexistente(){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		driver.get("https://autogestionuat.personal.com.ar");
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.id("idToken1")));
		driver.findElement(By.id("idToken1")).sendKeys("1111223311");
		driver.findElement(By.id("idToken2")).clear();
		driver.findElement(By.id("idToken2")).sendKeys("1234");
		driver.findElement(By.id("loginButton_0")).click();
		sleep(2000);
		System.out.println(driver.findElement(By.className("message")).getText());
		Assert.assertTrue(driver.findElement(By.className("message")).getText().contains("Los datos ingresados son incorrectos"));
		Assert.assertTrue(driver.findElement(By.cssSelector(".fa.alert-message-icon")).isDisplayed());
	}
	
	@Test(groups ={ "AutogestionIndividuosWeb","login"})  
	public void Login_Iniciar_Sesion_con_clave_Incorrecta() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea= String.valueOf(retornaLinea(nombreCaso,archivoLineas));
		driver.get("https://autogestionuat.personal.com.ar");
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.id("idToken1")));
		driver.findElement(By.id("idToken1")).sendKeys(linea);
		driver.findElement(By.id("idToken2")).clear();
		driver.findElement(By.id("idToken2")).sendKeys("1234");
		driver.findElement(By.id("loginButton_0")).click();
		sleep(2000);
		System.out.println(driver.findElement(By.className("message")).getText());
		Assert.assertTrue(driver.findElement(By.className("message")).getText().contains("Los datos ingresados son incorrectos"));
		Assert.assertTrue(driver.findElement(By.cssSelector(".fa.alert-message-icon")).isDisplayed());
	}
	
	@Test(groups ={ "AutogestionIndividuosWeb","login"}) //Agregar linea a excel y sacar c�digo de login del caso
	public void Login_Iniciar_Sesion_con_Linea_PreActiva() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea= String.valueOf(retornaLinea(nombreCaso,archivoLineas));

		driver.get("https://autogestionuat.personal.com.ar");
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.id("idToken1")));
		driver.findElement(By.id("idToken1")).sendKeys(linea);
		driver.findElement(By.id("idToken2")).clear();
		driver.findElement(By.id("idToken2")).sendKeys("1469");
		driver.findElement(By.id("loginButton_0")).click();
		sleep(10000);
		irA("mi l\u00ednea");
		sleep(8000);
		boolean asd = false;
		for (WebElement x : driver.findElements(By.cssSelector(".card.card-front"))) {
			if (x.getText().toLowerCase().contains("predesactiva")) {
				x.isDisplayed();
				asd = true;
				break;
			}
		}
		
		Assert.assertTrue(asd);
	}
	
	@Test(groups ={ "AutogestionIndividuosWeb","facturacion"}) 
	public void Facturacion_Notas_de_Credito_y_Debito_MIX() throws AWTException, IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("facturaci\u00f3n");
		sleep(12000);
		buscarYClick(driver.findElements(By.cssSelector(".dev-item-menu.list-group-item")),"equals","notas de cr\u00e9dito y d\u00e9bito");
		sleep(8000);
		String Fecha = driver.findElement(By.id("itemContainer")).findElements(By.className("table-row")).get(0).findElements(By.cssSelector(".col-xs-7.col-sm-2.col-md-2.col-lg-2")).get(0).findElement(By.tagName("span")).getText();
		String NumFact = driver.findElement(By.id("itemContainer")).findElements(By.className("table-row")).get(0).findElements(By.cssSelector(".col-xs-7.col-sm-2.col-md-2.col-lg-2")).get(1).findElement(By.tagName("span")).getText();
		System.out.println(NumFact);
		Fecha = Fecha.replace("/", "_");
		String Fecha2 = Fecha;
			if(Fecha.charAt(3)=='0')
				Fecha2= Fecha.substring(0,3)+Fecha.substring(4,Fecha.length());
			if(Fecha.charAt(0)=='0')
				Fecha2= Fecha2.substring(1,Fecha2.length());
		System.out.println(Fecha2);
		System.out.println(NumFact+"_"+Fecha2+".pdf");
		driver.findElement(By.className("dev-lnk-descargar")).click();
		sleep(15000);
		boolean exito = false;
		AbrirTab(driver);
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		sleep(5000);
		try {
			driver.switchTo().window(tabs2.get(1));
			String DownloadPath = GetUserDownloadPath();
			driver.get("file:///"+ DownloadPath + NumFact+"_"+Fecha2+".pdf");
			exito = true;
			PDF pdf =  new PDF();
			//Assert.assertTrue(pdf.ContenidoPDF("C:\\Users\\Sofia Chardin\\Downloads\\"+NumFact+"_"+Fecha2+".pdf").contains(NumFact));
			sleep(12000);
			driver.close();
			driver.switchTo().window(tabs2.get(0));
			Assert.assertTrue(exito);
		}catch(Exception ex1) {
			System.out.println("Fallo la descarga de la factura");
			driver.close();
			driver.switchTo().window(tabs2.get(0));
			Assert.assertTrue(false);
		}
	}
	
	@Test(groups ={ "AutogestionIndividuosWeb","facturacion"}) 
	public void Facturacion_Notas_de_Credito_y_Debito_POS() throws AWTException, IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("facturaci\u00f3n");
		sleep(12000);
		buscarYClick(driver.findElements(By.cssSelector(".dev-item-menu.list-group-item")),"equals","notas de cr\u00e9dito y d\u00e9bito");
		sleep(8000);
		String Fecha = driver.findElement(By.id("itemContainer")).findElements(By.className("table-row")).get(0).findElements(By.cssSelector(".col-xs-7.col-sm-2.col-md-2.col-lg-2")).get(0).findElement(By.tagName("span")).getText();
		String NumFact = driver.findElement(By.id("itemContainer")).findElements(By.className("table-row")).get(0).findElements(By.cssSelector(".col-xs-7.col-sm-2.col-md-2.col-lg-2")).get(1).findElement(By.tagName("span")).getText();
		Fecha = Fecha.replace("/", "_");
		String Fecha2 = Fecha;
			if(Fecha.charAt(3)=='0')
				Fecha2= Fecha.substring(0,3)+Fecha.substring(4,Fecha.length());
			if(Fecha.charAt(0)=='0')
				Fecha2= Fecha2.substring(1,Fecha2.length());
		driver.findElement(By.className("dev-lnk-descargar")).click();
		sleep(15000);
		boolean exito = false;
		AbrirTab(driver);
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		sleep(5000);
		try {
			driver.switchTo().window(tabs2.get(1));
			String DownloadPath = GetUserDownloadPath();
			driver.get("file:///"+ DownloadPath + NumFact+"_"+Fecha2+".pdf");
			//driver.get("file:///C:/Users/Sofia%20Chardin/Downloads/"+NumFact+"_"+Fecha2+".pdf");
			exito = true;
			//PDF pdf =  new PDF();
			System.out.println("C:\\"+DownloadPath+NumFact+"_"+Fecha2+".pdf");
			//Assert.assertTrue(pdf.ContenidoPDF("C:\\Users\\Sofia Chardin\\Downloads\\"+NumFact+"_"+Fecha2+".pdf").contains(NumFact));
			sleep(12000);
			driver.close();
			driver.switchTo().window(tabs2.get(0));
			Assert.assertTrue(exito);
		}catch(Exception ex1) {
			System.out.println("Fallo la descarga de la factura");
			driver.close();
			driver.switchTo().window(tabs2.get(0));
			Assert.assertTrue(false);
		}
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","packs"}) 
	public void Comprar_Packs_Compra_de_Packs_con_puntos_Club_MIX () throws IOException {
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("packs");
		sleep(15000);
		List <WebElement> packs = driver.findElement(By.id("collapseTwo")).findElements(By.cssSelector(".list-group-item.li-puntos-club"));
			for(WebElement p : packs ){
				System.out.println(p.getText());
				if(p.getText().toLowerCase().equals("larga distancia internacional")){
					p.click();
				}
			}
		sleep(3000);
		buscarYClick(driver.findElements(By.cssSelector(".panel-heading.titulo-simple")),"equals","minutos");
		sleep(1500);
		driver.findElement(By.id("collapseLargaDistanciaMinutos-pc")).findElement(By.cssSelector(".panel-body.lista-packs")).click();
		sleep(8000);
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-lg.btn-primary.pull-right")),"equals","canjear");
		sleep(120000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".card-body.recarga-exito")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector(".card-body.recarga-exito")).getText().toLowerCase().contains("canjeaste tus puntos con \u00e9xito"));
	}
	
	@Test(groups ={ "AutogestionIndividuosWeb","packs"}) 
	public void Comprar_Packs_Compra_de_Packs_con_puntos_Club_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("ahorros");
		sleep(15000);
		buscarYClick(driver.findElements(By.cssSelector(".card-body.ng-scope")),"equals","packs destacados");
		sleep(15000);
		buscarYClick(driver.findElements(By.cssSelector(".list-group-item.dev-categorias.ng-scope")),"equals","roaming");
		sleep(15000);
		List <WebElement> packs = driver.findElement(By.id("collapseTwo")).findElements(By.cssSelector(".list-group-item.li-puntos-club"));
		for(WebElement p : packs ){
			System.out.println(p.getText());
			if(p.getText().toLowerCase().equals("larga distancia internacional")){
				p.click();
			}
		}
		sleep(3000);
		buscarYClick(driver.findElements(By.cssSelector(".panel-heading.titulo-simple")),"equals","minutos");
		sleep(1500);
		driver.findElement(By.id("collapseLargaDistanciaMinutos-pc")).findElement(By.cssSelector(".panel-body.lista-packs")).click();
		sleep(12000);
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-lg.btn-primary.pull-right")),"equals","canjear");
		sleep(120000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".card-body.recarga-exito")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector(".card-body.recarga-exito")).getText().toLowerCase().contains("canjeaste tus puntos con \u00e9xito"));
	}
	
	@Test(groups ={ "AutogestionIndividuosWeb","facturacion"}) 
	public void Facturacion_Pagar_Factura_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("facturaci\u00f3n");
		driver.findElement(By.id("btnPagarFactura")).click();
		sleep(5000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".section-title")).getText().equals("Medios de pago disponibles"));
		driver.findElement(By.id("lnkPagoTC")).click();
		sleep(5000);
		Assert.assertTrue(driver.findElement(By.id("nroTarjeta")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("txtVto")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("txtCodSeguridad")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("txtCaptcha")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("btnContinuar")).isDisplayed());
		driver.findElement(By.cssSelector(".tpicon.tpicon-flechaizquierda.lnkVolverAindexPagar")).click();
		sleep(5000);
		driver.findElement(By.id("lnkPMCuentas")).click();
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		sleep(5000);
		Assert.assertTrue(driver.getCurrentUrl().equals("https://pagomiscuentas.com/"));
		driver.close();
		driver.switchTo().window(tabs.get(0));
		sleep(5000);
		driver.findElement(By.id("lnkLNKpagos")).click();
		tabs=new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		sleep(5000);
		Assert.assertTrue(driver.getCurrentUrl().equals("https://www.redlink.com.ar/link_pagos_y_cobranzas.html"));
		driver.close();
		driver.switchTo().window(tabs.get(0));
		sleep(5000);
		driver.findElement(By.id("lnkTodoPago")).click();
		tabs=new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		sleep(5000);
		Assert.assertTrue(driver.getCurrentUrl().equals("https://www.todopago.com.ar/"));
		driver.close();
		driver.switchTo().window(tabs.get(0));
	}
	
	@Test(groups ={ "AutogestionIndividuosWeb","facturacion"})  
	public void Facturacion_Pagar_Factura_POS() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("facturaci\u00f3n");
		driver.findElement(By.id("btnPagarFactura")).click();
		sleep(5000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".section-title")).getText().equals("Medios de pago disponibles"));
		driver.findElement(By.id("lnkPagoTC")).click();
		sleep(5000);
		Assert.assertTrue(driver.findElement(By.id("nroTarjeta")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("txtVto")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("txtCodSeguridad")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("txtCaptcha")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("btnContinuar")).isDisplayed());
		driver.findElement(By.cssSelector(".tpicon.tpicon-flechaizquierda.lnkVolverAindexPagar")).click();
		sleep(5000);
		driver.findElement(By.id("lnkPMCuentas")).click();
		ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		sleep(5000);
		Assert.assertTrue(driver.getCurrentUrl().equals("https://pagomiscuentas.com/"));
		driver.close();
		driver.switchTo().window(tabs.get(0));
		sleep(5000);
		driver.findElement(By.id("lnkLNKpagos")).click();
		tabs=new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		sleep(5000);
		Assert.assertTrue(driver.getCurrentUrl().equals("https://www.redlink.com.ar/link_pagos_y_cobranzas.html"));
		driver.close();
		driver.switchTo().window(tabs.get(0));
		sleep(5000);
		driver.findElement(By.id("lnkTodoPago")).click();
		tabs=new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		sleep(5000);
		Assert.assertTrue(driver.getCurrentUrl().equals("https://www.todopago.com.ar/"));
		driver.close();
		driver.switchTo().window(tabs.get(0));
	}

	@Test(groups ={ "AutogestionIndividuosWeb","mis datos"}) 
	public void Mis_Datos_Mi_Perfil_Cambio_de_Domicilio_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		driver.findElement(By.id("tpi-user")).click();
		sleep(3000);
		driver.findElement(By.cssSelector(".tpi-user-link.tpi-fix-micuenta")).click();
		sleep(15000);
		driver.findElement(By.id("lnkDomicilioFacturacion")).click();
		sleep(8000);
		driver.findElement(By.id("btnAction")).click();
		sleep(3000);
		driver.findElement(By.id("inputDni")).sendKeys("33851579");
		Select prov = new Select(driver.findElement(By.id("lstProvincia")));
		prov.selectByVisibleText("Capital Federal y GBA");
		driver.findElement(By.id("inputNumeroCalle")).clear();
		driver.findElement(By.id("inputNumeroCalle")).sendKeys("4682");
		driver.findElement(By.id("btnAction")).click();
		Assert.assertTrue(false);
	}
	
	@Test(groups ={ "AutogestionIndividuosWeb","mis datos"}) 
	public void Mis_Datos_Mi_Perfil_Cambio_de_Domicilio_POS() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		driver.findElement(By.id("tpi-user")).click();
		sleep(3000);
		driver.findElement(By.cssSelector(".tpi-user-link.tpi-fix-micuenta")).click();
		sleep(15000);
		driver.findElement(By.id("lnkDomicilioFacturacion")).click();
		sleep(8000);
		driver.findElement(By.id("btnAction")).click();
		sleep(3000);
		driver.findElement(By.id("inputDni")).sendKeys("21976636");
		Select prov = new Select(driver.findElement(By.id("lstProvincia")));
		prov.selectByVisibleText("Capital Federal y GBA");
		driver.findElement(By.id("inputNumeroCalle")).clear();
		driver.findElement(By.id("inputNumeroCalle")).sendKeys("4682");
		driver.findElement(By.id("btnAction")).click();
		Assert.assertTrue(false);
	}
	
	@Test(groups ={ "AutogestionIndividuosWeb","mis datos"}) 
	public void Mis_Datos_Mi_Perfil_Cambio_de_Domicilio_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		driver.findElement(By.id("tpi-user")).click();
		sleep(3000);
		driver.findElement(By.cssSelector(".tpi-user-link.tpi-fix-micuenta")).click();
		sleep(15000);
		driver.findElement(By.id("lnkDomicilioFacturacion")).click();
		sleep(8000);
		driver.findElement(By.id("btnAction")).click();
		sleep(3000);
		driver.findElement(By.id("inputDni")).sendKeys("30500051163");
		Select prov = new Select(driver.findElement(By.id("lstProvincia")));
		prov.selectByVisibleText("Capital Federal y GBA");
		driver.findElement(By.id("inputNumeroCalle")).clear();
		driver.findElement(By.id("inputNumeroCalle")).sendKeys("4682");
		driver.findElement(By.id("btnAction")).click();
		Assert.assertTrue(false);
	}
	
	@Test(groups ={ "AutogestionIndividuosWeb","mi linea"}) 
	public void Mi_Linea_Capro() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("mi l\u00ednea");
		sleep(10000);
		buscarYClick(driver.findElements(By.cssSelector(".dev-item-menu.list-group-item-full")),"equals","mejorar mi plan");
		sleep(10000);
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-block.btn-primary.btn-lg")),"equals","lo quiero");
		sleep(10000);
		driver.findElement(By.id("llamame-core-number0")).sendKeys("11");
		driver.findElement(By.id("llamame-core-number1")).sendKeys("32465432");
		driver.findElement(By.id("llamame-core-submit")).click();
		sleep(5000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".llamame-br-label1")).getText().equals("Te estamos llamando"));
	}

	@Test(groups ={ "AutogestionIndividuosWeb","consumos"}) 
	public void Mis_Consumos_Modificar_Cuota_de_Datos() throws FileNotFoundException, UnsupportedEncodingException, JSchException, SftpException, IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		
		//Genero el archivo para realizar el consumo de 1mb
		String fecha = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
		String consumo="1048576";
		String mitadconsumo="524288";
		int x = (int)(Math.random() * 99999 + 10000);
		int z = (int)(Math.random() * 999999999 + 100000000);
		int y = (int)(Math.random() * 999999 + 100000);
	
		String echo="|||||0||3|1||||" + fecha + "105100|0|54" + linea + "|||||2000||||" + consumo + "|" + mitadconsumo + "|" + mitadconsumo + "||||||||||||||||||||||||||||||||||"
				+ "3000|||||100.71.211.86||65.170.31.0|65.170.31.0|||||datos.personal.comID" + x + "|||0400|72234||||722-34-12345-41315||||||||||||||" + z + "|||" + consumo + "|"
						+ "180|" + consumo + "|" + consumo + "|||||";
		
		String filename="GPRS_" + fecha + "_" + y + ".unl";
		PrintWriter writer = new PrintWriter(filename, "UTF-8");
		writer.println(echo);
		writer.close();
		
		//Realizo conexion ftp para subir el archivo
		JSch jsch = new JSch();
		Session session = jsch.getSession("adptftp","10.75.198.17", 22);
		session.setPassword("CA3DS2_21");
		java.util.Properties config = new java.util.Properties(); 
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		session.connect();
		ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");
		sftpChannel.connect();
		sftpChannel.put(filename, "/home/onip/offcdr_mount/offcdr_nfs/cbpadpt451/input/cbs/data/scan_file/"+filename);
		sftpChannel.disconnect();
		session.disconnect();
		
		//Borro el archivo
		File file = new File(filename);
		file.delete();
		
		//Loguin en la web
		
		loginPorLinea(linea);

		irA("consumos");
		Assert.assertTrue(driver.findElement(By.cssSelector(".text-brand-cyanoscuro.ng-binding.ng-scope")).getText().equals("Internet X Dia"));
		buscarYClick(driver.findElements(By.cssSelector(".ng-scope")),"equals","modificar la cuota diaria");
		sleep(10000);
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-lg.btn-primary.pull-right")),"equals","seleccionar");
		sleep(10000);
		String cuota = driver.findElement(By.cssSelector(".col-xs-12.col-lg-offset-1.col-lg-10.col-lg-offset-1")).findElements(By.cssSelector(".col-xs-12.col-lg-5.text-center.margin-top-20")).get(1).findElement(By.cssSelector(".text-destacado.text-destacado-xs.text-primary")).getText();
		Assert.assertTrue(cuota.equals("Internet 200MB por Dia"));
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-lg.btn-primary.corregirBtn")),"equals","confirmar");
		sleep(10000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".text-primary.ng-scope")).getText().equals("�Felicitaciones!"));
		driver.findElement(By.cssSelector(".tpicon.tpicon-flechaizquierda")).click();
		sleep(10000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".text-brand-cyanoscuro.ng-binding.ng-scope")).getText().equals("Internet 200MB por Dia"));
		buscarYClick(driver.findElements(By.cssSelector(".ng-scope")),"equals","modificar la cuota diaria");
		sleep(10000);
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-lg.btn-primary.pull-right")),"equals","seleccionar");
		sleep(10000);
		cuota = driver.findElement(By.cssSelector(".col-xs-12.col-lg-offset-1.col-lg-10.col-lg-offset-1")).findElements(By.cssSelector(".col-xs-12.col-lg-5.text-center.margin-top-20")).get(1).findElement(By.cssSelector(".text-destacado.text-destacado-xs.text-primary")).getText();
		Assert.assertTrue(cuota.equals("Internet X Dia"));
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-lg.btn-primary.corregirBtn")),"equals","confirmar");
		sleep(10000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".ng-binding")).getText().equals("La cuota Internet X Dia fue activada con \u00e9xito"));
		driver.findElement(By.cssSelector(".tpicon.tpicon-flechaizquierda")).click();
		sleep(10000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".text-brand-cyanoscuro.ng-binding.ng-scope")).getText().equals("Internet X Dia"));
		}

	@Test(groups ={ "AutogestionIndividuosWeb","facturacion"}) 
	public void Facturacion_Compras_Realizadas_Descargar_Comprobante_MIX() throws IOException {
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("facturaci\u00f3n");
		sleep(10000);
		buscarYClick(driver.findElements(By.cssSelector(".dev-item-menu.list-group-item")),"equals","compras realizadas");
		sleep(7000);
		driver.findElement(By.cssSelector(".tpicon.tpicon-descargar")).click();
		Assert.assertTrue(false);
	}
	
	@Test(groups ={ "AutogestionIndividuosWeb","facturacion"})  
	public void Facturacion_Compras_Realizadas_Descargar_Comprobante_POS() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("facturaci\u00f3n");
		sleep(10000);
		buscarYClick(driver.findElements(By.cssSelector(".dev-item-menu.list-group-item")),"equals","compras realizadas");
		sleep(7000);
		driver.findElement(By.cssSelector(".tpicon.tpicon-descargar")).click();
		Assert.assertTrue(false);
	}
	
	@Test(groups ={ "AutogestionIndividuosWeb","facturacion"}) 
	public void Facturacion_Compras_Realizadas_Descargar_Comprobante_PRE() throws IOException {
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("facturaci\u00f3n");
		sleep(10000);
		buscarYClick(driver.findElements(By.cssSelector(".dev-item-menu.list-group-item")),"equals","compras realizadas");
		sleep(7000);
		driver.findElement(By.cssSelector(".tpicon.tpicon-descargar")).click();
		Assert.assertTrue(false);
	}
	
	@Test(groups ={ "AutogestionIndividuosWeb","facturacion"}) 
	public void Facturacion_Informar_un_Pago_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("facturaci\u00f3n");
		sleep(10000);
		driver.findElement(By.id("btnInformarPago")).click();
		sleep(10000);
		driver.findElement(By.id("btnInformar")).click();
		sleep(10000);
		Assert.assertTrue(false);
	}

	@Test(groups ={ "AutogestionIndividuosWeb","facturacion"})  
	public void Facturacion_Informar_un_Pago_POS() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("facturaci\u00f3n");
		sleep(10000);
		driver.findElement(By.id("btnInformarPago")).click();
		sleep(10000);
		driver.findElement(By.id("btnInformar")).click();
		sleep(10000);
		Assert.assertTrue(false);
	}
	
	@Test (groups ={ "AutogestionIndividuosWeb","recargas"}) 
	public void Recargas_Recarga_con_puntos_Club_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("recargas");
		sleep(15000);
		buscarYClick(driver.findElements(By.cssSelector(".re-text")),"equals","con puntos club");
		sleep(5000);
		buscarYClick(driver.findElements(By.cssSelector(".panel-title")),"equals","recargas");
		buscarYClick(driver.findElements(By.cssSelector(".ng-binding")),"equals","cr\u00e9dito $10");
		sleep(10000);
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-lg.btn-primary.pull-right")),"equals","canjear");
		sleep(15000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".col-xs-12.col-sm-6.col-md-6.col-lg-6.text-center-xs")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector(".col-xs-12.col-sm-6.col-md-6.col-lg-6.text-center-xs")).getText().toLowerCase().contains("canjeaste tus puntos con \u00e9xito"));
		
		
	}
	
	@Test(groups ={ "AutogestionIndividuosWeb","recargas"}) 
	public void Recargas_Recarga_con_puntos_Club_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("recargas");
		sleep(15000);
		buscarYClick(driver.findElements(By.cssSelector(".re-text")),"equals","con puntos club");
		sleep(5000);
		buscarYClick(driver.findElements(By.cssSelector(".panel-title")),"equals","recargas");
		buscarYClick(driver.findElements(By.cssSelector(".ng-binding")),"equals","cr\u00e9dito $10");
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-lg.btn-primary.pull-right")),"equals","canjear");
		sleep(15000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".col-xs-12.col-sm-6.col-md-6.col-lg-6.text-center-xs")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector(".col-xs-12.col-sm-6.col-md-6.col-lg-6.text-center-xs")).getText().toLowerCase().contains("canjeaste tus puntos con \u00e9xito"));
	}
	
	@Test(groups ={ "AutogestionIndividuosWeb","mis datos"}) 
	public void Mis_Datos_Mi_Perfil_Ir_a_club_Personal() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		//Linea adherida a club
		String linea = retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);		
		
		driver.findElement(By.id("tpi-user")).click();
		sleep(3000);
		buscarYClick(driver.findElements(By.cssSelector(".tpi-user-link")),"equals","canjear puntos");
		sleep(15000);
		Assert.assertTrue(driver.getCurrentUrl().equals("http://clubuat.personal.com.ar:8090/fe/#/filtros/a-mi-alcance/"));
	}
	

	@Test (groups ={ "Robustez","Inputs"})
	public void DIGITAL_WEB_IND_FACTURACION_FACTURA_ONLINE_MIX_EMAIL_FORMATO_INVALIDO_con_el_caracter_enie() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("facturaci\u00f3n");
		IngresarFacturaOnline("\u00f1");
		Assert.assertTrue(driver.findElement(By.id("lblErrorEmail")).findElement(By.cssSelector(".control-label.dev-alert-danger")).getText().toLowerCase().contains("el formato de email no es correcto"));
	}
	
	@Test (groups ={ "Robustez","Inputs"})
	public void DIGITAL_WEB_IND_FACTURACION_FACTURA_ONLINE_MIX_EMAIL_FORMATO_INVALIDO_sin_caracteres_luego_del_arroba() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("facturaci\u00f3n");
		IngresarFacturaOnline("asd@");
		Assert.assertTrue(driver.findElement(By.id("lblErrorEmail")).findElement(By.cssSelector(".control-label.dev-alert-danger")).getText().toLowerCase().contains("el formato de email no es correcto"));
	}
	
	@Test (groups ={ "Robustez","Inputs"})
	public void DIGITAL_WEB_IND_FACTURACION_FACTURA_ONLINE_MIX_EMAIL_FORMATO_INVALIDO_sin_caracteres_antes_del_arroba() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("facturaci\u00f3n");
		IngresarFacturaOnline("@gmail");
		Assert.assertTrue(driver.findElement(By.id("lblErrorEmail")).findElement(By.cssSelector(".control-label.dev-alert-danger")).getText().toLowerCase().contains("el formato de email no es correcto"));
	}
	@Test (groups ={ "Robustez","Inputs"})
	public void DIGITAL_WEB_IND_FACTURACION_FACTURA_ONLINE_MIX_EMAIL_FORMATO_INVALIDO_sin_caracteres_luego_del_punto_posterior_al_arroba() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("facturaci\u00f3n");
		IngresarFacturaOnline("asd@gmail.");
		Assert.assertTrue(driver.findElement(By.id("lblErrorEmail")).findElement(By.cssSelector(".control-label.dev-alert-danger")).getText().toLowerCase().contains("el formato de email no es correcto"));
	}
	
	@Test (groups ={ "Robustez","Inputs"})
	public void DIGITAL_WEB_IND_FACTURACION_FACTURA_ONLINE_MIX_EMAIL_FORMATO_INVALIDO_sin_arroba() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("facturaci\u00f3n");
		IngresarFacturaOnline("asdgmail.com");
		Assert.assertTrue(driver.findElement(By.id("lblErrorEmail")).findElement(By.cssSelector(".control-label.dev-alert-danger")).getText().toLowerCase().contains("el formato de email no es correcto"));
	}
	
	@Test (groups ={ "Robustez","Inputs"})
	public void DIGITAL_WEB_IND_FACTURACION_FACTURA_ONLINE_MIX_EMAIL_FORMATO_INVALIDO_sin_punto() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("facturaci\u00f3n");
		IngresarFacturaOnline("asdgmailcom");
		Assert.assertTrue(driver.findElement(By.id("lblErrorEmail")).findElement(By.cssSelector(".control-label.dev-alert-danger")).getText().toLowerCase().contains("el formato de email no es correcto"));
	}
	
	@Test (groups ={ "Robustez","Inputs"})
	public void DIGITAL_WEB_IND_FACTURACION_FACTURA_ONLINE_MIX_EMAIL_FORMATO_INVALIDO_sin_dato() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginPorLinea(linea);

		irA("facturaci\u00f3n");
		IngresarFacturaOnline("");
		Assert.assertTrue(driver.findElement(By.id("lblErrorEmail")).findElement(By.cssSelector(".control-label.dev-alert-danger")).getText().toLowerCase().contains("el formato de email no es correcto"));
	}
	
	
	@Test(groups ={ "Robustez","Inputs"})
	public void Login_Linea_Input_Numero_Caracter_Letra(){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		driver.get("https://autogestionuat.personal.com.ar");
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.id("idToken1")));
		driver.findElement(By.id("idToken1")).sendKeys("a");
		sleep(2000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".help-block.line.form-text.text-muted.tp-login-error")).getText().equals("Ingres\u00e1 s\u00f3lo n\u00fameros"));
	}
	
	@Test(groups ={ "Robustez","Inputs"})
	public void Login_Linea_Input_Numero_Caracter_Combinado(){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		driver.get("https://autogestionuat.personal.com.ar");
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.id("idToken1")));
		driver.findElement(By.id("idToken1")).sendKeys("1134A");
		sleep(2000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".help-block.line.form-text.text-muted.tp-login-error")).getText().equals("Debes ingresar una l\u00ednea de Personal"));
	}
	
	@Test(groups ={ "Robustez","Inputs"})
	public void Login_Linea_Input_Numero_Caracter_NoEstand(){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		driver.get("https://autogestionuat.personal.com.ar");
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.id("idToken1")));
		driver.findElement(By.id("idToken1")).sendKeys("*");
		sleep(2000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".help-block.line.form-text.text-muted.tp-login-error")).isDisplayed());
		driver.findElement(By.id("idToken1")).sendKeys("+");
		sleep(2000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".help-block.line.form-text.text-muted.tp-login-error")).isDisplayed());
		driver.findElement(By.id("idToken1")).sendKeys("'");
		sleep(2000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".help-block.line.form-text.text-muted.tp-login-error")).isDisplayed());
	}
	
	@Test(groups ={ "Robustez","Inputs"})
	public void Login_Linea_Input_Numero_Caracter_Estand(){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		driver.get("https://autogestionuat.personal.com.ar");
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.id("idToken1")));
		driver.findElement(By.id("idToken1")).sendKeys(".");
		sleep(2000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".help-block.line.form-text.text-muted.tp-login-error")).isDisplayed());
		driver.findElement(By.id("idToken1")).sendKeys(",");
		sleep(2000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".help-block.line.form-text.text-muted.tp-login-error")).isDisplayed());
	}
	
	@Test(groups ={ "Robustez","Inputs"})
	public void Login_Linea_Input_Numero_Mas11Digitos(){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		driver.get("https://autogestionuat.personal.com.ar");
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.id("idToken1")));
		driver.findElement(By.id("idToken1")).sendKeys("11627451655");
		sleep(2000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".help-block.line.form-text.text-muted.tp-login-error")).getText().equals("L\u00ednea incorrecta. Verific\u00e1 los datos ingresados"));
	}
	
	@Test(groups ={ "Robustez","Inputs"})
	public void Login_Linea_Input_Numero_Menos11Digitos(){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		driver.get("https://autogestionuat.personal.com.ar");
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.id("idToken1")));
		driver.findElement(By.id("idToken1")).sendKeys("11627451");
		sleep(2000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".help-block.line.form-text.text-muted.tp-login-error")).getText().equals("Tu n\u00famero de l\u00ednea debe tener al menos 10 d\u00edgitos"));
	}
	
	@Test(groups ={ "Robustez","Inputs"})
	public void Login_Linea_Input_Numero_Obligatorio(){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		driver.get("https://autogestionuat.personal.com.ar");
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.id("idToken2")));
		driver.findElement(By.id("idToken2")).sendKeys("1234");
		sleep(2000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".help-block.line.form-text.text-muted.tp-login-error")).getText().equals("Debes ingresar una l\u00ednea de Personal"));
		Assert.assertFalse(driver.findElement(By.id("loginButton_0")).isEnabled());
	}
	
	@Test(groups ={ "Robustez","Inputs"}) 
	public void Login_Clave_Input_Numero_Caracter_Letra(){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		driver.get("https://autogestionuat.personal.com.ar");
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.id("idToken1")));
		driver.findElement(By.id("idToken1")).sendKeys("1162735148");
		sleep(2000);
		driver.findElement(By.id("idToken2")).sendKeys("a");
		sleep(2000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".help-block.pin.form-text.text-muted.tp-login-error")).getText().equals("Ingres\u00e1 s\u00f3lo n\u00fameros"));
	}
	
	@Test(groups ={ "Robustez","Inputs"})
	public void Login_Clave_Input_Numero_Caracter_Combinado(){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		driver.get("https://autogestionuat.personal.com.ar");
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.id("idToken1")));
		driver.findElement(By.id("idToken1")).sendKeys("1162735148");
		sleep(2000);
		driver.findElement(By.id("idToken2")).sendKeys("12a");
		sleep(2000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".help-block.pin.form-text.text-muted.tp-login-error")).getText().equals("Ingres\u00e1 s\u00f3lo n\u00fameros"));
	}
	
	@Test(groups ={ "Robustez","Inputs"})
	public void Login_Clave_Input_Numero_Caracter_NoEstand(){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		driver.get("https://autogestionuat.personal.com.ar");
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.id("idToken1")));
		driver.findElement(By.id("idToken1")).sendKeys("1162735148");
		sleep(2000);
		driver.findElement(By.id("idToken2")).sendKeys("*");
		sleep(2000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".help-block.pin.form-text.text-muted.tp-login-error")).isDisplayed());
		driver.findElement(By.id("idToken2")).sendKeys("+");
		sleep(2000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".help-block.pin.form-text.text-muted.tp-login-error")).isDisplayed());
		driver.findElement(By.id("idToken2")).sendKeys("'");
		sleep(2000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".help-block.pin.form-text.text-muted.tp-login-error")).isDisplayed());
	}
	
	@Test(groups ={ "Robustez","Inputs"})
	public void Login_Clave_Input_Numero_Caracter_Estand(){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		driver.get("https://autogestionuat.personal.com.ar");
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.id("idToken1")));
		driver.findElement(By.id("idToken1")).sendKeys("1162735148");
		sleep(2000);
		driver.findElement(By.id("idToken2")).sendKeys(".");
		sleep(2000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".help-block.pin.form-text.text-muted.tp-login-error")).isDisplayed());
		driver.findElement(By.id("idToken2")).sendKeys(",");
		sleep(2000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".help-block.pin.form-text.text-muted.tp-login-error")).isDisplayed());
	}
	
	@Test(groups ={ "Robustez","Inputs"})
	public void Login_Clave_Input_Numero_Max10Digitos(){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		driver.get("https://autogestionuat.personal.com.ar");
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.id("idToken1")));
		driver.findElement(By.id("idToken1")).sendKeys("11627451655");
		sleep(2000);
		driver.findElement(By.id("idToken2")).sendKeys("012345678913452");
		sleep(2000);
		String number=driver.findElement(By.id("idToken2")).getAttribute("value");
		Assert.assertEquals(number.length(), 10);
	}
	
	@Test(groups ={ "Robustez","Inputs"})
	public void Login_Clave_Input_Numero_Menos4Digitos(){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		driver.get("https://autogestionuat.personal.com.ar");
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.id("idToken1")));
		driver.findElement(By.id("idToken1")).sendKeys("1162735148");
		sleep(2000);
		driver.findElement(By.id("idToken2")).sendKeys("123");
		sleep(2000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".help-block.pin.form-text.text-muted.tp-login-error")).getText().equals("Ingres\u00e1 al menos 4 n\u00fameros"));
	}
	
	@Test(groups ={ "Robustez","Inputs"}) 
	public void Login_Clave_Input_Numero_Obligatorio(){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		driver.get("https://autogestionuat.personal.com.ar");
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.id("idToken1")));
		driver.findElement(By.id("idToken1")).sendKeys("1162735148");
		sleep(2000);
		Assert.assertFalse(driver.findElement(By.id("loginButton_0")).isEnabled());
		
	}
	
	@Test(groups ={ "Robustez","Inputs"}) 
	public void Login_Generar_CambiarClave_Numeros_Repetidos(){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		driver.get("https://autogestionuat.personal.com.ar");
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.id("idToken1")));
		driver.findElement(By.id("idToken1")).sendKeys("1162735148");
		sleep(2000);
		driver.findElement(By.id("password-generation-page")).click();
		sleep(10000);
		driver.findElement(By.id("txtSms")).sendKeys("5384");
		sleep(2000);
		driver.findElement(By.id("txtPin")).sendKeys("1111");
		sleep(2000);
		driver.findElement(By.id("btnCambiarPin")).click();
		sleep(2000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".form-group.has-error")).isDisplayed());
	}
	
	@Test(groups ={ "Robustez","Inputs"}) 
	public void Login_Generar_CambiarClave_Numeros_Consecutivos(){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		driver.get("https://autogestionuat.personal.com.ar");
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.id("idToken1")));
		driver.findElement(By.id("idToken1")).sendKeys("1162735148");
		sleep(2000);
		driver.findElement(By.id("password-generation-page")).click();
		sleep(10000);
		driver.findElement(By.id("txtSms")).sendKeys("5384");
		sleep(2000);
		driver.findElement(By.id("txtPin")).sendKeys("1234");
		sleep(2000);
		driver.findElement(By.id("btnCambiarPin")).click();
		sleep(2000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".form-group.has-error")).isDisplayed());
	}
	
	@Test(groups ={ "Robustez","Inputs"}) 
	public void Login_Generar_CambiarClave_Numeros_Linea(){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		driver.get("https://autogestionuat.personal.com.ar");
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.id("idToken1")));
		driver.findElement(By.id("idToken1")).sendKeys("1162735148");
		sleep(2000);
		driver.findElement(By.id("password-generation-page")).click();
		sleep(10000);
		driver.findElement(By.id("txtSms")).sendKeys("5384");
		sleep(2000);
		driver.findElement(By.id("txtPin")).sendKeys("5148");
		sleep(2000);
		driver.findElement(By.id("btnCambiarPin")).click();
		sleep(2000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".form-group.has-error")).isDisplayed());
	}
	
	@Test(groups ={ "Robustez","Inputs"}) 
	public void Login_Generar_CambiarClave_3Digitos(){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		driver.get("https://autogestionuat.personal.com.ar");
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.id("idToken1")));
		driver.findElement(By.id("idToken1")).sendKeys("1162735148");
		sleep(2000);
		driver.findElement(By.id("password-generation-page")).click();
		sleep(10000);
		driver.findElement(By.id("txtSms")).sendKeys("5384");
		sleep(2000);
		driver.findElement(By.id("txtPin")).sendKeys("195");
		sleep(2000);
		driver.findElement(By.id("btnCambiarPin")).click();
		sleep(2000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".form-group.has-error")).isDisplayed());
	}
	
	@Test(groups ={ "Robustez","Inputs"}) 
	public void Login_Generar_CambiarClave_11Digitos(){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		driver.get("https://autogestionuat.personal.com.ar");
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.id("idToken1")));
		driver.findElement(By.id("idToken1")).sendKeys("1162735148");
		sleep(2000);
		driver.findElement(By.id("password-generation-page")).click();
		sleep(10000);
		driver.findElement(By.id("txtSms")).sendKeys("5384");
		sleep(2000);
		driver.findElement(By.id("txtPin")).sendKeys("012345678913452");
		sleep(2000); 
		String number=driver.findElement(By.id("txtPin")).getAttribute("value");
		Assert.assertEquals(number.length(), 10); 
	}
	
	@Test(groups ={ "Robustez","Inputs"}) 
	public void Login_Generar_CambiarClave_letras(){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		driver.get("https://autogestionuat.personal.com.ar");
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.id("idToken1")));
		driver.findElement(By.id("idToken1")).sendKeys("1162735148");
		sleep(2000);
		driver.findElement(By.id("password-generation-page")).click();
		sleep(10000);
		driver.findElement(By.id("txtSms")).sendKeys("5384");
		sleep(2000);
		driver.findElement(By.id("txtPin")).sendKeys("letras");
		sleep(2000); 
		String number=driver.findElement(By.id("txtPin")).getAttribute("value");
		Assert.assertEquals(number.length(), 0); 
	}
	
	@Test(groups ={ "Robustez","Inputs"}) 
	public void Login_Generar_CambiarClave_CaracteresCombinados(){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		driver.get("https://autogestionuat.personal.com.ar");
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.id("idToken1")));
		driver.findElement(By.id("idToken1")).sendKeys("1162735148");
		sleep(2000);
		driver.findElement(By.id("password-generation-page")).click();
		sleep(10000);
		driver.findElement(By.id("txtSms")).sendKeys("5384");
		sleep(2000);
		driver.findElement(By.id("txtPin")).sendKeys("14ab");
		sleep(2000); 
		String number=driver.findElement(By.id("txtPin")).getAttribute("value");
		Assert.assertEquals(number.length(), 2); 
	}
	
	@Test(groups ={ "Robustez","Inputs"}) 
	public void Login_Generar_CambiarClave_CaracteresNoEstandar(){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		driver.get("https://autogestionuat.personal.com.ar");
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.id("idToken1")));
		driver.findElement(By.id("idToken1")).sendKeys("1162735148");
		sleep(2000);
		driver.findElement(By.id("password-generation-page")).click();
		sleep(10000);
		driver.findElement(By.id("txtSms")).sendKeys("5384");
		sleep(2000);
		driver.findElement(By.id("txtPin")).sendKeys("'");
		sleep(2000); 
		String number=driver.findElement(By.id("txtPin")).getAttribute("value");
		Assert.assertEquals(number.length(), 0);
		driver.findElement(By.id("txtPin")).sendKeys("*");
		sleep(2000); 
		number=driver.findElement(By.id("txtPin")).getAttribute("value");
		Assert.assertEquals(number.length(), 0); 	
	}
	
	@Test(groups ={ "Robustez","Inputs"}) 
	public void Login_Generar_CambiarClave_CaracteresEstandar(){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		driver.get("https://autogestionuat.personal.com.ar");
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.id("idToken1")));
		driver.findElement(By.id("idToken1")).sendKeys("1162735148");
		sleep(2000);
		driver.findElement(By.id("password-generation-page")).click();
		sleep(10000);
		driver.findElement(By.id("txtSms")).sendKeys("5384");
		sleep(2000);
		driver.findElement(By.id("txtPin")).sendKeys(",");
		sleep(2000); 
		String number=driver.findElement(By.id("txtPin")).getAttribute("value");
		Assert.assertEquals(number.length(), 0);
		driver.findElement(By.id("txtPin")).sendKeys(".");
		sleep(2000); 
		number=driver.findElement(By.id("txtPin")).getAttribute("value");
		Assert.assertEquals(number.length(), 0); 	
	}
	
	@Test(groups ={ "Robustez","Inputs"}) 
	public void Login_Generar_CambiarClave_NumeroObligatorio(){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		driver.get("https://autogestionuat.personal.com.ar");
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.id("idToken1")));
		driver.findElement(By.id("idToken1")).sendKeys("1162735148");
		sleep(2000);
		driver.findElement(By.id("password-generation-page")).click();
		sleep(10000);
		driver.findElement(By.id("txtSms")).sendKeys("5384");
		sleep(2000);
		driver.findElement(By.id("btnCambiarPin")).click();
		sleep(2000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".form-group.has-error")).isDisplayed());
	}
	
	@Test(groups ={ "Robustez","Inputs"}) 
	public void Login_Generar_CambiarClave_CodigoSMSIncorrecto(){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		driver.get("https://autogestionuat.personal.com.ar");
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.id("idToken1")));
		driver.findElement(By.id("idToken1")).sendKeys("1162735148");
		sleep(2000);
		driver.findElement(By.id("password-generation-page")).click();
		sleep(10000);
		driver.findElement(By.id("txtSms")).sendKeys("5384");
		sleep(2000);
		driver.findElement(By.id("txtPin")).sendKeys("4104");
		sleep(2000);
		driver.findElement(By.id("btnCambiarPin")).click();
		sleep(5000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".alert-text")).isDisplayed());
	}
}
