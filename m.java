import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

public class last2021 {
    public static void main(String[] args) {
       
        System.setProperty("webdriver.chrome.driver","C:\\Users\\DELL\\Downloads\\chromedriver-win64 (1)\\chromedriver-win64\\chromedriver.exe");
       
        WebDriver driver = new ChromeDriver();
       
        driver.get("file:///D:/Y III Sem II/SQA/home.html");

        driver.findElement(By.id("fname")).sendKeys("Ravi");
        driver.findElement(By.id("lname")).sendKeys("Kathir");
        driver.findElement(By.id("email")).sendKeys("Ravi@gmail.com");
        driver.findElement(By.id("password")).sendKeys("ravi@12345");
        driver.findElement(By.id("confirm")).sendKeys("ravi@12345");
       
        // RADIO and CHECK BOX
        driver.findElement(By.name("gender")).click();
        driver.findElement(By.name("hobby")).click();
       
        // DROP DOWN
        driver.findElement(By.id("income")).sendKeys("Employed");
        driver.findElement(By.id("age")).sendKeys("30");

        // BIODATA
        driver.findElement(By.id("bio")).sendKeys("A dedicated and enthusiastic university graduate with a background in Computer Science and industry experience");
       
        // 3. SUBMIT
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // 4. Open a new tab
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://www.google.com");

     
       
       // driver.quit();
    }
}