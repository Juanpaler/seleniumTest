package Tests;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import PageMetodos.Metodos;

public class ClubPersonalBack extends Metodos{
	
	private WebDriver driver;
	
	String nombreCaso;
	String archivoLineas="dataInput/LineasClubPersonalBack.xlsx";
	String rutaCaptura;
	String modulo="ClubPersonalBackWeb";

	@BeforeClass (alwaysRun = true)
	public void beforeClass() throws IOException {
		rutaCaptura=reportDirectory(modulo);
	}
	
	@BeforeMethod (alwaysRun = true)
		public void before(){
		driver = setup();	
	}
	
	@AfterMethod (alwaysRun = true)
	public void after(){
		tomarCaptura(driver,nombreCaso,rutaCaptura);
		try {
			driver.findElement(By.linkText("Salir")).click();
			sleep(3000);
			driver.close();
		}catch(Exception ex1){	driver.close();
		}
	}
	
	
	@Test (groups = "ClubPersonalBack", priority = 0)
	public void Adhesion_Usuario_Titular_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
		AdhesionTitularClubBack(linea,"testtest867");
    }
	
	@Test (groups = "ClubPersonalBack", priority = 1)
	public void Adhesion_Usuario_Titular_POS() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
		AdhesionTitularClubBack(linea,"testtest868");
    }
	
	@Test (groups = "ClubPersonalBack", priority = 2)
	public void Adhesion_Usuario_Titular_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
		AdhesionTitularClubBack(linea,"testtest687");
    }
	
	@Test (groups = "ClubPersonalBack", priority = 6, enabled = false)
	public void Adhesion_Linea_Usuario_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		loginClubBack();	
    }
	
	@Test (groups = "ClubPersonalBack", priority = 7,enabled = false)
	public void Adhesion_Linea_Usuario_POS() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		loginClubBack();	
    }
	
	@Test (groups = "ClubPersonalBack", priority = 8,enabled = false)
	public void Adhesion_Linea_Usuario_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		loginClubBack();	
    }
	
	@Test (groups = "ClubPersonalBack", priority = 12)
	public void Busqueda_por_Linea_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
        busquedaLinea(linea);
    }
	
	@Test (groups = "ClubPersonalBack", priority = 13)
	public void Busqueda_por_Linea_POS() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
        busquedaLinea(linea);
    }
	
	@Test (groups = "ClubPersonalBack", priority = 14)
	public void Busqueda_por_Linea_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
        busquedaLinea(linea);
    }
	
	@Test (groups = "ClubPersonalBack", priority = 18)
	public void Canje_de_Premio_Diferido_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
		canjePremioBack(linea,"Diferido");   
    }
	
	@Test (groups = "ClubPersonalBack", priority = 19)
	public void Canje_de_Premio_Diferido_POS() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
		canjePremioBack(linea,"Diferido");
    }
	
	@Test (groups = "ClubPersonalBack", priority = 20)
	public void Canje_de_Premio_Diferido_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
		canjePremioBack(linea,"Diferido");
    }
	
	@Test (groups = "ClubPersonalBack", priority = 25)
	public void Canje_de_Puntos_Canje_de_Voucher_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
		canjePremioBack(linea,"Voucher");   
    }
	
	@Test (groups = "ClubPersonalBack", priority = 26)
	public void Canje_de_Puntos_Canje_de_Voucher_POS() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
		canjePremioBack(linea,"Voucher");   
    }
	
	@Test (groups = "ClubPersonalBack", priority = 27)
	public void Canje_de_Puntos_Canje_de_Voucher_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
		canjePremioBack(linea,"Voucher");   
    }
	
	@Test (groups = "ClubPersonalBack", priority = 15)
	public void Busqueda_por_Documento_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
        busquedaPorDni("38092272", linea);
    }
	
	@Test (groups = "ClubPersonalBack", priority = 16)
	public void Busqueda_por_Documento_POS() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
        busquedaPorDni("21976636", linea);
    }
	
	@Test (groups = "ClubPersonalBack", priority = 17)
	public void Busqueda_por_Documento_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
        busquedaPorDni("5235040", linea);
    }
	
	public void movimientosPuntosBack() {
		driver.findElement(By.linkText("Movimientos de Puntos")).click();				

		String totalPuntosString = 	driver.findElement(By.xpath("//table[@id='panelResumen']/tbody/tr[3]/td[2]")).getText();
		Double totalPuntos = Double.parseDouble(totalPuntosString);
		Double sumaPuntos = 0.0;
		
		WebElement lineas = driver.findElement(By.name("idLine"));
		lineas.click();
		lineas.sendKeys("(Todas)");
		lineas.sendKeys(Keys.ENTER);
		driver.findElement(By.id("btnPointsMovements")).click();
		
		int cantFilas = driver.findElements(By.xpath("//table[@class='tablaDatos'][@align='left']/tbody/tr")).size();
		
		for (int i = 1; i <= cantFilas; i++) 
		{
			String puntos = driver.findElement(By.xpath("//table[@class='tablaDatos'][@align='left']/tbody/tr[" + i + "]/td[10]")).getText();             
            sumaPuntos = sumaPuntos + Double.parseDouble(puntos);              
		}
		Assert.assertTrue(totalPuntos.equals(sumaPuntos));
	}
	
	@Test (groups = "ClubPersonalBack", priority = 28)
	public void Movimientos_de_Puntos_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		loginClubBack();
		Busqueda_por_Linea(linea);
        movimientosPuntosBack();
	}
	
	@Test (groups = "ClubPersonalBack", priority = 29)
	public void Movimientos_de_Puntos_POS() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		loginClubBack();
		Busqueda_por_Linea(linea);
        movimientosPuntosBack();
	}
	
	@Test (groups = "ClubPersonalBack", priority = 30)
	public void Movimientos_de_Puntos_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		loginClubBack();
		Busqueda_por_Linea(linea);
        movimientosPuntosBack();
	}
	
	private void Busqueda_por_Linea(String linea)
	{
		WaitForElement("id", "submenu");
		driver.findElement(By.linkText("Búsqueda por Línea")).click();
		WaitForElement("id", "lineNumber");
		driver.findElement(By.id("lineNumber")).sendKeys(linea);
		WaitForElement("id", "btnBuscar");
		driver.findElement(By.id("btnBuscar")).click();		
	}
	
	private Boolean Validar_Resumen_de_Puntos()
	{
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WaitForElement("id", "submenu");
		driver.findElement(By.linkText("Resumen de Puntos")).click();	
		
		int cantFilas = driver.findElements(By.xpath("//table[@class='tablaDatos'][@align='center']/tbody/tr")).size();

		String totalPuntosString = 	driver.findElement(By.xpath("//table[@id='panelResumen']/tbody/tr[3]/td[2]")).getText();
		Double totalPuntos = Double.parseDouble(totalPuntosString);
		Double sumaPuntos = 0.0;
		
		for(int i=1; i<= cantFilas; i++)
		{
			String puntos = driver.findElement(By.xpath("//table[@class='tablaDatos'][@align='center']/tbody["+i+"]/tr[1]/td[8]")).getText();
			sumaPuntos = sumaPuntos + Double.parseDouble(puntos);
		}
		return (totalPuntos.equals(sumaPuntos));
	}
	
	@Test (groups = "ClubPersonalBack", priority = 33)
	public void Resumen_de_Puntos_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
		Busqueda_por_Linea(linea);
        Assert.assertTrue(Validar_Resumen_de_Puntos());
    }
	
	@Test (groups = "ClubPersonalBack", priority = 31)
	public void Resumen_de_Puntos_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
		Busqueda_por_Linea(linea);
        Assert.assertTrue(Validar_Resumen_de_Puntos());
    }
	
	@Test (groups = "ClubPersonalBack", priority = 32)
	public void Resumen_de_Puntos_POS() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
		Busqueda_por_Linea(linea);
        Assert.assertTrue(Validar_Resumen_de_Puntos());
    }
	
	public void canjesRealizadosBack() {
		
		driver.findElement(By.linkText("Canjes Realizados")).click();				

		int cantFilas = driver.findElements(By.xpath("//table[@class='tablaDatos'][@align='left']/tbody/tr")).size();
		for (int i = 1; i <= cantFilas; i++) {
			String premio = driver.findElement(By.xpath("//table[@class='tablaDatos'][@align='left']/tbody/tr[" + i + "]/td[10]")).getText();
			System.out.println(premio);
		}
	}
	
	@Test (groups = "ClubPersonalBack", priority = 34)
	public void Canjes_Realizados_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		loginClubBack();
		Busqueda_por_Linea(linea);
		canjesRealizadosBack();
	}
	
	@Test (groups = "ClubPersonalBack", priority = 35)
	public void Canjes_Realizados_POS() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		loginClubBack();
		Busqueda_por_Linea(linea);
		canjesRealizadosBack();
	}
	
	@Test (groups = "ClubPersonalBack", priority = 36)
	public void Canjes_Realizados_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		loginClubBack();
		Busqueda_por_Linea(linea);
		canjesRealizadosBack();
	}
	
	@Test (groups = "ClubPersonalBack", priority = 40)
	public void Creacion_de_producto_TP() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		loginClubBack();
		nuevoProducto();
		int nroIntentoCreacion = 0;
		String fechaActual = GetStringDate();
		String fechaActualSinBarras = fechaActual.replace("/", "");
		String nombreProducto = "Credito 50 Prueba "+fechaActualSinBarras;
		driver.findElement(By.id("productTypeId")).sendKeys("Producto TP");
		driver.findElement(By.name("productCategory")).sendKeys("Cr");
		cargarNombreProducto(nombreProducto);

		String path = new File(".").getCanonicalPath();
		driver.findElement(By.id("idFile")).sendKeys(path +"/dataInput/ClubPersonalxDefecto.jpeg");
		
		String fechaMasUnDia = GetStringDatePlusDay(1);
		cargarFechaProducto(fechaActual, fechaMasUnDia);

		driver.findElement(By.name("productProvider")).sendKeys("Albus");
		driver.findElement(By.id("money")).sendKeys("50");
		driver.findElement(By.name("productEfectivization")).sendKeys("Inmediata");
		driver.findElement(By.name("origin")).sendKeys("Argentina");
		driver.findElement(By.name("guarantee")).sendKeys("1 mes");
		driver.findElement(By.id("productInfiniteStock")).click();
		driver.findElement(By.name("productAvailableStock")).sendKeys("10");
		driver.findElement(By.name("creditAmmount")).sendKeys("3");
		
		Boolean saved = grabarProducto();
		
		while (saved == false) {
			nroIntentoCreacion = nroIntentoCreacion + 1;
			driver.findElement(By.id("idFile")).sendKeys(path +"\\ClubPersonalxDefecto.jpeg");
			cargarFechaProducto(fechaActual, fechaMasUnDia);
			cargarNombreProducto(nombreProducto+"_" + nroIntentoCreacion);
			saved = grabarProducto();
		}

		validarProductoCargado(nombreProducto, nroIntentoCreacion);
	}
	
	public void validarProductoCargado(String nombreProducto, int nroIntentoCreacion) {	
		if(nroIntentoCreacion > 0)
		{
			nombreProducto = nombreProducto +"_" + nroIntentoCreacion;
		}
		
		driver.findElement(By.id("idCode")).sendKeys(nombreProducto);
		driver.findElement(By.id("idCode")).sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("//table[@class='tablaDatos']/tbody/tr[2]/td[2]")).click();	
		String proveedor =new Select(driver.findElement(By.name("productProvider"))).getFirstSelectedOption().getText();
		String origen =new Select(driver.findElement(By.name("origin"))).getFirstSelectedOption().getText();
		String efectivizacion=new Select(driver.findElement(By.name("productEfectivization"))).getFirstSelectedOption().getText();
		String garantia=new Select(driver.findElement(By.name("guarantee"))).getFirstSelectedOption().getText();		
		String categoria=new Select(driver.findElement(By.name("productCategory"))).getFirstSelectedOption().getText();		
		String codigo=driver.findElement(By.id("productCodeId")).getAttribute("value");
		String nombre=driver.findElement(By.name("productName")).getAttribute("value");
		String descripcion=driver.findElement(By.name("productDescription")).getText();
		String observaciones=driver.findElement(By.name("productObservations")).getText();
		String valor=driver.findElement(By.id("money")).getAttribute("value");
		String stockIlimitado=driver.findElement(By.id("productInfiniteStock")).getAttribute("value");
		String stock=driver.findElement(By.name("productAvailableStock")).getAttribute("value");
		String credito=driver.findElement(By.name("creditAmmount")).getAttribute("value");
		
		Assert.assertTrue(proveedor.equals("Albus"));
		Assert.assertTrue(origen.equals("Argentina"));
		Assert.assertTrue(efectivizacion.equals("Inmediata"));
		Assert.assertTrue(garantia.equals("1 mes"));
		Assert.assertTrue(categoria.equals("Cr�dito (Personal con Tarjeta / con Abono Fijo)"));
		Assert.assertTrue(codigo.equals(nombreProducto));
		Assert.assertTrue(nombre.equals(nombreProducto));
		Assert.assertTrue(descripcion.equals(nombreProducto));
		Assert.assertTrue(observaciones.equals(nombreProducto));
		Assert.assertTrue(valor.equals("50,00"));
		Assert.assertTrue(stockIlimitado.equals("1"));
		Assert.assertTrue(stock.equals("10"));
		Assert.assertTrue(credito.equals("3"));	
		
	}
	public Boolean grabarProducto() {		
		driver.findElement(By.id("btnGuardar")).click();		
		Boolean saved = !ElementCreated("cssSelector",".error",5);
		return saved;
	}
	public void cargarFechaProducto(String fechaActual, String fechaMasUnDia) {		
		((JavascriptExecutor)driver).executeScript("document.getElementById('productDateFromText').value='"+fechaActual+"'");
		((JavascriptExecutor)driver).executeScript("document.getElementById('productDateFrom').value='"+fechaActual+"'");		
		((JavascriptExecutor)driver).executeScript("document.getElementById('productDateToText').value='"+fechaMasUnDia+"'");
		((JavascriptExecutor)driver).executeScript("document.getElementById('productDateTo').value='"+fechaMasUnDia+"'");
	}
	public void cargarNombreProducto(String nombreProducto) {		
		driver.findElement(By.id("productCodeId")).clear();;
		driver.findElement(By.name("productName")).clear();
		driver.findElement(By.name("productDescription")).clear();
		driver.findElement(By.name("productObservations")).clear();
		driver.findElement(By.id("productCodeId")).sendKeys(nombreProducto);
		driver.findElement(By.name("productName")).sendKeys(nombreProducto);
		driver.findElement(By.name("productDescription")).sendKeys(nombreProducto);
		driver.findElement(By.name("productObservations")).sendKeys(nombreProducto);
	}
	public void nuevoProducto() {		
		driver.findElement(By.linkText("Adm. de Premios")).click();
		driver.findElement(By.linkText("Productos")).click();
		driver.findElement(By.xpath("//input[@onclick='newProduct()'][@class='botonIzq']")).click();
	}
	
	@Test (groups = "ClubPersonalBack", priority = 41)
	public void Creacion_de_premio_TP() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		loginClubBack();
		nuevoPremio();
		String fechaActual = GetStringDate();
		String fechaActualSinBarras = fechaActual.replace("/", "");
		String nombrePremio = "Credito 50 Prueba "+fechaActualSinBarras;
		
		driver.findElement(By.id("description")).sendKeys(nombrePremio);
		driver.findElement(By.name("factura")).click();
		driver.findElement(By.name("tarjeta")).click();
		driver.findElement(By.name("abonoFijo")).click();		
		driver.findElement(By.id("checkbox0")).click();		
		driver.findElement(By.id("idProductCategory")).sendKeys("Cr");
		driver.findElement(By.id("producto")).sendKeys(nombrePremio);		
		driver.findElement(By.id("pointsValue")).sendKeys("50");
		driver.findElement(By.id("bonus")).sendKeys("30");		
		driver.findElement(By.name("published")).click();
		driver.findElement(By.name("webVisible")).click();
		driver.findElement(By.name("canjeWeb")).click();		
		driver.findElement(By.id("legales")).sendKeys("Legales");
		
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);	
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("btnGuardar")));	
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);		

		driver.findElement(By.id("btnGuardar")).click();	
		validarPremioCargado(nombrePremio);
	}
	
	public void validarPremioCargado(String nombrePremio) {		
		
		driver.findElement(By.name("description")).sendKeys(nombrePremio);
		driver.findElement(By.name("description")).sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("//table[@class='tablaDatos1']/tbody/tr[2]/td[2]/a[2]")).click();	
		
		String descripcion=driver.findElement(By.id("description")).getAttribute("value");
		String factura=driver.findElement(By.name("factura")).getAttribute("value");
		String tarjeta=driver.findElement(By.name("tarjeta")).getAttribute("value");
		String abonoFijo=driver.findElement(By.name("abonoFijo")).getAttribute("value");
		String categoria =new Select(driver.findElement(By.id("idProductCategory"))).getFirstSelectedOption().getText();
		String producto =new Select(driver.findElement(By.id("producto"))).getFirstSelectedOption().getText();
		String categoriaBlack=driver.findElement(By.id("checkbox0")).getAttribute("value");
		String valorPuntos=driver.findElement(By.id("pointsValue")).getAttribute("value");
		String bonificacion=driver.findElement(By.id("bonus")).getAttribute("value");
		String publicado=driver.findElement(By.name("published")).getAttribute("value");
		String visibleWeb=driver.findElement(By.name("webVisible")).getAttribute("value");
		String canjeWeb=driver.findElement(By.name("canjeWeb")).getAttribute("value");
		String legales=driver.findElement(By.id("legales")).getText();
		
		Assert.assertTrue(descripcion.equals("Credito 50 Prueba 29032019"));
		Assert.assertTrue(factura.equals("on"));
		Assert.assertTrue(tarjeta.equals("on"));
		Assert.assertTrue(abonoFijo.equals("on"));
		Assert.assertTrue(categoria.equals("Cr\u00E9dito (Personal con Tarjeta / con Abono Fijo)"));
		Assert.assertTrue(producto.equals("Credito 50 Prueba 29032019"));
		Assert.assertTrue(categoriaBlack.equals("on"));
		Assert.assertTrue(valorPuntos.equals("50"));
		Assert.assertTrue(bonificacion.equals("30"));
		Assert.assertTrue(publicado.equals("on"));
		Assert.assertTrue(visibleWeb.equals("on"));
		Assert.assertTrue(canjeWeb.equals("on"));
		Assert.assertTrue(legales.equals("Legales"));		
	}
	
	public void nuevoPremio() {		
		driver.findElement(By.linkText("Adm. de Premios")).click();
		driver.findElement(By.linkText("Premios")).click();
		driver.findElement(By.id("btnNuevo")).click();
	}
	
	@Test (groups = "ClubPersonalBack", priority = 21)
	public void Canje_de_Puntos_Canje_de_Credito_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
		canjePremioBack(linea,"Credito");   
    }
	
	@Test (groups = "ClubPersonalBack", priority = 22)
	public void Canje_de_Puntos_Canje_de_Credito_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
		canjePremioBack(linea,"Credito");   
    }
	
	@Test (groups = "ClubPersonalBack", priority = 23)
	public void Canje_de_Puntos_Canje_de_Pack_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
		canjePremioBack(linea,"Pack");   
    }
	
	@Test (groups = "ClubPersonalBack", priority = 24)
	public void Canje_de_Puntos_Canje_de_Pack_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
		canjePremioBack(linea,"Pack");   
    }
	
	@Test (groups = "ClubPersonalBack", priority = 9)
	public void Actualizacion_de_Datos_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
		actualizarDatosBack(linea);
    }
	
	@Test (groups = "ClubPersonalBack", priority = 10)
	public void Actualizacion_de_Datos_POS() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
		actualizarDatosBack(linea);
    }
	
	@Test (groups = "ClubPersonalBack", priority = 11)
	public void Actualizacion_de_Datos_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
		actualizarDatosBack(linea);
    }
	
	@Test (groups = "ClubPersonalBack", priority = 3)
	public void ABM_Usuarios_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
		abmUsuarios(linea);
    }
	
	@Test (groups = "ClubPersonalBack", priority = 4)
	public void ABM_Usuarios_POS() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
		abmUsuarios(linea);
    }
	
	@Test (groups = "ClubPersonalBack", priority = 5)
	public void ABM_Usuarios_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
		abmUsuarios(linea);
    }
	
	@Test (groups = "ClubPersonalBack", priority = 37)
	public void Anulacion_Canje_de_premio_Diferido_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
		anulacionPremioDiferido(linea);
    }
	
	@Test (groups = "ClubPersonalBack", priority = 38)
	public void Anulacion_Canje_de_premio_Diferido_POS() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
		anulacionPremioDiferido(linea);
    }
	
	@Test (groups = "ClubPersonalBack", priority = 39)
	public void Anulacion_Canje_de_premio_Diferido_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
		anulacionPremioDiferido(linea);
    }
	
}