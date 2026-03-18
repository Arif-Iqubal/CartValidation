package com.myntra.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties p;

    static {
        try {
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties");
             //"D:\VS Code Workspace\coding\java\AutomationExercise_Ecommerce\src\test\resources\config.properties"
            p = new Properties();
            p.load(fis);
            fis.close();//this is important to prevent from lock
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("config.properties file not loaded");
        }
    }

    public static String getProperty(String key) {
        return p.getProperty(key);
    }
}
