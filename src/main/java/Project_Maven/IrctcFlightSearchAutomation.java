package Project_Maven;
import java.io.File;
import java.io.IOException;
import java.util.List;



import java.util.concurrent.TimeUnit;
import java.util.Scanner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


public class IrctcFlightSearchAutomation {
	public static WebDriver driver;
	@SuppressWarnings("deprecation")
//	@SuppressWarnings("resource")
	public static void main(String[] args) throws InterruptedException, IOException {
		
		WebDriverManager.chromedriver().setup();
		WebDriverManager.firefoxdriver().setup();
		Scanner sc = new Scanner(System.in);
		String browserName = sc.nextLine();
		if(browserName.equalsIgnoreCase("chrome")) 
		{
			 driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver","E:\\browsers\\geckodriver-v0.30.0-win64\\geckodriver.exe");
			 driver = new FirefoxDriver();
		}
		
		
		
		// Navigating to the webpage
		driver.get("https://www.air.irctc.co.in/");
		driver.manage().window().maximize();
		// Selecting from city
		WebElement frombox = driver.findElement(By.id("stationFrom"));
		frombox.sendKeys("Hyd");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//div[text()='Hyderabad (HYD)']")).click();
		// Selecting to city
		WebElement toBox = driver.findElement(By.id("stationTo"));
		toBox.sendKeys("Pune");
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS );
		driver.findElement(By.xpath("//div[text()='Pune (PNQ)']")).click();
		// Selecting origin date
		driver.findElement(By.xpath("//*[@id=\"originDate\"]")).click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		// Selecting passengers and class
		driver.findElement(By.xpath("//span[@class='act active-red']")).click();
		driver.findElement(By.id("noOfpaxEtc")).click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		@SuppressWarnings("unused")
		WebElement d = driver.findElement(By.id("travelClass"));
		driver.findElement(By.xpath("//*[@id=\"travelClass\"]")).click();
		driver.findElement(By.xpath("/html/body/app-root/app-index/div[2]/div/div[1]/div/div/div[2]/form/div[5]/div/div[2]/div[1]/select/option[2]")).click();
		driver.findElement(By.xpath("/html/body/app-root/app-index/div[2]/div/div[1]/div/div/div[2]/form/div[6]/button")).click();
		Thread.sleep(5000);

		String msg = driver.findElement(By.xpath("/html/body/app-root/app-oneway/div[1]/main/div/div/div/div[3]/div/div[2]/span")).getText();
		System.out.println("Validating the city");
		System.out.println(msg);
		String msg1 = driver.findElement(By.xpath("/html/body/app-root/app-oneway/div[1]/main/div/div/div/div[3]/div/div[3]/span")).getText();
		System.out.println("Validating the arrival city");
		System.out.println(msg1);
		String msg2= driver.findElement(By.xpath("/html/body/app-root/app-oneway/div[1]/main/div/div/div/div[1]/div/div/div[1]/a/span[1]")).getText();
		System.out.println("Validating the Date");
		System.out.println(msg2);
		
		// Displaying name and number of flights
		List<WebElement> list = driver.findElements(By.xpath("//div[@class='right_Airline_no']"));
		System.out.println("Total flights available for today is"+list.size());
		for (WebElement webElement : list) {
			System.out.println("Name and Number of the available flight is"+webElement.getText());
			
		}
		
		// Capturing screenshot
		TakesScreenshot ts = (TakesScreenshot)driver;
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);
		File destinationFile = new File("C:\\Users\\HP\\eclipse-workspace\\Maven_Irctc\\screenshot\\screenshot.png");
		FileHandler.copy(sourceFile, destinationFile);
		
		// Close the driver
		driver.close();
		
		

		
		
		
		
		
		
	}
	
	

}
