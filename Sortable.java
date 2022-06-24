package week4.day2;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Sortable {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver  = new ChromeDriver();
		
		driver.get("http://www.leafground.com/pages/sortable.html");
		driver.manage().window().maximize();
		
		List<WebElement> ele = driver.findElements(By.xpath("//div[@id='mydiv']//li"));
		
		Thread.sleep(2000);
		
		//Move 3 to 1
		WebElement from1 = ele.get(2);
		WebElement to1  = ele.get(0);
		Actions builder1 = new Actions(driver);
		builder1.clickAndHold(from1).moveToElement(to1).build().perform();
		
		Thread.sleep(2000);
		
		//Move 7 to 5
		WebElement from2 = ele.get(6);
		WebElement to2  = ele.get(4);
		Actions builder2 = new Actions(driver);
		builder2.clickAndHold(from2).moveToElement(to2).build().perform();

	}

}
