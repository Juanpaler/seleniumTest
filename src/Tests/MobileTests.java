package Tests;

import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import PageMetodos.Metodos;

import java.net.URL;
import java.net.MalformedURLException;
import java.util.logging.Level;

public class MobileTests extends Metodos {
	
	private String reportDirectory = "reports";
    private String reportFormat = "xml";
    private String testName = "Untitled";
    private String nroUDID = "42004754d431448d";
    private AndroidDriver<AndroidElement> driver = null;
    DesiredCapabilities dc = new DesiredCapabilities();
    
    
    //----------------------------------------------------- METODOS PRIVADOS -----------------------------------------------------\\
    
    private void scrollAndClick(String by, String using) {
        AndroidElement element = null;
        int numberOfTimes = 10;
        Dimension size = driver.manage().window().getSize();
        int anchor = (int) (size.width / 2);
        int startPoint = (int) (size.height - 10);
        int endPoint = 10;
        for (int i=0; i<numberOfTimes; i++) {
            try {
                new TouchAction(driver).longPress(anchor, startPoint).moveTo(anchor, endPoint).release().perform();
                element = (AndroidElement) driver.findElement(by, using);
                i = numberOfTimes;
            } catch(NoSuchElementException e) {
                System.out.println(String.format("Element not available. Scrolling (%s) times...", i + 1));
            }
        }
        element.click();
        sleep(7000);
    }
    
    private boolean verificarRoamingYLDIActivos() {
    	boolean roaming = false, ldi = false;
    	scrollAndClick("xpath", "//android.widget.TextView[@text='MIS SERVICIOS']");
    	driver.findElement(By.xpath("//*[@class='android.widget.LinearLayout' and ./*[@text='Roaming']]")).click();
    	sleep(5000);
    	driver.findElement(By.xpath("//*[@text='Roaming' and @id='text_component_title_custom']")).click();
    	sleep(5000);
    	for (WebElement x : driver.findElements(By.className("android.widget.TextView"))) {
    		if (x.getText().contains("Roaming Activo"))
    			roaming = true;
    	}
    	driver.navigate().back();
    	driver.navigate().back();
    	driver.findElement(By.xpath("//*[@class='android.widget.LinearLayout' and ./*[@text='Larga Distancia Internacional']]")).click();
    	sleep(5000);
    	for (WebElement x : driver.findElements(By.className("android.widget.TextView"))) {
    		if (x.getText().contains("Larga Distancia Internacional Activado"))
    			ldi = true;
    	}
    	driver.navigate().back();
    	return roaming && ldi;
    }
       
