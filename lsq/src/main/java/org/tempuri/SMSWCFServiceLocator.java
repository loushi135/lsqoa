package org.tempuri;

public class SMSWCFServiceLocator extends org.apache.axis.client.Service implements org.tempuri.SMSWCFService {

    public SMSWCFServiceLocator() {
    }


    public SMSWCFServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SMSWCFServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for BasicHttpBinding_ISMS
    private java.lang.String BasicHttpBinding_ISMS_address = "http://127.0.0.1:9032/SMSWCFService";

    public java.lang.String getBasicHttpBinding_ISMSAddress() {
        return BasicHttpBinding_ISMS_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String BasicHttpBinding_ISMSWSDDServiceName = "BasicHttpBinding_ISMS";

    public java.lang.String getBasicHttpBinding_ISMSWSDDServiceName() {
        return BasicHttpBinding_ISMSWSDDServiceName;
    }

    public void setBasicHttpBinding_ISMSWSDDServiceName(java.lang.String name) {
        BasicHttpBinding_ISMSWSDDServiceName = name;
    }

    public org.tempuri.ISMS getBasicHttpBinding_ISMS() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(BasicHttpBinding_ISMS_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getBasicHttpBinding_ISMS(endpoint);
    }

    public org.tempuri.ISMS getBasicHttpBinding_ISMS(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.tempuri.BasicHttpBinding_ISMSStub _stub = new org.tempuri.BasicHttpBinding_ISMSStub(portAddress, this);
            _stub.setPortName(getBasicHttpBinding_ISMSWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setBasicHttpBinding_ISMSEndpointAddress(java.lang.String address) {
        BasicHttpBinding_ISMS_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.tempuri.ISMS.class.isAssignableFrom(serviceEndpointInterface)) {
                org.tempuri.BasicHttpBinding_ISMSStub _stub = new org.tempuri.BasicHttpBinding_ISMSStub(new java.net.URL(BasicHttpBinding_ISMS_address), this);
                _stub.setPortName(getBasicHttpBinding_ISMSWSDDServiceName());
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
        if ("BasicHttpBinding_ISMS".equals(inputPortName)) {
            return getBasicHttpBinding_ISMS();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "SMSWCFService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "BasicHttpBinding_ISMS"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("BasicHttpBinding_ISMS".equals(portName)) {
            setBasicHttpBinding_ISMSEndpointAddress(address);
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
