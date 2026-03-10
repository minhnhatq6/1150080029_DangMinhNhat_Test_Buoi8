package dtm.tests;
//bai2.1
import dtm.base.BaseTest;
import dtm.base.DriverFactory;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    @Test(groups = {"smoke", "regression"}, description = "Test điền thông tin Checkout")
    public void testCheckoutInformation() throws InterruptedException {
        System.out.println("CheckoutTest - testCheckoutInformation chạy trên Thread: " + Thread.currentThread().getId());
        DriverFactory.getDriver().get("https://www.saucedemo.com/checkout-step-one.html");
        Thread.sleep(3000);
    }

    @Test(groups = {"regression"}, description = "Test hoàn tất đơn hàng")
    public void testCheckoutFinish() throws InterruptedException {
        System.out.println("CheckoutTest - testCheckoutFinish chạy trên Thread: " + Thread.currentThread().getId());
        DriverFactory.getDriver().get("https://www.saucedemo.com/checkout-step-two.html");
        Thread.sleep(3000);
    }
}