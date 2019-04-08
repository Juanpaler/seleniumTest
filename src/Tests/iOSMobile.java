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
import java.net.MalformedURLException;
import java.util.logging.Level;

public class iOSMobile extends MetodosiOS {

    private IOSDriver<IOSElement> driver = null;
    private DesiredCapabilities dc = new DesiredCapabilities();
    
    
	@BeforeClass (groups = "AutogestionIndividuosAPP")
	public void init() {
		dc.setCapability("reportDirectory", "reports");
		dc.setCapability("reportFormat", "xml");
		dc.setCapability("testName", "Untitled");
		dc.setCapability(MobileCapabilityType.UDID, "c2ced461f2d136211a630c1f06668a1771abd2b2");
		dc.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.personal.misconsumos.uat");
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
    public void after() {
    	sleep(5000);
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
    public void Comprar_Packs_Compra_de_Packs_MIX() {
    	loginPorLineaMobile(driver, lineaMIX);
    	Assert.assertTrue(verificarCompraDePacks(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 1)
    public void Comprar_Packs_Compra_de_Packs_PRE() {
    	loginPorLineaMobile(driver, lineaPre);
    	Assert.assertTrue(verificarCompraDePacks(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 2)
    public void Comprar_Packs_Compra_de_Packs_con_puntos_Club_MIX() {
    	loginPorLineaMobile(driver, "3854041917");
    	Assert.assertTrue(verificarCompraPacksPuntosClub(driver));  //Mensaje de error al intentar canjear
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 3)
    public void Comprar_Packs_Compra_de_Packs_con_puntos_Club_PRE() {
    	loginPorLineaMobile(driver, "1164473518");
    	Assert.assertTrue(verificarCompraPacksPuntosClub(driver));  //Mensaje de error al intentar canjear
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 4)
    public void Comprar_Packs_Consultar_Comprobantes_MIX() {
    	loginPorLineaMobile(driver, lineaMIX);
    	Assert.assertTrue(verificarDescargaComprobante(driver));  //No se pueden descargar los comprobantes
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 5)
    public void Comprar_Packs_Consultar_Comprobantes_PRE() {
    	loginPorLineaMobile(driver, lineaPre);
    	Assert.assertTrue(verificarDescargaComprobante(driver));  //No se pueden descargar los comprobantes
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 6)
    public void Consumos_Detalles_de_Consumos_MIX() {
    	loginPorLineaMobile(driver, lineaMIX);
    	Assert.assertTrue(verificarDetallesDeConsumos(driver, "MIX o Pre"));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 7)
    public void Consumos_Detalles_de_Consumos_POS() {
    	loginPorLineaMobile(driver, lineaPos);
    	Assert.assertTrue(verificarDetallesDeConsumos(driver, "Pos"));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 8)
    public void Consumos_Detalles_de_Consumos_PRE() {
    	loginPorLineaMobile(driver, lineaPre);
    	Assert.assertTrue(verificarDetallesDeConsumos(driver, "MIX o Pre"));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 9)
    public void Facturacion_Compras_Realizadas_Descargar_Comprobante_MIX() {
    	loginPorLineaMobile(driver, "1164599468");
    	Assert.assertTrue(descargaComprobanteDeCompra(driver, "MIX"));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 10)
    public void Facturacion_Compras_Realizadas_Descargar_Comprobante_POS() {
    	loginPorLineaMobile(driver, "1145642605");
    	Assert.assertTrue(descargaComprobanteDeCompra(driver, "Pos"));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 11)
    public void Facturacion_Imprimir_cupon_de_pago_MIX() {
    	loginPorLineaMobile(driver, "1162733281");
    	Assert.assertTrue(imprimirCuponDePago(driver));  //Mensaje de error al intentar descargar el cupon
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 12)
    public void Facturacion_Imprimir_cupon_de_pago_POS() {
    	loginPorLineaMobile(driver, "3413130145");
    	Assert.assertTrue(imprimirCuponDePago(driver));  //Mensaje de error al intentar descargar el cupon
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 13)
    public void Facturacion_Informar_un_Pago_MIX() {
    	loginPorLineaMobile(driver, "1162733281");
    	Assert.assertTrue(informarUnPago(driver));  //No deja ingresar el importe
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 14)
    public void Facturacion_Informar_un_Pago_POS() {
    	loginPorLineaMobile(driver, "3413130145");
    	Assert.assertTrue(informarUnPago(driver));  //No deja ingresar el importe
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 15)
    public void Facturacion_Notas_de_Credito_y_Debito_MIX() {
    	loginPorLineaMobile(driver, "1161120234");
    	Assert.assertTrue(descargaNotaDeCreditoYDebito(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 16)
    public void Facturacion_Notas_de_Credito_y_Debito_POS() {
    	loginPorLineaMobile(driver, "3758649203");
    	Assert.assertTrue(descargaNotaDeCreditoYDebito(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 17)
    public void Facturacion_Pagar_Factura_MIX() {
    	loginPorLineaMobile(driver, "1162733281");
    	Assert.assertTrue(verificarDetalleDePagoConTarjeta(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 18)
    public void Facturacion_Pagar_Factura_POS() {
    	loginPorLineaMobile(driver, "1165990597");
    	Assert.assertTrue(verificarDetalleDePagoConTarjeta(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 19)
    public void Facturacion_Ver_Facturas_MIX() {
    	loginPorLineaMobile(driver, "1162733281");
    	Assert.assertTrue(verFacturas(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 20)
    public void Facturacion_Ver_Facturas_POS() {
    	loginPorLineaMobile(driver, "1165990597");
    	Assert.assertTrue(verFacturas(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 21)
    public void Mi_Linea_Baja_de_Suscripciones_MIX() {
    	loginPorLineaMobile(driver, lineaMIX);
    	Assert.assertTrue(bajaDeSuscripcion(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 22)
    public void Mi_Linea_Baja_de_Suscripciones_POS() {
    	loginPorLineaMobile(driver, "1145642605");
    	Assert.assertTrue(bajaDeSuscripcion(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 23)
    public void Mi_Linea_Baja_de_Suscripciones_PRE() {
    	loginPorLineaMobile(driver, lineaPre);
    	Assert.assertTrue(bajaDeSuscripcion(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 24)
    public void MI_Linea_Roaming_y_LDI_habilitado_MIX() {
    	loginPorLineaMobile(driver, "3496652414");
    	Assert.assertTrue(verificarRoamingYLDIActivos(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 25)
    public void MI_Linea_Roaming_y_LDI_habilitado_POS() {
    	loginPorLineaMobile(driver, "3794601129");
    	Assert.assertTrue(verificarRoamingYLDIActivos(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 26)
    public void MI_Linea_Roaming_y_LDI_habilitado_PRE() {
    	loginPorLineaMobile(driver, "1164520012");
    	Assert.assertTrue(verificarRoamingYLDIActivos(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 27)
    public void Recargas_Gestiones_y_Consultas_Recargar_Ahora_MIX() {
    	loginPorLineaMobile(driver, lineaMIX);
    	Assert.assertTrue(verificarMetodosDeRecarga(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 28)
    public void Recargas_Gestiones_y_Consultas_Recargar_Ahora_PRE() {
    	loginPorLineaMobile(driver, lineaPre);
    	Assert.assertTrue(verificarMetodosDeRecarga(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 29)
    public void Recargas_Recarga_SOS_MIX() {
    	loginPorLineaMobile(driver, lineaMIX);
    	Assert.assertTrue(verificarRecargaSOS(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 30)
    public void Recargas_Recarga_SOS_PRE() {
    	loginPorLineaMobile(driver, lineaPre);
    	Assert.assertTrue(verificarRecargaSOS(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 31)
    public void Recargas_Recarga_con_puntos_Club_MIX() {
    	loginPorLineaMobile(driver, "3854041917");
    	Assert.assertTrue(verificarRecargaPuntosClub(driver));  //Mensaje de error al hacer click en canjear
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 32)
    public void Recargas_Recarga_con_puntos_Club_PRE() {
    	loginPorLineaMobile(driver, "1164473518");
    	Assert.assertTrue(verificarRecargaPuntosClub(driver));  //Mensaje de error al hacer click en canjear
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 33)
    public void Recargas_Gestiones_y_Consultas_Ultimas_Recargas_MIX() {
    	loginPorLineaMobile(driver, lineaMIX);
    	Assert.assertTrue(verificarUltimasRecargas(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 34)
    public void Recargas_Gestiones_y_Consultas_Ultimas_Recargas_PRE() {
    	loginPorLineaMobile(driver, lineaPre);
    	Assert.assertTrue(verificarUltimasRecargas(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 35)
    public void Ayuda_Acceder_a_AYUDA_y_seleccionar_alguna_opcion() {
    	loginPorLineaMobile(driver, lineaPre);
    	boolean ayuda = false;
    	scrollAndClick(driver, "id", "Ayuda");
    	sleep(10000);
    	scrollAndClick(driver, "id", "Mi equipo no reconoce la Sim");
    	if (driver.findElement(By.id("Mi equipo no reconoce la Sim")).isEnabled())
    		ayuda = true;
    	Assert.assertTrue(ayuda);
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 36)
    public void Login_Iniciar_Sesion_con_clave_Incorrecta() {
    	driver.findElement(By.className("UIATextField")).sendKeys(lineaMIX);
        driver.findElement(By.id("INGRESAR CON CLAVE PERSONAL")).click();
        driver.findElement(By.xpath("//*[@class='UIAView' and (./preceding-sibling::* | ./following-sibling::*)[@text='Clave num�rica'] and ./parent::*[@class='UIAView']]")).sendKeys("1470");
        driver.findElement(By.id("INGRESAR A MI PERSONAL UAT")).click();
    	Boolean mensajeIncorrecto = (driver.findElement(By.id("Los datos ingresados son incorrectos")).getText().contains("Los datos ingresados son incorrectos"));
        scrollAndClick(driver, "id", "Aceptar");
    	Assert.assertTrue(mensajeIncorrecto);
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 37)
    public void Login_Iniciar_Sesion_sin_clave() {
    	driver.findElement(By.className("UIATextField")).sendKeys(lineaMIX);
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
    public void Login_Iniciar_Sesion_con_Linea_PreActiva() {
    	loginPorLineaMobile(driver, "1162645152");
    	scrollAndClick(driver, "id", "Mis Servicios");
    	Assert.fail();  //No se visualiza el mensaje de linea pre desactivada
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 40)
    public void Inicio_Detalle_de_credito_MIX() {
    	loginPorLineaMobile(driver, lineaMIX);
    	Assert.assertTrue(verificarDetalleDeCredito(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 41)
    public void Inicio_Detalle_de_credito_PRE() {
    	loginPorLineaMobile(driver, lineaPre);
    	Assert.assertTrue(verificarDetalleDeCredito(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 42)
    public void Inicio_Mis_disponibles_Internet_MIX() {
    	loginPorLineaMobile(driver, lineaMIX);
    	Assert.assertTrue(verifDisponibles(driver, "MIX"));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 43)
    public void Inicio_Mis_disponibles_Internet_PRE() {
    	loginPorLineaMobile(driver, lineaPre);
    	Assert.assertTrue(verifDisponibles(driver, "Pre"));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 44)
    public void Inicio_Mis_disponibles_Mis_Facturas_MIX() {
    	loginPorLineaMobile(driver, lineaMIX);
        Assert.assertTrue(verificarFactura(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 45)
    public void Inicio_Mis_disponibles_Mis_Facturas_POS() {
    	loginPorLineaMobile(driver, "1145642605");
    	Assert.assertTrue(verificarFactura(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 46)
    public void Pagos_Paga_con_Tarjeta_de_Credito_MIX() {
    	loginPorLineaMobile(driver, lineaMIX);
    	Assert.assertTrue(verificarPagoConTarjetaDeCredito(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 47)
    public void Pagos_Paga_con_Tarjeta_de_Credito_POS() {
    	loginPorLineaMobile(driver, "1145642605");
    	Assert.assertTrue(verificarPagoConTarjetaDeCredito(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 48)
    public void Pagos_Paga_con_Pago_Mis_Cuentas_MIX() {
    	loginPorLineaMobile(driver, lineaMIX);
    	Assert.assertTrue(verificarPagoConPagoMisCuentas(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 49)
    public void Pagos_Paga_con_Pago_Mis_Cuentas_POS() {
    	loginPorLineaMobile(driver, "1145642605");
    	Assert.assertTrue(verificarPagoConPagoMisCuentas(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 50)
    public void Pagos_Pago_online_MIX() {
    	loginPorLineaMobile(driver, lineaMIX);
    	Assert.assertTrue(verificarPagoOnline(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 51)
    public void Pagos_Pago_online_POS() {
    	loginPorLineaMobile(driver, "1145642605");
    	Assert.assertTrue(verificarPagoOnline(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 52)
    public void Pagos_Pago_Presencial_MIX() {
    	loginPorLineaMobile(driver, lineaMIX);
    	Assert.assertTrue(verificarPagoPresencial(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 53)
    public void Pagos_Pago_Presencial_POS() {
    	loginPorLineaMobile(driver, "1145642605");
    	Assert.assertTrue(verificarPagoPresencial(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 54)
    public void Mis_Consumos_Doble_Blue_Activar_PRE() {
    	loginPorLineaMobile(driver, "1164484643");
    	Assert.assertTrue(verificarActivYDesactDobleBlue(driver, "Pre"));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 55)
    public void Mis_Consumos_Doble_Blue_Activar_MIX() {
    	loginPorLineaMobile(driver, "1164483808");
    	Assert.assertTrue(verificarActivYDesactDobleBlue(driver, "MIX"));
    }
}