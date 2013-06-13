/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.websecurity.service;

import javax.jws.WebService;

/**
 *
 * @author Helge Waastad <helge.waastad@datametrix.no>
 */
@WebService(targetNamespace = "http://superbiz.org/wsdl")
public interface CalculatorWs {

    public int sum(int add1, int add2);
}
