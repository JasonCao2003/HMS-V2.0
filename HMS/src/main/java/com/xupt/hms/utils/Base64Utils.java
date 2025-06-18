package com.xupt.hms.utils;

import java.io.*;
import java.util.Base64;

public class Base64Utils {

	/**
	 * 根据文件获取Base64字符串
	 * @param file 文件对象
	 * @return
	 */
	public static String getBase64Str(File file) {
		String r = null;
		FileInputStream fis = null;
		byte[] data = null;
		try {
			fis = new FileInputStream(file);
			data = new byte[fis.available()];
			fis.read(data);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		// 防止data数组为空。
		if (null == data || data.length == 0) {
			return null;
		}
		r = Base64.getEncoder().encodeToString(data);
		return r;
	}

	/**
	 * 根据Base64字符串创建文件。
	 * @param base64Str Base64字符串。
	 * @param file      要创建的文件
	 * @return          true表示创建成功。false表示创建失败。
	 */
	public static boolean createFile(String base64Str, File file){
		boolean flag = false;
		if (null == base64Str) {
			return flag;
		}
		byte[] b = Base64.getDecoder().decode(base64Str);
		File dir = file.getParentFile();
		FileOutputStream out = null;
		try {
			if (!dir.exists()) {
				dir.mkdirs();
			}
			if (!file.exists()) {
				file.createNewFile();
			}
			// 生成文件
			out = new FileOutputStream(file);
			out.write(b);
			flag = true;
		} catch (IOException e) {
			e.printStackTrace();
			flag = false;
		} finally {
			try {
				if (null != out) {
					out.flush();
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
}
