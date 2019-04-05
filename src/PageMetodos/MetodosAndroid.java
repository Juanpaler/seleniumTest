package PageMetodos;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class MetodosAndroid {
	
	public static String lineaMIX = "1162735148";
	public static String lineaPre = "1162745165";
	public static String lineaPos = "1145642605";
	
	
	public void sleep(int miliseconds) {
		try {Thread.sleep(miliseconds);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	}
	
	public void loginPorLineaMobile(WebDriver driver, String linea) {
		sleep(7000);
		driver.findElement(By.id("editTextLinea")).sendKeys(linea);
        driver.findElement(By.id("btn_log_in")).click();
        driver.findElement(By.id("editTextPin")).sendKeys("1469");
        driver.findElement(By.id("btn_log_in")).click();
        sleep(20000);
	}

	public void scrollAndClick(AndroidDriver<AndroidElement> driver, String by, String using) {
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
	
	 public static void verticalScroll(AndroidDriver<AndroidElement> driver) {
		 Dimension size = driver.manage().window().getSize();
	     int y_start=(int)(size.height*0.60);
	     int y_end=(int)(size.height*0.30);
	     int x=size.width/2;
	     driver.swipe(x,y_start,x,y_end,4000);
		
	}
	
	public boolean verificarCompraDePacks(AndroidDriver<AndroidElement> driver) {
		boolean msj = false;
		try {
			scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='PAGOS, RECARGAS Y PACKS']");
		} catch(Exception e) {
			scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='RECARGAS Y PACKS']");
		}
    	scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='Con Cr\u00e9dito']");
    	scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='ROAMING']");
		if (driver.findElements(By.xpath("//android.widget.TextView[contains(text(),'Pack Roaming 40 SMS Limitrofes')]")).size() > 0) {
			driver.findElement(By.xpath("//android.widget.TextView[contains(text(),'Pack Roaming 40 SMS Limitrofes')]")).click();
		} else {
			driver.findElement(By.xpath("//*[@text='Ver m\u00E1s']")).click();
			driver.findElement(By.xpath("//android.widget.TextView[contains(text(),'Pack Roaming 40 SMS Limitrofes')]")).click();
		}
    	scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='COMPRAR']");
    	scrollAndClick(driver, "xpath", "//android.widget.Button[@text='ACEPTAR']");
    	sleep(5000);
    	if (driver.findElement(By.className("android.widget.TextView")).getText().contains("La compra se realiz\u00f3 con \u00e9xito"))
    		msj = true;
    	driver.findElement(By.className("android.widget.Button")).click();
    	sleep(5000);
    	return msj;
	}
	
	public boolean verificarDescargaDeComprobante(AndroidDriver<AndroidElement> driver) {
		try {
			scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='PAGOS, RECARGAS Y PACKS']");
		} catch(Exception e) {
			scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='RECARGAS Y PACKS']");
		}
		scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='Compras con cr\u00e9dito Personal']");
    	sleep(15000);
    	driver.findElement(By.xpath("//*[@text='i' and @class='UIAImage' and ./*[@class='UIAStaticText'] and ./parent::*[./parent::*[(./preceding-sibling::* | ./following-sibling::*)[./*[@text='11/02/2019 03:39:54']]]]]")).click();
    	return false; //Mensaje de error en descarga de comprobante
	}
	
	public boolean verificarDetallesDeConsumos(AndroidDriver<AndroidElement> driver, String tipoDeLinea) {
		boolean detalle = false;
		scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='INICIO']");
    	scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='Detalle de consumos']");
    	driver.findElement(By.id("item_spinner_title")).click();
    	switch(tipoDeLinea) {
    	case "MIX o Pre":
    		driver.findElement(By.xpath("//*[text() = 'Todos']")).click();
        	driver.findElements(By.id("item_spinner_title")).get(1).click();
        	driver.findElement(By.xpath("//*[text() = 'Desde mi \u00faltima recarga']")).click();
        	scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='Consultar']");
        	scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='Recarga']");
        	if (driver.findElement(By.id("base_component_border")).isEnabled())
        		detalle = true;
        	break;
    	case "Pos":
    		driver.findElement(By.xpath("//*[text() = '  Todos']")).click();
        	driver.findElement(By.className("android.widget.EditText")).clear();
        	driver.findElement(By.className("android.widget.EditText")).sendKeys("22/12/2018");
        	scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='CONSULTAR']");
        	sleep(5000);
        	if (driver.findElement(By.id("itemGrillaInternet")).isEnabled())
        		detalle = true;
        	break;
    	}
    	return detalle;
	}
	
	public boolean verificarMetodosDeRecarga(AndroidDriver<AndroidElement> driver) {
		boolean otrasFormas = false, tarjetaCredito = false, tarjetaPrepaga = true;
		try {
			driver.findElement(By.xpath("//android.widget.TextView[@text='PAGOS, RECARGAS Y PACKS']")).click();
		} catch(Exception e) {
			driver.findElement(By.xpath("//android.widget.TextView[@text='RECARGAS Y PACKS']")).click();
		}
		new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.id("button3")));
		    driver.findElement(By.id("button3")).click();
		    //scrollAndClick(driver, "xpath", "//*[@id='button3']");
	    	sleep(15000);
	    	if (driver.findElement(By.id("divRecargasTarjetas")).isEnabled())
	    		tarjetaCredito = true;
	    	driver.navigate().back();
	    	scrollAndClick(driver, "xpath", "//*[@text='Recarga online con Tarjeta Personal']");
	    	sleep(15000);
	    	if (driver.findElement(By.id("divRecargasPin")).isEnabled())
	    		tarjetaPrepaga = true;
	    	driver.navigate().back();
	    	scrollAndClick(driver, "xpath", "//*[@text='Recarga online - otras formas de pago']");
	    	sleep(15000);
	    	if (driver.findElement(By.xpath("//*[@text='Medios de recarga online']")).isEnabled())
	    		otrasFormas = true;
	    	driver.navigate().back();
		return tarjetaCredito && tarjetaPrepaga && otrasFormas;
	}
	
	public boolean descargaComprobanteDeCompra(AndroidDriver<AndroidElement> driver, String tipoDeLinea) {
		boolean descarga = false;		
		  //Bajo
		new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='Mis disponibles']")));
		  for(int i=0;i<5;i++)
	      {          
	    	  if(driver.findElements(By.xpath("//*[@text='Mis facturas']")).size()>0){    	
	    		  //Si esta visible hago click
	              driver.findElement(By.xpath("//*[@text='Mis facturas']")).click();
	              break;          
	          }else{        
		          verticalScroll(driver);		      
	          }      
	      }
	    scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='Facturas de compra']");
		sleep(10000);
        scrollAndClick(driver, "xpath", "//android.view.View[@text='Descargar']");
        driver.swipe(159, 28, 145, 498, 289);
        scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='MiPersonal UAT']");
        sleep(5000);
        try {
        	if (driver.findElement(By.id("pdf_view")).isDisplayed())
        		descarga = true;       	
        	driver.navigate().back();
        	return descarga;
        } catch(NoSuchElementException e) {
        	driver.navigate().back();
        	return false;
        }
	}
	
	public boolean verificarRoamingYLDIActivos(AndroidDriver<AndroidElement> driver) {
    	boolean roaming = false, ldi = false;
    	scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='MIS SERVICIOS']");
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
	
	public boolean verificarImprimirCupon(AndroidDriver<AndroidElement> driver) {
		try {
			scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='PAGOS, RECARGAS Y PACKS']");
		} catch(Exception e) {
			scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='PAGOS Y PACKS']");
		}
    	scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='Mis Facturas']");
    	scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='Cup\u00f3n de pago']");
    	driver.findElement(By.className("android.widget.EditText")).sendKeys("10.00");
    	//scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='Descargar']");
    	return false;  //Mensaje de error al intentar descargar el cupon
	}
	
	public boolean verificarRecargaSOS(AndroidDriver<AndroidElement> driver) {
		boolean msj = false;
		try {
			scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='PAGOS, RECARGAS Y PACKS']");
		} catch(Exception e) {
			scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='RECARGAS Y PACKS']");
		}
    	driver.findElement(By.xpath("//*[@text='RECARGA S.O.S.']")).click();
    	scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='RECARG\u00c1 AHORA']");
    	for (WebElement x : driver.findElements(By.className("android.widget.TextView"))) {
    		if (x.getText().contains("La recarga S.O.S. se realiz\u00f3 con \u00e9xito"))
    			msj = true;
    	}
    	scrollAndClick(driver, "xpath", "//android.widget.Button[@text='ACEPTAR']");
    	return msj;
	}
	
	public boolean verificarRecargaPuntosClub(AndroidDriver<AndroidElement> driver) {
		try {
			scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='PAGOS, RECARGAS Y PACKS']");
		} catch(Exception e) {
			scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='RECARGAS Y PACKS']");
		}
    	driver.findElement(By.xpath("//*[@class='android.widget.RelativeLayout' and ./*[@text='Puntos Club']]")).click();
    	scrollAndClick(driver, "xpath", "//android.view.View[@text='RECARGAS']");
    	scrollAndClick(driver, "xpath", "//android.view.View[contains(text(),'Cr\u00e9dito $10')]"); 
    	//scrollAndClick(driver, "xpath", "//android.view.View[@text='CANJEAR']");
    	return false;  //Mensaje de error al hacer click en canjear
	}
	
	public boolean verificarUltimasRecargas(AndroidDriver<AndroidElement> driver) {
		int recargas = 0;
		try {
			scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='PAGOS, RECARGAS Y PACKS']");
		} catch(Exception e) {
			scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='RECARGAS Y PACKS']");
		}
    	scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='Historial de Recargas']");
    	List<MobileElement> tabla = driver.findElement(By.xpath("//*[@class='android.widget.LinearLayout' and ./*[@id='complex_component_layout']]")).findElements(By.className("android.widget.TextView"));
    	for (int i=0; i<tabla.size(); i++) {
    		if (tabla.get(i).getText().contains("Recarga por Tarjeta de Credito"))
    			recargas++;
    	}
    	return recargas >= 1;
	}
	
	public boolean verificarLogin(AndroidDriver<AndroidElement> driver, String linea, String clave) {
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
        scrollAndClick(driver, "xpath", "//android.widget.Button[@text='Aceptar']");
        driver.quit();
        return password;
    }
	
	public boolean compraDePacksPuntosClub(AndroidDriver<AndroidElement> driver) {
		try {
			scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='PAGOS, RECARGAS Y PACKS']");
		} catch(Exception e) {
			scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='RECARGAS Y PACKS']");
		}
    	scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='Con Puntos Club Personal']");
    	sleep(5000);
    	scrollAndClick(driver, "xpath", "//android.view.View[@text='ROAMING']");
    	scrollAndClick(driver, "xpath", "//android.view.View[@text='SMS']");
    	scrollAndClick(driver, "xpath", "//android.view.View[contains(text(),'NUEVO PREMIO PACK SMS Roaming Lim\u00edtrofes Pack de 10 SMS')]");
    	scrollAndClick(driver, "xpath", "//android.view.View[@text='CANJEAR']");
    	return false;  //Mensaje de error al querer canjear
	}
	
	public boolean verFacturas(AndroidDriver<AndroidElement> driver) {
		boolean cantFacturas = false;
		scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='INICIO']");
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
        return cantFacturas;
	}
	
	public boolean verificarDetalleDePagoConTarjeta(AndroidDriver<AndroidElement> driver) {
		boolean factura = false;
		try {
			scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='PAGOS, RECARGAS Y PACKS']");
		} catch(Exception e) {
			scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='PAGOS Y PACKS']");
		}
    	scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='Mis Facturas']");
    	scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='Pagar']");
    	sleep(5000);
    	for (WebElement x : driver.findElements(By.className("android.view.View"))) {
    		if (x.getText().contains("Detalles del pago"))
    			factura = true;
    	}
    	return factura;
	}
	
	public boolean verificarInformarUnPago(AndroidDriver<AndroidElement> driver) {
		try {
			scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='PAGOS, RECARGAS Y PACKS']");
		} catch(Exception e) {
			scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='PAGOS Y PACKS']");
		}
    	scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='Informar Pago']");
    	driver.findElement(By.id("textinput_component_edittext")).sendKeys("30/01/2019");
    	driver.findElement(By.id("textinput_component_edittext_currency")).sendKeys("150.00");
    	scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='ENVIAR']");
    	scrollAndClick(driver, "id", "button2");
    	scrollAndClick(driver, "id", "button1");
    	sleep(5000);
    	scrollAndClick(driver, "id", "button1");
    	return false;  //Mensaje de error no se puede informar un pago por el momento
	}
	
	public boolean verificarDescargaNotaDeCreditoYDebito(AndroidDriver<AndroidElement> driver) {
		boolean descarga = false;
		try {
			scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='PAGOS, RECARGAS Y PACKS']");
		} catch(Exception e) {
			scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='PAGOS Y PACKS']");
		}
		scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='Mis Facturas']");
    	scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='Notas fiscales']");
    	for (WebElement x : driver.findElements(By.className("android.view.View"))) {
    		if (x.getText().contains("Descargar")) {
    			x.click();
    			break;
    		}
    	}
    	sleep(5000);
    	driver.swipe(159, 28, 145, 498, 289);
        scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='MiPersonal UAT']");
        sleep(5000);
        try {
        	if (driver.findElement(By.id("projector_toolbar")).isDisplayed())
        		descarga = true;
         	driver.navigate().back();
         	return descarga;
        } catch(NoSuchElementException e) {
        	driver.navigate().back();
         	return false;
        }
	}
	
	public boolean activYElimVozYSMS(AndroidDriver<AndroidElement> driver, String tipoDeLinea) {
    	boolean actVoz = false, actSMS = false, elimVoz = false, elimSMS = false;
    	switch(tipoDeLinea) {
    	case "MIX":
    		scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='MIS SERVICIOS']");
        	scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='N\u00fameros Gratis']");
        	break;
    	case "Pre":
    		driver.swipe(259, 154, 82, 151, 585);
        	driver.findElement(By.xpath("//*[@text='MIS SERVICIOS']")).click();
        	sleep(7000);
        	driver.findElement(By.xpath("//*[@text='N\u00fameros Gratis']")).click();
        	sleep(5000);
        	break;
    	}
    	scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='AGREGAR']");
    	driver.findElements(By.className("android.widget.EditText")).get(0).sendKeys("11");
    	driver.findElements(By.className("android.widget.EditText")).get(1).sendKeys("62745165");
    	scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='AGREGAR']");
    	if (driver.findElement(By.id("dialog_alert_message")).getText().contains("El n\u00famero se agreg\u00f3 con \u00e9xito!"))
    		actVoz = true;
    	scrollAndClick(driver, "xpath", "//android.widget.Button[@text='ACEPTAR']");
    	driver.findElement(By.xpath("//*[@text='AGREGAR' and ./parent::*[./parent::*[(./preceding-sibling::* | ./following-sibling::*)[./*[./*[./*[@text='SMS']]]]]]]")).click();
    	driver.findElements(By.className("android.widget.EditText")).get(0).sendKeys("11");
    	driver.findElements(By.className("android.widget.EditText")).get(1).sendKeys("62735148");
    	scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='AGREGAR']");
    	if (driver.findElement(By.id("dialog_alert_message")).getText().contains("El n\u00famero se agreg\u00f3 con \u00e9xito!"))
    		actSMS = true;
    	scrollAndClick(driver, "xpath", "//android.widget.Button[@text='ACEPTAR']");
    	scrollAndClick(driver, "id", "imageview_component_image");
    	scrollAndClick(driver, "id", "button1");
    	if (driver.findElement(By.id("dialog_alert_message")).getText().contains("El n\u00famero se elimin\u00f3 con \u00e9xito!"))
    		elimVoz = true;
    	scrollAndClick(driver, "xpath", "//android.widget.Button[@text='ACEPTAR']");
    	scrollAndClick(driver, "id", "imageview_component_image");
    	scrollAndClick(driver, "id", "button1");
    	if (driver.findElement(By.id("dialog_alert_message")).getText().contains("El n\u00famero se elimin\u00f3 con \u00e9xito!"))
    		elimSMS = true;
    	scrollAndClick(driver, "xpath", "//android.widget.Button[@text='ACEPTAR']");
    	return actVoz && actSMS && elimVoz && elimSMS;
    }
	
	public boolean bajaDeSuscripcion(AndroidDriver<AndroidElement> driver) {
    	boolean bajaSus = false;
    	scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='MIS SERVICIOS']");
    	scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='Mis suscripciones a servicios']");
    	scrollAndClick(driver, "id", "imageview_component_image");
    	scrollAndClick(driver, "id", "button1");
    	if (driver.findElement(By.className("android.widget.TextView")).getText().contains("La suscripci\u00f3n se di\u00f3 de baja con \u00e9xito"))
    		bajaSus = true;
    	scrollAndClick(driver, "id", "button1");
    	return bajaSus;
    }
	
	public boolean cambioDeDomicilio(AndroidDriver<AndroidElement> driver, String DNI) {
		scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='MIS SERVICIOS']");
    	scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='Domicilio de Facturaci\u00f3n']");
    	//String direccionVieja = driver.findElement(By.id("textinput_component_edittext")).getText();
    	String direccionNueva = "Av Rivadavia";
    	driver.findElement(By.id("textinput_component_edittext")).clear();
    	driver.findElement(By.id("textinput_component_edittext")).sendKeys(direccionNueva);
    	driver.findElement(By.xpath("(//*[@id='complex_component_layout']/*/*[@id='textinput_component_edittext_number'])[3]")).clear();
    	driver.findElement(By.xpath("(//*[@id='complex_component_layout']/*/*[@id='textinput_component_edittext_number'])[3]")).sendKeys(DNI);
    	//scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='GUARDAR']");
    	return false;  //Mensaje de error no se puede modificar domicilio en este momento
	}
	
	public boolean verifDisponibles(AndroidDriver<AndroidElement> driver, String tipoDeLinea) {
    	boolean internet = false, minutos = false, sms = false;
    	scrollAndClick(driver, "id", "iv_chevron");
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
	
	public boolean verificarDetalleDeCredito(AndroidDriver<AndroidElement> driver) {
		boolean credito = false;
    	List<AndroidElement> credDisponible = driver.findElements(By.id("text_component_title_custom"));
    	for (int i=0; i<credDisponible.size(); i++) {
    		if (credDisponible.get(i).getText().contains("Cr\u00e9dito de mi l\u00ednea"))
    			credito = true;
    	}
    	return credito;
	}
	
	public boolean verificarFactura(AndroidDriver<AndroidElement> driver) {
		boolean factura = false;
    	driver.swipe(237, 854, 251, 231, 672);
        driver.findElement(By.xpath("//*[@id='button_component_layout' and ./*[@text='Mis facturas']]")).click();
        sleep(5000);
        for (WebElement x : driver.findElements(By.className("android.widget.TextView"))) {
        	if (x.getText().contains("Cup\u00f3n de pago"))
        		factura = true;
        }
        return factura;
	}
	
	public boolean verificarActivYDesactDobleBlue(AndroidDriver<AndroidElement> driver, String tipoDeLinea) {
		boolean activ = false, desact = false;
    	driver.findElement(By.xpath("//*[@id='button_component_layout']")).click();
    	scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='AMPLIAR MI CUOTA A 200 MB']");
    	scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='CONFIRMAR']");
    	switch(tipoDeLinea) {
    	case "MIX":
    		if (driver.findElement(By.id("dialog_alert_message")).getText().contains("Felicitaciones! Activaste la cuota Internet 200MB por Dia"))
        		activ = true;
    		break;
    	case "Pre":
    		if (driver.findElement(By.id("dialog_alert_message")).getText().contains("La Cuota Internet 200MB por Dia fue activada con \u00e9xito"))
        		activ = true;
    		break;
    	}
    	scrollAndClick(driver, "id", "button1");
    	scrollAndClick(driver, "id", "iv_chevron");
    	scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='Modificar']");
    	switch(tipoDeLinea) {
    	case "MIX":
    		scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='CONFIRMAR']");
        	if (driver.findElement(By.id("dialog_alert_message")).getText().contains("Se desactiv\u00f3 con \u00e9xito Internet 200MB por Dia"))
        		desact = true;
    		break;
    	case "Pre":
    		scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='REDUCIR MI CUOTA 50 MB']");
        	scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='CONFIRMAR']");
        	if (driver.findElement(By.id("dialog_alert_message")).getText().contains("La Cuota Internet X Dia fue activada con \u00e9xito"))
        		desact = true;
    		break;
    	}
    	scrollAndClick(driver, "id", "button1");
    	return activ && desact;
	}
	
	public boolean verificarPagoConTarjetaDeCredito(AndroidDriver<AndroidElement> driver) {
		boolean factura = false;
		try {
			scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='PAGOS, RECARGAS Y PACKS']");
		} catch(Exception e) {
			scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='PAGOS Y PACKS']");
		}
		scrollAndClick(driver, "xpath", "//*[@id='button1' and ./*[@text='Pag\u00e1 con']]");
    	for (WebElement x : driver.findElements(By.className("android.view.View"))) {
    		if (x.getText().contains("Complet\u00e1 el siguiente formulario para finalizar el pago"))
    			factura = true;
    	}
    	return factura;
	}
	
	public boolean verificarPagoConPagoMisCuentas(AndroidDriver<AndroidElement> driver) {
		boolean pmc = false;
		try {
			scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='PAGOS, RECARGAS Y PACKS']");
		} catch(Exception e) {
			scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='PAGOS Y PACKS']");
		}
		scrollAndClick(driver, "xpath", "//*[@id='button2' and ./*[@text='Pag\u00e1 con']]");
    	for (WebElement x : driver.findElements(By.className("android.widget.TextView"))) {
    		if (x.getText().contains("Pago con Pago Mis Cuentas: Paso a paso"))
    			pmc = true;
    	}
    	return pmc;
	}
	
	public boolean verificarPagoOnline(AndroidDriver<AndroidElement> driver) {
		boolean tdc = false, hb = false, pmc = false, pagos = false;
		try {
			scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='PAGOS, RECARGAS Y PACKS']");
		} catch(Exception e) {
			scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='PAGOS Y PACKS']");
		}
		scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='Pago online']");
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
    	return tdc && hb && pmc && pagos;
	}
	
	public boolean verificarPagoPresencial(AndroidDriver<AndroidElement> driver) {
		boolean pee = false, ca = false, pcc = false;
		try {
			scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='PAGOS, RECARGAS Y PACKS']");
		} catch(Exception e) {
			scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='PAGOS Y PACKS']");
		}
		scrollAndClick(driver, "xpath", "//android.widget.TextView[@text='Pago Presencial']");
    	for (WebElement x : driver.findElements(By.className("android.widget.TextView"))) {
    		if (x.getText().contains("Pagos en efectivo"))
    			pee = true;
    		if (x.getText().contains("Cajeros Autom\u00e1ticos"))
    			ca = true;
    		if (x.getText().contains("Pagos con Cheque"))
    			pcc = true;
    	}
    	return pee && ca && pcc;
	}
}