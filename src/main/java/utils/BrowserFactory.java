package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/*
@author : Raaghav
 */

public class BrowserFactory {

	static WebDriver driver ;

	public static WebDriver getDriver(String browser) {
		DesiredCapabilities capability = new DesiredCapabilities();
		if ("IE".equalsIgnoreCase(browser)) {
			String iePath = PropertyReader.readConfig(ConfigurationProperties.IE_EXE_PATH);
			iePath = System.getProperty("user.dir") + iePath;
			System.setProperty("webdriver.ie.driver", iePath);
			capability = DesiredCapabilities.internetExplorer();
			capability.setCapability("enablePersistentHover", false);
			capability.setCapability("ignoreZoomSetting", true);
			capability.setCapability("nativeEvents", false);
			capability.setCapability("ignoreProtectedModeSettings", true);
			capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capability.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
			capability.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
			capability.setCapability("ie.ensureCleanSession", true);
			capability.setJavascriptEnabled(true);
			driver = new InternetExplorerDriver(capability);
		} else if ("FIREFOX".equalsIgnoreCase(browser)) {
			capability = DesiredCapabilities.firefox();
			driver = new FirefoxDriver(capability);
		} else if ("CHROME".equalsIgnoreCase(browser)) {
			String chromePath = PropertyReader.readConfig(ConfigurationProperties.CHROME_EXE_PATH);
			chromePath = System.getProperty("user.dir") + chromePath;
			System.setProperty("webdriver.chrome.driver", chromePath);

			ChromeOptions options = new ChromeOptions();
			options.addArguments("--ignore-certificate-errors");
			options.addArguments("--disable-bundled-ppapi-flash");
			options.addArguments("--disable-extensions");
			options.addArguments("--disable-web-security");
			options.addArguments("--always-authorize-plugins");
			options.addArguments("--allow-running-insecure-content");
			options.addArguments("--test-type");
			options.addArguments("--enable-npapi");
			options.addArguments("--disable-extensions");
			options.addArguments("start-maximized");
			options.addArguments("disable-infobars");

			capability = DesiredCapabilities.chrome();
			capability.setJavascriptEnabled(true);
			capability.setCapability(ChromeOptions.CAPABILITY, options);
			capability.setCapability(CapabilityType.TAKES_SCREENSHOT, true);

			driver = new ChromeDriver(capability);

		}
		return driver;
	}
}
