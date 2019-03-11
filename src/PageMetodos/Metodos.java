package PageMetodos;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

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
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;

import DataProvider.ExcelUtils;


public class Metodos {
	
	static WebDriver driver;
	static WebDriver driverf;
	static WebDriver driveri;
	
	public static String lineaMIX = "1162735148";
	public static String lineaPre = "1162745165";
	public static String lineaPos = "1145642605";
	
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
		/*driver.findElement(By.id("modal-ingresar")).click();
		sleep(4000);
		String parentWindowHandler = driver.getWindowHandle();
		String subWindowHandler = null;
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()) {
			subWindowHandler = iterator.next();
		}
		driver.switchTo().window(subWindowHandler);*/
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.id("idToken1")));
		//driver.findElement(By.id("linea-numero")).clear();
		//sleep(5000);
		driver.findElement(By.id("idToken1")).sendKeys(sLinea);
		
		//driver.findElement(By.id("btn-login")).click();;
		//sleep(25000);
		driver.findElement(By.id("idToken2")).clear();
		driver.findElement(By.id("idToken2")).sendKeys("1469");
		driver.findElement(By.id("loginButton_0")).click();
		//driver.switchTo().window(parentWindowHandler);
		sleep(20000);
	}
	
	public void loginPorLineaF(String sLinea) {
		driverf.get("https://autogestionuat.personal.com.ar");
		/*driver.findElement(By.id("modal-ingresar")).click();
		sleep(4000);
		String parentWindowHandler = driver.getWindowHandle();
		String subWindowHandler = null;
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()) {
			subWindowHandler = iterator.next();
		}
		driver.switchTo().window(subWindowHandler);*/
		sleep(10000);
		driverf.switchTo().frame(cambioFrame(driverf, By.id("idToken1")));
		//driver.findElement(By.id("linea-numero")).clear();
		//sleep(5000);
		driverf.findElement(By.id("idToken1")).sendKeys(sLinea);
		
		//driver.findElement(By.id("btn-login")).click();;
		//sleep(25000);
		driverf.findElement(By.id("idToken2")).clear();
		driverf.findElement(By.id("idToken2")).sendKeys("1469");
		driverf.findElement(By.id("loginButton_0")).click();
		//driver.switchTo().window(parentWindowHandler);
		sleep(20000);
	}
	
	public void loginPorLineaI(String sLinea) {
		driveri.get("https://autogestionuat.personal.com.ar");
		/*driver.findElement(By.id("modal-ingresar")).click();
		sleep(4000);
		String parentWindowHandler = driver.getWindowHandle();
		String subWindowHandler = null;
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()) {
			subWindowHandler = iterator.next();
		}
		driver.switchTo().window(subWindowHandler);*/
		sleep(10000);
		driveri.switchTo().frame(cambioFrame(driveri, By.id("idToken1")));
		//driver.findElement(By.id("linea-numero")).clear();
		//sleep(5000);
		driveri.findElement(By.id("idToken1")).sendKeys(sLinea);
		
		//driver.findElement(By.id("btn-login")).click();;
		//sleep(25000);
		driveri.findElement(By.id("idToken2")).clear();
		driveri.findElement(By.id("idToken2")).sendKeys("1469");
		driveri.findElement(By.id("loginButton_0")).click();
		//driver.switchTo().window(parentWindowHandler);
		sleep(20000);
	}
	
	public void LoginPorLineaVariable(String sLinea){
		driver.get("https://autogestionuat.personal.com.ar");
		/*driver.findElement(By.id("modal-ingresar")).click();
		sleep(4000);
		String parentWindowHandler = driver.getWindowHandle();
		String subWindowHandler = null;
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()) {
			subWindowHandler = iterator.next();
		}*/
		sleep(10000);
		driver.switchTo().frame(cambioFrame(driver, By.id("idToken1")));		
		//driver.findElement(By.id("idToken1")).clear();
		//sleep(5000);
		driver.findElement(By.id("idToken1")).sendKeys(sLinea);
		//driver.findElement(By.id("btn-login")).click();;
		//sleep(25000);
		driver.findElement(By.id("idToken2")).clear();
		driver.findElement(By.id("idToken2")).sendKeys("1469");
		driver.findElement(By.id("loginButton_0")).click();
		//driver.switchTo().window(parentWindowHandler);
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
		obligarclick(driver.findElement(By.id("btnInformar")));
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