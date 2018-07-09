package liuxiaoran.listenerPackage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import liuxiaoran.uploadUtils.FileUploadAppProperties;


/**
 * Application Lifecycle Listener implementation class extensionListener
 *
 */
@WebListener
public class extensionListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public extensionListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    	InputStream is=getClass().getClassLoader().getResourceAsStream("/extension.properties");
    	Properties properties=new Properties();
    	try {
			properties.load(is);
			for(Map.Entry<Object,Object> propertiesEntry:properties.entrySet()) {
				String propertyName=(String) propertiesEntry.getKey();
				String propertyValue=(String) propertiesEntry.getValue();
				FileUploadAppProperties.getInstance().addProperties(propertyName, propertyValue);				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	
    }
	
}
