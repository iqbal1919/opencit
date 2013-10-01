/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intel.mtwilson.datatypes;

import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;

/**
 *
 * @author dsmagadx
 */
public class ErrorCodeTest {
    
    //@Test
    public void testErrorCodeWithFormataAndMultipleConcat(){
        AuthResponse authResponse = new AuthResponse(ErrorCode.WS_MLE_ASSOCIATION_EXISTS,"hello","how",1111);
        if(authResponse.getErrorCodeEnum() == ErrorCode.WS_MLE_ASSOCIATION_EXISTS)
            System.out.println(authResponse.getErrorMessage());
    }

    //@Test
    public void testErrorCodeWithFormataAndSingleConcat(){
        AuthResponse authResponse = new AuthResponse(ErrorCode.WS_MLE_DATA_MISSING,"MLENAME");
        if(authResponse.getErrorCodeEnum() == ErrorCode.WS_MLE_DATA_MISSING)
            System.out.println(authResponse.getErrorMessage());
    }
    
    //@Test
    public void testConnectionString() {
        try {
            System.out.println("Testing ConnectionString datatype.");
            ConnectionString conStr = new ConnectionString("citrix:https://vcenterserver.com:1234;username;password");
            System.out.println(conStr.getAddOnConnectionString());
            System.out.println(conStr.getManagementServerName());
            System.out.println(conStr.getPort());
        } catch (MalformedURLException ex) {
            Logger.getLogger(ErrorCodeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //@Test
    public void testConnectionString2() {
        try {
            System.out.println("Testing ConnectionString datatype.");
            ConnectionString conStr = new ConnectionString(Vendor.VMWARE, "https://vcenterserver.com:1234/sdk;username;password");
            System.out.println(conStr.getConnectionStringWithPrefix());
            System.out.println(conStr.getConnectionString());
        } catch (MalformedURLException ex) {
            Logger.getLogger(ErrorCodeTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ErrorCodeTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //@Test
    public void testErrorCodeWithNoFormat(){
        AuthResponse authResponse = new AuthResponse(ErrorCode.WS_MLE_DATA_MISSING);
         if(authResponse.getErrorCodeEnum() == ErrorCode.WS_MLE_DATA_MISSING)
            System.out.println(authResponse.getErrorMessage());
    }

    
}
