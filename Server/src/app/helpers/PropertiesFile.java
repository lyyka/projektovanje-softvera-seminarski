/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.helpers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author kredium
 */
public class PropertiesFile {
    private static Properties properties;
    
    public PropertiesFile() throws FileNotFoundException, IOException {
        PropertiesFile.init();
    }
    
    private static void init()
    {
        if(PropertiesFile.properties == null) {
            PropertiesFile.properties = new Properties();
            try {
                PropertiesFile.properties.load(new FileInputStream("config/env.properties"));
            } catch(IOException e) {
                System.out.println("Cannot load env file!");
            }
        }
    }
    
    public static String get(String key) {
        PropertiesFile.init();
        return PropertiesFile.properties.getProperty(key);
    }
}
