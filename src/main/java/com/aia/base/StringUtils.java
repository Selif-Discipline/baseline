package com.aia.base;

import javax.servlet.http.HttpServletRequest;
import java.security.SecureRandom;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 *
 * @author liuxun
 */

public class StringUtils{

    /**
     * 空字符串
     */
    private static final String NULLSTR = "";

    /**
     * 下划线
     */
    private static final char SEPARATOR = '_';

    /**
     * 获取参数不为空值
     *
     * @param value defaultValue 要判断的value
     * @return value 返回值
     */
    public static <T> T nvl(T value, T defaultValue) {
        return value != null ? value : defaultValue;
    }

    /**
     * * 判断一个Collection是否为空， 包含List，Set，Queue
     *
     * @param coll 要判断的Collection
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(Collection<?> coll) {
        return isNull(coll) || coll.isEmpty();
    }

    /**
     * * 判断一个Collection是否非空，包含List，Set，Queue
     *
     * @param coll 要判断的Collection
     * @return true：非空 false：空
     */
    public static boolean isNotEmpty(Collection<?> coll) {
        return !isEmpty(coll);
    }

    /**
     * * 判断一个对象数组是否为空
     *
     * @param objects 要判断的对象数组
     *                * @return true：为空 false：非空
     */
    public static boolean isEmpty(Object[] objects) {
        return isNull(objects) || (objects.length == 0);
    }

    /**
     * * 判断一个对象数组是否非空
     *
     * @param objects 要判断的对象数组
     * @return true：非空 false：空
     */
    public static boolean isNotEmpty(Object[] objects) {
        return !isEmpty(objects);
    }

    /**
     * * 判断一个Map是否为空
     *
     * @param map 要判断的Map
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return isNull(map) || map.isEmpty();
    }

    /**
     * * 判断一个Map是否为空
     *
     * @param map 要判断的Map
     * @return true：非空 false：空
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * * 判断一个字符串是否为空串
     *
     * @param str String
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(String str) {
        return isNull(str) || NULLSTR.equals(str.trim());
    }

    /**
     * * 判断一个字符串是否为非空串
     *
     * @param str String
     * @return true：非空串 false：空串
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * * 判断一个对象是否为空
     *
     * @param object Object
     * @return true：为空 false：非空
     */
    public static boolean isNull(Object object) {
        return object == null;
    }

    /**
     * * 判断一个对象是否非空
     *
     * @param object Object
     * @return true：非空 false：空
     */
    public static boolean isNotNull(Object object) {
        return !isNull(object);
    }

    /**
     * * 判断一个对象是否是数组类型（Java基本型别的数组）
     *
     * @param object 对象
     * @return true：是数组 false：不是数组
     */
    public static boolean isArray(Object object) {
        return isNotNull(object) && object.getClass().isArray();
    }

    /**
     * 去空格
     */
    public static String trim(String str) {
        return (str == null ? "" : str.trim());
    }

    /**
     * 截取字符串
     *
     * @param str   字符串
     * @param start 开始
     * @return 结果
     */
    public static String substring(final String str, int start) {
        if (str == null) {
            return NULLSTR;
        }

        if (start < 0) {
            start = str.length() + start;
        }

        if (start < 0) {
            start = 0;
        }
        if (start > str.length()) {
            return NULLSTR;
        }

        return str.substring(start);
    }

    /**
     * 截取字符串
     *
     * @param str   字符串
     * @param start 开始
     * @param end   结束
     * @return 结果
     */
    public static String substring(final String str, int start, int end) {
        if (str == null) {
            return NULLSTR;
        }

        if (end < 0) {
            end = str.length() + end;
        }
        if (start < 0) {
            start = str.length() + start;
        }

        if (end > str.length()) {
            end = str.length();
        }

        if (start > end) {
            return NULLSTR;
        }

        if (start < 0) {
            start = 0;
        }
        if (end < 0) {
            end = 0;
        }

        return str.substring(start, end);
    }

    /**
     * 格式化文本, {} 表示占位符<br>
     * 此方法只是简单将占位符 {} 按照顺序替换为参数<br>
     * 如果想输出 {} 使用 \\转义 { 即可，如果想输出 {} 之前的 \ 使用双转义符 \\\\ 即可<br>
     * 例：<br>
     * 通常使用：format("this is {} for {}", "a", "b") -> this is a for b<br>
     * 转义{}： format("this is \\{} for {}", "a", "b") -> this is \{} for a<br>
     * 转义\： format("this is \\\\{} for {}", "a", "b") -> this is \a for b<br>
     *
     * @param template 文本模板，被替换的部分用 {} 表示
     * @param params   参数值
     * @return 格式化后的文本
     */
//    public static String format(String template, Object... params) {
//        if (isEmpty(params) || isEmpty(template)) {
//            return template;
//        }
//        return StrFormatter.format(template, params);
//    }

