package PageMetodos;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Metodos {
	
	static WebDriver driver;
	
	public static WebDriver setup(){
		System.setProperty("webdriver.chrome.driver", "Chromedriver.exe");
		ChromeOptions Options = new ChromeOptions();
		Options.addArguments("start-maximized");
		driver = new ChromeDriver(Options);
		return driver;
	}
		
	public void sleep(int miliseconds) {
		try {Thread.sleep(miliseconds);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
	}
	
	public void loginPorLinea(String tipoDeLinea) {
		driver.get("https://autogestionuat.personal.com.ar");
		driver.findElement(By.id("modal-ingresar")).click();
		try {Thread.sleep(4000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		String parentWindowHandler = driver.getWindowHandle();
		String subWindowHandler = null;
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()) {
			subWindowHandler = iterator.next();
		}
		driver.switchTo().window(subWindowHandler);
		driver.findElement(By.id("linea-numero")).clear();
		try {Thread.sleep(5000);} catch (Exception ignore) {}
		switch(tipoDeLinea) {
		case "MIX":
			driver.findElement(By.id("linea-numero")).sendKeys("1162735148");
			break;
		case "Pre":
			driver.findElement(By.id("linea-numero")).sendKeys("1162745165");
			break;
		case "Pos":
			driver.findElement(By.id("linea-numero")).sendKeys("1145642605");
			break;
		}
		try {Thread.sleep(5000);} catch (Exception ignore) {}
		driver.findElement(By.id("btn-login")).click();
		try {Thread.sleep(15000);} catch (InterruptedException ex) {Thread.currentThread().interrupt();}
		driver.findElement(By.id("idToken2")).clear();
		driver.findElement(By.id("idToken2")).sendKeys("1469");
		driver.findElement(By.id("loginButton_0")).click();
		driver.switchTo().window(parentWindowHandler);
		try {Thread.sleep(20000);} catch (Exception ignore) {}
	}
}
