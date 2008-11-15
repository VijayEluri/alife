package jml.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public final class PropertiesJml {
	 
	
	  public static Properties readProperties(String resourceName)
	    throws Exception {

	    Properties defaultProps = new Properties();
	    /*try {
	        defaultProps.load(ClassLoader.getSystemResourceAsStream(resourceName));
	    } catch (Exception ex) {

	      System.err.println("Warning, unable to load properties file from "
				 +"system resource (Utils.java)");
	    }

	    int slInd = resourceName.lastIndexOf('/');
	    if (slInd != -1) {
	      resourceName = resourceName.substring(slInd + 1);
	    }*/

	    Properties userProps = new Properties(defaultProps);    
	    File propFile = new File(System.getProperties().getProperty("user.home")
	                             + File.separatorChar
	                             + resourceName);
	    if (propFile.exists()) {
	      try {
	        userProps.load(new FileInputStream(propFile));
	      } catch (Exception ex) {
	        throw new Exception("Problem reading user properties: " + propFile);
	      }
	    }

	   Properties localProps = new Properties(userProps);
	    propFile = new File(resourceName);
	    if (propFile.exists()) {
	      try {
	        localProps.load(new FileInputStream(propFile));
	      } catch (Exception ex) {
	        throw new Exception("Problem reading local properties: " + propFile);
	      }
	    }
	    
	    return localProps;
	  }
}
