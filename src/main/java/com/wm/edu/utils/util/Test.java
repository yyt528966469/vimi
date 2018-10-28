package com.wm.edu.utils.util;


import com.wm.edu.model.base.Express;
import com.wm.edu.model.base.T_city;

import com.wm.edu.utils.common.AttaUtils;
import com.wm.edu.utils.http.HttpUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.http.client.ClientProtocolException;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    static int count=100;
    static String lock="111";
    public static  void main(String[] args) throws IOException, URISyntaxException {
        List<Express> list=new ArrayList<>();
        URL res=Test.class.getClassLoader().getResource("123.json");
        String input=FileUtils.readFileToString(new File(res.toURI().getPath()),"UTF-8");
        //System.out.println(input);
        JSONArray jsonArray=JSONArray.fromObject(input);
        for (int i=0;i<jsonArray.size();i++){
            JSONObject jsonObject=jsonArray.getJSONObject(i);
            String expressname=jsonObject.getString("expressname");
            String expresskey=jsonObject.getString("expresskey");
            Express express=new Express();
            express.setName(expressname);
            express.setId(expresskey);
            list.add(express);
        }

        System.out.println(list.size());



               //for (int i=0;i<jsonObject.size();i++){
            //String str=jsonObject.getJSONObject(i).getString("name");
            //jsonObject
            //String str=jsonObject.getString("name");
            //System.out.println(str);
        //}

        //getClass().getResource("abc");

       /* String html = HttpUtils.get("https://www.kuaidi100.com/network/plist.shtml");
        Pattern pattern = Pattern.compile("<a href=\"(.*?)\" target=\"_blank\"><h4>.*?</h4><b>(.*?)</b>");
        Matcher matcher = pattern.matcher(html);
        JSONObject json = new JSONObject();
        while (matcher.find()) {
            String url = matcher.group(1);
            JSONObject item= parse(url);
            if(item != null){
                json.put(item.getString("key"), item);
            }
            else{
                System.out.println(url);
            }
        }
        System.out.println(json);*/
    }

    public static JSONObject parse(String url) throws ClientProtocolException, IOException {
        String html = HttpUtils.get(url);
        Pattern pattern = Pattern.compile("<div class=\"ex-title\">[\\s\\S]*?<h1>(.*?)</h1>[\\s\\S]*?<input type=\"hidden\" id=\"companyCode\" value=\"(.*?)\" />[\\s\\S]*?<font id=\"allcompanytel\" class=\"tel-icon\" title=\"拨打客服电话\">(.*?)</font>&emsp;<a target=\"_blank\" rel=\"nofollow\" id=\"allcompanyurl\" class=\"url-icon\" title=\"访问官网\" href=\"(.*?)\">.*?</a>&emsp;<a target=\"_blank\" class=\"net-icon\" rel=\"nofollow\" id=\"serversite\" title=\"查看快递网点\" href=\".*?\">服务网点</a>[\\s\\S]*?</div>");
        Matcher matcher = pattern.matcher(html);

        if (matcher.find()) {
            String name = matcher.group(1);
            String key = matcher.group(2);
            String tel = matcher.group(3);
            String site = matcher.group(4);
            JSONObject item =  new JSONObject();
            item.put("key", key);
            item.put("name", name);
            item.put("tel", tel);
            item.put("site", site);
            return item;
        }
        return null;

    }


    public static   List<T_city> f(List<T_city> list, JSONObject map, String pid) {
        String nnn=pid;
        if (map!=null&&map.size()>0){
            for (Object mapData : map.entrySet()) {

                Map.Entry entry = (Map.Entry) mapData;
                System.out.println(entry.getKey() + "--->" + entry.getValue());
                T_city p = new T_city();
                p.setId((String) entry.getKey());
                p.setParid(pid);
                if(entry.getValue() instanceof JSONObject){
                    JSONObject jsonObject = (JSONObject) entry.getValue();
                    p.setName((String) jsonObject.get("name"));
                    if(jsonObject.get("child") instanceof JSONObject){
                        JSONObject child = (JSONObject) jsonObject.get("child");
                        if(p.getId().contains("0000")){
                            p.setParid("none");
                        }
                        pid=p.getId();
                        f(list,child,pid);
                        pid=nnn;
                    }
                }else {
                    p.setName((String)entry.getValue());
                }
                list.add(p);

            }
        }

        return list;

    }

    class Pick extends Thread{
        String name;
        public Pick(){
        }
        public Pick(String name){
            this.name=name;
        }
        @Override
        public void run(){
            while (count>0){
                synchronized (lock){
                    if(count>0){
                        System.out.println(name+"卖了第"+(count)+"张票");
                        count--;
                    }else {
                        System.out.println("票卖光了");
                    }

                }
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
