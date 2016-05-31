package com.xy.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MediaFileDownloadServlet extends HttpServlet {

	private static final long serialVersionUID = 5L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// 得到请求文件的文件名和用户名
			ObjectInputStream objIn = new ObjectInputStream(request.getInputStream());
			String userName_fileName = (String) objIn.readObject();
			// 不用判断,既然已经来下载了,证明之前一定上传过,所以一定存在用户目录
			File file = new File("D:\\BSData\\" + userName_fileName);
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			// 将文件写出
			BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buf = new byte[1024];
			int len = 0;
			while ((len = bis.read(buf)) != -1) {
				bos.write(buf, 0, len);
			}
			// 关闭流
			bos.close();
			objIn.close();
			bis.close();

		} catch (Exception e) {

		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
