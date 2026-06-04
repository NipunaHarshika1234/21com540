package CA01;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.time.Duration;

public class CA01 {

    public static void main(String[] args) {
        // Setup Chrome Driver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            // =================================================================
            // a. Open the given HTML file in a browser & b. Maximize window
            // =================================================================
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            
            // REPLACE THIS PATH with the actual location of your practical HTML file
            driver.get("file:///C:/path/to/your/file.html"); 
            driver.manage().window().maximize();

            // =================================================================
            // c, d, e, f. Locate and fill text input fields
            // =================================================================
            // c. Locate Full Name and enter "Test User"
            WebElement nameField = driver.findElement(By.id("name")); // Replace locators if needed
            nameField.sendKeys("Test User");

            // d. Locate Email and enter "test@gmail.com"
            driver.findElement(By.id("email")).sendKeys("test@gmail.com");

            // e. Locate Password and enter "Test@123"
            driver.findElement(By.id("password")).sendKeys("Test@123");

            // f. Locate Mobile Number and enter "0765643212"
            driver.findElement(By.id("mobile")).sendKeys("0765643212");

            // =================================================================
            // g. Select Gender = Female (Radio Button)
            // =================================================================
            WebElement femaleRadio = driver.findElement(By.xpath("//input[@value='Female']")); 
            if (!femaleRadio.isSelected()) {
                femaleRadio.click();
            }

            // =================================================================
            // h. Select Hobbies: Reading, Music (Checkboxes)
            // =================================================================
            WebElement readingCheckbox = driver.findElement(By.xpath("//input[@value='Reading']"));
            WebElement musicCheckbox = driver.findElement(By.xpath("//input[@value='Music']"));

            if (!readingCheckbox.isSelected()) readingCheckbox.click();
            if (!musicCheckbox.isSelected()) musicCheckbox.click();

            // =================================================================
            // i. Select Country = Sri Lanka (Dropdown List)
            // =================================================================
            WebElement countryDropdown = driver.findElement(By.id("country"));
            Select selectCountry = new Select(countryDropdown);
            selectCountry.selectByVisibleText("Sri Lanka");

            // =================================================================
            // j. Set Experience slider to value 8
            // =================================================================
            // Modifying a slider range component cleanly using keyboard arrows
            WebElement slider = driver.findElement(By.id("experience"));
            // Reset to 0 first (pressing left arrow repeatedly), then hit right arrow 8 times
            for (int i = 0; i < 10; i++) {
                slider.sendKeys(Keys.ARROW_LEFT);
            }
            for (int i = 0; i < 8; i++) {
                slider.sendKeys(Keys.ARROW_RIGHT);
            }

            // =================================================================
            // k. Select Date of Birth as: 2000-01-01
            // =================================================================
            WebElement dobField = driver.findElement(By.id("dob"));
            dobField.sendKeys("01012000"); // Format depends on browser locale (MMDYYYY or DDMMYYYY)

            // =================================================================
            // l. Click the submit button
            // =================================================================
            WebElement submitButton = driver.findElement(By.id("submit_btn"));
            submitButton.click();

            // =================================================================
            // m. Clear Name field, click Submit, and Capture validation message
            // =================================================================
            nameField.clear();
            submitButton.click();

            // Capture HTML5 browser validation message (e.g., "Please fill out this field.")
            String validationMessage = nameField.getAttribute("validationMessage");
            System.out.println("Validation message is: " + validationMessage);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser session
            driver.quit();
        }
    }
}
