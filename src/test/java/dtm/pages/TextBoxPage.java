package dtm.pages;
//bai6.2
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TextBoxPage {
    private WebDriver driver;

    @FindBy(id = "userName")
    private WebElement nameField;

    @FindBy(id = "userEmail")
    private WebElement emailField;

    @FindBy(id = "currentAddress")
    private WebElement currentAddressField;

    @FindBy(id = "submit")
    private WebElement submitBtn;

    @FindBy(id = "output")
    private WebElement outputSection;

    public TextBoxPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillAndSubmit(String name, String email, String address) {
        nameField.clear();
        nameField.sendKeys(name);
        
        emailField.clear();
        emailField.sendKeys(email);
        
        currentAddressField.clear();
        currentAddressField.sendKeys(address);
        
        submitBtn.click();
    }

    // Kiểm tra xem Output box có hiển thị không
    public boolean isOutputDisplayed() {
        try {
            return outputSection.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Kiểm tra ô Email có bị gán class "field-error" (viền đỏ) không
    public boolean hasEmailError() {
        String classes = emailField.getAttribute("class");
        return classes.contains("field-error");
    }
}