    /**
     * 下划线转驼峰命名
     */
    public static String toUnderScoreCase(String s) {
        if (s == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            boolean nextUpperCase = true;

            if (i < (s.length() - 1)) {
                nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
            }

            if ((i > 0) && Character.isUpperCase(c)) {
                if (!upperCase || !nextUpperCase) {
                    sb.append(SEPARATOR);
                }
                upperCase = true;
            } else {
                upperCase = false;
            }

            sb.append(Character.toLowerCase(c));
        }

        return sb.toString();
    }

    /**
     * 是否包含字符串
     *
     * @param str  验证字符串
     * @param strs 字符串组
     * @return 包含返回true
     */
    public static boolean inStringIgnoreCase(String str, String... strs) {
        if (str != null && strs != null) {
            for (String s : strs) {
                if (str.equalsIgnoreCase(trim(s))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 将下划线大写方式命名的字符串转换为驼峰式。如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。 例如：HELLO_WORLD->HelloWorld
     *
     * @param name 转换前的下划线大写方式命名的字符串
     * @return 转换后的驼峰式命名的字符串
     */
    public static String convertToCamelCase(String name) {
        StringBuilder result = new StringBuilder();
        // 快速检查
        if (name == null || name.isEmpty()) {
            // 没必要转换
            return "";
        } else if (!name.contains("_")) {
            // 不含下划线，仅将首字母大写
            return name.substring(0, 1).toUpperCase() + name.substring(1);
        }
        // 用下划线将原始字符串分割
        String[] camels = name.split("_");
        for (String camel : camels) {
            // 跳过原始字符串中开头、结尾的下换线或双重下划线
            if (camel.isEmpty()) {
                continue;
            }
            // 首字母大写
            result.append(camel.substring(0, 1).toUpperCase());
            result.append(camel.substring(1).toLowerCase());
        }
        return result.toString();
    }

    public final static String PREFIX_CHAR = "*****";

    /**
     * java中对Byte字符数组定长截取的方法
     * <p>
     * java中是用Unicode编码的方式计数，一个中文汉字以Bytes的方式是两个字节，而用Unicode的方式是一个字
     * <p>
     * 将字符串转换为Bytes【】数组，编码方式为“Unicode”，转出来的数组中的第一位和第二位是-2和-1，真正的内容从第三位开始，
     * 所有的字符都占用两个字节的空间，如果是英文和字母则第一个空间为0，如果是汉字就不是0，通过这样的方法就可以正确处理截取字符串的内容了
     *
     * @param str
     * @param length
     * @return
     * @throws Exception
     */
    public static String getUnicodeSubStr(String str, int length) throws Exception {
        byte[] bytes = str.getBytes("Unicode");
        int n = 0; // 表示当前的字节数
        int i = 2; // 要截取的字节数，从第3个字节开始

        // Java内部采用的都是 Unicode 16（UCS2）编码，在这种编码中所有的字符都是两个字符
        // 由于上面生成的字节数组中前两个字节是标志位，bytes[0] = -2，bytes[1] = -1，因此，要从第三个字节开始扫描.
        // 对于一个英文或数字字符，UCS2编码的第二个字节是相应的ASCII，第一个字节是0，如a的UCS2编码是0 97
        // 而汉字两个字节都不为0，因此，可以利于UCS2编码的这个规则来计算实际的字节数
        for (; i < bytes.length && n < length; i++) {
            // 奇数位置，如3、5、7等，为UCS2编码中两个字节的第二个字节
            if (i % 2 == 1) {
                n++; // 在UCS2第二个字节时n加1
            } else {
                // 当UCS2编码的第一个字节不等于0时，该UCS2字符为汉字，一个汉字算两个字节
                if (bytes[i] != 0) {
                    n++;
                }
            }
        }
        // 如果i为奇数时，处理成偶数
        if (i % 2 == 1) {
            // 该UCS2字符是汉字时，去掉这个截一半的汉字
            if (bytes[i - 1] != 0) {
                i = i - 1;
                // 该UCS2字符是字母或数字，则保留该字符
            } else {
                i = i + 1;
            }
        }
        return new String(bytes, 0, i, "Unicode");
    }

    /**
     * 按指定字节数截取字符串
     * <p>
     * DB2中LONG VARCHAR 允许的数据最大长度为32700字节，所以对LONG类型的字段进行内容截取，截取长度不超过27000
     *
     * @param str
     * @param maxBytesNums
     * @return
     */
    public static String getBytenumsSubStr(String str, int maxBytesNums, String suffix) {
        if (str == null) {
            return null;
        }
        if (str.getBytes().length <= maxBytesNums) {
            return str;
        }
        return new String(Arrays.copyOfRange(str.getBytes(), 1, maxBytesNums)) + (suffix == null ? "" : suffix);
    }

    /**
     * 中文字符串编码转换
     *
     * @param request
     * @param realFileName
     * @return
     */
    public String getStr(HttpServletRequest request, String realFileName) {
        String browName = null;
        try {
            String clientInfo = request.getHeader("User-agent");
            if (clientInfo != null && clientInfo.indexOf("MSIE") > 0) {//
                // IE采用URLEncoder方式处理
                if (clientInfo.indexOf("MSIE 6") > 0 || clientInfo.indexOf("MSIE 5") > 0) {// IE6，用GBK，此处实现由局限性
                    browName = new String(realFileName.getBytes("UTF-8"), "ISO-8859-1");
                } else {// ie7+用URLEncoder方式
                    browName = java.net.URLEncoder.encode(realFileName, "UTF-8");
                }
            } else if (clientInfo != null && clientInfo.indexOf("HttpClient") > 0) {
                browName = realFileName;
            } else {// 其他浏览器
                browName = new String(realFileName.getBytes("UTF-8"), "ISO-8859-1");
            }
        } catch (Exception e) {
//            e.printStackTrace();
            browName = realFileName;
        }
        return browName;
    }



    /**
     * 图片转化成base64字符串
     *
     * @param imgFile
     * @return
     */
//    public static String getImageStr(String imgFile) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
//        // String imgFile = "C:/Users/Star/Desktop/test.png";// 待处理的图片
//        InputStream in = null;
//        byte[] data = null;
//        // 读取图片字节数组
//        try {
//            in = new FileInputStream(imgFile);
//            data = new byte[in.available()];
//            in.read(data);
//            in.close();
//        } catch (IOException e) {
//            log.error("IO异常", e);
//        }
//        // 对字节数组Base64编码
//        Base64Encoder encoder = new Base64Encoder();
//        return encoder.encode(data);// 返回Base64编码过的字节数组字符串
//    }

    protected static List<String> allowFileType = new ArrayList<String>();

    static {
        allowFileType.add("txt");
        allowFileType.add("xls");
        allowFileType.add("doc");
        allowFileType.add("pdf");
        allowFileType.add("jpg");
        allowFileType.add("png");
        allowFileType.add("bmp");
        allowFileType.add("csv");
        allowFileType.add("docx");
        allowFileType.add("xlsx");
        allowFileType.add("xml");
        allowFileType.add("html");
        allowFileType.add("zip");
        allowFileType.add("rar");
    }

    private static SecureRandom random = new SecureRandom();

//    public static String verifyMsg(String message) {
//        try {
//            message = obj2String(message);
//            if (message.indexOf("%0d") != -1) {
//                message = message.replaceAll("%0d", "");
//            }
//            if (message.indexOf("%0a") != -1) {
//                message = message.replaceAll("%0a", "");
//            }
//            if (message.indexOf("\r") != -1) {
//                message = message.replaceAll("\r", "");
//            }
//            if (message.indexOf("\n") != -1) {
//                message = message.replaceAll("\n", "");
//            }
//            message.replace("|", "u007C").replace("&", "").replace(";", "").replace("$", "").replace("%", "")
//                .replace("@", "").replace("'", "").replace("\"", "").replace("\'", "").replace("<", "").replace(">", "")
//                .replace("(", "").replace(")", "").replace("+", "").replace("\r", "").replace("\n", "").replace(",", "")
//                .replace("\\", "").replace(" ", "").replace("  ", "");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "";
//        }
//        return message;
//    }

    public static String obj2String(Object obj) {
        if (null == obj) {
            return "";
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        String ret = String.valueOf(obj);
        return ret.equals("null") ? "" : ret;
    }

    public static String objArray2String(Object[] obj) {
        if (null == obj) {
            return "";
        }
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < obj.length; i++) {
            str.append(obj2String(obj[i]) + ",");
        }
        str.deleteCharAt(str.length() - 1);
        String ret = str.toString();
        return ret.equals("null") ? "" : ret;
    }

    public static String getRandom(int length) {
        int max = 999999;
        int min = 100000;
        int s = random.nextInt(max) % (max - min + 1) + min;
        return String.valueOf(s);
    }

    public static List<String> getMatcherStr(String s, String fmt) {
        List<String> strs = new ArrayList<>();
        Pattern p = Pattern.compile(fmt);
        Matcher m = p.matcher(s);
        while (m.find()) {
            strs.add(m.group(m.groupCount()));
        }
        return strs;
    }

    /**
     * 获取byte字符的8位长度（不足八位左补零）
     *
     * @param content
     * @return
     */
    public static String getByteLength8(String content) {
        String length8 = "";
        if (null == content) {
            return length8;
        }
        String length = (content.length() + 8) + "";
        int size = length.length();
        for (int i = 0; i < 8 - size; i++) {
            length = "0" + length;
        }
        length8 = length;
        return length8;
    }

    /**********************************************************************************
     **
     ** 小驼峰
     **
     **********************************************************************************/

    private static Pattern linePattern = Pattern.compile("_(\\w)");

    /**
     * * 下划线转驼峰
     **/
    public static String lineToHump(String str) {
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 驼峰转下划线(简单写法，效率低于{@link #humpToLine2(String)})
     */
    public static String humpToLine(String str) {
        return str.replaceAll("[A-Z]", "_$0").toLowerCase();
    }

    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    /**
     * 驼峰转下划线,效率比上面高
     */
    public static String humpToLine2(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

}