
	package Test_packg;

	import java.util.List;

	import org.openqa.selenium.WebElement;
	import org.testng.annotations.Test;
	import com.aventstack.extentreports.ExtentTest;
	import base_packg.Base_class;
	import page_packg.Checkout_page;
	import page_packg.Login_page;
	import page_packg.Product_page;

	public class Checkout_test extends Base_class {

	    @Test
	    public void iconclick() throws InterruptedException {
	        // open website url
	        driver.get("https://www.saucedemo.com/");

	        Login_page log = new Login_page(driver);
	        Product_page pdt = new Product_page(driver);
	        Checkout_page checkout_page = new Checkout_page(driver);

	        ExtentTest test = extent.createTest("checkout test");
	        
	        // login
	        log.setvalue("standard_user", "secret_sauce");
	        log.login_button();
	        Thread.sleep(2000);

	        // adding product to cart
	        pdt.selectZtoA();
	        pdt.additems();
	        List<WebElement> dt1 = pdt.productdtls();
	        System.out.println("Total Number of Products: " + dt1.size());

	        // Print details of each product
	        for (WebElement product : dt1) {
	            System.out.println(product.getText());
	        }

	        test.pass("Products displayed and items added to cart successfully");
	    

	        

	        // 1. moving to check clicking carticon
	        checkout_page.cartclick();

	        // 2. continue using customer details
	        checkout_page.enterCustomerDetails("Silpa", "Silpasasikumar", "689672");

	        //3. finish order
	        //checkout_page.finishOrder();
	        
	        

	     // 4. Logout from application 
	        checkout_page.logoutProcess();
	        Thread.sleep(2000);

	        test.pass("Checkout Flow completed and logged out successfully");

	        
	    }
	
}
