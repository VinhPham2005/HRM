package com.VinhPham.hrmanagement.Utils;

import java.text.Normalizer;

public class StringUtils {
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static String removeAccent(String str) {
        String temp = Normalizer.normalize(str, Normalizer.Form.NFD);
        temp = temp.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

        temp = temp.replaceAll("đ", "d");
        temp = temp.replaceAll("Đ", "D");

        return temp;
    }


}
