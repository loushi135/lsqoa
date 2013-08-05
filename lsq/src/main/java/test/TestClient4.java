package test;


import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;
import org.model.HandleJob;
import org.model.YhoaHandleJob;


public class TestClient4 {
	public static void main(String[] args) {
		try {
			String wsdlUrl = "http://localhost:8080/oa_ljt/services/YhteService?wsdl";
			Service service = new Service();
			Call call = (Call) service.createCall();
			QName qn = new QName("urn:BeanService","addYhteToGoldMantis");   
			call.registerTypeMapping(HandleJob.class, qn,      
                    new BeanSerializerFactory(YhoaHandleJob.class, qn),      
                    new BeanDeserializerFactory(YhoaHandleJob.class, qn));    
			YhoaHandleJob handleJob  = new YhoaHandleJob();
			handleJob.setBillCode("121321");
			handleJob.setMessage("21321");
//			call.addHeader(new SOAPHeaderElement("Authorization","username","admin"));  
//			call.addHeader(new SOAPHeaderElement("Authorization","password","123456")); 
			call.getMessageContext().setUsername("admin");
			call.getMessageContext().setPassword("123456");
            call.setTargetEndpointAddress(wsdlUrl);
            call.setOperationName(qn);//WSDL里面描述的接口名称
//            call.setOperationName("getUser");//WSDL里面描述的接口名称
            call.addParameter("yhoaHandleJob", qn,
                          javax.xml.rpc.ParameterMode.IN);//接口的参数
//            call.setReturnType(qn,HandleJob.class);//设置返回类型  
//            String result = (String)call.invoke(new Object[]{"2013-05-05"});
            String result = (String)call.invoke(new Object[]{handleJob});
            //给方法传递参数，并且调用方法
            System.out.println(result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
}
