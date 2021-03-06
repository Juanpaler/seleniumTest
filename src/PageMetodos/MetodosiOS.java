package PageMetodos;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class MetodosiOS extends Utils{	
	
	
	public void loginPorLineaMobile(IOSDriver<IOSElement> driver, String linea) {
		if(ElementCreated(driver, "xpath", "//*[@text='\u00A1Ups! en este momento Mi Personal UAT no est\u00E1 disponible.']", 5)) {
        	scrollAndClick(driver, "xpath", "//*[@text='Aceptar']");
		}
		WaitForElement(driver, "cssSelector", ".UIATextField");
		driver.findElement(By.className("UIATextField")).sendKeys(linea);
        driver.findElement(By.id("INGRESAR CON CLAVE PERSONAL")).click();
		WaitForElement(driver, "xpath", "//*[@class='UIAView' and (./preceding-sibling::* | ./following-sibling::*)[@text='Clave num\u00E9rica'] and ./parent::*[@class='UIAView']]");

        driver.findElement(By.xpath("//*[@class='UIAView' and (./preceding-sibling::* | ./following-sibling::*)[@text='Clave num\u00E9rica'] and ./parent::*[@class='UIAView']]")).sendKeys("1469");
        driver.findElement(By.id("INGRESAR A MI PERSONAL UAT")).click();
        try {
        	if(ElementCreated(driver, "id", "En otro momento", 10))
        	{
            	scrollAndClick(driver, "id", "En otro momento");
        	}
        } catch(Exception e) {}
	}
/*
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
	*/
	public void scrollAndClick(IOSDriver<IOSElement> driver, String by, String using) {
		if (ElementCreated(driver, by, using, 5)) {
			driver.findElement(by, using).click();
		} else {
			int numberOfTimes = 10;
			//Mejorar la cantidad de iteraciones teniendo en cuenta el largo de la ventana dividido la resolucion de la pantalla 
			for (int i=0; i<numberOfTimes; i++) {
				try {					
			    	driver.swipe(10, 480, 20, 70, 400);
					if (ElementCreated(driver, by, using, 2)) {
						driver.findElement(by, using).click();
						i= numberOfTimes;
					}
				} catch(NoSuchElementException e) {
					System.out.println(e.getMessage());
				}
			}			
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
    	Boolean opcionExiste = ElementCreated(driver, "xpath", "//*[@class='UIAStaticText'][contains(text(),'Pack Roaming 40 SMS Limitrofes')]", 5);
    	while(!opcionExiste)
    	{
    		WaitForElement(driver, "xpath", "//*[@id='Ver m\u00E1s']");
			driver.findElement("xpath", "//*[@id='Ver m\u00E1s']").click();
	    	opcionExiste = ElementCreated(driver, "xpath", "//*[@class='UIAStaticText'][contains(text(),'Pack Roaming 40 SMS Limitrofes')]", 5);
    	}
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
    	scrollAndClick(driver, "xpath", "//*[@text='ACEPTAR']");
    	bajaSus = ElementCreated(driver, "xpath", "//*[@text='La suscripci\u00F3n se di\u00F3 de baja con \u00E9xito']",15);
    	scrollAndClick(driver, "id", "Aceptar");
    	return bajaSus;
    }
    
    public boolean verificarRoamingYLDIActivos(IOSDriver<IOSElement> driver) {
    	boolean roaming = false, ldi = false;
    	scrollAndClick(driver, "id", "Mis Servicios");
    	scrollAndClick(driver, "id", "Roaming");
    	scrollAndClick(driver, "id", "Pod\u00e9s usar tu Personal fuera del Pa\u00eds.");
    	if (driver.findElement(By.id("Roaming Activo")).isEnabled())
    		roaming = true;
    	driver.findElement(By.id("Atr\u00e1s")).click();
    	driver.findElement(By.id("Atr\u00e1s")).click();
    	scrollAndClick(driver, "id", "Larga Distancia Internacional");
    	if (driver.findElement(By.id("Larga Distancia Internacional Activado")).isEnabled())
    		ldi = true;
    	return roaming && ldi;
    }
    
    public boolean verificarMetodosDeRecarga(IOSDriver<IOSElement> driver) {
    	boolean otrasFormas = false, tarjetaCredito = false, tarjetaPrepaga = true;
    	try {
    		scrollAndClick(driver, "id", "Pagos, Recargas y Packs");
    	} catch(Exception e) {
    		scrollAndClick(driver, "id", "Recargas y Packs");
    	}
    	scrollAndClick(driver, "id", "Tarjeta de Cr\u00e9dito");
    	WaitForElement(driver, "id", "Recarga con Tarjeta de Cr\u00e9dito");    	
    	tarjetaCredito = ElementCreated(driver, "id", "Recarga con Tarjeta de Cr\u00E9dito", 7);
    	driver.findElement(By.id("Atr\u00e1s")).click();
    	
    	WaitForElement(driver, "id", "Recarga online con Tarjeta Personal");
    	scrollAndClick(driver, "id", "Recarga online con Tarjeta Personal");    	
    	tarjetaPrepaga = ElementCreated(driver, "id", "Recarga con Tarjeta Prepaga", 7);
    	driver.findElement(By.id("Atr\u00e1s")).click();
    	
    	WaitForElement(driver, "id", "Recarga online - otras formas de pago");
    	scrollAndClick(driver, "id", "Recarga online - otras formas de pago");    	
    	otrasFormas = ElementCreated(driver, "id", "Medios de recarga online", 7);
    	driver.findElement(By.id("Atr\u00e1s")).click();    	
    	
    	return tarjetaPrepaga && otrasFormas && tarjetaCredito;
    }
    
    public boolean verificarRecargaSOS(IOSDriver<IOSElement> driver) {
    	boolean msj = false;
    	try {
    		scrollAndClick(driver, "id", "Pagos, Recargas y Packs");
    	} catch(Exception e) {
    		scrollAndClick(driver, "id", "Recargas y Packs");
    	}
    	scrollAndClick(driver, "id", "RECARGA S.O.S.");
    	scrollAndClick(driver, "id", "RECARG\u00c1 AHORA");
    	for (WebElement x : driver.findElements(By.className("UIAStaticText"))) {
    		if (x.getText().contains("La recarga S.O.S. se realiz\u00f3 con \u00e9xito!"))
    			msj = true;
    	}
    	scrollAndClick(driver, "id", "ACEPTAR");
    	return msj;
    }
    
    public boolean verificarRecargaPuntosClub(IOSDriver<IOSElement> driver) {
    	try {
    		scrollAndClick(driver, "id", "Pagos, Recargas y Packs");
    	} catch(Exception e) {
    		scrollAndClick(driver, "id", "Recargas y Packs");
    	}
    	scrollAndClick(driver, "id", "Puntos Club");
    	sleep(15000);
    	scrollAndClick(driver, "id", "RECARGAS");
    	scrollAndClick(driver, "id", "Cr\u00e9dito $10");
    	scrollAndClick(driver, "id", "CANJEAR");
    	return false;  //Mensaje de error al hacer click en canjear
    }
    
    public boolean verificarUltimasRecargas(IOSDriver<IOSElement> driver) {
    	int recargas = 0;
    	try {
    		scrollAndClick(driver, "id", "Pagos, Recargas y Packs");
    	} catch(Exception e) {
    		scrollAndClick(driver, "id", "Recargas y Packs");
    	}
    	scrollAndClick(driver, "id", "Historial de Recargas");
    	List<MobileElement> tabla = driver.findElement(By.className("UIATable")).findElements(By.className("UIAStaticText"));
    	for (int i=0; i<tabla.size(); i++) {
    		if (tabla.get(i).getText().contains("Recarga por Tarjeta de Credito"))
    			recargas++;
    	}
		return recargas >= 1;
	}
    
    public boolean verificarDetalleDeCredito(IOSDriver<IOSElement> driver) {
		boolean credito = false;
		scrollAndClick(driver, "id", "Inicio");
		for (WebElement x : driver.findElements(By.className("UIAStaticText"))) {
			if (x.getText().contains("Cr\u00e9dito de mi l\u00ednea"))
    			credito = true;
		}
    	return credito;
	}
    
    public boolean verifDisponibles(IOSDriver<IOSElement> driver, String tipoDeLinea) {
    	boolean internet = false, minutos = false, sms = false;
    	scrollAndClick(driver, "id", "Internet");
    	for (WebElement x : driver.findElements(By.className("UIAStaticText"))) {
    		if (x.getText().contains("Compr\u00e1 m\u00e1s internet")) {
    			internet = true;
    			break;
    		}
    	}
    	scrollAndClick(driver, "id", "Minutos");
    	for (WebElement x : driver.findElements(By.className("UIAStaticText"))) {
    		if (x.getText().contains("Compr\u00e1 m\u00e1s minutos")) {
    			minutos = true;
    			break;
    		}
    	}
    	scrollAndClick(driver, "id", "SMS");
    	switch(tipoDeLinea) {
    	case "MIX":
    		for (WebElement x : driver.findElements(By.className("UIAStaticText"))) {
        		if (x.getText().contains("SMS ilimitados a todas las compa\u00f1\u00edas")) {
        			sms = true;
        			break;
        		}
        	}
    	case "Pre":
    		for (WebElement x : driver.findElements(By.className("UIAStaticText"))) {
        		if (x.getText().contains("Compr\u00e1 m\u00e1s SMS")) {
        			sms = true;
        			break;
        		}
        	}
    	}
    	return internet && minutos && sms;
    }
    
    public boolean verificarFactura(IOSDriver<IOSElement> driver) {
		boolean factura = false;
    	scrollAndClick(driver, "id", "Mis facturas");
        for (WebElement x : driver.findElements(By.className("UIAStaticText"))) {
        	if (x.getText().contains("Cup\u00f3n de pago"))
        		factura = true;
        }
        return factura;
	}
    
    public boolean verificarPagoConTarjetaDeCredito(IOSDriver<IOSElement> driver) {		
		try {
			scrollAndClick(driver, "id", "Pagos, Recargas y Packs");
    	} catch(Exception e) {
    		scrollAndClick(driver, "id", "Pagos y Packs");
    	}
		scrollAndClick(driver,"xpath","//*[@text='Tarjeta de Cr\u00E9dito' and (./preceding-sibling::* | ./following-sibling::*)[@text='Pag\u00E1 con']]");
		return ElementCreated(driver, "xpath", "//*[@id='Complet\u00e1 el siguiente formulario para finalizar el pago']",15);    	
	}
    
    public boolean verificarPagoConPagoMisCuentas(IOSDriver<IOSElement> driver) {
		try {
    		scrollAndClick(driver, "id", "Pagos, Recargas y Packs");
    	} catch(Exception e) {
    		scrollAndClick(driver, "id", "Pagos y Packs");
    	}
		scrollAndClick(driver, "id", "Pago online");
		scrollAndClick(driver, "id", "Pago Mis Cuentas");
		
		return ElementCreated(driver, "id", "Pago con Pago Mis Cuentas: Paso a paso", 10);
	}
    
    public boolean verificarPagoOnline(IOSDriver<IOSElement> driver) {
		boolean tdc = false, hb = false, pmc = false, pagos = false;
		try {
    		scrollAndClick(driver, "id", "Pagos, Recargas y Packs");
    	} catch(Exception e) {
    		scrollAndClick(driver, "id", "Pagos y Packs");
    	}
		driver.swipe(242, 748, 249, 431, 1697);
		sleep(5000);
		driver.findElement(By.id("Pago online")).click();
		sleep(5000);
    	for (WebElement x : driver.findElements(By.className("UIAStaticText"))) {
    		if (x.getText().contains("Tarjetas de Cr\u00e9dito"))
    			tdc = true;
    		if (x.getText().contains("Home Banking"))
    			hb = true;
    		if (x.getText().contains("Pago Mis Cuentas"))
    			pmc = true;
    		if (x.getText().contains("Link Pagos"))
    			pagos = true;
    	}
    	return tdc && hb && pmc && pagos;
	}
    
    public boolean verificarPagoPresencial(IOSDriver<IOSElement> driver) {
		boolean pee = false, ca = false, pcc = false;
		try {
    		scrollAndClick(driver, "id", "Pagos, Recargas y Packs");
    	} catch(Exception e) {
    		scrollAndClick(driver, "id", "Pagos y Packs");
    	}
		driver.swipe(242, 800, 249, 431, 1697);
		sleep(5000);
		driver.findElement(By.id("Pago Presencial")).click();
		sleep(5000);
    	for (WebElement x : driver.findElements(By.className("UIAStaticText"))) {
    		if (x.getText().contains("Pagos en efectivo"))
    			pee = true;
    		if (x.getText().contains("Cajeros Autom\u00e1ticos"))
    			ca = true;
    		if (x.getText().contains("Pagos con Cheque"))
    			pcc = true;
    	}
    	return pee && ca && pcc;
	}
    
    public boolean verificarActivYDesactDobleBlue(IOSDriver<IOSElement> driver, String tipoDeLinea) {
		boolean activ = false, desact = false;
		scrollAndClick(driver, "id", "Compr\u00e1 m\u00e1s internet");
    	scrollAndClick(driver, "id", "CAMBIAR A CUOTA NACIONAL 200 MB");
    	scrollAndClick(driver, "id", "CONFIRMAR");
    	switch(tipoDeLinea) {
    	case "MIX":
    		for (WebElement x : driver.findElements(By.className("UIAStaticText"))) {
    			if (x.getText().contains("La Cuota Internet 200MB por Dia fue activada con \u00e9xito"))
            		activ = true;
    		}
    		break;
    	case "Pre":
    		for (WebElement x : driver.findElements(By.className("UIAStaticText"))) {
    			if (x.getText().contains("La Cuota Internet 200MB por Dia fue activada con \u00e9xito"))
            		activ = true;
    		}
    		break;
    	}
    	scrollAndClick(driver, "id", "CONFIRMAR");
    	scrollAndClick(driver, "id", "Compr\u00e1 m\u00e1s internet");
    	scrollAndClick(driver, "id", "CAMBIAR A CUOTA NAC Y ROAMING");
    	scrollAndClick(driver, "id", "CONFIRMAR");
    	switch(tipoDeLinea) {
    	case "MIX":
    		for (WebElement x : driver.findElements(By.className("UIAStaticText"))) {
    			if (x.getText().contains("La Cuota Internet por Dia Limitrofe  fue activada con \u00E9xito"))
            		desact = true;
    		}
    		break;
    	case "Pre":
    		for (WebElement x : driver.findElements(By.className("UIAStaticText"))) {
    			if (x.getText().contains("La Cuota Internet por Dia Limitrofe  fue activada con \u00e9xito"))
            		desact = true;
    		}
    		break;
    	}
    	scrollAndClick(driver, "id", "ACEPTAR");
    	return activ && desact;
	}
    
	public void WaitForElement(IOSDriver<IOSElement> driver,String by, String text) {
		
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);		
		
		WebDriverWait wait = new WebDriverWait(driver, 15);
		try {
			switch (by) {
			case "id":
				wait.until(ExpectedConditions.elementToBeClickable(By.id(text)));	
				break;
			case "cssSelector":
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(text)));
				break;
			case "xpath":
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(text)));
				break;	
			}
		} catch (Exception e) {
			Assert.assertTrue(false);
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);			
	}
	
    public Boolean ElementCreated(IOSDriver<IOSElement> driver,String by, String text, int time) {
		
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);		
		
		WebDriverWait wait = new WebDriverWait(driver, time);
		try {
			switch (by) {
			case "id":
				wait.until(ExpectedConditions.elementToBeClickable(By.id(text)));	
				break;
			case "cssSelector":
				wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(text)));
				break;
			case "xpath":
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(text)));
				break;	
			}
		} catch (Exception e) {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			return false;
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		
		return true;
	}
    
	public static void getScreenshot(IOSDriver<IOSElement> driver, String imageName, String rutaCaptura ) throws IOException {
		File directory;
		directory = new File(rutaCaptura);
		System.out.println("Capturing the snapshot of the page ");
		File srcFiler=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFiler, new File(directory.getAbsolutePath() + "\\" + imageName + ".png"));
	}	
}