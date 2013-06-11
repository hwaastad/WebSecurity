/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.internal;

import java.util.Date;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import org.waastad.business.BusinessBean;
import org.waastad.business.HelloBean;

/**
 *
 * @author Helge Waastad <helge.waastad@datametrix.no>
 */
@Singleton
@Startup
@DeclareRoles("InternalGroup")
@RunAs("InternalGroup")
public class TimerSessionBean {

    @EJB
    private BusinessBean businessBean;
    @EJB private HelloBean helloBean;
    @Inject @Any private Event<String> log;

    @Schedule(hour = "*", minute = "*", second = "*/30")
    public void myTimer() {
//        try {
//            Properties props = new Properties();
//            props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.openejb.client.RemoteInitialContextFactory");
//            //props.setProperty(Context.PROVIDER_URL, "ejbd://localhost:4201");
//            props.setProperty(Context.SECURITY_PRINCIPAL, "someuser");
//            props.setProperty(Context.SECURITY_CREDENTIALS, "thepass");
//            //props.setProperty("openejb.authentication.realmName", "file");
//            Context initialContext = new InitialContext(props);
//            businessBean = (BusinessBeanRemote) initialContext.lookup("java:global/WebSecurity-1.0-SNAPSHOT/BusinessBean!org.waastad.websecurity.ejb.BusinessBeanRemote");
            System.out.println("Timer event: " + new Date());
            businessBean.sayHello();
            log.fire("Whatever");
            helloBean.sayHello();
//        } catch (NamingException ex) {
//            Logger.getLogger(TimerSessionBean.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
