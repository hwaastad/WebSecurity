/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.business;

import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.ejb.Asynchronous;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;

/**
 *
 * @author Helge Waastad <helge.waastad@datametrix.no>
 */
@Stateless
@Asynchronous
@DeclareRoles({"InternalGroup", "SuperAdmin"})
public class HelloBean {

    @Resource
    SessionContext sessionContext;

    public void sayHello() {
        if (sessionContext.isCallerInRole("InternalGroup")) {
            System.out.println("HelloBean: User is in InternalGroup");
        } else if (sessionContext.isCallerInRole("SuperAdmin")) {
            System.out.println("HelloBean: User is in SuperAdmin");
        } else {
            System.out.println("HelloBean: Do not know which group the user belongs...");
        }
        System.out.println("HelloBean: Hello: " + sessionContext.getCallerPrincipal().getName());
    }
    
    public void listenHello(@Observes String message){
        if (sessionContext.isCallerInRole("InternalGroup")) {
            System.out.println("HelloBean Event: User is in InternalGroup");
        } else if (sessionContext.isCallerInRole("SuperAdmin")) {
            System.out.println("HelloBean Event: User is in SuperAdmin");
        } else {
            System.out.println("HelloBean Event:Do not know which group the user belongs...");
        }
        System.out.println("HelloBean Event: Hello: " + sessionContext.getCallerPrincipal().getName());
    }
}
