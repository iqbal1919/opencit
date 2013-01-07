/*
 * Copyright (C) 2012 Intel Corporation
 * All rights reserved.
 */
package com.intel.mtwilson.agent.vmware;

import com.intel.mountwilson.util.vmware.VMwareClient;
import com.intel.mtwilson.tls.TlsConnection;
import org.apache.commons.pool.BaseKeyedPoolableObjectFactory;

/**
 * The VmwareClientFactory creates VmwareClient instances. After an instance
 * is created the factory calls connect() so the object you get back is already
 * connected to its vCenter and ready to use. These objects can be used
 * directly or put in a connection pool.
 * 
 * See also KeyedPoolableObjectFactory in Apache Commons Pool
 * 
 * @author jbuhacoff
 */
public class VmwareClientFactory extends BaseKeyedPoolableObjectFactory<TlsConnection,VMwareClient> {
    
    @Override
    public VMwareClient makeObject(TlsConnection tlsConnection) throws Exception {
        VMwareClient client = new VMwareClient();
        client.setTlsPolicy(tlsConnection.getTlsPolicy());
        client.connect(tlsConnection.getConnectionString());        
        return client;
    }
    
    /**
     * This gets called every time an object is being borrowed from the pool.
     * We don't need to do anything here, as vmware clients in the pool should
     * already be connected (that is the purpose of maintaining a pool of vmware
     * clients).
     * @param tlsConnection
     * @param client
     * @throws Exception 
     */
    @Override
    public void activateObject(TlsConnection tlsConnection, VMwareClient client) throws Exception {
    }
    
    /**
     * If the pool is configured to validate objects before borrowing, then
     * this is called every time an object is being borrowed from the pool.
     * We validate the vmware client connection by making a quick
     * call to vcenter here. that way if it fails the pool can destroy the 
     * client and create a new one for the caller.
     * @param tlsConnection
     * @param client
     * @return 
     */
    @Override
    public boolean validateObject(TlsConnection tlsConnection, VMwareClient client) {
        return client.isConnected(); //return true;
    }
    
    /**
     * This is called when the pool needs to get rid of a client - maybe because
     * it was idle too long and lost its connection, or because there are too
     * many idle clients, etc.
     * @param tlsConnection
     * @param client
     * @throws Exception 
     */
    @Override
    public void destroyObject(TlsConnection tlsConnection, VMwareClient client) throws Exception {
        client.disconnect();
    }
}