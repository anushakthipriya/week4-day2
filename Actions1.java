package week4.day2.assignement;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Actions1 {
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();

		// 1. to get a Link
		driver.get("https://jqueryui.com/resizable");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		// 2. to find frame
		WebElement frame = driver.findElement(By.xpath("//iframe[@class='demo-frame']"));
		driver.switchTo().frame(frame);

		// 3. Finding Resizable element
		WebElement resize = driver.findElement(By.xpath("//div[@id='resizable']/div[3]"));
		int a = resize.getLocation().getX();
		int b = resize.getLocation().getX();

		a = a + 5;
		b = b + 10;

		// 4. Draging the Element
		Actions builder = new Actions(driver);
		builder.clickAndHold(resize).pause(2000).moveByOffset(a, b).release().perform();

	}
}
