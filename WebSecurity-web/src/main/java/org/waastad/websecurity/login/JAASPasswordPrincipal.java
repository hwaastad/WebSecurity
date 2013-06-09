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
public class JAASPasswordPrincipal implements Principal, Serializable {

    private static final long serialVersionUID = -6121899103842302206L;
    private String name;

    public JAASPasswordPrincipal(String name) {
        if (name == null) {
            throw new NullPointerException("NULL password.");
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.name != null ? this.name.hashCode() : 0);
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
        final JAASPasswordPrincipal other = (JAASPasswordPrincipal) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "JAASPasswordPrincipal{" + "name=" + name + '}';
    }
}
