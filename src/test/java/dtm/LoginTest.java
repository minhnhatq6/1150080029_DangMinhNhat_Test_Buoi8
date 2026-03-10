package dtm;
//bai1.2
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;

public class LoginTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com");
        
        // Khởi tạo Explicit Wait tối đa 10 giây
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test(description = "testLoginSuccess: Nhap user/pass hop le")
    public void testLoginSuccess() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // Chờ URL thay đổi chứa "/inventory.html" thay vì dùng Thread.sleep()
        wait.until(ExpectedConditions.urlContains("/inventory.html"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/inventory.html"), "Loi: Khong chuyen sang trang inventory!");
    }

    @Test(description = "testLoginWrongPassword: Nhap sai mat khau")
    public void testLoginWrongPassword() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("sai_mat_khau_123");
        driver.findElement(By.id("login-button")).click();

        // Chờ thông báo lỗi xuất hiện và lấy text
        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3[data-test='error']")));
        Assert.assertTrue(errorMsg.getText().contains("Username and password do not match"), "Loi: Thong bao sai mat khau khong khop!");
    }

    @Test(description = "testLoginEmptyUsername: Bo trong username")
    public void testLoginEmptyUsername() {
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3[data-test='error']")));
        Assert.assertTrue(errorMsg.getText().contains("Username is required"), "Loi: Thong bao trong username khong khop!");
    }

    @Test(description = "testLoginEmptyPassword: Bo trong password")
    public void testLoginEmptyPassword() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("login-button")).click();

        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3[data-test='error']")));
        Assert.assertTrue(errorMsg.getText().contains("Password is required"), "Loi: Thong bao trong password khong khop!");
    }

    @Test(description = "testLoginLockedUser: Dung locked_out_user")
    public void testLoginLockedUser() {
        driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3[data-test='error']")));
        Assert.assertTrue(errorMsg.getText().contains("Sorry, this user has been locked out"), "Loi: Thong bao tai khoan bi khoa khong khop!");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}