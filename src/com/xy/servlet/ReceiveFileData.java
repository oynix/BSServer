package com.xy.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xy.utils.WriteToDisk;

public class ReceiveFileData extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		boolean isSuccess = false;
		InputStream inputStream = null;
		ObjectInputStream objectInputStream = null;
		OutputStream out = null;

		try {
			// 得到用户名和文件名的组合 String
			inputStream = request.getInputStream();
			objectInputStream = new ObjectInputStream(inputStream);
			String userName_fileName = (String) objectInputStream.readObject();
			// 拆分出用户名
			String userName = userName_fileName.split("\\\\")[0];
			File userDir = new File("D:\\BSData\\" + userName);
			// 如果不存在,先创建用户目录
			if(!userDir.exists()){
				userDir.mkdir();
			}
			WriteToDisk w2d = new WriteToDisk(inputStream, userName_fileName);
			System.out.println("file name" + userName_fileName.split("\\\\")[1]);
			isSuccess = w2d.doWrite();

			out = response.getOutputStream();
			if (isSuccess) {
				out.write("OK".getBytes());
			} else {
				out.write("NO".getBytes());
			}
			out.flush();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}
			if (objectInputStream != null) {
				objectInputStream.close();
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
