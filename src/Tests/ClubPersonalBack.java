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
}