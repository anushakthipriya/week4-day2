package week4.day2.assignement;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Action4 {
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();

		// 1. Get Link
		driver.get("http://www.leafground.com/pages/selectable.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		// 2. Get Elements
		WebElement item1 = driver.findElement(By.xpath("//ol[@id='selectable']/li[1]"));
		WebElement item4 = driver.findElement(By.xpath("//ol[@id='selectable']/li[4]"));
		WebElement item6 = driver.findElement(By.xpath("//ol[@id='selectable']/li[6]"));
		
		Point location = item4.getLocation();
		int x = location.getX();
		int y = location.getY();

		// 3. Action
		Actions builder = new Actions(driver);
		builder.dragAndDropBy(item1, x, y).clickAndHold(item4).pause(1000).moveToElement(item6).release().perform();

	}
}