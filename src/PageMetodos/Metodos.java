package PageMetodos;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

public class Metodos {
	
	static WebDriver driver;
	public static String lineaMIX = "1162735148";
	public static String lineaPre = "1162745165";
	public static String lineaPos = "1145642605";
	
	
	public static WebDriver setup(){
		System.setProperty("webdriver.chrome.driver", "Chromedriver.exe");
		ChromeOptions Options = new ChromeOptions();
		Options.addArguments("start-maximized");
		driver = new ChromeDriver(Options);
		return driver;
	}
		
	public static void sleep(int miliseconds) {
		try {Thread.sleep(miliseconds);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	}
	
	public void loginPorLinea(String tipoDeLinea) {
		driver.get("https://autogestionuat.personal.com.ar");
		driver.findElement(By.id("modal-ingresar")).click();
		sleep(4000);
		String parentWindowHandler = driver.getWindowHandle();
		String subWindowHandler = null;
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()) {
			subWindowHandler = iterator.next();
		}
		driver.switchTo().window(subWindowHandler);
		driver.findElement(By.id("linea-numero")).clear();
		sleep(5000);
		switch(tipoDeLinea) {
		case "MIX":
			driver.findElement(By.id("linea-numero")).sendKeys(lineaMIX);
			break;
		case "Pre":
			driver.findElement(By.id("linea-numero")).sendKeys(lineaPre);
			break;
		case "Pos":
			driver.findElement(By.id("linea-numero")).sendKeys(lineaPos);
			break;
		}
		driver.findElement(By.id("btn-login")).click();;
		sleep(25000);
		driver.findElement(By.id("idToken2")).clear();
		driver.findElement(By.id("idToken2")).sendKeys("1469");
		driver.findElement(By.id("loginButton_0")).click();
		driver.switchTo().window(parentWindowHandler);
		sleep(20000);
	}
	
	public void LoginPorLineaVariable(String sLinea){
		driver.get("https://autogestionuat.personal.com.ar");
		driver.findElement(By.id("modal-ingresar")).click();
		sleep(4000);
		String parentWindowHandler = driver.getWindowHandle();
		String subWindowHandler = null;
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()) {
			subWindowHandler = iterator.next();
		}
		driver.switchTo().window(subWindowHandler);
		driver.findElement(By.id("linea-numero")).clear();
		sleep(5000);
		driver.findElement(By.id("linea-numero")).sendKeys(sLinea);
		driver.findElement(By.id("btn-login")).click();;
		sleep(25000);
		driver.findElement(By.id("idToken2")).clear();
		driver.findElement(By.id("idToken2")).sendKeys("1469");
		driver.findElement(By.id("loginButton_0")).click();
		driver.switchTo().window(parentWindowHandler);
		sleep(20000);
	}
	
	public void buscarYClick(List <WebElement> elements, String match, String texto) {
		sleep(2000);
		switch (match) {
		case "contains":
			for (WebElement x : elements) {
				if (x.getText().toLowerCase().contains(texto)) {
					x.click();
					break;
				}
			}
			break;
		case "equals":
			for (WebElement x : elements) {
				if (x.getText().toLowerCase().equals(texto)) {
					x.click();
					break;
				}
			}
			break;
		}
		sleep(5000);
	}
	
	public void selectByText(WebElement element, String data){
		Select select = new Select(element);
		select.selectByVisibleText(data);
		sleep(2000);
	}
	
	public void irA(String sMenu) {
		sleep(3000);
		List<WebElement> wMenu = driver.findElement(By.id("ul_menusolapas")).findElements(By.className("dev-solapa"));
		for (WebElement wAux : wMenu) {
			if (wAux.getText().toLowerCase().contains(sMenu.toLowerCase())) {
				wAux.click();
				break;
			}
		}
		sleep(5000);
	}
	
	public int getIndexFrame(WebDriver driver, By byForElement) { 
		int index = 0;
		driver.switchTo().defaultContent();
		List<WebElement> frames = driver.findElements(By.tagName("iframe"));
		for(WebElement frame : frames) {
			try {
				driver.switchTo().frame(frame);
				driver.findElement(byForElement).getText(); 
				driver.findElement(byForElement).isDisplayed(); 
				driver.switchTo().defaultContent();
				return index;
			} catch(NoSuchElementException noSuchElemExcept) {
				index++;
				driver.switchTo().defaultContent();
			}
		}
		return -1; 
	}
	
	public WebElement cambioFrame(WebDriver driver, By byForElement) {
		driver.switchTo().defaultContent();
		List<WebElement> frames = driver.findElements(By.tagName("iframe"));
		try {
			return frames.get(getIndexFrame(driver, byForElement));
		} catch (ArrayIndexOutOfBoundsException iobExcept) {
			System.out.println("Elemento no encontrado en ningun frame.");
			return null;
		}
	}
	
	public boolean cambiarClave(String nuevaClave) {
		boolean cambioDeClave = false;
		driver.findElement(By.id("tpi-user")).click();
		sleep(3000);
		driver.findElement(By.cssSelector(".tpi-user-link.tpi-fix-micuenta")).click();
		sleep(15000);
		driver.findElement(By.id("lnkCambioClave")).click();
		sleep(15000);
		driver.findElement(By.id("inputclaveanterior")).sendKeys("1469");
		driver.findElement(By.id("inputclave")).sendKeys(nuevaClave);
		driver.findElement(By.id("inputrepetirclave")).sendKeys(nuevaClave);
		driver.findElement(By.id("btnAceptarCambio")).click();
		sleep(5000);
		if (driver.findElement(By.cssSelector(".card-body.padding-bottom-20")).getText().equalsIgnoreCase("El cambio de clave fue tomado correctamente."))
			cambioDeClave = true;
		driver.findElement(By.id("tpi-user")).click();
		sleep(3000);
		driver.findElement(By.cssSelector(".tpi-user-link.tpi-fix-micuenta")).click();
		sleep(10000);
		driver.findElement(By.id("lnkCambioClave")).click();
		sleep(10000);
		driver.findElement(By.id("inputclaveanterior")).sendKeys(nuevaClave);
		driver.findElement(By.id("inputclave")).sendKeys("1469");
		driver.findElement(By.id("inputrepetirclave")).sendKeys("1469");
		driver.findElement(By.id("btnAceptarCambio")).click();
		return cambioDeClave;
	}
	
	public void obligarclick(WebElement element) {	
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+element.getLocation().y+")");
	    element.click();
	}
	
	public void tomarCaptura(WebDriver driver, String imageName) {
		// Directorio donde quedaran las imagenes guardadas
		File directory;
		directory = new File("IMG");
		try {
			if (directory.isDirectory()) {
				// Toma la captura de imagen
				File imagen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				// Mueve el archivo a la carga especificada con el respectivo nombre
				FileUtils.copyFile(imagen, new File(directory.getAbsolutePath() + "\\" + imageName + ".png"));
			} else {
				// Se lanza la excepcion cuando no encuentre el directorio
				throw new IOException("ERROR : La ruta especificada no es un directorio!");
			}
		} catch (IOException e) {
			// Impresion de Excepciones
			e.printStackTrace();
		}
	}
}