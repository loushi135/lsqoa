package com.xpsoft.webservice.handler;
import org.apache.axis.AxisFault;
import org.apache.axis.MessageContext;
import org.apache.axis.handlers.BasicHandler;
import org.apache.axis.security.AuthenticatedUser;
import org.apache.axis.security.SecurityProvider;
import org.apache.axis.security.simple.SimpleSecurityProvider;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//此handler的目的是对用户认证，只有认证的用户才能访问目标服务。
public class AuthenticationHandler extends BasicHandler {
    Log log = LogFactory.getLog(AuthenticationHandler.class);
    String securityProvider = "securityProvider";
    String unauthenticated = "unauthenticated";
    String authenticatedUser = "authenticatedUser";
    String cantAuth = "cantAuth";

    public void invoke(MessageContext msgContext) throws AxisFault {
        log.info("身份验证 : 开始");
        SecurityProvider provider = (SecurityProvider)msgContext.getProperty(securityProvider);
        if(provider == null) {
            provider = new SimpleSecurityProvider();
            msgContext.setProperty(securityProvider, provider);
        }

        if(provider != null) {
            String userId = msgContext.getUsername();
            String password = msgContext.getPassword();
            //对用户进行认证，如果authUser==null，表示没有通过认证，
            log.info(userId + ", " + password);
            //抛出Server.Unauthenticated异常。
            AuthenticatedUser authUser = provider.authenticate(msgContext);

            if(authUser == null)
                throw new AxisFault(unauthenticated, 
                    "error", null, null);
            //用户通过认证，把用户的设置成认证了的用户。
            msgContext.setProperty(authenticatedUser, authUser);
        }
        log.info("身份验证 : 结束");
    }
}
