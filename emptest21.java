package Employee_Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.time.Duration;

public class Employee_Test {

    // Define a static global WebDriver instance to access across all methods safely
    public static WebDriver driver;

    // 9. Call all the above created methods inside the main method
    public static void main(String[] args) {
        try {
            // Step 1: Initialize browser
            openBrowser("http://localhost/EmployeeUser/login.php"); // Adjust to your actual local web path

            // Step 2 & 3: Run login logic with validation
            adminLogin("admin@gmail.com", "admin@12345");

            // Step 4: Validate navigation URL
            validateUserSideMenuUrl("http://localhost/EmployeeUser/dashboard.php"); // Adjust expected URL

            // Step 5: Handle Show Entries dropdown selection
            selectShowEntriesByIndex(2); // Selects the 3rd option (index tracking starts at 0)

            // Step 6: Perform user search
            searchUser("Ad");

            // Step 7: Add a new user and capture alert response
            addNewUser("ravi@gmail.com", "ravi@12345", "ravi", "kathir");

            // Step 8: Log out
            logoutWebsite();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Terminate browser execution context cleanly
            if (driver != null) {
                driver.quit();
            }
        }
    }

    // 1. Open the Chrome Browser and navigate to the "Admin Login Page".
    public static void openBrowser(String url) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(url);
        driver.manage().window().maximize();
    }

    // 2 & 3. Pass the values into admin login fields & Click login button with conditions
    public static void adminLogin(String username, String password) {
        if (username == null || username.isEmpty()) {
            System.out.println("username is empty");
            return;
        } 
        if (password == null || password.isEmpty()) {
            System.out.println("password is empty");
            return;
        }

        // Otherwise login execution path
        driver.findElement(By.id("username")).sendKeys(username); // Replace locators with actual form IDs
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login_btn")).click();
    }

    // 4. Click the user-side menu and should check the URL validation
    public static void validateUserSideMenuUrl(String expectedUrl) {
        driver.findElement(By.id("user_side_menu")).click(); // Replace with actual element locator
        
        String currentUrl = driver.getCurrentUrl();
        if (currentUrl.equals(expectedUrl)) {
            System.out.println("URL Validation Success!");
        } else {
            System.out.println("URL Validation Failed!");
        }
    }

    // 5. Click the show entries dropdown and print the selected value in console.
    public static void selectShowEntriesByIndex(int index) {
        WebElement dropdownElement = driver.findElement(By.name("show_entries_length")); // Replace locator
        Select dropdown = new Select(dropdownElement);
        
        // Condition: Select option only by an index
        dropdown.selectByIndex(index);
        
        // Get the chosen visible value text to print out
        String selectedOptionText = dropdown.getFirstSelectedOption().getText();
        System.out.println("The Selected Option is : " + selectedOptionText);
    }

    // 6. Search the user (Search value is - "Ad")
    public static void searchUser(String searchValue) {
        WebElement searchBox = driver.findElement(By.id("search_box")); // Replace locator
        searchBox.clear();
        searchBox.sendKeys(searchValue);
    }

    // 7. Add a new user & handle confirmation pop-ups
    public static void addNewUser(String email, String password, String firstName, String lastName) {
        driver.findElement(By.id("add_user_btn")).click(); // Replace locator

        driver.findElement(By.id("user_email")).sendKeys(email);
        driver.findElement(By.id("user_password")).sendKeys(password);
        driver.findElement(By.id("user_firstname")).sendKeys(firstName);
        driver.findElement(By.id("user_lastname")).sendKeys(lastName);
        
        driver.findElement(By.id("save_btn")).click();

        // Condition: Close the alert box after successfully creates & Print text content
        Alert alert = driver.switchTo().alert();
        System.out.println("User Saving Alert Message is : " + alert.getText());
        alert.accept(); 
    }

    // 8. Logout from the website.
    public static void logoutWebsite() {
        driver.findElement(By.id("logout_btn")).click(); // Replace locator
        System.out.println("Logged out from the website successfully.");
    }
}
