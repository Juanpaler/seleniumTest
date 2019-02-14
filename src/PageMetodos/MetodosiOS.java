package PageMetodos;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class MetodosiOS {
	
	public static String lineaMIX = "1162735148";
	public static String lineaPre = "1162745165";
	public static String lineaPos = "1145642605";
	
	
	public void sleep(int miliseconds) {
		try {Thread.sleep(miliseconds);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	}
	
	public void loginPorLineaMobile(IOSDriver<IOSElement> driver, String linea) {
		sleep(15000);
		driver.findElement(By.className("UIATextField")).sendKeys(linea);
        driver.findElement(By.id("INGRESAR CON CLAVE PERSONAL")).click();
        driver.findElement(By.xpath("//*[@class='UIAView' and (./preceding-sibling::* | ./following-sibling::*)[@text='Clave numérica'] and ./parent::*[@class='UIAView']]")).sendKeys("1469");
        driver.findElement(By.id("INGRESAR A MI PERSONAL UAT")).click();
        sleep(10000);
	}

	public void scrollAndClick(IOSDriver<IOSElement> driver, String by, String using) {
		if (driver.findElement(by, using).isDisplayed()) {
			driver.findElement(by, using).click();
			sleep(7000);
		} else {
			IOSElement element = null;
			int numberOfTimes = 10;
			Dimension size = driver.manage().window().getSize();
			for (int i=0; i<numberOfTimes; i++) {
				try {
					driver.swipe(size.width / 3, (int) (size.height * 0.8), size.width / 3, (int) (size.height * 0.8) - 200, 300);
					sleep(2000);
					element = (IOSElement) driver.findElement(by, using);
					i = numberOfTimes;
				} catch(NoSuchElementException e) {
					System.out.println(String.format("Element not available. Scrolling (%s) times...", i + 1));
				}
			}
			element.click();
			sleep(7000);
		}
	}
	
    public boolean verificarCompraDePacks(IOSDriver<IOSElement> driver) {
    	boolean msj = false;
    	try {
    		scrollAndClick(driver, "id", "Pagos, Recargas y Packs");
    	} catch(Exception e) {
    		scrollAndClick(driver, "id", "Recargas y Packs");
    	}
    	scrollAndClick(driver, "id", "Con Cr\u00e9dito");
    	scrollAndClick(driver, "id", "ROAMING");
    	scrollAndClick(driver, "xpath", "//*[@class='UIAStaticText'][contains(text(),'Pack Roaming 40 SMS Limitrofes')]");
    	scrollAndClick(driver, "id", "COMPRAR");
    	scrollAndClick(driver, "id", "ACEPTAR");
    	sleep(5000);
		for (WebElement x : driver.findElements(By.className("UIAStaticText"))) {
			if (x.getText().contains("La compra se realiz\u00f3 con \u00e9xito"))
				msj = true;
		}
    	scrollAndClick(driver, "id", "ACEPTAR");
    	sleep(5000);
    	return msj;
    }
    
    public boolean verificarCompraPacksPuntosClub(IOSDriver<IOSElement> driver) {
    	try {
    		scrollAndClick(driver, "id", "Pagos, Recargas y Packs");
    	} catch(Exception e) {
    		scrollAndClick(driver, "id", "Recargas y Packs");
    	}   	
    	scrollAndClick(driver, "id", "Con Puntos Club Personal");
    	sleep(5000);
    	scrollAndClick(driver, "id", "ROAMING");
    	scrollAndClick(driver, "id", "SMS");
    	scrollAndClick(driver, "id", "NUEVO PREMIO");
    	scrollAndClick(driver, "id", "CANJEAR");
    	return false;  //Mensaje de error al intentar canjear
    }
    
    public boolean verificarDescargaComprobante(IOSDriver<IOSElement> driver) {
    	try {
    		scrollAndClick(driver, "id", "Pagos, Recargas y Packs");
    	} catch(Exception e) {
    		scrollAndClick(driver, "id", "Recargas y Packs");
    	} 
    	scrollAndClick(driver, "id", "Compras con cr\u00e9dito Personal");
    	sleep(15000);
    	driver.findElement(By.xpath("//*[@text='i' and @class='UIAImage' and ./*[@class='UIAStaticText'] and ./parent::*[./parent::*[(./preceding-sibling::* | ./following-sibling::*)[./*[@text='11/02/2019 03:39:54']]]]]")).click();
    	return false;  //No se pueden descargar los comprobantes
    }
    
    public boolean verificarDetallesDeConsumos(IOSDriver<IOSElement> driver, String tipoDeLinea) {
    	boolean detalle = false, descripcion = false, destino = false;
    	scrollAndClick(driver, "id", "Inicio");
    	scrollAndClick(driver, "id", "Detalle de consumos");
    	switch(tipoDeLinea) {
    	case "MIX o Pre":
    		scrollAndClick(driver, "id", "Consultar");
        	scrollAndClick(driver, "id", "Recarga");
        	if (driver.findElement(By.className("UIATable")).isEnabled())
        		detalle = true;
        	break;
    	case "Pos":
    		scrollAndClick(driver, "id", "CONSULTAR");
    		sleep(5000);
    		for (WebElement x : driver.findElements(By.className("UIAStaticText"))) {
    			if (x.getText().contains("DESCRIPCI\u00d3N"))
    				descripcion = true;
    			if (x.getText().contains("DESTINO"))
    				destino = true;
    		}
    		break;
    	}
    	if (detalle == true || (descripcion == true && destino == true))
    		return true;
    	else
    		return false;
    }
    
    public boolean descargaComprobanteDeCompra(IOSDriver<IOSElement> driver, String tipoDeLinea) {
    	boolean descarga = false;
    	scrollAndClick(driver, "id", "Inicio");
    	switch(tipoDeLinea) {
    	case "MIX":
    		scrollAndClick(driver, "id", "Mis facturas");
    		break;
    	case "Pos":
    		driver.swipe(335, 812, 331, 707, 436);
        	driver.findElement(By.id("Mis facturas")).click();
        	sleep(5000);
        	break;
    	}
    	scrollAndClick(driver, "id", "Facturas de compra");
    	scrollAndClick(driver, "id", "Descargar");
    	scrollAndClick(driver, "id", "Guardar en Archivos");
    	for (WebElement x : driver.findElements(By.className("UIAStaticText"))) {
    		if (x.getText().contains("Factura"))
    			descarga = true;
    	}
    	scrollAndClick(driver, "id", "Cancelar");
    	return descarga;
    }
    
    public boolean imprimirCuponDePago(IOSDriver<IOSElement> driver) {
    	try {
    		scrollAndClick(driver, "id", "Pagos, Recargas y Packs");
    	} catch(Exception e) {
    		scrollAndClick(driver, "id", "Pagos y Packs");	
    	}
    	scrollAndClick(driver, "id", "Mis Facturas");
    	scrollAndClick(driver, "id", "Cup\u00f3n de pago");
    	driver.findElement(By.className("UIATextField")).sendKeys("10.00");
    	//scrollAndClick(driver, "id", "Descargar");
    	return false;  //Mensaje de error al intentar descargar el cupon
    }
    
    public boolean informarUnPago(IOSDriver<IOSElement> driver) {
    	try {
    		scrollAndClick(driver, "id", "Pagos, Recargas y Packs");
    	} catch(Exception e) {
    		scrollAndClick(driver, "id", "Pagos y Packs");	
    	}
    	scrollAndClick(driver, "id", "Informar Pago");
    	return false;  //No deja ingresar el importe
    }
    
    public boolean descargaNotaDeCreditoYDebito(IOSDriver<IOSElement> driver) {
    	boolean descarga = false;
    	try {
    		scrollAndClick(driver, "id", "Pagos, Recargas y Packs");
    	} catch(Exception e) {
    		scrollAndClick(driver, "id", "Pagos y Packs");	
    	}
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
    	return descarga;
    }
    
    public boolean verificarDetalleDePagoConTarjeta(IOSDriver<IOSElement> driver) {
    	boolean factura = false;
    	try {
    		scrollAndClick(driver, "id", "Pagos, Recargas y Packs");
    	} catch(Exception e) {
    		scrollAndClick(driver, "id", "Pagos y Packs");	
    	}
    	scrollAndClick(driver, "id", "Mis Facturas");
    	scrollAndClick(driver, "id", "Pagar");    	
    	scrollAndClick(driver, "id", "Tarjeta de cr\u00e9dito");
    	for (WebElement x : driver.findElements(By.className("UIAStaticText"))) {
    		if (x.getText().contains("Detalles del pago"))
    			factura = true;
    	}
    	return factura;
    }
    
    public boolean verFacturas(IOSDriver<IOSElement> driver) {
    	boolean cantFacturas = false;
    	scrollAndClick(driver, "id", "Inicio");
    	driver.swipe(237, 854, 251, 231, 672);
        driver.findElement(By.id("Mis facturas")).click();
        sleep(5000);
        try {
        	driver.findElement(By.xpath("//*[@class='UIAView' and @height>0 and ./*[@text='20/07/2018'] and ./*[@class='UIAStaticText']]")).click();
        } catch(Exception e) {
        	driver.findElement(By.xpath("//*[@class='UIAView' and @height>0 and ./*[@text='17/02/2018']  and ./*[@class='UIAStaticText']]")).click();
        }
        sleep(40000);
        List<IOSElement> facturas = driver.findElements(By.className("UIAView"));
        for (int i=0; i<facturas.size(); i++) {
        	if (facturas.size() > 1)
        		cantFacturas = true;
        }
        return cantFacturas;
    }
    
    public boolean bajaDeSuscripcion(IOSDriver<IOSElement> driver) {
    	boolean bajaSus = false;
    	scrollAndClick(driver, "id", "Mis Servicios");
    	scrollAndClick(driver, "id", "Mis suscripciones a servicios");
    	driver.findElement(By.xpath("//*[@class='UIAStaticText'][contains(text(),'Precio no disponible')]")).click();
    	for (WebElement x : driver.findElements(By.className("UIAStaticText"))) {
    		if (x.getText().contains("La suscripci\u00f3n se di\u00f3 de baja con \u00e9xito"))
    			bajaSus = true;
    	}
    	scrollAndClick(driver, "id", "ACEPTAR");
    	return bajaSus;
    }
}