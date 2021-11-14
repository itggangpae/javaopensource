package opensource;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class NaverCafeMacro {

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
		String id = "ggangpae11";
		String password = "cyberadam";
		
		driver.get("https://nid.naver.com/nidlogin.login");
		Thread.sleep(10000);
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("document.getElementsByName('id')[0].value=\'" + id + "\'");
		js.executeScript("document.getElementsByName('pw')[0].value=\'" + password + "\'");
		
		driver.findElement(By.xpath("//*[@id=\"log.login\"]")).click();

		
		Thread.sleep(10000);
		driver.get("https://cafe.naver.com/joonggonara");

		
		Thread.sleep(10000);
		driver.get("https://cafe.naver.com/joonggonara/617588810");
		Thread.sleep(3000);
		driver.switchTo().frame(driver.findElement(By.id("cafe_main")));
		
		String reply = "매크로를 이용한 댓글";
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div[2]/div[5]/div[2]/div[1]/textarea")).sendKeys(reply);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div[2]/div[5]/div[2]/div[2]/div[2]/a")).click();
		}catch(Exception e) {
			System.out.println("댓글 작성 에러");
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
