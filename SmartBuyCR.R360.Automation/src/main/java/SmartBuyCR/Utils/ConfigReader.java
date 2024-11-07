package SmartBuyCR.Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    // Static variables for the properties
    public static String LoginUsername;
    public static String LoginPassword;

    static {
        try {
            String filePath = "src/main/resources/config.properties";
            FileInputStream fileInput = new FileInputStream(filePath);
            properties = new Properties();
            properties.load(fileInput);
            fileInput.close();

            // Initialize static fields with values from properties file
            LoginUsername = properties.getProperty("LoginUsername");
            LoginPassword = properties.getProperty("LoginPassword");
            
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration properties file cannot be found");
        }
    }
}
