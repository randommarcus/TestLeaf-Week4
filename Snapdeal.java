package week4.day2;

import java.io.File;
import java.io.IOException;
import java.util.*;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Snapdeal {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver  = new ChromeDriver();
		
		driver.get("https://www.snapdeal.com/");
		driver.manage().window().maximize();
		
		
		//Go to Men's Fashion > Sports Shoes
		Actions builder1 = new Actions(driver);
		WebElement men = driver.findElement(By.linkText("Men's Fashion"));
		builder1.moveToElement(men).perform();
		driver.findElement(By.xpath("//span[contains(text(), 'Sports Shoes')]")).click();
		Thread.sleep(2000);
		
		
		//Count of sports shoes
		WebElement shoes = driver.findElement(By.xpath("//span[contains(@class, 'category-count')]"));
		System.out.println("Number of sports shoes: "+shoes.getText());
		Thread.sleep(2000);
		
		
		//Go to training shoes and sort by low to high
		driver.findElement(By.xpath("//div[contains(text(), 'Training Shoes')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[contains(text(), 'Sort by')]")).click();
		driver.findElement(By.xpath("(//li[@class='search-li'])[1]")).click();
		Thread.sleep(2000);
		
		
		//Check if the results are sorted correctly
		List<WebElement>sortedPrice = driver.findElements(By.xpath("//span[@class='lfloat product-price']"));
		System.out.println("Verifying Low to High Sort");
		for(WebElement element:sortedPrice)
		{
			System.out.println(element.getText());
		}
		Thread.sleep(2000);
		
		
		//Select 900-1200 Price Range
		WebElement from = driver.findElement(By.xpath("//input[@name='fromVal']"));
		from.clear();
		from.sendKeys("900");
		WebElement to = driver.findElement(By.xpath("//input[@name='toVal']"));
		to.clear();
		to.sendKeys("1200", Keys.ENTER);
		Thread.sleep(2000);
		
		
		//Select Navy color
		driver.findElement(By.xpath("(//button[text()='View More '])[1]")).click();
		WebElement color = driver.findElement(By.xpath("//a[text()=' Navy']"));
		Thread.sleep(2000);
		
		
		//Verify all filters
		WebElement filterPrice = driver.findElement(By.xpath("(//a[@class='clear-filter-pill'])[1]"));
		String price = filterPrice.getText();
		System.out.println("Filtered Price Range: " + price);
		Thread.sleep(2000);
		
		
		//Hover mouse on first result and click Quick View
		WebElement firstResult = driver.findElement(By.xpath("//p[@class='product-title']"));
		Actions builder2 = new Actions(driver);
		builder2.moveToElement(firstResult).perform();
		driver.findElement(By.xpath("//div[contains(text(),'Quick View')]")).click();
		Thread.sleep(2000);
		
		
		//Print Cost and discount percentage
		WebElement cost = driver.findElement(By.xpath("//span[@class='payBlkBig']"));
		String costString = cost.getText();
		System.out.println("The cost of the product: "+costString);
		WebElement discount = driver.findElement(By.xpath("//span[@class='percent-desc ']"));
		String discountString = discount.getText();
		System.out.println("The discount on the prduct: " + discountString);
		Thread.sleep(2000);
		
		
		//Take screenshot
		File source = driver.getScreenshotAs(OutputType.FILE);
		File destination = new File(".screenshot2.png");
		FileUtils.copyFile(source, destination);
		Thread.sleep(2000);
		
		
		//Close all windows
		driver.quit();
		
	}

}
