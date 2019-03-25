package PageMetodos;

import static org.testng.Assert.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

import DataProvider.ExcelUtils;

import java.io.FileInputStream;

import java.io.FileNotFoundException;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Metodos {
	
    private static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static XSSFCell Cell;
	
	static WebDriver driver;
	static WebDriver driverf;
	static WebDriver driveri;
	
	public static String lineaMIX = "1162735148";
	public static String lineaPre = "1162745165";
	public static String lineaPos = "1145642605";
	
	public Properties config = new Properties();
    InputStream configInput = null;
	

    public void loadConfig(){
        try{
            configInput = new FileInputStream("appConfig.properties");
            config.load(configInput);
            //Las configuraciones se consumen as�:
            //String excelLineasEcommerce = config.getProperty("ExcelLineasEcommerce");
           
        } catch(Exception e){
        	System.out.println("Error cargando configuraci�n\n" + e.getMessage());
        }
    }
    
	public static WebDriver setup(){
		System.setProperty("webdriver.chrome.driver", "Chromedriver.exe");
		ChromeOptions Options = new ChromeOptions();
		Options.addArguments("start-maximized");
		Options.addArguments("disable-infobars");
		driver = new ChromeDriver(Options);
		return driver;
	}
		
	public static WebDriver setup2(){
		System.setProperty("webdriver.geckod.driver", "geckodriver.exe");
		FirefoxOptions Options = new FirefoxOptions();
		driverf = new FirefoxDriver(Options);
		return driverf;
	}
	
		public static WebDriver setup3(){
		System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, false);
		driveri = new InternetExplorerDriver(capabilities);
		return driveri;
		
	}
		
	public static void sleep(int miliseconds) {
		try {Thread.sleep(miliseconds);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	}
	
	public void loginPorLinea(String sLinea) {
		driver.get("https://autogestionuat.personal.com.ar");
		
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.id("idToken1")));		
		driver.findElement(By.id("idToken1")).sendKeys(sLinea);		
		driver.findElement(By.id("idToken2")).clear();
		driver.findElement(By.id("idToken2")).sendKeys("1469");
		driver.findElement(By.id("loginButton_0")).click();
		sleep(20000);
	}
	
	public void loginPorLineaF(String sLinea) {
		driverf.get("https://autogestionuat.personal.com.ar");		
		sleep(10000);
		driverf.switchTo().frame(cambioFrame(driverf, By.id("idToken1")));	
		driverf.findElement(By.id("idToken1")).sendKeys(sLinea);		
		driverf.findElement(By.id("idToken2")).clear();
		driverf.findElement(By.id("idToken2")).sendKeys("1469");
		driverf.findElement(By.id("loginButton_0")).click();
		sleep(20000);
	}
	
	public void loginPorLineaI(String sLinea) {
		driveri.get("https://autogestionuat.personal.com.ar");		
		sleep(10000);
		driveri.switchTo().frame(cambioFrame(driveri, By.id("idToken1")));		
		driveri.findElement(By.id("idToken1")).sendKeys(sLinea);		
		driveri.findElement(By.id("idToken2")).clear();
		driveri.findElement(By.id("idToken2")).sendKeys("1469");
		driveri.findElement(By.id("loginButton_0")).click();		
		sleep(20000);
	}
	
	public void LoginPorLineaVariable(String sLinea){
		driver.get("https://autogestionuat.personal.com.ar");		
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.id("idToken1")));		
		driver.findElement(By.id("idToken1")).sendKeys(sLinea);		
		driver.findElement(By.id("idToken2")).clear();
		driver.findElement(By.id("idToken2")).sendKeys("1469");
		driver.findElement(By.id("loginButton_0")).click();
		sleep(20000);
	}
	
	public void buscarYClick(List<WebElement> elements, String match, String texto) {
		sleep(2000);
		switch (match) {
		case "contains":
			for (WebElement x : elements) {
				if (x.getText().toLowerCase().contains(texto.toLowerCase())) {
					x.click();
					break;
				}
			}
			break;
		case "equals":
			for (WebElement x : elements) {
				if (x.getText().toLowerCase().equals(texto.toLowerCase())) {
					x.click();
					break;
				}
			}
			break;
		}
		sleep(5000);
	}
	
	public void selectByText(WebElement element, String data){
		Select select = new Select(element);
		select.selectByVisibleText(data);
		sleep(2000);
	}
	
	public void irA(String sMenu) {
		sleep(3000);
		List<WebElement> wMenu = driver.findElement(By.id("ul_menusolapas")).findElements(By.className("dev-solapa"));
		for (WebElement wAux : wMenu) {
			if (wAux.getText().toLowerCase().contains(sMenu.toLowerCase())) {
				wAux.click();
				break;
			}
		}
		sleep(5000);
	}
	
	public int getIndexFrame(WebDriver driver, By byForElement) { 
		int index = 0;
		driver.switchTo().defaultContent();
		List<WebElement> frames = driver.findElements(By.tagName("iframe"));
		for(WebElement frame : frames) {
			try {
				driver.switchTo().frame(frame);
				driver.findElement(byForElement).getText(); 
				driver.findElement(byForElement).isDisplayed(); 
				driver.switchTo().defaultContent();
				return index;
			} catch(NoSuchElementException noSuchElemExcept) {
				index++;
				driver.switchTo().defaultContent();
			}
		}
		return -1; 
	}
	
	public WebElement cambioFrame(WebDriver driver, By byForElement) {
		driver.switchTo().defaultContent();
		List<WebElement> frames = driver.findElements(By.tagName("iframe"));
		try {
			return frames.get(getIndexFrame(driver, byForElement));
		} catch (ArrayIndexOutOfBoundsException iobExcept) {
			System.out.println("Elemento no encontrado en ningun frame.");
			return null;
		}
	}
	
	public boolean cambiarClave(String nuevaClave) {
		boolean cambioDeClave = false;
		driver.findElement(By.id("tpi-user")).click();
		sleep(3000);
		driver.findElement(By.cssSelector(".tpi-user-link.tpi-fix-micuenta")).click();
		sleep(15000);
		driver.findElement(By.id("lnkCambioClave")).click();
		sleep(15000);
		driver.findElement(By.id("inputclaveanterior")).sendKeys("1469");
		driver.findElement(By.id("inputclave")).sendKeys(nuevaClave);
		driver.findElement(By.id("inputrepetirclave")).sendKeys(nuevaClave);
		driver.findElement(By.id("btnAceptarCambio")).click();
		sleep(5000);
		if (driver.findElement(By.cssSelector(".card-body.padding-bottom-20")).getText().equalsIgnoreCase("El cambio de clave fue tomado correctamente."))
			cambioDeClave = true;
		driver.findElement(By.id("tpi-user")).click();
		sleep(3000);
		driver.findElement(By.cssSelector(".tpi-user-link.tpi-fix-micuenta")).click();
		sleep(10000);
		driver.findElement(By.id("lnkCambioClave")).click();
		sleep(10000);
		driver.findElement(By.id("inputclaveanterior")).sendKeys(nuevaClave);
		driver.findElement(By.id("inputclave")).sendKeys("1469");
		driver.findElement(By.id("inputrepetirclave")).sendKeys("1469");
		driver.findElement(By.id("btnAceptarCambio")).click();
		return cambioDeClave;
	}
	
	public void obligarclick(WebElement element) {	
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+element.getLocation().y+")");
	    element.click();
	}
	
	public void tomarCaptura(WebDriver driver, String imageName) {
		// Directorio donde quedaran las imagenes guardadas
		File directory;
		directory = new File("IMG");
		directory.mkdirs();
		try {
			if (directory.isDirectory()) {
				// Toma la captura de imagen
				File imagen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				// Mueve el archivo a la carga especificada con el respectivo nombre
				FileUtils.copyFile(imagen, new File(directory.getAbsolutePath() + "\\" + imageName + ".png"));
			} else {
				// Se lanza la excepcion cuando no encuentre el directorio
				throw new IOException("ERROR : La ruta especificada no es un directorio!");
			}
		} catch (IOException e) {
			// Impresion de Excepciones
			e.printStackTrace();
		}
	}
	public void completarDatos(String sMotivo, String sProvincia, String sCiudad, String sOficina, String sTelefono, String sMail, String sFecha){
		Select mot = new Select(driver.findElement(By.id("lstMotivo")));
		mot.selectByVisibleText(sMotivo);
		Select pro = new Select(driver.findElement(By.id("lstProvincia")));
		pro.selectByVisibleText(sProvincia);
		sleep(3500);
		Select ciu = new Select(driver.findElement(By.id("lstLocalidad")));
		ciu.selectByVisibleText(sCiudad);
		Select ofi = new Select(driver.findElement(By.id("lstOficina")));
		ofi.selectByVisibleText(sOficina);
		driver.findElement(By.id("txtTelefonoAlter")).sendKeys(sTelefono);
		driver.findElement(By.id("txtemail")).sendKeys(sMail);
		driver.findElement(By.id("dpkReserva")).sendKeys(sFecha);
		sleep(1000);
		driver.findElement(By.id("txtHora")).click();
		sleep(2500);
		driver.findElement(By.id("liMan0")).click();
		driver.findElement(By.id("btnConfirmar")).click();
		sleep(8000);
	}
	
	public void AbrirTab(WebDriver driver) throws AWTException {
		Robot r = new Robot();       
		r.keyPress(KeyEvent.VK_CONTROL); 
		r.keyPress(KeyEvent.VK_T); 
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_T);
	}
	
	// Metodo para obtener el dato deseado del excel indicando la hoja o pesta;a
	// donde se encuentra (se agrupa por modulo)
	public String buscarCampoExcel(int hoja, String desc, int columna) throws IOException {
		String Campo = null;
		File archivo = new File("Lineas.xlsx");
		FileInputStream file = new FileInputStream(archivo);
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheetAt(hoja);
		Iterator<Row> rows = sheet.rowIterator();
		// rows.next();
		System.out.println("Aquiiiii");
		System.out.println(rows.next().getCell(0).getStringCellValue());
		while (rows.hasNext()) {
			XSSFRow row = (XSSFRow) rows.next();
			// System.out.println(row.getCell(0).getStringCellValue());
			if (row.getCell(0).getStringCellValue().toLowerCase().contains(desc.toLowerCase())) {
				try {
					Campo = row.getCell(columna).getStringCellValue();
				} catch(java.lang.IllegalStateException ex1) {
					Campo = Double.toString(row.getCell(columna).getNumericCellValue());
					if (Campo.contains("E")) {
						Campo = Double.toString(row.getCell(columna).getNumericCellValue());
						Campo = Campo.substring(0, Campo.indexOf("E")).replace(".", "");
					}
				}
				break;
			}
		}
		return (Campo);
	}		
	
	public void IngresarCBU(String cbu){
		buscarYClick(driver.findElements(By.cssSelector(".dev-item-menu.list-group-item-full")),"contains","adherir a d\u00e9bito autom\u00e1tico");
		sleep(8000);
		Select bco = new Select(driver.findElement(By.id("lstBancos")));
		bco.selectByIndex(1);
		driver.findElement(By.id("inputCBU")).sendKeys(cbu);
		driver.findElement(By.id("btnAdherir")).click();
		sleep(3000);
	}
	
	public void IngresarCuponPago(String CP){
		driver.findElement(By.id("lnk-descargar-cupon-pagos")).click();
		sleep(10000);
		driver.findElement(By.id("inputImporte")).sendKeys(CP);
		driver.findElement(By.id("btnDescargar")).click();
		sleep(3000);
	}
	
	public void IngresarFacturaOnline(String mail){
		driver.findElement(By.id("card-adhesion-fol")).click();
		sleep(10000);
		driver.findElement(By.id("inputEmail")).sendKeys(mail);
		driver.findElement(By.id("btn_Adherirme")).click();
		sleep(3000);
	}
	
	public void InformarPago(String pago){
		driver.findElement(By.id("btnInformarPago")).click();
		sleep(10000);
		driver.findElement(By.id("txtImporte")).clear();
		driver.findElement(By.id("txtImporte")).sendKeys(pago);
		sleep(2000);
		//obligarclick(driver.findElement(By.id("btnInformar")));
		driver.findElement(By.id("btnInformar")).click();

		sleep(7000);
	}
	public String GetStringDatePlusDay (int days){
		SimpleDateFormat sdf = new SimpleDateFormat ("-dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		System.out.println("Current Date" +sdf.format(cal.getTime()));
		cal.add(Calendar.DAY_OF_MONTH, days);
		String newDate = sdf.format(cal.getTime());
		return newDate;		
	}
	
	public String GetUserDownloadPath (){
		String path = System.getProperty("user.home") +"\\Downloads\\";
		return path;		
	}
	
	
	public Boolean RechargeLine (String lineNumber, String amount){
		
		try {
			String appPath = new File(".").getCanonicalPath();
			ProcessBuilder builder = new ProcessBuilder(
			        "cmd.exe", "/c", "cd \""+ appPath +"\\UtilsLinea\" && UtilsLinea.exe recarga "+ lineNumber +" "+ amount + " --ambiente OCS");
			    builder.redirectErrorStream(true);
			    Process p = builder.start();
			    BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
			    String line;
			    String salida ="";
			    while (true) {
			        line = r.readLine();
			        salida = salida + line;
			        if (line == null) { break; }
			        System.out.println(line);
			    }
			    Boolean succeeded = false;
			    if(salida.contains("\"Succeeded\": true"))
			    {
			        System.out.println("Recarga exitosa");
			        succeeded = true;
			    }
			    else{
			        System.out.println("Recarga fallida");
			        succeeded = false;
			    }		
			    
			    return succeeded;
			    
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	public WebElement HighLightElement(WebElement elemento){
		
		if (driver instanceof JavascriptExecutor) {
	        ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='1px solid red'", elemento);
	    }
		return elemento;
	}
	
	public void WaitForElement(String by, String text) {
		
		//Preguntar si implicit wait es mayor a cero 
		//(Hacer un gestor para almacenar la variable, porque no se puede recuperar por selenium)
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);		
		
		WebDriverWait wait = new WebDriverWait(driver, 15);
		try {
			switch (by) {
			case "id":
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id(text)));	
				break;
			case "cssSelector":
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(text)));
				break;
			}
		} catch (Exception e) {
			Assert.assertTrue(false);
		}
		//Si implicitWait era mayor a cero, volver a poner el valor.
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);			
	}
	
