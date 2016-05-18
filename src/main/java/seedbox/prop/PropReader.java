package seedbox.prop;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropReader {

	private Properties prop=null;
	private static PropReader instance=null;
	
	
	private PropReader(){
		
		prop=new Properties();
		
		try {
			prop.load(new FileInputStream(Location.PATH+"config.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	public static String getProp(String name){
		if(instance==null) instance=new PropReader();
		
		return instance.prop.getProperty(name);
	}

}
