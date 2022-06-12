package week4.day1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver  = new ChromeDriver();
		
		//Open Amazon India
		driver.get("https://amazon.in");
		driver.manage().window().maximize();
		
		
		//Search for Oneplus 9 Pro
		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("Oneplus 9 Pro");
		driver.findElement(By.xpath("//input[@id='nav-search-submit-button']")).click();
		
		
		//Price of the first result
		String price = driver.findElement(By.xpath("//span[@class='a-price-whole']")).getText();
		System.out.println("Price of the first result: "+price);
		
		
		//Number of customer ratings
		String ratings = driver.findElement(By.xpath("//span[@class='a-size-base s-underline-text']")).getText();
		System.out.println("Number of customer ratings: "+ratings);
		
		
		//Open first result
		driver.findElement(By.xpath("//span[@class='a-size-medium a-color-base a-text-normal']")).click();
		
		
		//Switch to the product tab
		Set <String> windowHandle = driver.getWindowHandles();
		List <String> windows = new ArrayList <String> (windowHandle);
		driver.switchTo().window(windows.get(1));
		
		
		//Screenshot product page
		File source = driver.getScreenshotAs(OutputType.FILE);
		File destination = new File(".screenshot.png");
		FileUtils.copyFile(source, destination);
		
		
		//Add product to cart and wait 3 seconds
		driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
		Thread.sleep(3000);
		
		
		//Cart subtotal and verification
		String total = driver.findElement(By.xpath("//span[@id='attach-accessory-cart-subtotal']")).getText();
		if (total.substring(0).contains(price)) {
			System.out.println("Subtotal verified");
		}
		else {
			System.out.println("Error");
		}
		
		
		//Close browser
		driver.close();
		driver.switchTo().window(windows.get(0));
		driver.close();
		
		
	}

}
