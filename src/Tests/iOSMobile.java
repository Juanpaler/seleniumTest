package Tests;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;

import PageMetodos.Metodos;

import java.net.URL;
import java.net.MalformedURLException;
import java.util.logging.Level;


public class iOSMobile extends Metodos {

	private String reportDirectory = "reports";
    private String reportFormat = "xml";
    private String testName = "Untitled";
    private String nroUDID = "c2ced461f2d136211a630c1f06668a1771abd2b2";
    protected IOSDriver<IOSElement> driver = null;
    DesiredCapabilities dc = new DesiredCapabilities();
    
    
	@BeforeClass (alwaysRun = true)
	public void init() {
		dc.setCapability("reportDirectory", reportDirectory);
		dc.setCapability("reportFormat", reportFormat);
		dc.setCapability("testName", testName);
		dc.setCapability(MobileCapabilityType.UDID, nroUDID);
		dc.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.personal.misconsumos.uat");
	}
    
    @BeforeMethod (alwaysRun = true)
    public void before() throws MalformedURLException {
    	driver = new IOSDriver<>(new URL("http://localhost:4723/wd/hub"), dc);
        driver.setLogLevel(Level.INFO);
        try {
        	driver.findElement(By.id("Aceptar")).click();
        } catch(Exception e) {}        
    }
    
    //@AfterMethod (alwaysRun = true)
    public void after() {
    	sleep(5000);
    	int menu = 0;
    	while(((!driver.findElement(By.className("UIANavigationBar")).getText().contains("Mi Personal"))) && menu < 5) {
        	driver.findElement(By.id("Atr\u00e1s")).click();
        	menu++;
        }	
    	sleep(3000);
    	driver.findElement(By.id("SideMenu")).click();
    	sleep(5000);
    	driver.findElement(By.xpath("//*[@text='Cerrar Sesi\u00f3n']")).click();
    	sleep(5000);
    	driver.quit();
    }
    
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 0)
    public void Comprar_Packs_Compra_de_Packs_MIX() {
    	boolean msj = false;
    	loginPorLineaMobileiOS(driver, lineaMIX);
    	scrollAndClickiOS(driver, "id", "Pagos, Recargas y Packs");
    	scrollAndClickiOS(driver, "id", "Con Cr\u00e9dito");
    	scrollAndClickiOS(driver, "id", "ROAMING");
    	scrollAndClickiOS(driver, "xpath", "//*[@class='UIAStaticText'][contains(text(),'Pack Roaming 40 SMS Limitrofes')]");
    	scrollAndClickiOS(driver, "id", "COMPRAR");
    	scrollAndClickiOS(driver, "id", "ACEPTAR");
    	sleep(5000);
		for (WebElement x : driver.findElements(By.className("UIAStaticText"))) {
			if (x.getText().contains("La compra se realiz\u00f3 con \u00e9xito"))
				msj = true;
		}
    	scrollAndClickiOS(driver, "id", "ACEPTAR");
    	sleep(5000);
    	Assert.assertTrue(msj);
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 1)
    public void Comprar_Packs_Compra_de_Packs_PRE() {
    	boolean msj = false;
    	loginPorLineaMobileiOS(driver, lineaPre);
    	scrollAndClickiOS(driver, "id", "Recargas y Packs");
    	scrollAndClickiOS(driver, "id", "Con Cr\u00e9dito");
    	scrollAndClickiOS(driver, "id", "ROAMING");
    	scrollAndClickiOS(driver, "xpath", "//*[@class='UIAStaticText'][contains(text(),'Pack Roaming 40 SMS Limitrofes')]");
    	scrollAndClickiOS(driver, "id", "COMPRAR");
    	scrollAndClickiOS(driver, "id", "ACEPTAR");
    	sleep(5000);
		for (WebElement x : driver.findElements(By.className("UIAStaticText"))) {
			if (x.getText().contains("La compra se realiz\u00f3 con \u00e9xito"))
				msj = true;
		}
    	scrollAndClickiOS(driver, "id", "ACEPTAR");
    	sleep(5000);
    	Assert.assertTrue(msj);
    }
}