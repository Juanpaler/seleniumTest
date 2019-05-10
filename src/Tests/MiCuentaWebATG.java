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
			logoutMiCuentaWebATG();
			driver.close();
		}catch(Exception ex1){	driver.close();
		}
	}
	
	
	@Test (groups = "DummyGroup", priority = 0)
	public void C01_LoginEnLaWeb (){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		loginMiCuentaWebATG("tiagotest01@cablevision.com.ar","Prueba12");
		Assert.assertTrue(ElementCreatedUni(driver, "cssSelector", ".name",20));
	}
	
	
	@Test (groups = "DummyGroup", priority = 0)
	public void C03_InformacionDeFacturacion (){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		loginMiCuentaWebATG("tiagotest02@cablevision.com.ar","Prueba12");
		
		driver.findElement(By.xpath("/html/body/main/div[5]/div[1]/ul/li[2]/a")).click();
		driver.findElement(By.xpath("//div[@class='saldo']/span[4]")).click();
		String saldo = driver.findElement(By.xpath("//div[@class='saldo']/span[@class='pla']")).getText();
		String nroRefPago = driver.findElement(By.xpath("//div[@class='saldo']/span[4]")).getText();
		
		saldo = saldo.replace(",","");
		nroRefPago = nroRefPago.replace("No. de Referencia de Pago: ", "");
		
		Assert.assertTrue(intValue(saldo));
		Assert.assertTrue(intValue(nroRefPago));		
		
	}
	
}