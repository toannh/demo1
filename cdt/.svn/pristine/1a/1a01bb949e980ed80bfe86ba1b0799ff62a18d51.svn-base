package vn.chodientu.util;

import com.google.gson.Gson;
import java.lang.reflect.Type;

/**
 *
 * @author phugt
 */
public class JsonUtils {

    private static final Gson gson = new Gson();

    public static String encode(Object obj) {
        return gson.toJson(obj);
    }

    public static Object decode(String json, Type type) {
        return gson.fromJson(json, type);
    }
}
