package com.xy.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 通过给定的name，返回与之匹配的password
 * 
 * @author Administrator
 * 
 */
public class XMLUtils {
	
	@Test
	public void test2(){
	}
	/**
	 * MD5加密算法
	 * @param password
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	public static String md5Digest(String password) throws NoSuchAlgorithmException {
		StringBuilder sb = new StringBuilder();
		// 1. 获取摘要器
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		// 2.得到密码的摘要 为byte数组
		byte[] digest = messageDigest.digest(password.getBytes());
		// 3.遍历数组
		for (int i = 0; i < digest.length; i++) {
			// 4.MD5加密
			int result = digest[i] & 0xff;
			// 转化为16进制
			String hexString = Integer.toHexString(result) + 1;
			if(hexString.length() < 2){
				sb.append("0");
			}
			sb.append(hexString);
		}
		
		return sb.toString();
	}
	
	
	/**
	 * 登录验证
	 * @param name
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public static boolean loginCheck(String name, String password)
			throws Exception {
		// 工厂新对象
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// 得到builder
		DocumentBuilder builder = factory.newDocumentBuilder();
		// 得到document对象
		Document document = builder.parse("BSServer/users.xml");
		// Document document = builder.parse(new File("users.xml"));
		// 得到要查找的user节点
		NodeList userList = document.getElementsByTagName("user");
		for (int i = 0; i < userList.getLength(); i++) {
			Element user = (Element) userList.item(i);
			String s_name = user.getElementsByTagName("name").item(0)
					.getTextContent();
			System.out.println("当前name>>>" + s_name);
			// 如果当前节点是要查找的节点
			if (s_name.equals(name)) {
				String s_pass = user.getElementsByTagName("password").item(0)
						.getTextContent();
				// 如果密码正确
				if (s_pass.equals(password)) {
					return true;
				}
			}
		}
		return false;
	}
	
	
	/**
	 * 根据用户名得到密码
	 * 
	 * @param name
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static String getPassword(String name)
			throws ParserConfigurationException, SAXException, IOException {
		// 工厂新对象
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// 得到builder
		DocumentBuilder builder = factory.newDocumentBuilder();
		// 得到document对象
		Document document = builder.parse("BSServer/users.xml");
		// Document document = builder.parse(new File("users.xml"));
		// 得到要查找的user列表
		NodeList userList = document.getElementsByTagName("user");
		// 遍历列表中每一个user
		for (int i = 0; i < userList.getLength(); i++) {
			Element user = (Element) userList.item(i);
			// 得到user下的name标签
			// getElementsByTagName ： 返回是名字为name的list
			// getTextContent ： 得到标签的内容
			String s_name = user.getElementsByTagName("name").item(0)
					.getTextContent();
			System.out.println("当前name>>>" + s_name);
			// 如果当前user是要查找的user
			if (s_name.equals(name)) {
				// 得到这个user的password的内容
				String s_pass = user.getElementsByTagName("password").item(0)
						.getTextContent();
				return s_pass;
			}
		}
		return null;
	}

	/**
	 * 判断这个用户名是否存在
	 * 
	 * @param name
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static boolean isExist(String name)
			throws ParserConfigurationException, SAXException, IOException {
		// 工厂新对象
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// 得到builder
		DocumentBuilder builder = factory.newDocumentBuilder();
		// 得到document对象
		Document document = builder.parse("BSServer/users.xml");
		// Document document = builder.parse(new File("users.xml"));
		// 得到要查找的user列表
		NodeList userList = document.getElementsByTagName("user");
		// 遍历列表中每一个user
		for (int i = 0; i < userList.getLength(); i++) {
			Element user = (Element) userList.item(i);
			// 得到user下的name标签
			// getElementsByTagName ： 返回是名字为name的list
			// getTextContent ： 得到标签的内容
			String s_name = user.getElementsByTagName("name").item(0).getTextContent();
			System.out.println("当前name>>>" + s_name);
			// 如果当前user是要查找的user
			if (s_name.equals(name)) {
				// 如果存在
				return true;
			}
		}
		return false;
	}
	/**
	 * 删除用户
	 * 
	 * @param name
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 * @throws TransformerException 
	 */
	public static boolean deleteUser(String name)
			throws ParserConfigurationException, SAXException, IOException, TransformerException {
		// 工厂新对象
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// 得到builder
		DocumentBuilder builder = factory.newDocumentBuilder();
		// 得到document对象
		Document document = builder.parse("BSServer/users.xml");
		// Document document = builder.parse(new File("users.xml"));
		// 得到要查找的user列表
		NodeList userList = document.getElementsByTagName("user");
		// 遍历列表中每一个user
		for (int i = 0; i < userList.getLength(); i++) {
			Element user = (Element) userList.item(i);
			// 得到user下的name标签
			// getElementsByTagName ： 返回是名字为name的list
			// getTextContent ： 得到标签的内容
			String s_name = user.getElementsByTagName("name").item(0).getTextContent();
			System.out.println("当前name>>>" + s_name);
			// 如果当前user是要查找的user
			if (s_name.equals(name)) {
				// 如果存在
				user.getParentNode().removeChild(user);
				return true;
			}
		}
		TransformerFactory tfactory = TransformerFactory.newInstance();
		Transformer transformer = tfactory.newTransformer();
		transformer.transform(new DOMSource(document), new StreamResult(new FileOutputStream("BSServer/users.xml")));

		return false;
	}

	/**
	 * 添加用户
	 * @param name 用户名
	 * @param password 密码
	 * @return
	 * @throws Exception
	 */
	public static boolean addUser(String name, String password)
			throws Exception {
		// 工厂新对象
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		// 得到builder
		DocumentBuilder builder = factory.newDocumentBuilder();
		// 得到document对象
		// Document document = builder.parse(new File("users.xml"));
		Document document = builder.parse("BSServer/users.xml");

		Node user = document.createElement("user");
		Element nameEle = document.createElement("name");
		nameEle.setTextContent(name);
		Element passEle = document.createElement("password");
		passEle.setTextContent(password);
		user.appendChild(nameEle);
		user.appendChild(passEle);

		Node users = document.getElementsByTagName("users").item(0);
		users.appendChild(user);

		TransformerFactory tfactory = TransformerFactory.newInstance();
		Transformer transformer = tfactory.newTransformer();
		transformer.transform(new DOMSource(document), new StreamResult(new FileOutputStream("BSServer/users.xml")));
//		transformer.transform(new DOMSource(document), new StreamResult(new FileOutputStream("users.xml")));

		return true;
	}

	@Test
	public void test1() {
		try {
			XMLUtils.addUser("li", "jfj");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
