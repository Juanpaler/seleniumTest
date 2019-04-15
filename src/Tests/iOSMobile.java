package Tests;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;

import PageMetodos.MetodosiOS;

import java.net.URL;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;

public class iOSMobile extends MetodosiOS {

    private IOSDriver<IOSElement> driver = null;
    private DesiredCapabilities dc = new DesiredCapabilities();
    
	private String nombreCaso;
	private String archivoLineas="dataInput/LineasIOSMiCuenta.xlsx";
	private String modulo="MiCuentaAppIOS";
    
	@BeforeClass (groups = "AutogestionIndividuosAPP")
	public void init() throws IOException {
		dc.setCapability("reportDirectory", "reports");
		dc.setCapability("reportFormat", "xml");
		dc.setCapability("testName", "Untitled");
		dc.setCapability(MobileCapabilityType.UDID, "c2ced461f2d136211a630c1f06668a1771abd2b2");
		dc.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.personal.misconsumos.uat");
		reportDirectory(modulo);
	}
    
    @BeforeMethod (alwaysRun = true)
    public void before() throws MalformedURLException {
    	driver = new IOSDriver<>(new URL("http://localhost:4723/wd/hub"), dc);
        driver.setLogLevel(Level.INFO);
        try {
        	driver.findElement(By.id("Cancel")).click();
        } catch(Exception e) {}
    }
    
