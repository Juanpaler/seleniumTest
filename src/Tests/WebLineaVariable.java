package Tests;

import java.awt.AWTException;
import java.io.File;
import java.io.FileNotFoundException;
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
import org.openqa.selenium.interactions.ClickAction;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
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
	public void Mi_Linea_Destransferencia_de_Llamadas_POS(){
		imagen = "Mi_Lï¿½nea_Destransferencia_de_Llamadas_POS";
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
		imagen = "Mi_Linea_Numeros_Amigos_para_Hablar_SMS_Activaciï¿½n_MIX";
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
		imagen = "Mi_Linea_Numeros_Amigos_para_Hablar_SMS_Activaciï¿½n_PRE";
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
	
	@Test(groups = "AutogestionIndividuosWeb") 
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
			driver.get("file:///C:/Users/Sofia%20Chardin/Downloads/"+NumFact+"-"+Fecha2+".pdf");
			exito = true;
			PDF pdf =  new PDF();
			Assert.assertTrue(pdf.ContenidoPDF("C:\\Users\\Sofia Chardin\\Downloads\\"+NumFact+"-"+Fecha2+".pdf").contains(NumFact));
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
	
	@Test(groups = "AutogestionIndividuosWeb") 
	public void Facturacion_Ver_Facturas_POS() throws AWTException{
		imagen ="Facturacion_Ver_Facturas_POS";
		LoginPorLineaVariable("1165990597");
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
			driver.get("file:///C:/Users/Sofia%20Chardin/Downloads/"+NumFact+"-"+Fecha2+".pdf");
			exito = true;
			PDF pdf =  new PDF();
			Assert.assertTrue(pdf.ContenidoPDF("C:\\Users\\Sofia Chardin\\Downloads\\"+NumFact+"-"+Fecha2+".pdf").contains(NumFact));
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
	
	@Test(groups = "AutogestionIndividuosWeb") 
	public void Facturacion_Alarmas_POS(){
		imagen = "Facturacion_Alarmas_POS";
		LoginPorLineaVariable("1166248383");
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
	
	@Test(groups = "AutogestionIndividuosWeb") 
	public void Facturacion_Alarmas_MIX(){
		imagen = "Facturacion_Alarmas_MIX";
		LoginPorLineaVariable("1162733281");
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
		
	@Test(groups = "AutogestionIndividuosWeb") 
	public void Login_Iniciar_Sesion_con_Linea_Inexistente(){
		imagen = "Login_Iniciar_Sesion_con_Linea_Inexistente";
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
	
	@Test(groups = "AutogestionIndividuosWeb") 
	public void Login_Iniciar_Sesion_con_clave_Incorrecta(){
		imagen = "Login_Iniciar_Sesion_con_clave_Incorrecta";
		driver.get("https://autogestionuat.personal.com.ar");
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.id("idToken1")));
		driver.findElement(By.id("idToken1")).sendKeys("1166248383");
		driver.findElement(By.id("idToken2")).clear();
		driver.findElement(By.id("idToken2")).sendKeys("1234");
		driver.findElement(By.id("loginButton_0")).click();
		sleep(2000);
		System.out.println(driver.findElement(By.className("message")).getText());
		Assert.assertTrue(driver.findElement(By.className("message")).getText().contains("Los datos ingresados son incorrectos"));
		Assert.assertTrue(driver.findElement(By.cssSelector(".fa.alert-message-icon")).isDisplayed());
	}
	
	@Test(groups = "AutogestionIndividuosWeb") 
	public void Login_Iniciar_Sesion_con_Linea_PreActiva(){
		imagen = "Login_Iniciar_Sesion_con_clave_Incorrecta";
		driver.get("https://autogestionuat.personal.com.ar");
		sleep(15000);
		driver.switchTo().frame(cambioFrame(driver, By.id("idToken1")));
		driver.findElement(By.id("idToken1")).sendKeys("1162645152");
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
	
	@Test(groups = "AutogestionIndividuosWeb") 
	public void Facturacion_Notas_de_Credito_y_Debito_MIX() throws AWTException{
		imagen = "Facturacion_Notas_de_Credito_y_Debito_MIX";
		LoginPorLineaVariable("1161120234");
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
			driver.get("file:///C:/Users/Sofia%20Chardin/Downloads/"+NumFact+"_"+Fecha2+".pdf");
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
	
	@Test(groups = "AutogestionIndividuosWeb") 
	public void Facturacion_Notas_de_Credito_y_Debito_POS() throws AWTException{
		imagen = "Facturacion_Notas_de_Credito_y_Debito_POS";
		LoginPorLineaVariable("1161120234");
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
			driver.get("file:///C:/Users/Sofia%20Chardin/Downloads/"+NumFact+"_"+Fecha2+".pdf");
			exito = true;
			PDF pdf =  new PDF();
			System.out.println("C:\\Users\\Sofia Chardin\\Downloads\\"+NumFact+"_"+Fecha2+".pdf");
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
	
	@Test(groups = "AutogestionIndividuosWeb") 
	public void Comprar_Packs_Compra_de_Packs_con_puntos_Club_MIX (){
		imagen = "Comprar_Packs_Compra_de_Packs_con_puntos_Club_MIX";
		LoginPorLineaVariable("1168829219");
		irA("packs");
		sleep(8000);
		List <WebElement> packs = driver.findElement(By.id("collapseTwo")).findElements(By.cssSelector(".list-group-item.li-puntos-club"));
			for(WebElement p : packs ){
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
		Assert.assertTrue(false);
	}
	
	@Test(groups = "AutogestionIndividuosWeb") 
	public void Comprar_Packs_Compra_de_Packs_con_puntos_Club_PRE(){
		imagen = "Comprar_Packs_Compra_de_Packs_con_puntos_Club_PRE";
		LoginPorLineaVariable("1164473518");
		irA("ahorros");
		sleep(15000);
		buscarYClick(driver.findElements(By.cssSelector(".card-body.ng-scope")),"equals","packs destacados");
		sleep(15000);
		buscarYClick(driver.findElements(By.cssSelector(".list-group-item.dev-categorias.ng-scope")),"equals","roaming");
		sleep(15000);
		List <WebElement> packs = driver.findElement(By.id("collapseTwo")).findElements(By.cssSelector(".list-group-item.li-puntos-club"));
			for(WebElement p : packs ){
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
		Assert.assertTrue(false);
	}
	
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
	
	@Test(groups = "AutogestionIndividuosWeb") 
	public void Mi_Linea_Capro(){
		imagen = "Mi_Linea_Capro";
		LoginPorLineaVariable("1164520012");
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
	
	@Test(groups = "AutogestionIndividuosWeb") 
	public void Mis_Consumos_Modificar_Cuota_de_Datos() throws FileNotFoundException, UnsupportedEncodingException, JSchException, SftpException{
		imagen = "Mis_Consumos_Modificar_Cuota_de_Datos";
		
		//Genero el archivo para realizar el consumo de 1mb
		String linea="1152292615";
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
		LoginPorLineaVariable(linea);
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
		Assert.assertTrue(driver.findElement(By.cssSelector(".text-primary.ng-scope")).getText().equals("¡Felicitaciones!"));
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

	@Test(groups = "AutogestionIndividuosWeb") 
	public void Facturacion_Compras_Realizadas_Descargar_Comprobante_MIX() {
		imagen = "Facturacion_Compras_Realizadas_Descargar_Comprobante_MIX";
		LoginPorLineaVariable("1164599468");
		irA("facturaci\u00f3n");
		sleep(10000);
		buscarYClick(driver.findElements(By.cssSelector(".dev-item-menu.list-group-item")),"equals","compras realizadas");
		sleep(7000);
		driver.findElement(By.cssSelector(".tpicon.tpicon-descargar")).click();
	    Assert.assertTrue(false);
	}
	
	@Test(groups = "AutogestionIndividuosWeb") 
	public void Facturacion_Compras_Realizadas_Descargar_Comprobante_POS() {
		imagen = "Facturacion_Compras_Realizadas_Descargar_Comprobante_POS";
		LoginPorLineaVariable("1164599450");
		irA("facturaci\u00f3n");
		sleep(10000);
		buscarYClick(driver.findElements(By.cssSelector(".dev-item-menu.list-group-item")),"equals","compras realizadas");
		sleep(7000);
		driver.findElement(By.cssSelector(".tpicon.tpicon-descargar")).click();
	    Assert.assertTrue(false);
	}
	
	@Test(groups = "AutogestionIndividuosWeb") 
	public void Facturacion_Compras_Realizadas_Descargar_Comprobante_PRE() {
		imagen = "Facturacion_Compras_Realizadas_Descargar_Comprobante_PRE";
		LoginPorLineaVariable("1162745165");
		irA("facturaci\u00f3n");
		sleep(10000);
		buscarYClick(driver.findElements(By.cssSelector(".dev-item-menu.list-group-item")),"equals","compras realizadas");
		sleep(7000);
		driver.findElement(By.cssSelector(".tpicon.tpicon-descargar")).click();
	    Assert.assertTrue(false);
	}
	
	@Test(groups = "AutogestionIndividuosWeb") 
	public void Facturacion_Informar_un_Pago_MIX() {
		imagen = "Facturacion_Informar_un_Pago_MIX";
		LoginPorLineaVariable("1162733281");
		irA("facturaci\u00f3n");
		sleep(10000);
		driver.findElement(By.id("btnInformarPago")).click();
		sleep(10000);
		driver.findElement(By.id("btnInformar")).click();
		sleep(10000);
		Assert.assertTrue(false);
	}

	@Test(groups = "AutogestionIndividuosWeb") 
	public void Facturacion_Informar_un_Pago_POS() {
		imagen = "Facturacion_Informar_un_Pago_POS";
		LoginPorLineaVariable("1165990597");
		irA("facturaci\u00f3n");
		sleep(10000);
		driver.findElement(By.id("btnInformarPago")).click();
		sleep(10000);
		driver.findElement(By.id("btnInformar")).click();
		sleep(10000);
		Assert.assertTrue(false);
	}
	
	@Test(groups = "AutogestionIndividuosWeb") 
	public void Recargas_Recarga_con_puntos_Club_MIX() {
		imagen = "Recargas_Recarga_con_puntos_Club_MIX";
		LoginPorLineaVariable("1168829219");
		irA("recargas");
		sleep(15000);
		buscarYClick(driver.findElements(By.cssSelector(".re-text")),"equals","con puntos club");
		sleep(5000);
		buscarYClick(driver.findElements(By.cssSelector(".panel-title")),"equals","recargas");
		buscarYClick(driver.findElements(By.cssSelector(".ng-binding")),"equals","cr\u00e9dito $10");
		sleep(10000);
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-lg.btn-primary.pull-right")),"equals","canjear");
		Assert.assertTrue(false);	
	}
	
	@Test(groups = "AutogestionIndividuosWeb") 
	public void Recargas_Recarga_con_puntos_Club_PRE() {
		imagen = "Recargas_Recarga_con_puntos_Club_PRE";
		LoginPorLineaVariable("1164473518");
		irA("recargas");
		sleep(15000);
		buscarYClick(driver.findElements(By.cssSelector(".re-text")),"equals","con puntos club");
		sleep(5000);
		buscarYClick(driver.findElements(By.cssSelector(".panel-title")),"equals","recargas");
		buscarYClick(driver.findElements(By.cssSelector(".ng-binding")),"equals","cr\u00e9dito $10");
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-lg.btn-primary.pull-right")),"equals","canjear");
		sleep(10000);
		Assert.assertTrue(false);	
	}
	
	
} 
