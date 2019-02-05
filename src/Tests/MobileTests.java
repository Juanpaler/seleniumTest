package Tests;

import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.MobileElement;
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
import java.util.List;
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
    	return roaming && ldi;
    }
    
    private boolean verificarLogin(String linea, String clave) {
    	boolean password = false;
    	sleep(5000);
    	driver.findElement(By.id("custom_ab_drawer")).click();
    	sleep(5000);
    	driver.findElement(By.xpath("//*[@text='Cerrar Sesi\u00f3n']")).click();
    	sleep(5000);
    	driver.findElement(By.id("editTextLinea")).sendKeys(linea);
    	driver.findElement(By.id("btn_log_in")).click();
        driver.findElement(By.id("editTextPin")).sendKeys(clave);
        driver.findElement(By.id("btn_log_in")).click();
        if (driver.findElement(By.className("android.widget.TextView")).getText().contains("Los datos ingresados son incorrectos."))
        	password = true;
        scrollAndClick("xpath", "//android.widget.Button[@text='Aceptar']");
        return password;
    }
    
    private boolean activYElimVozYSMS(String tipoDeLinea) {
    	boolean actVoz = false, actSMS = false, elimVoz = false, elimSMS = false;
    	switch(tipoDeLinea) {
    	case "MIX":
    		scrollAndClick("xpath", "//android.widget.TextView[@text='MIS SERVICIOS']");
        	scrollAndClick("xpath", "//android.widget.TextView[@text='N\u00fameros Gratis']");
        	break;
    	case "Pre":
    		driver.swipe(259, 154, 82, 151, 585);
        	driver.findElement(By.xpath("//*[@text='MIS SERVICIOS']")).click();
        	sleep(7000);
        	driver.findElement(By.xpath("//*[@text='N\u00fameros Gratis']")).click();
        	sleep(5000);
        	break;
    	}
    	scrollAndClick("xpath", "//android.widget.TextView[@text='AGREGAR']");
    	driver.findElements(By.className("android.widget.EditText")).get(0).sendKeys("11");
    	driver.findElements(By.className("android.widget.EditText")).get(1).sendKeys("62745165");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='AGREGAR']");
    	if (driver.findElement(By.id("dialog_alert_message")).getText().contains("El n\u00famero se agreg\u00f3 con \u00e9xito!"))
    		actVoz = true;
    	scrollAndClick("xpath", "//android.widget.Button[@text='ACEPTAR']");
    	driver.findElement(By.xpath("//*[@text='AGREGAR' and ./parent::*[./parent::*[(./preceding-sibling::* | ./following-sibling::*)[./*[./*[./*[@text='SMS']]]]]]]")).click();
    	driver.findElements(By.className("android.widget.EditText")).get(0).sendKeys("11");
    	driver.findElements(By.className("android.widget.EditText")).get(1).sendKeys("62735148");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='AGREGAR']");
    	if (driver.findElement(By.id("dialog_alert_message")).getText().contains("El n\u00famero se agreg\u00f3 con \u00e9xito!"))
    		actSMS = true;
    	scrollAndClick("xpath", "//android.widget.Button[@text='ACEPTAR']");
    	scrollAndClick("id", "imageview_component_image");
    	scrollAndClick("id", "button1");
    	if (driver.findElement(By.id("dialog_alert_message")).getText().contains("El n\u00famero se elimin\u00f3 con \u00e9xito!"))
    		elimVoz = true;
    	scrollAndClick("xpath", "//android.widget.Button[@text='ACEPTAR']");
    	scrollAndClick("id", "imageview_component_image");
    	scrollAndClick("id", "button1");
    	if (driver.findElement(By.id("dialog_alert_message")).getText().contains("El n\u00famero se elimin\u00f3 con \u00e9xito!"))
    		elimSMS = true;
    	scrollAndClick("xpath", "//android.widget.Button[@text='ACEPTAR']");
    	return actVoz && actSMS && elimVoz && elimSMS;
    }
    
    private boolean bajaDeSuscripcion() {
    	boolean bajaSus = false;
    	scrollAndClick("xpath", "//android.widget.TextView[@text='MIS SERVICIOS']");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='Mis suscripciones a servicios']");
    	scrollAndClick("id", "imageview_component_image");
    	scrollAndClick("id", "button1");
    	if (driver.findElement(By.className("android.widget.TextView")).getText().contains("La suscripci\u00f3n se di\u00f3 de baja con \u00e9xito"))
    		bajaSus = true;
    	scrollAndClick("id", "button1");
    	return bajaSus;
    }
    
    private boolean verifDisponibles(String tipoDeLinea) {
    	boolean internet = false, minutos = false, sms = false;
    	scrollAndClick("id", "iv_chevron");
    	for (WebElement x : driver.findElement(By.id("complex_component_layout")).findElements(By.className("android.widget.TextView"))) {
    		if (x.getText().contains("Compr\u00e1 m\u00e1s internet"))
    			internet = true;
    	}
    	driver.findElement(By.xpath("//*[@text='Minutos']")).click();
    	for (WebElement x : driver.findElement(By.id("complex_component_layout")).findElements(By.className("android.widget.TextView"))) {
    		if (x.getText().contains("Compr\u00e1 m\u00e1s minutos"))
    			minutos = true;
    	}
    	driver.findElement(By.xpath("//*[@text='SMS']")).click();
    	switch(tipoDeLinea) {
    	case "MIX":
    		for (WebElement x : driver.findElement(By.id("complex_component_layout")).findElements(By.className("android.widget.TextView"))) {
        		if (x.getText().contains("SMS ilimitados a todas las compa\u00f1\u00edas"))
        			sms = true;
        	}
    	case "Pre":
    		for (WebElement x : driver.findElement(By.id("complex_component_layout")).findElements(By.className("android.widget.TextView"))) {
        		if (x.getText().contains("Compr\u00e1 m\u00e1s SMS"))
        			sms = true;
        	}
    	}
    	return internet && minutos && sms;
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
    
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Comprar_Packs_Compra_de_Packs_MIX() {
    	loginPorLineaMobile(driver, lineaMIX);
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
    	loginPorLineaMobile(driver, lineaPre);
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
    	loginPorLineaMobile(driver, lineaMIX);
    	scrollAndClick("xpath", "//android.widget.TextView[@text='PAGOS, RECARGAS Y PACKS']");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='Compras con cr\u00e9dito Personal']");
    	sleep(15000);
    	scrollAndClick("xpath", "//android.view.View[@text='i']");
    	Assert.assertTrue(false); //Mensaje de error en descarga de comprobante
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Comprar_Packs_Consultar_Comprobantes_PRE() {
    	loginPorLineaMobile(driver, lineaPre);
    	scrollAndClick("xpath", "//android.widget.TextView[@text='RECARGAS Y PACKS']");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='Historial de compras de Packs']");
    	sleep(15000);
    	scrollAndClick("xpath", "//android.view.View[@text='i']");
    	Assert.assertTrue(false); //Mensaje de error en descarga de comprobante
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Consumos_Detalles_de_Consumos_MIX() {
    	loginPorLineaMobile(driver, lineaMIX);
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
    	loginPorLineaMobile(driver, lineaPos);
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
    	loginPorLineaMobile(driver, lineaPre);
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
    	loginPorLineaMobile(driver, lineaPre);
    	scrollAndClick("xpath", "//android.widget.TextView[@text='RECARGAS Y PACKS']");
    	scrollAndClick("id", "button1");
    	Assert.assertTrue(driver.findElement(By.id("button_component_text")).getText().contains("RECARG\u00c1 AHORA"));
    	driver.navigate().back();
    	scrollAndClick("xpath", "//android.widget.TextView[@text='RECARGAS Y PACKS']");
    	scrollAndClick("id", "button3");
    	sleep(10000);
    	Assert.assertTrue(driver.findElement(By.id("divRecargaTarjeta")).isEnabled());
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Recargas_Gestiones_y_Consultas_Recargar_Ahora_MIX() {
    	loginPorLineaMobile(driver, lineaMIX);
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
    	loginPorLineaMobile(driver, "1164599468");
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
        	driver.navigate().back();
        } catch(NoSuchElementException e) {
        	driver.navigate().back();
        	Assert.assertTrue(false);
        }
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Facturacion_Compras_Realizadas_Descargar_Comprobante_POS() {
    	loginPorLineaMobile(driver, "1164599450");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='Mis facturas']");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='Facturas de compra']");
    	sleep(10000);
    	scrollAndClick("xpath", "//android.view.View[@text='Descargar']");
    	driver.swipe(159, 28, 145, 498, 289);
        scrollAndClick("xpath", "//android.widget.TextView[@text='MiPersonal UAT']");
        sleep(5000);
        try {
        	Assert.assertTrue(driver.findElement(By.id("pdf_view")).isDisplayed());
        	driver.navigate().back();
        } catch(NoSuchElementException e) {
        	driver.navigate().back();
        	Assert.assertTrue(false);
        }
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void MI_Linea_Roaming_y_LDI_habilitado_MIX() {
    	loginPorLineaMobile(driver, "3496652414");
    	Assert.assertTrue(verificarRoamingYLDIActivos());
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void MI_Linea_Roaming_y_LDI_habilitado_POS() {
    	loginPorLineaMobile(driver, "3794601129");
    	Assert.assertTrue(verificarRoamingYLDIActivos());
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void MI_Linea_Roaming_y_LDI_habilitado_PRE() {
    	loginPorLineaMobile(driver, "1164520012");
    	Assert.assertTrue(verificarRoamingYLDIActivos());
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Facturacion_Imprimir_cupon_de_pago_MIX() {
    	loginPorLineaMobile(driver, "1162733281");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='PAGOS, RECARGAS Y PACKS']");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='Mis Facturas']");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='Cup\u00f3n de pago']");
    	driver.findElement(By.className("android.widget.EditText")).sendKeys("10.00");
    	//scrollAndClick("xpath", "//android.widget.TextView[@text='Descargar']");
    	Assert.assertTrue(false); //Mensaje de error al intentar descargar el cupon
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Facturacion_Imprimir_cupon_de_pago_POS() {
    	loginPorLineaMobile(driver, "3413130145");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='PAGOS Y PACKS']");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='Mis Facturas']");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='Cup\u00f3n de pago']");
    	driver.findElement(By.className("android.widget.EditText")).sendKeys("10.00");
    	//scrollAndClick("xpath", "//android.widget.TextView[@text='Descargar']");
    	Assert.assertTrue(false); //Mensaje de error al intentar descargar el cupon
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Recargas_Recarga_SOS_MIX() {
    	boolean msj = false;
    	loginPorLineaMobile(driver, lineaMIX);
    	scrollAndClick("xpath", "//android.widget.TextView[@text='PAGOS, RECARGAS Y PACKS']");
    	driver.findElement(By.xpath("//*[@text='RECARGA S.O.S.']")).click();
    	scrollAndClick("xpath", "//android.widget.TextView[@text='RECARG\u00c1 AHORA']");
    	for (WebElement x : driver.findElements(By.className("android.widget.TextView"))) {
    		if (x.getText().contains("La recarga S.O.S. se realiz\u00f3 con \u00e9xito"))
    			msj = true;
    	}
    	scrollAndClick("xpath", "//android.widget.Button[@text='ACEPTAR']");
    	Assert.assertTrue(msj);
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Recargas_Recarga_SOS_PRE() {
    	boolean msj = false;
    	loginPorLineaMobile(driver, lineaPre);
    	scrollAndClick("xpath", "//android.widget.TextView[@text='RECARGAS Y PACKS']");
    	driver.findElement(By.xpath("//*[@text='RECARGA S.O.S.']")).click();
    	scrollAndClick("xpath", "//android.widget.TextView[@text='RECARG\u00c1 AHORA']");
    	for (WebElement x : driver.findElements(By.className("android.widget.TextView"))) {
    		if (x.getText().contains("La recarga S.O.S. se realiz\u00f3 con \u00e9xito"))
    			msj = true;
    	}
    	scrollAndClick("xpath", "//android.widget.Button[@text='ACEPTAR']");
    	Assert.assertTrue(msj);
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Recargas_Recarga_con_puntos_Club_MIX() {
    	loginPorLineaMobile(driver, "1168829219");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='PAGOS, RECARGAS Y PACKS']");
    	driver.findElement(By.xpath("//*[@class='android.widget.RelativeLayout' and ./*[@text='Puntos Club']]")).click();
    	scrollAndClick("xpath", "//android.view.View[@text='RECARGAS']");
    	scrollAndClick("xpath", "//android.view.View[contains(text(),'Cr\u00e9dito $10')]");
    	//scrollAndClick("xpath", "//android.view.View[@text='CANJEAR']");
    	Assert.assertTrue(false);  //Mensaje de error al hacer click en canjear
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Recargas_Recarga_con_puntos_Club_PRE() {
    	loginPorLineaMobile(driver, "1164473518");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='RECARGAS Y PACKS']");
    	driver.findElement(By.xpath("//*[@class='android.widget.RelativeLayout' and ./*[@text='Puntos Club']]")).click();
    	scrollAndClick("xpath", "//android.view.View[@text='RECARGAS']");
    	scrollAndClick("xpath", "//android.view.View[contains(text(),'Cr\u00e9dito $10')]");
    	//scrollAndClick("xpath", "//android.view.View[@text='CANJEAR']");
    	Assert.assertTrue(false);  //Mensaje de error al hacer click en canjear
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Recargas_Gestiones_y_Consultas_Ultimas_Recargas_MIX() {
    	int recargas = 0;
    	loginPorLineaMobile(driver, lineaMIX);
    	scrollAndClick("xpath", "//android.widget.TextView[@text='PAGOS, RECARGAS Y PACKS']");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='Historial de Recargas']");
    	List<MobileElement> tabla = driver.findElement(By.xpath("//*[@class='android.widget.LinearLayout' and ./*[@id='complex_component_layout']]")).findElements(By.className("android.widget.TextView"));
    	for (int i=0; i<tabla.size(); i++) {
    		if (tabla.get(i).getText().contains("Recarga por Tarjeta de Credito"))
    			recargas++;
    	}
    	Assert.assertTrue(recargas >= 1);
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Recargas_Gestiones_y_Consultas_Ultimas_Recargas_PRE() {
    	int recargas = 0;
    	loginPorLineaMobile(driver, lineaPre);
    	scrollAndClick("xpath", "//android.widget.TextView[@text='RECARGAS Y PACKS']");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='Historial de Recargas']");
    	List<MobileElement> tabla = driver.findElement(By.xpath("//*[@class='android.widget.LinearLayout' and ./*[@id='complex_component_layout']]")).findElements(By.className("android.widget.TextView"));
    	for (int i=0; i<tabla.size(); i++) {
    		if (tabla.get(i).getText().contains("Recarga por Tarjeta de Credito"))
    			recargas++;
    	}
    	Assert.assertTrue(recargas >= 1);
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Ayuda_Acceder_a_AYUDA_y_seleccionar_alguna_opcion() {
    	boolean ayuda = false;
    	loginPorLineaMobile(driver, lineaPre);
    	driver.swipe(315, 154, -12, 154, 84);
    	scrollAndClick("xpath", "//android.widget.TextView[@text='AYUDA']");
    	driver.findElement(By.xpath("//*[@text='d. T\u00e9cnico | Personal Help - Argentina']")).click();
    	sleep(7000);
    	for (WebElement x : driver.findElements(By.className("android.view.View"))) {
    		if (x.getText().contains("Mi equipo no reconoce la Sim"))
    			ayuda = true;
    	}
    	Assert.assertTrue(ayuda);
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Login_Iniciar_Sesion_con_clave_Incorrecta() {
    	loginPorLineaMobile(driver, lineaMIX);
    	Assert.assertTrue(verificarLogin(lineaMIX, "1470"));
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Login_Iniciar_Sesion_sin_clave() {
    	loginPorLineaMobile(driver, lineaMIX);
    	Assert.assertTrue(verificarLogin(lineaMIX, ""));
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Login_Iniciar_Sesion_con_Linea_Inexistente() {
    	loginPorLineaMobile(driver, lineaMIX);
    	Assert.assertTrue(verificarLogin("1192735149", "1469"));
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Login_Iniciar_Sesion_con_Linea_PreActiva() {
    	loginPorLineaMobile(driver, "1162645152");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='MIS SERVICIOS']");
    	Assert.assertTrue(false);  //No se visualiza el mensaje de linea pre desactivada
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Comprar_Packs_Compra_de_Packs_con_puntos_Club_MIX() {
    	loginPorLineaMobile(driver, "1168829219");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='PAGOS, RECARGAS Y PACKS']");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='Con Puntos Club Personal']");
    	sleep(5000);
    	scrollAndClick("xpath", "//android.view.View[@text='ROAMING']");
    	scrollAndClick("xpath", "//android.view.View[@text='SMS']");
    	scrollAndClick("xpath", "//android.view.View[contains(text(),'NUEVO PREMIO PACK SMS Roaming Lim\u00edtrofes Pack de 10 SMS')]");
    	scrollAndClick("xpath", "//android.view.View[@text='CANJEAR']");
    	Assert.assertTrue(false);  //Mensaje de error al querer canjear
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Comprar_Packs_Compra_de_Packs_con_puntos_Club_PRE() {
    	loginPorLineaMobile(driver, "1164473518");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='RECARGAS Y PACKS']");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='Con Puntos Club Personal']");
    	sleep(5000);
    	scrollAndClick("xpath", "//android.view.View[@text='ROAMING']");
    	scrollAndClick("xpath", "//android.view.View[@text='SMS']");
    	scrollAndClick("xpath", "//android.view.View[contains(text(),'NUEVO PREMIO PACK SMS Roaming Lim\u00edtrofes Pack de 10 SMS')]");
    	scrollAndClick("xpath", "//android.view.View[@text='CANJEAR']");
    	Assert.assertTrue(false);  //Mensaje de error al querer canjear
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Facturacion_Ver_Facturas_MIX() {
    	boolean cantFacturas = false;
    	loginPorLineaMobile(driver, "1162733281");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='INICIO']");
    	driver.swipe(237, 854, 251, 231, 672);
        driver.findElement(By.xpath("//*[@id='button_component_layout' and ./*[@text='Mis facturas']]")).click();
        sleep(5000);
        driver.findElement(By.xpath("//*[@id='imageview_component_image' and ./parent::*[(./preceding-sibling::* | ./following-sibling::*)[./*[@id='double_component_main']] and ./parent::*[@id='double_component_main']]]")).click();
        sleep(40000);
        List<AndroidElement> facturas = driver.findElements(By.className("android.view.ViewGroup"));
        for (int i=0; i<facturas.size(); i++) {
        	if (facturas.size() > 1)
        		cantFacturas = true;
        }
        driver.navigate().back();
        Assert.assertTrue(cantFacturas);
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Facturacion_Ver_Facturas_POS() {
    	boolean cantFacturas = false;
    	loginPorLineaMobile(driver, "1165990597");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='INICIO']");
    	driver.swipe(237, 854, 251, 231, 672);
        driver.findElement(By.xpath("//*[@id='button_component_layout' and ./*[@text='Mis facturas']]")).click();
        sleep(5000);
        driver.findElement(By.xpath("//*[@id='imageview_component_image' and ./parent::*[(./preceding-sibling::* | ./following-sibling::*)[./*[@id='double_component_main']] and ./parent::*[@id='double_component_main']]]")).click();
        sleep(40000);
        List<AndroidElement> facturas = driver.findElements(By.className("android.view.ViewGroup"));
        for (int i=0; i<facturas.size(); i++) {
        	if (facturas.size() > 1)
        		cantFacturas = true;
        }
        driver.navigate().back();
        Assert.assertTrue(cantFacturas);
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Facturacion_Pagar_Factura_MIX() {
    	boolean factura = false;
    	loginPorLineaMobile(driver, "1162733281");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='PAGOS, RECARGAS Y PACKS']");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='Mis Facturas']");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='Pagar']");
    	sleep(5000);
    	for (WebElement x : driver.findElements(By.className("android.view.View"))) {
    		if (x.getText().contains("Detalles del pago"))
    			factura = true;
    	}
    	Assert.assertTrue(factura);
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Facturacion_Pagar_Factura_POS() {
    	boolean factura = false;
    	loginPorLineaMobile(driver, "1165990597");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='PAGOS Y PACKS']");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='Mis Facturas']");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='Pagar']");
    	sleep(5000);
    	for (WebElement x : driver.findElements(By.className("android.view.View"))) {
    		if (x.getText().contains("Detalles del pago"))
    			factura = true;
    	}
    	Assert.assertTrue(factura);
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Facturacion_Informar_un_Pago_MIX() {
    	loginPorLineaMobile(driver, "1162733281");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='PAGOS, RECARGAS Y PACKS']");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='Informar Pago']");
    	driver.findElement(By.id("textinput_component_edittext")).sendKeys("29/01/2019");
    	driver.findElement(By.id("textinput_component_edittext_currency")).sendKeys("150.00");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='ENVIAR']");
    	scrollAndClick("id", "button2");
    	scrollAndClick("id", "button1");
    	sleep(5000);
    	scrollAndClick("id", "button1");
    	Assert.assertTrue(false);  //Mensaje de error no se puede informar un pago por el momento
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Facturacion_Informar_un_Pago_POS() {
    	loginPorLineaMobile(driver, "3413130145");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='PAGOS Y PACKS']");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='Informar Pago']");
    	driver.findElement(By.id("textinput_component_edittext")).sendKeys("29/01/2019");
    	driver.findElement(By.id("textinput_component_edittext_currency")).sendKeys("150.00");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='ENVIAR']");
    	scrollAndClick("id", "button2");
    	scrollAndClick("id", "button1");
    	sleep(5000);
    	scrollAndClick("id", "button1");
    	Assert.assertTrue(false);  //Mensaje de error no se puede informar un pago por el momento
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Facturacion_Notas_de_Credito_y_Debito_MIX() {
    	loginPorLineaMobile(driver, "1161120234");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='PAGOS, RECARGAS Y PACKS']");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='Mis Facturas']");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='Notas fiscales']");
    	for (WebElement x : driver.findElements(By.className("android.view.View"))) {
    		if (x.getText().contains("Descargar")) {
    			x.click();
    			break;
    		}
    	}
    	sleep(5000);
    	driver.swipe(159, 28, 145, 498, 289);
        scrollAndClick("xpath", "//android.widget.TextView[@text='MiPersonal UAT']");
        sleep(5000);
        try {
        	Assert.assertTrue(driver.findElement(By.id("projector_toolbar")).isDisplayed());
         	driver.navigate().back();
        } catch(NoSuchElementException e) {
        	driver.navigate().back();
         	Assert.assertTrue(false);
        }
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Facturacion_Notas_de_Credito_y_Debito_POS() {
    	Assert.assertTrue(false);  //No estan funcionando las lineas Pos
    	/*loginPorLineaMobile(driver, lineaPos);
    	scrollAndClick("xpath", "//android.widget.TextView[@text='RECARGAS Y PACKS']");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='Mis Facturas']");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='Notas fiscales']");
    	for (WebElement x : driver.findElements(By.className("android.view.View"))) {
    		if (x.getText().contains("Descargar")) {
    			x.click();
    			break;
    		}
    	}
    	sleep(5000);
    	driver.swipe(159, 28, 145, 498, 289);
        scrollAndClick("xpath", "//android.widget.TextView[@text='MiPersonal UAT']");
        sleep(5000);
        try {
        	Assert.assertTrue(driver.findElement(By.id("projector_toolbar")).isDisplayed());
         	driver.navigate().back();
        } catch(NoSuchElementException e) {
        	driver.navigate().back();
         	Assert.assertTrue(false);
        }*/
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Mi_Linea_Numeros_Amigos_para_Hablar_SMS_Activacion_MIX() {
    	loginPorLineaMobile(driver, "1164461283");
    	Assert.assertTrue(activYElimVozYSMS("MIX"));
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Mi_Linea_Numeros_Amigos_para_Hablar_SMS_Activacion_PRE() {
    	loginPorLineaMobile(driver, "1164480623");
    	Assert.assertTrue(activYElimVozYSMS("Pre"));
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Mi_Linea_Baja_de_Suscripciones_PRE() {
    	loginPorLineaMobile(driver, lineaPre);
    	Assert.assertTrue(bajaDeSuscripcion());
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Mi_Linea_Baja_de_Suscripciones_POS() {
    	loginPorLineaMobile(driver, "1164599450");
    	Assert.assertTrue(bajaDeSuscripcion());
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Mi_Linea_Baja_de_Suscripciones_MIX() {
    	loginPorLineaMobile(driver, lineaMIX);
    	Assert.assertTrue(bajaDeSuscripcion());
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Mis_Datos_Mi_Perfil_Cambio_de_Domicilio_MIX() {
    	loginPorLineaMobile(driver, "1162749941");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='MIS SERVICIOS']");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='Domicilio de Facturaci\u00f3n']");
    	//String direccionVieja = driver.findElement(By.id("textinput_component_edittext")).getText();
    	String direccionNueva = "Av Rivadavia";
    	driver.findElement(By.id("textinput_component_edittext")).clear();
    	driver.findElement(By.id("textinput_component_edittext")).sendKeys(direccionNueva);
    	driver.findElement(By.xpath("(//*[@id='complex_component_layout']/*/*[@id='textinput_component_edittext_number'])[3]")).clear();
    	driver.findElement(By.xpath("(//*[@id='complex_component_layout']/*/*[@id='textinput_component_edittext_number'])[3]")).sendKeys("33851579");
    	//scrollAndClick("xpath", "//android.widget.TextView[@text='GUARDAR']");
    	Assert.assertTrue(false);  //Mensaje de error no se puede modificar domicilio en este momento
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Mis_Datos_Mi_Perfil_Cambio_de_Domicilio_POS() {
    	loginPorLineaMobile(driver, "1164599450");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='MIS SERVICIOS']");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='Domicilio de Facturaci\u00f3n']");
    	//String direccionVieja = driver.findElement(By.id("textinput_component_edittext")).getText();
    	String direccionNueva = "Av Rivadavia";
    	driver.findElement(By.id("textinput_component_edittext")).clear();
    	driver.findElement(By.id("textinput_component_edittext")).sendKeys(direccionNueva);
    	driver.findElement(By.xpath("(//*[@id='complex_component_layout']/*/*[@id='textinput_component_edittext_number'])[3]")).clear();
    	driver.findElement(By.xpath("(//*[@id='complex_component_layout']/*/*[@id='textinput_component_edittext_number'])[3]")).sendKeys("30444584");
    	//scrollAndClick("xpath", "//android.widget.TextView[@text='GUARDAR']");
    	Assert.assertTrue(false);  //Mensaje de error no se puede modificar domicilio en este momento
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Mis_Datos_Mi_Perfil_Cambio_de_Mail() {
    	loginPorLineaMobile(driver, "1164599450");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='MIS SERVICIOS']");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='Configurar mi e-mail']");
    	String mailViejo = driver.findElement(By.className("android.widget.EditText")).getText();
    	System.out.println(mailViejo);
    	driver.findElement(By.className("android.widget.EditText")).clear();
    	driver.findElement(By.className("android.widget.EditText")).sendKeys("testmail@gmail.com");
    	//scrollAndClick("xpath", "//android.widget.TextView[@text='GUARDAR']");
    	Assert.assertTrue(false);  //Mensaje de error no se puede modificar mail en este momento
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Inicio_Mis_disponibles_Internet_MIX() {
    	loginPorLineaMobile(driver, lineaMIX);
    	Assert.assertTrue(verifDisponibles("MIX"));
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Inicio_Mis_disponibles_Internet_PRE() {
    	loginPorLineaMobile(driver, lineaPre);
    	Assert.assertTrue(verifDisponibles("Pre"));
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Inicio_Detalle_de_credito_MIX() {
    	loginPorLineaMobile(driver, lineaMIX);
    	boolean credito = false;
    	List<AndroidElement> credDisponible = driver.findElements(By.id("text_component_title_custom"));
    	for (int i=0; i<credDisponible.size(); i++) {
    		if (credDisponible.get(i).getText().contains("Cr\u00e9dito de mi l\u00ednea"))
    			credito = true;
    	}
    	Assert.assertTrue(credito);
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Inicio_Detalle_de_credito_PRE() {
    	loginPorLineaMobile(driver, lineaPre);
    	boolean credito = false;
    	List<AndroidElement> credDisponible = driver.findElements(By.id("text_component_title_custom"));
    	for (int i=0; i<credDisponible.size(); i++) {
    		if (credDisponible.get(i).getText().contains("Cr\u00e9dito de mi l\u00ednea"))
    			credito = true;
    	}
    	Assert.assertTrue(credito);
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Inicio_Mis_disponibles_Mis_Facturas_MIX() {
    	loginPorLineaMobile(driver, lineaMIX);
    	boolean factura = false;
    	driver.swipe(237, 854, 251, 231, 672);
        driver.findElement(By.xpath("//*[@id='button_component_layout' and ./*[@text='Mis facturas']]")).click();
        sleep(5000);
        for (WebElement x : driver.findElements(By.className("android.widget.TextView"))) {
        	if (x.getText().contains("Cup\u00f3n de pago"))
        		factura = true;
        }
        Assert.assertTrue(factura);
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Inicio_Mis_disponibles_Mis_Facturas_POS() {
    	loginPorLineaMobile(driver, "1164599450");
    	boolean factura = false;
    	driver.swipe(237, 854, 251, 231, 672);
        driver.findElement(By.xpath("//*[@id='button_component_layout' and ./*[@text='Mis facturas']]")).click();
        sleep(5000);
        for (WebElement x : driver.findElements(By.className("android.widget.TextView"))) {
        	if (x.getText().contains("Cup\u00f3n de pago"))
        		factura = true;
        }
        Assert.assertTrue(factura);
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Mis_Consumos_Doble_Blue_Activar_MIX() {
    	loginPorLineaMobile(driver, "1164491372");
    	boolean activ = false, desact = false;
    	driver.findElement(By.xpath("//*[@id='button_component_layout']")).click();
    	scrollAndClick("xpath", "//android.widget.TextView[@text='AMPLIAR MI CUOTA A 200 MB']");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='CONFIRMAR']");
    	if (driver.findElement(By.id("dialog_alert_message")).getText().contains("Felicitaciones! Activaste la cuota Internet 200MB por Dia"))
    		activ = true;
    	scrollAndClick("id", "button1");
    	scrollAndClick("id", "iv_chevron");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='Modificar']");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='CONFIRMAR']");
    	if (driver.findElement(By.id("dialog_alert_message")).getText().contains("Se desactiv\u00f3 con \u00e9xito Internet 200MB por Dia"))
    		desact = true;
    	scrollAndClick("id", "button1");
    	Assert.assertTrue(activ && desact);
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Mis_Consumos_Doble_Blue_Activar_PRE() {
    	loginPorLineaMobile(driver, "1164477818");
    	boolean activ = false, desact = false;
    	driver.findElement(By.xpath("//*[@id='button_component_layout']")).click();
    	scrollAndClick("xpath", "//android.widget.TextView[@text='AMPLIAR MI CUOTA A 200 MB']");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='CONFIRMAR']");
    	if (driver.findElement(By.id("dialog_alert_message")).getText().contains("La Cuota Internet 200MB por Dia fue activada con \u00e9xito"))
    		activ = true;
    	scrollAndClick("id", "button1");
    	scrollAndClick("id", "iv_chevron");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='Modificar']");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='REDUCIR MI CUOTA 50 MB']");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='CONFIRMAR']");
    	if (driver.findElement(By.id("dialog_alert_message")).getText().contains("La Cuota Internet X Dia fue activada con \u00e9xito"))
    		desact = true;
    	scrollAndClick("id", "button1");
    	Assert.assertTrue(activ && desact);
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Pagos_Paga_con_Tarjeta_de_Credito_MIX() {
    	loginPorLineaMobile(driver, lineaMIX);
    	boolean factura = false;
    	scrollAndClick("xpath", "//android.widget.TextView[@text='PAGOS, RECARGAS Y PACKS']");
    	scrollAndClick("xpath", "//*[@id='button1' and ./*[@text='Pag\u00e1 con']]");
    	for (WebElement x : driver.findElements(By.className("android.view.View"))) {
    		if (x.getText().contains("Complet\u00e1 el siguiente formulario para finalizar el pago"))
    			factura = true;
    	}
    	Assert.assertTrue(factura);
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Pagos_Paga_con_Tarjeta_de_Credito_POS() {
    	loginPorLineaMobile(driver, "1164599450");
    	boolean factura = false;
    	scrollAndClick("xpath", "//android.widget.TextView[@text='PAGOS Y PACKS']");
    	scrollAndClick("xpath", "//*[@id='button1' and ./*[@text='Pag\u00e1 con']]");
    	for (WebElement x : driver.findElements(By.className("android.view.View"))) {
    		if (x.getText().contains("Complet\u00e1 el siguiente formulario para finalizar el pago"))
    			factura = true;
    	}
    	Assert.assertTrue(factura);
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Pagos_Paga_con_Pago_Mis_Cuentas_MIX() {
    	loginPorLineaMobile(driver, lineaMIX);
    	boolean pmc = false;
    	scrollAndClick("xpath", "//android.widget.TextView[@text='PAGOS, RECARGAS Y PACKS']");
    	scrollAndClick("xpath", "//*[@id='button2' and ./*[@text='Pag\u00e1 con']]");
    	for (WebElement x : driver.findElements(By.className("android.widget.TextView"))) {
    		if (x.getText().contains("Pago con Pago Mis Cuentas: Paso a paso"))
    			pmc = true;
    	}
    	Assert.assertTrue(pmc);
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Pagos_Paga_con_Pago_Mis_Cuentas_POS() {
    	loginPorLineaMobile(driver, "1164599450");
    	boolean pmc = false;
    	scrollAndClick("xpath", "//android.widget.TextView[@text='PAGOS Y PACKS']");
    	scrollAndClick("id", "button2");
    	for (WebElement x : driver.findElements(By.className("android.widget.TextView"))) {
    		if (x.getText().contains("Pago con Pago Mis Cuentas: Paso a paso"))
    			pmc = true;
    	}
    	Assert.assertTrue(pmc);
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Pagos_Pago_online_MIX() {
    	loginPorLineaMobile(driver, lineaMIX);
    	boolean tdc = false, hb = false, pmc = false, pagos = false;
    	scrollAndClick("xpath", "//android.widget.TextView[@text='PAGOS, RECARGAS Y PACKS']");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='Pago online']");
    	for (WebElement x : driver.findElements(By.className("android.widget.TextView"))) {
    		if (x.getText().contains("Tarjetas de Cr\u00e9dito"))
    			tdc = true;
    		if (x.getText().contains("Home Banking"))
    			hb = true;
    		if (x.getText().contains("Pago Mis Cuentas"))
    			pmc = true;
    		if (x.getText().contains("Link Pagos"))
    			pagos = true;
    	}
    	Assert.assertTrue(tdc && hb && pmc && pagos);
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Pagos_Pago_online_POS() {
    	loginPorLineaMobile(driver, "1164599450");
    	boolean tdc = false, hb = false, pmc = false, pagos = false;
    	scrollAndClick("xpath", "//android.widget.TextView[@text='PAGOS Y PACKS']");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='Pago online']");
    	for (WebElement x : driver.findElements(By.className("android.widget.TextView"))) {
    		if (x.getText().contains("Tarjetas de Cr\u00e9dito"))
    			tdc = true;
    		if (x.getText().contains("Home Banking"))
    			hb = true;
    		if (x.getText().contains("Pago Mis Cuentas"))
    			pmc = true;
    		if (x.getText().contains("Link Pagos"))
    			pagos = true;
    	}
    	Assert.assertTrue(tdc && hb && pmc && pagos);
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Pagos_Pago_Presencial_MIX() {
    	loginPorLineaMobile(driver, lineaMIX);
    	boolean pee = false, ca = false, pcc = false;
    	scrollAndClick("xpath", "//android.widget.TextView[@text='PAGOS, RECARGAS Y PACKS']");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='Pago Presencial']");
    	for (WebElement x : driver.findElements(By.className("android.widget.TextView"))) {
    		if (x.getText().contains("Pagos en efectivo"))
    			pee = true;
    		if (x.getText().contains("Cajeros Autom\u00e1ticos"))
    			ca = true;
    		if (x.getText().contains("Pagos con Cheque"))
    			pcc = true;
    	}
    	Assert.assertTrue(pee && ca && pcc);
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Pagos_Pago_Presencial_POS() {
    	loginPorLineaMobile(driver, "1164599450");
    	boolean pee = false, ca = false, pcc = false;
    	scrollAndClick("xpath", "//android.widget.TextView[@text='PAGOS Y PACKS']");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='Pago Presencial']");
    	for (WebElement x : driver.findElements(By.className("android.widget.TextView"))) {
    		if (x.getText().contains("Pagos en efectivo"))
    			pee = true;
    		if (x.getText().contains("Cajeros Autom\u00e1ticos"))
    			ca = true;
    		if (x.getText().contains("Pagos con Cheque"))
    			pcc = true;
    	}
    	Assert.assertTrue(pee && ca && pcc);
    }
}