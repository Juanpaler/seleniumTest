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

import PageMetodos.MetodosiOS;

import java.net.URL;
import java.net.MalformedURLException;
import java.util.logging.Level;

public class iOSMobile extends MetodosiOS {

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
        	driver.findElement(By.id("Cancel")).click();
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
    	scrollAndClick(driver, "id", "Pagos, Recargas y Packs");
    	scrollAndClick(driver, "id", "Compras con cr\u00e9dito Personal");
    	sleep(15000);
    	driver.findElement(By.xpath("//*[@text='i' and @class='UIAImage' and ./*[@class='UIAStaticText'] and ./parent::*[./parent::*[(./preceding-sibling::* | ./following-sibling::*)[./*[@text='11/02/2019 03:39:54']]]]]")).click();
    	Assert.assertTrue(false);  //No se pueden descargar los comprobantes
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 5)
    public void Comprar_Packs_Consultar_Comprobantes_PRE() {
    	loginPorLineaMobile(driver, lineaPre);
    	scrollAndClick(driver, "id", "Recargas y Packs");
    	scrollAndClick(driver, "id", "Historial de compras de Packs");
    	sleep(15000);
    	driver.findElement(By.xpath("//*[@text='i' and @class='UIAImage' and ./*[@class='UIAStaticText'] and ./parent::*[./parent::*[(./preceding-sibling::* | ./following-sibling::*)[./*[@text='11/02/2019 03:39:54']]]]]")).click();
    	Assert.assertTrue(false);  //No se pueden descargar los comprobantes
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 6)
    public void Consumos_Detalles_de_Consumos_MIX() {
    	loginPorLineaMobile(driver, lineaMIX);
    	Assert.assertTrue(verificarDetallesDeConsumos(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 7)
    public void Consumos_Detalles_de_Consumos_POS() {
    	loginPorLineaMobile(driver, lineaPos);
    	Assert.assertTrue(verificarDetallesDeConsumos(driver));  //No funciona la linea POS
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 8)
    public void Consumos_Detalles_de_Consumos_PRE() {
    	loginPorLineaMobile(driver, lineaPre);
    	Assert.assertTrue(verificarDetallesDeConsumos(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 9)
    public void Facturacion_Compras_Realizadas_Descargar_Comprobante_MIX() {
    	loginPorLineaMobile(driver, "1164599468");
    	Assert.assertTrue(descargaComprobanteDeCompra(driver, "MIX"));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 10)
    public void Facturacion_Compras_Realizadas_Descargar_Comprobante_POS() {
    	loginPorLineaMobile(driver, "1164599450");
    	Assert.assertTrue(descargaComprobanteDeCompra(driver, "Pos"));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 11)
    public void Facturacion_Imprimir_cupon_de_pago_MIX() {
    	loginPorLineaMobile(driver, "1162733281");
    	scrollAndClick(driver, "id", "Pagos, Recargas y Packs");
    	scrollAndClick(driver, "id", "Mis Facturas");
    	scrollAndClick(driver, "id", "Cup\u00f3n de pago");
    	driver.findElement(By.className("UIATextField")).sendKeys("10.00");
    	//scrollAndClick(driver, "id", "Descargar");
    	Assert.assertTrue(false); //Mensaje de error al intentar descargar el cupon
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 12)
    public void Facturacion_Imprimir_cupon_de_pago_POS() {
    	loginPorLineaMobile(driver, "3413130145");
    	Assert.assertTrue(false); //No funciona la linea
    	/*scrollAndClick(driver, "id", "Pagos y Packs");
    	scrollAndClick(driver, "id", "Mis Facturas");
    	scrollAndClick(driver, "id", "Cup\u00f3n de pago");
    	driver.findElement(By.className("UIATextField")).sendKeys("10.00");
    	//scrollAndClick(driver, "id", "Descargar");
    	Assert.assertTrue(false); //Mensaje de error al intentar descargar el cupon*/
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 13)
    public void Facturacion_Informar_un_Pago_MIX() {
    	loginPorLineaMobile(driver, "1162733281");
    	scrollAndClick(driver, "id", "Pagos, Recargas y Packs");
    	scrollAndClick(driver, "id", "Informar Pago");
    	Assert.assertTrue(false);  //No deja ingresar el importe
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 14)
    public void Facturacion_Informar_un_Pago_POS() {
    	loginPorLineaMobile(driver, "3413130145");
    	Assert.assertTrue(false);  //No funciona la linea POS
    	//scrollAndClick(driver, "id", "Pagos y Packs");
    	//scrollAndClick(driver, "id", "Informar Pago");
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 15)
    public void Facturacion_Notas_de_Credito_y_Debito_MIX() {
    	loginPorLineaMobile(driver, "1161120234");
    	boolean descarga = false;
    	scrollAndClick(driver, "id", "Pagos, Recargas y Packs");
    	scrollAndClick(driver, "id", "Mis Facturas");
    	driver.swipe(361, 783, 394, 461, 128);
    	driver.findElement(By.id("Notas fiscales")).click();
    	sleep(10000);
    	scrollAndClick(driver, "id", "Descargar");
    	scrollAndClick(driver, "id", "Guardar en Archivos");
    	for (WebElement x : driver.findElements(By.className("UIAStaticText"))) {
    		if (x.getText().contains("NotaFiscal"))
    			descarga = true;
    	}
    	scrollAndClick(driver, "id", "Cancelar");
    	Assert.assertTrue(descarga);
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 16)
    public void Facturacion_Notas_de_Credito_y_Debito_POS() {
    	loginPorLineaMobile(driver, "3758649203");
    	Assert.assertTrue(false);  //No funciona la linea POS
    	/*scrollAndClick(driver, "id", "Pagos y Packs");
    	scrollAndClick(driver, "id", "Mis Facturas");
    	driver.swipe(361, 783, 394, 461, 128);
    	driver.findElement(By.id("Notas fiscales")).click();*/
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
    	Assert.assertTrue(bajaDeSuscripcioniOS(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 22)
    public void Mi_Linea_Baja_de_Suscripciones_POS() {
    	loginPorLineaMobile(driver, "1164599450");
    	Assert.assertTrue(bajaDeSuscripcioniOS(driver));
    }
    
    @Test (groups = "AutogestionIndividuosAPP", priority = 23)
    public void Mi_Linea_Baja_de_Suscripciones_PRE() {
    	loginPorLineaMobile(driver, lineaPre);
    	Assert.assertTrue(bajaDeSuscripcioniOS(driver));
    }
}