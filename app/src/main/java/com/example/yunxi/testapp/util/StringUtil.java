package com.example.yunxi.testapp.util;

import java.util.List;

public class StringUtil {

    public static boolean isEmpty(String str) {
        return str == null || str.equals("");
    }

    public static String toCommonString(List<?> objects) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < objects.size(); i++) {
            sb.append(objects.get(i).toString());
            sb.append(',');
        }
        return sb.substring(0, sb.length() - 1);
    }

    /**
     * 将转义后的html解码为正常的html
     *
     * @param str
     * @return
     */
    public static String decodeToHtml(String str) {
        return str.replace("&lt;", "<")
                .replace("&gt;", ">")
                .replace("&amp;", "&")
                .replace("&quot;", "\"")
                .replace("&#39;", "'");

    }
}
