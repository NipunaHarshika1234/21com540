package user_register;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.io .File;

public class user_registration {

    public static void main(String[] args) {
        
        // 1. Open the Chrome Browser
        // Note: Modern Selenium (v4.x+) manages the ChromeDriver driver executable automatically.
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        try {
            // 2. Enter the URL and navigate to "home.html" page
            // Replace this path with the actual local absolute path to your home.html file
            String localHtmlPath = "file:///C:/path/to/your/folder/home.html"; 
            driver.get(localHtmlPath);

            // 3. Enter first name value as "Ava Bella"
            WebElement firstName = driver.findElement(By.id("firstName")); // Adjust locator if needed
            firstName.sendKeys("Ava Bella");

            // 4. Enter last name value as "Willson"
            WebElement lastName = driver.findElement(By.id("lastName"));
            lastName.sendKeys("Willson");

            // 5. Enter email value as "Bella@gmail.com"
            WebElement email = driver.findElement(By.id("email"));
            email.sendKeys("Bella@gmail.com");

            // 6. Enter password value as "ava@2024"
            WebElement passwordField = driver.findElement(By.id("password"));
            String passValue = "ava@2024";
            passwordField.sendKeys(passValue);

            // 7. Enter confirm password field value as "ava@2024"
            WebElement confirmPasswordField = driver.findElement(By.id("confirmPassword"));
            String confirmPassValue = "ava@2024";
            confirmPasswordField.sendKeys(confirmPassValue);

            // 7.a. Check password and confirm password conditions
            if (passValue.isEmpty()) {
                System.out.println("Password field is Empty");
            }
            if (confirmPassValue.isEmpty()) {
                System.out.println("Confirm Password field is Empty");
            }

            // 7.b. Check if password and confirm password are same
            if (passValue.equals(confirmPassValue)) {
                System.out.println("Passwords are Equal");
            } else {
                System.out.println("Passwords are not Equal");
            }

            // 8. Select your gender name as "Female" (Assuming a radio button layout)
            WebElement genderFemale = driver.findElement(By.xpath("//input[@value='Female']"));
            genderFemale.click();

            // 9. Select your hobbies as "Travel" (Assuming a checkbox layout)
            WebElement hobbyTravel = driver.findElement(By.xpath("//input[@value='Travel']"));
            if (!hobbyTravel.isSelected()) {
                hobbyTravel.click();
            }

            // 10. Select your Source of Income as "Self-employed" (Assuming a dropdown menu)
            WebElement incomeSourceDropdown = driver.findElement(By.id("sourceOfIncome"));
            Select selectIncome = new Select(incomeSourceDropdown);
            selectIncome.selectByVisibleText("Self-employed");

            // 11. Set your income as "65K" (Could be a slider, dropdown, or text field. Handled as text here)
            WebElement incomeField = driver.findElement(By.id("income"));
            incomeField.sendKeys("65K");

            // 12. Choose an image "sqa.png" from registration folder
            // Dynamically building absolute path from a local "Registration" project folder
            String projectPath = System.getProperty("user.dir");
            String imagePath = projectPath + File.separator + "Registration" + File.separator + "sqa.png";
            
            // 12.a. Validation condition for image data
            File imageFile = new File(imagePath);
            if (!imageFile.exists() || imageFile.length() == 0) {
                System.out.println("Select image data");
            } else {
                // Condition: Selenium code selects the image file via sendKeys on the file input
                WebElement fileInput = driver.findElement(By.id("imageUpload"));
                fileInput.sendKeys(imagePath);
            }

            // 13. Select your Age as "30" (Assuming a dropdown sequence)
            WebElement ageDropdown = driver.findElement(By.id("age"));
            Select selectAge = new Select(ageDropdown);
            selectAge.selectByVisibleText("30");

            // 14. Type biodata detailed string
            WebElement bioDataField = driver.findElement(By.id("biodata"));
            bioDataField.sendKeys("Bella is a software engineer with extensive experience and management skill and works for hi-tech telecommunication company");

            // 15. Open a new tab and search https://www.yahoo.com/
            driver.switchTo().newWindow(WindowType.TAB);
            driver.get("https://www.yahoo.com/");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Optional: Keeps browser alive for validation. Uncomment below to auto-close.
            // driver.quit();
        }
    }
}