package dtm.tests;
//bai3.2
import org.testng.Assert;
import org.testng.annotations.Test;

public class TinhTienNuocTest {

    // Hàm gốc mô phỏng lại logic nghiệp vụ
    public static double tinhTienNuoc(int soM3, String loaiKhachHang) {
        if (soM3 <= 0) return 0; // N1
        double don_gia;
        if (loaiKhachHang.equals("ho_ngheo")) { // N3
            don_gia = 5000;
        } else if (loaiKhachHang.equals("dan_cu")) { // N5
            if (soM3 <= 10) { // N6
                don_gia = 7500;
            } else if (soM3 <= 20) { // N8
                don_gia = 9900;
            } else {
                don_gia = 11400;
            }
        } else { // kinh_doanh
            don_gia = 22000;
        }
        return soM3 * don_gia;
    }

    @Test(description = "Phủ N1-True: Số khối nước = 0")
    public void testSoKhoiBangKhong() {
        Assert.assertEquals(tinhTienNuoc(0, "dan_cu"), 0.0);
    }

    @Test(description = "Phủ N3-True: Khách hàng hộ nghèo")
    public void testHoNgheo() {
        Assert.assertEquals(tinhTienNuoc(5, "ho_ngheo"), 25000.0);
    }

    @Test(description = "Phủ N6-True: Dân cư, khối lượng <= 10")
    public void testDanCuBac1() {
        Assert.assertEquals(tinhTienNuoc(5, "dan_cu"), 37500.0);
    }

    @Test(description = "Phủ N8-True: Dân cư, khối lượng 11 -> 20")
    public void testDanCuBac2() {
        Assert.assertEquals(tinhTienNuoc(15, "dan_cu"), 148500.0);
    }

    @Test(description = "Phủ N8-False: Dân cư, khối lượng > 20")
    public void testDanCuBac3() {
        Assert.assertEquals(tinhTienNuoc(25, "dan_cu"), 285000.0);
    }

    @Test(description = "Phủ N5-False: Loại khách hàng khác (Kinh doanh)")
    public void testKinhDoanh() {
        Assert.assertEquals(tinhTienNuoc(10, "kinh_doanh"), 220000.0);
    }
}