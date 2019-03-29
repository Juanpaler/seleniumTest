package Tests;


import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import PageMetodos.Metodos;

public class ClubPersonalBack extends Metodos{
	
	private WebDriver driver;
	
	String nombreCaso;
	String archivoLineas="LineasClubPersonalBack.xlsx";
	
	@BeforeMethod (alwaysRun = true)
		public void before(){
		driver = setup();	
	}
	
	//@AfterMethod (alwaysRun = true)
	public void after(){
		tomarCaptura(driver,nombreCaso);
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
	
	@Test (groups = "ClubPersonalBack", priority = 0)
	public void Adhesion_Usuario_Titular_POS() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
		AdhesionTitularClubBack(linea,"testtest868");
    }
	
	@Test (groups = "ClubPersonalBack", priority = 0)
	public void Adhesion_Usuario_Titular_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
		AdhesionTitularClubBack(linea,"testtest687");
    }
	
	@Test (groups = "ClubPersonalBack", priority = 0)
	public void Busqueda_por_Linea_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
        busquedaLinea(linea);
    }
	
	@Test (groups = "ClubPersonalBack", priority = 0)
	public void Busqueda_por_Linea_POS() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
        busquedaLinea(linea);
    }
	
	@Test (groups = "ClubPersonalBack", priority = 0)
	public void Busqueda_por_Linea_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
        busquedaLinea(linea);
    }
	
	@Test (groups = "ClubPersonalBack", priority = 0)
	public void Canje_de_Premio_Diferido_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
		canjePremioBack(linea,"Diferido");   
    }
	
	@Test (groups = "ClubPersonalBack", priority = 0)
	public void Canje_de_Premio_Diferido_POS() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
		canjePremioBack(linea,"Diferido");
    }
	
	@Test (groups = "ClubPersonalBack", priority = 0)
	public void Canje_de_Premio_Diferido_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
		canjePremioBack(linea,"Diferido");
    }
	
	@Test (groups = "ClubPersonalBack", priority = 0)
	public void Canje_de_Puntos_Canje_de_Voucher_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
		canjePremioBack(linea,"Voucher");   
    }
	
	@Test (groups = "ClubPersonalBack", priority = 0)
	public void Canje_de_Puntos_Canje_de_Voucher_POS() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
		canjePremioBack(linea,"Voucher");   
    }
	
	@Test (groups = "ClubPersonalBack", priority = 0)
	public void Canje_de_Puntos_Canje_de_Voucher_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
		canjePremioBack(linea,"Voucher");   
    }
	
	@Test (groups = "ClubPersonalBack", priority = 0)
	public void Busqueda_por_Documento_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
        busquedaPorDni("38092272", linea);
    }
	
	@Test (groups = "ClubPersonalBack", priority = 0)
	public void Busqueda_por_Documento_POS() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
        busquedaPorDni("21976636", linea);
    }
	
	@Test (groups = "ClubPersonalBack", priority = 0)
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
	
	@Test (groups = "ClubPersonalBack", priority = 0)
	public void Movimientos_de_Puntos_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		loginClubBack();
		Busqueda_por_Linea(linea);
        movimientosPuntosBack();
	}
	
	@Test (groups = "ClubPersonalBack", priority = 0)
	public void Movimientos_de_Puntos_POS() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		loginClubBack();
		Busqueda_por_Linea(linea);
        movimientosPuntosBack();
	}
	
	@Test (groups = "ClubPersonalBack", priority = 0)
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
		driver.findElement(By.linkText("B\u00FAsqueda por L\u00EDnea")).click();
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
		System.out.println(cantFilas);

		String totalPuntosString = 	driver.findElement(By.xpath("//table[@id='panelResumen']/tbody/tr[3]/td[2]")).getText();
		System.out.println(totalPuntosString);

		Double totalPuntos = Double.parseDouble(totalPuntosString);
		Double sumaPuntos = 0.0;
		
		for(int i=1; i<= cantFilas; i++)
		{
			String puntos = driver.findElement(By.xpath("//table[@class='tablaDatos'][@align='center']/tbody["+i+"]/tr[1]/td[8]")).getText();
			System.out.println(puntos);

			sumaPuntos = sumaPuntos + Double.parseDouble(puntos);
		}
		System.out.println(sumaPuntos);
		System.out.println(totalPuntos);
		return (totalPuntos.equals(sumaPuntos));
	}
	
	@Test (groups = "ClubPersonalBack", priority = 0)
	public void Resumen_de_Puntos_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
		Busqueda_por_Linea(linea);
        Assert.assertTrue(Validar_Resumen_de_Puntos());
    }
	
	@Test (groups = "ClubPersonalBack", priority = 0)
	public void Resumen_de_Puntos_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
		Busqueda_por_Linea(linea);
        Assert.assertTrue(Validar_Resumen_de_Puntos());
    }
	
	@Test (groups = "ClubPersonalBack", priority = 0)
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
	
	@Test (groups = "ClubPersonalBack", priority = 0)
	public void Canjes_Realizados_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		loginClubBack();
		Busqueda_por_Linea(linea);
		canjesRealizadosBack();
	}
	
	@Test (groups = "ClubPersonalBack", priority = 0)
	public void Canjes_Realizados_POS() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		loginClubBack();
		Busqueda_por_Linea(linea);
		canjesRealizadosBack();
	}
	
	@Test (groups = "ClubPersonalBack", priority = 0)
	public void Canjes_Realizados_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		loginClubBack();
		Busqueda_por_Linea(linea);
		canjesRealizadosBack();
	}
	
	@Test (groups = "ClubPersonalBack", priority = 0)
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
		driver.findElement(By.id("idFile")).sendKeys(path +"\\ClubPersonalxDefecto.jpeg");
		
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
	
	
	@Test (groups = "ClubPersonalBack", priority = 0)
	public void Canje_de_Puntos_Canje_de_Credito_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
		canjePremioBack(linea,"Credito");   
    }
	
	@Test (groups = "ClubPersonalBack", priority = 0)
	public void Canje_de_Puntos_Canje_de_Credito_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
		canjePremioBack(linea,"Credito");   
    }
	
	@Test (groups = "ClubPersonalBack", priority = 0)
	public void Canje_de_Puntos_Canje_de_Pack_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
		canjePremioBack(linea,"Pack");   
    }
	
	@Test (groups = "ClubPersonalBack", priority = 0)
	public void Canje_de_Puntos_Canje_de_Pack_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
		canjePremioBack(linea,"Pack");   
    }
	
	@Test (groups = "ClubPersonalBack", priority = 0)
	public void Actualizacion_de_Datos_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
		actualizarDatosBack(linea);
    }
	
	@Test (groups = "ClubPersonalBack", priority = 0)
	public void Actualizacion_de_Datos_POS() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
		actualizarDatosBack(linea);
    }
	
	@Test (groups = "ClubPersonalBack", priority = 0)
	public void Actualizacion_de_Datos_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
		actualizarDatosBack(linea);
    }
	
	@Test (groups = "ClubPersonalBack", priority = 0)
	public void ABM_Usuarios_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
		abmUsuarios(linea);
    }
	
	@Test (groups = "ClubPersonalBack", priority = 0)
	public void ABM_Usuarios_POS() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
		abmUsuarios(linea);
    }
	
	@Test (groups = "ClubPersonalBack", priority = 0)
	public void ABM_Usuarios_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
		abmUsuarios(linea);
    }
	
	@Test (groups = "ClubPersonalBack", priority = 0)
	public void Anulacion_Canje_de_premio_Diferido_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
		anulacionPremioDiferido(linea);
    }
	
	@Test (groups = "ClubPersonalBack", priority = 0)
	public void Anulacion_Canje_de_premio_Diferido_POS() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
		anulacionPremioDiferido(linea);
    }
	
	@Test (groups = "ClubPersonalBack", priority = 0)
	public void Anulacion_Canje_de_premio_Diferido_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubBack();	
		anulacionPremioDiferido(linea);
    }
	
}