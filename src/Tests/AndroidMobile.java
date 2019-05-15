package Tests;

import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;

import PageMetodos.MetodosAndroid;

import java.net.URL;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;

public class AndroidMobile extends MetodosAndroid {

    private AndroidDriver<AndroidElement> driver = null;
    private DesiredCapabilities dc = new DesiredCapabilities();

	private String nombreCaso;
	private String archivoLineas="dataInput/LineasAndroidMiCuenta.xlsx";
	private String rutaCaptura;
	private String modulo="MiCuentaAppAndroid";
    
    @BeforeClass (alwaysRun = true)
    public void init() throws IOException {
    	dc.setCapability("reportDirectory", "reports");
        dc.setCapability("reportFormat", "xml");
        dc.setCapability("testName", "Untitled");
        dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        dc.setCapability(MobileCapabilityType.UDID, "42004754d431448d");
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "ar.com.personal.bandaruattp");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "ar.com.personal.app.activities.bandar.SplashActivity");
        dc.setCapability(MobileCapabilityType.NO_RESET, true);
        rutaCaptura=reportDirectory(modulo);
    }
    
    @BeforeMethod (alwaysRun = true)
    public void before() throws MalformedURLException {
    	driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), dc);
        driver.setLogLevel(Level.INFO);
    }
    
    @AfterMethod (alwaysRun = true)
    public void after() throws IOException {
    	sleep(5000);
    	getScreenshot(driver,nombreCaso,rutaCaptura);
    	int menu = 0;
    	try {
    	while(!driver.findElement(By.id("custom_ab_title")).getText().contains("Mi Personal") && menu < 3) {
        	driver.navigate().back();
        	menu++;
        }	
    	sleep(3000);
    	scrollAndClick(driver, "id", "custom_ab_drawer");
    	sleep(5000);
    	scrollAndClick(driver, "xpath", "(//*[@id='table_container_menu']/*/*[@id='text_component_title_custom'])[2]");
    	sleep(5000);
    	driver.quit();
		} catch (Exception e) {
	    	driver.quit();
		}
    }
    
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 0)
    public void Comprar_Packs_Compra_de_Packs_MIX() throws IOException {
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarCompraDePacks(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 1)
    public void Comprar_Packs_Compra_de_Packs_PRE() throws IOException {
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarCompraDePacks(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 2)
    public void Comprar_Packs_Consultar_Comprobantes_MIX() throws IOException {
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarDescargaDeComprobante(driver)); //Mensaje de error en descarga de comprobante
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 3)
    public void Comprar_Packs_Consultar_Comprobantes_PRE() throws IOException {
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarDescargaDeComprobante(driver)); //Mensaje de error en descarga de comprobante
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 4)
    public void Consumos_Detalles_de_Consumos_MIX() throws IOException {
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarDetallesDeConsumos(driver, "MIX o Pre"));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 5)
    public void Consumos_Detalles_de_Consumos_POS() throws IOException {
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarDetallesDeConsumos(driver, "Pos"));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 6)
    public void Consumos_Detalles_de_Consumos_PRE() throws IOException {
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarDetallesDeConsumos(driver, "MIX o Pre"));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 7)
    public void Recargas_Gestiones_y_Consultas_Recargar_Ahora_PRE() throws IOException {
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarMetodosDeRecarga(driver));
    }
    
   @Test (groups = "AutogestionIndividuosAPP", priority = 8)
    public void Recargas_Gestiones_y_Consultas_Recargar_Ahora_MIX() throws IOException {
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
   	    loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarMetodosDeRecarga(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 9)
    public void Facturacion_Compras_Realizadas_Descargar_Comprobante_MIX() throws IOException {
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
   	    loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(descargaComprobanteDeCompra(driver, "MIX"));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 10)
    public void Facturacion_Compras_Realizadas_Descargar_Comprobante_POS() throws IOException {
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
   	    loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(descargaComprobanteDeCompra(driver, "Pos"));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 11)
    public void MI_Linea_Roaming_y_LDI_habilitado_MIX() throws IOException {
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
   	    loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarRoamingYLDIActivos(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 12)
    public void MI_Linea_Roaming_y_LDI_habilitado_POS() throws IOException {
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
   	    loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarRoamingYLDIActivos(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 13)
    public void MI_Linea_Roaming_y_LDI_habilitado_PRE() throws IOException {
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
   	    loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarRoamingYLDIActivos(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 14)
    public void Facturacion_Imprimir_cupon_de_pago_MIX() throws IOException {
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
   	    loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarImprimirCupon(driver));  //Mensaje de error al intentar descargar el cupon
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 15)
    public void Facturacion_Imprimir_cupon_de_pago_POS() throws IOException {
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
   	    loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarImprimirCupon(driver));  //Mensaje de error al intentar descargar el cupon
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 16)
    public void Recargas_Recarga_SOS_MIX() throws IOException {
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
   	    loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarRecargaSOS(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 17)
    public void Recargas_Recarga_SOS_PRE() throws IOException {
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
   	    loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarRecargaSOS(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 18)
    public void Recargas_Recarga_con_puntos_Club_MIX() throws IOException {
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
   	    loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarRecargaPuntosClub(driver));  //Mensaje de error al hacer click en canjear
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 19)
    public void Recargas_Recarga_con_puntos_Club_PRE() throws IOException {
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
   	    loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarRecargaPuntosClub(driver));  //Mensaje de error al hacer click en canjear
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 20)
    public void Recargas_Gestiones_y_Consultas_Ultimas_Recargas_MIX() throws IOException {
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
   	    loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarUltimasRecargas(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 21)
    public void Recargas_Gestiones_y_Consultas_Ultimas_Recargas_PRE() throws IOException {
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
   	    loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarUltimasRecargas(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 22)
    public void Ayuda_Acceder_a_AYUDA_y_seleccionar_alguna_opcion() throws IOException {
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
   	    loginPorLineaMobile(driver, linea);
    	boolean ayuda = false;
    	driver.swipe(315, 154, -12, 154, 84);
    	scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='AYUDA']");
    	driver.findElement(By.xpath("//*[@text='d. T\u00e9cnico | Personal Help - Argentina']")).click();
    	sleep(7000);
    	for (WebElement x : driver.findElements(By.className("android.view.View"))) {
    		if (x.getText().contains("Mi equipo no reconoce la Sim"))
    			ayuda = true;
    	}
    	Assert.assertTrue(ayuda);
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 23)
    public void Login_Iniciar_Sesion_con_clave_Incorrecta() throws IOException {
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
   	    loginPorLineaMobile(driver, linea);
    	driver.findElement(By.id("editTextLinea")).sendKeys(linea);
        driver.findElement(By.id("btn_log_in")).click();
        driver.findElement(By.id("editTextPin")).sendKeys("1111");
        driver.findElement(By.id("btn_log_in")).click();
    	Boolean mensajeIncorrecto = (driver.findElement(By.id("message")).getText().contains("Los datos ingresados son incorrectos"));
        scrollAndClick(driver, "id", "button3");
    	Assert.assertTrue(mensajeIncorrecto);
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 24)
    public void Login_Iniciar_Sesion_sin_clave() throws IOException {
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
   	    loginPorLineaMobile(driver, linea);
    	driver.findElement(By.id("editTextLinea")).sendKeys(linea);
        driver.findElement(By.id("btn_log_in")).click();
        driver.findElement(By.id("editTextPin")).sendKeys("");
        driver.findElement(By.id("btn_log_in")).click();
    	Boolean mensajeIncorrecto = (driver.findElement(By.id("message")).getText().contains("Los datos ingresados son incorrectos"));
        scrollAndClick(driver, "id", "button3");
    	Assert.assertTrue(mensajeIncorrecto);
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 25)
    public void Login_Iniciar_Sesion_con_Linea_Inexistente() {
    	driver.findElement(By.id("editTextLinea")).sendKeys("1132465000");
        driver.findElement(By.id("btn_log_in")).click();
        driver.findElement(By.id("editTextPin")).sendKeys("1469");
        driver.findElement(By.id("btn_log_in")).click();
    	Boolean mensajeIncorrecto = (driver.findElement(By.id("message")).getText().contains("Los datos ingresados son incorrectos"));
        scrollAndClick(driver, "id", "button3");
    	Assert.assertTrue(mensajeIncorrecto);
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 26)
    public void Login_Iniciar_Sesion_con_Linea_PreActiva() throws IOException {
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
   	    loginPorLineaMobile(driver, linea);
    	scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='MIS SERVICIOS']");
    	Assert.assertTrue(false);  //No se visualiza el mensaje de linea pre desactivada
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 27)
    public void Comprar_Packs_Compra_de_Packs_con_puntos_Club_MIX() throws IOException {
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
   	    loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(compraDePacksPuntosClub(driver));  //Mensaje de error al querer canjear
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 28)
    public void Comprar_Packs_Compra_de_Packs_con_puntos_Club_PRE() throws IOException {
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
   	    loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(compraDePacksPuntosClub(driver));  //Mensaje de error al querer canjear
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 29)
    public void Facturacion_Ver_Facturas_MIX() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
   	    loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verFacturas(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 30)
    public void Facturacion_Ver_Facturas_POS() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
   	    loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verFacturas(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 31)
    public void Facturacion_Pagar_Factura_MIX() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
   	    loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarDetalleDePagoConTarjeta(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 32)
    public void Facturacion_Pagar_Factura_POS() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
   	    loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarDetalleDePagoConTarjeta(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 33)
    public void Facturacion_Informar_un_Pago_MIX() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
   	    loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarInformarUnPago(driver));  //Mensaje de error no se puede informar un pago por el momento
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 34)
    public void Facturacion_Informar_un_Pago_POS() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
   	    loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarInformarUnPago(driver));  //Mensaje de error no se puede informar un pago por el momento
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 35)
    public void Facturacion_Notas_de_Credito_y_Debito_MIX() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
   	    loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarDescargaNotaDeCreditoYDebito(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 36)
    public void Facturacion_Notas_de_Credito_y_Debito_POS() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
   	    loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarDescargaNotaDeCreditoYDebito(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 37)
    public void Mi_Linea_Numeros_Amigos_para_Hablar_SMS_Activacion_MIX() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
   	    loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(activYElimVozYSMS(driver, "MIX"));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 38)
    public void Mi_Linea_Numeros_Amigos_para_Hablar_SMS_Activacion_PRE() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
   	    loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(activYElimVozYSMS(driver, "Pre"));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 39)
    public void Mi_Linea_Baja_de_Suscripciones_PRE() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
   	    loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(bajaDeSuscripcion(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 40)
    public void Mi_Linea_Baja_de_Suscripciones_POS() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
   	    loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(bajaDeSuscripcion(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 41)
    public void Mi_Linea_Baja_de_Suscripciones_MIX() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
   	    loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(bajaDeSuscripcion(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 42)
    public void Mis_Datos_Mi_Perfil_Cambio_de_Domicilio_MIX() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
   	    loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(cambioDeDomicilio(driver, "33851579"));  //Mensaje de error no se puede modificar domicilio en este momento
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 43)
    public void Mis_Datos_Mi_Perfil_Cambio_de_Domicilio_POS() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
   	    loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(cambioDeDomicilio(driver, "30444584"));  //Mensaje de error no se puede modificar domicilio en este momento
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 44)
    public void Mis_Datos_Mi_Perfil_Cambio_de_Mail() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
   	    loginPorLineaMobile(driver, linea);
    	scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='MIS SERVICIOS']");
    	scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='Configurar mi e-mail']");
    	String mailViejo = driver.findElement(By.className("android.widget.EditText")).getText();
    	System.out.println(mailViejo);
    	driver.findElement(By.className("android.widget.EditText")).clear();
    	driver.findElement(By.className("android.widget.EditText")).sendKeys("testmail@gmail.com");
    	//scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='GUARDAR']");
    	Assert.fail();  //Mensaje de error no se puede modificar mail en este momento
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 45)
    public void Inicio_Mis_disponibles_Internet_MIX() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
   	    loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verifDisponibles(driver, "MIX"));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 46)
    public void Inicio_Mis_disponibles_Internet_PRE() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
   	    loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verifDisponibles(driver, "Pre"));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 47)
    public void Inicio_Detalle_de_credito_MIX() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
   	    loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarDetalleDeCredito(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 48)
    public void Inicio_Detalle_de_credito_PRE() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
   	    loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarDetalleDeCredito(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 49)
    public void Inicio_Mis_disponibles_Mis_Facturas_MIX() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
   	    loginPorLineaMobile(driver, linea);
        Assert.assertTrue(verificarFactura(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 50)
    public void Inicio_Mis_disponibles_Mis_Facturas_POS() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
   	    loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarFactura(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 51)
    public void Mis_Consumos_Doble_Blue_Activar_MIX() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
   	    loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarActivYDesactDobleBlue(driver, "MIX"));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 52)
    public void Mis_Consumos_Doble_Blue_Activar_PRE() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
   	    loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarActivYDesactDobleBlue(driver, "Pre"));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 53)
    public void Pagos_Paga_con_Tarjeta_de_Credito_MIX() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
   	    loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarPagoConTarjetaDeCredito(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 54)
    public void Pagos_Paga_con_Tarjeta_de_Credito_POS() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
   	    loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarPagoConTarjetaDeCredito(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 55)
    public void Pagos_Paga_con_Pago_Mis_Cuentas_MIX() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
   	    loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarPagoConPagoMisCuentas(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 56)
    public void Pagos_Paga_con_Pago_Mis_Cuentas_POS() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
   	    loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarPagoConPagoMisCuentas(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 57)
    public void Pagos_Pago_online_MIX() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
   	    loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarPagoOnline(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 58)
    public void Pagos_Pago_online_POS() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
   	    loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarPagoOnline(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 59)
    public void Pagos_Pago_Presencial_MIX() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
   	    loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarPagoPresencial(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 60)
    public void Pagos_Pago_Presencial_POS() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
   	    loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarPagoPresencial(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 61)
    public void Mis_Consumos_DataSharing_Alta_MIX() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName(); 	
		String linea=retornaLinea(nombreCaso,archivoLineas);
   	    loginPorLineaMobile(driver, linea);
    	boolean activ = false, modif = false, elim = false;
    	scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='MIS SERVICIOS']");
    	scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='Compart\u00ed Gigas']");
    	scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='COMENZAR']");
    	driver.findElement(By.className("android.widget.EditText")).sendKeys("11");
    	driver.findElements(By.className("android.widget.EditText")).get(1).sendKeys("64480754");
    	driver.findElements(By.className("android.widget.EditText")).get(2).sendKeys("1");
    	scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='AGREGAR']");
    	if (driver.findElement(By.id("dialog_alert_message")).getText().contains("Felicitaciones! El n\u00famero para compartir Internet se agreg\u00f3 con \u00e9xito."))
    		activ = true;
    	scrollAndClick(driver, "xpath", "//android.widget.Button[@text='ACEPTAR']");
    	driver.findElement(By.xpath("(//*[@id='double_component_main' and ./parent::*[./parent::*[@id='double_component_main']]]/*/*[@id='imageview_component_image'])[2]")).click();
    	driver.findElement(By.className("android.widget.EditText")).clear();
    	driver.findElement(By.className("android.widget.EditText")).sendKeys("2");
    	scrollAndClick(driver, "id", "button_component_layout");
    	if (driver.findElement(By.id("dialog_alert_message")).getText().contains("Se modific\u00f3 a 2 GB el limite de consumo de la l\u00ednea 1164480754"))
    		modif = true;
    	scrollAndClick(driver, "id", "button1");
    	driver.findElement(By.xpath("(//*[@id='double_component_main' and ./parent::*[./parent::*[@id='double_component_main']]]/*/*[@id='imageview_component_image'])[1]")).click();
    	scrollAndClick(driver, "id", "button1");
    	if (driver.findElement(By.id("dialog_alert_message")).getText().contains("El n\u00famero se elimin\u00f3 con \u00e9xito!"))
    		elim = true;
    	scrollAndClick(driver, "id", "button1");
    	Assert.assertTrue(activ && modif && elim);
    }
}