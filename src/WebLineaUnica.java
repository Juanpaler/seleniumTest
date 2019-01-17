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
		loginPorLinea("MIX");
		irA("packs");
		sleep(15000);
		driver.findElement(By.id("btnConsultarDesk")).click();
		sleep(15000);
		driver.findElement(By.cssSelector(".item-cell-body.text-center")).findElement(By.cssSelector(".tpicon.font-30.tpicon-descargar"));
		Assert.assertTrue(false);
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