package dtm;

public class PhoneValidator {
    public static boolean isValid(String phone) {
        // D1: Check null
        if (phone == null) return false;
        
        // D2: Chỉ chứa số, +, space
        if (!phone.matches("^[0-9+ ]+$")) return false;
        
        // Chuẩn hóa
        String normalized = phone.replace(" ", "");
        if (normalized.startsWith("+84")) {
            normalized = "0" + normalized.substring(3);
        }
        
        // D3: Độ dài phải là 10
        if (normalized.length() != 10) return false;
        
        // D4: Đầu số VN hợp lệ
        return normalized.matches("^(03|05|07|08|09)[0-9]{8}$");
    }
}