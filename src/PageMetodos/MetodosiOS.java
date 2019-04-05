package PageMetodos;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.appium.java_client.MobileElement;
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
		sleep(7000);
		driver.findElement(By.className("UIATextField")).sendKeys(linea);
        driver.findElement(By.id("INGRESAR CON CLAVE PERSONAL")).click();
        sleep(2000);
        driver.findElement(By.xpath("//*[@class='UIAView' and (./preceding-sibling::* | ./following-sibling::*)[@text='Clave num�rica'] and ./parent::*[@class='UIAView']]")).sendKeys("1469");
        driver.findElement(By.id("INGRESAR A MI PERSONAL UAT")).click();
        sleep(10000);
        try {
        	scrollAndClick(driver, "id", "En otro momento");
        } catch(Exception e) {}
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
    	boolean sos = false, puntosClub = false, tdc = false;
    	try {
    		scrollAndClick(driver, "id", "Pagos, Recargas y Packs");
    	} catch(Exception e) {
    		scrollAndClick(driver, "id", "Recargas y Packs");
    	}
    	scrollAndClick(driver, "id", "S.O.S.");
    	if (driver.findElement(By.id("RECARG\u00c1 AHORA")).isEnabled())
    		sos = true;
    	driver.findElement(By.id("Atr\u00e1s")).click();
    	sleep(3000);
    	scrollAndClick(driver, "id", "Puntos Club");
    	sleep(10000);
    	if (driver.findElement(By.id("Recarga con Puntos Club")).isEnabled())
    		puntosClub = true;
    	driver.findElement(By.id("Atr\u00e1s")).click();
    	sleep(3000);
    	scrollAndClick(driver, "id", "Tarjeta de Cr\u00e9dito");
    	sleep(10000);
    	if (driver.findElement(By.id("Recarga con Tarjeta de Cr\u00e9dito")).isEnabled())
    		tdc = true;
    	return sos && puntosClub && tdc;
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
    
    public boolean verificarLogin(IOSDriver<IOSElement> driver, String tipoDeLogin, String linea, String clave) {
    	boolean password = false;
    	sleep(5000);
    	driver.findElement(By.id("SideMenu")).click();
    	sleep(5000);
    	driver.findElement(By.xpath("//*[@text='Cerrar Sesi\u00f3n']")).click();
    	sleep(5000);
    	driver.findElement(By.className("UIATextField")).sendKeys(linea);
        driver.findElement(By.id("INGRESAR CON CLAVE PERSONAL")).click();
        driver.findElement(By.xpath("//*[@class='UIAView' and (./preceding-sibling::* | ./following-sibling::*)[@text='Clave num�rica'] and ./parent::*[@class='UIAView']]")).sendKeys(clave);
        driver.findElement(By.id("INGRESAR A MI PERSONAL UAT")).click();
        sleep(10000);
        switch(tipoDeLogin) {
        case "Password invalido y linea inexistente":
        	if (driver.findElement(By.id("Los datos ingresados son incorrectos")).getText().contains("Los datos ingresados son incorrectos"))
            	password = true;
        	break;
        case "Sin password":
        	if (driver.findElement(By.id("Debes completar los campos!")).getText().contains("Debes completar los campos!"))
        		password = true;
        }
        scrollAndClick(driver, "id", "Aceptar");
        driver.quit();
        return password;
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
		boolean factura = false;
		try {
    		scrollAndClick(driver, "id", "Pagos, Recargas y Packs");
    	} catch(Exception e) {
    		scrollAndClick(driver, "id", "Pagos y Packs");
    	}
		driver.findElement(By.xpath("((//*[@class='UIATable' and ./parent::*[./parent::*[@class='UIATable'] and (./preceding-sibling::* | ./following-sibling::*)[./*[@class='UIAButton']]]]/*[@class='UIAView'])[2]/*[@class='UIAButton'])[2]")).click();
		sleep(7000);
    	for (WebElement x : driver.findElements(By.className("UIAStaticText"))) {
    		if (x.getText().contains("Complet\u00e1 el siguiente formulario para finalizar el pago"))
    			factura = true;
    	}
    	return factura;
	}
    
    public boolean verificarPagoConPagoMisCuentas(IOSDriver<IOSElement> driver) {
		boolean pmc = false;
		try {
    		scrollAndClick(driver, "id", "Pagos, Recargas y Packs");
    	} catch(Exception e) {
    		scrollAndClick(driver, "id", "Pagos y Packs");
    	}
		driver.swipe(242, 200, 249, 431, 1697);
		sleep(5000);
		driver.findElement(By.xpath("((//*[@class='UIATable' and ./parent::*[./parent::*[@class='UIATable'] and (./preceding-sibling::* | ./following-sibling::*)[./*[@class='UIAButton']]]]/*[@class='UIAView'])[2]/*[@class='UIAButton'])[1]")).click();
		sleep(5000);
    	for (WebElement x : driver.findElements(By.className("UIAStaticText"))) {
    		if (x.getText().contains("Pago con Pago Mis Cuentas: Paso a paso"))
    			pmc = true;
    	}
    	return pmc;
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
    			if (x.getText().contains("La Cuota Internet por Dia Limitrofe  fue activada con �xito"))
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
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id(text)));	
				break;
			case "cssSelector":
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(text)));
				break;
			case "xpath":
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(text)));
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
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id(text)));	
				break;
			case "cssSelector":
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(text)));
				break;
			case "xpath":
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(text)));
				break;	
			}
		} catch (Exception e) {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			return false;
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		
		return true;
	}
}