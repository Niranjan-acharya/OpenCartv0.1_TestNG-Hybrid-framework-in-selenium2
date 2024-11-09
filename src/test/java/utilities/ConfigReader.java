package utilities;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
  static Properties prop;

  public static Properties getProperties() throws IOException {
    FileReader file = new FileReader(System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties");
    try {
      prop = new Properties();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    prop.load(file);

    return prop;
  }
}