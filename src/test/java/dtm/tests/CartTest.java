package dtm.tests;
//bai2.1
import dtm.base.BaseTest;
import dtm.base.DriverFactory;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    @Test(groups = {"smoke", "regression"}, description = "Test thêm 1 SP vào giỏ")
    public void testAddOneItem() throws InterruptedException {
        System.out.println("CartTest - testAddOneItem chạy trên Thread: " + Thread.currentThread().getId());
        DriverFactory.getDriver().get("https://www.saucedemo.com/inventory.html");
        Thread.sleep(3000);
    }

    @Test(groups = {"regression"}, description = "Test xóa SP khỏi giỏ")
    public void testRemoveItem() throws InterruptedException {
        System.out.println("CartTest - testRemoveItem chạy trên Thread: " + Thread.currentThread().getId());
        DriverFactory.getDriver().get("https://www.saucedemo.com/cart.html");
        Thread.sleep(3000);
    }
}