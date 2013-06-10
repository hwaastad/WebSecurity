/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.datametrix.internal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;
import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RolesAllowed;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.SessionContext;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import no.datametrix.qualifier.AsynchQualifier;
import no.datametrix.qualifier.StandardQualifier;

/**
 *
 * @author Helge Waastad <helge.waastad@datametrix.no>
 */
@Stateless
//@Asynchronous
@DeclareRoles({"InternalGroup", "SuperAdmin"})
public class Observer {

    @Resource
    SessionContext sessionContext;
    private List<Date> dates = new ArrayList<Date>();
    private List<Date> asynchdates = new ArrayList<Date>();

    public void listenStandard(@Observes @StandardQualifier Date date){
        if (sessionContext.isCallerInRole("InternalGroup")) {
            System.out.println("Observer Standard Event: User is in InternalGroup");
        } else if (sessionContext.isCallerInRole("SuperAdmin")) {
            System.out.println("Observer Standard Event: User is in SuperAdmin");
        } else {
            System.out.println("Observer Standard Event: Do not know which group the user belongs...");
        }
        System.out.println("Observer Standard Event: Hello: " + sessionContext.getCallerPrincipal().getName());
        dates.add(date);
    }
    
    @Asynchronous   
    public void listenAsynch(@Observes @AsynchQualifier Date date) {
        if (sessionContext.isCallerInRole("InternalGroup")) {
            System.out.println("Observer Asynch Event: User is in InternalGroup");
        } else if (sessionContext.isCallerInRole("SuperAdmin")) {
            System.out.println("Observer Asynch Event: User is in SuperAdmin");
        } else {
            System.out.println("Observer Asynch Event: Do not know which group the user belongs...");
        }
        System.out.println("Observer Asynch Event: Hello: " + sessionContext.getCallerPrincipal().getName());
        asynchdates.add(date);
    }

    public Future<List<Date>> getDates() {
        return new AsyncResult<List<Date>>(dates);
    }
    
    public Future<List<Date>> getAsynchDates() {
        return new AsyncResult<List<Date>>(asynchdates);
    }
}
