import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class student_test {

    public static void main(String[] args) {
        // =================================================================
        // a. Launch the Web Application
        // =================================================================
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        // Set timeouts and maximize window
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        // Open the local web page (Adjust URL to match your server path)
        driver.get("http://localhost/student/index.php");
        driver.manage().window().maximize();


        // =================================================================
        // b. Admin Login Automation & Validation
        // =================================================================
        // Click the Admin Login option
        driver.findElement(By.linkText("Admin Login")).click();

        String username = "admin";
        String password = "admin@123";

        // Implement requested text validations
        if (username.isEmpty()) {
            System.out.println("username is empty");
        } else if (password.isEmpty()) {
            System.out.println("password is empty");
        } else {
            // Enter login credentials and click login button
            driver.findElement(By.id("username")).sendKeys(username);
            driver.findElement(By.id("password")).sendKeys(password);
            driver.findElement(By.id("login_btn")).click();
        }


        // =================================================================
        // c. Student Menu Navigation and URL Validation
        // =================================================================
        // Click the Student-side menu
        driver.findElement(By.id("student_menu_nav")).click();

        // Validate the resulting URL
        String expectedUrl = "http://localhost/student/students_list.php";
        if (driver.getCurrentUrl().equals(expectedUrl)) {
            System.out.println("URL Validation Success");
        } else {
            System.out.println("URL Validation Fail");
        }


        // =================================================================
        // d. Search for a Student
        // =================================================================
        // Locate search field, clear old input, and search for "Ad"
        WebElement searchField = driver.findElement(By.id("search_input"));
        searchField.clear();
        searchField.sendKeys("Ad");


        // =================================================================
        // e. Add a New Student
        // =================================================================
        // Click "Add Student" option/button
        driver.findElement(By.id("add_new_student_btn")).click();

        // Fill out student form details
        driver.findElement(By.id("first_name")).sendKeys("Stanely");
        driver.findElement(By.id("last_name")).sendKeys("Lambert");
        driver.findElement(By.id("grade")).sendKeys("Grade 11");
        driver.findElement(By.id("phone_number")).sendKeys("0777889090");
        
        // Submit the form
        driver.findElement(By.id("save_student_btn")).click();

        // Handle success alert box
        Alert saveAlert = driver.switchTo().alert();
        System.out.println("Student Saving Alert Message is: " + saveAlert.getText());
        saveAlert.accept();


        // =================================================================
        // f. Delete a Student
        // =================================================================
        // Click delete on the very first record in the student list table
        driver.findElement(By.xpath("(//button[contains(@class,'delete-btn')])[1]")).click();

        // Handle confirmation alert box using the Cancel/Dismiss option
        Alert deleteAlert = driver.switchTo().alert();
        System.out.println("Student Deleting Alert Message is: " + deleteAlert.getText());
        deleteAlert.dismiss(); 


        // =================================================================
        // g. Update Student Information
        // =================================================================
        // Target the edit button belonging to the row containing "sewwandi"
        driver.findElement(By.xpath("//tr[contains(.,'sewwandi')]//button[contains(@class,'edit-btn')]")).click();

        // Update the last name text box field value to "User"
        WebElement lastNameField = driver.findElement(By.id("last_name"));
        lastNameField.clear();
        lastNameField.sendKeys("User");
        
        // Click the update submission button
        driver.findElement(By.id("update_student_btn")).click();

        // Handle success update alert box
        Alert updateAlert = driver.switchTo().alert();
        System.out.println("Student Updating Alert Message is: " + updateAlert.getText());
        updateAlert.accept();


        // =================================================================
        // h. Logout from the System
        // =================================================================
        driver.findElement(By.id("logout_btn")).click();
        System.out.println("Logout from the website successfully.");

        // Tear down the session
        driver.quit();
    }
}