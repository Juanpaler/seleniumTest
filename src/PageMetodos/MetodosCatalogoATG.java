package PageMetodos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MetodosCatalogoATG extends Utils {	
  	
	static WebDriver driver;

	public Properties testConfig = new Properties();
    InputStream testConfigInput = null;
    public static OutputStream configOutput = null;
    
    public void loadTestConfig(){
        try{
        	testConfigInput = new FileInputStream("TestsConfig/configATG.properties");
            testConfig.load(testConfigInput);          
        } catch(Exception e){
        	System.out.println("Error cargando configuración\n" + e.getMessage());
        }
    }
    
    public void saveTestConfig(String key, String value){
        try{
            configOutput = new FileOutputStream("TestsConfig/configATG.properties");
            testConfig.setProperty(key, value);                
            testConfig.store(configOutput, null);
        } catch(Exception e){
        	System.out.println("Error guardando configuración\n" + e.getMessage());
        }
    }
	
	public static WebDriver setup(){
		System.setProperty("webdriver.chrome.driver", "Chromedriver.exe");
		String fileDownloadPath =  System.getProperty("user.home") +"\\Downloads\\";
		Map<String, Object> prefsMap = new HashMap<String, Object>();
		prefsMap.put("profile.default_content_settings.popups", 0);
		prefsMap.put("download.default_directory", fileDownloadPath);   
		ChromeOptions option = new ChromeOptions();
		option.setExperimentalOption("prefs", prefsMap);
		option.addArguments("--test-type");
		option.addArguments("--disable-extensions");
		option.addArguments("start-maximized");
		option.addArguments("disable-infobars");
		driver = new ChromeDriver(option);
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

		driver.get("http://sr-docker-xt01.corp.cablevision.com.ar:8888/catalogo/#!/ ");		
		
		WaitForElement(driver, "id", "username_tmp");
		driver.findElement(By.id("username_tmp")).sendKeys(userTp);
		WaitForElement(driver, "id", "password");
		driver.findElement(By.id("password")).sendKeys(passTp);		
		driver.findElement(By.id("ingresar-button")).click();				
	}
	
	
	public void buscarProductoPorId(String id, Boolean click) {
		
		WaitForElement(driver, "xpath", "//*[@id='panel_table_productos']/article[1]/table/thead/tr/th[8]/div/button");
		sleep(2000);
		driver.findElement(By.xpath("//*[@id='panel_table_productos']/article[1]/table/thead/tr/th[8]/div/button")).click();		
		WaitForElement(driver, "xpath", "//*[@id='panel_table_productos']/article[1]/table/thead/tr/th[8]/div/ul/div[7]/div/input");
		sleep(1000);
		driver.findElement(By.xpath("//*[@id='panel_table_productos']/article[1]/table/thead/tr/th[8]/div/ul/div[7]/div/input")).sendKeys(id);	
		WaitForElement(driver, "xpath", "//*[@id='panel_table_productos']/article[1]/table/tbody/tr/td[1]/div/label");
		sleep(1000);
		driver.findElement(By.xpath("//*[@id='panel_table_productos']/article[1]/table/tbody/tr/td[1]/div/label")).click();
		WaitForElement(driver, "xpath", "//*[@id='panel_table_productos']/article[2]/div/div[2]/button[2]");
		sleep(1000);
		if(click)
		{
			driver.findElement(By.xpath("//*[@id='panel_table_productos']/article[2]/div/div[2]/button[2]")).click();
		}
	}

	public void buscarPromocionPorId(String id, Boolean click) {
		
		WaitForElement(driver, "xpath", "//*[@id='table_collapse']/div[2]/div[1]/div/div[11]/button");
		sleep(1000);
		driver.findElement(By.xpath("//*[@id='table_collapse']/div[2]/div[1]/div/div[11]/button")).click();	
		
		WaitForElement(driver, "xpath", "//*[@id='table_collapse']/div[2]/div[1]/div/div[11]/ul/li[6]/div/input");
		
		sleep(1000);
		driver.findElement(By.xpath("//*[@id='table_collapse']/div[2]/div[1]/div/div[11]/ul/li[6]/div/input")).clear();	
		driver.findElement(By.xpath("//*[@id='table_collapse']/div[2]/div[1]/div/div[11]/ul/li[6]/div/input")).sendKeys(id);	
		
		WaitForElement(driver, "xpath", "//*[@id='table_collapse']/div[2]/div[2]/div/div/div[1]/div/label");
		
		sleep(1000);
		driver.findElement(By.xpath("//*[@id='table_collapse']/div[2]/div[2]/div/div/div[1]/div/label")).click();
		
		WaitForElement(driver, "xpath", "//*[@id='panel_table_promo']/article/div/div[2]/button[2]");
		
		sleep(1000);
		if(click)
		{
			driver.findElement(By.xpath("//*[@id='panel_table_promo']/article/div/div[2]/button[2]")).click();
		}
	}
		
	public void logoutCatalogoATG() {		
		
		WaitForElement(driver, "cssSelector", ".logout");
		driver.findElement(By.cssSelector(".logout")).click();
		WaitForElement(driver, "xpath", "//button[ng-click='global.aceptar()']");
		List<WebElement> botones =  driver.findElements(By.xpath("//button[@ng-click='global.aceptar()']"));
		WebElement boton = GetElementoVisible(botones);
		boton.click();
	}
	
	
		
} 