package Tests;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.testng.annotations.*;

import PageMetodos.Metodos;

import java.net.URL;
import java.net.MalformedURLException;

import java.util.logging.Level;


public class iOSMobile extends Metodos {

	private String reportDirectory = "reports";
    private String reportFormat = "xml";
    private String testName = "Untitled";
    protected IOSDriver<IOSElement> driver = null;
    DesiredCapabilities dc = new DesiredCapabilities();
    
    
    @BeforeClass
    public void init() {
        dc.setCapability("reportDirectory", reportDirectory);
        dc.setCapability("reportFormat", reportFormat);
        dc.setCapability("testName", testName);
        dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        dc.setCapability(MobileCapabilityType.UDID, "c2ced461f2d136211a630c1f06668a1771abd2b2");
        dc.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "ar.com.personal.bandaruattp");
        dc.setCapability(MobileCapabilityType.NO_RESET, true);
    }
    
    @BeforeMethod
    public void before() throws MalformedURLException {
    	driver = new IOSDriver<>(new URL("http://localhost:4723/wd/hub"), dc);
        driver.setLogLevel(Level.INFO);
    }
    
    //@AfterMethod
    public void after() {
    	sleep(5000);
    	int menu = 0;
    	while(((!driver.findElement(By.id("custom_ab_title")).getText().contains("Mi Personal"))) && menu < 3) {
        	driver.navigate().back();
        	menu++;
        }	
    	sleep(3000);
    	driver.findElement(By.id("custom_ab_drawer")).click();
    	sleep(5000);
    	driver.findElement(By.xpath("//*[@text='Cerrar Sesi\u00f3n']")).click();
    	sleep(5000);
    	driver.quit();
    }
    
    @Test
    public void asd() {
    	//loginPorLineaMobile(driver, lineaMIX);
    }
}
