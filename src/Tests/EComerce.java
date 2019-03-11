package Tests;

import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import PageMetodos.Metodos;


public class EComerce extends Metodos{
	
	private WebDriver driver;
	
	String imagen;

	@BeforeMethod (alwaysRun = true)
		public void before(){
		driver = setup();
		driver.get("https://personaluat.vtexcommercestable.com.br/Admin/Site/Login.aspx?ReturnUrl=%2f");
		sleep(8000);
		try {
			driver.findElement(By.id("vtexIdUI-google-plus")).click();
			sleep(5000);
			ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
			driver.switchTo().window(tabs2.get(1));
			driver.findElement(By.id("identifierId")).sendKeys("ecomerce.testing@gmail.com");
			driver.findElement(By.id("identifierNext")).click();
			sleep(5000);
			driver.findElement(By.name("password")).sendKeys("Telecom*123");
			driver.findElement(By.id("passwordNext")).click();
			sleep(10000);
			driver.switchTo().window(tabs2.get(0));
		}catch(Exception ex1) {
			}
		
	}
	
	//@AfterMethod (alwaysRun = true)
	public void after(){
		tomarCaptura(driver,imagen);
		try{
			driver.findElement(By.id("vtex-callcenter__user-logout")).click();
			sleep(5000);
			driver.quit();
		}catch(Exception ex1){
			driver.quit();
		}
	}
	
	@Test (groups ={"Home Tienda","Visualizacion de Productos"}) 
	public void C28_Visualizacion_de_productos_en_cliente_No_logueado(){
		imagen = "C28_Visualizacion_de_productos_en_cliente_No_logueado";
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		Boolean precio = false;
		List<WebElement> precios = driver.findElements(By.cssSelector(".product-list__price--leyend"));
			for(WebElement p : precios){
				if(p.getText().contains("\u0024")){
					precio = true;	
				}
			}
		Assert.assertTrue(precio);
	}
	
	@Test (groups ={"Home Tienda","Visualizacion de Productos"}) 
	public void C27_Visualizacion_de_catalogo_de_equipos_disponibles(){
		imagen = "C27_Visualizacion_de_catalogo_de_equipos_disponibles";
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		Boolean precio = false;
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary.btn-lg.btn-block")),"contains","ver todos los equipos");
		List<WebElement> precios = driver.findElements(By.cssSelector(".font-platform-regular.text-primary"));
			for(WebElement p : precios){
				if(p.getText().contains("\u0024")){
					precio = true;	
				}
			}
		Assert.assertTrue(precio);
	}
	
	
	
	@Test (groups ={"Home Tienda","Barra Navegacion"}) 
	public void C31_Cliente_no_logueado_ingresa_a_Planes(){
		imagen="C31_Cliente_no_logueado_ingresa_a_Planes";
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebElement searchBtn = driver.findElement(By.id("tpi-navbar-item2"));
		Actions action = new Actions(driver);
		action.moveToElement(searchBtn).perform();
		driver.findElement(By.linkText("Pasate a Personal")).click();
		Assert.assertTrue(driver.getCurrentUrl().equals("https://www.personal.com.ar/portabilidad/"));
	}
	
	@Test (groups ={"Home Tienda","Barra Navegacion"}) 
	public void C29_Cliente_no_logueado_ingresa_a_Autogestion(){
		imagen="C29_Cliente_no_logueado_ingresa_a_Autogestion";
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		WebElement searchBtn = driver.findElement(By.id("tpi-navbar-item3"));
		Actions action = new Actions(driver);
		action.moveToElement(searchBtn).perform();
		driver.findElement(By.linkText("Comprar packs")).click();
		Assert.assertTrue(driver.getCurrentUrl().equals("https://autogestionuat.personal.com.ar/login/"));
	}
	
	@Test (groups ={"Home Tienda","Barra Navegacion"}) 
	public void C33_Cliente_no_logueado_ingresa_a_Temas_de_Ayuda(){
		imagen="C33_Cliente_no_logueado_ingresa_a_Temas_de_Ayuda";;
		sleep(10000);
		WebElement searchBtn = driver.findElement(By.id("tpi-navbar-item5"));
		Actions action = new Actions(driver);
		action.moveToElement(searchBtn).perform();
		sleep(3000);
		driver.findElement(By.xpath("//strong[contains(text(),'Temas de ayuda')]")).click();
	}
	
