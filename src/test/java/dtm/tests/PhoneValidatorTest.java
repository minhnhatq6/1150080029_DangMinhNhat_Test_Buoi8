package dtm.tests;
//bai6.1
import dtm.PhoneValidator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PhoneValidatorTest {

    @Test(description = "TC1 (D1-True): Chuỗi null -> False")
    public void testNull() {
        Assert.assertFalse(PhoneValidator.isValid(null));
    }

    @Test(description = "TC2 (D2-True): Chứa ký tự chữ cái -> False")
    public void testInvalidChars() {
        Assert.assertFalse(PhoneValidator.isValid("0987654abc"));
    }

    @Test(description = "TC3 (D3-True): Độ dài sai (9 số) -> False")
    public void testInvalidLength() {
        Assert.assertFalse(PhoneValidator.isValid("098765432")); // 9 số
    }

    @Test(description = "TC4 (D4-False): Đầu số không hợp lệ (02) -> False")
    public void testInvalidPrefix() {
        Assert.assertFalse(PhoneValidator.isValid("0287654321"));
    }

    @Test(description = "TC5 (Path chuẩn): Đầu 0 bình thường -> True")
    public void testValidPrefix0() {
        Assert.assertTrue(PhoneValidator.isValid("0987654321"));
    }

    @Test(description = "TC6 (Biên/Mở rộng): Chứa +84 và khoảng trắng -> True")
    public void testValidPrefix84WithSpaces() {
        Assert.assertTrue(PhoneValidator.isValid("+84 987 654 321"));
    }
}