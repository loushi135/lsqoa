package com.xpsoft.webservice.handler;
import java.util.Date;

import org.apache.axis.AxisFault;
import org.apache.axis.Handler;
import org.apache.axis.MessageContext;
import org.apache.axis.handlers.BasicHandler;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LogHandler extends BasicHandler {
	private final Log logger = LogFactory.getLog(LogHandler.class);
    /**
        invoke，每一个handler都必须实现的方法。
     */
    public void invoke(MessageContext msgContext) throws AxisFault {
        //每当web服务被调用，都记录到log中。
    	logger.info("日志记录 : invoke : start");
        try {
            Handler handler = msgContext.getService();
            Integer counter = (Integer)handler.getOption("accesses");
            if (counter == null)
                counter = new Integer(0);

            counter = new Integer(counter.intValue() + 1);            
            Date date = new Date();
            msgContext.getMessage().writeTo(System.out);
           
            String result = "在" + date + ": Web 服务 " +
                msgContext.getTargetService() +
                " 被调用，现在已经共调用了 " + counter + " 次.";
            handler.setOption("accesses", counter);            
            logger.info(result);            
        } catch (Exception e) {
            throw AxisFault.makeFault(e);
        }
    }
}
