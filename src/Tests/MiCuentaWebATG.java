package Tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import PageMetodos.MetodosMiCuentaWebATG;

public class MiCuentaWebATG extends MetodosMiCuentaWebATG{
	
	private WebDriver driver;
	
	String nombreCaso;
	String archivoUsuarios="dataInput/";
	String rutaCaptura;
	String modulo="MiCuentaWebATG";

	@BeforeClass (alwaysRun = true)
	public void beforeClass() throws IOException {
		rutaCaptura=reportDirectory(modulo);
	}
	
	@BeforeMethod (alwaysRun = true)
		public void before(){
		driver = setup();	
	}
	
	//@AfterMethod (alwaysRun = true)
	public void after(){
		tomarCaptura(driver,nombreCaso,rutaCaptura);
		try {
			logoutCatalogoATG();
			driver.close();
		}catch(Exception ex1){	driver.close();
		}
	}
	
	
	@Test (groups = "DummyGroup", priority = 0)
	public void C01_DummyTest (){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		loginMiCuentaWebATG();
		
	}
	
}