    @AfterMethod (alwaysRun = true)
    public void after() throws IOException {
    	sleep(5000);
    	getScreenshot(driver,nombreCaso);
    	int menu = 0;
    	try {
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
    public void Comprar_Packs_Compra_de_Packs_con_puntos_Club_MIX() throws IOException  {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
    	String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarCompraPacksPuntosClub(driver));  //Mensaje de error al intentar canjear
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 3)
    public void Comprar_Packs_Compra_de_Packs_con_puntos_Club_PRE() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
    	String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarCompraPacksPuntosClub(driver));  //Mensaje de error al intentar canjear
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 4)
    public void Comprar_Packs_Consultar_Comprobantes_MIX() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
    	String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarDescargaComprobante(driver));  //No se pueden descargar los comprobantes
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 5)
    public void Comprar_Packs_Consultar_Comprobantes_PRE() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
    	String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarDescargaComprobante(driver));  //No se pueden descargar los comprobantes
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 6)
    public void Consumos_Detalles_de_Consumos_MIX() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
    	String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarDetallesDeConsumos(driver, "MIX o Pre"));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 7)
    public void Consumos_Detalles_de_Consumos_POS() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
    	String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarDetallesDeConsumos(driver, "Pos"));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 8)
    public void Consumos_Detalles_de_Consumos_PRE() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
    	String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarDetallesDeConsumos(driver, "MIX o Pre"));
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
    public void Facturacion_Imprimir_cupon_de_pago_MIX() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
    	String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(imprimirCuponDePago(driver));  //Mensaje de error al intentar descargar el cupon
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 12)
    public void Facturacion_Imprimir_cupon_de_pago_POS() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
    	String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(imprimirCuponDePago(driver));  //Mensaje de error al intentar descargar el cupon
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 13)
    public void Facturacion_Informar_un_Pago_MIX() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
    	String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(informarUnPago(driver));  //No deja ingresar el importe
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 14)
    public void Facturacion_Informar_un_Pago_POS() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
    	String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(informarUnPago(driver));  //No deja ingresar el importe
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 15)
    public void Facturacion_Notas_de_Credito_y_Debito_MIX() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
    	String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(descargaNotaDeCreditoYDebito(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 16)
    public void Facturacion_Notas_de_Credito_y_Debito_POS() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
    	String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(descargaNotaDeCreditoYDebito(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 17)
    public void Facturacion_Pagar_Factura_MIX() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
    	String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarDetalleDePagoConTarjeta(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 18)
    public void Facturacion_Pagar_Factura_POS() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
    	String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarDetalleDePagoConTarjeta(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 19)
    public void Facturacion_Ver_Facturas_MIX() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
    	String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verFacturas(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 20)
    public void Facturacion_Ver_Facturas_POS() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
    	String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verFacturas(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 21)
    public void Mi_Linea_Baja_de_Suscripciones_MIX() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
    	String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(bajaDeSuscripcion(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 22)
    public void Mi_Linea_Baja_de_Suscripciones_POS() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
    	String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(bajaDeSuscripcion(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 23)
    public void Mi_Linea_Baja_de_Suscripciones_PRE() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
    	String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(bajaDeSuscripcion(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 24)
    public void MI_Linea_Roaming_y_LDI_habilitado_MIX() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
    	String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarRoamingYLDIActivos(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 25)
    public void MI_Linea_Roaming_y_LDI_habilitado_POS() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
    	String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarRoamingYLDIActivos(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 26)
    public void MI_Linea_Roaming_y_LDI_habilitado_PRE() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
    	String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarRoamingYLDIActivos(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 27)
    public void Recargas_Gestiones_y_Consultas_Recargar_Ahora_MIX() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
    	String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarMetodosDeRecarga(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 28)
    public void Recargas_Gestiones_y_Consultas_Recargar_Ahora_PRE() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
    	String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarMetodosDeRecarga(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 29)
    public void Recargas_Recarga_SOS_MIX() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
    	String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarRecargaSOS(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 30)
    public void Recargas_Recarga_SOS_PRE() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
    	String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarRecargaSOS(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 31)
    public void Recargas_Recarga_con_puntos_Club_MIX() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
    	String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarRecargaPuntosClub(driver));  //Mensaje de error al hacer click en canjear
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 32)
    public void Recargas_Recarga_con_puntos_Club_PRE() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
    	String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarRecargaPuntosClub(driver));  //Mensaje de error al hacer click en canjear
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 33)
    public void Recargas_Gestiones_y_Consultas_Ultimas_Recargas_MIX() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
    	String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarUltimasRecargas(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 34)
    public void Recargas_Gestiones_y_Consultas_Ultimas_Recargas_PRE() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
    	String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarUltimasRecargas(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 35)
    public void Ayuda_Acceder_a_AYUDA_y_seleccionar_alguna_opcion() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
    	String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	boolean ayuda = false;
    	scrollAndClick(driver, "id", "Ayuda");
    	sleep(10000);
    	scrollAndClick(driver, "id", "Mi equipo no reconoce la Sim");
    	if (driver.findElement(By.id("Mi equipo no reconoce la Sim")).isEnabled())
    		ayuda = true;
    	Assert.assertTrue(ayuda);
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 36)
    public void Login_Iniciar_Sesion_con_clave_Incorrecta() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
    	String linea=retornaLinea(nombreCaso,archivoLineas);
    	driver.findElement(By.className("UIATextField")).sendKeys(linea);
        driver.findElement(By.id("INGRESAR CON CLAVE PERSONAL")).click();
        driver.findElement(By.xpath("//*[@class='UIAView' and (./preceding-sibling::* | ./following-sibling::*)[@text='Clave num�rica'] and ./parent::*[@class='UIAView']]")).sendKeys("1470");
        driver.findElement(By.id("INGRESAR A MI PERSONAL UAT")).click();
    	Boolean mensajeIncorrecto = (driver.findElement(By.id("Los datos ingresados son incorrectos")).getText().contains("Los datos ingresados son incorrectos"));
        scrollAndClick(driver, "id", "Aceptar");
    	Assert.assertTrue(mensajeIncorrecto);
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 37)
    public void Login_Iniciar_Sesion_sin_clave() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
    	String linea=retornaLinea(nombreCaso,archivoLineas);
    	driver.findElement(By.className("UIATextField")).sendKeys(linea);
        driver.findElement(By.id("INGRESAR CON CLAVE PERSONAL")).click();
        //driver.findElement(By.xpath("//*[@class='UIAView' and (./preceding-sibling::* | ./following-sibling::*)[@text='Clave num�rica'] and ./parent::*[@class='UIAView']]")).sendKeys("");
        driver.findElement(By.id("INGRESAR A MI PERSONAL UAT")).click();
    	Boolean mensajeIncorrecto = (driver.findElement(By.id("Debes completar los campos!")).getText().contains("Debes completar los campos!"));
        scrollAndClick(driver, "id", "Aceptar");
    	Assert.assertTrue(mensajeIncorrecto);
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 38)
    public void Login_Iniciar_Sesion_con_Linea_Inexistente() {
    	driver.findElement(By.className("UIATextField")).sendKeys("1111111111");
        driver.findElement(By.id("INGRESAR CON CLAVE PERSONAL")).click();
        driver.findElement(By.xpath("//*[@class='UIAView' and (./preceding-sibling::* | ./following-sibling::*)[@text='Clave num�rica'] and ./parent::*[@class='UIAView']]")).sendKeys("4356");
        driver.findElement(By.id("INGRESAR A MI PERSONAL UAT")).click();
    	Boolean mensajeIncorrecto = (driver.findElement(By.id("Los datos ingresados son incorrectos")).getText().contains("Los datos ingresados son incorrectos"));
        scrollAndClick(driver, "id", "Aceptar");
    	Assert.assertTrue(mensajeIncorrecto);
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 39)
    public void Login_Iniciar_Sesion_con_Linea_PreActiva() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
    	String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	scrollAndClick(driver, "id", "Mis Servicios");
    	Assert.fail();  //No se visualiza el mensaje de linea pre desactivada
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 40)
    public void Inicio_Detalle_de_credito_MIX() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
    	String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarDetalleDeCredito(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 41)
    public void Inicio_Detalle_de_credito_PRE() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
    	String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarDetalleDeCredito(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 42)
    public void Inicio_Mis_disponibles_Internet_MIX() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
    	String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verifDisponibles(driver, "MIX"));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 43)
    public void Inicio_Mis_disponibles_Internet_PRE() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
    	String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verifDisponibles(driver, "Pre"));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 44)
    public void Inicio_Mis_disponibles_Mis_Facturas_MIX() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
    	String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
        Assert.assertTrue(verificarFactura(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 45)
    public void Inicio_Mis_disponibles_Mis_Facturas_POS() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
    	String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarFactura(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 46)
    public void Pagos_Paga_con_Tarjeta_de_Credito_MIX() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
    	String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarPagoConTarjetaDeCredito(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 47)
    public void Pagos_Paga_con_Tarjeta_de_Credito_POS() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
    	String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarPagoConTarjetaDeCredito(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 48)
    public void Pagos_Paga_con_Pago_Mis_Cuentas_MIX() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
    	String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarPagoConPagoMisCuentas(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 49)
    public void Pagos_Paga_con_Pago_Mis_Cuentas_POS() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
    	String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarPagoConPagoMisCuentas(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 50)
    public void Pagos_Pago_online_MIX() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
    	String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarPagoOnline(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 51)
    public void Pagos_Pago_online_POS() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
    	String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarPagoOnline(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 52)
    public void Pagos_Pago_Presencial_MIX() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
    	String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarPagoPresencial(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 53)
    public void Pagos_Pago_Presencial_POS() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
    	String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarPagoPresencial(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 54)
    public void Mis_Consumos_Doble_Blue_Activar_PRE() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
    	String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarActivYDesactDobleBlue(driver, "Pre"));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 55)
    public void Mis_Consumos_Doble_Blue_Activar_MIX() throws IOException {
    	nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
    	String linea=retornaLinea(nombreCaso,archivoLineas);
    	loginPorLineaMobile(driver, linea);
    	Assert.assertTrue(verificarActivYDesactDobleBlue(driver, "MIX"));
    }
}