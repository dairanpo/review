package com.tobetek.review.util;

public class StringUtil {

    /**
     * 根据大写字符，用splitStr分割
     * @param str
     * @param splitStr
     * @return
     */
    public static String getSplitName(String str, String splitStr) {
        for(int i=str.length()-2; i > 0; i--) {
            if(str.charAt(i) > 64 && str.charAt(i) < 91) {
                str = str.substring(0, i) + splitStr + str.substring(i);
            }
        }
        return str.toLowerCase();
    }

    /**
     * 首写字符大写
     * @param str
     * @return
     */
    public static String firstCodeUpper(String str) {
        return ( new StringBuilder().append( Character.toUpperCase(str.charAt(0)) ).append(str.substring(1)) ).toString();
    }

    /**
     * 首写字符小写
     * @param str
     * @return
     */
    public static String firstCodeLower(String str) {
        return ( new StringBuilder().append( Character.toLowerCase(str.charAt(0)) ).append(str.substring(1)) ).toString();
    }
}
