/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.websecurity.login;

import java.io.Serializable;
import java.security.Principal;

/**
 *
 * @author Helge Waastad <helge.waastad@datametrix.no>
 */
public class JAASRolePrincipal implements Principal, Serializable {

    private static final long serialVersionUID = 6003532759090606413L;
    private String name;

    public JAASRolePrincipal(String name) {
        if (name == null) {
            throw new NullPointerException("NULL role name");
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "JAASRolePrincipal{" + "name=" + name + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final JAASRolePrincipal other = (JAASRolePrincipal) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return true;
    }
    
}
