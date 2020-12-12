package com.fydk.productone.common;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 *
 * @Description : 字符串工具类
 * @time 创建时间 : 2018年7月19日
 * @author : FanHua
 * @Copyright (c) 2018 一碑科技
 * @version
 */
public class StringUtils {
    
    public static DecimalFormat df2 = new java.text.DecimalFormat("#.00");

	/**
	 * 将字符串有某种编码转变成另一种编码
	 * @param string 编码的字符串
	 * @param originCharset 原始编码格式
	 * @param targetCharset 目标编码格式
	 * @return String 编码后的字符串
	 */
	public static String encodeString(String string,Charset originCharset,Charset targetCharset){
		return string=new String(string.getBytes(originCharset),targetCharset);
	}

	/**
	 * URL编码
	 * @param string 编码字符串
	 * @param charset 编码格式
	 * @return String
	 */
	@SuppressWarnings("deprecation")
	public static String encodeUrl(String string,String charset){
		if(null!=charset&&!charset.isEmpty()){
			try {
				return URLEncoder.encode(string,charset);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return URLEncoder.encode(string);
	}

	/**
	 * URL编码
	 * @param string 解码字符串
	 * @param charset 解码格式
	 * @return String
	 */
	@SuppressWarnings("deprecation")
	public static String decodeUrl(String string,String charset){
		if(null!=charset&&!charset.isEmpty()){
			try {
				return URLDecoder.decode(string,charset);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return null;
			}
		}
		return URLDecoder.decode(string);
	}
	/**
	 * 判断字符串是否是空的
	 * 方法摘自commons.lang
	 * <pre>
     * StringUtils.isEmpty(null)      = true
     * StringUtils.isEmpty("")        = true
     * StringUtils.isEmpty(" ")       = false
     * StringUtils.isEmpty("bob")     = false
     * StringUtils.isEmpty("  bob  ") = false
     * </pre>
	 * @param str
	 * @return boolean
	 */
	public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

	/**
	 * 判断字符串不为空的
	 * 方法摘自commons.lang
	 * <pre>
     * StringUtils.isNotEmpty(null)      = false
     * StringUtils.isNotEmpty("")        = false
     * StringUtils.isNotEmpty(" ")       = true
     * StringUtils.isNotEmpty("bob")     = true
     * StringUtils.isNotEmpty("  bob  ") = true
     * </pre>
	 * @param str
	 * @return boolean
	 */
	public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

	 /**
     * <p>判断字符串是否是""," ",null,注意和isEmpty的区别</p>
     * 方法摘自commons.lang
     * <pre>
     * StringUtils.isBlank(null)      = true
     * StringUtils.isBlank("")        = true
     * StringUtils.isBlank(" ")       = true
     * StringUtils.isBlank("bob")     = false
     * StringUtils.isBlank("  bob  ") = false
     * </pre>
     */
    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    /**
     * <p>判断字符串不是""," ",null,注意和isNotEmpty的区别</p>
     * 方法摘自commons.lang
     * <pre>
     * StringUtils.isNotBlank(null)      = false
     * StringUtils.isNotBlank("")        = false
     * StringUtils.isNotBlank(" ")       = false
     * StringUtils.isNotBlank("bob")     = true
     * StringUtils.isNotBlank("  bob  ") = true
     * </pre>
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
	 * 首字母转大写
	 * @param str
	 * @return
	 */
	public static String firstCharToUpperCase(String str){
		if(Character.isUpperCase(str.charAt(0))){
			return str;
		}else{
			char[] cs=str.toCharArray();
        	cs[0]-=32;
        	return String.valueOf(cs);
        }
	}

	 /**
		 * 首字母转小写
		 * @param str
		 * @return
		 */
		public static String firstCharToLowerCase(String str){
			if(Character.isLowerCase(str.charAt(0))){
				return str;
			}else{
				char[] cs=str.toCharArray();
	        	cs[0]+=32;
	        	return String.valueOf(cs);
	        }
		}

		public static String toString(Object obj) {
			if(obj == null) {
				return "";
			}else {
				return obj.toString();
			}
		}

	/**
	 * 去字符串两端空格
	 * @param str
	 * @return
	 */
	public static String trim(String str){
		return str.trim();
	}

	/**
	 * 字符串判断是否相等
	 * @param str1
	 * @param str2
	 * @return
	 */
	public static boolean equals(String str1, String str2){
		return str1.equalsIgnoreCase(str2);
	}
	/**
	 * set集合转逗号分割的字符串
	 * @param set
	 * @return
	 */
	public static String setToString(Set<String> set,String division) {
        if(set == null || set.size() == 0) {
            return "";
        }
        if (isEmpty(division)) {
            division = ",";
        }

        StringBuffer sb = new StringBuffer();
        for (String string : set) {
            sb.append(division).append(string);
        }

        if (sb.length() > 0) {
            sb.deleteCharAt(0);
        }

        return sb.toString();
    }
	
	/**
	 * set集合转逗号分割的字符串
	 * @param set
	 * @return
	 */
	public static String longSetToString(Set<Long> set,String division) {
	    if(set == null || set.size() == 0) {
	        return "";
	    }
	    if (isEmpty(division)) {
	        division = ",";
	    }
	    
	    StringBuffer sb = new StringBuffer();
	    for (Long string : set) {
	        sb.append(division).append(String.valueOf(string));
	    }
	    
	    if (sb.length() > 0) {
	        sb.deleteCharAt(0);
	    }
	    
	    return sb.toString();
	}
	
	public static Set<Long> toLongSet(String target,String division) {
	    if(StringUtils.isEmpty(target)) {
	        return null;
	    }
	    if (isEmpty(division)) {
	        division = ",";
	    }
	    Set<Long> set = new HashSet<>();
	    String[] arr = target.split(division);
	    for(String loos:arr){
            set.add(Long.valueOf(loos));
        }
	    return set;
	}

    public static List<String> toStringList(String target,String division) {
        if(isEmpty(target)) {
            return new ArrayList<>();
        }
        if (isEmpty(division)) {
            division = ",";
        }
        Set<String> set = new HashSet<>();
        String[] arr = target.split(division);
        for(String loos:arr){
            set.add(loos);
        }
        return new ArrayList<>(set);
    }

    public static List<Long> toLongList(String target,String division) {
        if(isEmpty(target)) {
            return new ArrayList<>();
        }
        if (isEmpty(division)) {
            division = ",";
        }
        Set<Long> set = new HashSet<>();
        String[] arr = target.split(division);
        for(String loos:arr){
            set.add(Long.valueOf(loos));
        }
        return new ArrayList<>(set);
    }
    public static List<Integer> toIntegerList(String target,String division) {
        if(isEmpty(target)) {
            return new ArrayList<>();
        }
        if (isEmpty(division)) {
            division = ",";
        }
        Set<Integer> set = new HashSet<>();
        String[] arr = target.split(division);
        for(String loos:arr){
            set.add(Integer.valueOf(loos));
        }
        return new ArrayList<>(set);
    }
    /**
     * 将金额字符串转化成非科学计数法的形式
     * @param str
     * @return
     */
    public static String toPlainString(String str) {
        if (isEmpty(str)) {
            return "";
        }
        try {
            BigDecimal bd = new BigDecimal(str);
            return bd.toPlainString();
        } catch (Exception e) {
            return str;
        }
    }
    /**
     * 将金额字符串转化成非科学计数法的形式
     * @param str
     * @return
     */
    public static String toPlainString(double str) {
        BigDecimal bd = new BigDecimal(str);
        return bd.toPlainString();
    }
    
    /**
     * 去掉非数字字符
     * @return
     */
    public static Integer removeNotNumber(String target) {
        if (isEmpty(target)) {
            return 0;
        }
        boolean isNegative = false;
        if (target.startsWith("-")) {
            isNegative = true;
        }
        String numstr = target.replaceAll("[^\\d]+", "");
        if (isEmpty(numstr)) {
            return 0;
        }
        try {
            return isNegative?-1*Integer.valueOf(numstr):Integer.valueOf(numstr);
        } catch (Exception e) {
            return 0;
        }
    }
    
    // 获取set中的第一个元素
    public static String getFirstString(Set<String> targetset) {
        if (targetset == null) {
            return null;
        }
        
        for(String s:targetset){
            return s;
        }
        
        return null;
    }
    public static Long getFirstLong(Set<Long> targetset) {
        if (targetset == null) {
            return null;
        }
        
        for(Long s:targetset){
            return s;
        }
        
        return null;
    }

	public static boolean compareTo(String v1,String v2) {
		if (v1 == null && v2 == null) {
			return true;
		}
		if (v1 == null && v2 != null) {
			return false;
		}
		if (v1 != null && v2 == null) {
			return false;
		}
		return v1.equals(v2);
	}

	public static String place(String str, Object... params) {

		if (isEmpty(str)) {
			return str;
		}
		if (params == null || params.length == 0) {
			return str;
		}
		str = str.replaceAll("\\{\\}", "%s");
		return String.format(str, params);
	}
	
	
	/**
     * 利用正则表达式判断字符串是否是合法 Double类型,小数点后两位
     * isDouble(String str){
     *
     * @param str
     * @return
     */
    public static boolean isDouble(String str) {
        if (isEmpty(str)) {
            return false;
        }
        // 判断小数点后2位的数字的正则表达式
        Pattern pattern = Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$");
        Matcher isDouble = pattern.matcher(str);
        if (!isDouble.matches()) {
            return false;
        }
        return true;
    }
    
    
    public static String decimalFormat2(String str) {
        if (StringUtils.isEmpty(str)) {
            return "";
        }
        
        String s_tar = df2.format(Double.valueOf(str));
        if (s_tar.startsWith(".")) {
            s_tar = "0" + s_tar;
        }
        
        return s_tar;
    }
    
    public static JSONArray jySortByKey(JSONArray rsjy, String sortKey) {
        List<JSONObject> jsonValues = new ArrayList<JSONObject>();
        // 将参数json数组每一项取出，放入list
        for (int i = 0; i < rsjy.size(); i++) {
            jsonValues.add(rsjy.getJSONObject(i));
        }
        Collections.sort(jsonValues, new Comparator<JSONObject>() {
            //排序字段
            private  final String KEY_NAME = sortKey;
            //重写compare方法
            @Override
            public int compare(JSONObject a, JSONObject b) {
                String valA = new String();
                String valB = new String();
                valA = a.getString(KEY_NAME);
                valB = b.getString(KEY_NAME);
                //是升序还是降序
                return valA.compareTo(valB);
            }
        });
        
        rsjy.clear();
        for (int i = 0; i < jsonValues.size(); i++) {
            rsjy.add(jsonValues.get(i));
        }
        return rsjy;
    }
    
    
    public static String[] getStartEndRow(Integer pageIndex,Integer pageSize){
        String[] startEndRowArr = "0,0".split(",");
        
        startEndRowArr[0] = String.valueOf((pageIndex -1) * pageSize + 1);
        startEndRowArr[1] = String.valueOf(pageIndex * pageSize);
        
        return startEndRowArr;
    }
    
    public static void main(String[] args) {
        String[]  startEndRowArr = getStartEndRow(4, 18);
        
        System.out.println(startEndRowArr[0]);
        System.out.println(startEndRowArr[1]);
    }
    
    public static void main1(String[] args) {
        System.out.println(place("第一个{}第二个【{}】", 2, "哈哈"));;
        System.out.println(decimalFormat2("0.23"));
        System.out.println(decimalFormat2("0.03"));
    }

}
