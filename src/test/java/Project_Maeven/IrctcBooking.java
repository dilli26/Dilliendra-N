package Project_Maeven;

import java.util.Date;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class IrctcBooking {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		String date = LocalDate.now().toString();
		System.out.println(date);
		String day = date.split("-")[2];
		String month = date.split("-")[1];
		String year = date.split("-")[0];
		
		WebDriver driver = new ChromeDriver();
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://www.air.irctc.co.in");
		
		if(driver.getCurrentUrl().contains("https://www.air.irctc.co.in")) {
			System.out.println("Website is loaded successfully");
		} else {
			System.out.println("Not loaded");
		}
		
		driver.manage().window().maximize();
		
		if(driver.getCurrentUrl().contains("https://www.air.irctc.co.in")) {
			System.out.println("Website is loaded successfully");
		} else {
			System.out.println("Not loaded");
		}
		
		driver.findElement(By.id("stationFrom")).sendKeys("Hyd");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//ul[@id='ui-id-1']/descendant::div[contains(text(), 'Hyderabad')]")).click();
		
		driver.findElement(By.id("stationTo")).sendKeys("Pune (PNQ)");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//ul[@id='ui-id-2']/descendant::div[contains(text(), 'Pune')]")).click();
//		Thread.sleep(5000);
		
//		driver.findElement(By.xpath("//*[contains(text(), 'Pune (PNQ)')]")).click();
		
	
		driver.findElement(By.xpath("//*[@id=\"carouselExampleInterval\"]/div[1]/div/div/div[2]/form/div[1]/i")).click();
		driver.findElement(By.xpath("//*[@id=\"carouselExampleInterval\"]/div[1]/div/div/div[2]/form/div[1]/i")).click();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.id("originDate")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		List<WebElement> dates = driver.findElements(By.xpath("//div[@class='datepicker datepicker-dropdown datepicker-left datepicker-bottom rdeparturedate no-border']/div[@class='datepicker-days']/table/tbody/descendant::td"));
		
		for(WebElement d : dates) {
			List<WebElement> re = driver.findElements(By.xpath("//div[@class='datepicker datepicker-dropdown datepicker-left datepicker-bottom rdeparturedate no-border']/div[@class='datepicker-days']/table/tbody/descendant::td//span[@class='act']"));
			
			if(d.getText().equals(day)) {
				d.click();
			}
		}
		
		driver.findElement(By.id("noOfpaxEtc")).click();
		Select s = new Select(driver.findElement(By.id("travelClass")));
		s.selectByVisibleText("Business");
		
		driver.findElement(By.xpath("//*[@id=\"carouselExampleInterval\"]/div[1]/div/div/div[2]/form/div[6]/button")).click();
		
		System.out.println(driver.findElements(By.xpath("//div[@class='right-searchbarbtm']")).size());
		
		List<WebElement> flights = driver.findElements(By.xpath("//div[@class='right-searchbarbtm-in']/div[1]/div[2]/b"));
		
		for(WebElement e : flights) {
			System.out.println(e.getText());
		}
		
		WebElement from = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("stationFrom")));
		WebElement to = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("stationTo")));
		WebElement fromCheck = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='right-searchbarbtm-in']/div[2]/span")));
		WebElement toCheck = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='right-searchbarbtm-in']/div[3]/span")));
		
		if(from.getAttribute("value").equals(fromCheck.getText())) {
			System.out.println("From value is valid");
		} else {
			System.out.println("From value is invalid");
		}
		
		if(to.getAttribute("value").equals(toCheck.getText())) {
			System.out.println("To value is valid");
		} else {
			System.out.println("To value is invalid");
		}
		
		WebElement fromDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("originDate")));
//		System.out.println(fromDate.getAttribute("value").split("-")[0] + " " + day);
//		System.out.println(fromDate.getAttribute("value").split("-")[1] + " " + month);
//		System.out.println(fromDate.getAttribute("value").split("-")[2] + " " + year);
		if(fromDate.getAttribute("value").split("-")[0].equals(day) && fromDate.getAttribute("value").split("-")[1].equals(month) && fromDate.getAttribute("value").split("-")[2].equals(year)) {
			System.out.println("Date is valid");
		} else {
			System.out.println("Date is invaid");
		}
		
//		System.out.println(driver.findElement(By.id("stationFrom")).getAttribute("value"));
//		System.out.println(driver.findElement(By.id("stationTo")).getAttribute("value"));
//		System.out.println(driver.findElement(By.xpath("//div[@class='right-searchbarbtm-in']/div[2]/span")).getText());
//		System.out.println(driver.findElement(By.xpath("//div[@class='right-searchbarbtm-in']/div[3]/span")).getText());
	}

}
