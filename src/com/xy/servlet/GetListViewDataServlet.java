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
import com.xy.utils.IndexUtils;

/**
 * 响应手机端的请求上传历史文件新的的请求,将该用户的上传历史文件信息的对象发送至服务器端
 * @author Administrator
 *
 */
public class GetListViewDataServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			System.out.println("有人访问getListViewDataServlet");
			// 从总的listData选出该用户的记录,存储
			ArrayList<HashMap<String[],String>> listDataForClient = new ArrayList<HashMap<String[],String>>();
			// 得到要查找的用户名
			ObjectInputStream objIn = new ObjectInputStream(request.getInputStream());
			String userName = (String) objIn.readObject();
			System.out.println("userName::::" + userName);
			objIn.close();
			// 从本地文件读取到存储数据的listData.boj
			ArrayList<HashMap<String[],String>> listData2 = (ArrayList<HashMap<String[],String>>) DiskUtils.getListData();
			// 若重复上传则将旧的记录删除
			ArrayList<HashMap<String[],String>> listData = IndexUtils.trimListData(listData2);
			// 遍历listData,找到该用户的上传记录
			for (int i = 0; i < listData.size(); i++) {
				HashMap<String[], String> hm = listData.get(i);
				String hm_userName = hm.keySet().iterator().next()[3];
				if (hm_userName.equals(userName)) {
					listDataForClient.add(hm);
				}
			}
			// 将数据文件写到输出流，传给client
			ObjectOutputStream objOut = new ObjectOutputStream(response.getOutputStream());
			objOut.writeObject(listDataForClient);
			objOut.flush();
			objOut.close();
			objIn.close();
			// 将新的lsitData写入到硬盘中
			DiskUtils.writeToDisk(listData);
			
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
