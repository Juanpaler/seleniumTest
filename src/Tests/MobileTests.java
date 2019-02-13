package Tests;

import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

import org.junit.Assert;
import org.openqa.selenium.By;
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
    
    @AfterMethod (groups = "AutogestionIndividuosAPP")
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
    
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 0)
    public void Comprar_Packs_Compra_de_Packs_MIX() {
    	boolean msj = false;
    	loginPorLineaMobile(driver, lineaMIX);
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='PAGOS, RECARGAS Y PACKS']");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='Con Cr\u00e9dito']");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='ROAMING']");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[contains(text(),'Pack Roaming 40 SMS Limitrofes')]");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='COMPRAR']");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.Button[@text='ACEPTAR']");
    	sleep(5000);
    	if (driver.findElement(By.className("android.widget.TextView")).getText().contains("La compra se realiz\u00f3 con \u00e9xito"))
    		msj = true;
    	driver.findElement(By.className("android.widget.Button")).click();
    	sleep(5000);
    	Assert.assertTrue(msj);
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 1)
    public void Comprar_Packs_Compra_de_Packs_PRE() {
    	loginPorLineaMobile(driver, lineaPre);
    	boolean msj = false;
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='RECARGAS Y PACKS']");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='Con Cr\u00e9dito']");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='ROAMING']");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[contains(text(),'Pack Roaming 40 SMS Limitrofes')]");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='COMPRAR']");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.Button[@text='ACEPTAR']");
    	sleep(5000);
    	if (driver.findElement(By.className("android.widget.TextView")).getText().contains("La compra se realiz\u00f3 con \u00e9xito"))
    		msj = true;
    	driver.findElement(By.className("android.widget.Button")).click();
    	sleep(5000);
    	Assert.assertTrue(msj);
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 2)
    public void Comprar_Packs_Consultar_Comprobantes_MIX() {
    	loginPorLineaMobile(driver, lineaMIX);
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='PAGOS, RECARGAS Y PACKS']");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='Compras con cr\u00e9dito Personal']");
    	sleep(15000);
    	driver.findElement(By.xpath("//*[@text='i' and @class='UIAImage' and ./*[@class='UIAStaticText'] and ./parent::*[./parent::*[(./preceding-sibling::* | ./following-sibling::*)[./*[@text='11/02/2019 03:39:54']]]]]")).click();
    	Assert.assertTrue(false); //Mensaje de error en descarga de comprobante
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 3)
    public void Comprar_Packs_Consultar_Comprobantes_PRE() {
    	loginPorLineaMobile(driver, lineaPre);
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='RECARGAS Y PACKS']");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='Historial de compras de Packs']");
    	sleep(15000);
    	driver.findElement(By.xpath("//*[@text='i' and @class='UIAImage' and ./*[@class='UIAStaticText'] and ./parent::*[./parent::*[(./preceding-sibling::* | ./following-sibling::*)[./*[@text='11/02/2019 03:39:54']]]]]")).click();
    	Assert.assertTrue(false); //Mensaje de error en descarga de comprobante
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 4)
    public void Consumos_Detalles_de_Consumos_MIX() {
    	loginPorLineaMobile(driver, lineaMIX);
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='INICIO']");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='Detalle de consumos']");
    	driver.findElement(By.id("item_spinner_title")).click();
    	driver.findElement(By.xpath("//*[text() = 'Todos']")).click();
    	driver.findElements(By.id("item_spinner_title")).get(1).click();
    	driver.findElement(By.xpath("//*[text() = 'Desde mi \u00faltima recarga']")).click();
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='Consultar']");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='Recarga']");
    	Assert.assertTrue(driver.findElement(By.id("base_component_border")).isEnabled());
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 5)
    public void Consumos_Detalles_de_Consumos_POS() {
    	loginPorLineaMobile(driver, lineaPos);
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='INICIO']");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='Detalle de consumos']");
    	driver.findElement(By.id("item_spinner_title")).click();
    	driver.findElement(By.xpath("//*[text() = '  Todos']")).click();
    	driver.findElement(By.className("android.widget.EditText")).clear();
    	driver.findElement(By.className("android.widget.EditText")).sendKeys("22/12/2018");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='CONSULTAR']");
    	sleep(5000);
    	Assert.assertTrue(driver.findElement(By.id("itemGrillaInternet")).isEnabled());
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 6)
    public void Consumos_Detalles_de_Consumos_PRE() {
    	loginPorLineaMobile(driver, lineaPre);
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='INICIO']");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='Detalle de consumos']");
    	driver.findElement(By.id("item_spinner_title")).click();
    	driver.findElement(By.xpath("//*[text() = 'Todos']")).click();
    	driver.findElements(By.id("item_spinner_title")).get(1).click();
    	driver.findElement(By.xpath("//*[text() = 'Desde mi \u00faltima recarga']")).click();
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='Consultar']");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='Recarga']");
    	Assert.assertTrue(driver.findElement(By.id("base_component_border")).isEnabled());
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 7)
    public void Recargas_Gestiones_y_Consultas_Recargar_Ahora_PRE() {
    	loginPorLineaMobile(driver, lineaPre);
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='RECARGAS Y PACKS']");
    	scrollAndClickAndroid(driver, "id", "button1");
    	Assert.assertTrue(driver.findElement(By.id("button_component_text")).getText().contains("RECARG\u00c1 AHORA"));
    	driver.navigate().back();
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='RECARGAS Y PACKS']");
    	scrollAndClickAndroid(driver, "id", "button3");
    	sleep(10000);
    	Assert.assertTrue(driver.findElement(By.id("divRecargaTarjeta")).isEnabled());
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 8)
    public void Recargas_Gestiones_y_Consultas_Recargar_Ahora_MIX() {
    	loginPorLineaMobile(driver, lineaMIX);
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='PAGOS, RECARGAS Y PACKS']");
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
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 9)
    public void Facturacion_Compras_Realizadas_Descargar_Comprobante_MIX() {
    	loginPorLineaMobile(driver, "1164599468");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='INICIO']");
    	sleep(5000);
    	driver.swipe(237, 854, 251, 231, 672);
        driver.findElement(By.xpath("//*[@id='button_component_layout' and ./*[@text='Mis facturas']]")).click();
        sleep(5000);
        driver.findElement(By.xpath("//*[@class='android.widget.LinearLayout' and ./*[@text='Facturas de compra']]")).click();
        sleep(10000);
        scrollAndClickAndroid(driver, "xpath", "//android.view.View[@text='Descargar']");
        driver.swipe(159, 28, 145, 498, 289);
        scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='MiPersonal UAT']");
        sleep(5000);
        try {
        	Assert.assertTrue(driver.findElement(By.id("pdf_view")).isDisplayed());
        	driver.navigate().back();
        } catch(NoSuchElementException e) {
        	driver.navigate().back();
        	Assert.assertTrue(false);
        }
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 10)
    public void Facturacion_Compras_Realizadas_Descargar_Comprobante_POS() {
    	loginPorLineaMobile(driver, "1164599450");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='Mis facturas']");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='Facturas de compra']");
    	sleep(10000);
    	scrollAndClickAndroid(driver, "xpath", "//android.view.View[@text='Descargar']");
    	driver.swipe(159, 28, 145, 498, 289);
        scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='MiPersonal UAT']");
        sleep(5000);
        try {
        	Assert.assertTrue(driver.findElement(By.id("pdf_view")).isDisplayed());
        	driver.navigate().back();
        } catch(NoSuchElementException e) {
        	driver.navigate().back();
        	Assert.assertTrue(false);
        }
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 11)
    public void MI_Linea_Roaming_y_LDI_habilitado_MIX() {
    	loginPorLineaMobile(driver, "3496652414");
    	Assert.assertTrue(verificarRoamingYLDIActivos(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 12)
    public void MI_Linea_Roaming_y_LDI_habilitado_POS() {
    	loginPorLineaMobile(driver, "3794601129");
    	Assert.assertTrue(verificarRoamingYLDIActivos(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 13)
    public void MI_Linea_Roaming_y_LDI_habilitado_PRE() {
    	loginPorLineaMobile(driver, "1164520012");
    	Assert.assertTrue(verificarRoamingYLDIActivos(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 14)
    public void Facturacion_Imprimir_cupon_de_pago_MIX() {
    	loginPorLineaMobile(driver, "1162733281");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='PAGOS, RECARGAS Y PACKS']");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='Mis Facturas']");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='Cup\u00f3n de pago']");
    	driver.findElement(By.className("android.widget.EditText")).sendKeys("10.00");
    	//scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='Descargar']");
    	Assert.assertTrue(false); //Mensaje de error al intentar descargar el cupon
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 15)
    public void Facturacion_Imprimir_cupon_de_pago_POS() {
    	loginPorLineaMobile(driver, "3413130145");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='PAGOS Y PACKS']");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='Mis Facturas']");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='Cup\u00f3n de pago']");
    	driver.findElement(By.className("android.widget.EditText")).sendKeys("10.00");
    	//scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='Descargar']");
    	Assert.assertTrue(false); //Mensaje de error al intentar descargar el cupon
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 16)
    public void Recargas_Recarga_SOS_MIX() {
    	boolean msj = false;
    	loginPorLineaMobile(driver, lineaMIX);
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='PAGOS, RECARGAS Y PACKS']");
    	driver.findElement(By.xpath("//*[@text='RECARGA S.O.S.']")).click();
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='RECARG\u00c1 AHORA']");
    	for (WebElement x : driver.findElements(By.className("android.widget.TextView"))) {
    		if (x.getText().contains("La recarga S.O.S. se realiz\u00f3 con \u00e9xito"))
    			msj = true;
    	}
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.Button[@text='ACEPTAR']");
    	Assert.assertTrue(msj);
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 17)
    public void Recargas_Recarga_SOS_PRE() {
    	boolean msj = false;
    	loginPorLineaMobile(driver, lineaPre);
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='RECARGAS Y PACKS']");
    	driver.findElement(By.xpath("//*[@text='RECARGA S.O.S.']")).click();
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='RECARG\u00c1 AHORA']");
    	for (WebElement x : driver.findElements(By.className("android.widget.TextView"))) {
    		if (x.getText().contains("La recarga S.O.S. se realiz\u00f3 con \u00e9xito"))
    			msj = true;
    	}
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.Button[@text='ACEPTAR']");
    	Assert.assertTrue(msj);
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 18)
    public void Recargas_Recarga_con_puntos_Club_MIX() {
    	loginPorLineaMobile(driver, "1168829219");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='PAGOS, RECARGAS Y PACKS']");
    	driver.findElement(By.xpath("//*[@class='android.widget.RelativeLayout' and ./*[@text='Puntos Club']]")).click();
    	scrollAndClickAndroid(driver, "xpath", "//android.view.View[@text='RECARGAS']");
    	scrollAndClickAndroid(driver, "xpath", "//android.view.View[contains(text(),'Cr\u00e9dito $10')]");
    	//scrollAndClickAndroid(driver, "xpath", "//android.view.View[@text='CANJEAR']");
    	Assert.assertTrue(false);  //Mensaje de error al hacer click en canjear
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 19)
    public void Recargas_Recarga_con_puntos_Club_PRE() {
    	loginPorLineaMobile(driver, "1164473518");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='RECARGAS Y PACKS']");
    	driver.findElement(By.xpath("//*[@class='android.widget.RelativeLayout' and ./*[@text='Puntos Club']]")).click();
    	scrollAndClickAndroid(driver, "xpath", "//android.view.View[@text='RECARGAS']");
    	scrollAndClickAndroid(driver, "xpath", "//android.view.View[contains(text(),'Cr\u00e9dito $10')]");
    	//scrollAndClickAndroid(driver, "xpath", "//android.view.View[@text='CANJEAR']");
    	Assert.assertTrue(false);  //Mensaje de error al hacer click en canjear
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 20)
    public void Recargas_Gestiones_y_Consultas_Ultimas_Recargas_MIX() {
    	int recargas = 0;
    	loginPorLineaMobile(driver, lineaMIX);
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='PAGOS, RECARGAS Y PACKS']");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='Historial de Recargas']");
    	List<MobileElement> tabla = driver.findElement(By.xpath("//*[@class='android.widget.LinearLayout' and ./*[@id='complex_component_layout']]")).findElements(By.className("android.widget.TextView"));
    	for (int i=0; i<tabla.size(); i++) {
    		if (tabla.get(i).getText().contains("Recarga por Tarjeta de Credito"))
    			recargas++;
    	}
    	Assert.assertTrue(recargas >= 1);
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 21)
    public void Recargas_Gestiones_y_Consultas_Ultimas_Recargas_PRE() {
    	int recargas = 0;
    	loginPorLineaMobile(driver, lineaPre);
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='RECARGAS Y PACKS']");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='Historial de Recargas']");
    	List<MobileElement> tabla = driver.findElement(By.xpath("//*[@class='android.widget.LinearLayout' and ./*[@id='complex_component_layout']]")).findElements(By.className("android.widget.TextView"));
    	for (int i=0; i<tabla.size(); i++) {
    		if (tabla.get(i).getText().contains("Recarga por Tarjeta de Credito"))
    			recargas++;
    	}
    	Assert.assertTrue(recargas >= 1);
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 22)
    public void Ayuda_Acceder_a_AYUDA_y_seleccionar_alguna_opcion() {
    	boolean ayuda = false;
    	loginPorLineaMobile(driver, lineaPre);
    	driver.swipe(315, 154, -12, 154, 84);
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='AYUDA']");
    	driver.findElement(By.xpath("//*[@text='d. T\u00e9cnico | Personal Help - Argentina']")).click();
    	sleep(7000);
    	for (WebElement x : driver.findElements(By.className("android.view.View"))) {
    		if (x.getText().contains("Mi equipo no reconoce la Sim"))
    			ayuda = true;
    	}
    	Assert.assertTrue(ayuda);
    }
    
    @Test (groups = "SinAfterMethod", priority = 23)
    public void Login_Iniciar_Sesion_con_clave_Incorrecta() {
    	loginPorLineaMobile(driver, lineaMIX);
    	Assert.assertTrue(verificarLogin(driver, lineaMIX, "1470"));
    }
    
    @Test (groups = "SinAfterMethod", priority = 24)
    public void Login_Iniciar_Sesion_sin_clave() {
    	loginPorLineaMobile(driver, lineaMIX);
    	Assert.assertTrue(verificarLogin(driver, lineaMIX, ""));
    }
    
    @Test (groups = "SinAfterMethod", priority = 25)
    public void Login_Iniciar_Sesion_con_Linea_Inexistente() {
    	loginPorLineaMobile(driver, lineaMIX);
    	Assert.assertTrue(verificarLogin(driver, "1192735149", "1469"));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 26)
    public void Login_Iniciar_Sesion_con_Linea_PreActiva() {
    	loginPorLineaMobile(driver, "1162645152");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='MIS SERVICIOS']");
    	Assert.assertTrue(false);  //No se visualiza el mensaje de linea pre desactivada
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 27)
    public void Comprar_Packs_Compra_de_Packs_con_puntos_Club_MIX() {
    	loginPorLineaMobile(driver, "1168829219");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='PAGOS, RECARGAS Y PACKS']");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='Con Puntos Club Personal']");
    	sleep(5000);
    	scrollAndClickAndroid(driver, "xpath", "//android.view.View[@text='ROAMING']");
    	scrollAndClickAndroid(driver, "xpath", "//android.view.View[@text='SMS']");
    	scrollAndClickAndroid(driver, "xpath", "//android.view.View[contains(text(),'NUEVO PREMIO PACK SMS Roaming Lim\u00edtrofes Pack de 10 SMS')]");
    	scrollAndClickAndroid(driver, "xpath", "//android.view.View[@text='CANJEAR']");
    	Assert.assertTrue(false);  //Mensaje de error al querer canjear
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 28)
    public void Comprar_Packs_Compra_de_Packs_con_puntos_Club_PRE() {
    	loginPorLineaMobile(driver, "1164473518");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='RECARGAS Y PACKS']");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='Con Puntos Club Personal']");
    	sleep(5000);
    	scrollAndClickAndroid(driver, "xpath", "//android.view.View[@text='ROAMING']");
    	scrollAndClickAndroid(driver, "xpath", "//android.view.View[@text='SMS']");
    	scrollAndClickAndroid(driver, "xpath", "//android.view.View[contains(text(),'NUEVO PREMIO PACK SMS Roaming Lim\u00edtrofes Pack de 10 SMS')]");
    	scrollAndClickAndroid(driver, "xpath", "//android.view.View[@text='CANJEAR']");
    	Assert.assertTrue(false);  //Mensaje de error al querer canjear
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 29)
    public void Facturacion_Ver_Facturas_MIX() {
    	boolean cantFacturas = false;
    	loginPorLineaMobile(driver, "1162733281");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='INICIO']");
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
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 30)
    public void Facturacion_Ver_Facturas_POS() {
    	boolean cantFacturas = false;
    	loginPorLineaMobile(driver, "1165990597");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='INICIO']");
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
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 31)
    public void Facturacion_Pagar_Factura_MIX() {
    	boolean factura = false;
    	loginPorLineaMobile(driver, "1162733281");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='PAGOS, RECARGAS Y PACKS']");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='Mis Facturas']");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='Pagar']");
    	sleep(5000);
    	for (WebElement x : driver.findElements(By.className("android.view.View"))) {
    		if (x.getText().contains("Detalles del pago"))
    			factura = true;
    	}
    	Assert.assertTrue(factura);
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 32)
    public void Facturacion_Pagar_Factura_POS() {
    	boolean factura = false;
    	loginPorLineaMobile(driver, "1165990597");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='PAGOS Y PACKS']");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='Mis Facturas']");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='Pagar']");
    	sleep(5000);
    	for (WebElement x : driver.findElements(By.className("android.view.View"))) {
    		if (x.getText().contains("Detalles del pago"))
    			factura = true;
    	}
    	Assert.assertTrue(factura);
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 33)
    public void Facturacion_Informar_un_Pago_MIX() {
    	loginPorLineaMobile(driver, "1162733281");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='PAGOS, RECARGAS Y PACKS']");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='Informar Pago']");
    	driver.findElement(By.id("textinput_component_edittext")).sendKeys("29/01/2019");
    	driver.findElement(By.id("textinput_component_edittext_currency")).sendKeys("150.00");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='ENVIAR']");
    	scrollAndClickAndroid(driver, "id", "button2");
    	scrollAndClickAndroid(driver, "id", "button1");
    	sleep(5000);
    	scrollAndClickAndroid(driver, "id", "button1");
    	Assert.assertTrue(false);  //Mensaje de error no se puede informar un pago por el momento
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 34)
    public void Facturacion_Informar_un_Pago_POS() {
    	loginPorLineaMobile(driver, "3413130145");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='PAGOS Y PACKS']");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='Informar Pago']");
    	driver.findElement(By.id("textinput_component_edittext")).sendKeys("29/01/2019");
    	driver.findElement(By.id("textinput_component_edittext_currency")).sendKeys("150.00");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='ENVIAR']");
    	scrollAndClickAndroid(driver, "id", "button2");
    	scrollAndClickAndroid(driver, "id", "button1");
    	sleep(5000);
    	scrollAndClickAndroid(driver, "id", "button1");
    	Assert.assertTrue(false);  //Mensaje de error no se puede informar un pago por el momento
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 35)
    public void Facturacion_Notas_de_Credito_y_Debito_MIX() {
    	loginPorLineaMobile(driver, "1161120234");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='PAGOS, RECARGAS Y PACKS']");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='Mis Facturas']");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='Notas fiscales']");
    	for (WebElement x : driver.findElements(By.className("android.view.View"))) {
    		if (x.getText().contains("Descargar")) {
    			x.click();
    			break;
    		}
    	}
    	sleep(5000);
    	driver.swipe(159, 28, 145, 498, 289);
        scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='MiPersonal UAT']");
        sleep(5000);
        try {
        	Assert.assertTrue(driver.findElement(By.id("projector_toolbar")).isDisplayed());
         	driver.navigate().back();
        } catch(NoSuchElementException e) {
        	driver.navigate().back();
         	Assert.assertTrue(false);
        }
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 36)
    public void Facturacion_Notas_de_Credito_y_Debito_POS() {
    	loginPorLineaMobile(driver, lineaPos);
    	Assert.assertTrue(false);  //No estan funcionando las lineas Pos
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='RECARGAS Y PACKS']");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='Mis Facturas']");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='Notas fiscales']");
    	for (WebElement x : driver.findElements(By.className("android.view.View"))) {
    		if (x.getText().contains("Descargar")) {
    			x.click();
    			break;
    		}
    	}
    	sleep(5000);
    	driver.swipe(159, 28, 145, 498, 289);
        scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='MiPersonal UAT']");
        sleep(5000);
        try {
        	Assert.assertTrue(driver.findElement(By.id("projector_toolbar")).isDisplayed());
         	driver.navigate().back();
        } catch(NoSuchElementException e) {
        	driver.navigate().back();
         	Assert.assertTrue(false);
        }
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 37)
    public void Mi_Linea_Numeros_Amigos_para_Hablar_SMS_Activacion_MIX() {
    	loginPorLineaMobile(driver, "1164461283");
    	Assert.assertTrue(activYElimVozYSMS(driver, "MIX"));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 38)
    public void Mi_Linea_Numeros_Amigos_para_Hablar_SMS_Activacion_PRE() {
    	loginPorLineaMobile(driver, "1164480623");
    	Assert.assertTrue(activYElimVozYSMS(driver, "Pre"));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 39)
    public void Mi_Linea_Baja_de_Suscripciones_PRE() {
    	loginPorLineaMobile(driver, lineaPre);
    	Assert.assertTrue(bajaDeSuscripcion(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 40)
    public void Mi_Linea_Baja_de_Suscripciones_POS() {
    	loginPorLineaMobile(driver, "1164599450");
    	Assert.assertTrue(bajaDeSuscripcion(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 41)
    public void Mi_Linea_Baja_de_Suscripciones_MIX() {
    	loginPorLineaMobile(driver, lineaMIX);
    	Assert.assertTrue(bajaDeSuscripcion(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 42)
    public void Mis_Datos_Mi_Perfil_Cambio_de_Domicilio_MIX() {
    	loginPorLineaMobile(driver, "1162749941");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='MIS SERVICIOS']");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='Domicilio de Facturaci\u00f3n']");
    	//String direccionVieja = driver.findElement(By.id("textinput_component_edittext")).getText();
    	String direccionNueva = "Av Rivadavia";
    	driver.findElement(By.id("textinput_component_edittext")).clear();
    	driver.findElement(By.id("textinput_component_edittext")).sendKeys(direccionNueva);
    	driver.findElement(By.xpath("(//*[@id='complex_component_layout']/*/*[@id='textinput_component_edittext_number'])[3]")).clear();
    	driver.findElement(By.xpath("(//*[@id='complex_component_layout']/*/*[@id='textinput_component_edittext_number'])[3]")).sendKeys("33851579");
    	//scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='GUARDAR']");
    	Assert.assertTrue(false);  //Mensaje de error no se puede modificar domicilio en este momento
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 43)
    public void Mis_Datos_Mi_Perfil_Cambio_de_Domicilio_POS() {
    	loginPorLineaMobile(driver, "1164599450");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='MIS SERVICIOS']");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='Domicilio de Facturaci\u00f3n']");
    	//String direccionVieja = driver.findElement(By.id("textinput_component_edittext")).getText();
    	String direccionNueva = "Av Rivadavia";
    	driver.findElement(By.id("textinput_component_edittext")).clear();
    	driver.findElement(By.id("textinput_component_edittext")).sendKeys(direccionNueva);
    	driver.findElement(By.xpath("(//*[@id='complex_component_layout']/*/*[@id='textinput_component_edittext_number'])[3]")).clear();
    	driver.findElement(By.xpath("(//*[@id='complex_component_layout']/*/*[@id='textinput_component_edittext_number'])[3]")).sendKeys("30444584");
    	//scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='GUARDAR']");
    	Assert.assertTrue(false);  //Mensaje de error no se puede modificar domicilio en este momento
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 44)
    public void Mis_Datos_Mi_Perfil_Cambio_de_Mail() {
    	loginPorLineaMobile(driver, "1164599450");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='MIS SERVICIOS']");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='Configurar mi e-mail']");
    	String mailViejo = driver.findElement(By.className("android.widget.EditText")).getText();
    	System.out.println(mailViejo);
    	driver.findElement(By.className("android.widget.EditText")).clear();
    	driver.findElement(By.className("android.widget.EditText")).sendKeys("testmail@gmail.com");
    	//scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='GUARDAR']");
    	Assert.assertTrue(false);  //Mensaje de error no se puede modificar mail en este momento
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 45)
    public void Inicio_Mis_disponibles_Internet_MIX() {
    	loginPorLineaMobile(driver, lineaMIX);
    	Assert.assertTrue(verifDisponibles(driver, "MIX"));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 46)
    public void Inicio_Mis_disponibles_Internet_PRE() {
    	loginPorLineaMobile(driver, lineaPre);
    	Assert.assertTrue(verifDisponibles(driver, "Pre"));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 47)
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
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 48)
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
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 49)
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
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 50)
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
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 51)
    public void Mis_Consumos_Doble_Blue_Activar_MIX() {
    	loginPorLineaMobile(driver, "1164491372");
    	boolean activ = false, desact = false;
    	driver.findElement(By.xpath("//*[@id='button_component_layout']")).click();
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='AMPLIAR MI CUOTA A 200 MB']");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='CONFIRMAR']");
    	if (driver.findElement(By.id("dialog_alert_message")).getText().contains("Felicitaciones! Activaste la cuota Internet 200MB por Dia"))
    		activ = true;
    	scrollAndClickAndroid(driver, "id", "button1");
    	scrollAndClickAndroid(driver, "id", "iv_chevron");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='Modificar']");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='CONFIRMAR']");
    	if (driver.findElement(By.id("dialog_alert_message")).getText().contains("Se desactiv\u00f3 con \u00e9xito Internet 200MB por Dia"))
    		desact = true;
    	scrollAndClickAndroid(driver, "id", "button1");
    	Assert.assertTrue(activ && desact);
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 52)
    public void Mis_Consumos_Doble_Blue_Activar_PRE() {
    	loginPorLineaMobile(driver, "1164477818");
    	boolean activ = false, desact = false;
    	driver.findElement(By.xpath("//*[@id='button_component_layout']")).click();
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='AMPLIAR MI CUOTA A 200 MB']");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='CONFIRMAR']");
    	if (driver.findElement(By.id("dialog_alert_message")).getText().contains("La Cuota Internet 200MB por Dia fue activada con \u00e9xito"))
    		activ = true;
    	scrollAndClickAndroid(driver, "id", "button1");
    	scrollAndClickAndroid(driver, "id", "iv_chevron");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='Modificar']");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='REDUCIR MI CUOTA 50 MB']");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='CONFIRMAR']");
    	if (driver.findElement(By.id("dialog_alert_message")).getText().contains("La Cuota Internet X Dia fue activada con \u00e9xito"))
    		desact = true;
    	scrollAndClickAndroid(driver, "id", "button1");
    	Assert.assertTrue(activ && desact);
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 53)
    public void Pagos_Paga_con_Tarjeta_de_Credito_MIX() {
    	loginPorLineaMobile(driver, lineaMIX);
    	boolean factura = false;
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='PAGOS, RECARGAS Y PACKS']");
    	scrollAndClickAndroid(driver, "xpath", "//*[@id='button1' and ./*[@text='Pag\u00e1 con']]");
    	for (WebElement x : driver.findElements(By.className("android.view.View"))) {
    		if (x.getText().contains("Complet\u00e1 el siguiente formulario para finalizar el pago"))
    			factura = true;
    	}
    	Assert.assertTrue(factura);
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 54)
    public void Pagos_Paga_con_Tarjeta_de_Credito_POS() {
    	loginPorLineaMobile(driver, "1164599450");
    	boolean factura = false;
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='PAGOS Y PACKS']");
    	scrollAndClickAndroid(driver, "xpath", "//*[@id='button1' and ./*[@text='Pag\u00e1 con']]");
    	for (WebElement x : driver.findElements(By.className("android.view.View"))) {
    		if (x.getText().contains("Complet\u00e1 el siguiente formulario para finalizar el pago"))
    			factura = true;
    	}
    	Assert.assertTrue(factura);
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 55)
    public void Pagos_Paga_con_Pago_Mis_Cuentas_MIX() {
    	loginPorLineaMobile(driver, lineaMIX);
    	boolean pmc = false;
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='PAGOS, RECARGAS Y PACKS']");
    	scrollAndClickAndroid(driver, "xpath", "//*[@id='button2' and ./*[@text='Pag\u00e1 con']]");
    	for (WebElement x : driver.findElements(By.className("android.widget.TextView"))) {
    		if (x.getText().contains("Pago con Pago Mis Cuentas: Paso a paso"))
    			pmc = true;
    	}
    	Assert.assertTrue(pmc);
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 56)
    public void Pagos_Paga_con_Pago_Mis_Cuentas_POS() {
    	loginPorLineaMobile(driver, "1164599450");
    	boolean pmc = false;
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='PAGOS Y PACKS']");
    	scrollAndClickAndroid(driver, "id", "button2");
    	for (WebElement x : driver.findElements(By.className("android.widget.TextView"))) {
    		if (x.getText().contains("Pago con Pago Mis Cuentas: Paso a paso"))
    			pmc = true;
    	}
    	Assert.assertTrue(pmc);
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 57)
    public void Pagos_Pago_online_MIX() {
    	loginPorLineaMobile(driver, lineaMIX);
    	boolean tdc = false, hb = false, pmc = false, pagos = false;
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='PAGOS, RECARGAS Y PACKS']");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='Pago online']");
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
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 58)
    public void Pagos_Pago_online_POS() {
    	loginPorLineaMobile(driver, "1164599450");
    	boolean tdc = false, hb = false, pmc = false, pagos = false;
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='PAGOS Y PACKS']");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='Pago online']");
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
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 59)
    public void Pagos_Pago_Presencial_MIX() {
    	loginPorLineaMobile(driver, lineaMIX);
    	boolean pee = false, ca = false, pcc = false;
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='PAGOS, RECARGAS Y PACKS']");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='Pago Presencial']");
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
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 60)
    public void Pagos_Pago_Presencial_POS() {
    	loginPorLineaMobile(driver, "1164599450");
    	boolean pee = false, ca = false, pcc = false;
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='PAGOS Y PACKS']");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='Pago Presencial']");
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
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 61)
    public void Mis_Consumos_DataSharing_Alta_MIX() {
    	boolean activ = false, modif = false, elim = false;
    	loginPorLineaMobile(driver, "1164493210");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='MIS SERVICIOS']");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='Compart\u00ed Gigas']");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='COMENZAR']");
    	driver.findElement(By.className("android.widget.EditText")).sendKeys("11");
    	driver.findElements(By.className("android.widget.EditText")).get(1).sendKeys("64480754");
    	driver.findElements(By.className("android.widget.EditText")).get(2).sendKeys("1");
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.TextView[@text='AGREGAR']");
    	if (driver.findElement(By.id("dialog_alert_message")).getText().contains("Felicitaciones! El n\u00famero para compartir Internet se agreg\u00f3 con \u00e9xito."))
    		activ = true;
    	scrollAndClickAndroid(driver, "xpath", "//android.widget.Button[@text='ACEPTAR']");
    	driver.findElement(By.xpath("(//*[@id='double_component_main' and ./parent::*[./parent::*[@id='double_component_main']]]/*/*[@id='imageview_component_image'])[2]")).click();
    	driver.findElement(By.className("android.widget.EditText")).clear();
    	driver.findElement(By.className("android.widget.EditText")).sendKeys("2");
    	scrollAndClickAndroid(driver, "id", "button_component_layout");
    	if (driver.findElement(By.id("dialog_alert_message")).getText().contains("Se modific\u00f3 a 2 GB el limite de consumo de la l\u00ednea 1164480754"))
    		modif = true;
    	scrollAndClickAndroid(driver, "id", "button1");
    	driver.findElement(By.xpath("(//*[@id='double_component_main' and ./parent::*[./parent::*[@id='double_component_main']]]/*/*[@id='imageview_component_image'])[1]")).click();
    	scrollAndClickAndroid(driver, "id", "button1");
    	if (driver.findElement(By.id("dialog_alert_message")).getText().contains("El n\u00famero se elimin\u00f3 con \u00e9xito!"))
    		elim = true;
    	scrollAndClickAndroid(driver, "id", "button1");
    	Assert.assertTrue(activ && modif && elim);
    }
}