package com.wm.edu.controller.ueditor;

import com.wm.edu.utils.ueditor.ActionEnter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;


@Controller
@RequestMapping(value = "/uEditor/")
public class UEditorController {
	@Autowired
	private Environment env;
	@RequestMapping(value="config")
	public void config(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("application/json");
			request.setCharacterEncoding("utf-8");
			response.setHeader("Content-Type", "text/html");
			//response.setHeader("X-Frame-Options", "SAMEORIGIN");
			System.out.println(request.getServletContext().getContextPath());
			//org.springframework.core.io.Resource res=new ClassPathResource("config.json");
			//System.out.println(((ClassPathResource) res).getPath());

			String rootPath = request.getSession().getServletContext().getRealPath("/");
			System.out.println("rootPath: " + rootPath);
			//request.getServletContext().getRealPath("/"); //如果此方法使用报错 请使用下面的方法
			//request.getSession().getServletContext().getRealPath("/");
			
			//rootPath = rootPath + "js\\ueditor\\";
			
			String exec = new ActionEnter(request, rootPath).exec();
			PrintWriter writer = response.getWriter();
			writer.write(exec);
			writer.flush();
			writer.close();
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*public static  void main(String[] args){
		String configFileName = "config.json";
		try {
			InputStream in=new FileInputStream("configFileName");
			int end=0;
			while ((end=in.read())>-1){
				System.out.println(end);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
}