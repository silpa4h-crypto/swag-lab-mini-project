package Test_packg;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

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

        // Open base URL and perform login
        driver.get("https://www.saucedemo.com/");
        log.setvalue("standard_user", "secret_sauce");
        log.login_button();

        // Create Extent Test report
        test = extent.createTest("Logo Validation Test");

        WebElement x = pdt.logoverify();

        if (x.isDisplayed()) {
            test.pass("Logo displayed in product page");
        } else {
            String path = Reporter_class.screenshotmethodString(
                driver, "logo_failed");

            test.addScreenCaptureFromPath(path);
            test.fail("Logo not displayed");
        }
    }

    @Test(priority = 2)
    public void addItemToCartAndDisplayProductsTest()
            throws InterruptedException, IOException {

        // Initialize objects
        Login_page log = new Login_page(driver);
        Product_page pdt = new Product_page(driver);

        // Open website and login
        driver.get("https://www.saucedemo.com/");
        log.setvalue("standard_user", "secret_sauce");
        log.login_button();

        test = extent.createTest(
            "Add Items To Cart And Display Products Test");

        // Select Z to A
        pdt.selectZtoA();
        Thread.sleep(3000);

        // Add items to cart
        pdt.additems();

        // Get product details list
        List<WebElement> dt1 = pdt.productdtls();

        System.out.println("Total Number of Products: " + dt1.size());

        // Print product details
        for (WebElement product : dt1) {
            System.out.println(product.getText());
        }

        test.pass("Products displayed and items added to cart successfully");
    }

    // Product Details Report Test
    @Test(priority = 3)
    public void productDetailsReportTest() throws InterruptedException {

        // Initialize objects
        Login_page log = new Login_page(driver);

        // Open website and login
        driver.get("https://www.saucedemo.com/");
        log.setvalue("standard_user", "secret_sauce");
        log.login_button();

        // Create Extent Test
        test = extent.createTest("Product Details Report Test");

        WebDriverWait wait =
            new WebDriverWait(driver, Duration.ofSeconds(15));

        // Wait for product list
        wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.className("inventory_list")));

        // Get all products
        List<WebElement> products =
            driver.findElements(By.className("inventory_item"));

        System.out.println("\n===== PRODUCT DETAILS REPORT =====");

        // Loop through all products
        for (WebElement product : products) {

            String name = product.findElement(
                By.className("inventory_item_name")).getText();

            String price = product.findElement(
                By.className("inventory_item_price")).getText();

            String description = product.findElement(
                By.className("inventory_item_desc")).getText();

            // Console output
            System.out.println("Product Name: " + name);
            System.out.println("Product Price: " + price);
            System.out.println("Product Description: " + description);

            System.out.println("----------------------------------");

            // Extent Report output
            test.info("<b>Product Name:</b> " + name);
            test.info("<b>Product Price:</b> " + price);
            test.info("<b>Product Description:</b> " + description);
        }

        // Total products
        System.out.println("Total Products: " + products.size());

        test.pass("All product details displayed successfully");
    }
}