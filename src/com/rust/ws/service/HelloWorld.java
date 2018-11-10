package com.rust.ws.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * FileName: HelloWorld
 * Author:   Rust
 * Date:     2018/8/3
 * Description:
 * History:
 */
@WebService
public class HelloWorld {
	@WebMethod
	public String sayHelloWorldFrom(String from) {
		String result = "Hello, world, from " + from + "reverse call success.";
		System.out.println(result);
		return result;
	}

}
