package Tests;

import java.awt.AWTException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.ClickAction;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import PageMetodos.Metodos;

public class WebLineaVariable extends Metodos{
	
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
	public void Mi_Linea_Transferencia_de_Llamadas_POS(){
		imagen = "Mi_Linea_Transferencia_de_Llamadas_POS";
		LoginPorLineaVariable("1166248383");
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
	
	@Test(groups = "AutogestionIndividuosWeb")
	public void Mi_Línea_Destransferencia_de_Llamadas_POS(){
		imagen = "Mi_Línea_Destransferencia_de_Llamadas_POS";
		LoginPorLineaVariable("1166248383");
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
	
	@Test(groups = "AutogestionIndividuosWeb")
	public void MI_Linea_Roaming_y_LDI_habilitado_MIX(){
		imagen = "MI_Linea_Roaming_y_LDI_habilitado_MIX";
		LoginPorLineaVariable("3496652414");
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
	
	@Test(groups = "AutogestionIndividuosWeb")
	public void MI_Linea_Roaming_y_LDI_habilitado_POS(){
		imagen = "MI_Linea_Roaming_y_LDI_habilitado_POS";
		LoginPorLineaVariable("3794601129");
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
	
	@Test(groups = "AutogestionIndividuosWeb") 
	public void MI_Linea_Roaming_y_LDI_habilitado_PRE(){
		imagen = "MI_Linea_Roaming_y_LDI_habilitado_PRE";
		LoginPorLineaVariable("1164520012");
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
	
	@Test(groups = "AutogestionIndividuosWeb") 
	public void Mi_Linea_Numeros_Amigos_para_Hablar_SMS_Activacion_MIX(){
		imagen = "Mi_Linea_Numeros_Amigos_para_Hablar_SMS_Activación_MIX";
		String ln = "1162735148";
		ln = ln.substring(ln.length()-8);
		LoginPorLineaVariable("1164461283");
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
	
	@Test(groups = "AutogestionIndividuosWeb") 
	public void Mi_Linea_Numeros_Amigos_para_Hablar_SMS_Activacion_PRE(){
		imagen = "Mi_Linea_Numeros_Amigos_para_Hablar_SMS_Activación_PRE";
		String ln = "1162735148";
		ln = ln.substring(ln.length()-8);
		LoginPorLineaVariable("1164480623");
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
	
	@Test(groups = "AutogestionIndividuosWeb") 
	public void Mi_Linea_Numeros_Amigos_para_Hablar_SMS_Eliminacion_MIX(){
		imagen = "Mi_Linea_Numeros_Amigos_para_Hablar_SMS_Eliminacion_MIX";
		String ln = "1162735148";
		ln = ln.substring(ln.length()-8);
		LoginPorLineaVariable("1164480623");
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
	
	@Test(groups = "AutogestionIndividuosWeb") 
	public void Mi_Linea_Numeros_Amigos_para_Hablar_SMS_Eliminacion_PRE(){
		imagen = "Mi_Linea_Numeros_Amigos_para_Hablar_SMS_Eliminacion_PRE";
		String ln = "1162735148";
		ln = ln.substring(ln.length()-8);
		LoginPorLineaVariable("1164480623");
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
	
	@Test(groups = "AutogestionIndividuosWeb") 
	public void Mi_Linea_Seguimineto_de_Gestiones_MIX(){
		imagen = "Mi_Linea_Seguimineto_de_Gestiones_MIX";
		LoginPorLineaVariable("1162755344");
		irA("mi l\u00ednea");
		sleep(12000);
		buscarYClick(driver.findElements(By.cssSelector(".dev-item-menu.list-group-item-full")),"equals","seguimiento de gestiones");
		sleep(8000);
		Assert.assertTrue(driver.findElement(By.id("itemContainer")).isDisplayed());
		driver.findElement(By.id("imgVerDetalle")).click();
		sleep(5000);
		Assert.assertTrue(false);
	}
	
	@Test(groups = "AutogestionIndividuosWeb") 
	public void Mi_Linea_Seguimineto_de_Gestiones_PRE(){
		imagen = "Mi_Linea_Seguimineto_de_Gestiones_PRE";
		LoginPorLineaVariable("1164405558");
		irA("mi l\u00ednea");
		sleep(12000);
		buscarYClick(driver.findElements(By.cssSelector(".dev-item-menu.list-group-item-full")),"equals","seguimiento de gestiones");
		sleep(8000);
		Assert.assertTrue(driver.findElement(By.id("itemContainer")).isDisplayed());
		driver.findElement(By.id("imgVerDetalle")).click();
		sleep(5000);
		Assert.assertTrue(false);
	}
	
	@Test(groups = "AutogestionIndividuosWeb") 
	public void Mi_Linea_Seguimineto_de_Gestiones_POS(){
		imagen = "Mi_Linea_Seguimineto_de_Gestiones_POS";
		LoginPorLineaVariable("1166248383");
		irA("mi l\u00ednea");
		sleep(12000);
		buscarYClick(driver.findElements(By.cssSelector(".dev-item-menu.list-group-item-full")),"equals","seguimiento de gestiones");
		sleep(10000);
		Assert.assertTrue(driver.findElement(By.id("itemContainer")).isDisplayed());
		driver.findElement(By.id("imgVerDetalle")).click();
		sleep(5000);
		Assert.assertTrue(false);
	}
	
	@Test(groups = "AutogestionIndividuosWeb") 
	public void Mis_Consumos_DataSharing_Alta_MIX(){
		imagen = "Mis_Consumos_DataSharing_Alta_MIX";
		String num = "64480754";
		LoginPorLineaVariable("1164493210");
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
	
	@Test(groups = "AutogestionIndividuosWeb") 
	public void Mis_Consumos_DataSharing_Modificacion_MIX(){
		imagen = "Mis_Consumos_DataSharing_Modificacion_MIX";
		LoginPorLineaVariable("1164493210");
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
	
	@Test(groups = "AutogestionIndividuosWeb") 
	public void Mis_Consumos_DataSharing_Baja_MIX(){
		imagen = "Mis_Consumos_DataSharing_Baja_MIX";
		LoginPorLineaVariable("1164493210");
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
	
	@Test(groups = "AutogestionIndividuosWeb") 
	public void Facturacion_Imprimir_cupon_de_pago_MIX(){
		imagen = "Facturacion_Imprimir_cupon_de_pago_MIX";
		LoginPorLineaVariable("1162733281");
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
	
	@Test(groups = "AutogestionIndividuosWeb") 
	public void Facturacion_Imprimir_cupon_de_pago_POS() throws AWTException{
		imagen = "Facturacion_Imprimir_cupon_de_pago_POS";
		LoginPorLineaVariable("3413130145");
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
	
	/*@Test(groups = "AutogestionIndividuosWeb") 
	public void Facturacion_Ver_Facturas_MIX() throws AWTException{
		imagen ="Facturacion_Ver_Facturas_MIX";
		LoginPorLineaVariable("1162733281");
		irA("facturaci\u00f3n");
		sleep(10000);
		driver.findElement(By.id("lnk-ver-todo-mis-facturas")).click();
		sleep(8000);
		String Fecha = driver.findElement(By.id("itemContainer")).findElements(By.cssSelector(".col-xs-12.col-md-2.item-cell.ng-scope")).get(0).findElement(By.cssSelector(".item-cell-body.padding-left-10-xs.ng-binding")).getText();
		String NumFact = driver.findElement(By.id("itemContainer")).findElements(By.cssSelector(".col-xs-12.col-md-2.item-cell.ng-scope")).get(1).findElement(By.cssSelector(".item-cell-body.padding-left-10-xs.ng-binding")).getText();
		Fecha = Fecha.replace("/", "-");
		String Fecha2 = Fecha;
		if(Fecha.charAt(3)=='0')
			Fecha2= Fecha.substring(0,2)+Fecha.substring(4,Fecha.length());
		if(Fecha.charAt(0)=='0')
			Fecha2= Fecha2.substring(1,Fecha2.length());
		System.out.println(NumFact);
		System.out.println(Fecha);
		driver.findElement(By.id("itemContainer")).findElement(By.cssSelector(".col-xs-12.col-md-2.item-cell.item-cell-last")).findElement(By.tagName("a")).click();
		sleep(15000);
		boolean exito = false;
		AbrirTab(driver);
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		sleep(5000);
		try {
			driver.switchTo().window(tabs2.get(1));
			driver.get("file:///C:/Users/Sofia%20Chardin/Downloads/"+NumFact+Fecha+".pdf");
			exito = true;
			LeerPDF pdfTextParserObj = new LeerPDF();
			//String pdfToText = pdfTextParserObj.pdftoText(args[0]);
			String pdfToText = pdfTextParserObj.pdftoText("C://Users//Sofia Chardin//Downloads//"+NumFact+Fecha+".pdf");
			System.out.println(pdfToText);
		}catch(Exception ex1) {
			driver.close();
		    driver.switchTo().window(tabs2.get(0));
		}
		
	}*/
	
	@Test(groups = "AutogestionIndividuosWeb") 
	public void Facturacion_Pagar_Factura_MIX(){
		imagen = "Facturacion_Pagar_Factura_MIX";
		LoginPorLineaVariable("1162733281");
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
	
	@Test(groups = "AutogestionIndividuosWeb") 
	public void Facturacion_Pagar_Factura_POS(){
		imagen = "Facturacion_Pagar_Factura_POS";
		LoginPorLineaVariable("1165990597");
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
	
} 
