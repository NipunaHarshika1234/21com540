package EmployeeTest;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EmployeeTest {

	public static void main(String[] args) throws InterruptedException {
		//System.setProperty("webdriver.chrome.driver",
     //"C:\\Users\\harsh\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // =========================
        // OPEN LOGIN PAGE
        // =========================
        driver.get("http://localhost/employee/admin");

        // =========================
        // 1. EMPTY USERNAME & PASSWORD
        // =========================
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.cssSelector(".btn.btn-primary.btn-block")).click();

        try {
            WebElement error = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[contains(text(),'required') or contains(text(),'empty')]")
                    )
            );
            System.out.println("EMPTY VALIDATION: " + error.getText());
        } catch (Exception e) {
            System.out.println("No empty field validation message found");
        }

        // =========================
        // 2. ONLY USERNAME EMPTY
        // =========================
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("password")).clear();

        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.cssSelector(".btn.btn-primary.btn-block")).click();

        try {
            WebElement error = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[contains(text(),'username')]")
                    )
            );
            System.out.println("USERNAME EMPTY: " + error.getText());
        } catch (Exception e) {
            System.out.println("No username validation message");
        }

        // =========================
        // 3. ONLY PASSWORD EMPTY
        // =========================
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("password")).clear();

        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.cssSelector(".btn.btn-primary.btn-block")).click();

        try {
            WebElement error = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[contains(text(),'password')]")
                    )
            );
            System.out.println("PASSWORD EMPTY: " + error.getText());
        } catch (Exception e) {
            System.out.println("No password validation message");
        }

        
        // =========================
        // 4. INVALID LOGIN
        // =========================
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("password")).clear();

        driver.findElement(By.name("username")).sendKeys("wrongUser");
        driver.findElement(By.name("password")).sendKeys("wrongPass");
        driver.findElement(By.cssSelector(".btn.btn-primary.btn-block")).click();

        try {
            WebElement error = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[contains(text(),'Invalid') or contains(text(),'incorrect')]")
                    )
            );
            System.out.println("INVALID LOGIN: " + error.getText());
        } catch (Exception e) {
            System.out.println("No invalid login message found");
        }

        // =========================
        // 5. VALID LOGIN
        // =========================
        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("password")).clear();

        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.cssSelector(".btn.btn-primary.btn-block")).click();

        wait.until(ExpectedConditions.urlContains("admin"));

        if (driver.getCurrentUrl().contains("admin")) {
            System.out.println("LOGIN SUCCESS");
        } else {
            System.out.println("LOGIN FAILED");
        }

        
        // =========================
        // 6. NAVIGATE TO EMPLOYEE PAGE
        // =========================
        try {
            driver.findElement(By.linkText("Employee")).click();
        } catch (Exception e) {
            driver.get("http://localhost/employee/admin/employee.php");
        }

        wait.until(ExpectedConditions.urlContains("employee"));
        System.out.println("EMPLOYEE PAGE LOADED");

        // =========================
        // 7. CLICK ADD NEW
        // =========================
        WebElement addBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(text(),'Add')]")
                )
        );
        addBtn.click();
        System.out.println("ADD NEW CLICKED");

        // =========================
        // 8. EMPTY FORM VALIDATION
        // =========================

        try {
            WebElement error = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[contains(text(),'required')]")
                    )
            );
            System.out.println("FORM EMPTY VALIDATION: " + error.getText());
        } catch (Exception e) {
            System.out.println("No form validation message");
        }

        // =========================
        // 9. FILL FORM
        // =========================
        driver.findElement(By.name("firstname")).sendKeys("ABC");
        driver.findElement(By.name("middlename")).sendKeys("EFG");
        driver.findElement(By.name("lastname")).sendKeys("ASDF");
        driver.findElement(By.name("department")).sendKeys("Finance");
        driver.findElement(By.name("position")).sendKeys("Manager");

        // =========================
        // 10. SAVE EMPLOYEE
        // =========================
        WebElement saveBtn = wait.until(
                ExpectedConditions.elementToBeClickable(By.name("save"))
        );
        saveBtn.click();

        try {
            WebElement successMsg = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//*[contains(text(),'success') or contains(text(),'added')]")
                    )
            );
            System.out.println("SAVE SUCCESS: " + successMsg.getText());
        } catch (Exception e) {
            System.out.println("No success message found");
        }

        // =========================
        // END
        // =========================
        // driver.quit();
        
     // =========================
     // CLICK EDIT BUTTON
     // =========================
     WebElement editBtn = wait.until(
             ExpectedConditions.elementToBeClickable(
                     By.xpath("(//button[contains(@class,'edit_employee')])[1]")
             )
     );
     editBtn.click();


     // =========================
     // WAIT FOR EDIT POPUP (IMPORTANT)
     // =========================
     WebElement modal = wait.until(
             ExpectedConditions.visibilityOfElementLocated(
                     By.xpath("//div[contains(@class,'modal') and contains(@style,'display: block')]")
             )
     );


     // =========================
     // EDIT FIELDS (INSIDE MODAL)
     // =========================
     WebElement middleName1 = wait.until(
             ExpectedConditions.visibilityOfElementLocated(
                     By.xpath("//div[contains(@class,'modal') and contains(@style,'display: block')]//input[@name='middlename']")
             )
     );

     middleName1.clear();
     middleName1.sendKeys("Updated mid Name");

     // Example: edit another field
     WebElement firstName = driver.findElement(
             By.xpath("//div[contains(@class,'modal') and contains(@style,'display: block')]//input[@name='firstname']")
     );
     firstName.clear();
     firstName.sendKeys("Updated Fst Name");


     // =========================
     // CLICK SAVE / UPDATE BUTTON (INSIDE MODAL)
     // =========================
     WebElement saveBtn1 = wait.until(
             ExpectedConditions.elementToBeClickable(
                     By.xpath("//div[contains(@class,'modal') and contains(@style,'display: block')]//button[@name='save']")
             )
     );
     saveBtn1.click();

     try {
    	    Alert alert = wait.until(ExpectedConditions.alertIsPresent());
    	    System.out.println("Alert: " + alert.getText());
    	    alert.accept(); // Click OK
    	} catch (Exception e) {
    	    System.out.println("No alert appeared");
    	}

     // =========================
     // VALIDATE SUCCESS MESSAGE
     // =========================
     try {
         WebElement successMsg = wait.until(
                 ExpectedConditions.visibilityOfElementLocated(
                         By.xpath("//*[contains(text(),'updated') or contains(text(),'success')]")
                 )
         );
         System.out.println("UPDATE SUCCESS: " + successMsg.getText());
     } catch (Exception e) {
         System.out.println("No update message found");
     }
     
     // =========================
     // CLICK DELETE BUTTON
     // =========================
     WebElement deleteBtn = wait.until(
    	        ExpectedConditions.elementToBeClickable(
    	                By.xpath("//button[contains(@class,'remove_employee') and @data-id='13']")
    	        )
    	);
    	deleteBtn.click();


     // Handle confirmation alert
     try {
         Alert alert = wait.until(ExpectedConditions.alertIsPresent());

         System.out.println("Alert text: " + alert.getText());

         // Choose ONE:
         //alert.accept();   // OK button (confirm delete)
         alert.dismiss(); // Cancel button (abort delete)

     } catch (Exception e) {
         System.out.println("No alert appeared");
     }

     try {
 	    Alert alert = wait.until(ExpectedConditions.alertIsPresent());
 	    System.out.println("Alert: " + alert.getText());
 	    alert.accept(); // Click OK
 	} catch (Exception e) {
 	    System.out.println("No alert appeared");
 	}
     
     
     //======================LOGOUT-BUTTON=======================
     driver.findElement(By.xpath("//a[contains(text(),'Admin')]")).click();
	}

}