public void logoutEcommerce(){
		
		WaitForElement("id", "tpi-user");
		driver.findElement(By.id("tpi-user")).click();		
		WaitForElement("id", "tpi-form-logoff");
		driver.findElement(By.id("tpi-form-logoff")).click();	
	}
	
	public void loginEComerceWithBug(String sLinea, String sPass){
		
		WaitForElement("id", "tpi-login");
		buscarYClick(driver.findElements(By.id("tpi-login")),"contains","Ingresar");
		WaitForElement("id", "linea-area-numero");
		driver.findElement(By.id("linea-area-numero")).sendKeys(sLinea);
		WaitForElement("id", "btn-ingresar-clave");
		buscarYClick(driver.findElements(By.id("btn-ingresar-clave")),"contains","Ingresar con clave personal");
		WaitForElement("id", "txt-pin");
		driver.findElement(By.id("txt-pin")).sendKeys(sPass);
		WaitForElement("id", "login-btn");
		buscarYClick(driver.findElements(By.id("login-btn")),"contains","Ingresar a Personal");
		
		//Esperamos que vuelva a aparecer el bot�n ingresar con clave, para redirigir a la tienda y estar logueados.
		WaitForElement("id", "btn-ingresar-clave");
		driver.navigate().to("https://personaluat.vtexcommercestable.com.br/");
	}
	public void loginEComerce(String sLinea, String sPass){
		driver.findElement(By.id("tpi-user-login-btn")).click();
		sleep(5000);
		driver.findElement(By.id("linea-area-numero")).sendKeys(sLinea);
		sleep(3000);
		driver.findElement(By.id("btn-ingresar-clave")).click();
		driver.findElement(By.id("txt-pin")).sendKeys(sPass);
		driver.findElement(By.id("login-btn")).click();
		sleep(5000);
	}
	
	public String retornaLinea(String caso,String filename ) throws IOException{			
		String CellData = null;
		long CellData2 = 0;

		try (FileInputStream fis = new FileInputStream(filename)) {
			ExcelWBook = new XSSFWorkbook(fis);
			ExcelWSheet = ExcelWBook.getSheetAt(0);
			int count = ExcelWSheet.getPhysicalNumberOfRows();
			for (int i = 0; i < count; i++) {
				Cell = ExcelWSheet.getRow(i).getCell(0);
				CellData = Cell.getStringCellValue();
				if (CellData.equals(caso)) {
					Cell = ExcelWSheet.getRow(i).getCell(1);
					CellData2 = (long) Cell.getNumericCellValue();
					break;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return String.valueOf(CellData2);
	}
	
	public void loginClubFront(String sLinea) {
		driver.get("https://clubuat.personal.com.ar/fe/#/");
		sleep(10000);
		driver.findElement(By.id("tpi-login")).click();
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.id("idToken1")));
		driver.findElement(By.id("idToken1")).sendKeys(sLinea);
		sleep(3000);
		driver.findElement(By.id("idToken2")).clear();
		driver.findElement(By.id("idToken2")).sendKeys("1469");
		driver.findElement(By.id("loginButton_0")).click();
		sleep(20000);
		WaitForElement("id", "tpi-user");
		if (driver.findElements(By.xpath("//p/strong[@class='ng-binding']")).size() > 0)
		{
			buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary")),"contains","CONTINUAR");
		}
		
	}
	
	public void loginClubFrontSinSleep(String sLinea) {
		driver.get("https://clubuat.personal.com.ar/fe/#/");
		
		WaitForElement("id", "tpi-login");
		driver.findElement(By.id("tpi-login")).click();		
		
		WebElement frame = driver.findElement(By.id("loginIframe"));
		driver.switchTo().frame(frame);				
		
		WaitForElement("id", "idToken1");
		driver.findElement(By.id("idToken1")).sendKeys(sLinea);		
		
		WaitForElement("id", "idToken2");
		driver.findElement(By.id("idToken2")).clear();
		driver.findElement(By.id("idToken2")).sendKeys("1469");
		
		WaitForElement("id", "loginButton_0");
		driver.findElement(By.id("loginButton_0")).click();
		
		WaitForElement("id", "tpi-user");
		if (driver.findElements(By.xpath("//p/strong[@class='ng-binding']")).size() > 0)
		{
			buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary")),"contains","CONTINUAR");
		}		
	}
	
	public void AdhesionTitularClub(String mail) {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary.btn-lg.padding-left-5.padding-right-5")),"contains","SI, SON MIS DATOS");
		driver.findElement(By.id("titular")).sendKeys(mail);
		new Select(driver.findElement(By.id("selectProvincia"))).selectByVisibleText("Capital Federal");
		sleep(3000);
		new Select(driver.findElement(By.name("localidad"))).selectByVisibleText("Capital Federal");
		sleep(3000);
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary.btn-lg.btn-block")),"contains","CONTINUAR");
		sleep(10000);
		Assert.assertTrue(driver.findElement(By.cssSelector(".hidden-xs")).isDisplayed());	
		buscarYClick(driver.findElements(By.cssSelector(".text-primary.btn.btn-link.font-size-10")),"contains","IR A CLUB PERSONAL");
		sleep(10000);
		
		Connection connection = null;
		String IDTRACKINGMAIL = null;
		try {
			String driverName = "oracle.jdbc.driver.OracleDriver";
			Class.forName(driverName);
			String serverName = "10.75.253.90";
			String serverPort = "1521";
			String sid = "clubper";
			String url = "jdbc:oracle:thin:@" + serverName + ":" + serverPort + ":" + sid;
			String username = "CRM";
			String password = "DSATs6A2d";
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Successfully Connected to the database!");
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery("select HASHNUMBER from  cpmtrackingmail where TO_MAIL='"+mail+"'");
			while (rs.next()) {
				System.out.println(rs.getString(1));
				IDTRACKINGMAIL=rs.getString(1);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Could not find the database driver " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Could not connect to the database " + e.getMessage());
		}
		String url="https://clubuat.personal.com.ar/club/services/confiabilization/confirmMyEmail?feedback="+IDTRACKINGMAIL;
		driver.navigate().to(url);
		sleep(10000);
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-lg.btn-default.pull-right.ng-scope")),"contains","Mi Club");
		String validation=driver.findElement(By.cssSelector(".margin-bottom-0.col-xs-3.col-lg-4.col-md-2.col-sm-4.alert.alert-success.alert-inline.text-static.ng-scope")).getText();
		Assert.assertTrue(validation.equals("Confirmado"));
	}
	
	public void canjePremio(String premio) {
		String validacion;
		switch (premio) {
		case "Diferido":
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			buscarYClick(driver.findElements(By.cssSelector(".item-middle.text-gray-dark.ng-binding")), "contains",
					"Bolso Love&Peace");
			buscarYClick(driver.findElements(By.cssSelector(".btn.btn-danger.btn-lg")), "contains", "CANJEAR");
			buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary.btn-md")), "contains", "ACEPTAR");
			buscarYClick(driver.findElements(By.id("submit")), "contains", "CONFIRMAR CANJE");
			validacion = driver
					.findElement(By.cssSelector(".col-xs-12.text-center.text-destacado.text-destacado-xs.text-info"))
					.getText();
			Assert.assertTrue(validacion.contains("Felicitaciones"));
			break;
		case "Voucher":
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			buscarYClick(driver.findElements(By.cssSelector(".item-middle.text-gray-dark.ng-binding")), "contains",
					"Voucher EL MUNDO DEL JUGUETE POR $500");
			buscarYClick(driver.findElements(By.cssSelector(".btn.btn-danger.btn-lg")), "contains", "CANJEAR");
			buscarYClick(driver.findElements(By.id("submit")), "contains", "Continuar");
			buscarYClick(driver.findElements(By.id("submit")), "contains", "CONFIRMAR CANJE");
			validacion = driver
					.findElement(By.cssSelector(".col-xs-12.text-center.text-destacado.text-destacado-xs.text-info"))
					.getText();
			Assert.assertTrue(validacion.contains("Felicitaciones"));
			break;
		case "Credito":
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			buscarYClick(driver.findElements(By.cssSelector(".col-xs-12.col-md-12.col-lg-12.collapse-grid")), "contains",
					"RECARGAS");
			buscarYClick(driver.findElements(By.cssSelector(".item-middle.text-gray-dark.ng-binding")), "contains",
					"Cr\u00E9dito $10");
			buscarYClick(driver.findElements(By.cssSelector(".btn.btn-danger.btn-lg")), "contains", "CANJEAR");
			buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary.btn-md")), "contains", "ACEPTAR");
			buscarYClick(driver.findElements(By.id("submit")), "contains", "CONFIRMAR CANJE");
			validacion = driver
					.findElement(By.cssSelector(".col-xs-12.text-center.text-destacado.text-destacado-xs.text-info"))
					.getText();      
			Assert.assertTrue(validacion.contains("Felicitaciones"));
			break;
		case "Pack":
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			buscarYClick(driver.findElements(By.cssSelector(".col-xs-12.col-md-12.col-lg-12.collapse-grid")), "contains",
					"Packs Abono Fijo y Tarjeta");
			sleep(5000);
			driver.findElement(By.linkText("2")).click();
			sleep(5000);
			buscarYClick(driver.findElements(By.cssSelector(".item-middle.text-gray-dark.ng-binding")), "contains",
					"Pack SMS Roaming Lim\u00EDtrofes");
			buscarYClick(driver.findElements(By.cssSelector(".btn.btn-danger.btn-lg")), "contains", "CANJEAR");
			buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary.btn-md")), "contains", "ACEPTAR");
			buscarYClick(driver.findElements(By.id("submit")), "contains", "CONFIRMAR CANJE");
			validacion = driver
					.findElement(By.cssSelector(".col-xs-12.text-center.text-destacado.text-destacado-xs.text-info"))
					.getText();      
			Assert.assertTrue(validacion.contains("Felicitaciones"));
			break;
		default:
		}
	}
	
	public void actualizarDatos() {
		boolean  existe = false;
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-lg.btn-default.pull-right.ng-scope")), "contains",
				"Mi Club");
		new Select(driver.findElement(By.id("selectProvincia"))).selectByVisibleText("Mendoza");
		sleep(3000);
		new Select(driver.findElement(By.name("localidad"))).selectByVisibleText("Lujan De Cuyo");
		sleep(3000);
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-lg.btn-primary")), "contains", "Guardar >");
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-primary.btn-lg")), "contains", "Guardar");
		String texto = "Gracias por actualizar tus datos";
		List<WebElement> elements = driver.findElements(By.cssSelector(".ng-binding"));
		for (WebElement x : elements) {
			if (x.getText().toLowerCase().contains(texto.toLowerCase())) {
				existe = true;				
				break;
			}
		}
		Assert.assertTrue(existe);
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-md.btn-primary")), "contains",
				"CONTINUAR EN MI CLUB");
	}

	public void canjesRealizados() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-lg.btn-default.pull-right.ng-scope")), "contains",
				"Mi Club");
		buscarYClick(driver.findElements(By.cssSelector(".ng-binding")), "contains", "Canjes Realizados");
		int cantFilas = driver.findElements(By.xpath("//table[@class='table table-condensed ng-scope ng-table']/tbody/tr")).size();
		for (int i = 1; i <= cantFilas; i++) {
			String premio = driver.findElement(By.xpath("//table[@class='table table-condensed ng-scope ng-table']/tbody/tr[" + i + "]/td[2]")).getText();
			System.out.println(premio);
		}

	}

	public void movimientosPuntos() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		buscarYClick(driver.findElements(By.cssSelector(".btn.btn-lg.btn-default.pull-right.ng-scope")), "contains","Mi Club");
		buscarYClick(driver.findElements(By.cssSelector(".ng-binding")), "contains", "Movimientos de Puntos");
		String totalPuntosString = driver.findElement(By.cssSelector(".text-brand-cyan.margin-top-0.text-right.ng-binding")).getText();
		Double totalPuntos = Double.parseDouble(totalPuntosString);
		Double sumaPuntos = 0.0;
		
		int cantFilas = driver.findElements(By.xpath("//table[@class='table table-condensed ng-scope ng-table']/tbody/tr")).size();
		for (int i = 1; i <= cantFilas; i++) {
			String puntos = driver.findElement(By.xpath("//table[@class='table table-condensed ng-scope ng-table']/tbody/tr[" + i + "]/td[3]")).getText();
              if (puntos.contains("(")) {
            	  puntos = puntos.replace("(", "");
            	  puntos = puntos.replace(")", "");
            	  sumaPuntos = sumaPuntos - Double.parseDouble(puntos);
              }else {
            	  sumaPuntos = sumaPuntos + Double.parseDouble(puntos);
              }
		}
		Assert.assertTrue(totalPuntos.equals(sumaPuntos));
	}
	
	public void loginClubBack() {
		driver.get("https://10.75.247.45/backEnd/");
		WaitForElement("id", "userName");
		driver.findElement(By.id("userName")).sendKeys("CLU000248");
		WaitForElement("id", "password");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("Telecom2016");
		WaitForElement("id", "btnIngresar");
		driver.findElement(By.id("btnIngresar")).click();
	}
	
	public void AdhesionTitularClubBack(String linea, String mail) {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.linkText("B\u00FAsqueda por L\u00EDnea")).click();
		driver.findElement(By.id("lineNumber")).sendKeys(linea);
		driver.findElement(By.id("btnBuscar")).click();
		driver.findElement(By.id("link")).click();
		driver.findElement(By.linkText("S\u00ED")).click();
		driver.findElement(By.id("email")).sendKeys(mail);
		driver.findElement(By.id("emaildominio")).sendKeys("gmail.com");
		new Select(driver.findElement(By.id("idProvince"))).selectByVisibleText("Capital Federal");
		sleep(3000);
		new Select(driver.findElement(By.id("idLocalidad"))).selectByVisibleText("Capital Federal");
		driver.findElement(By.id("btnNo")).click();
		Assert.assertTrue(driver.findElement(By.id("popup_fancy_alert")).isDisplayed());	
		driver.findElement(By.linkText("Close")).click();
		
		Connection connection = null;
		String IDTRACKINGMAIL = null;
		try {
			String driverName = "oracle.jdbc.driver.OracleDriver";
			Class.forName(driverName);
			String serverName = "10.75.253.90";
			String serverPort = "1521";
			String sid = "clubper";
			String url = "jdbc:oracle:thin:@" + serverName + ":" + serverPort + ":" + sid;
			String username = "CRM";
			String password = "DSATs6A2d";
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Successfully Connected to the database!");
			Statement s = connection.createStatement();
			ResultSet rs = s.executeQuery("select HASHNUMBER from  cpmtrackingmail where TO_MAIL='"+mail+"@gmail.com"+"'");
			while (rs.next()) {
				System.out.println(rs.getString(1));
				IDTRACKINGMAIL=rs.getString(1);
			}
		} catch (ClassNotFoundException e) {
			System.out.println("Could not find the database driver " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Could not connect to the database " + e.getMessage());
		}
		String url="https://clubuat.personal.com.ar/club/services/confiabilization/confirmMyEmail?feedback="+IDTRACKINGMAIL;
		driver.navigate().to(url);
		sleep(5000);
		driver.navigate().to("https://10.75.247.45/backEnd/home.do");
		driver.findElement(By.linkText("B\u00FAsqueda por L\u00EDnea")).click();
		driver.findElement(By.id("lineNumber")).sendKeys(linea);
		driver.findElement(By.id("btnBuscar")).click();
		String validacion=driver.findElement(By.xpath("//*[@id=\"panelResumen\"]/tbody/tr[7]/td[5]/strong")).getText();
		Assert.assertTrue(validacion.equals("Confirmado"));	
	
	}
	
	public void busquedaLinea(String linea) {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.linkText("B\u00FAsqueda por L\u00EDnea")).click();
		driver.findElement(By.id("lineNumber")).sendKeys(linea);
		driver.findElement(By.id("btnBuscar")).click();
		Assert.assertTrue(driver.findElement(By.id("mensajeDest")).isDisplayed());	
		Assert.assertTrue(driver.findElement(By.id("tablaDatos")).isDisplayed());	
		String validacion=driver.findElement(By.xpath("//*[@id=\"panelResumen\"]/tbody/tr[5]/td[2]/strong")).getText();
		Assert.assertTrue(validacion.equals(linea));
	}
	
	public void busquedaPorDni(String nroDoc, String linea) {
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	driver.findElement(By.linkText("B\u00FAsqueda por DNI")).click();
	driver.findElement(By.id("dniNumber")).sendKeys(nroDoc);
	driver.findElement(By.id("btnBuscar")).click();		
	int cantFilas = driver.findElements(By.xpath("//table[@class='tablaDatos']/tbody/tr")).size();

	for (int i = 1; i<= cantFilas; i++)
	{
		String titular = driver.findElement(By.xpath("//table[@class='tablaDatos']/tbody/tr["+i+"]/td[12]")).getText();
		if(titular.equals("Si"))
		{
			String lineaTitular =  driver.findElement(By.xpath("//table[@class='tablaDatos']/tbody/tr["+i+"]/td[4]")).getText();
			Assert.assertTrue(linea.contentEquals(lineaTitular));
			driver.findElement(By.xpath("//table[@class='tablaDatos']/tbody/tr["+i+"]/td[2]")).click();

			String dniResultado =  driver.findElement(By.xpath("//table[@id='panelResumen']/tbody/tr[1]/td[5]")).getText();
			dniResultado = dniResultado.replace("DNI ", "");
			Assert.assertTrue(dniResultado.equals(nroDoc));
		}
	}
}
	
	public void canjePremioBack(String linea,String premio) {
		String texto;
		boolean  existe = false;
		List<WebElement> elements= null ;
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.linkText("B\u00FAsqueda por L\u00EDnea")).click();
		driver.findElement(By.id("lineNumber")).sendKeys(linea);
		driver.findElement(By.id("btnBuscar")).click();
		driver.findElement(By.linkText("Cat\u00E1logo de Premios")).click();
		switch (premio) {
		case "Diferido":
			buscarYClick(driver.findElements(By.cssSelector(".itemHolderPar")), "contains","Bolso Love&Peace");
			driver.findElement(By.id("btnCanjear")).click();
			driver.findElement(By.id("btnCanjear")).click();
			texto = "El canje se ha realizado exitosamente";
			elements = driver.findElements(By.cssSelector(".txtAzul"));
			for (WebElement x : elements) {
				if (x.getText().toLowerCase().contains(texto.toLowerCase())) {
					existe = true;				
					break;
				}
			}
			Assert.assertTrue(existe);
			break;
		case "Voucher":
			buscarYClick(driver.findElements(By.cssSelector(".itemHolderPar")), "contains","Voucher EL MUNDO DEL JUGUETE POR $500");
			driver.findElement(By.id("btnCanjear")).click();
			driver.findElement(By.id("btnCanjear")).click();
			texto = "El canje se ha realizado exitosamente";
			elements = driver.findElements(By.cssSelector(".txtAzul"));
			for (WebElement x : elements) {
				if (x.getText().toLowerCase().contains(texto.toLowerCase())) {
					existe = true;				
					break;
				}
			}
			Assert.assertTrue(existe);
			break;
		case "Credito":
			buscarYClick(driver.findElements(By.cssSelector(".itemHolder")), "contains","RECARGAS");
			buscarYClick(driver.findElements(By.cssSelector(".itemHolderPar")), "contains","Cr\u00E9dito $10");
			driver.findElement(By.id("btnCanjear")).click();
			driver.findElement(By.id("btnCanjear")).click();
			texto = "El canje se ha realizado exitosamente";
			elements = driver.findElements(By.cssSelector(".txtAzul"));
			for (WebElement x : elements) {
				if (x.getText().toLowerCase().contains(texto.toLowerCase())) {
					existe = true;				
					break;
				}
			}
			Assert.assertTrue(existe);
			break;
		case "Pack":
			
			break;
		default:
		}
	}

	@DataProvider
	public Object[][] MIX() throws Exception {
		Object[][] testObjArray = ExcelUtils.getTableArray("Lineas.xlsx", "TodasLasLineas", 1, 1, 1, "Mix");
		return (testObjArray);

	}

	@DataProvider
	public Object[][] PRE() throws Exception {
		Object[][] testObjArray = ExcelUtils.getTableArray("Lineas.xlsx", "TodasLasLineas", 1, 1, 1, "Pre");
		return (testObjArray);

	}

	@DataProvider
	public Object[][] POS() throws Exception {
		Object[][] testObjArray = ExcelUtils.getTableArray("Lineas.xlsx", "TodasLasLineas", 1, 1, 1, "Pos");
		return (testObjArray);
	}

	@DataProvider
	public Object[][] Nota_de_Credito_MIX() throws Exception {
		Object[][] testObjArray = ExcelUtils.getTableArray("Lineas.xlsx", "TodasLasLineas", 1, 1, 1, "Nota de Credito MIX");
		return (testObjArray);
	}

	@DataProvider
	public Object[][] Numeros_Amigos_Sms_Activar_MIX() throws Exception {
		Object[][] testObjArray = ExcelUtils.getTableArray("Lineas.xlsx", "TodasLasLineas", 1, 1, 1, "Numeros Amigos Sms Activar MIX");
		return (testObjArray);
	}

	@DataProvider
	public Object[][] Seguimiento_de_Gestion_MIX() throws Exception {
		Object[][] testObjArray = ExcelUtils.getTableArray("Lineas.xlsx", "TodasLasLineas", 1, 1, 1, "Seguimiento de Gestion MIX");
		return (testObjArray);
	}

	@DataProvider
	public Object[][] Roaming_LDI_Habilitado_MIX() throws Exception {
		Object[][] testObjArray = ExcelUtils.getTableArray("Lineas.xlsx", "TodasLasLineas", 1, 1, 1, "Roaming LDI Habilitado MIX");

		return (testObjArray);
	}

	@DataProvider
	public Object[][] Imprimir_cupon_de_pago_MIX() throws Exception {
		Object[][] testObjArray = ExcelUtils.getTableArray("Lineas.xlsx", "TodasLasLineas", 1, 1, 1, "Imprimir cupon de pago MIX");
		return (testObjArray);
	}

	@DataProvider
	public Object[][] DataSharing_Alta_MIX() throws Exception {
		Object[][] testObjArray = ExcelUtils.getTableArray("Lineas.xlsx", "TodasLasLineas", 1, 1, 1, "DataSharing Alta MIX");
		return (testObjArray);
	}

	@DataProvider
	public Object[][] Recargas_puntos_club_MIX() throws Exception {
		Object[][] testObjArray = ExcelUtils.getTableArray("Lineas.xlsx", "TodasLasLineas", 1, 1, 1, "Recargas puntos club MIX");
		return (testObjArray);
	}

	@DataProvider
	public Object[][] Cambio_de_domicilio_MIX() throws Exception {
		Object[][] testObjArray = ExcelUtils.getTableArray("Lineas.xlsx", "TodasLasLineas", 1, 1, 1, "Cambio de domicilio MIX");
		return (testObjArray);
	}

	@DataProvider
	public Object[][] Compras_descargar_comprobante_MIX() throws Exception {
		Object[][] testObjArray = ExcelUtils.getTableArray("Lineas.xlsx", "TodasLasLineas", 1, 1, 1, "Compras descargar comprobante MIX");
		return (testObjArray);
	}

	@DataProvider
	public Object[][] Roaming_LDI_Habilitado_PRE() throws Exception {
		Object[][] testObjArray = ExcelUtils.getTableArray("Lineas.xlsx", "TodasLasLineas", 1, 1, 1, "Roaming LDI Habilitado PRE");
		return (testObjArray);
	}

	@DataProvider
	public Object[][] Numeros_Amigos_Sms_Activar_PRE() throws Exception {
		Object[][] testObjArray = ExcelUtils.getTableArray("Lineas.xlsx", "TodasLasLineas", 1, 1, 1, "Numeros Amigos Sms Activar PRE");
		return (testObjArray);
	}

	@DataProvider
	public Object[][] Seguimiento_de_Gestion_PRE() throws Exception {
		Object[][] testObjArray = ExcelUtils.getTableArray("Lineas.xlsx", "TodasLasLineas", 1, 1, 1, "Seguimiento de Gestion PRE");
		return (testObjArray);
	}

	@DataProvider
	public Object[][] Recargas_puntos_club_PRE() throws Exception {
		Object[][] testObjArray = ExcelUtils.getTableArray("Lineas.xlsx", "TodasLasLineas", 1, 1, 1, "Recargas puntos club PRE");
		return (testObjArray);
	}

	@DataProvider
	public Object[][] Cambio_de_domicilio_PRE() throws Exception {
		Object[][] testObjArray = ExcelUtils.getTableArray("Lineas.xlsx", "TodasLasLineas", 1, 1, 1, "Cambio de domicilio PRE");
		return (testObjArray);
	}

	@DataProvider
	public Object[][] Nota_de_Credito_POS() throws Exception {
		Object[][] testObjArray = ExcelUtils.getTableArray("Lineas.xlsx", "TodasLasLineas", 1, 1, 1, "Nota de Credito POS");
		return (testObjArray);
	}

	@DataProvider
	public Object[][] Roaming_LDI_Habilitado_POS() throws Exception {
		Object[][] testObjArray = ExcelUtils.getTableArray("Lineas.xlsx", "TodasLasLineas", 1, 1, 1, "Roaming LDI Habilitado POS");
		return (testObjArray);
	}

	@DataProvider
	public Object[][] Imprimir_cupon_de_pago_POS() throws Exception {
		Object[][] testObjArray = ExcelUtils.getTableArray("Lineas.xlsx", "TodasLasLineas", 1, 1, 1, "Imprimir cupon de pago POS");
		return (testObjArray);
	}

	@DataProvider
	public Object[][] pagar_factura_POS() throws Exception {
		Object[][] testObjArray = ExcelUtils.getTableArray("Lineas.xlsx", "TodasLasLineas", 1, 1, 1, "pagar factura POS");
		return (testObjArray);
	}

	@DataProvider
	public Object[][] transferencia_de_llamada_POS() throws Exception {
		Object[][] testObjArray = ExcelUtils.getTableArray("Lineas.xlsx", "TodasLasLineas", 1, 1, 1, "transferencia de llamada POS");
		return (testObjArray);
	}

	@DataProvider
	public Object[][] Compras_descargar_comprobante_POS() throws Exception {
		Object[][] testObjArray = ExcelUtils.getTableArray("Lineas.xlsx", "TodasLasLineas", 1, 1, 1, "Compras descargar comprobante POS");
		return (testObjArray);
	}

	@DataProvider
	public Object[][] Cambio_de_domicilio_POS() throws Exception {
		Object[][] testObjArray = ExcelUtils.getTableArray("Lineas.xlsx", "TodasLasLineas", 1, 1, 1, "Cambio de domicilio POS");
		return (testObjArray);
	}

	@DataProvider
	public Object[][] Linea_Capro() throws Exception {
		Object[][] testObjArray = ExcelUtils.getTableArray("Lineas.xlsx", "TodasLasLineas", 1, 1, 1, "Mi linea capro");
		return (testObjArray);
	}

	@DataProvider
	public Object[][] Modificar_cuota_de_datos() throws Exception {
		Object[][] testObjArray = ExcelUtils.getTableArray("Lineas.xlsx", "TodasLasLineas", 1, 1, 1, "Modificar Cuota de Datos");
		return (testObjArray);
	}
	
	@DataProvider
	public Object[][] Input_mail() throws Exception {
		Object[][] testObjArray = ExcelUtils.getTableArray("Lineas.xlsx", "TodasLasLineas", 1, 1, 1, "Input Mail");
		return (testObjArray);
	}
}