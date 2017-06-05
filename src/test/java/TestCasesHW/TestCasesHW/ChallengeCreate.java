package TestCasesHW.TestCasesHW;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ChallengeCreate {

	private WebDriver driver;
	private int register_date, start_date, end_date;
	private String baseUrl;
	private String Result;

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
		baseUrl = "http://live.healthwel.com/web/login";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void Test() throws InterruptedException {
		driver.get(baseUrl);
		driver.findElement(By.id("login-email")).clear();
		driver.findElement(By.id("login-email")).sendKeys("kirit@aimdek.com");
		driver.findElement(By.id("login-pswd")).clear();
		driver.findElement(By.id("login-pswd")).sendKeys("kirit1212");

		WebElement element = driver.findElement(By.xpath("//*[@id='login-form']/div[4]/button"));
		Thread.sleep(1000);
		boolean clicked = false;
		do {
			try {
				element.click();
			} catch (WebDriverException e) {
				continue;
			} finally {
				clicked = true;
			}
		} while (!clicked);

		Result = driver.findElement(By.xpath("//*[@id='navbar']/ul[1]/li[1]/a")).getText();
		Thread.sleep(1000);
		Assert.assertEquals("Dashboard", Result);
		Thread.sleep(3000);
		driver.findElement(By.linkText("Challenges")).click();
		Thread.sleep(1000);
		String Result1 = driver.findElement(By.xpath("//*[@id='navbar']/ul[1]/li[2]/a")).getText();
		Assert.assertEquals("Challenges", Result1);
		Thread.sleep(3000);
		driver.findElement(By.linkText("History")).click();
		Thread.sleep(1000);
		Result = driver.findElement(By.xpath("//*[@id='navbar']/ul[1]/li[3]/a")).getText();
		Assert.assertEquals("History", Result);
		driver.findElement(By.linkText("Friends")).click();
		Thread.sleep(1000);
		Result = driver.findElement(By.xpath("//*[@id='navbar']/ul[1]/li[4]/a")).getText();
		Assert.assertEquals("Friends", Result);
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("p")).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Profile")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("p")).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Change Password")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("newPassword")).clear();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("p")).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Logout")).click();
		Thread.sleep(1000);

	}

	@Test
	public void testCreateChallenge() throws Exception {
		driver.get(baseUrl);
		driver.findElement(By.id("login-email")).clear();
		driver.findElement(By.id("login-email")).sendKeys("juhil@aimdek.com");
		driver.findElement(By.id("login-pswd")).clear();
		driver.findElement(By.id("login-pswd")).sendKeys("juhil123$");
		WebElement element = driver.findElement(By.xpath("//*[@id='login-form']/div[4]/button"));
		Thread.sleep(1000);
		boolean clicked = false;
		do {
			try {
				element.click();
			} catch (WebDriverException e) {
				continue;
			} finally {
				clicked = true;
			}
		} while (!clicked);

		register_date = getCurrentDay() + 1;
		start_date = getCurrentDay() + 3;
		end_date = getCurrentDay() + 5;
		Thread.sleep(1000);
		driver.findElement(By.linkText("Challenges")).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("+ Create New Challenge")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys("only challenge(pbc)");
		
		new Select(driver.findElement(By.id("activityName"))).selectByVisibleText("Walking/Jogging");
		// Thread.sleep(1000);
		driver.findElement(By.id("distancePerDayLiteral")).clear();
		driver.findElement(By.id("distancePerDayLiteral")).sendKeys("1");
		// Thread.sleep(1000);
		new Select(driver.findElement(By.id("unit"))).selectByVisibleText("km");
		// Thread.sleep(1000);
		new Select(driver.findElement(By.id("frequency"))).selectByVisibleText("Everyday");
		// Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='last-registration-date']")).click();
		// #last-registration-date;

		dates(register_date, "3");

		// Thread.sleep(1000);
		Thread.sleep(1000);

		driver.findElement(By.cssSelector("div.ms-options-wrap > button")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id='start-date']")).click();
		dates(start_date, "4");
		System.out.println(start_date);

		driver.findElement(By.xpath("//*[@id='end-date']")).click();
		dates(end_date, "5");

		System.out.println(end_date);
		new Select(driver.findElement(By.name("rank"))).selectByVisibleText("All Finisher");
		Thread.sleep(1000);
		driver.findElement(By.id("prize")).clear();
		driver.findElement(By.id("prize")).sendKeys("2000");
		Thread.sleep(1000);
		driver.findElement(By.id("prize-description")).clear();
		driver.findElement(By.id("prize-description")).sendKeys("don't worry we will give you!");
		driver.findElement(By.cssSelector("div.ms-options-wrap > button")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("ms-opt-1")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//form[@id='challenge-form']/div/div[8]")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("url")).clear();
		driver.findElement(By.id("url")).sendKeys("http://live.healthwel.com/web/login");
		Thread.sleep(1000);
		driver.findElement(By.id("description")).clear();
		driver.findElement(By.id("description")).sendKeys("the challenge for all");
		Thread.sleep(1000);
		driver.findElement(By.id("termsAndConditions")).clear();
		driver.findElement(By.id("termsAndConditions")).sendKeys("No conditions");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("button.btn.save-btn")).click();
		Thread.sleep(2000);

	}

	private int getCurrentDay() {
		// TODO Auto-generated method stub
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

		// Get Current Day as a number
		int todayInt = calendar.get(Calendar.DAY_OF_MONTH);
		System.out.println("Today Int: " + todayInt + "\n");

		// Integer to String Conversion

		return todayInt;
	}

	private void dates(int GetDate, String path) {

		if (GetDate >= 31) {
			int result = GetDate % 30;
			GetDate = result;
		}
		WebElement dateWidgetForm = driver.findElement(By.xpath("html/body/div[" + path + "]/div[1]/table/tbody"));
		List<WebElement> columns = dateWidgetForm.findElements(By.tagName("td"));
		for (WebElement cell : columns) {

			String z = cell.getAttribute("class").toString();
			if (z.equalsIgnoreCase("day")) {
				if (cell.getText().equals(GetDate + "")) {
					cell.click();
					break;
				}
			}
		}
		try {
			Thread.sleep(1000);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// return null;
	}

	@org.testng.annotations.AfterTest
	public void AfterTest() throws Exception {
		driver.quit();
	}
}