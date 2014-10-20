package vn.chodientu.util;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ThienPhu
 * @since Jun 24, 2013
 */
public class TextUtils {

    public static String createAlias(String str) {
        if (str == null || str.equals("")) {
            return "";
        }
        return TextUtils.removeDiacritical(str).replaceAll("\\W", "-").toLowerCase();
    }

    /**
     * Cắt hết dấu tiếng Việt
     *
     * @param str
     * @return
     */
    public static String removeDiacritical(String str) {
        if (str == null) {
            return str;
        }
        str = str.replaceAll("(à|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|ắ|ặ|ẳ|ẵ)", "a");
        str = str.replaceAll("(è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ)", "e");
        str = str.replaceAll("(ì|í|ị|ỉ|ĩ)", "i");
        str = str.replaceAll("(ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|ớ|ợ|ở|ỡ)", "o");
        str = str.replaceAll("(ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ)", "u");
        str = str.replaceAll("(ỳ|ý|ỵ|ỷ|ỹ)", "y");
        str = str.replaceAll("(đ)", "d");
        str = str.replaceAll("(À|Á|Ạ|Ả|Ã|Â|Ầ|Ấ|Ậ|Ẩ|Ẫ|Ă|Ằ|Ắ|Ặ|Ẳ|Ẵ)", "A");
        str = str.replaceAll("(È|É|Ẹ|Ẻ|Ẽ|Ê|Ề|Ế|Ệ|Ể|Ễ)", "E");
        str = str.replaceAll("(Ì|Í|Ị|Ỉ|Ĩ)", "I");
        str = str.replaceAll("(Ò|Ó|Ọ|Ỏ|Õ|Ô|Ồ|Ố|Ộ|Ổ|Ỗ|Ơ|Ờ|Ớ|Ợ|Ở|Ỡ)", "O");
        str = str.replaceAll("(Ù|Ú|Ụ|Ủ|Ũ|Ư|Ừ|Ứ|Ự|Ử|Ữ)", "U");
        str = str.replaceAll("(Ỳ|Ý|Ỵ|Ỷ|Ỹ)", "Y");
        str = str.replaceAll("(Đ)", "D");
        return str;
    }

    /**
     * remove tag
     *
     * @param string
     * @return
     */
    public static String removeTags(String string) {
        if (string == null || string.length() == 0) {
            return string;
        }
        Pattern pattern = Pattern.compile("<.+?>");
        Matcher m = pattern.matcher(string);
        return m.replaceAll("");
    }

    /**
     * return false neu la tieng viet khong dau, return true neu la tieng viet
     * co dau
     *
     * @param text
     * @return
     */
    public static boolean detectVietnamese(String text) {
        if (text == null ? TextUtils.removeDiacritical(text) == null : text.equals(TextUtils.removeDiacritical(text))) {
            return false;
        } else {
            return true;
        }
    }

    public static String numberFormat(Double price) {
        String str = "0";
        try {
            NumberFormat nf = NumberFormat.getInstance(new Locale("vi", "VN"));
            str = nf.format(price);
        } catch (Exception ex) {
        }
        return str;
    }

    public static String percentFormat(Double percent) {
        String str = "0";
        try {
            NumberFormat nf = NumberFormat.getPercentInstance(new Locale("vi", "VN"));
            str = nf.format(percent);
        } catch (Exception ex) {
        }
        return str.replace("%", "");
    }

    public static String percentFormat(Long startPrice, Long sellPrice, Boolean discount, Long discountPrice, Integer discountPercent) {
        double percent = 0;
        if (!discount && startPrice > sellPrice) {
            percent = (double) (startPrice - sellPrice) / startPrice;
        } else {
            if (startPrice <= sellPrice) {
                startPrice = sellPrice;
            }
            double price = 0;
            if (discountPercent > 0) {
                price = (100 - discountPercent * 1.0);
                price = (double) sellPrice * price / 100;
            } else {
                price = (double) sellPrice - discountPrice;
            }
            percent = (double) (startPrice - price) / startPrice;
        }
        percent *= 100;
        percent = Math.ceil(percent);
        return TextUtils.numberFormat(percent);
    }

    public static String startPrice(Long startPrice, Long sellPrice, Boolean discount) {
        if (!discount && startPrice <= sellPrice) {
            return "0";
        }
        if (discount && startPrice <= sellPrice) {
            startPrice = sellPrice;
        }
        if (startPrice > 0 && startPrice >= sellPrice) {
            return numberFormat(startPrice * 1.0);
        }
        return "0";
    }

    public static String sellPrice(Long sellPrice, Boolean discount, Long discountPrice, Integer discountPercent) {
        if (discount) {
            if (discountPercent > 0) {
                sellPrice = sellPrice * (100 - discountPercent) / 100;
            } else {
                sellPrice = sellPrice - discountPrice;
            }
        }
        return numberFormat(sellPrice * 1.0);
    }

    public static String discountPrice(Long startPrice, Long sellPrice, Boolean discount, Long discountPrice, Integer discountPercent) {
        if (discount && startPrice <= sellPrice) {
            startPrice = sellPrice;
        }
        if (discount) {
            if (discountPercent > 0) {
                sellPrice = sellPrice * (100 - discountPercent) / 100;
            } else {
                sellPrice = sellPrice - discountPrice;
            }
        }
        double price = (startPrice - sellPrice) * 1.0;
        price = (price > 0 ? price : 0);
        return numberFormat(price);
    }

