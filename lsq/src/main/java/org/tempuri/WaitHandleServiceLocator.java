package org.tempuri;

public class WaitHandleServiceLocator extends org.apache.axis.client.Service implements org.tempuri.WaitHandleService {

    public WaitHandleServiceLocator() {
    }


    public WaitHandleServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WaitHandleServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for BasicHttpBinding_IWaitHandleService
    private java.lang.String BasicHttpBinding_IWaitHandleService_address = "http://127.0.0.1:9052/WaitHandleWCFService";

    public java.lang.String getBasicHttpBinding_IWaitHandleServiceAddress() {
        return BasicHttpBinding_IWaitHandleService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String BasicHttpBinding_IWaitHandleServiceWSDDServiceName = "BasicHttpBinding_IWaitHandleService";

    public java.lang.String getBasicHttpBinding_IWaitHandleServiceWSDDServiceName() {
        return BasicHttpBinding_IWaitHandleServiceWSDDServiceName;
    }

    public void setBasicHttpBinding_IWaitHandleServiceWSDDServiceName(java.lang.String name) {
        BasicHttpBinding_IWaitHandleServiceWSDDServiceName = name;
    }

    public org.tempuri.IWaitHandleService getBasicHttpBinding_IWaitHandleService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(BasicHttpBinding_IWaitHandleService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getBasicHttpBinding_IWaitHandleService(endpoint);
    }

    public org.tempuri.IWaitHandleService getBasicHttpBinding_IWaitHandleService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.tempuri.BasicHttpBinding_IWaitHandleServiceStub _stub = new org.tempuri.BasicHttpBinding_IWaitHandleServiceStub(portAddress, this);
            _stub.setPortName(getBasicHttpBinding_IWaitHandleServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setBasicHttpBinding_IWaitHandleServiceEndpointAddress(java.lang.String address) {
        BasicHttpBinding_IWaitHandleService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.tempuri.IWaitHandleService.class.isAssignableFrom(serviceEndpointInterface)) {
                org.tempuri.BasicHttpBinding_IWaitHandleServiceStub _stub = new org.tempuri.BasicHttpBinding_IWaitHandleServiceStub(new java.net.URL(BasicHttpBinding_IWaitHandleService_address), this);
                _stub.setPortName(getBasicHttpBinding_IWaitHandleServiceWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("BasicHttpBinding_IWaitHandleService".equals(inputPortName)) {
            return getBasicHttpBinding_IWaitHandleService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "WaitHandleService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "BasicHttpBinding_IWaitHandleService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("BasicHttpBinding_IWaitHandleService".equals(portName)) {
            setBasicHttpBinding_IWaitHandleServiceEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
