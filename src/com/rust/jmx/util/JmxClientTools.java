 /*
  * Package com.rust.jmx.util
  * FileName: JmxClientTools
  * Author:   Rust
  * Date:     2018/8/4 0:17
  */
 package com.rust.jmx.util;

 import javax.management.JMX;
 import javax.management.MBeanServerConnection;
 import javax.management.ObjectName;
 import javax.management.remote.JMXConnector;
 import javax.management.remote.JMXConnectorFactory;
 import javax.management.remote.JMXServiceURL;
 import java.util.Map;

 /**
  * FileName:    JmxClientTools
  * Author:      Rust
  * Date:        2018/8/4
  * Description:
  */
 public class JmxClientTools {
	 public static <T> T makeRMIProxy(String rmiRegistryIp, int rmiRegistryPort, String rmiRegistryName, String
			 objctName, Class<T> intfClazz, Map<String, ?> env) {

		 String url = "service:jmx:rmi:///jndi/rmi://" + rmiRegistryIp + ":" + rmiRegistryPort + "/" + rmiRegistryName;

		 try {
			 JMXServiceURL jmxServiceURL = new JMXServiceURL(url);
			 JMXConnector jmxc = JMXConnectorFactory.connect(jmxServiceURL, env);
			 MBeanServerConnection mbsc = jmxc.getMBeanServerConnection();

			 return JMX.newMBeanProxy(mbsc, new ObjectName(objctName), intfClazz, true);
		 } catch (Exception e) {
			 System.err.print("make RMIProxy error. url=" + url + ",objectName=" + objctName + ",intfClazz=" +
					 intfClazz + e);
		 }
		 return null;
	 }
 }