	@Test (groups ={"Home Tienda","Barra Navegacion"}) 
	public void C32_Cliente_no_logueado_ingresa_a_Soporte_Tecnico(){
		imagen="C32_Cliente_no_logueado_ingresa_a_Soporte_Tecnico";;
		sleep(10000);
		WebElement searchBtn = driver.findElement(By.id("tpi-navbar-item5"));
		Actions action = new Actions(driver);
		action.moveToElement(searchBtn).perform();
		sleep(3000);
		driver.findElement(By.xpath("//strong[contains(text(),'Soporte tecnico')]")).click();
	}
	
	@Test (groups ={"Home Tienda","Barra Navegacion"}) 
	public void C30_Cliente_no_logueado_ingresa_a_Ayuda_Lineas(){
		imagen="C30_Cliente_no_logueado_ingresa_a_Ayuda_Lineas";;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		WebElement searchBtn = driver.findElement(By.id("tpi-navbar-item5"));
		Actions action = new Actions(driver);
		action.moveToElement(searchBtn).perform();
		driver.findElement(By.linkText("Lineas")).click();
		Assert.assertTrue(driver.getCurrentUrl().equals("https://uat.personal.com.ar/ayudaysoporte/#/individuos/soporte/lineas"));
	}
	
	@Test (groups ={"Home Tienda","Barra Navegacion"}) 
	public void C02_Busqueda_de_resultados_relacionados(){
		imagen="C02_Busqueda_de_resultados_relacionados";;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.findElement(By.cssSelector(".tpi-search")).click();
		driver.findElement(By.id("searchable1")).sendKeys("linea");
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Tienda Online'])[1]/following::p[1]")).click();
		Assert.assertTrue(driver.getCurrentUrl().equals("https://tienda.personal.com.ar/"));
	}
	
