package week4.day2.assignement;

	import java.io.File;
	import java.io.IOException;
	import java.time.Duration;
	import java.util.List;

	import org.apache.commons.io.FileUtils;
	import org.openqa.selenium.By;
	import org.openqa.selenium.OutputType;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.chrome.ChromeOptions;
	import org.openqa.selenium.interactions.Actions;

	import io.github.bonigarcia.wdm.WebDriverManager;

	public class SnapdealAssignment {
		public static void main(String[] args) throws InterruptedException, IOException {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			ChromeDriver driver = new ChromeDriver(options);

			// 1. Launch https://www.snapdeal.com/
			driver.get("https://www.snapdeal.com/");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

			// 2. Go to Mens Fashion
			driver.findElement(By.xpath("(//span[@class='catText'])[1]")).click();
			Thread.sleep(2000);

			// 3. Go to Sports Shoes
			driver.findElement(By.xpath("//div[@class='colDataInnerBlk']/p[2]//span[text()='Sports Shoes']")).click();
			Thread.sleep(2000);

			// 4. Get the count of the sports shoes
			String count = driver.findElement(By.xpath("(//div[@class='child-cat-count '])[1]")).getText();
			System.out.println("The Count of the Sport Shoes are " + count);

			// 5. Click Training shoes
			driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
			Thread.sleep(2000);

			// 6. Sort by Low to High
			driver.findElement(By.xpath("//div[@class='sort-drop clearfix']")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath("//div[@class='sort-drop clearfix']/following-sibling::ul/li[@data-index='1']"))
					.click();
			Thread.sleep(2000);

			// 7. Check if the items displayed are sorted correctly
			List<WebElement> priceElements = driver.findElements(By.xpath("//span[@class='lfloat product-price']"));
			String priceFirst = priceElements.get(0).getAttribute("data-price");
			String priceLast = priceElements.get(priceElements.size() - 1).getAttribute("data-price");

			if (Integer.parseInt(priceFirst) < Integer.parseInt(priceLast)) {
				System.out.println("The Items are Sorted with Price Low To High. Hence VALIDATION SUCCESS");
			} else {
				System.out.println("The Items are not Sorted in Correct Order. Hence VALIDATION FAILED");
			}
			Thread.sleep(2000);

			// 8.Select the price range (900-1200)
			driver.findElement(By.xpath("//input[@name='fromVal']")).clear();
			driver.findElement(By.xpath("//input[@name='fromVal']")).sendKeys("900");
			driver.findElement(By.xpath("//input[@name='toVal']")).clear();
			driver.findElement(By.xpath("//input[@name='toVal']")).sendKeys("1200");
			driver.findElement(By.xpath("//div[contains(@class,'price-go-arrow')]")).click();
			Thread.sleep(2000);

			// 9. Filter with color Navy
			driver.findElement(By.xpath("//div[@data-name='Color_s']/following-sibling::button[contains(text(),'View More')]"))
					.click();
			WebElement navyColor = driver.findElement(By.xpath("//label[@for='Color_s-Navy']/preceding-sibling::input"));
			boolean navyFlag = navyColor.isEnabled();
			if (navyFlag) {
				navyColor.click();
			} else {
				System.out.println("Navy Color is Disabled, Hence Navy color cannot be selected for filter.");
			}
			Thread.sleep(2000);

			// 10. verify the all applied filters
			String filteredPrice = driver.findElement(By.xpath("(//div[@class='filters']/div)[1]/a")).getText();
			String filteredColour = driver.findElement(By.xpath("(//div[@class='filters']/div)[2]/a")).getText();
			if ((filteredPrice.equals("Rs. 900 - Rs. 1200")) && (filteredColour.equals("Navy") || navyFlag == false)) {
				System.out.println("The Filters are Applied Correctly. Hence VALIDATION SUCCESS.");
			} else {
				System.out.println("The Filters are not Applied Correctly. Hence VALIDATION FAILED.");
			}

			// 11. Mouse Hover on first resulting Training shoes
			WebElement firstTrainingShoe = driver.findElement(By.xpath("(//picture[@class='picture-elem']/img)[1]"));
			WebElement quickView = driver.findElement(By.xpath(
					"(//picture[@class='picture-elem']/img)[1]//ancestor::a/following-sibling::div[@class='clearfix row-disc']/div[contains(text(),'Quick View')]"));
			Actions builder = new Actions(driver);
			builder.moveToElement(firstTrainingShoe).pause(1000).perform();

			// 12. click QuickView button
			builder.click(quickView).perform();

			// 13. Print the cost and the discount percentage
			String shoeName = driver.findElement(By.xpath("//div[@class='quickViewHead']")).getText();
			String priceValue = driver.findElement(By.xpath("//div[contains(@class,'product-price')]/span[1]")).getText();
			String[] price = priceValue.split("&");
			String discountValue = driver.findElement(By.xpath("//div[contains(@class,'product-price')]/span[2]"))
					.getText();
			String[] discount = discountValue.split(" ");
			System.out.println("The Price of the Shoe " + shoeName + " is RS: " + price[0]);
			System.out.println("The Discount of the Shoe " + shoeName + " is " + discount[0]);
			Thread.sleep(2000);

			// 14. Take the snapshot of the shoes
			File source = driver.getScreenshotAs(OutputType.FILE);
			File destination = new File("./screenshots/shoe.png");
			FileUtils.copyFile(source, destination);

			// 15. Close the current window
			driver.findElement(By.xpath("//div[@class='close close1 marR10']/i[contains(@class,'icon-delete-sign')]"))
					.click();

			// 16.Close the main window
			driver.close();
		}
	}


