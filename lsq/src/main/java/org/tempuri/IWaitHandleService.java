package org.tempuri;

public interface IWaitHandleService extends java.rmi.Remote {
    public void addWaitHandleJob(java.lang.String waitHandleJobId, java.lang.String billCode, java.lang.String workflowName, java.lang.String requestJobCode, java.lang.String handlerJobCode, java.lang.String workflowNodeName, java.lang.String sourceApp, java.lang.String waitHandleJobUrl, java.lang.String memo, javax.xml.rpc.holders.BooleanWrapperHolder addWaitHandleJobResult, javax.xml.rpc.holders.StringHolder message) throws java.rmi.RemoteException;
    public void processWaitHandleJob(java.lang.String waitHandleJobId, java.lang.String sourceApp, java.lang.String factHandleJobCode, org.datacontract.schemas._2004._07.GoldMantis_WaitHandle_Common_Interface.ProcessMethod processMethod, javax.xml.rpc.holders.BooleanWrapperHolder processWaitHandleJobResult, javax.xml.rpc.holders.StringHolder message) throws java.rmi.RemoteException;
    public void getWaitHandleJob(java.lang.String loginUserJobId, java.lang.String sourceApp, org.datacontract.schemas._2004._07.GoldMantis_WorkFlow_Implement.holders.ArrayOfMyTokensModelHolder getWaitHandleJobResult, javax.xml.rpc.holders.StringHolder message) throws java.rmi.RemoteException;
}
