package detail_form;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.time.Duration;

public class detail_form {

    public static void main(String[] args) {
        // Setup Chrome Driver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            // Setup timeouts and maximize window
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            
            // Open the given index.html file (Change path to match your local setup)
            driver.get("file:///C:/Source_Data_CS3212/index.html");
            driver.manage().window().maximize();

            // =================================================================
            // i to vii. Text Field Inputs
            // =================================================================
            // i. Enter first name
            driver.findElement(By.id("first_name")).sendKeys("Joseph Vijay"); // Change element IDs if different

            // ii. Enter last name
            driver.findElement(By.id("last_name")).sendKeys("Chandrasekhar");

            // iii. Enter date of birth
            driver.findElement(By.id("dob")).sendKeys("10062000"); // Standard input injection format

            // iv. Enter age
            driver.findElement(By.id("age")).sendKeys("22");

            // v. Enter email address
            driver.findElement(By.id("email")).sendKeys("vijay@yahoo.com");

            // vi. Enter phone number
            driver.findElement(By.id("phone")).sendKeys("+9461 768 6547");

            // vii. Enter address
            driver.findElement(By.id("address")).sendKeys("64, Kaveri Street, Saligramam, Chennai 600093, Tamil Nadu, and India.");


            // =================================================================
            // viii. Select Gender = Male (Radio Button)
            // =================================================================
            WebElement maleRadio = driver.findElement(By.xpath("//input[@value='Male']"));
            if (!maleRadio.isSelected()) {
                maleRadio.click();
            }


            // =================================================================
            // ix. Select Hobbies: Drawing and Dancing (Checkboxes)
            // =================================================================
            WebElement drawingCheck = driver.findElement(By.xpath("//input[@value='Drawing']"));
            WebElement dancingCheck = driver.findElement(By.xpath("//input[@value='Dancing']"));

            if (!drawingCheck.isSelected()) drawingCheck.click();
            if (!dancingCheck.isSelected()) dancingCheck.click();


            // =================================================================
            // x. Select Course = B.Tech (Dropdown Select Option)
            // =================================================================
            WebElement courseDropdown = driver.findElement(By.id("course"));
            Select selectCourse = new Select(courseDropdown);
            selectCourse.selectByVisibleText("B.Tech");


            // =================================================================
            // xi & xii. Enter Passwords and Conditional Check
            // =================================================================
            String passValue = "vijay@123456";
            String retypePassValue = "vijay@123456";

            driver.findElement(By.id("password")).sendKeys(passValue);
            driver.findElement(By.id("retype_password")).sendKeys(retypePassValue);

            // Conditional block validation requested in the second image
            if (passValue.equals(retypePassValue)) {
                System.out.println("passwords are equal");
            } else {
                System.out.println("passwords are not equal");
            }


            // =================================================================
            // xiii. Fill the Qualification Table
            // =================================================================
            // targets the text inputs inside your dynamic web form table rows
            driver.findElement(By.id("table_results")).sendKeys("2A and B");
            driver.findElement(By.id("table_zscore")).sendKeys("1.225");
            driver.findElement(By.id("table_rank")).sendKeys("1");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close browser execution context
            driver.quit();
        }
    }
}