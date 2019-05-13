package PageMetodos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.TestNG;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

public class Utils {
	
    private static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static XSSFCell Cell;

    
	public Properties config = new Properties();
    InputStream configInput = null;
	

    public void loadConfig(){
        try{
            configInput = new FileInputStream("appConfig.properties");
            config.load(configInput);
            //Las configuraciones se consumen as�:
            //String excelLineasEcommerce = config.getProperty("ExcelLineasEcommerce");
           
        } catch(Exception e){
        	System.out.println("Error cargando configuración\n" + e.getMessage());
        }
    }
    
	public void WaitForElement(WebDriver driver, String by, String text) {		
		
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);		
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		try {
			switch (by) {
			case "id":
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id(text)));	
				break;
			case "cssSelector":
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(text)));
				break;
			case "xpath":
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(text)));
				break;
			}
		} catch (Exception e) {
			Assert.assertTrue(false);
		}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);			
	}
	
	public Boolean ElementCreatedUni(WebDriver driver, String by, String text, int time) {
			
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);		
		
		WebDriverWait wait = new WebDriverWait(driver, time);
		try {
			switch (by) {
			case "id":
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id(text)));	
				break;
			case "cssSelector":
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(text)));
				break;
			case "xpath":
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(text)));
				break;
			}
		} catch (Exception e) {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			return false;
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		
		return true;
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

	
	public String GetStringDatePlusDay (int days){
		SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();
		System.out.println("Current Date" +sdf.format(cal.getTime()));
		cal.add(Calendar.DAY_OF_MONTH, days);
		String newDate = sdf.format(cal.getTime());
		return newDate;		
	}
	
	public String GetFormattedStringDatePlusDay (int days, String formato){
		SimpleDateFormat sdf = new SimpleDateFormat (formato);
		Calendar cal = Calendar.getInstance();
		System.out.println("Current Date" +sdf.format(cal.getTime()));
		cal.add(Calendar.DAY_OF_MONTH, days);
		String newDate = sdf.format(cal.getTime());
		return newDate;		
	}
	
	public String GetStringDate(){
		String pattern = "dd/MM/yyyy";
		String dateInString =new SimpleDateFormat(pattern).format(new Date());
		return dateInString;		
	}
	
	public String GetFormattedStringDate(String formato){		
		String dateInString =new SimpleDateFormat(formato).format(new Date());
		return dateInString;		
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
	
	public WebElement HighLightElement(WebElement elemento, WebDriver driver){
		
		if (driver instanceof JavascriptExecutor) {
	        ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='1px solid red'", elemento);
	    }
		return elemento;
	}
	public WebElement HighLightElement(IOSElement elemento, IOSDriver<WebElement> driver){
		
		if (driver instanceof JavascriptExecutor) {
	        ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='1px solid red'", elemento);
	    }
		return elemento;
	}
	
	public String reportDirectory(String modulo) throws IOException {
		//Se crea nuevo directorio para guardar el reporte de ejecuciones
        String fileName = "Ejecuciones";
        Path path = Paths.get(fileName);
        if (!Files.exists(path)) {
            Files.createDirectory(path);
            System.out.println("Directorio de evidencias creado");
        } else {
            
            System.out.println("Directorio de evidencias ya existe");
        }
        
		//Se crea nuevo directorio para guardar el reporte del Modulo
        path = Paths.get(fileName+"/"+modulo);
        if (!Files.exists(path)) {
            Files.createDirectory(path);
            System.out.println("Directorio de evidencias " + modulo + " creado");
        } else {
            
            System.out.println("Directorio de evidencias " + modulo + " ya existe");
        }
        
    	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
    	Date date = new Date();
    	String fecha=dateFormat.format(date);
        TestNG.getDefault().setOutputDirectory(fileName+"/"+modulo+"/"+fecha);
 
        //Creo el directorio para guardar la ejecucion
        String directorio=fileName+"/"+modulo+"/"+fecha;
        path = Paths.get(directorio);
        if (!Files.exists(path)) {
            Files.createDirectory(path);
            System.out.println("Se crea directorio de ejecucion");
        } else {
            
            System.out.println("Directorio de ejecucion ya existe");
        }
        
        //Creo el directorio para guardar las capturas
       
        path = Paths.get(directorio+"/Evidencias");
        if (!Files.exists(path)) {
            Files.createDirectory(path);
            System.out.println("Directorio de imagenes creado");
        } else {
            
            System.out.println("Directorio de imagenes ya existe");
        }
         
        return directorio+"/Evidencias";
	}
	
	public static void sleep(int miliseconds) {
		try {Thread.sleep(miliseconds);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
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
	
	public boolean intValue(String value) {  
	     try{  
			 Integer.parseInt(value);  
			 return true;  
	      } 
	     catch (NumberFormatException e){  
	    	 return false;  
	     }  
	}
	
	public boolean doubleValue(String value) {  
	     try{  
			 Double.parseDouble(value);  
			 return true;  
	      } 
	     catch (NumberFormatException e){  
	    	 return false;  
	     }  
	}
  
}
