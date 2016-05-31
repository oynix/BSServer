package com.xy.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

public class IndexUtils {
	/**
	 * 创建一个ArrayList<String []>，并存入文件中 String[],element:FileName, FileSize,
	 * Map<String[],Bitmap>
	 * UserName, UploadTime
	 * 
	 * @throws Exception
	 */
	@Test
	public void createArr() throws Exception {
		ArrayList<String[]> listData = new ArrayList<String[]>();
		String[] cell1 = new String[] { "1213.mp4", "32M", "2016-5-12" };
		String[] cell2 = new String[] { "1215.mp4", "32M", "2016-5-12" };
		listData.add(cell1);
		listData.add(cell2);
		ObjectOutputStream objOut = new ObjectOutputStream(
				new FileOutputStream(new File("D:\\BSData\\listData.obj")));
		objOut.writeObject(listData);
		objOut.flush();
		objOut.close();
	}

	/**
	 * 删除重复记录
	 * 
	 */
	public static ArrayList<HashMap<String[], String>> trimListData(ArrayList<HashMap<String[], String>> listData){
		System.out.println("IndexUtils>trimArray>listData.size():"+listData.size());
		for (int i = 0; i < listData.size(); i++) {
			// 得到当前的HashMap
			HashMap<String[], String> temp = listData.get(i);
			String[] info = temp.keySet().iterator().next();
			// 得到文件名和用户名
			String fileName = info[0];
			String user = info[3];
			// 从当前位置的下一个位置开始查找
			for (int j = i+1; j < listData.size(); j++) {
				// 得到当前的HashMap
				HashMap<String[], String> temp2 = listData.get(j);
				String[] info2 = temp2.keySet().iterator().next();
				// 得到文件名和用户名
				String fileName2 = info2[0];
				String user2 = info2[3];
				// 作出判断,如果相同,则删掉前面的,保留最新的
				if(fileName.equals(fileName2) && user.equals(user2)){
					listData.remove(i);
					i--;
					break;
				}
			}
		}
		return listData;
	}
	/**
	 * 清空listData
	 * @param listData
	 * @return
	 */
	public static ArrayList<HashMap<String[], String>> clearListData(ArrayList<HashMap<String[], String>> listData){
		for (int i = 1; i < listData.size(); i++) {
			listData.remove(i);
		}
		return listData;
	}
}
