/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.datametrix.internal;

import java.util.Date;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RunAs;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import no.datametrix.qualifier.AsynchQualifier;
import no.datametrix.qualifier.StandardQualifier;

/**
 *
 * @author Helge Waastad <helge.waastad@datametrix.no>
 */
@Singleton
@Startup
@DeclareRoles("InternalGroup")
@RunAs("InternalGroup")
public class Notifier {
        
    @Inject @AsynchQualifier
    private Event<Date> asynchEvent;
    @Inject @StandardQualifier
    private Event<Date> standardEvent;
    
    @Schedule(hour = "*", minute = "*", second = "*")
    public void myTimer() {
        asynchEvent.fire(new Date());
        standardEvent.fire(new Date());
    }
    
}
