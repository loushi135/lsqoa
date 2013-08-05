package test;


import org.apache.axis.client.Call;
import org.apache.axis.client.Service;


public class TestClient3 {
	public static void main(String[] args) {
//		 testSync();
//		 testDept();
		 testGetDeptById();
	}
	
	public static void testSync(){
		try {
			String wsdlUrl = "http://localhost:8080/oa_ljt/services/SyncUserInfoService?wsdl";
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.getMessageContext().setUsername("admin");
			call.getMessageContext().setPassword("123456");
            call.setTargetEndpointAddress(wsdlUrl);
//			call.addHeader(new SOAPHeaderElement("Authorsization","username","admin"));  
//			call.addHeader(new SOAPHeaderElement("Authorization","password","123456")); 
            call.setOperationName("getUser");//WSDL里面描述的接口名称
            call.addParameter("updateDate", org.apache.axis.encoding.XMLType.XSD_STRING,
                          javax.xml.rpc.ParameterMode.IN);//接口的参数
            call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);//设置返回类型  
            String result = (String)call.invoke(new Object[]{"2013-05-05"});
            //给方法传递参数，并且调用方法
            System.out.println(result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void testDept(){
		try {
			String wsdlUrl = "http://localhost:8080/oa_ljt/services/SyncUserInfoService?wsdl";
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.getMessageContext().setUsername("admin");
			call.getMessageContext().setPassword("123456");
            call.setTargetEndpointAddress(wsdlUrl);
            call.setOperationName("getDepartmentAndJob");//WSDL里面描述的接口名称
            String result = (String)call.invoke(new Object[]{});
            //给方法传递参数，并且调用方法
            System.out.println(result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public static void testGetDeptById(){
		try {
			String wsdlUrl = "http://58.210.126.158:9898/OA2/services/SyncUserInfoService?wsdl";
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.getMessageContext().setUsername("admin");
			call.getMessageContext().setPassword("123456");
            call.setTargetEndpointAddress(wsdlUrl);
            call.setOperationName("getDeptInfoById");//WSDL里面描述的接口名称
            call.addParameter("id", org.apache.axis.encoding.XMLType.XSD_STRING,
                    javax.xml.rpc.ParameterMode.IN);//接口的参数
            call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);//设置返回类型  
            String result = (String)call.invoke(new Object[]{"0"});
            //给方法传递参数，并且调用方法
            System.out.println(result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
