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
	
	@Test (groups = "DummyGroup", priority = 0)
	public void C05_InformacionDeFacturacion (){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		loginMiCuentaWebATG("tiagotest02@cablevision.com.ar","Prueba12");
		
		driver.findElement(By.xpath("/html/body/main/div[5]/div[1]/ul/li[2]/a")).click();
		driver.findElement(By.xpath("//div[@class='saldo']/span[4]")).click();
		
		
		driver.findElement(By.xpath("//p[@class='med-pago']/a[@href='/clientes/factura/debito-automatico']")).click();

		Assert.assertTrue(ElementCreatedUni(driver, "id", "cbuSlider", 10));
		driver.findElement(By.id("cbuSlider")).click();
		sleep(1500);
		Assert.assertTrue(ElementCreatedUni(driver, "id", "creditoSlider", 10));	
		driver.findElement(By.id("creditoSlider")).click();
		sleep(1500);

		driver.navigate().back();
		
		Assert.assertTrue(ElementCreatedUni(driver, "xpath", "//p[@class='med-pago']/a[@href='https://pagomiscuentas.com/']", 10));			
		
		driver.findElement(By.xpath("//a[@href='/MediosDePago']")).click();
		sleep(500);

		driver.findElement(By.xpath("/html/body/main/main/div[2]/div[2]/div/ul/li[2]/a")).click();
		String pagoEnEfectivo = driver.findElement(By.xpath("/html/body/main/main/div[2]/div[2]/div/div/div[2]/h3[1]")).getText();
		Assert.assertTrue(pagoEnEfectivo.contains("Pago en efectivo"));
		sleep(500);

		driver.findElement(By.xpath("/html/body/main/main/div[2]/div[2]/div/ul/li[3]/a")).click();
		String pagoPorInternet = driver.findElement(By.xpath("/html/body/main/main/div[2]/div[2]/div/div/div[3]/h3[1]")).getText();
		Assert.assertTrue(pagoPorInternet.contains("Pago por internet"));
		sleep(500);

		driver.findElement(By.xpath("/html/body/main/main/div[2]/div[2]/div/ul/li[4]/a")).click();
		String pagoTelefonico = driver.findElement(By.xpath("/html/body/main/main/div[2]/div[2]/div/div/div[4]/h3")).getText();
		Assert.assertTrue(pagoTelefonico.contains("Pago telefónico"));
		
	}
	
}
}
