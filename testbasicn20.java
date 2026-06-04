package test01;

import org.testng.annotations.Test;

public class dependentClass {

    @Test
    public void registerAccount() {
        System.out.println("First register your account");
    }

    @Test
    public void sendEmail() {
        System.out.println("Send email after login");
    }

    // Requirement 4a: login is dependent on registerAccount
    @Test(dependsOnMethods = {"registerAccount"})
    public void login() {
        System.out.println("Login to the account after registration");
    }

    @Test
    public void homepage() {
        System.out.println("This is the index page");
    }

    // Requirement 4b: logout is dependent on both login and homepage
    @Test(dependsOnMethods = {"login", "homepage"})
    public void logout() {
        System.out.println("Logout current session");
    }
}



//333333333333

package test01;

import org.testng.annotations.Test;

public class NewTest {

    @Test
    public void registerAccount() {
        System.out.println("First register your account");
    }

    @Test
    public void sendEmail() {
        System.out.println("Send email after login");
    }

    // Requirement 3: Ensure registerAccount runs before login using dependencies
    @Test(dependsOnMethods = {"registerAccount"})
    public void login() {
        System.out.println("Login to the account after registration");
    }

    @Test
    public void homepage() {
        System.out.println("This is the index page");
    }

    @Test
    public void logout() {
        System.out.println("Logout current session");
    }
}