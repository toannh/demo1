package vn.chodientu.util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ThienPhu
 * @since Jul 30, 2013
 */
public class ListUtils {

    public static List orderByArray(java.util.List list, List order) {
        try {
            java.util.List newList = new ArrayList();
            for (Object key : order) {
                for (Object obj : list) {
                    if (obj.getClass().getMethod("getId").invoke(obj).equals(key)) {
                        newList.add(obj);
                    }
                }
            }
            return newList;
        } catch (Exception ex) {
            return list;
        }
    }
}
