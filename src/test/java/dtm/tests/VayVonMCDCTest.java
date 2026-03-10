package dtm.tests;
//bai5.2
import org.testng.Assert;
import org.testng.annotations.Test;

public class VayVonMCDCTest {

    // Hàm gốc mô phỏng logic nghiệp vụ theo đề bài
    public static boolean duDieuKienVay(int tuoi, double thuNhap, boolean coTaiSanBaoLanh, int dienTinDung) {
        boolean dieuKienCoBan = (tuoi >= 22) && (thuNhap >= 10_000_000);
        boolean dieuKienBaoDam = coTaiSanBaoLanh || (dienTinDung >= 700);
        return dieuKienCoBan && dieuKienBaoDam;
    }

    // --- CÁC TEST METHOD PHÁT SINH TỪ PHÂN TÍCH MC/DC ---
    // A: Tuổi (22) | B: ThuNhập (10tr) | C: TàiSản | D: TínDụng (700)

    @Test(description = "Row 2: A=T, B=T, C=T, D=F => True")
    public void testMCDC_BaseTrue_TaiSanThayTheTinDung() {
        // Tuổi 25 (T), Thu nhập 15tr (T), Có tài sản (T), Tín dụng 600 (F)
        boolean result = duDieuKienVay(25, 15_000_000, true, 600);
        Assert.assertTrue(result, "Lỗi tại Row 2: Đủ điều kiện cơ bản và có tài sản bảo đảm -> Phải được vay.");
    }

    @Test(description = "Row 3: A=T, B=T, C=F, D=T => True (D chứng minh tính độc lập)")
    public void testMCDC_BaseTrue_TinDungThayTheTaiSan() {
        // Tuổi 25 (T), Thu nhập 15tr (T), Không tài sản (F), Tín dụng 750 (T)
        boolean result = duDieuKienVay(25, 15_000_000, false, 750);
        Assert.assertTrue(result, "Lỗi tại Row 3: Đủ điều kiện cơ bản và điểm tín dụng tốt -> Phải được vay.");
    }

    @Test(description = "Row 4: A=T, B=T, C=F, D=F => False (C chứng minh tính độc lập)")
    public void testMCDC_BaseTrue_KhongCoBaoDam() {
        // Tuổi 25 (T), Thu nhập 15tr (T), Không tài sản (F), Tín dụng 600 (F)
        boolean result = duDieuKienVay(25, 15_000_000, false, 600);
        Assert.assertFalse(result, "Lỗi tại Row 4: Không có điều kiện bảo đảm nào -> KHÔNG được vay.");
    }

    @Test(description = "Row 6: A=T, B=F, C=T, D=F => False (B chứng minh tính độc lập)")
    public void testMCDC_ThuNhapKhongDu() {
        // Tuổi 25 (T), Thu nhập 5tr (F), Có tài sản (T), Tín dụng 600 (F)
        boolean result = duDieuKienVay(25, 5_000_000, true, 600);
        Assert.assertFalse(result, "Lỗi tại Row 6: Thu nhập không đủ -> KHÔNG được vay.");
    }

    @Test(description = "Row 10: A=F, B=T, C=T, D=F => False (A chứng minh tính độc lập)")
    public void testMCDC_TuoiKhongDu() {
        // Tuổi 20 (F), Thu nhập 15tr (T), Có tài sản (T), Tín dụng 600 (F)
        boolean result = duDieuKienVay(20, 15_000_000, true, 600);
        Assert.assertFalse(result, "Lỗi tại Row 10: Chưa đủ 22 tuổi -> KHÔNG được vay.");
    }
}