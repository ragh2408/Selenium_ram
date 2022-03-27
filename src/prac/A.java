package prac;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class A {
     @Test
	public static void fetchdata() throws InterruptedException, IOException {
		
		    System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

		    ChromeDriver driver=	   new ChromeDriver();
		    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		    driver.manage().window().maximize();
		    driver.get("http://sellers.snapdeal.com/");
		    driver.findElementById("pgLogin:navbar:txtUserName").sendKeys("turnertools01@gmail.com");
		    driver.findElementById("pgLogin:navbar:txtPassword").sendKeys("turneronline1");
		    driver.findElementByName("pgLogin:navbar:j_id12").click();
		    Thread.sleep(10000);
		    try {
		    	driver.findElementByXPath("//*[@id='skipAction']/span").click();
			    Thread.sleep(1000);
			    driver.findElementByXPath("//li[@id='installLater']").click();
		    }catch(NoSuchElementException e){
		    	
		    }
		    Thread.sleep(5000);
		   
		    try {
		    	WebElement we2=    driver.findElementByXPath("//div[@id='webklipper-publisher-widget-container']//iframe[@name='webklipper-publisher-widget-container-notification-frame']");
				driver.switchTo().frame(we2);
				driver.findElementByXPath("//button[text()='I accept']").click();
		    }catch (NoSuchElementException e1){
		    	e1.printStackTrace();
		    }
		    Thread.sleep(3000);
		    
		  WebElement we=  driver.findElementByXPath("//*[@id='leftNavGroup']/div/div[3]/span[1]");
		    Actions actobj=new Actions(driver);
		    actobj.moveToElement(we).build().perform();
		    Thread.sleep(3000);
		    driver.findElementByXPath("//*[@id='totalInvButton']/a").click();
		    
		    Thread.sleep(10000);
		    try {
		    	driver.findElementByXPath("//*[@id='skipAction']/span").click();
			    Thread.sleep(4000);
			    driver.findElementByXPath("//li[@id='installLater']").click();
		    }catch(NoSuchElementException e){
		    	
		    }
		    Workbook  wbook=new XSSFWorkbook();
		    Sheet sheetobj=wbook.createSheet("Sheet1");
		  List<WebElement>  rowcoll=driver.findElements(By.xpath("//ul[@class='inventory-table']/li"));
		    System.out.println(rowcoll.size());
		    for (int i=0;i<rowcoll.size();i++){
		    	Row rowobj=sheetobj.createRow(i);
		    	List<WebElement>  colcoll=driver.findElements(By.xpath("//ul[@class='inventory-table']/li["+(i+1)+"]/span"));
		    	System.out.println(colcoll.size());
		    	for (int j=0;j<colcoll.size();j++){
		    		Cell cellobj=rowobj.createCell(j);
		    	WebElement we1=	colcoll.get(j);
		    	String txt=we1.getText();
		    	cellobj.setCellValue(txt);
		    	System.out.println(txt);
		    		
		    	}
		    }
		    
		    FileOutputStream fos=new FileOutputStream("E:/classb/ramselclasses/sel4pmold/ram/Project/data.xlsx");
		    wbook.write(fos);
		    
		    
		    
	}

}
