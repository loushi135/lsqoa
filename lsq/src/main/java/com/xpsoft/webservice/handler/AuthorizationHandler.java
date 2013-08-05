package com.xpsoft.webservice.handler;
import java.util.StringTokenizer;

import org.apache.axis.AxisFault;
import org.apache.axis.Handler;
import org.apache.axis.MessageContext;
import org.apache.axis.handlers.BasicHandler;
import org.apache.axis.i18n.Messages;
import org.apache.axis.security.AuthenticatedUser;
import org.apache.axis.security.SecurityProvider;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AuthorizationHandler extends BasicHandler {
    Log log = LogFactory.getLog(AuthorizationHandler.class);
    /**
        invoke，每一个handler都必须实现的方法。
     */
    public void invoke(MessageContext msgContext) throws AxisFault {
        log.info("start");

        AuthenticatedUser user = (AuthenticatedUser)msgContext.getProperty("authenticatedUser");
        if(user == null)
            throw new AxisFault("Server.NoUser", Messages.getMessage("needUser00"), null, null);
        String userId = user.getName();
        Handler serviceHandler = msgContext.getService();
        if(serviceHandler == null)
            throw new AxisFault(Messages.getMessage("needService00"));
        String serviceName = serviceHandler.getName();
        String allowedRoles = (String)serviceHandler.getOption("allowedRoles");
        if(allowedRoles == null) {
            log.info("不需要验证");
            return;
        }
        SecurityProvider provider = (SecurityProvider)msgContext.getProperty("securityProvider");
        if(provider == null)
            throw new AxisFault(Messages.getMessage("noSecurity00"));
        for(StringTokenizer st = new StringTokenizer(allowedRoles, ","); st.hasMoreTokens();) {
            String thisRole = st.nextToken();
            if(provider.userMatches(user, thisRole)) {
                log.info("通过验证");
                return;//访问授权通过。
            }
        }
        //没有通过授权，不能访问目标服务，抛出Server.Unauthorized异常。
        throw new AxisFault("Server.Unauthorized", 
            Messages.getMessage("cantAuth02", userId, serviceName), null, null);
    }     
}
