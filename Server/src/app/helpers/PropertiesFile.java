package app.helpers;

import app.thread.ProcessClientsRequests;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertiesFile {
    private static PropertiesFile instance;
    private static Properties properties;
    
    private PropertiesFile() {
        PropertiesFile.init();
    }
    
    public static PropertiesFile getInstance()
    {
        if(instance == null) {
            instance = new PropertiesFile();
        }
        
        return instance;
    }
    
    private static Properties init()
    {
        if(properties == null) {
            properties = new Properties();
            try {
                properties.load(new FileInputStream("config/env.properties"));
            } catch(IOException ex) {
                Logger.getLogger(ProcessClientsRequests.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return properties;
    }
    
    public String get(String key) {
        return properties.getProperty(key);
    }
    
    public void set(String key, String value) throws FileNotFoundException, IOException {
        properties.setProperty(key, value);
        properties.store(new FileOutputStream("config/env.properties"), "ENV configuration");
    }
}
