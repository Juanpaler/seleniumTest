package Tests;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



import PageMetodos.MetodosCatalogoATG;

public class CatalogoATG extends MetodosCatalogoATG{
	
	private WebDriver driver;
	
	String nombreCaso;
	String archivoLineas="dataInput/";
	String rutaCaptura;
	String modulo="CatalogoATG";

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
		driver.close();
		try {

		}catch(Exception ex1){	driver.close();
		}
	}
	
	
	@Test (groups = "CatalogoATG", priority = 0)
	public void Test (){
		loginCatalogoATG();
	}
	

}