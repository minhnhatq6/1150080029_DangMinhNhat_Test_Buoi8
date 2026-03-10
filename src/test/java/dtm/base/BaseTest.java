package dtm.base;
//bai2.2
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        // Tự động gọi Factory để mở trình duyệt trước mỗi @Test
        DriverFactory.initDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        // Tự động đóng trình duyệt sau mỗi @Test
        DriverFactory.quitDriver();
    }
}