package dtm.tests;

import dtm.base.BaseTest;
import dtm.base.DriverFactory; // Thêm dòng import này
import dtm.pages.TextBoxPage;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TextBoxWhiteBoxTest extends BaseTest {

    @Test(description = "TC_UI_01: Dữ liệu hợp lệ, Output hiển thị")
    public void testValidInput() {
        // Dùng DriverFactory.getDriver() thay vì getDriver()
        DriverFactory.getDriver().get("https://demoqa.com/text-box");
        TextBoxPage page = new TextBoxPage(DriverFactory.getDriver());
        
        // Cuộn chuột xuống một chút vì DemoQA hay bị quảng cáo che nút Submit
        ((JavascriptExecutor) DriverFactory.getDriver()).executeScript("window.scrollBy(0, 300)");

        page.fillAndSubmit("Dang Minh Nhat", "nhat@gmail.com", "123 HCM");
        
        Assert.assertTrue(page.isOutputDisplayed(), "Output không hiển thị dù nhập đúng!");
        Assert.assertFalse(page.hasEmailError(), "Email đúng nhưng lại báo lỗi!");
    }

    @Test(description = "TC_UI_02: Email sai định dạng (Thiếu @), hiển thị viền đỏ")
    public void testInvalidEmail() {
        DriverFactory.getDriver().get("https://demoqa.com/text-box");
        TextBoxPage page = new TextBoxPage(DriverFactory.getDriver());
        
        ((JavascriptExecutor) DriverFactory.getDriver()).executeScript("window.scrollBy(0, 300)");

        // Nhập email sai (Boundary / Error Guessing)
        page.fillAndSubmit("Dang Minh Nhat", "nhat_sai_format.com", "123 HCM");
        
        // Mong đợi: Bị gán class field-error (viền đỏ)
        Assert.assertTrue(page.hasEmailError(), "Hệ thống không báo lỗi Email sai định dạng!");
    }

    @Test(description = "TC_UI_03: Form rỗng (Empty Boundary), Submit không có tác dụng phụ")
    public void testEmptyForm() {
        DriverFactory.getDriver().get("https://demoqa.com/text-box");
        TextBoxPage page = new TextBoxPage(DriverFactory.getDriver());
        
        ((JavascriptExecutor) DriverFactory.getDriver()).executeScript("window.scrollBy(0, 300)");

        // Nhập chuỗi rỗng
        page.fillAndSubmit("", "", "");
        
        // Form này của DemoQA khi rỗng submit không hiện output, cũng không báo lỗi
        Assert.assertFalse(page.hasEmailError(), "Rỗng không nên báo lỗi format Email");
    }
}