/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.websecurity.service;

import javax.ejb.Stateless;
import javax.jws.WebService;

/**
 *
 * @author Helge Waastad <helge.waastad@datametrix.no>
 */
@Stateless
@WebService(
        portName = "CalculatorPort",
        serviceName = "CalculatorService",
        targetNamespace = "http://superbiz.org/wsdl",
        endpointInterface = "org.waastad.websecurity.service.CalculatorWs")
public class Calculator implements CalculatorWs {

    @Override
    public int sum(int add1, int add2) {
        return add1 + add2;
    }
    
}
