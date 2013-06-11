/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.websecurity.view;

import java.security.Principal;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import org.waastad.business.BorderControl;

/**
 *
 * @author Helge Waastad <helge.waastad@datametrix.no>
 */
@Named
@RequestScoped
public class ViewController {
    @EJB
    private BorderControl borderControl;
    
    public void action(ActionEvent event){
        FacesContext context = FacesContext.getCurrentInstance();
        Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        System.out.println("ViewController Start");
        if (context.getExternalContext().isUserInRole("InternalGroup")) {
            System.out.println("ViewController: User is in InternalGroup");
        } else
            if (context.getExternalContext().isUserInRole("SuperAdmin")) {
            System.out.println("ViewController: User is in SuperAdmin");
        } else {
            System.out.println("ViewController: Do not know which group the user belongs...");
        }
        System.out.println("ViewController: Calling BorderController. Principal Name: " + principal.getName());
        borderControl.sayHelloBorder();
    }
    
}
