package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/*
@author : Raaghav
 */
public class PropertyReader {

	private static String configFilePath = "Config/configuration.properties";
	
	public static String readConfig(String key) {
		String value = "";
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream(configFilePath);
			prop.load(input);
			value = prop.getProperty(key);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return value;
	}
}
