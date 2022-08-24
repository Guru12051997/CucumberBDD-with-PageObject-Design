package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	public WebDriver driver;

	public WebDriver WebDriverManager() throws IOException {
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//test//resources//global.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String url = prop.getProperty("QAUrl");

		if (driver == null) {
			if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {
				// WebDriverManager.chromedriver().setup();
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "//src//test//resources//chromedriver.exe");
				driver = new ChromeDriver();
			}
			if (prop.getProperty("browser").equalsIgnoreCase("firefox")) {
				//WebDriverManager.firefoxdriver().setup();
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "//src//test//resources//geckodriver.exe");
				driver = new FirefoxDriver();
			}
			if (prop.getProperty("browser").equalsIgnoreCase("IE")) {
				//WebDriverManager.firefoxdriver().setup();
				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "//src//test//resources//IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
			driver.get(url);

		}
		return driver;
	}
}