	@Test (groups ={"Home Tienda","Barra Navegacion"}) 
	public void C01_Busqueda_de_resultado(){
		imagen="C01_Busqueda_de_resultado";
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector(".tpi-search")).click();
		driver.findElement(By.id("searchable1")).sendKeys("cambio de equipo");
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Tienda Online'])[1]/following::p[1]")).click();
		Assert.assertTrue(driver.getCurrentUrl().equals("https://tienda.personal.com.ar/"));
	}
	
	@Test (groups ={"Home Tienda","Filtros"}) 
	public void C09_Filtro_Marcas(){
		imagen="C09_Filtro_Marcas";
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector(".filter__item--mark.filter__item--mark-Todas.las.marcas")).click();
		driver.findElement(By.cssSelector(".filter__item.filter__item--mark.filter__item--mark-samsung")).click();
		List<WebElement> elementos = driver.findElements(By.cssSelector(".shelf-product-list"));
		Boolean elementoEncontraado = false;		
		for (WebElement elemento : elementos) {
		    if(!elemento.getText().contains("Samsung"))
		    {
		    	elementoEncontraado = true;
		    }		    
		}
		Assert.assertTrue(elementoEncontraado); 		
	}
	
	
	@Test (groups ={"Home Tienda","Filtros"}) 
	public void C11_Filtro_Tipo_de_Camara(){
		imagen="C11_Filtro_Tipo_de_Camara";
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		buscarYClick(driver.findElements(By.cssSelector(".filter__combo--select")),"contains","Tipos de camara");
		buscarYClick(driver.findElements(By.cssSelector(".filter__item")),"contains","10Mpx a 15Mpx");
		
		List<WebElement> resultadoBusqueda = driver.findElements(By.cssSelector(".btn.btn-default.btn-lg.product-list__link"));
		Boolean filtroValido = true;
		for (int i = 0; i <= 0; ++i) {
		    resultadoBusqueda.get(i).click();
		    
			List<WebElement> elementosDetalle = driver.findElements(By.cssSelector(".detail__title--item"));
			for(WebElement detalle : elementosDetalle)
			{
				if(detalle.getText().contains("Mpx"))
				{
					String tipoCamara = detalle.getText();
					int index = tipoCamara.indexOf("Mpx");
					int valorMpx = Integer.parseInt(tipoCamara.substring(index -2, index));
					filtroValido = (filtroValido == (valorMpx >= 10 && valorMpx <= 15));					
				}
			}	
			driver.navigate().back();
			driver.navigate().back();
		}
		Assert.assertTrue(filtroValido); 			
	}
	
	@Test (groups ={"Home Tienda","Filtros"}) 
	public void C13_Filtro_Tipo_de_Pantalla(){
		imagen="C13_Filtro_Tipo_de_Pantalla";
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		buscarYClick(driver.findElements(By.cssSelector(".filter__combo--select")),"contains","Tama�o de pantalla");
		buscarYClick(driver.findElements(By.cssSelector(".filter__item")),"contains","grande");
		
		List<WebElement> resultadoBusqueda = driver.findElements(By.cssSelector(".btn.btn-default.btn-lg.product-list__link"));
		Boolean filtroValido = resultadoBusqueda.size() > 0;
		// TODO: Pendiente seg�n respuesta analistas, hacer la validaci�n de los resultados.

		Assert.assertTrue(filtroValido); 			
	}
	
	@Test (groups ={"Home Tienda","Filtros"}) 
	public void C17_Limite_de_comparador_de_Equipos(){
		imagen="C17_Limite_de_comparador_de_Equipos";;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary.btn-lg.btn-block")),"contains","ver todos los equipos");
		List<WebElement> elementos = driver.findElements(By.cssSelector(".comparator__checkbox.icn.checkbox"));
		for (WebElement elemento : elementos) {
		    elemento.click();
		}
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary")),"contains","comparar");
		elementos = driver.findElements(By.cssSelector(".buy-button.btn.btn-danger"));
		Boolean elementoEncontraado = true;		
		for (WebElement elemento : elementos) {
		    if(!elemento.getText().contains("COMPRAR"))
		    {
		    	elementoEncontraado = false;
		    }		    
		}
		Assert.assertTrue(elementoEncontraado);
		Assert.assertTrue(elementos.size()==4); 	
	}
	
	@Test (groups ={"Interna Producto","Financiacion"}) 
	public void C52_Cliente_no_logueado_elige_linea_Nueva(){
		imagen="C52_Cliente_no_logueado_elige_linea_Nueva";
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary.btn-lg.btn-block")),"contains","ver todos los equipos");
		buscarYClick(driver.findElements(By.cssSelector(".product-list__button")),"contains","ver detalle");
		driver.findElement(By.cssSelector(".product-main__btn.product-main__btn--buy.btn.btn-primary.js-steps")).click();
		driver.findElement(By.cssSelector(".product-main__btn.btn.btn-default.js-select-plan.js-steps")).click();

		List<WebElement> planesFacPre = driver.findElements(By.cssSelector(".page-header"));
		int elementosEncontrados = 0;
		for(WebElement plan : planesFacPre)
		{
			if(plan.getText().contains("Planes con Factura")) 
			{
				elementosEncontrados++;
			}
			if(plan.getText().contains("Plan Prepago")) 
			{
				elementosEncontrados++;
			}
		}
		List<WebElement> planAbono = driver.findElements(By.cssSelector(".text-brand-cyan.product-main__mercado--subtitle"));
		for(WebElement plan : planAbono)
		{
			if(plan.getText().contains("Abono Fijo")) 
			{
				elementosEncontrados++;
			}
		}
		
		Assert.assertTrue(elementosEncontrados == 3);		
	}
	
	
	@Test (groups ={"Interna Producto","Entrega"}) 
	public void C54_plazos_y_opciones_de_entrega(){
		imagen="C54_plazos_y_opciones_de_entrega";
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary.btn-lg.btn-block")),"contains","ver todos los equipos");
		buscarYClick(driver.findElements(By.cssSelector(".product-list__button")),"contains","ver detalle");
		buscarYClick(driver.findElements(By.cssSelector(".icon__shipping--product")),"contains","ver plazos");
		List<WebElement> elementos = driver.findElements(By.cssSelector(".modal-body-shipping"));
		Boolean elementoEncontrado = false;
		for (WebElement elemento : elementos)
		{
			if(elemento.getText().contains("ENV\u00CDO A TODO EL PA\u00CDS"))
			{
				elementoEncontrado = true;
			}
		}		
		Assert.assertTrue(elementoEncontrado);		
	}
	
	@Test (groups ={"Interna Producto","Financiacion"}) 
	public void C55_opciones_de_financiacion(){
		imagen="C55_opciones_de_financiacion";
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary.btn-lg.btn-block")),"contains","ver todos los equipos");
		buscarYClick(driver.findElements(By.cssSelector(".product-list__button")),"contains","ver detalle");
		buscarYClick(driver.findElements(By.cssSelector(".product-main__btn.product-main__btn--payment")),"contains","financiaci\u00F3n");
		List<WebElement> elementos = driver.findElements(By.cssSelector(".calculator__title"));
		Boolean elementoEncontrado = false;
		for (WebElement elemento : elementos)
		{
			if(elemento.getText().contains("El Personal que quer\u00E9s, con la financiaci\u00F3n que necesit\u00E1s"))
			{
				elementoEncontrado = true;
			}
		}		
		Assert.assertTrue(elementoEncontrado);		
	}
	
	@Test (groups ={"Interna Producto","Financiacion"}) 
	public void C56_opciones_de_Cuotas(){
		imagen="C56_opciones_de_Cuotas";
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary.btn-lg.btn-block")),"contains","ver todos los equipos");
		buscarYClick(driver.findElements(By.cssSelector(".product-list__button")),"contains","ver detalle");
		buscarYClick(driver.findElements(By.cssSelector(".product-main__btn.product-main__btn--payment")),"contains","financiaci\u00F3n");
		WebElement comboCuotas = driver.findElement(By.cssSelector(".tp-selector__view"));
		Boolean elementoEncontrado = false;
		if(comboCuotas.getText().contains("cuotas"))
		{
			elementoEncontrado = true;
		}
			
		Assert.assertTrue(elementoEncontrado);		
	}


	@Test (groups ={"Home Tienda","Filtros"}) 
	public void C23_Ordenamiento_Precio_Mayor(){
		imagen="C23_Ordenamiento_Precio_Mayor";
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		buscarYClick(driver.findElements(By.cssSelector(".filter__combo--select")),"contains","destacados");
		buscarYClick(driver.findElements(By.cssSelector(".filter__item")),"contains","mayor precio");
		
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".product-list__price--leyend")));		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
		
	    List<WebElement> precios = driver.findElements(By.cssSelector(".product-list__price--leyend"));
	    int tamanio=precios.size();
	    double temp = 0;
	    Boolean elementoEncontrado = true;
	    for (int i = 0; i < tamanio; i++) {
	    	String precioS=precios.get(i).getText();
	    	if (!precioS.equals("")) {
		    	precioS=precioS.replace("$","");
		    	precioS=precioS.replace(".","");
		    	precioS=precioS.replace(",",".");
		    	double value = Double.parseDouble(precioS);
		    	System.out.println(value); 
		    	if (i>0) {
		    		if (temp<value) {
		    			elementoEncontrado = false;
		    		}		
		    	}
		    	temp=value;
	    	}
	    }
	    Assert.assertTrue(elementoEncontrado);	    
	}
	
	@Test (groups ={"Home Tienda","Filtros"}) 
	public void C25_Ordenamiento_Precio_Menor(){
		imagen="C25_Ordenamiento_Precio_Menor";
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		buscarYClick(driver.findElements(By.cssSelector(".filter__combo--select")),"contains","destacados");
		buscarYClick(driver.findElements(By.cssSelector(".filter__item")),"contains","menor precio");
		
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".product-list__price--leyend")));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		

	    List<WebElement> precios = driver.findElements(By.cssSelector(".product-list__price--leyend"));
	    int tamanio=precios.size();
	    double temp = 0;
	    Boolean elementoEncontrado = true;
	    for (int i = 0; i < tamanio; i++) {
	    	String precioS=precios.get(i).getText();
	    	if (!precioS.equals("")) {
		    	precioS=precioS.replace("$","");
		    	precioS=precioS.replace(".","");
		    	precioS=precioS.replace(",",".");
		    	double value = Double.parseDouble(precioS);
		    	System.out.println(value); 
		    	if (i>0) {
		    		if (temp>value) {
		    			elementoEncontrado = false;
		    		}		
		    	}
		    	temp=value;
	    	}
	    }
	    Assert.assertTrue(elementoEncontrado);	    
	}
	
	@Test (groups ={"Interna Producto","Financiacion"}) 
	public void C68_Compra_equipo_linea_nueva_Mail_registrado(){
		imagen="C68_Compra_equipo_linea_nueva_Mail_registrado";
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary.btn-lg.btn-block")),"contains","ver todos los equipos");
		buscarYClick(driver.findElements(By.cssSelector(".product-list__button")),"contains","ver detalle");
		driver.findElement(By.cssSelector(".product-main__btn.product-main__btn--buy.btn.btn-primary.js-steps")).click();
		driver.findElement(By.cssSelector(".product-main__btn.btn.btn-default.js-select-plan.js-steps")).click();
		buscarYClick(driver.findElements(By.cssSelector(".plan__btn.product-main__btn.btn.btn-default")),"contains","Quiero este plan");
		driver.findElement(By.cssSelector(".action__go-to-checkout.btn.btn-primary.btn-lg.col-md-3.col-sm-6.col-xs-12")).click();
		driver.findElement(By.id("client-pre-email")).sendKeys("alejandro-miguel.rubinstein@atos.net");

		driver.findElement(By.id("btn-client-pre-email")).click();		
		buscarYClick(driver.findElements(By.id("btn-identified-user-button")),"contains","continuar con la compra");

		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("client-first-name")));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("client-last-name")));
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		Boolean camposLlenos = false;		
		camposLlenos = !driver.findElement(By.id("client-first-name")).getText().isEmpty();		
		camposLlenos = !(camposLlenos == driver.findElement(By.id("client-last-name")).getText().isEmpty());
		
		Assert.assertTrue(camposLlenos);		
	}
	
	@Test (groups ={"Interna Producto","Financiacion"}) 
	public void C70_Compra_equipo_linea_nueva_Mail_no_registrado(){
		imagen="C70_Compra_equipo_linea_nueva_Mail_no_registrado";
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary.btn-lg.btn-block")),"contains","ver todos los equipos");
		buscarYClick(driver.findElements(By.cssSelector(".product-list__button")),"contains","ver detalle");
		driver.findElement(By.cssSelector(".product-main__btn.product-main__btn--buy.btn.btn-primary.js-steps")).click();
		driver.findElement(By.cssSelector(".product-main__btn.btn.btn-default.js-select-plan.js-steps")).click();
		buscarYClick(driver.findElements(By.cssSelector(".plan__btn.product-main__btn.btn.btn-default")),"contains","Quiero este plan");
		driver.findElement(By.cssSelector(".action__go-to-checkout.btn.btn-primary.btn-lg.col-md-3.col-sm-6.col-xs-12")).click();
		driver.findElement(By.id("client-pre-email")).sendKeys("correo@electronico.com");
		driver.findElement(By.id("btn-client-pre-email")).click();		
		
		Boolean camposVacios = false;		
		camposVacios = driver.findElement(By.id("client-first-name")).getText().isEmpty();		
		camposVacios = (camposVacios == driver.findElement(By.id("client-last-name")).getText().isEmpty());
		
		Assert.assertTrue(camposVacios);		
	}
	
	@Test (groups ={"Interna Accesorio","Financiacion"}) 
	public void C57_Compra_Accesorio_Carro_de_compra(){
		imagen="C57_Compra_Accesorio_Carro_de_compra";
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		
		
		driver.navigate().to("https://personaluat.vtexcommercestable.com.br/accesorios");
		buscarYClick(driver.findElements(By.cssSelector(".product-list__button")),"contains","ver detalle");
		driver.findElement(By.cssSelector(".buy-button.buy-button-ref")).click();			
		Boolean carroCompras = false;		
		carroCompras = driver.findElement(By.cssSelector(".cart__title--container")).getText().equals("Resumen de compra");	
		Assert.assertTrue(carroCompras);		
	}
	
	@Test (groups ={"Interna Accesorio","Financiacion"}) 
	public void C72_Compra_Accesorio_Mail_no_registrado(){
		imagen="C72_Compra_Accesorio_Mail_no_registrado(TS052)";
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.navigate().to("https://personaluat.vtexcommercestable.com.br/accesorios");
		buscarYClick(driver.findElements(By.cssSelector(".product-list__button")),"contains","ver detalle");
		driver.findElement(By.cssSelector(".buy-button.buy-button-ref")).click();
		driver.findElement(By.cssSelector(".action__go-to-checkout.btn.btn-primary.btn-lg.col-md-3.col-sm-6.col-xs-12")).click();
		driver.findElement(By.id("client-pre-email")).sendKeys("correo@electronico.com");
		driver.findElement(By.id("btn-client-pre-email")).click();		
		
		Boolean camposVacios = false;		
		camposVacios = driver.findElement(By.id("client-first-name")).getText().isEmpty();		
		camposVacios = (camposVacios == driver.findElement(By.id("client-last-name")).getText().isEmpty());
		
		Assert.assertTrue(camposVacios);		
	}
	
	@Test (groups ={"Registracion de email","Linea nueva"}) 
	public void TS049_Cliente_no_logueado_compra_equipo_con_nueva_linea_e_ingresa_un_mail_registrado(){
		imagen="TS049_Cliente_no_logueado_compra_equipo_con_nueva_linea_e_ingresa_un_mail_registrado";
		driver.findElements(By.cssSelector(".col-xs-5.col-sm-12")).get(2).click();
		sleep(8000);
		buscarYClick(driver.findElements(By.cssSelector(".product-main__btn.product-main__btn--buy.btn.btn-primary.js-steps")),"equals","comprar");
		sleep(5000);
		buscarYClick(driver.findElements(By.cssSelector(".product-main__btn.btn.btn-default.js-select-plan.js-steps")),"contains","lo quiero con linea nueva");
		sleep(5000);
		buscarYClick(driver.findElements(By.cssSelector(".plan__btn.product-main__btn.btn.btn-default")),"equals","quiero este plan");
		sleep(8000);
		buscarYClick(driver.findElements(By.cssSelector(".action__go-to-checkout.btn.btn-primary.btn-lg.col-md-3.col-sm-6.col-xs-12")),"equals","siguiente");
		sleep(8000);
		driver.findElement(By.id("client-pre-email")).sendKeys("alejandro-miguel.rubinstein@atos.net");
		driver.findElement(By.id("btn-client-pre-email")).click();
		sleep(6000);
		driver.findElement(By.id("btn-identified-user-button")).click();
		sleep(3000);
		Assert.assertFalse(driver.findElement(By.id("client-first-name")).getAttribute("value").isEmpty());
	}
	
	
	@Test(groups ={"Registracion de email","Venta Accesorio"}) // El accesorio lo reconoce como un equipo y pregunta por la linea 
	public void TS053_Cliente_no_logueado_compra_un_accesorio_e_ingresa_un_mail_no_registrado_y_debe_completar_todos_los_datos_en_checkout(){
		imagen="TS053_Cliente_no_logueado_compra_un_accesorio_e_ingresa_un_mail_no_registrado_y_debe_completar_todos_los_datos_en_checkout";
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary.btn-lg.btn-block")),"contains","ver todos los equipos");
		sleep(7000);
		List <WebElement> products = driver.findElements(By.cssSelector(".product-list__wrapper"));
			for(WebElement p : products){
				if (p.getText().toLowerCase().contains("vidrio templado")){
					p.findElement(By.cssSelector(".col-xs-5.col-sm-12")).click();
					break;
				}
			}
		sleep(5000);
		buscarYClick(driver.findElements(By.cssSelector(".product-main__btn.product-main__btn--buy.btn.btn-primary.js-steps")),"equals","comprar");
		Assert.assertTrue(false);
	}
	
	@Test(groups ={"Registracion de email","Venta Accesorio","cliente logueado"})  // El accesorio lo reconoce como un equipo y pregunta por la linea 
	public void TS054_Cliente_logueado_compra_un_accesorio_e_ingresa_un_mail_cargado_en_Siebel(){
		imagen="TS054_Cliente_logueado_compra_un_accesorio_e_ingresa_un_mail_cargado_en_Siebel";
		loginEComerce("1164597262","1469");
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary.btn-lg.btn-block")),"contains","ver todos los equipos");
		sleep(7000);
		List <WebElement> products = driver.findElements(By.cssSelector(".product-list__wrapper"));
			for(WebElement p : products){
				if (p.getText().toLowerCase().contains("vidrio templado")){
					p.findElement(By.cssSelector(".col-xs-5.col-sm-12")).click();
					break;
				}
			}
		sleep(5000);
		buscarYClick(driver.findElements(By.cssSelector(".product-main__btn.product-main__btn--buy.btn.btn-primary.js-steps")),"equals","comprar");
		Assert.assertTrue(false);
	}
}