/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.business;

import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

/**
 *
 * @author Helge Waastad <helge.waastad@datametrix.no>
 */
@Stateless
@RunAs("InternalGroup")
@DeclareRoles({"InternalGroup","SuperAdmin"})
public class BorderControl {

    @EJB
    private BusinessBean businessBean;
    @Resource
    private SessionContext sessionContext;

    @RolesAllowed("SuperAdmin")
    public void sayHelloBorder() {
        System.out.println("BorderControl:Start");
        if (sessionContext.isCallerInRole("InternalGroup")) {
            System.out.println("BorderControl: User is in InternalGroup");
        } else
            if (sessionContext.isCallerInRole("SuperAdmin")) {
            System.out.println("BorderControl: User is in SuperAdmin");
        } else {
            System.out.println("BorderControl: Do not know which group the user belongs...");
        }
        System.out.println("BorderControl:Calling Businessbean. Principal Name: " + sessionContext.getCallerPrincipal().getName());
        businessBean.sayHello();
    }
}
