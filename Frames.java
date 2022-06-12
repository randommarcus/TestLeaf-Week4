package week4.day1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Frames {

	public static void main(String[] args) {
		

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver  = new ChromeDriver();
		
		driver.get("https://chercher.tech/practice/frames-example-selenium-webdriver");
		driver.manage().window().maximize();
		
		//Topic text box
		String topic = driver.findElement(By.xpath("//label[text()='Topic : ']/span")).getText();
		WebElement frameElement1 = driver.findElement(By.xpath("//iframe[@id='frame1']"));
		driver.switchTo().frame(frameElement1);
		driver.findElement(By.xpath("//b[@id='topic']/following-sibling::input")).sendKeys(topic);
		
		//Inner Frame check box
		WebElement frameElement2 = driver.findElement(By.xpath("//iframe[@id='frame3']"));
		driver.switchTo().frame(frameElement2);
		driver.findElement(By.xpath("//b[text()='Inner Frame Check box :']/following-sibling::input")).click();
		
		driver.switchTo().defaultContent();
		
		//Animals drop-down
		WebElement frameElement3 = driver.findElement(By.xpath("//iframe[@id='frame2']"));
		driver.switchTo().frame(frameElement3);
		WebElement dropEle = driver.findElement(By.xpath("//b[text()='Animals :']/following-sibling::select"));
		Select dropDown = new Select(dropEle);
		dropDown.selectByIndex(1);

	}

}
