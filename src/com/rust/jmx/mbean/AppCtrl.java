 /*
  * Package com.rust.jmx.mbean
  * FileName: AppCtrl
  * Author:   Rust
  * Date:     2018/8/3 23:30
  */
 package com.rust.jmx.mbean;

 import javax.management.MXBean;

 /**
  * FileName:    AppCtrl
  * Author:      Rust
  * Date:        2018/8/3
  * Description:
  */
 @MXBean
 public interface AppCtrl {
	 public void invokeHello(String param) throws Exception;

 }
