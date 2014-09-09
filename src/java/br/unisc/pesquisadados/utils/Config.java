package br.unisc.pesquisadados.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

/**
 *
 * @author tauame
 */

public class Config{
 
    private static Properties props;
 
    static {
      props = new Properties();
      try {
          Config util = new Config();
          props = util.getPropertiesFromClasspath("config.properties");
      } catch (FileNotFoundException e) {
          e.printStackTrace();
      } catch (IOException e) {
          e.printStackTrace();
      }
    }

    // private constructor
    private Config() { }
 
    public static String get(String key) {
        return props.getProperty(key);
    }
 
    public static Set<Object> getkeys() {
        return props.keySet();
    }
 
    /**
     * loads properties file from classpath
     *
     * @param propFileName
     * @return
     * @throws IOException
     */
    private Properties getPropertiesFromClasspath(String propFileName)
      throws IOException {
          Properties props = new Properties();
          InputStream inputStream =
              this.getClass().getClassLoader().getResourceAsStream(propFileName);

          if (inputStream == null) {
              throw new FileNotFoundException("property file '" + propFileName
                + "' not found in the classpath");
          }

          props.load(inputStream);
          return props;
    }
}