package com.qsp.basics;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

public class Scenario {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./Softwares/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://www.google.com");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys("Keerthy Suresh");
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,500)");
		driver.findElement(By.xpath("(//input[@name='btnK'])[1]")).click();
		List<WebElement> all_links = driver.findElements(By.xpath("//h3"));

		//count
		int count=all_links.size();
		System.out.println("Total No. of Links: "+count);

		//click on the particular element link
		for (int i = 0; i < all_links.size(); i++) 
		{
			WebElement link = all_links.get(i);
			if (i==7) 
			{
				System.out.println("7th link is: "+link.getText());
				link.click();
				Thread.sleep(3000);
			}
		}

		//take screenshot
		TakesScreenshot ts=(TakesScreenshot) driver; 
		File src=ts.getScreenshotAs(OutputType.FILE); 
		File dst=new File("./screenshot/b.jpeg"); 
		try 
		{ 
			FileHandler.copy(src, dst); 
		} 
		catch(IOException e) 
		{ 
			e.printStackTrace(); 
		}
		Thread.sleep(2000);
		driver.quit();
	}
}
