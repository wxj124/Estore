package com.wxj.factory;

import java.io.FileReader;
import java.util.Properties;

public class BasicFactory {
	/**
	 * 实现层与层之间的解耦
	 */
	private static BasicFactory factory = new BasicFactory();
	private static Properties pro = null;

	private BasicFactory() { // 构造方法私有化
	}

	// 读配置文件
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
		// 读配置文件
		try {
			String infName = clazz.getSimpleName(); // 读取接口
			String implName = pro.getProperty(infName); // 实现的名字
			return (T) Class.forName(implName).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
