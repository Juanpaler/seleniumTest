package Tests;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import PageMetodos.Metodos;

public class ClubPersonalFront extends Metodos{
	
	private WebDriver driver;
	
	String nombreCaso;
	String archivoLineas="dataInput/LineasClubPersonalFront.xlsx";
	
	@BeforeMethod (alwaysRun = true)
		public void before(){
		driver = setup();	
	}
	
	@AfterMethod (alwaysRun = true)
	public void after(){
		tomarCaptura(driver,nombreCaso);
		try {
			driver.findElement(By.id("tpi-user")).click();
			sleep(7000);
			driver.findElement(By.id("tpi-form-logoff")).click();
			sleep(7000);
			driver.close();
		}catch(Exception ex1){	driver.close();
		}
	}
	
	
	@Test (groups = "ClubPersonalFront", priority = 0)
	public void Adhesion_Titular_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubFront(linea);
        AdhesionTitularClub("testtest867@gmail.com");		
	}
	
	@Test (groups = "ClubPersonalFront", priority = 1)
	public void Adhesion_Titular_POS() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubFront(linea);
        AdhesionTitularClub("testtest868@gmail.com");		
	}
	
	@Test (groups = "ClubPersonalFront", priority = 2)
	public void Adhesion_Titular_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubFront(linea);
        AdhesionTitularClub("testtest687@gmail.com");		
	}
	
	@Test (groups = "ClubPersonalFront", priority = 3, enabled = false)
	public void Adhesion_Ususario_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubFront(linea);
        //AdhesionTitularClub("testtest687@gmail.com");		
	}
	
	@Test (groups = "ClubPersonalFront", priority = 4, enabled = false)
	public void Adhesion_Ususario_POS() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubFront(linea);
        //AdhesionTitularClub("testtest687@gmail.com");		
	}
	
	@Test (groups = "ClubPersonalFront", priority = 5 ,enabled = false)
	public void Adhesion_Ususario_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubFront(linea);
        //AdhesionTitularClub("testtest687@gmail.com");		
	}
	
	@Test (groups = "ClubPersonalFront", priority = 6)
	public void Actualizacion_de_Datos_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubFront(linea);
		actualizarDatos();
	}
	
	@Test (groups = "ClubPersonalFront", priority = 7)
	public void Actualizacion_de_Datos_POS() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubFront(linea);
		actualizarDatos();
	}
	
	@Test (groups = "ClubPersonalFront", priority = 8)
	public void Actualizacion_de_Datos_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubFront(linea);
		actualizarDatos();
	}
	
	@Test (groups = "ClubPersonalFront", priority = 9)
	public void Canje_de_Premio_Diferido_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubFront(linea);
        canjePremio("Diferido");
	}
	
	@Test (groups = "ClubPersonalFront", priority = 10)
	public void Canje_de_Premio_Diferido_POS() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubFront(linea);
        canjePremio("Diferido");
	}
	
	@Test (groups = "ClubPersonalFront", priority = 11)
	public void Canje_de_Premio_Diferido_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubFront(linea);
        canjePremio("Diferido");
	}
	
	@Test (groups = "ClubPersonalFront", priority = 12)
	public void Canje_de_Puntos_Canje_de_Voucher_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubFront(linea);
        canjePremio("Voucher");
	}
	
	@Test (groups = "ClubPersonalFront", priority = 13)
	public void Canje_de_Puntos_Canje_de_Voucher_POS() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubFront(linea);
        canjePremio("Voucher");
	}
	
	@Test (groups = "ClubPersonalFront", priority = 14)
	public void Canje_de_Puntos_Canje_de_Voucher_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubFront(linea);
        canjePremio("Voucher");
	}
	
	@Test (groups = "ClubPersonalFront", priority = 15)
	public void Canje_de_Puntos_Canje_de_Credito_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubFront(linea);
        canjePremio("Credito");
	}
	
	@Test (groups = "ClubPersonalFront", priority = 16)
	public void Canje_de_Puntos_Canje_de_Credito_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubFront(linea);
        canjePremio("Credito");
	}
	
	@Test (groups = "ClubPersonalFront", priority = 17)
	public void Canje_de_Puntos_Canje_de_Pack_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubFront(linea);
        canjePremio("Pack");
	}
	
	@Test (groups = "ClubPersonalFront", priority = 18)
	public void Canje_de_Puntos_Canje_de_Pack_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubFront(linea);
        canjePremio("Pack");
	}
	
	private Boolean Validar_Resumen_de_Puntos()
	{
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-lg.btn-default.pull-right.ng-scope")),"contains","Mi Club");
		buscarYClick(driver.findElements(By.cssSelector(".ng-binding")),"contains","Resumen de Puntos");		
		
		int cantFilas = driver.findElements(By.xpath("//table[@class='table table-condensed ng-scope']/tbody/tr")).size();
		String totalPuntosString = driver.findElement(By.cssSelector(".text-brand-cyan.margin-top-0.text-right.ng-binding")).getText();
		Double totalPuntos = Double.parseDouble(totalPuntosString);
		Double sumaPuntos = 0.0;
		
		for(int i=1; i<= cantFilas; i++)
		{
			String puntos = driver.findElement(By.xpath("//table[@class='table table-condensed ng-scope']/tbody/tr["+i+"]/td[4]")).getText();
			puntos = puntos.replace(" pts", "");
			sumaPuntos = sumaPuntos + Double.parseDouble(puntos);
		}
		
		return (totalPuntos.equals(sumaPuntos));
	}
	
	@Test (groups = "ClubPersonalFront", priority = 19)
	public void Resumen_de_Puntos_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubFront(linea);
		
		Assert.assertTrue(Validar_Resumen_de_Puntos());
	}
	
	@Test (groups = "ClubPersonalFront", priority = 20)
	public void Resumen_de_Puntos_POS() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubFront(linea);
		
		Assert.assertTrue(Validar_Resumen_de_Puntos());
	}
	
	@Test (groups = "ClubPersonalFront", priority = 21)
	public void Resumen_de_Puntos_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubFront(linea);
		
		Assert.assertTrue(Validar_Resumen_de_Puntos());
	}
	
	@Test (groups = "ClubPersonalFront", priority = 22)
	public void Canjes_Realizados_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubFront(linea);
        canjesRealizados();
	}
	
	@Test (groups = "ClubPersonalFront", priority = 23)
	public void Canjes_Realizados_POS() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubFront(linea);
        canjesRealizados();
	}
	
	@Test (groups = "ClubPersonalFront", priority = 24)
	public void Canjes_Realizados_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubFront(linea);
        canjesRealizados();
	}
	
	@Test (groups = "ClubPersonalFront", priority = 25)
	public void Movimientos_de_Puntos_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubFront(linea);
        movimientosPuntos();
	}
	
	@Test (groups = "ClubPersonalFront", priority = 26)
	public void Movimientos_de_Puntos_POS() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubFront(linea);
        movimientosPuntos();
	}
	
	@Test (groups = "ClubPersonalFront", priority = 27)
	public void Movimientos_de_Puntos_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubFront(linea);
        movimientosPuntos();
	}
}