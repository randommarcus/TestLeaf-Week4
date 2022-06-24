package week4.day2;

import java.util.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver  = new ChromeDriver();
		
		driver.get("https://www.nykaa.com/");
		driver.manage().window().maximize();
		
		
		//Hover over brands, click on L'Oreal Paris logo, and get resulting page title
		WebElement brands = driver.findElement(By.xpath("//a[text()='brands']"));
		Actions builder = new Actions(driver);
		builder.moveToElement(brands).perform();
		driver.findElement(By.xpath("//img[contains(@src, 'lorealparis')]")).click();
		System.out.println("Title of the page: "+driver.getTitle());
		Thread.sleep(2000);
		
		
		//Sort by Customer Top Rated
		driver.findElement(By.xpath("//span[contains(text(), 'Sort By')]")).click();
		driver.findElement(By.xpath("//span[contains(text(), 'customer top rated')]")).click();
		Thread.sleep(2000);
		
		
		//Select Shampoo from Category with Concern as Color Protection
		driver.findElement(By.xpath("//span[contains(text(), 'Category')]")).click();
		driver.findElement(By.xpath("//span[contains(text(), 'Hair')]")).click();
		driver.findElement(By.xpath("//span[contains(text(), 'Hair Care')]")).click();
		driver.findElement(By.xpath("//span[contains(text(), 'Shampoo')]")).click();
		driver.findElement(By.xpath("//span[contains(text(), 'Concern')]")).click();
		driver.findElement(By.xpath("//span[contains(text(), 'Color Protection')]")).click();
		Thread.sleep(2000);
		
		
		//Check filter
		String check = driver.findElement(By.xpath("//span[contains(text(),'Shampoo')]")).getText();
		if (check.contains("Shampoo")) {
			System.out.println("Filter applied successfully");
		}
		else
		{
			System.out.println("Filter failed");
		}
		Thread.sleep(2000);
		
		
		//Click on the result
		driver.findElement(By.xpath("//div[contains(text(), 'Colour Protect Shampoo')]")).click();
		Thread.sleep(2000);
		
		
		//Go to product window
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> handle = new ArrayList <String>(windowHandles);
		driver.switchTo().window(handle.get(1));
		
		
		//Select 175ml and print price
		WebElement size = driver.findElement(By.xpath("//select[@title='SIZE']"));
		Select d = new Select(size);
		d.selectByVisibleText("175ml");
		WebElement price = driver.findElement(By.xpath("//span[@class='css-1jczs19'][1]"));
		System.out.println("The price of the product is "+ price.getText().substring(1));
		Thread.sleep(2000);
		
		
		//Add to bag and print Grand Total from Shopping Bag
		driver.findElement(By.xpath("//span[contains(text(), 'Add to Bag')]")).click();
		driver.findElement(By.xpath("//span[contains(text(), 'Account')]/following::button[1]")).click();
		Thread.sleep(5000);
		WebElement frameEle = driver.findElement(By.xpath("//iframe[@class='css-acpm4k']"));
		driver.switchTo().frame(frameEle);
		WebElement total = driver.findElement(By.xpath("//div[@class='value medium-strong']"));
		String totalStr = total.getText().substring(1);
		System.out.println("The Grand Total is "+totalStr);
		Thread.sleep(2000);
		
		
		//Checkout the product as Guest
		driver.findElement(By.xpath("//span[contains(text(), 'Proceed')]")).click();
		driver.findElement(By.xpath("//button[contains(text(), 'CONTINUE AS GUEST')]")).click();
		Thread.sleep(3000);
		
		
		//Compare Grand Total of Checkout and Shopping Bag
		WebElement checkout = driver.findElement(By.xpath("(//div[@class='value'])[2]"));
		String ctotalStr = checkout.getText().substring(1);
		System.out.println("The Checkout total is "+ctotalStr);
		if(ctotalStr.equals(totalStr)) {
			System.out.println("Checkout total matches with Shopping Bag");
		}
		else
		{
			System.out.println("Checkout total does not match with Shopping Bag");
		}
		Thread.sleep(2000);
		
		
		//Close all windows
		driver.quit();

	}

}
