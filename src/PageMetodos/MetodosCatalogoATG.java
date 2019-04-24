package PageMetodos;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class MetodosCatalogoATG extends Utils {	
  	
	static WebDriver driver;

	public static WebDriver setup(){
		System.setProperty("webdriver.chrome.driver", "Chromedriver.exe");
		ChromeOptions Options = new ChromeOptions();
		Options.addArguments("start-maximized");
		Options.addArguments("disable-infobars");
		driver = new ChromeDriver(Options);
		return driver;
	}
	
	public void tomarCaptura(WebDriver driver, String imageName , String rutaCaptura) {
		// Directorio donde quedaran las imagenes guardadas
		File directory;
		directory = new File(rutaCaptura);
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
	
	public void loginCatalogoATG() {
		
		loadConfig();
		String userTp = config.getProperty("usertp");
		String passTp = config.getProperty("passtp");

		driver.get("http://sr-docker-xt01.corp.cablevision.com.ar:8887/catalogo/#!/ ");		
		
		WaitForElement(driver, "id", "username_tmp");
		driver.findElement(By.id("username_tmp")).sendKeys(userTp);
		WaitForElement(driver, "id", "password");
		driver.findElement(By.id("password")).sendKeys(passTp);		
		driver.findElement(By.id("ingresar-button")).click();				
	}
		
	public void logoutCatalogoATG() {		
		
		WaitForElement(driver, "cssSelector", ".logout");
		driver.findElement(By.cssSelector(".logout")).click();
		WaitForElement(driver, "xpath", "//button[ng-click='global.aceptar()']");
		List<WebElement> botones =  driver.findElements(By.xpath("//button[@ng-click='global.aceptar()']"));		
		
		for(int i=0; i< botones.size();i++)
		{
			System.out.println(botones.get(i).getLocation().x +" - "+ botones.get(i).getLocation().y);
			if(botones.get(i).getLocation().x>0 || botones.get(i).getLocation().y>0) 
			{
				botones.get(i).click();
			}
		}
	}
		
} 