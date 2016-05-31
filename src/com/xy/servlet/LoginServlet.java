package com.xy.servlet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xy.utils.XMLUtils;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			System.out.println("connection.....");
			ObjectInputStream objIn = new ObjectInputStream(request.getInputStream());
			String[] user = (String[]) objIn.readObject();
			// 解析出用户名和密码
			String name = user[0];
			String password = user[1];
			// 验证用户名密码是否匹配
			Boolean result = XMLUtils.loginCheck(name, password);
			ObjectOutputStream objOut = new ObjectOutputStream(response.getOutputStream());
			// 返回一个Boolean对象到客户端
			objOut.writeObject(new Boolean(result));
			objOut.flush();
			objOut.close();
			objIn.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
