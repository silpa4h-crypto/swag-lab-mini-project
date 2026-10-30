
	package page_packg;

	import java.time.Duration;

	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.PageFactory;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;

	public class Login_page {
		 WebDriver driver;

		    //Locate webelement using PageFactory 
		    @FindBy(xpath = "//*[@id='user-name']")
		    public WebElement username;

		    @FindBy(xpath = "//*[@id=\"password\"]")
		    public WebElement pass;

		    @FindBy(xpath = "//*[@id=\"login-button\"]")
		    public WebElement login;

		    @FindBy(xpath = "//*[@id=\"root\"]/div/div[1]")
		    public WebElement logo;

		    // Constructor 
		    public Login_page(WebDriver driver) {
		        this.driver = driver;
		        PageFactory.initElements(driver, this);
		    }

		    // Logo verification method
		    public WebElement logoverification() {
		        return logo;
		    }

		    // To return Login button text or element
		    public WebElement buttontext() {
		        return login;
		    }

		    // Method for username and password
		    public void setvalue(String user, String pwd) throws InterruptedException {
		    	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    	    
		    	    wait.until(ExpectedConditions.visibilityOf(username));
		        username.sendKeys(user);
		        pass.sendKeys(pwd);
		    }

		    //to click login button
		    public void login_button() {
		    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		       wait.until(ExpectedConditions.elementToBeClickable(login));
		        login.click();
		    }
		}


