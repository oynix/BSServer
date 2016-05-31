package com.xy.servlet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xy.utils.XMLUtils;

public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			System.out.println("connection.....");
			ObjectInputStream objIn = new ObjectInputStream(request.getInputStream());
			String[] user = (String[]) objIn.readObject();
			// 解析出用户名和密码
			String name = user[0];
			String password = user[1];
			// 验证该用户名是否被使用
			boolean isUsed = XMLUtils.isExist(name);
			Boolean result = false;
			// 如果已经被使用
			if(isUsed){
				result  = false;
			} else {
				// 如果没有被使用
				// 将新的用户名和密码写入文件
				// 将result置为true
				XMLUtils.addUser(name, password);
				result = true;
			}
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

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
