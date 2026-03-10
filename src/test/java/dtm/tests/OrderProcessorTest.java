package dtm.tests;
//bai7.1
import dtm.Item;
import dtm.OrderProcessor;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class OrderProcessorTest {

    private OrderProcessor processor;
    private List<Item> itemsNhieuTien; // List hàng có tổng > 500k
    private List<Item> itemsItTien;    // List hàng có tổng < 500k

    @BeforeMethod
    public void setup() {
        processor = new OrderProcessor();
        itemsNhieuTien = Arrays.asList(new Item(300_000), new Item(300_000)); // Total 600k
        itemsItTien = Collections.singletonList(new Item(100_000)); // Total 100k
    }

    // =========================================================
    // PHẦN 1: THỰC THI 9 TEST CASE CỦA BASIS PATH
    // =========================================================

    @Test(description = "BP_01 (Baseline): Mua >500k, ko mã, ko member, trả CASH -> Freeship")
    public void testPath1_Baseline() {
        double total = processor.calculateTotal(itemsNhieuTien, "", "NONE", "CASH");
        Assert.assertEquals(total, 600_000.0, "Loi Path 1: Tinh toan sai khi khong co giam gia va freeship.");
    }

    @Test(description = "BP_02 (Lật D1): Giỏ hàng trống -> Báo lỗi")
    public void testPath2_EmptyCart() {
        Assert.assertThrows(IllegalArgumentException.class,
            () -> processor.calculateTotal(Collections.emptyList(), "", "NONE", "CASH"));
    }

    @Test(description = "BP_03 (Lật D2, D3): Dùng mã SALE10 -> Giảm 10%")
    public void testPath3_Sale10() {
        double total = processor.calculateTotal(itemsNhieuTien, "SALE10", "NONE", "CASH");
        Assert.assertEquals(total, 540_000.0, "Loi Path 3: Giam gia SALE10 khong chinh xac.");
    }

    @Test(description = "BP_04 (Lật D4): Dùng mã SALE20 -> Giảm 20%")
    public void testPath4_Sale20() {
        double total = processor.calculateTotal(itemsNhieuTien, "SALE20", "NONE", "CASH");
        Assert.assertEquals(total, 510_000.0, "BUG GHI NHAN: Phi ship bi ap dung sai quy trinh sau khi giam gia.");
    }

    @Test(description = "BP_05 (Lật D4-False): Dùng mã FAKE -> Báo lỗi")
    public void testPath5_FakeCoupon() {
        Assert.assertThrows(IllegalArgumentException.class,
            () -> processor.calculateTotal(itemsNhieuTien, "FAKE", "NONE", "CASH"));
    }

    @Test(description = "BP_06 (Lật D5): Thành viên GOLD -> Giảm 5%")
    public void testPath6_GoldMember() {
        double total = processor.calculateTotal(itemsNhieuTien, "", "GOLD", "CASH");
        Assert.assertEquals(total, 570_000.0, "Loi Path 6: Giam gia thanh vien GOLD khong chinh xac.");
    }

    @Test(description = "BP_07 (Lật D6): Thành viên PLATINUM -> Giảm 10%")
    public void testPath7_PlatinumMember() {
        double total = processor.calculateTotal(itemsNhieuTien, "", "PLATINUM", "CASH");
        Assert.assertEquals(total, 540_000.0, "Loi Path 7: Giam gia thanh vien PLATINUM khong chinh xac.");
    }

    @Test(description = "BP_08 (Lật D7, D8): Mua <500k, thanh toán Online -> Phí ship 30k")
    public void testPath8_ShipOnline() {
        double total = processor.calculateTotal(itemsItTien, "", "NONE", "ONLINE");
        Assert.assertEquals(total, 130_000.0, "Loi Path 8: Tinh sai phi ship cho thanh toan online.");
    }

    @Test(description = "BP_09 (Lật D8-False): Mua <500k, thanh toán COD -> Phí ship 20k")
    public void testPath9_ShipCod() {
        double total = processor.calculateTotal(itemsItTien, "", "NONE", "COD");
        Assert.assertEquals(total, 120_000.0, "Loi Path 9: Tinh sai phi ship cho thanh toan COD.");
    }

    // =========================================================
    // PHẦN 2: THỰC THI CÁC TEST CASE CỦA MC/DC
    // =========================================================

    @Test(description = "MCDC_Row2: Coupon truyền NULL -> Không giảm giá (C1=False)")
    public void testMCDC_CouponNull() {
        // C1=F, C2=X, C3=X -> Phải không giảm giá
        double total = processor.calculateTotal(itemsNhieuTien, null, "NONE", "CASH");
        Assert.assertEquals(total, 600_000.0, "Loi MC/DC: He thong khong xu ly duoc coupon null.");
    }

    @Test(description = "MCDC_Row3: Coupon rỗng -> Không giảm giá (C2=False)")
    public void testMCDC_CouponEmpty() {
        // C1=T, C2=F, C3=X -> Phải không giảm giá
        double total = processor.calculateTotal(itemsNhieuTien, "", "NONE", "CASH");
        Assert.assertEquals(total, 600_000.0, "Loi MC/DC: He thong khong xu ly duoc coupon rong.");
    }
}