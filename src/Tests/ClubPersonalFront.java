package Tests;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
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
		int linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubFront(String.valueOf(linea));
        AdhesionTitularClub("testtest867@gmail.com");		
	}
	
	@Test 
	public void Adhesion_Titular_POS() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		int linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubFront(String.valueOf(linea));
        AdhesionTitularClub("testtest868@gmail.com");		
	}
	
	@Test 
	public void Adhesion_Titular_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		int linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubFront(String.valueOf(linea));
        AdhesionTitularClub("testtest687@gmail.com");		
	}
	
	@Test 
	public void Canje_de_Premio_Diferido_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		int linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubFront(String.valueOf(linea));
        canjePremio("Diferido");
	}
	
	@Test 
	public void Canje_de_Premio_Diferido_POS() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		int linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubFront(String.valueOf(linea));
        canjePremio("Diferido");
	}
	
	@Test 
	public void Canje_de_Premio_Diferido_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		int linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubFront(String.valueOf(linea));
        canjePremio("Diferido");
	}
	
	@Test 
	public void Canje_de_Puntos_Canje_de_Voucher_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		int linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubFront(String.valueOf(linea));
        canjePremio("Voucher");
	}
	
	@Test 
	public void Canje_de_Puntos_Canje_de_Voucher_POS() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		int linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubFront(String.valueOf(linea));
        canjePremio("Voucher");
	}
	
	@Test 
	public void Canje_de_Puntos_Canje_de_Voucher_PRE() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		int linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubFront(String.valueOf(linea));
        canjePremio("Voucher");
	}
	
	@Test 
	public void Resumen_de_Puntos_MIX() throws IOException{
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		int linea=retornaLinea(nombreCaso,archivoLineas);
		loginClubFront(String.valueOf(linea));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-lg.btn-default.pull-right.ng-scope")),"contains","Mi Club");
		buscarYClick(driver.findElements(By.cssSelector(".ng-binding")),"contains","Resumen de Puntos");
		
		//WebElement table = driver.findElement(By.tagName("table"));
	}
	
}