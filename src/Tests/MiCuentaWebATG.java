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
	public void C02_CuentaValidarDatosDelCliente (){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		loginMiCuentaWebATG("tiagotest05@cablevision.com.ar","Prueba12");

		String titular=driver.findElement(By.xpath("//div[@class='box fill size-1']/div/h4")).getText(); 
		System.out.println(titular);
		Assert.assertTrue(titular.equals("TITULAR"));
		
		String nombre=driver.findElement(By.xpath("//div[@class='box fill size-1']/div/p[1]/span[2]")).getText(); 
		System.out.println(nombre);
		Assert.assertFalse(nombre.isEmpty());
		
		String nCliente=driver.findElement(By.xpath("//div[@class='box fill size-1']/div/p[3]/span[2]")).getText(); 
		System.out.println(nCliente);
		Assert.assertTrue(doubleValue(nCliente));
		
		String nDni=driver.findElement(By.xpath("//div[@class='box fill size-1']/div/p[4]/span[2]")).getText(); 
		System.out.println(nDni);
		Assert.assertTrue(doubleValue(nDni));
		
		String contacto=driver.findElement(By.xpath("//div[@class='box size-1']/div/h4")).getText(); 
		System.out.println(contacto);
		Assert.assertTrue(contacto.equals("CONTACTO"));
		
		String email=driver.findElement(By.xpath("//div[@class='box size-1']/div/p[1]/span[2]")).getText(); 
		System.out.println(email);
		Assert.assertFalse(email.isEmpty());
		
		String telefono=driver.findElement(By.xpath("//div[@class='box size-1']/div/p[2]/span[2]")).getText(); 
		telefono = telefono.replaceAll("\\s","");
		System.out.println(telefono);
		Assert.assertTrue(doubleValue(telefono));
		
		String domicilio=driver.findElement(By.xpath("//div[@class='box size-2']/div/h4")).getText(); 
		Assert.assertTrue(domicilio.equals("DOMICILIO DE INSTALACIÓN"));
		
		String usuario=driver.findElement(By.xpath("//*[@id=\"registerSection\"]/h4")).getText(); 
		System.out.println(usuario);
		Assert.assertTrue(usuario.equals("USUARIO DE REGISTRACIÓN"));
		
		String nombreUsuario=driver.findElement(By.xpath("//*[@id=\"actualUsername\"]")).getText(); 
		System.out.println(nombreUsuario);
		Assert.assertFalse(nombreUsuario.isEmpty());
		
		nombre=driver.findElement(By.xpath("//*[@id=\"registerSection\"]/p[2]/span[2]")).getText(); 
		System.out.println(nombre);
		Assert.assertFalse(nombre.isEmpty());
		
	}
	
}