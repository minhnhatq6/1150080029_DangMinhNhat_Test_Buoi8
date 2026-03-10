package dtm.tests;
//bai3.1
import org.testng.Assert;
import org.testng.annotations.Test;

public class XepLoaiTest {

    // Hàm chính (chỉ dùng mô phỏng lại trong Test để chạy, hoặc bạn import từ class chính)
    public static String xepLoai(int diemTB, boolean coThiLai) {
        if (diemTB < 0 || diemTB > 10) return "Diem khong hop le";
        if (diemTB >= 8.5) return "Gioi";
        else if (diemTB >= 7.0) return "Kha";
        else if (diemTB >= 5.5) return "Trung Binh";
        else {
            if (coThiLai) return "Thi lai";
            return "Yeu - Hoc lai";
        }
    }

    @Test(description = "Phủ nhánh N1-True: Điểm < 0")
    public void testDiemKhongHopLe() {
        Assert.assertEquals(xepLoai(-1, false), "Diem khong hop le");
    }

    @Test(description = "Phủ nhánh N3-True: Điểm Giỏi")
    public void testDiemGioi() {
        Assert.assertEquals(xepLoai(9, false), "Gioi");
    }

    @Test(description = "Phủ nhánh N5-True: Điểm Khá")
    public void testDiemKha() {
        Assert.assertEquals(xepLoai(8, false), "Kha");
    }

    @Test(description = "Phủ nhánh N7-True: Điểm Trung Bình")
    public void testDiemTrungBinh() {
        Assert.assertEquals(xepLoai(6, false), "Trung Binh");
    }

    @Test(description = "Phủ nhánh N9-True: Thi lại")
    public void testThiLai() {
        Assert.assertEquals(xepLoai(4, true), "Thi lai");
    }

    @Test(description = "Phủ nhánh N9-False: Yếu - Học lại")
    public void testYeuHocLai() {
        Assert.assertEquals(xepLoai(4, false), "Yeu - Hoc lai");
    }
}