/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.business;

import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

/**
 *
 * @author Helge Waastad <helge.waastad@datametrix.no>
 */
@Stateless
@LocalBean
@DeclareRoles({"InternalGroup", "SuperAdmin"})
public class BusinessBean {

    @Resource
    SessionContext sessionContext;

    public void sayHello() {
        if (sessionContext.isCallerInRole("InternalGroup")) {
            System.out.println("BusinessBean: User is in InternalGroup");
        } else if (sessionContext.isCallerInRole("SuperAdmin")) {
            System.out.println("BusinessBean: User is in SuperAdmin");
        } else {
            System.out.println("BusinessBean: Do not know which group the user belongs...");
        }
        System.out.println("BusinessBean: Hello: " + sessionContext.getCallerPrincipal().getName());
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
