package Tests;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
	
}