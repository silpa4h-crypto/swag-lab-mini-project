package page_packg;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Product_page {

    WebDriver driver;

    @FindBy(className = "inventory_item")
    List<WebElement> productdetails;

    @FindBy(className = "btn_inventory")
    List<WebElement> cartbutton;

    @FindBy(className = "product_sort_container")
    WebElement dropdown;

    @FindBy(className = "app_logo")
    WebElement logo;

    // Constructor
    public Product_page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Method to verify logo
    public WebElement logoverify() {
        return logo;
    }

    // Method to add items to cart
    public void additems() {

        WebDriverWait wait =
            new WebDriverWait(driver, Duration.ofSeconds(15));

        List<WebElement> buttons = wait.until(
            ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.className("btn_inventory")
            )
        );

        for (WebElement items : buttons) {

            wait.until(
                ExpectedConditions.elementToBeClickable(items)
            );

            System.out.println(items.getText());
            items.click();
        }
    }

    // Method to select Z to A from dropdown
    public void selectZtoA() {

        WebDriverWait wait =
            new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(
            ExpectedConditions.visibilityOf(dropdown)
        );

        Select sel = new Select(dropdown);
        sel.selectByValue("za");
    }

    // Method to get product details list
    public List<WebElement> productdtls() {

        WebDriverWait wait =
            new WebDriverWait(driver, Duration.ofSeconds(15));

        return wait.until(
            ExpectedConditions.visibilityOfAllElementsLocatedBy(
                By.className("inventory_item")
            )
        );
    }
}