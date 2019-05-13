package Tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.AssertJUnit;
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
		
		String direccion=driver.findElement(By.xpath("//div[@class='box size-2']/div/p[1]/span[2]")).getText(); 
		System.out.println(direccion);
		Assert.assertFalse(direccion.isEmpty());
		
		String localidad=driver.findElement(By.xpath("//div[@class='box size-2']/div/p[2]/span[2]")).getText(); 
		System.out.println(localidad);
		Assert.assertFalse(localidad.isEmpty());
		
		String provincia=driver.findElement(By.xpath("//div[@class='box size-2']/div/p[3]/span[2]")).getText(); 
		System.out.println(provincia);
		Assert.assertFalse(provincia.isEmpty());

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
	

	
	@Test (groups = "DummyGroup", priority = 0)
	public void C06_MediosDePago (){
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
	
	@Test (groups = "DummyGroup", priority = 0)
	public void C07_CuentaGestionarMudanza (){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		loginMiCuentaWebATG("tiagotest05@cablevision.com.ar","Prueba12");
		buscarYClick(driver.findElements(By.cssSelector(".cv-btn")),"equals","gestionar mudanza");
		new Select(driver.findElement(By.id("documentType"))).selectByVisibleText("DNI Masculino");
		driver.findElement(By.id("documentNumber")).sendKeys("34270300");
		sleep(2000);
		driver.findElement(By.id("nameAndSurname")).clear();
		driver.findElement(By.id("nameAndSurname")).sendKeys("Juan Perez");
		new Select(driver.findElement(By.id("phoneType"))).selectByVisibleText("Celular");
		driver.findElement(By.id("areaCode")).clear();
		driver.findElement(By.id("areaCode")).sendKeys("011");
		driver.findElement(By.id("phoneNumber")).clear();
		driver.findElement(By.id("phoneNumber")).sendKeys("32643788");
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("juanperze23@gmail.com");
		buscarYClick(driver.findElements(By.cssSelector(".cv-btn-default.primary.pull-right")),"equals","Solicitar");
		sleep(2000);
		String mensaje = driver.findElement(By.xpath("//*[@id=\"modal-mudanza\"]/header/h2")).getText();
		Assert.assertTrue(mensaje.contains("¡Muchas Gracias!"));
		driver.findElement(By.xpath("//*[@id=\"modal-mudanza\"]/header/button")).click();
	}
	
	@Test (groups = "DummyGroup", priority = 0)
	public void C08_CuentaModificarUsuario (){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		loginMiCuentaWebATG("tiagotest05@cablevision.com.ar","Prueba12");
		//Modificacion Usuario
		driver.findElement(By.id("modifyUserModalBtn")).click();
		sleep(2000);
		String usuarioActual = driver.findElement(By.id("modalUsername")).getText();
		driver.findElement(By.id("newUserLogin")).sendKeys("automationtest@cablevision.com.ar");
		driver.findElement(By.id("modifyUser")).click();
		sleep(2000);
		String mensaje = driver.findElement(By.xpath("//*[@id=\"mod-user\"]/div[2]/div/div/div[2]/div[2]/p")).getText();
		Assert.assertTrue(mensaje.contains("Los cambios fueron guardados exitosamente."));
		buscarYClick(driver.findElements(By.cssSelector(".cv-btn.close")),"equals","volver a mi cuenta");
		sleep(2000);
		driver.findElement(By.id("modifyUserModalBtn")).click();
		sleep(2000);
		String usuarioNuevo = driver.findElement(By.id("modalUsername")).getText();
		Assert.assertTrue(usuarioNuevo.contains("automationtest@cablevision.com.ar"));
		driver.findElement(By.id("newUserLogin")).sendKeys("tiagotest05@cablevision.com.ar");
		driver.findElement(By.id("modifyUser")).click();
		sleep(2000);
		mensaje = driver.findElement(By.xpath("//*[@id=\"mod-user\"]/div[2]/div/div/div[2]/div[2]/p")).getText();
		Assert.assertTrue(mensaje.contains("Los cambios fueron guardados exitosamente."));
		buscarYClick(driver.findElements(By.cssSelector(".cv-btn.close")),"equals","volver a mi cuenta");
		sleep(2000);
		usuarioNuevo=driver.findElement(By.xpath("//*[@id=\"actualUsername\"]")).getText(); 
		Assert.assertTrue(usuarioActual.contains(usuarioNuevo));
		
		//Modificacion Contraseña
		driver.findElement(By.id("btnPasswordChange")).click();
		sleep(2000);
		driver.findElement(By.id("user_password")).sendKeys("Prueba12");
		driver.findElement(By.id("new_password")).sendKeys("Prueba13");
		driver.findElement(By.id("donfirm_password")).sendKeys("Prueba13");
		driver.findElement(By.id("modifyPasswordButton")).click();
		sleep(5000);
		mensaje = driver.findElement(By.xpath("//*[@id=\"_123\"]/div[2]/div/div/div/div[2]/div/p")).getText();
		Assert.assertTrue(mensaje.contains("Modificaste con éxito la contraseña de tu cuenta"));
		buscarYClick(driver.findElements(By.cssSelector(".cv-btn.close")),"equals","volver a mi cuenta");
		sleep(2000);
		driver.findElement(By.id("btnPasswordChange")).click();
		sleep(2000);
		driver.findElement(By.id("user_password")).sendKeys("Prueba13");
		driver.findElement(By.id("new_password")).sendKeys("Prueba12");
		driver.findElement(By.id("donfirm_password")).sendKeys("Prueba12");
		driver.findElement(By.id("modifyPasswordButton")).click();
		sleep(5000);
		mensaje = driver.findElement(By.xpath("//*[@id=\"_123\"]/div[2]/div/div/div/div[2]/div/p")).getText();
		Assert.assertTrue(mensaje.contains("Modificaste con éxito la contraseña de tu cuenta"));
		buscarYClick(driver.findElements(By.cssSelector(".cv-btn.close")),"equals","volver a mi cuenta");
		sleep(2000);
	}
	
	@Test (groups = "DummyGroup", priority = 0)
	public void C09_CuentaPrivacidad (){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		loginMiCuentaWebATG("tiagotest05@cablevision.com.ar","Prueba12");
		driver.findElement(By.xpath("//div[@class='configuration']/a")).click();
		driver.findElement(By.id("submitAjaxHandler")).click();
		sleep(2000);
		String mensaje = driver.findElement(By.xpath("//div[@class='modal-body sml cen']/p[1]")).getText();
		Assert.assertTrue(mensaje.contains("Los cambios fueron guardados exitosamente."));
		buscarYClick(driver.findElements(By.cssSelector(".cv-btn.close")),"equals","volver a mi cuenta");
		sleep(2000);
		
	}
	
	@Test (groups = "DummyGroup", priority = 0)
	public void C10_ServiciosServiciosContratados (){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		loginMiCuentaWebATG("pablotest157@cablevision.com.ar","Prueba12");
		ClickElementoVisible(driver,"xpath", "//div[@class='side-menu']/ul/li[4]/a");

		String television = driver.findElement(By.cssSelector(".col.tv.first")).getText();
		String internet = driver.findElement(By.cssSelector(".col.it.sec")).getText();
		String telefonia = driver.findElement(By.cssSelector(".col.te.last")).getText();

		Assert.assertTrue(television.contains("TELEVISION"));
		Assert.assertTrue(internet.contains("INTERNET"));
		Assert.assertTrue(telefonia.contains("TELEFONÍA"));
		
	}
	
	@Test (groups = "DummyGroup", priority = 0)
	public void C11_ServiciostelevisionFlow (){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		loginMiCuentaWebATG("pablotest157@cablevision.com.ar","Prueba12");
		driver.findElement(By.xpath("/html/body/main/div[5]/div[1]/ul/li[4]/a")).click();

		driver.findElement(By.cssSelector(".adm")).click();
		
		ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs2.get(1));
	    WaitForElement(driver, "id", "usernameNew");
	    sleep(2500);
	    driver.close();
	    driver.switchTo().window(tabs2.get(0));		
	}
	
	@Test (groups = "DummyGroup", priority = 0)
	public void C12_ServiciosTelevisionEstadoDelServicio (){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		loginMiCuentaWebATG("pablotest157@cablevision.com.ar","Prueba12");
		driver.findElement(By.xpath("/html/body/main/div[5]/div[1]/ul/li[4]/a")).click();
		
		buscarYClick(driver.findElements(By.cssSelector(".est.catvServiceStatus")), "contains", "estado de servicios");
		String estadoServicio = driver.findElement(By.cssSelector(".lg.cen")).getText();
		
		
	    Assert.assertTrue(estadoServicio.contains("ESTADO DE SERVICIO"));
	
	}
	
	@Test (groups = "DummyGroup", priority = 0)
	public void C13_ServiciosInternetEstadoDelServicio (){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		loginMiCuentaWebATG("pablotest157@cablevision.com.ar","Prueba12");
		driver.findElement(By.xpath("/html/body/main/div[5]/div[1]/ul/li[4]/a")).click();
		
		buscarYClick(driver.findElements(By.cssSelector(".esi.internetServiceStatus")), "contains", "estado de servicios");
		String estadoServicio = driver.findElement(By.cssSelector(".lg.cen")).getText();
		
		
	    Assert.assertTrue(estadoServicio.contains("ESTADO DE SERVICIO"));
	
	}
	
	@Test (groups = "DummyGroup", priority = 0)
	public void C23_CentroDeAyudaValidarSitio (){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		loginMiCuentaWebATG("tiagotest01@cablevision.com.ar","Prueba12");
		ClickElementoVisible(driver,"xpath", "//div[@class='side-menu']/ul/li[6]/a");
	    ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		sleep(2000);
		Assert.assertTrue(false);
		//Falta validacion por que no carga nada la pagina de ayuda
		driver.close();
		driver.switchTo().window(tabs.get(0));
	}
	
	@Test (groups = "DummyGroup", priority = 0)
	public void C24_clubPersonalValidarSitio (){
		nombreCaso = new Object(){}.getClass().getEnclosingMethod().getName();
		loginMiCuentaWebATG("tiagotest01@cablevision.com.ar","Prueba12");
		ClickElementoVisible(driver,"xpath", "//div[@class='side-menu']/ul/li[7]/a");
	    ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		sleep(30000);
		String texto=driver.findElement(By.cssSelector(".text-center.title.c-violet")).getText();
		Assert.assertTrue(texto.equals("¿Qué es Club Personal ?"));
		driver.close();
		driver.switchTo().window(tabs.get(0));
	}
}

