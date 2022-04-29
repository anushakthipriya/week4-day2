package week4.day2.assignement;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebTable {
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();

		// 1. Get Link
		driver.get("http://www.leafground.com/pages/table.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		// 2.Get the count of number of columns
		List<WebElement> columnCount = driver.findElements(By.tagName("th"));
		System.out.println("The Count of Number of Columns are: " + columnCount.size());

		// 3.Get the count of number of rows
		List<WebElement> rowCount = driver.findElements(By.tagName("tr"));
		System.out.println("The Count of Number of Columns are: " + rowCount.size());

		// 4. Get the progress value of 'Learn to interact with Elements'
		List<WebElement> elements = driver.findElements(By.xpath("//tr/td[1]/font"));
		List<String> progressValue = new ArrayList<String>();

		// 5. Check whether the elements contain the Text 'Learn to interact with
		// Elements'
		for (int i = 1; i <= elements.size(); i++) {
			String text = elements.get(i - 1).getText();
			if (text.equals("Learn to interact with Elements")) {
				String value = driver.findElement(By.xpath("(//tr)[" + (i + 1) + "]/td[2]/font")).getText();
				progressValue.add(value);
			}
		}

		// 6. Get the progress value of 'Learn to interact with Elements'
		for (int i = 0; i < progressValue.size(); i++) {
			System.out.println("The Progress Value of Learn to interact with Elements in the Occurance " + (i + 1)
					+ " is " + progressValue.get(i));
		}

		// 7. Check the vital task for the least completed progress.
		List<WebElement> progress = driver.findElements(By.xpath("//tr/td[2]/font"));
		List<Integer> valueOfProgress = new ArrayList<Integer>();
		for (int i = 0; i < progress.size(); i++) {
			String text = progress.get(i).getText();
			int value = Integer.parseInt(text.substring(0, text.length() - 1));
			valueOfProgress.add(value);
		}
		Collections.sort(valueOfProgress);
		int leastValue = valueOfProgress.get(0);

		for (int i = 1; i <= progress.size(); i++) {
			String value = driver.findElement(By.xpath("(//tr)[" + (i + 1) + "]/td[2]/font")).getText();
			if (value.contains(Integer.toString(leastValue))) {
				driver.findElement(By.xpath("(//tr)[" + (i + 1) + "]/td[3]/input")).click();
				String topic = driver.findElement(By.xpath("(//tr)[" + (i + 1) + "]/td[1]/font")).getText();
				System.out.println("The Least completed Progress is " + leastValue + "% and the Topic is " + topic);
				break;
			}
		}
	}
}
