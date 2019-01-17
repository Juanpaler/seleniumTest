import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
		String ar = null;
		ln = ln.substring(ln.length()-8);
		System.out.println(ln);
		LoginPorLineaVariable("1164461283");
		irA("mi l\u00ednea");
		sleep(12000);
		buscarYClick(driver.findElements(By.cssSelector(".card-footer")),"equals","gestionar n\u00fameros gratis");
		sleep(8000);
		driver.findElement(By.id("divListNroSMS")).findElement(By.cssSelector(".text-destacado.text-destacado-lg")).click();
		sleep(5000);
		driver.findElement(By.id("editSms")).findElement(By.id("inputCarct")).sendKeys("11");
		driver.findElement(By.id("editSms")).findElement(By.id("inputNumb")).sendKeys(ln);
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-lg.btn-primary")),"equals","guardar");
		sleep(8000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".alert.alert-dismissable.alert-success.ng-binding")).getText().toLowerCase().contains(" registraste el n\u00famero gratis con \u00e9xito"));
		}
	
	
} 