    public static String genPasswordRadom() {
        int hash = 3;
        long current = new Date().getTime();
        hash = 83 * hash + (int) (current ^ (current >>> 32));

        if (hash < 0) {
            hash = 0 - hash;
        }

        char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890".toCharArray();
        String str = String.valueOf(alphabet[hash % 50]);
        hash = (hash / 50);
        str = str + String.valueOf(alphabet[hash % 40]);
        hash = (hash / 50);
        str = str + String.valueOf(alphabet[hash % 50]);
        hash = (hash / 50);
        str = str + String.valueOf(alphabet[hash % 50]);
        hash = (hash / 50);
        str = str + String.valueOf(alphabet[hash % 50]);
        hash = (hash / 50);

        str = str + String.valueOf(hash);
        return str;
    }

    /**
     * validate email nhap vao co dung dinh dang ko
     *
     * @param email email can check
     * @return true/false
     */
    public static boolean validateEmailString(String email) {
        String validEmail = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(validEmail);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * validate so dien thoai nhap vao co dung dinh dang ko (bat dau bang so 0,
     * dai tu 8-11 ki tu
     *
     * @param phone email can check
     * @return true/false
     */
    public static boolean validatePhoneNumber(String phone) {
        String validPhone = "^0\\d{7,10}$";
        Pattern pattern = Pattern.compile(validPhone);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    /**
     * Xóa ký tự đặc biệt
     *
     * @param str
     * @return
     */
    public static String removeSpecialCharacters(String str) {
//        str = str.replaceAll("[\":!&^/\\*|+-~()?{}\\[\\]\\\\]", " ");
        str = str.replaceAll("\\\\", "\\\\\\\\");
        str = str.replaceAll("\"", "\\\"");
        str = str.replaceAll("\\:", "\\\\:");
        str = str.replaceAll("\\!", "\\\\!");
        str = str.replaceAll("\\&", "\\\\&");
        str = str.replaceAll("\\^", "\\\\^");
        str = str.replaceAll("\\/", "\\\\/");
        str = str.replaceAll("\\*", "\\\\*");
        str = str.replaceAll("\\|", "\\\\|");
        str = str.replaceAll("\\+", " ");
        str = str.replaceAll("\\-", "\\\\-");
        str = str.replaceAll("\\~", "\\\\~");
        str = str.replaceAll("\\(", "\\\\(");
        str = str.replaceAll("\\)", "\\\\)");
        str = str.replaceAll("\\?", "\\\\?");
        str = str.replaceAll("\\{", "\\\\}");
        str = str.replaceAll("\\}", "\\\\}");
        str = str.replaceAll("\\[", "\\\\[");
        str = str.replaceAll("\\]", "\\\\]");
        str = str.replaceAll("\\%", "\\\\%");
        str = str.replaceAll("\\`", "\\\\`");
        str = str.replaceAll("\\$", "\\\\$");
        str = str.replaceAll("\\;", "\\\\;");
        str = str.replaceAll("\\'", "\\\\'");
        str = str.replaceAll("\\=", "\\\\=");
        str = str.replaceAll("\\#", "\\\\#");
        return str;
    }

    public static String createShopURL(String str) {
        str = str.replaceAll("[^a-zA-Z0-9_]", "");
        return str.toLowerCase();
    }

    public static String getClientIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_CLUSTER_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_FORWARDED");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_VIA");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("REMOTE_ADDR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * Định dạng số điện thoại
     *
     * @param phone
     * @return
     */
    public static String formatPhoneNumber(String phone) {
        return phone.format("(%s) %s-%s", phone.substring(0, 3), phone.substring(3, 6), phone.substring(6, 10));
    }

    /**
     * get domain by url
     *
     * @param url
     * @return
     */
    public static String getDomainName(String url) {
        String domainName = new String(url);
        int index = domainName.indexOf("://");
        if (index != -1) {
            domainName = domainName.substring(index + 3);
        }
        index = domainName.indexOf('/');

        if (index != -1) {
            domainName = domainName.substring(0, index);
        }

        domainName = domainName.replaceFirst("^www.*?\\.", "");
        return domainName;
    }

    public static long getTime(long time, boolean endday) {
        try {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            cal.setTime(new Date(time));
            return sdfTime.parse(cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DAY_OF_MONTH) + (endday ? " 23:59" : " 00:00")).getTime();
        } catch (Exception e) {
            return 0;
        }
    }

    public static long lastDayOfWeek(long time) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(time));
        int day = cal.get(Calendar.DAY_OF_YEAR);
        while (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
            cal.set(Calendar.DAY_OF_YEAR, ++day);
        }
        return cal.getTime().getTime();
    }

    public static long firstDayOfWeek(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(time));
        calendar.set(Calendar.DAY_OF_WEEK, 1);
        return calendar.getTime().getTime();
    }

    public static long firstDayOfMonth(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(time));
        calendar.set(Calendar.DATE, 1);
        return calendar.getTime().getTime();
    }

    public static long lastDayOfMonth(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(time));
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        return calendar.getTime().getTime();
    }
}
