 /*
  * Package com.rust.jmx.mbean
  * FileName: AppCtrlImpl
  * Author:   Rust
  * Date:     2018/8/3 23:31
  */
 package com.rust.jmx.mbean;

 import com.rust.ws.service.HelloWorld;

import java.lang.reflect.Method;

 /**
  * FileName:    AppCtrlImpl
  * Author:      Rust
  * Date:        2018/8/3
  * Description:
  */
 public class AppCtrlImpl implements AppCtrl {
	 @Override
	 public synchronized void invokeHello(String param) throws Exception{
	 	// 是用反射，因为没有使用spring,真正调用时可以使用依赖注入原有应用的服务+调用方法
		 Class<HelloWorld> clzz = HelloWorld.class;
		 Method method = clzz.getDeclaredMethod("sayHelloWorldFrom", String.class);
		 Object obj = clzz.newInstance();
		 method.invoke(obj, param);
	 }
 }
