package TestCasesHW.TestCasesHW;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class SignUpTest {
	private WebDriver driver;
	WebElement datePicker;
	 List<WebElement> noOfColumns;
	 List<String> monthList = Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
	
	 int expMonth;
	 int expYear;
	 String expDate = null;	
	 String calMonth = null;
	 String calYear = null;
	 boolean dateNotFound;
	 private String baseUrl;
	 private String FristName = "kirit";
	 private String LastName = "Thakrar";
	 private String Email = "kt1123@gmail.com";
	 private String Password = "pass@123";
	 private String Weight = "50";
	 private String Height = "150";
	 private String Gender = "gender1";
				  

	  @BeforeClass(alwaysRun = true)
	  public void setUp() throws Exception {
		
		  /* ClassLoader classLoader = getClass().getClassLoader();
		    String path  = classLoader.getResource("geckodriver.exe").getPath();
		    System.setProperty("webdriver.gecko.driver", path);
		    driver = new FirefoxDriver();*/
			
			ClassLoader classLoader = getClass().getClassLoader();
			String path  = classLoader.getResource("chromedriver.exe").getPath();
			System.setProperty("webdriver.chrome.driver", path);
			//driver = new ChromeDriver();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			driver = new ChromeDriver( options );
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			baseUrl = "http://live.healthwel.com/web/regular-signup";
			driver.get(baseUrl);
			 
		
			
	 }

	  @Test
	  public void testSignup() throws Exception {
	   
	    
	    expDate = "18";
	    expMonth= 8;
	    expYear = 2013;
	   
		 
	    driver.findElement(By.id("firstName")).sendKeys(FristName);
	   	Thread.sleep(3000); 
	   	driver.findElement(By.id("lastName")).sendKeys(LastName);
	   	Thread.sleep(3000);
	   	driver.findElement(By.id("email")).sendKeys(Email);
	   	Thread.sleep(3000);
	   	driver.findElement(By.id("password")).sendKeys(Password);
	   	Thread.sleep(3000);
	   	driver.findElement(By.id("weightLiteral")).sendKeys(Weight);
	   	Thread.sleep(3000);
	    driver.findElement(By.id("heightLiteral")).sendKeys(Height);
	    Thread.sleep(3000);
	    driver.findElement(By.id(Gender)).click();
	   
	    
	    Thread.sleep(3000);
	    driver.findElement(By.xpath("//*[@id='bday']")).click();
	    dateNotFound = true;
	    while(dateNotFound)
	    { 
	     //Retrieve current selected month name from date picker popup.
	     String monYear= driver.findElement(By.xpath("/html/body/div[2]/div[1]/table/thead/tr[1]/th[2]")).getText();
	     String [] data=monYear.split(" ");
	     calMonth=data[0];
	     //Retrieve current selected year name from date picker popup.
	     calYear = data[1];
	     
	     //If current selected month and year are same as expected month and year then go Inside this condition.
	     if(monthList.indexOf(calMonth)+1 == expMonth && (expYear == Integer.parseInt(calYear)))
	     {
	      //Call selectDate function with date to select and set dateNotFound flag to false.
	      selectDate(expDate);
	      dateNotFound = false;
	     }
	     //If current selected month and year are less than expected month and year then go Inside this condition.
	     else if(monthList.indexOf(calMonth)+1 < expMonth && (expYear == Integer.parseInt(calYear)) || expYear > Integer.parseInt(calYear))
	     {
	      //Click on next button of date picker.
	      driver.findElement(By.xpath("/html/body/div[2]/div[1]/table/thead/tr[1]/th[3]")).click();
	     }
	     //If current selected month and year are greater than expected month and year then go Inside this condition.
	     else if(monthList.indexOf(calMonth)+1 > expMonth && (expYear == Integer.parseInt(calYear)) || expYear < Integer.parseInt(calYear))
	     {
	      //Click on previous button of date picker.
	      driver.findElement(By.xpath("/html/body/div[2]/div[1]/table/thead/tr[1]/th[1]")).click();
	     }
	    }
	    Thread.sleep(5000);
	    
	    
	    
	    driver.findElement(By.xpath("//*[@id='sign-up-form']/div[2]/div[2]/button")).click();
	    Thread.sleep(3000);
	   
	    driver.findElement(By.id("login-email")).sendKeys(Email);
	    driver.findElement(By.id("login-pswd")).sendKeys(Password);
	 
	    WebElement element= driver.findElement(By.xpath("//*[@id='login-form']/div[4]/button"));
        Thread.sleep(3000);

       boolean clicked = false;
        do{
            try {
                element.click();
            } catch (WebDriverException e) {
                continue;
            } finally {
                clicked = true;
            }
        } while (!clicked);
        Thread.sleep(3000);
        driver.get("http://live.healthwel.com/web/profile");
        Thread.sleep(3000);
        WebElement fname = driver.findElement(By.xpath("//*[@id='firstName']"));
        String fnm=fname.getAttribute("value");
       
       
       System.out.println(fname);
       Assert.assertEquals(FristName,fnm);

	 }
	  
	  @AfterClass(alwaysRun = true)
	  public void tearDown() throws Exception {
	    driver.quit();
	   
	  }
	 public void selectDate(String date)
	  {
	   datePicker = driver.findElement(By.xpath("/html/body/div[2]/div[1]/table/tbody")); 
	   noOfColumns=datePicker.findElements(By.tagName("td"));

	   //Loop will rotate till expected date not found.
	   for (WebElement cell: noOfColumns){
	    //Select the date from date picker when condition match.
	    if (cell.getText().equals(date)){
	     cell.click();
	     break;
	    }
	   }
	  } 
}
