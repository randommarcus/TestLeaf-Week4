package week4.day2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TableInteraction {

	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		
		driver.get("http://www.leafground.com/pages/table.html");
		driver.manage().window().maximize();
		
		
		//Number of columns
		List <WebElement> columns = driver.findElements(By.tagName("th"));
		int colNum = columns.size();
		System.out.println("Number of columns: "+colNum);
		
		
		//Number of rows
		List <WebElement> rows = driver.findElements(By.tagName("tr"));
		int rowNum = rows.size();
		System.out.println("Number of rows: "+rowNum);
		
		
		//Progress value of 'Learn to interact with Elements'
		WebElement progress = driver.findElement(By.xpath("(//td[normalize-space()='Learn to interact with Elements']//following::td)[1]"));
		String proValue = progress.getText();
		System.out.println(proValue);
		
		
		//Vital task for the least completed progress
		List<WebElement> progressList = driver.findElements(By.xpath("//td[2]"));
		List<Integer> eleList = new ArrayList<Integer>();
		
		for (WebElement w : progressList) 
		{
			String s = w.getText().replace("%", "");
			eleList.add(Integer.parseInt(s));
		}
		
		System.out.println("Progress List:" + eleList);

		int leastProg = Collections.min(eleList);
		System.out.println("Least value:" + leastProg);
		String leastProgVal = Integer.toString(leastProg);
		
		if (leastProgVal.equals("20")) 
		{
			WebElement input = rows.get(5).findElement(By.tagName("input"));
			input.click();
		}

	}

}