  //------------------------------------------------------------------------------------------------------------------------------\\
    
    
    @BeforeClass (alwaysRun = true)
    public void init() {
    	dc.setCapability("reportDirectory", reportDirectory);
        dc.setCapability("reportFormat", reportFormat);
        dc.setCapability("testName", testName);
        dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        dc.setCapability(MobileCapabilityType.UDID, nroUDID);
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "ar.com.personal.bandaruattp");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "ar.com.personal.app.activities.bandar.SplashActivity");
        dc.setCapability(MobileCapabilityType.NO_RESET, true);
    }
    
    @BeforeMethod (alwaysRun = true)
    public void before() throws MalformedURLException {
    	driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), dc);
        driver.setLogLevel(Level.INFO);
    }
    
    //@AfterMethod (alwaysRun = true)
    public void after() {
    	driver.findElement(By.id("custom_ab_drawer")).click();
    	sleep(5000);
    	driver.findElement(By.xpath("//*[@text='Cerrar Sesi\u00f3n']")).click();
    	sleep(5000);
    	driver.quit();
    }
    
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Comprar_Packs_Compra_de_Packs_MIX() {
    	loginPorLineaMobile(driver, "MIX");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='PAGOS, RECARGAS Y PACKS']");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='Con Cr\u00e9dito']");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='ROAMING']");
    	scrollAndClick("xpath", "//android.widget.TextView[contains(text(),'Pack Roaming 40 SMS Limitrofes')]");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='COMPRAR']");
    	scrollAndClick("xpath", "//android.widget.Button[@text='ACEPTAR']");
    	sleep(5000);
    	Assert.assertTrue(driver.findElement(By.className("android.widget.TextView")).getText().contains("La compra se realiz\u00f3 con \u00e9xito"));
    	driver.findElement(By.className("android.widget.Button")).click();
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Comprar_Packs_Compra_de_Packs_PRE() {
    	loginPorLineaMobile(driver, "Pre");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='RECARGAS Y PACKS']");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='Con Cr\u00e9dito']");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='ROAMING']");
    	scrollAndClick("xpath", "//android.widget.TextView[contains(text(),'Pack Roaming 40 SMS Limitrofes')]");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='COMPRAR']");
    	scrollAndClick("xpath", "//android.widget.Button[@text='ACEPTAR']");
    	sleep(5000);
    	Assert.assertTrue(driver.findElement(By.className("android.widget.TextView")).getText().contains("La compra se realiz\u00f3 con \u00e9xito"));
    	driver.findElement(By.className("android.widget.Button")).click();
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Comprar_Packs_Consultar_Comprobantes_MIX() {
    	loginPorLineaMobile(driver, "MIX");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='PAGOS, RECARGAS Y PACKS']");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='Compras con cr\u00e9dito Personal']");
    	sleep(15000);
    	scrollAndClick("xpath", "//android.view.View[@text='i']");
    	Assert.assertTrue(false); //Mensaje de error en descarga de comprobante
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Comprar_Packs_Consultar_Comprobantes_PRE() {
    	loginPorLineaMobile(driver, "Pre");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='RECARGAS Y PACKS']");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='Historial de compras de Packs']");
    	sleep(15000);
    	scrollAndClick("xpath", "//android.view.View[@text='i']");
    	Assert.assertTrue(false); //Mensaje de error en descarga de comprobante
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Consumos_Detalles_de_Consumos_MIX() {
    	loginPorLineaMobile(driver, "MIX");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='INICIO']");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='Detalle de consumos']");
    	driver.findElement(By.id("item_spinner_title")).click();
    	driver.findElement(By.xpath("//*[text() = 'Todos']")).click();
    	driver.findElements(By.id("item_spinner_title")).get(1).click();
    	driver.findElement(By.xpath("//*[text() = 'Desde mi \u00faltima recarga']")).click();
    	scrollAndClick("xpath", "//android.widget.TextView[@text='Consultar']");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='Recarga']");
    	Assert.assertTrue(driver.findElement(By.id("base_component_border")).isEnabled());
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Consumos_Detalles_de_Consumos_POS() {
    	loginPorLineaMobile(driver, "Pos");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='INICIO']");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='Detalle de consumos']");
    	driver.findElement(By.id("item_spinner_title")).click();
    	driver.findElement(By.xpath("//*[text() = '  Todos']")).click();
    	driver.findElement(By.className("android.widget.EditText")).clear();
    	driver.findElement(By.className("android.widget.EditText")).sendKeys("22/12/2018");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='CONSULTAR']");
    	sleep(5000);
    	Assert.assertTrue(driver.findElement(By.id("itemGrillaInternet")).isEnabled());
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Consumos_Detalles_de_Consumos_PRE() {
    	loginPorLineaMobile(driver, "Pre");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='INICIO']");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='Detalle de consumos']");
    	driver.findElement(By.id("item_spinner_title")).click();
    	driver.findElement(By.xpath("//*[text() = 'Todos']")).click();
    	driver.findElements(By.id("item_spinner_title")).get(1).click();
    	driver.findElement(By.xpath("//*[text() = 'Desde mi \u00faltima recarga']")).click();
    	scrollAndClick("xpath", "//android.widget.TextView[@text='Consultar']");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='Recarga']");
    	Assert.assertTrue(driver.findElement(By.id("base_component_border")).isEnabled());
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Recargas_Gestiones_y_Consultas_Recargar_Ahora_PRE() {
    	loginPorLineaMobile(driver, "Pre");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='RECARGAS Y PACKS']");
    	scrollAndClick("id", "button1");
    	Assert.assertTrue(driver.findElement(By.id("button_component_text")).getText().contains("RECARG\u00c1 AHORA"));
    	driver.navigate().back();
    	scrollAndClick("xpath", "//android.widget.TextView[@text='RECARGAS Y PACKS']");
    	scrollAndClick("id", "button2");
    	sleep(10000);
    	Assert.assertTrue(driver.findElement(By.id("divRecargaPuntosClub")).isEnabled());
    	driver.navigate().back();
    	scrollAndClick("xpath", "//android.widget.TextView[@text='RECARGAS Y PACKS']");
    	scrollAndClick("id", "button3");
    	sleep(10000);
    	Assert.assertTrue(driver.findElement(By.id("divRecargaTarjeta")).isEnabled());
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Recargas_Gestiones_y_Consultas_Recargar_Ahora_MIX() {
    	loginPorLineaMobile(driver, "MIX");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='PAGOS, RECARGAS Y PACKS']");
    	driver.findElement(By.xpath("//*[@bounds='[21,606][179,771]']")).click();
    	sleep(5000);
    	Assert.assertTrue(driver.findElement(By.id("button_component_text")).getText().contains("RECARG\u00c1 AHORA"));
    	driver.navigate().back();
    	driver.findElement(By.xpath("//*[@bounds='[191,606][349,771]']")).click();
    	sleep(25000);
    	Assert.assertTrue(driver.findElement(By.id("divRecargaPuntosClub")).isEnabled());
    	driver.navigate().back();
    	driver.findElement(By.xpath("//*[@bounds='[361,606][519,771]']")).click();
    	sleep(25000);
    	Assert.assertTrue(driver.findElement(By.id("divRecargaTarjeta")).isEnabled());
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Facturacion_Compras_Realizadas_Descargar_Comprobante_MIX() {
    	loginPorLineaMobileVariable(driver, "1164599468");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='INICIO']");
    	sleep(5000);
    	driver.swipe(237, 854, 251, 231, 672);
        driver.findElement(By.xpath("//*[@id='button_component_layout' and ./*[@text='Mis facturas']]")).click();
        sleep(5000);
        driver.findElement(By.xpath("//*[@class='android.widget.LinearLayout' and ./*[@text='Facturas de compra']]")).click();
        sleep(10000);
        scrollAndClick("xpath", "//android.view.View[@text='Descargar']");
        driver.swipe(159, 28, 145, 498, 289);
        scrollAndClick("xpath", "//android.widget.TextView[@text='MiPersonal UAT']");
        sleep(5000);
        try {
        	Assert.assertTrue(driver.findElement(By.id("pdf_view")).isDisplayed());
        } catch(Exception e) {
        	for (int i=0; i<3; i++) {
            	driver.navigate().back();
        	}
        	Assert.assertTrue(false);
        }
        for (int i=0; i<3; i++) {
            driver.navigate().back();
        }
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Facturacion_Compras_Realizadas_Descargar_Comprobante_POS() {
    	loginPorLineaMobileVariable(driver, "1164599450");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='Mis facturas']");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='Facturas de compra']");
    	sleep(10000);
    	scrollAndClick("xpath", "//android.view.View[@text='Descargar']");
    	driver.swipe(159, 28, 145, 498, 289);
        scrollAndClick("xpath", "//android.widget.TextView[@text='MiPersonal UAT']");
        sleep(5000);
        try {
        	Assert.assertTrue(driver.findElement(By.id("pdf_view")).isDisplayed());
        } catch(Exception e) {
        	for (int i=0; i<3; i++) {
            	driver.navigate().back();
        	}
        	Assert.assertTrue(false);
        }
        for (int i=0; i<3; i++) {
            driver.navigate().back();
        }
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void MI_Linea_Roaming_y_LDI_habilitado_MIX() {
    	loginPorLineaMobileVariable(driver, "3496652414");
    	Assert.assertTrue(verificarRoamingYLDIActivos());
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void MI_Linea_Roaming_y_LDI_habilitado_POS() {
    	loginPorLineaMobileVariable(driver, "3794601129");
    	Assert.assertTrue(verificarRoamingYLDIActivos());
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void MI_Linea_Roaming_y_LDI_habilitado_PRE() {
    	loginPorLineaMobileVariable(driver, "1164520012");
    	Assert.assertTrue(verificarRoamingYLDIActivos());
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Facturacion_Imprimir_cupon_de_pago_MIX() {
    	loginPorLineaMobileVariable(driver, "1162733281");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='PAGOS, RECARGAS Y PACKS']");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='Mis Facturas']");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='Cup\u00f3n de pago']");
    	driver.findElement(By.className("android.widget.EditText")).sendKeys("10.00");
    	//scrollAndClick("xpath", "//android.widget.TextView[@text='Descargar']");
    	driver.navigate().back();
    	sleep(5000);
    	driver.navigate().back();
    	sleep(5000);
    	Assert.assertTrue(false); //Mensaje de error al intentar descargar el cupon
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Facturacion_Imprimir_cupon_de_pago_POS() {
    	loginPorLineaMobileVariable(driver, "3413130145");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='PAGOS Y PACKS']");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='Mis Facturas']");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='Cup\u00f3n de pago']");
    	driver.findElement(By.className("android.widget.EditText")).sendKeys("10.00");
    	//scrollAndClick("xpath", "//android.widget.TextView[@text='Descargar']");
    	driver.navigate().back();
    	sleep(5000);
    	driver.navigate().back();
    	sleep(5000);
    	Assert.assertTrue(false); //Mensaje de error al intentar descargar el cupon
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Recargas_Recarga_SOS_MIX() {
    	loginPorLineaMobile(driver, "MIX");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='PAGOS, RECARGAS Y PACKS']");
    	driver.findElement(By.xpath("//*[@text='RECARGA S.O.S.']")).click();
    	scrollAndClick("xpath", "//android.widget.TextView[@text='RECARG\u00c1 AHORA']");
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Recargas_Recarga_SOS_PRE() {
    	loginPorLineaMobile(driver, "Pre");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='RECARGAS Y PACKS']");
    	driver.findElement(By.xpath("//*[@text='RECARGA S.O.S.']")).click();
    	scrollAndClick("xpath", "//android.widget.TextView[@text='RECARG\u00c1 AHORA']");
    }
}