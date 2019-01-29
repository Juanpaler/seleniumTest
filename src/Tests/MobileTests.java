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
        	driver.navigate().back();
        } catch(NoSuchElementException e) {
        	driver.navigate().back();
        	Assert.assertTrue(false);
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
        	driver.navigate().back();
        } catch(NoSuchElementException e) {
        	driver.navigate().back();
        	Assert.assertTrue(false);
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
    	Assert.assertTrue(false); //Mensaje de error al intentar descargar el cupon
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Recargas_Recarga_SOS_MIX() {
    	boolean msj = false;
    	loginPorLineaMobile(driver, "MIX");
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
    	loginPorLineaMobile(driver, "Pre");
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
    	loginPorLineaMobileVariable(driver, "1168829219");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='PAGOS, RECARGAS Y PACKS']");
    	driver.findElement(By.xpath("//*[@class='android.widget.RelativeLayout' and ./*[@text='Puntos Club']]")).click();
    	scrollAndClick("xpath", "//android.view.View[@text='RECARGAS']");
    	scrollAndClick("xpath", "//android.view.View[contains(text(),'Cr\u00e9dito $10')]");
    	//scrollAndClick("xpath", "//android.view.View[@text='CANJEAR']");
    	Assert.assertTrue(false);  //Mensaje de error al hacer click en canjear
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Recargas_Recarga_con_puntos_Club_PRE() {
    	loginPorLineaMobileVariable(driver, "1164473518");
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
    	loginPorLineaMobile(driver, "MIX");
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
    	loginPorLineaMobile(driver, "Pre");
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
    	loginPorLineaMobile(driver, "Pre");
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
    	loginPorLineaMobile(driver, "MIX");
    	Assert.assertTrue(verificarLogin(lineaMIX, "1470"));
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Login_Iniciar_Sesion_sin_clave() {
    	loginPorLineaMobile(driver, "MIX");
    	Assert.assertTrue(verificarLogin(lineaMIX, ""));
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Login_Iniciar_Sesion_con_Linea_Inexistente() {
    	loginPorLineaMobile(driver, "MIX");
    	Assert.assertTrue(verificarLogin("1192735149", "1469"));
    }
    
    @Test (groups = "AutogestionIndividuosAPP")
    public void Login_Iniciar_Sesion_con_Linea_PreActiva() {
    	loginPorLineaMobileVariable(driver, "1162645152");
    	scrollAndClick("xpath", "//android.widget.TextView[@text='MIS SERVICIOS']");
    	Assert.assertTrue(false);
    }
}