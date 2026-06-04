package Q3;

import org.testng.annotations.Test;

public class dependentClass {

    @Test
    public void registerAccount() {
        System.out.println("First register your account");
    }

    // "login" is dependent on "registerAccount"
    @Test(dependsOnMethods = { "registerAccount" })
    public void login() {
        System.out.println("Login to the account after registration");
    }

    @Test(dependsOnMethods = { "login" })
    public void homepage() {
        System.out.println("This is the home page");
    }

    @Test
    public void sendEmail() {
        System.out.println("Send email after login");
    }

    // "logout" is dependent on both "login" and "homepage"
    @Test(dependsOnMethods = { "login", "homepage" })
    public void logout() {
        System.out.println("Logout current session");
    }
}



//##########################

package Q3;

import org.testng.annotations.Test;

public class FirstTestNG {

    // 3. Need to execute "registerAccount" before "login"
    @Test(priority = 1)
    public void registerAccount() {
        System.out.println("First register your account");
    }

    @Test(priority = 2)
    public void login() {
        System.out.println("Login to the account after registration");
    }

    // 2. Execute all other test cases without any explicit priority order
    @Test
    public void sendEmail() {
        System.out.println("Send email after login");
    }

    @Test
    public void homepage() {
        System.out.println("This is the home page");
    }

    @Test
    public void logout() {
        System.out.println("Logout current session");
    }
}

//##########################

package Q3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class SecondTestNG {

    // 5. Run 3 times concurrently using multi-threading
    @Test(invocationCount = 3, threadPoolSize = 3)
    public void openGoogleTest() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        
        try {
            driver.get("https://www.google.com");
            System.out.println("Google page is open");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}