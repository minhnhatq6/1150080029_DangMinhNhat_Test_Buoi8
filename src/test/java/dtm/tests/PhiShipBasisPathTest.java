package dtm.tests;
//bai4.2
import org.testng.Assert;
import org.testng.annotations.Test;

public class PhiShipBasisPathTest {

    // Hàm gốc mô phỏng logic nghiệp vụ
    public static double tinhPhiShip(double trongLuong, String vung, boolean laMember) {
        if (trongLuong <= 0) { // D1
            throw new IllegalArgumentException("Trong luong phai > 0");
        }
        double phi = 0;
        if (vung.equals("noi_thanh")) { // D2
            phi = 15000;
            if (trongLuong > 5) { // D3
                phi += (trongLuong - 5) * 2000;
            }
        } else if (vung.equals("ngoai_thanh")) { // D4
            phi = 25000;
            if (trongLuong > 3) { // D5
                phi += (trongLuong - 3) * 3000;
            }
        } else { // tinh khac
            phi = 50000;
            if (trongLuong > 2) { // D6
                phi += (trongLuong - 2) * 5000;
            }
        }
        if (laMember) { // D7
            phi = phi * 0.9; // giảm 10%
        }
        return phi;
    }

    @Test(description = "Path 1: Nội thành, <= 5kg, không member")
    public void testPath1_NoiThanhNheKhongMember() {
        Assert.assertEquals(tinhPhiShip(4, "noi_thanh", false), 15000.0, 0.01);
    }

    @Test(description = "Path 2: Trọng lượng không hợp lệ")
    public void testPath2_InvalidWeight() {
        Assert.assertThrows(IllegalArgumentException.class, 
            () -> tinhPhiShip(0, "noi_thanh", false));
    }

    @Test(description = "Path 3: Ngoại thành, <= 3kg, không member")
    public void testPath3_NgoaiThanhNheKhongMember() {
        Assert.assertEquals(tinhPhiShip(2, "ngoai_thanh", false), 25000.0, 0.01);
    }

    @Test(description = "Path 4: Tỉnh khác, <= 2kg, không member")
    public void testPath4_TinhKhacNheKhongMember() {
        Assert.assertEquals(tinhPhiShip(1, "tinh_khac", false), 50000.0, 0.01);
    }

    @Test(description = "Path 5: Nội thành, > 5kg, không member")
    public void testPath5_NoiThanhNangKhongMember() {
        Assert.assertEquals(tinhPhiShip(6, "noi_thanh", false), 17000.0, 0.01);
    }

    @Test(description = "Path 6: Ngoại thành, > 3kg, không member")
    public void testPath6_NgoaiThanhNangKhongMember() {
        Assert.assertEquals(tinhPhiShip(4, "ngoai_thanh", false), 28000.0, 0.01);
    }

    @Test(description = "Path 7: Tỉnh khác, > 2kg, không member")
    public void testPath7_TinhKhacNangKhongMember() {
        Assert.assertEquals(tinhPhiShip(3, "tinh_khac", false), 55000.0, 0.01);
    }

    @Test(description = "Path 8: Nội thành, <= 5kg, CÓ member (Kiểm tra giảm giá)")
    public void testPath8_NoiThanhNheCoMember() {
        Assert.assertEquals(tinhPhiShip(4, "noi_thanh", true), 13500.0, 0.01);
    }
}