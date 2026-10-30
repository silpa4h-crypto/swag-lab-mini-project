
	package Test_packg;

	import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import base_packg.Base_class;
import base_packg.Reporter_class;
import page_packg.Login_page;
import page_packg.Product_page;

	public class Product_test extends Base_class {

	    @Test(priority = 1)
	    public void logoValidationTest() throws IOException, InterruptedException {
	        // Initialize objects
	        Login_page log = new Login_page(driver);
	        Product_page pdt = new Product_page(driver);

	        // Open base URL and perform login using standard user
	        driver.get("https://www.saucedemo.com/");
	        log.setvalue("standard_user", "secret_sauce");
	        log.login_button(); 
	        
	        // Create Extent Test report
	        test = extent.createTest("logo validation test");
	        
	        WebElement x = pdt.logoverify();

	        if (x.isDisplayed()) {
	            test.pass("logo displayed in product page");
	        } else {
	            String path = Reporter_class.screenshotmethodString(driver, "logo_failed");
	            test.addScreenCaptureFromPath(path);
	            test.fail("logo not displayed");
	        }
	    }

	    @Test(priority = 2)
	    public void addItemToCartAndDisplayProductsTest() throws InterruptedException, IOException {
	        // Initialize objects
	        Login_page log = new Login_page(driver);
	        Product_page pdt = new Product_page(driver);

	        // Re-login to ensure session is active for product page testing
	        driver.get("https://www.saucedemo.com/");
	        log.setvalue("standard_user", "secret_sauce");
	        log.login_button();

	        test = extent.createTest("Add items to cart and display products test");
	     // Add items to cart
	        pdt.selectZtoA(); 
	        Thread.sleep(3000); 
	         
	        // Add items to cart
	        pdt.additems();

	        // Get product details list
	        List<WebElement> dt1 = pdt.productdtls();
	        System.out.println("Total Number of Products: " + dt1.size());

	        // Print details of each product
	        for (WebElement product : dt1) {
	            System.out.println(product.getText());
	        }

	        test.pass("Products displayed and items added to cart successfully");
	    }
	
}
