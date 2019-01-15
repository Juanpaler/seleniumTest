package PageMetodos;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Metodos {
	
	static WebDriver driver;
	public static String lineaMIX = "1162735148";
	public static String lineaPre = "1162745165";
	public static String lineaPos = "1145642605";
	
	
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
		waitFor.click(driver.findElement(By.id("modal-ingresar")));
		sleep(4000);
		String parentWindowHandler = driver.getWindowHandle();
		String subWindowHandler = null;
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> iterator = handles.iterator();
		while (iterator.hasNext()) {
			subWindowHandler = iterator.next();
		}
		driver.switchTo().window(subWindowHandler);
		driver.findElement(By.id("linea-numero")).clear();
		sleep(5000);
		switch(tipoDeLinea) {
		case "MIX":
			driver.findElement(By.id("linea-numero")).sendKeys(lineaMIX);
			break;
		case "Pre":
			driver.findElement(By.id("linea-numero")).sendKeys(lineaPre);
			break;
		case "Pos":
			driver.findElement(By.id("linea-numero")).sendKeys(lineaPos);
			break;
		}
		waitFor.click(driver.findElement(By.id("btn-login")));
		sleep(25000);
		driver.findElement(By.id("idToken2")).clear();
		driver.findElement(By.id("idToken2")).sendKeys("1469");
		waitFor.click(driver.findElement(By.id("loginButton_0")));
		driver.switchTo().window(parentWindowHandler);
		sleep(20000);
	}
	
	public void buscarYClick(List <WebElement> elements, String match, String texto) {
		sleep(2000);
		switch (match) {
		case "contains":
			for (WebElement x : elements) {
				if (x.getText().toLowerCase().contains(texto)) {
					x.click();
					break;
				}
			}
			break;
		case "equals":
			for (WebElement x : elements) {
				if (x.getText().toLowerCase().equals(texto)) {
					x.click();
					break;
				}
			}
			break;
		}
		sleep(2000);
	}
	
	public void selectByText(WebElement element, String data){
		Select select = new Select(element);
		select.selectByVisibleText(data);
		sleep(2000);
	}
	
	public static WebDriverWait dynamicWait() {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		return wait;
	}
	
	public static class waitFor {
		
		public static void selected(WebElement element) {
			dynamicWait().until(ExpectedConditions.elementToBeSelected(element));
		}
		
		public static void click(WebElement element) {
			dynamicWait().until(ExpectedConditions.elementToBeClickable(element));
			((JavascriptExecutor) driver).executeScript("window.scrollTo(0," + element.getLocation().y + ")");
			element.click();
		}
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
	}
}