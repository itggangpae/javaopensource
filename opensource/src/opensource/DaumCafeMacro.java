package opensource;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

class SeleniumDaum {
	// WebDriver
	private WebDriver driver;
	// Properties
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "/Users/adam/Documents/chromedriver";

	public SeleniumDaum() {
		super();
		// System Property SetUp
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        //Driver SetUp
        driver = new ChromeDriver();
	}

	public void crawl() {
		try {
			driver.get("https://logins.daum.net/accounts/signinform.do?url=https%3A%2F%2Fwww.daum.net%2F");
			Thread.sleep(10000);
			driver.findElement(By.xpath("//*[@id=\"id\"]")).sendKeys("ggangpae3");
			driver.findElement(By.xpath("//*[@id=\"inputPwd\"]")).sendKeys("cyberadam");
			driver.findElement(By.xpath("//*[@id=\"loginBtn\"]")).click();
			Thread.sleep(10000);
			driver.get("http://cafe.daum.net/samhak7/_memo");
			Thread.sleep(3000);

			//프레임이 있는 경우 프레임으로 이동
			driver.switchTo().frame(driver.findElement(By.id("down")));
			
			driver.findElement(By.xpath("//*[@id=\"primaryContent\"]/div/div[1]/div[2]/div/div/div[1]/textarea")).sendKeys("더조은에서 연습");
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id=\"primaryContent\"]/div/div[1]/div[2]/div/div/div[2]/div[2]/div/button")).click();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// driver.close();
		}
	}
}

public class DaumCafeMacro {
	public static void main(String[] args) {
		SeleniumDaum selTest = new SeleniumDaum();
		selTest.crawl();
	}
}
