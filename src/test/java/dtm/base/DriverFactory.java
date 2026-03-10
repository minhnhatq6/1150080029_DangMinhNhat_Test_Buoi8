package dtm.base;
//bai2.2
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {
    // ThreadLocal: đảm bảo mỗi thread có một biến WebDriver riêng biệt
    private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    // Khởi tạo trình duyệt
    public static void initDriver() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        tlDriver.set(driver); // Gắn driver vào luồng hiện tại
    }

    // Lấy trình duyệt ra để dùng
    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    // Đóng trình duyệt và xóa bộ nhớ
    public static void quitDriver() {
        if (tlDriver.get() != null) {
            tlDriver.get().quit();
            tlDriver.remove(); // Rất quan trọng: tránh memory leak (tràn bộ nhớ)
        }
    }
}