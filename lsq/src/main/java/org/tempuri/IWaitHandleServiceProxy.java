package org.tempuri;

public class IWaitHandleServiceProxy implements org.tempuri.IWaitHandleService {
  private String _endpoint = null;
  private org.tempuri.IWaitHandleService iWaitHandleService = null;
  
  public IWaitHandleServiceProxy() {
    _initIWaitHandleServiceProxy();
  }
  
  public IWaitHandleServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initIWaitHandleServiceProxy();
  }
  
  private void _initIWaitHandleServiceProxy() {
    try {
      iWaitHandleService = (new org.tempuri.WaitHandleServiceLocator()).getBasicHttpBinding_IWaitHandleService();
      if (iWaitHandleService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)iWaitHandleService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)iWaitHandleService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (iWaitHandleService != null)
      ((javax.xml.rpc.Stub)iWaitHandleService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.tempuri.IWaitHandleService getIWaitHandleService() {
    if (iWaitHandleService == null)
      _initIWaitHandleServiceProxy();
    return iWaitHandleService;
  }
  
  public void addWaitHandleJob(java.lang.String waitHandleJobId, java.lang.String billCode, java.lang.String workflowName, java.lang.String requestJobCode, java.lang.String handlerJobCode, java.lang.String workflowNodeName, java.lang.String sourceApp, java.lang.String waitHandleJobUrl, java.lang.String memo, javax.xml.rpc.holders.BooleanWrapperHolder addWaitHandleJobResult, javax.xml.rpc.holders.StringHolder message) throws java.rmi.RemoteException{
    if (iWaitHandleService == null)
      _initIWaitHandleServiceProxy();
    iWaitHandleService.addWaitHandleJob(waitHandleJobId, billCode, workflowName, requestJobCode, handlerJobCode, workflowNodeName, sourceApp, waitHandleJobUrl, memo, addWaitHandleJobResult, message);
  }
  
  public void processWaitHandleJob(java.lang.String waitHandleJobId, java.lang.String sourceApp, java.lang.String factHandleJobCode, org.datacontract.schemas._2004._07.GoldMantis_WaitHandle_Common_Interface.ProcessMethod processMethod, javax.xml.rpc.holders.BooleanWrapperHolder processWaitHandleJobResult, javax.xml.rpc.holders.StringHolder message) throws java.rmi.RemoteException{
    if (iWaitHandleService == null)
      _initIWaitHandleServiceProxy();
    iWaitHandleService.processWaitHandleJob(waitHandleJobId, sourceApp, factHandleJobCode, processMethod, processWaitHandleJobResult, message);
  }
  
  public void getWaitHandleJob(java.lang.String loginUserJobId, java.lang.String sourceApp, org.datacontract.schemas._2004._07.GoldMantis_WorkFlow_Implement.holders.ArrayOfMyTokensModelHolder getWaitHandleJobResult, javax.xml.rpc.holders.StringHolder message) throws java.rmi.RemoteException{
    if (iWaitHandleService == null)
      _initIWaitHandleServiceProxy();
    iWaitHandleService.getWaitHandleJob(loginUserJobId, sourceApp, getWaitHandleJobResult, message);
  }
  
  
}