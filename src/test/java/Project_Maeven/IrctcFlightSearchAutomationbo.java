	package Project_Maeven;

import org.testng.annotations.Test;

import ch.qos.logback.core.joran.action.Action;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;

public class IrctcFlightSearchAutomationbo {
	WebDriver driver;

	@SuppressWarnings({ "deprecation", "resource" })
	@Test
	public void test() throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		WebDriverManager.firefoxdriver().setup();
		String browserName = new Scanner(System.in).nextLine();
		if(browserName.equalsIgnoreCase("chrome")) 
		{
			 driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			 driver = new FirefoxDriver();
		}
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
		// Selecting origin 
		Actions act = new Actions(driver);
		WebElement inElement = driver.findElement(By.xpath("//input[@id='originDate']"));
		
		act.moveToElement(inElement).click().build().perform();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		// Selecting passengers and class
		driver.findElement(By.xpath("//span[@class='act active-red']")).click();
		driver.findElement(By.id("noOfpaxEtc")).click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		//WebElement d = driver.findElement(By.id("travelClass"));
		//driver.findElement(By.xpath("//*[@id=\"travelClass\"]")).click();
		Select classe = new Select(driver.findElement(By.id("travelClass")));
    	        classe.selectByVisibleText("Business");
    	        driver.findElement(By.id("noOfpaxEtc")).click();
    	        driver.findElement(By.id("noOfpaxEtc")).click();
		//driver.findElement(By.xpath("/html/body/app-root/app-index/div[2]/div/div[1]/div/div/div[2]/form/div[5]/div/div[2]/div[1]/select/option[2]")).click();
		driver.findElement(By.xpath("/html/body/app-root/app-index/div[2]/div/div[1]/div/div/div[2]/form/div[6]/button")).click();
		Thread.sleep(5000);
		//Validating city and date
		String msg = driver.findElement(By.xpath("/html/body/app-root/app-oneway/div[1]/main/div/div/div/div[3]/div/div[2]/span")).getText();
		System.out.println("Validating the Departure city");
		System.out.println(msg);
		String msg1 = driver.findElement(By.xpath("/html/body/app-root/app-oneway/div[1]/main/div/div/div/div[3]/div/div[3]/span")).getText();
		System.out.println("Validating the arrival city");
		System.out.println(msg1);
		String msg2= driver.findElement(By.xpath("//html/body/app-root/app-oneway/div[1]/main/div/div/div/div[1]/div/div/div[1]/a/span[1]")).getText();
		System.out.println("Validating the Date");
		System.out.println(msg2);
		// Displaying name and number of flights
		List<WebElement> plane = driver.findElements(By.xpath("//div[@class='right-searchbarbtm-in']/div/div[2]/b"));
    	        List<WebElement> no = driver.findElements(By.xpath("//div[@class='right-searchbarbtm-in']/div/div[2]/span"));
    	        System.out.println("Total number of flights available are: "+plane.size());
    	        for(int i=0;i<plane.size();i++){
			   System.out.println(plane.get(i).getText()+" - "+no.get(i).getText());
		}	
					
				
        // Taking Screenshot
		TakesScreenshot ts = (TakesScreenshot)driver;
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);
		File destinationFile = new File("C:\\Users\\HP\\eclipse-workspace\\Maven_Irctc\\screenshot\\screenshot.png");
		FileHandler.copy(sourceFile, destinationFile);
		//Closing the driver
		Thread.sleep(3000);
		driver.close();
	}

}
