/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intel.mtwilson.as.rest.v2.model;

import com.intel.mtwilson.jaxrs2.DefaultFilterCriteria;
import com.intel.mtwilson.jaxrs2.FilterCriteria;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

/**
 *
 * @author ssbangal
 */
public class HostTlsCertificateFilterCriteria extends DefaultFilterCriteria implements FilterCriteria<HostTlsCertificate> {
    
    @PathParam("host_id")
    public String hostUuid;
    @QueryParam("sha1")
    public String sha1;
        
}
