package com.xy.servlet;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xy.utils.DiskUtils;

public class ReceiveFileInfoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			System.out.println("有人访问servlet/ReceiveFileInfoServlet>>>>>>");
			// 接收客户端发送来的本次上传文件的信息
			ObjectInputStream objIn = new ObjectInputStream(request.getInputStream());
			@SuppressWarnings("unchecked")
			ArrayList<HashMap<String[], String>> singleFileInfo = (ArrayList<HashMap<String[], String>>) objIn.readObject();
			
			// 从硬盘得到服务器端存储文件信息的对象
			@SuppressWarnings("unchecked")
			ArrayList<HashMap<String[], String>> listData = (ArrayList<HashMap<String[], String>>) DiskUtils.getListData();
			System.out.println("在从硬盘读取listData之后>>>>>>>");
			// 如果长度为0,则创建一个
			if (listData.size() == 0) {
				System.out.println("判断来自硬盘的listData长度是否为0>>>>>>是为0");
				listData = new ArrayList<HashMap<String[],String>>();
			}
			System.out.println("listData不为0,则打印长度:"+listData.size());
			// 将新上传文件的信息添加到listData
			listData.addAll(singleFileInfo);
			// 将listData重新写到硬盘
			DiskUtils.writeToDisk(listData);
			// 回执
			ObjectOutputStream objOut = new ObjectOutputStream(response.getOutputStream());
			objOut.writeObject(new String("finish"));
			objOut.flush();
			// 关闭流
			objOut.close();
			objIn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
