package dtm.tests;
//bai2.1
import dtm.base.BaseTest;
import dtm.base.DriverFactory;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(groups = {"smoke", "regression"}, description = "Test đăng nhập hợp lệ")
    public void testLoginValid() throws InterruptedException {
        System.out.println("LoginTest - testLoginValid chạy trên Thread: " + Thread.currentThread().getId());
        DriverFactory.getDriver().get("https://www.saucedemo.com");
        Thread.sleep(3000); // Dừng 3s để quan sát trình duyệt mở
    }

    @Test(groups = {"regression"}, description = "Test đăng nhập sai mật khẩu")
    public void testLoginInvalid() throws InterruptedException {
        System.out.println("LoginTest - testLoginInvalid chạy trên Thread: " + Thread.currentThread().getId());
        DriverFactory.getDriver().get("https://www.saucedemo.com");
        Thread.sleep(3000);
    }
}