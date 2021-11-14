package opensource;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class YoutubeScroll {

	public static void main(String[] args) {
		try {
			// Properties
			final String WEB_DRIVER_ID = "webdriver.chrome.driver";
			final String WEB_DRIVER_PATH = "/Users/adam/Documents/chromedriver";

			// System Property SetUp
			System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			// WebDriver
			WebDriver driver = new ChromeDriver();

			driver.get("https://www.youtube.com/results?search_query=%EB%A7%88%EB%A7%88%EB%AC%B4");
			Thread.sleep(5000);
			WebElement body = driver.findElement(By.tagName("body"));

			int num_of_pagedowns = 20;
			for (int i = 0; i < num_of_pagedowns; i = i + 1) {
				body.sendKeys(Keys.PAGE_DOWN);
				Thread.sleep(3000);
				;
			}

			String html = driver.getPageSource();
			System.out.println(html);

		} catch (Exception e) {
			System.out.println("유투브 스크롤 실패");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
