/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package no.datametrix.internal;

import javax.ejb.embeddable.EJBContainer;
import javax.inject.Inject;
import javax.naming.NamingException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Helge Waastad <helge.waastad@datametrix.no>
 */
public class NotifierTest {
    
    private static EJBContainer container;
    private static String initialLogProperty;
    
    @Inject private Observer observer;
    
    public NotifierTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        initialLogProperty = System.getProperty("openejb.logger.external");
        System.setProperty("openejb.logger.external", "true");
        container = EJBContainer.createEJBContainer();
    }
    
    @AfterClass
    public static void tearDownClass() {
         if (container != null) {
            container.close();
        }
        if (initialLogProperty != null) {
            System.setProperty("openejb.logger.external", initialLogProperty);
        } else {
            System.getProperties().remove("openejb.logger.external");
        }
    }
    
    @Before
    public void setUp() throws NamingException {
        container.getContext().bind("inject", this);
    }
    
    @After
    public void tearDown() throws NamingException {
        container.getContext().unbind("inject");
    }

    /**
     * Test of myTimer method, of class Notifier.
     */
    @Test
    public void testMyTimer() throws Exception {
        System.out.println("myTimer");
        Thread.sleep(10000);
        assertTrue(observer.getDates().get().size() > 1);
        assertTrue(observer.getAsynchDates().get().size() > 1);
        //assertTrue(observer.getDates().size() > 1);
    }
}