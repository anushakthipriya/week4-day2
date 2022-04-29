package week4.day2.assignement;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Action3 {
	public static void main(String[] args) {
		
	WebDriverManager.chromedriver().setup();
	
	ChromeDriver driver = new ChromeDriver();
	
	driver.get("http://www.leafground.com/pages/drop.html");
	
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// to find drag element
		
		WebElement sourceEle = driver.findElement(By.id("draggable"));
		
		WebElement destEle = driver.findElement(By.id("droppable"));
		
		Actions builder = new Actions(driver);
		
		builder.dragAndDrop(sourceEle, destEle).perform();
		
		String text = driver.findElement(By.xpath("//div[@id='droppable']/p")).getText();

		if (text.contains("Dropped"))
		{
			System.out.println("The Element moved to Target so VALIDATION SUCCESS");
		} else {
			System.out.println(" VALIDATION FAILED");
}}
}
