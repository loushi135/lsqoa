package org.tempuri;

public interface SMSWCFService extends javax.xml.rpc.Service {
    public java.lang.String getBasicHttpBinding_ISMSAddress();

    public org.tempuri.ISMS getBasicHttpBinding_ISMS() throws javax.xml.rpc.ServiceException;

    public org.tempuri.ISMS getBasicHttpBinding_ISMS(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
