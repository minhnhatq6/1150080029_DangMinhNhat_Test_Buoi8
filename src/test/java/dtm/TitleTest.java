package dtm;
//bai1.1
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class TitleTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Tự động cấu hình ChromeDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com");
    }

    @Test(description = "Kiem thu tieu de trang chu")
    public void testTitle() {
        String expectedTitle = "Swag Labs";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "Tieu de trang khong dung!");
    }

    @Test(description = "Kiem thu URL trang chu")
    public void testURL() {
        String actualUrl = driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.contains("saucedemo"), "URL khong hop le!");
    }

    // --- CÁC TEST CASE TỰ THIẾT KẾ THEO YÊU CẦU BÀI 1.1 ---

    @Test(description = "Kiem thu nguon trang (page source) co chua tu khoa")
    public void testPageSource() {
        // Lấy toàn bộ mã HTML của trang web
        String pageSource = driver.getPageSource();
        // Kiểm tra xem mã nguồn có chứa thẻ <title>Swag Labs</title> hay không
        Assert.assertTrue(pageSource.contains("Swag Labs"), "Nguon trang khong chua tu khoa mong doi!");
    }

    @Test(description = "Kiem thu form dang nhap co hien thi day du hay khong")
    public void testLoginFormDisplayed() {
        // Tìm các phần tử giao diện bằng Locator
        WebElement loginContainer = driver.findElement(By.className("login_wrapper"));
        WebElement usernameField = driver.findElement(By.id("user-name"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        // Dùng phương thức isDisplayed() để kiểm tra hiển thị
        Assert.assertTrue(loginContainer.isDisplayed(), "Vung container dang nhap khong hien thi!");
        Assert.assertTrue(usernameField.isDisplayed(), "Truong nhap Username khong hien thi!");
        Assert.assertTrue(passwordField.isDisplayed(), "Truong nhap Password khong hien thi!");
        Assert.assertTrue(loginButton.isDisplayed(), "Nut Login khong hien thi!");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Đóng trình duyệt sau mỗi test
        }
    }
}