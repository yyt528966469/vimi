package com.wm.edu.utils.common;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class AttaUtils {

    private static SimpleDateFormat sdf=new SimpleDateFormat("yyMMddHHmmss");
    private static SimpleDateFormat ymd=new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat ymdhms=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static Properties properties=null;

    static {//加载配置文件
        URL url=AttaUtils.class.getClassLoader().getResource("config.properties");
        Properties props = new Properties();
        InputStream in= null;
        try {
            String p= url.getPath();
            if(StringUtils.isNotEmpty(p)){
                p=p.replace("%20"," ");
            }
            in = new FileInputStream(new File(p));
            props.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        properties=props;
    }

    /**
     * 字符串转时间
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate){
        Date date=null;
        try {
            date=ymdhms.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    public static String getAllUrl(HttpServletRequest request,String url){
        String reuquet_http=getConfigValueByKey("reuquet_http");
        if(url.contains(reuquet_http)){
            return url;
        }
        String host=request.getHeader("Host");
        String path=request.getContextPath();
        return reuquet_http+host+path+url;
    }

    /**
     * 将指定格式的字符串转为日期时间对象
     *
     * @param strDate
     *            时间
     * @param format
     *            格式
     * @return 时间 date
     */
    public static Date formatToDate(String strDate, String format) {
        if (strDate == null) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    public static Date strToshotDate(String strDate){
        return formatToDate(strDate,"yyyy-MM-dd");
    }

    /**
     * 验证日期格式是否合法
     *
     * @param strDate
     *            需要验证的日期字符串
     * @param format
     *            指定日期字符串的格式，如果不输入默认yyyy-MM-dd
     * @return
     */
    public static boolean isDate(String strDate, String... format) {
        String fstr = "yyyy-MM-dd";
        if (format.length == 1) {
            fstr = format[0];
        }
        // 指定日期格式为四位年/两位月份/两位日期，注意yyyy-MM-dd区分大小写；
        SimpleDateFormat formatdate = new SimpleDateFormat(fstr);
        // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007-02-29会被接受，并转换成2007-03-01
        formatdate.setLenient(false);
        try {
            formatdate.parse(strDate);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * 时间转字符串
     * @param date
     * @return
     */
    public static String dateToStr(Date date){
        return ymdhms.format(date);
    }

    public static String getNo(){
        String dateStr=sdf.format(new Date());
        int num=(int)(Math.random()*10000);
        int count=(int)(Math.random()*100);
        return dateStr+num+count;
    }

    public static String getOrderId(){
        String dateStr=sdf.format(new Date());
        String str=System.currentTimeMillis()+"";
        dateStr=dateStr+str.substring(str.length()-3,str.length());
        int num=(int)(Math.random()*1000);
        if(num<10){
            dateStr+="00"+num;
        }else if(num<100){
            dateStr+="0"+num;
        }else {
            dateStr+=num;
        }
        return dateStr;
    }

    public static void main(String[] args){
        String str=getOrderId();
        System.out.println(str);
       /* String str="è¿å»çå°å¤©";

        //buf_iso88591 = new byte[0];
        try {
            byte[] buf_iso88591 = str.getBytes("iso-8859-1");
            String sGbk = new String(buf_iso88591, "utf-8");
            System.out.println(sGbk);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/

        //String abc=getConfigValueByKey("abc");
        //System.out.println(abc);

    }





    /**
     *生成不重复随机数
     * @param count 随机数范围
     * @param size 产生随机数个数
     * @return
     */
    public static Set<Integer> randomArr(int count, int size){
        //int[] arr=new int[size];
        Set<Integer> set=new HashSet<Integer>();
        while (true){
            int index=(int)(Math.random()*count);
            set.add(index);
            if(set.size()>=size){
                break;
            }
        }
        //System.out.println(set);
        return set;

    }


    public static String getConfigValueByKey(String key){
        String result=properties.getProperty(key);
        return result;
    }




}
