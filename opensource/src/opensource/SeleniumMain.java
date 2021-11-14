package opensource;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

class SeleniumTest{
	// WebDriver
	private WebDriver driver;
	// Properties
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	//자신의 크롬 드라이버 위치로 설정
	public static final String WEB_DRIVER_PATH = "/Users/adam/Documents/chromedriver";
	// 크롤링 할 URL
	private String base_url;
	public SeleniumTest() {
        super();
        //System Property SetUp
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
        
        //Driver SetUp
        driver = new ChromeDriver();
        base_url = "https://www.naver.com";
    }
	public void crawl() {
		try {
			// get page (= 브라우저에서 url을 주소창에 넣은 후 request 한 것과 같다)
			driver.get(base_url);
			System.out.println(driver.getPageSource());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//driver.close();
		}
	}
}

public class SeleniumMain {
	public static void main(String[] args) {
		SeleniumTest selTest = new SeleniumTest();
		selTest.crawl();
	}
}
