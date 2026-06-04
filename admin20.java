package User_Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.Alert;

public class User_Test {
    // We declare the driver at the class level so all methods can use it
    static WebDriver driver;

    public static void main(String[] args) {
        // 11. Call all the above created methods inside the main method
        callBrowser();
        passingUsernameAndPassword("admin@gmail.com", "admin@12345");
        adminLogin();
        clickUserAndURLValidation();
        entriesDropdown();
        searchUser("Ad");
        addNewUser("ram@gmail.com", "ram@12345", "Ram", "Gopal");
        updateUser("User");
        deleteUser();
        logoutUser();
    }

    // 1. Open Chrome and navigate to Admin Login Page
    public static void callBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost/EmployeeUser/login.php"); // Adjust to your local database path
    }

    // 2. Pass values into login fields
    public static void passingUsernameAndPassword(String userName, String userPassword) {
        driver.findElement(By.id("email")).sendKeys(userName);
        driver.findElement(By.id("password")).sendKeys(userPassword);
    }

    // 3. Login method with empty field checks
    public static void adminLogin() {
        String uName = driver.findElement(By.id("email")).getAttribute("value");
        String uPass = driver.findElement(By.id("password")).getAttribute("value");

        if (uName.isEmpty()) System.out.println("username is empty");
        else if (uPass.isEmpty()) System.out.println("password is empty");
        else {
            driver.findElement(By.id("loginBtn")).click();
        }
    }

    // 4. Click user side menu and check URL validation
    public static void clickUserAndURLValidation() {
        driver.findElement(By.id("userMenu")).click();
        String expectedURL = "http://localhost/EmployeeUser/users.php";
        String currentURL = driver.getCurrentUrl();

        if (currentURL.equals(expectedURL)) System.out.println("URL Validation Success!");
        else System.out.println("URL Validation Failed!");
    }

    // 5. Select entry dropdown by index
    public static void entriesDropdown() {
        WebElement dropdown = driver.findElement(By.name("entries"));
        Select select = new Select(dropdown);
        select.selectByIndex(1); // Selecting the second option
        System.out.println("The Selected Option is : " + select.getFirstSelectedOption().getText());
    }

    // 6. Search the user
    public static void searchUser(String name) {
        driver.findElement(By.id("search")).sendKeys(name);
    }

    // 7. Add new user and handle alert
    public static void addNewUser(String username, String password, String firstname, String lastname) {
        driver.findElement(By.id("addBtn")).click();
        driver.findElement(By.id("new_email")).sendKeys(username);
        driver.findElement(By.id("new_pass")).sendKeys(password);
        driver.findElement(By.id("new_fname")).sendKeys(firstname);
        driver.findElement(By.id("new_lname")).sendKeys(lastname);
        driver.findElement(By.id("saveBtn")).click();

        Alert alert = driver.switchTo().alert();
        System.out.println("User Saving Alert Message is : " + alert.getText());
        alert.accept(); // Closes the alert box
    }

    // 8. Update user and handle alert
    public static void updateUser(String lastname) {
        driver.findElement(By.id("editBtn")).click();
        WebElement lNameField = driver.findElement(By.id("edit_lname"));
        lNameField.clear();
        lNameField.sendKeys(lastname);
        driver.findElement(By.id("updateBtn")).click();

        Alert alert = driver.switchTo().alert();
        System.out.println("User Updating Alert Message is : " + alert.getText());
        alert.accept();
    }

    // 9. Delete user and handle confirmation alert
    public static void deleteUser() {
        driver.findElement(By.id("deleteBtn_Ram")).click();
        Alert alert = driver.switchTo().alert();
        System.out.println("User Deleting Alert Message is : " + alert.getText());
        alert.dismiss(); // Requirement: "must close... without accepting"
    }

    // 10. Logout from the website
    public static void logoutUser() {
        driver.findElement(By.id("logout")).click();
        driver.quit();
    }
}