/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.websecurity.view;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import org.waastad.business.BusinessBean;

/**
 *
 * @author Helge Waastad <helge.waastad@datametrix.no>
 */
@Named
@RequestScoped
public class ViewController {
    @EJB
    private BusinessBean businessBean;
    
    public void action(ActionEvent event){
        businessBean.sayHello();
    }
    
}
