package liuxiaoran.uploadUtils;

import java.util.HashMap;
import java.util.Map;

public class FileUploadAppProperties {
private static Map<String,String> properties= new HashMap<>();

private static FileUploadAppProperties instance= new FileUploadAppProperties();

	private FileUploadAppProperties() {
		
	}

	public static FileUploadAppProperties getInstance() {
		return instance;
	}
	
	 public void addProperties(String propertyName,String propertyValue) {
		 properties.put(propertyName, propertyValue);
	 }
	
	 public String getPropertyValue(String PropertyName) {
		 return properties.get(PropertyName);
	 }
	 
	 
	}
