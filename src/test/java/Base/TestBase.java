package Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {

    public Properties prop;

    public TestBase(){
        prop = new Properties();
        FileInputStream fip = null;
        try {
            fip = new FileInputStream(System.getProperty("user.dir")+"/src/test/java/Configuration/config.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            prop.load(fip);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
