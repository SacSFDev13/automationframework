package utilites;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
  private Properties prop = new Properties();
  private InputStream inputStream = null;

  public PropertyReader() {
    try {
      String propertiesFilePath = System.getProperty("user.dir") + "/src/test/resources/env.properties";
      inputStream = new FileInputStream(propertiesFilePath);
      prop.load(inputStream);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String getEndPointUrl(String endpoint) {
    return prop.getProperty("base_url") + prop.getProperty(endpoint);
  }

  public String getProperty(String propertyName) {
    return prop.getProperty(propertyName);
  }
}
