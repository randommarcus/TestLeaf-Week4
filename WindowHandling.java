//Task: https://github.com/TestLeafPages/SeleniumAssignments/blob/master/week3/day2/Assignments/MergeContact.java

package week4.day1;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WindowHandling {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver  = new ChromeDriver();
		
		driver.get("http://leaftaps.com/opentaps/control/login");
		driver.manage().window().maximize();
		
		driver.findElement(By.id("username")).sendKeys("DemoSalesManager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		driver.findElement(By.className("decorativeSubmit")).click();
		
		driver.findElement(By.linkText("CRM/SFA")).click();
		driver.findElement(By.xpath("//a[text() = 'Contacts']")).click();
		driver.findElement(By.xpath("//a[text() = 'Merge Contacts']")).click();
		
		driver.findElement(By.xpath("//input[@id = 'partyIdFrom']/following-sibling::a")).click();
		
		Set <String> windowHandle1 = driver.getWindowHandles();
		List <String> windows1 = new ArrayList <String> (windowHandle1);
		driver.switchTo().window(windows1.get(1));
		
		Thread.sleep(2000);
		
		WebElement findFirstLink = driver.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a"));
		findFirstLink.click();
		driver.switchTo().window(windows1.get(0));
		
		driver.findElement(By.xpath("//input[@id='partyIdTo']/following-sibling::a")).click();		
		Set <String> windowHandle2 = driver.getWindowHandles();		
		List <String> windows2 = new ArrayList<String>(windowHandle2);
		driver.switchTo().window(windows2.get(1));
		
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a")).click();
		driver.switchTo().window(windows2.get(0));
		driver.findElement(By.xpath("//a[text()='Merge']")).click();
		
		Alert alert = driver.switchTo().alert();
		alert.accept();
		
		System.out.println(driver.getTitle());
		
		if(driver.getTitle().contains("Merge")) {
			System.out.println("Title Verified");
		}
		else {
			System.out.println("Error");	
		}
	}

}
