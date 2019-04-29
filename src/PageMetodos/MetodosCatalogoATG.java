package PageMetodos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
	
	
	public void buscarProductoPorId(String id) {
		
		WaitForElement(driver, "xpath", "//*[@id='panel_table_productos']/article[1]/table/thead/tr/th[8]/div/button");
		sleep(1000);
		driver.findElement(By.xpath("//*[@id='panel_table_productos']/article[1]/table/thead/tr/th[8]/div/button")).click();		
		WaitForElement(driver, "xpath", "//*[@id='panel_table_productos']/article[1]/table/thead/tr/th[8]/div/ul/div[7]/div/input");
		sleep(1000);
		driver.findElement(By.xpath("//*[@id='panel_table_productos']/article[1]/table/thead/tr/th[8]/div/ul/div[7]/div/input")).sendKeys(id);	
		WaitForElement(driver, "xpath", "//*[@id='panel_table_productos']/article[1]/table/tbody/tr/td[1]/div/label");
		sleep(1000);
		driver.findElement(By.xpath("//*[@id='panel_table_productos']/article[1]/table/tbody/tr/td[1]/div/label")).click();
		WaitForElement(driver, "xpath", "//*[@id='panel_table_productos']/article[2]/div/div[2]/button[2]");
		sleep(1000);
		driver.findElement(By.xpath("//*[@id='panel_table_productos']/article[2]/div/div[2]/button[2]")).click();			
	}

		
	public void logoutCatalogoATG() {		
		
		WaitForElement(driver, "cssSelector", ".logout");
		driver.findElement(By.cssSelector(".logout")).click();
		WaitForElement(driver, "xpath", "//button[ng-click='global.aceptar()']");
		List<WebElement> botones =  driver.findElements(By.xpath("//button[@ng-click='global.aceptar()']"));		
		
		for(int i=0; i< botones.size();i++)
		{
			//System.out.println(botones.get(i).getLocation().x +" - "+ botones.get(i).getLocation().y);
			if(botones.get(i).getLocation().x>0 || botones.get(i).getLocation().y>0) 
			{
				botones.get(i).click();
			}
		}
	}
	
	public WebElement GetElementoVisible(List<WebElement> elementos) {		
		
		int indiceElemento = 0;
		for(int i=0; i< elementos.size();i++)
		{
			//System.out.println(botones.get(i).getLocation().x +" - "+ botones.get(i).getLocation().y);
			if(elementos.get(i).getLocation().x > 0 && elementos.get(i).getLocation().y > 0) 
			{
				indiceElemento = i;
			}
		}
		return elementos.get(indiceElemento);
	}
	
	
		
} 