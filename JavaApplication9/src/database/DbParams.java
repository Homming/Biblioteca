package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DbParams {

	public static Properties getProp() {
		try {
                    Properties props = new Properties();
                    FileInputStream file = new FileInputStream("./database.properties");

                    props.load(file);

                    return props;
		} catch (IOException e) {
                    System.out.println(e);
                    return null;
		}
	}
}
