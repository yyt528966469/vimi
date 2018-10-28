package com.wm.edu.utils.util;


import com.wm.edu.utils.common.AttaUtils;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.Date;

public class Prient implements Printable {

    @Override
    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {

        if (page > 0) {
            return NO_SUCH_PAGE;
        }
        int x=7;
        int y=10;

        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(new Font("Default", Font.PLAIN, 10));
        g2d.drawString("武汉薇明科技", x+27, y);
        g2d.setFont(new Font("Default", Font.PLAIN, 7));
        g2d.drawString("交易号" + AttaUtils.getNo(), x, y=y+10);
        g2d.drawString("交易时间：" + AttaUtils.dateToStr(new Date()), x, y=y+10);
        g2d.setFont(new Font("Default", Font.PLAIN, 9));
        g2d.drawString("-------------------------------------", x, y=y+10);
        g2d.drawString("菜名",x,y=y+10);
        g2d.drawString("单价",60,y);
        g2d.drawString("数量",80,y);
        g2d.drawString("金额",100,y);
        for (int i=0;i<7;i++){
            y=y+10;
            g2d.drawString("人生果",7,y);
            g2d.drawString("12",60,y);
            g2d.drawString("2",80,y);
            g2d.drawString("24",100,y);
        }
        g2d.drawString("-----------------------------------------", 7, y=y+10);

        //g2d.setFont(new Font("Default", Font.PLAIN, 25));
        g2d.drawString("件数", x, y=y+10);
        g2d.drawString("6", 50, y);
        g2d.drawString("应收", 60, y);
        g2d.drawString("72", 100, y);
        g2d.setFont(new Font("Default", Font.PLAIN, 7));
        //g2d.setFont(new Font("Default", Font.PLAIN, 14));
        g2d.drawString("您之前还有" + 5 + "桌客人在等待", x, y=y+10);
        g2d.drawString("-----------------------------------------", 7, y=y+10);
        g2d.drawString("打印时间:" + AttaUtils.dateToStr(new Date()), 7, y=y+10);
        //g2d.drawString("店名：" + "11", 7, 175);
        String text="还是回国几哈骄傲和骄傲过哈根好尬还撒谎感受感受电话给大好时光哈十多个好好噶读后感收到货";
        String[] arr=getStrWith(text,100);
        for (int i=0;i<arr.length;i++){
            g2d.drawString(arr[i],7,y=y+10);
        }

        return PAGE_EXISTS;
    }

    public static void main(String[] args) {

        prient();


    }

    public static void prient(){
        int height = 275 + 3 * 15 + 20;

        // 通俗理解就是书、文档
        Book book = new Book();

        // 打印格式
        PageFormat pf = new PageFormat();
        pf.setOrientation(PageFormat.PORTRAIT);

        // 通过Paper设置页面的空白边距和可打印区域。必须与实际打印纸张大小相符。
        Paper p = new Paper();
        p.setSize(150, height);
        p.setImageableArea(5, -20, 150, height + 20);
        pf.setPaper(p);

        // 把 PageFormat 和 Printable 添加到书中，组成一个页面
        book.append(new Prient(), pf);

        // 获取打印服务对象
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPageable(book);
        try {
            job.print();
        } catch (PrinterException e) {
            System.out.println("================打印出现异常");
        }
    }

    private static String[] getStrWith(String str,int width){
        java.awt.FontMetrics metrics = sun.font.FontDesignMetrics.getMetrics(new java.awt.Font("宋体", java.awt.Font.PLAIN, 7));


        int StrPixelWidth = metrics.stringWidth(str); //字符串长度（像素） str要打印的字符串
        int lineSize =(int) Math.ceil(StrPixelWidth*1.0/width);//要多少行
        //FontMetrics metrics = new FontMetrics(font) {};
        // Rectangle2D bounds = metrics.getStringBounds(str, null);
        // int widthInPixels = (int) bounds.getWidth();
        //System.out.println(StrPixelWidth+"---:");
        if(width < StrPixelWidth) {//页面宽度（width）小于 字符串长度
            //lineNum = (int) Math.ceil(StrPixelWidth * 1.0 / lineSize);//算出行数
            StringBuilder sb = new StringBuilder();//存储每一行的字符串
            int j = 0;
            int tempStart = 0;
            String tempStrs[] = new String[lineSize];//存储换行之后每一行的字符串
            for (int i1 = 0; i1 < str.length(); i1++) {
                char ch = str.charAt(i1);
                sb.append(ch);
                Rectangle2D bounds2 = metrics.getStringBounds(sb.toString(), null);
                int tempStrPi1exlWi1dth = (int) bounds2.getWidth();
                if (tempStrPi1exlWi1dth > width) {
                    tempStrs[j++] = str.substring(tempStart, i1);
                    tempStart = i1;
                    sb.delete(0, sb.length());
                    sb.append(ch);
                }
                if (i1 == str.length() - 1) {//最后一行
                    tempStrs[j] = str.substring(tempStart);
                }
            }
            return tempStrs;
        }
        return null;
    }



}