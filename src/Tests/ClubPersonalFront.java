package Tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import PageMetodos.Metodos;

public class ClubPersonalFront extends Metodos{
	
	private WebDriver driver;
	
	String nombreCaso;
	String archivoLineas="LineasClubPersonalFront.xlsx";
	
	@BeforeMethod (alwaysRun = true)
		public void before(){
		driver = setup();	
	}
	
	//@AfterMethod (alwaysRun = true)
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
	
	
	@Test 
	public void Adhesion_Titular_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubFront(linea);
        AdhesionTitularClub("testtest867@gmail.com");		
	}
	
	@Test 
	public void Adhesion_Titular_POS() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubFront(linea);
        AdhesionTitularClub("testtest868@gmail.com");		
	}
	
	@Test 
	public void Adhesion_Titular_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubFront(linea);
        AdhesionTitularClub("testtest687@gmail.com");		
	}
	
	@Test 
	public void Canje_de_Premio_Diferido_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubFront(linea);
        canjePremio("Diferido");
	}
	
	@Test 
	public void Canje_de_Premio_Diferido_POS() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubFront(linea);
        canjePremio("Diferido");
	}
	
	@Test 
	public void Canje_de_Premio_Diferido_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubFront(linea);
        canjePremio("Diferido");
	}
	
	@Test 
	public void Canje_de_Puntos_Canje_de_Voucher_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubFront(linea);
        canjePremio("Voucher");
	}
	
	@Test 
	public void Canje_de_Puntos_Canje_de_Voucher_POS() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubFront(linea);
        canjePremio("Voucher");
	}
	
	@Test 
	public void Canje_de_Puntos_Canje_de_Voucher_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubFront(linea);
        canjePremio("Voucher");
	}
	
	@Test 
	public void Actualizacion_de_Datos_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubFront(linea);
		actualizarDatos();
	}
	
	@Test 
	public void Actualizaci�o_de_Datos_POS() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubFront(linea);
		actualizarDatos();
	}
	
	@Test 
	public void Actualizacion_de_Datos_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		String linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubFront(linea);
		actualizarDatos();
	}
}