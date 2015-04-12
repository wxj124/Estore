package com.wxj.factory;

import java.io.FileReader;
import java.util.Properties;

public class BasicFactory {
	/**
	 * ʵ�ֲ����֮��Ľ���
	 */
	private static BasicFactory factory = new BasicFactory();
	private static Properties pro = null;

	private BasicFactory() { // ���췽��˽�л�
	}

	// �������ļ�
	static {
		try {
			pro = new Properties();
			pro.load(new FileReader(BasicFactory.class.getClassLoader()
					.getResource("config.properties").getPath()));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static BasicFactory getFactory() {
		return factory;
	}

	public <T> T getInstence(Class<T> clazz) {
		// �������ļ�
		try {
			String infName = clazz.getSimpleName(); // ��ȡ�ӿ�
			String implName = pro.getProperty(infName); // ʵ�ֵ�����
			return (T) Class.forName(implName).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
