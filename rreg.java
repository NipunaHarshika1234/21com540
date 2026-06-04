package RegTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class RegTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\harsh\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("file:///C:/Users/harsh/OneDrive/Desktop/registration.html");
        //Thread.sleep(500000);
        
        //to test the web fields
        driver.findElement(By.name("fn")).sendKeys("ravi");
        driver.findElement(By.id("fn2")).sendKeys("kathir");
        driver.findElement(By.name("pass1")).sendKeys("0123");
        driver.findElement(By.name("pass2")).sendKeys("0123");
        driver.findElement(By.id("email")).sendKeys("test@gmail.com");
        driver.findElement(By.name("mobile")).sendKeys("0771234567");
        driver.findElement(By.id("description")).sendKeys("welcome to selenium testing");
        
        // to check by using web element
        WebElement element = driver.findElement(By.name("fn"));
        element.sendKeys("kamal");
        
        // radio button verification
        WebElement radio1 = driver.findElement(By.id("male"));
        radio1.click();
        
        // checkbox verification
        WebElement option1 = driver.findElement(By.className("automation2"));
        option1.click();

        WebElement option2 = driver.findElement(By.className("automation3"));
        option2.click();
        
        // dropdown verification
        driver.findElement(By.id("country")).sendKeys("Sri Lanka");
        
        //driver.quit();
	}

}
