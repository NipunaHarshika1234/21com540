package testngExam2;

import org.testng.annotations.Test;

public class DependentClass {
	
	@Test
	public void registerAccount() {
		System.out.println("First register your account");
	}
	
	@Test
	public void sendEmail() {
		System.out.println("Send email after login");
	}
	
	@Test(dependsOnMethods = "registerAccount")
	public void login() {
		System.out.println("login to the account after registartion");
	}
	
	@Test
	public void homepage() {
		System.out.println("This is the home page");
	}
	
	@Test(dependsOnMethods = {"login", "homepage"})
	public void logout() {
		System.out.println("Logout current session");
	}
}
