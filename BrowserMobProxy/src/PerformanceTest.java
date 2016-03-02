import java.io.File;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.browsermob.proxy.ProxyServer;
import org.browsermob.core.har.Har;

public class PerformanceTest {
	public static void main(String[] args) throws Exception {
		
		// Start the BrowserMob Proxy
		ProxyServer server = new ProxyServer(9090);
		server.start();
		
		// Get the Selenium proxy object
		Proxy proxy = server.seleniumProxy();
		
		// Configure Desired capability for using Proxy Server
		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		
		
		 System.setProperty("webdriver.firefox.bin", "E:\\Mozilla Firefox\\firefox.exe");  
		  
		capabilities.setCapability(CapabilityType.PROXY, proxy);

		// Start the Browser up
		WebDriver driver = new FirefoxDriver(capabilities);

		// Create a new HAR with the label "StockMarketData"
		server.newHar("StockMarketData");

		// Open the Application
		driver.get("http://money.rediff.com/");

		
	//	navigate to page
		/*WebElement searchTextBox = driver.findElement(By.name("srchword"));
		searchTextBox.sendKeys("Page Industries Ltd.");
		searchTextBox.sendKeys(Keys.ENTER);*/
		
		Thread.sleep(50000);
		
		// Collect the performance data from the BrowserMob proxy server
		// Get the HAR data 
		Har har = server.getHar();

		// Write the HAR Data in a File
		File harFile = new File("E:\\webhar\\rediffmoney.har");
		har.writeTo(harFile);
		
		// Stop the BrowserMob Proxy Server
		server.stop();
		
		// Close the browser
		driver.quit();
		System.out.println("completed making har");
				
	}
}	