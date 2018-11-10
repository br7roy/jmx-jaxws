 /*
  * Package com.rust
  * FileName: AppStart
  * Author:   Rust
  * Date:     2018/8/4 1:11
  */
 package com.rust;

 import com.rust.jmx.server.JmxServerService;
 import com.rust.ws.client.ClientInvoker;
 import com.rust.ws.service.HelloWorld;

 import javax.xml.ws.Endpoint;
 import java.lang.reflect.InvocationTargetException;
 import java.lang.reflect.Method;
 import java.util.concurrent.TimeUnit;

 /**
  * FileName:    AppStart
  * Author:      Rust
  * Date:        2018/8/4
  * Description:
  */
 public class AppStart {
	 public static void main(String[] args) throws InterruptedException, IllegalAccessException,
			 InstantiationException, NoSuchMethodException, InvocationTargetException {
		 Object implementor = new HelloWorld();
		 String address = "http://localhost:9000/HelloWorld";
		 Object implementor2 = new ClientInvoker();
		 String address2 = "http://localhost:9000/ClientInvoker";
		 Endpoint.publish(address, implementor);
		 Endpoint.publish(address2, implementor2);
		 boolean ret = JmxServerService.ONE.start();
		 if (!ret) {
			 System.exit(-1);
			 System.out.println("fail to start JmxServer");
			 JmxServerService.ONE.stop();
		 }
		 TimeUnit.SECONDS.sleep(1);
		 Class<ClientInvoker> clazz = ClientInvoker.class;
		 Object obj = clazz.newInstance();
		 Method method = clazz.getDeclaredMethod("invoke", String.class);
		 Object retobj = method.invoke(obj, "123");
		 System.out.println(retobj);


	 }
 }
