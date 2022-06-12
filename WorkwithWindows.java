package week4.day1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WorkwithWindows {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver  = new ChromeDriver();
		
		driver.get("http://www.leafground.com/pages/Window.html");
		driver.manage().window().maximize();
		
		
		//Open Home Page
		driver.findElement(By.xpath("//button[@id='home']")).click();
		Set <String> windowHandle1 = driver.getWindowHandles();
		List <String> windows1 = new ArrayList <String> (windowHandle1);
		driver.switchTo().window(windows1.get(1));
		System.out.println("Home Page Window");
		Thread.sleep(3000);
		driver.close();
		driver.switchTo().window(windows1.get(0));
		System.out.println("Main Window");
		
		//Interval of 3 seconds
		Thread.sleep(3000);
		
		
		//Open Multiple Windows
		driver.findElement(By.xpath("//button[text()='Open Multiple Windows']")).click();
		Set <String> windowHandle2 = driver.getWindowHandles();
		List <String> windows2 = new ArrayList <String> (windowHandle2);
		System.out.println("Number of windows: "+windows2.size());
		Thread.sleep(3000);
		for (int i = 1; i < windows2.size(); i++) {
			
			driver.switchTo().window(windows2.get(i));
			driver.close();
		}
		
		
		//Closing additional windows and interval of 3 seconds
		driver.switchTo().window(windows2.get(0));
		Thread.sleep(3000);
		
		
		//Do not close me
		driver.findElement(By.xpath("//button[text()='Do not close me ']")).click();
		Set <String> windowHandle3 = driver.getWindowHandles();
		List <String> windows3 = new ArrayList <String> (windowHandle3);
		System.out.println("Number of windows: "+windows3.size());
		Thread.sleep(3000);
		System.out.println("New windows not closed");
		
		
		//Closing additional windows and interval of 3 seconds
		for (int i = 1; i < windows3.size(); i++) {
			
			driver.switchTo().window(windows3.get(i));
			driver.close();
		}
		driver.switchTo().window(windows3.get(0));
		Thread.sleep(3000);
		
		
		//Wait for 5 seconds
		driver.findElement(By.xpath("//button[text()='Wait for 5 seconds']")).click();
		Set <String> windowHandle4 = driver.getWindowHandles();
		List <String> windows4 = new ArrayList <String> (windowHandle4);
		System.out.println(+windows4.size()-1 +" windows opened after 5 seconds");

	}

}
