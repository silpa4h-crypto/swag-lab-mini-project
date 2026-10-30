
	package Test_packg;

	import java.io.IOException;
	import org.openqa.selenium.WebElement;
	import org.testng.Assert;
	import org.testng.annotations.DataProvider;
	import org.testng.annotations.Test;
	import base_packg.Base_class;
	import base_packg.Reporter_class;
	import page_packg.Login_page;
	import utilities.Excelutility;

	public class Login_test extends Base_class {
	    
	    Login_page log;

	    // DATA PROVIDER FOR LOGIN
	   
	    @DataProvider(name = "loginData")
	    public Object[][] getData() throws Exception {
	        
	        String path = "D:\\Project\\swaglabdata.xlsx";
	        return Excelutility.getTestData(path, "Sheet1");
	    }

	   
	    // 1. LOGIN TEST (Using DataProvider)
	  
	    @Test(dataProvider = "loginData", priority = 1)
	    public void logintest(String username, String password) 
	            throws InterruptedException, IOException {
	        
	        driver.get("https://www.saucedemo.com/");
	        
	        test = extent.createTest("Login Test - " + username);
	        
	        log = new Login_page(driver);
	        
	        
	        log.setvalue(username, password);
	        log.login_button();
	        
	        System.out.println("Executed for: " + username);
	        
	        String currentUrl = driver.getCurrentUrl();
	        
	        // LOGIN SUCCESS CONDITION
	        if (currentUrl.equals("https://www.saucedemo.com/inventory.html")) {
	            test.pass("Login successful for: " + username);
	        } 
	        // LOGIN FAILURE CONDITION (Screenshot & Extent Report)
	        else {
	        	String screenshotName = "LoginFailed_" + username;
	            Reporter_class.screenshotmethodString(driver, screenshotName);
	            
	            
	            String fullPath = System.getProperty("user.dir") + "\\screenshot\\" + screenshotName + ".png";
	            test.addScreenCaptureFromPath(fullPath);
	            
	            test.fail("Login failed for: " + username);
	        }}
//	            String screenshotName = "LoginFailed_" + username;
//	            Reporter_class.screenshotmethodString(driver, screenshotName);
//	            test.addScreenCaptureFromPath(
//	                "screenshot\\" + screenshotName + ".png"
//	            );
//	            test.fail("Login failed for: " + username);
////	            Assert.fail("Login failed for: " + username);
//	        }
//	    }

	    // 2. LOGO VERIFICATION TEST
	  
	    @Test(priority = 2)
	    public void logoverification() throws IOException {
	        driver.get("https://www.saucedemo.com/");
	        
	        test = extent.createTest("Logo Validation Test");
	        
	        log = new Login_page(driver);
	        WebElement logoElement = log.logoverification();
	        
	        if (logoElement.isDisplayed()) {
	            test.pass("Logo verification passed successfully");
	        } else {
	            test.fail("Logo verification failed");
	            Assert.fail("Logo verification failed");
	        }
	    }

	    // 3. LOGIN BUTTON TEXT / DISPLAY TEST
	   
	    @Test(priority = 3)
	    public void loginButtonTest() throws IOException {
	        driver.get("https://www.saucedemo.com/");
	        
	        test = extent.createTest("Login Button Validation Test");
	        
	        log = new Login_page(driver);
	        WebElement btn = log.buttontext();
	        
	        if (btn.isDisplayed()) {
	            test.pass("Login button is displayed properly. Text: " + btn.getText());
	        } else {
	            test.fail("Login button is not displayed");
	            Assert.fail("Login button is not displayed");
	        }
	    }
	}


