 /*
  * Package com.rust.ws.client
  * FileName: ClientInvoker
  * Author:   Rust
  * Date:     2018/8/3 23:17
  */
 package com.rust.ws.client;

 import com.rust.jmx.mbean.AppCtrl;
 import com.rust.jmx.util.JmxClientTools;

 import javax.jws.WebMethod;
 import javax.jws.WebService;
 import java.util.ArrayList;
 import java.util.HashMap;
 import java.util.List;
 import java.util.Map;

 import static com.rust.jmx.server.JmxServerService.ONE;

 /**
  * FileName:    ClientInvoker
  * Author:      Rust
  * Date:        2018/8/3
  * Description:
  */
 @WebService
 public class ClientInvoker {
	 @WebMethod
	 public String invoke(String from) throws Exception {
		 System.out.println("this is invoke,param:" + from);

		 Map<String, Object> pMap = new HashMap<>();
		 // 使用appClassLoader.
		 pMap.put("jmx.remote.x.client.connection.check.period", 0);

		 // 这里模拟了一台本地机器，可以配置多个集群ip,数据库，缓存，zk
		 List<WorkerInfomation> workers = touchWorkers();
		 workers.forEach(k -> {
			 AppCtrl appCtrl = JmxClientTools.makeRMIProxy(k.getJmxIp(), k.getJmxPort(), ONE
							 .getRegistryName(), ONE
							 .getObjectName(),
					 AppCtrl.class, pMap);
			 try {
				 appCtrl.invokeHello("reverse call graceful.");
			 } catch (Exception e) {
				 System.err.print("fail to invoke by RMI");
			 }
		 });

		 return "client invoke ok " + from;
	 }

	 private static List<WorkerInfomation> touchWorkers() {
		 return workers;
	 }

	 static class WorkerInfomation {
		 private String jmxIp;
		 private int jmxPort;

		 public String getJmxIp() {
			 return jmxIp;
		 }

		 public void setJmxIp(String jmxIp) {
			 this.jmxIp = jmxIp;
		 }

		 public int getJmxPort() {
			 return jmxPort;
		 }

		 public void setJmxPort(int jmxPort) {
			 this.jmxPort = jmxPort;
		 }
	 }

	 private static List<WorkerInfomation> workers = new ArrayList<>();

	 static {
		 WorkerInfomation worker = new WorkerInfomation();
		 worker.setJmxIp(ONE.getJmxIp());
		 worker.setJmxPort(ONE.getJmxPort());
		 workers.add(worker);
	 }


 }
