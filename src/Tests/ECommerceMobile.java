package Tests;

import PageMetodos.MetodosAndroid;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileBrowserType;
import io.appium.java_client.remote.MobileCapabilityType;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

public class ECommerceMobile extends MetodosAndroid {
	
	private AndroidDriver<AndroidElement> driver = null;
    private DesiredCapabilities dc = new DesiredCapabilities();
    
    
	@BeforeClass (alwaysRun = true)
    public void setUp() throws MalformedURLException {
        dc.setCapability("reportDirectory", "reports");
        dc.setCapability("reportFormat", "xml");
        dc.setCapability("testName", "Untitled");
        dc.setCapability(MobileCapabilityType.UDID, "42004754d431448d");
        dc.setBrowserName(MobileBrowserType.CHROME);
        dc.setCapability("autoGrantPermissions", true);
        dc.setCapability(MobileCapabilityType.FULL_RESET, true);
        
        driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), dc);
        driver.setLogLevel(Level.INFO);
        driver.get("https://personaluat.vtexcommercestable.com.br/Admin/Site/Login.aspx?ReturnUrl=%2f");
        sleep(10000);
        driver.findElement(By.id("vtexIdUI-google-plus")).click();
        sleep(7000);
        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		driver.findElement(By.id("identifierId")).sendKeys("ecomerce.testing@gmail.com");
        driver.findElement(By.cssSelector("SPAN.RveJvd.snByac")).click();
        sleep(5000);
        driver.findElement(By.cssSelector("INPUT.whsOnd.zHQkBf")).sendKeys("Telecom*123");
        driver.findElement(By.cssSelector("SPAN.RveJvd.snByac")).click();
        sleep(40000);
        driver.switchTo().window(tabs.get(0));
        driver.switchTo().activeElement();
        driver.findElement(By.id("button1")).click();
//        for (int i=0; i<driver.findElements(By.className("android.widget.TextView")).size(); i++) {
//        	System.out.println(driver.findElements(By.className("android.widget.TextView")).get(i).getText());
//        }
    }
	
	//@BeforeMethod (alwaysRun = true)
	public void before() throws MalformedURLException {
		driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), dc);
        driver.setLogLevel(Level.INFO);        
        driver.get("https://personaluat.vtexcommercestable.com.br/Admin/Site/Login.aspx?ReturnUrl=%2f");
        sleep(10000);
	}
	
	//@AfterMethod (alwaysRun = true)
	public void after() {
    	sleep(5000);
    	driver.quit();
    }
	
	
	@Test (groups = "ECommerce")
	public void RegresionEcommerce_Filtro_Marcas() {
		/*boolean filtro = false;
		driver.findElement(By.cssSelector("A[href='https://personaluat.vtexcommercestable.com.br/tienda/']")).click();
		sleep(15000);
		driver.findElement(By.cssSelector("A.filter__mobile[href='.filter__combo--mobile']")).click();
		driver.findElement(By.cssSelector("A.filter__item--mark.filter__item--mark-Todas.las.marcas[href='.filter__combo--mark']")).click();
		driver.findElement(By.cssSelector("A.filter__item.filter__item--mark.filter__item--mark-samsung[href='/equipos/samsung']")).click();
		sleep(10000);
		for (WebElement x : driver.findElements(By.className("js-phone-brand"))) {
			if (x.getText().contains("Celulares samsung"))
				filtro = true;
		}
		Assert.assertTrue(filtro);*/
	}
	
	@Test (groups = "ECommerce")
	public void RegresionEcommerce_Filtro_Tipo_de_Camara() {
		driver.findElement(By.cssSelector("A[href='https://personaluat.vtexcommercestable.com.br/tienda/']")).click();
		sleep(15000);
		driver.findElement(By.cssSelector("A.filter__mobile[href='.filter__combo--mobile']")).click();
		driver.findElement(By.cssSelector("A[href='.filter__combo--category']")).click();
		driver.findElement(By.cssSelector("A.filter__item[href='/equipos/10Mpx a 15Mpx?map=c,specificationFilter_519']")).click();
		sleep(15000);
		List<AndroidElement> lista = driver.findElements(By.className("product-list__wrapper"));
		Assert.assertTrue(lista.size() >= 1);
	}
	
	@Test (groups = "ECommerce")
	public void RegresionEcommerce_Filtro_Tipo_de_Pantalla() {
		driver.findElement(By.cssSelector("A[href='https://personaluat.vtexcommercestable.com.br/tienda/']")).click();
		sleep(15000);
		driver.findElement(By.cssSelector("A.filter__mobile[href='.filter__combo--mobile']")).click();
		driver.findElement(By.cssSelector("A[href='.filter__combo--size']")).click();
		driver.findElement(By.cssSelector("A.filter__item[href='/equipos/Grande?map=c,specificationFilter_518']")).click();
		sleep(15000);
		List<AndroidElement> lista = driver.findElements(By.className("product-list__wrapper"));
		Assert.assertTrue(lista.size() >= 1);
	}
	
	@Test (groups = "ECommerce")
	public void RegresionEcommerce_Ordenamiento_Destacado() {
		driver.findElement(By.cssSelector("A[href='https://personaluat.vtexcommercestable.com.br/tienda/']")).click();
		sleep(15000);
		driver.findElement(By.cssSelector("A[href='.filter__combo--order']")).click();
		if (driver.findElement(By.cssSelector("A.filter__item.selected[href='/equipos/?O=OrderByTopSaleDESC']")).isDisplayed()) {
			driver.findElement(By.cssSelector("A.filter__item.selected[href='/equipos/?O=OrderByTopSaleDESC']")).click();
			Assert.assertTrue(true);
		} else
			Assert.assertTrue(false);
	}
	
	@Test (groups = "ECommerce")
	public void RegresionEcommerce_Ordenamiento_Precio_Mayor() {
		driver.findElement(By.cssSelector("A[href='https://personaluat.vtexcommercestable.com.br/tienda/']")).click();
		sleep(15000);
		driver.findElement(By.cssSelector("A[href='.filter__combo--order']")).click();
		if (driver.findElement(By.cssSelector("A.filter__item[href='/equipos/?O=OrderByPriceDESC']")).isDisplayed()) {
			driver.findElement(By.cssSelector("A.filter__item[href='/equipos/?O=OrderByPriceDESC']")).click();
			Assert.assertTrue(true);
		} else
			Assert.assertTrue(false);
	}
	
	@Test (groups = "ECommerce")
	public void RegresionEcommerce_Ordenamiento_Precio_Menor() {
		driver.findElement(By.cssSelector("A[href='https://personaluat.vtexcommercestable.com.br/tienda/']")).click();
		sleep(15000);
		driver.findElement(By.cssSelector("A[href='.filter__combo--order']")).click();
		if (driver.findElement(By.cssSelector("A.filter__item[href='/equipos/?O=OrderByPriceASC']")).isDisplayed()) {
			driver.findElement(By.cssSelector("A.filter__item[href='/equipos/?O=OrderByPriceASC']")).click();
			Assert.assertTrue(true);
		} else
			Assert.assertTrue(false);
	}
}