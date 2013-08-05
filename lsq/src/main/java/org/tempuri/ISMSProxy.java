package org.tempuri;

public class ISMSProxy implements org.tempuri.ISMS {
  private String _endpoint = null;
  private org.tempuri.ISMS iSMS = null;
  
  public ISMSProxy() {
    _initISMSProxy();
  }
  
  public ISMSProxy(String endpoint) {
    _endpoint = endpoint;
    _initISMSProxy();
  }
  
  private void _initISMSProxy() {
    try {
      iSMS = (new org.tempuri.SMSWCFServiceLocator()).getBasicHttpBinding_ISMS();
      if (iSMS != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)iSMS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)iSMS)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (iSMS != null)
      ((javax.xml.rpc.Stub)iSMS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public org.tempuri.ISMS getISMS() {
    if (iSMS == null)
      _initISMSProxy();
    return iSMS;
  }
  
  public void sendSMSAsync(java.lang.String[] phones, java.lang.String message, java.lang.String senderSysID, java.lang.String senderName, java.lang.String sourceApp, java.util.Calendar replyPeriodTime, java.lang.String replyToMobile, org.datacontract.schemas._2004._07.GoldMantis_SMS_Common.Priority priority, java.lang.String refID, java.lang.Boolean needAppDeal) throws java.rmi.RemoteException{
    if (iSMS == null)
      _initISMSProxy();
    iSMS.sendSMSAsync(phones, message, senderSysID, senderName, sourceApp, replyPeriodTime, replyToMobile, priority, refID, needAppDeal);
  }
  
  public org.datacontract.schemas._2004._07.GoldMantis_SMS_Common.ReceiptSMS readRecMsg(java.lang.String msgID) throws java.rmi.RemoteException{
    if (iSMS == null)
      _initISMSProxy();
    return iSMS.readRecMsg(msgID);
  }
  
  public org.datacontract.schemas._2004._07.GoldMantis_SMS_Common.SendSMS readSendMsg(java.lang.String msgID) throws java.rmi.RemoteException{
    if (iSMS == null)
      _initISMSProxy();
    return iSMS.readSendMsg(msgID);
  }
  
  public void readRecMsgList(java.lang.String sendMsgID, java.lang.Integer pageIndex, java.lang.Integer pageSize, org.datacontract.schemas._2004._07.GoldMantis_SMS_Common.holders.ArrayOfReceiptSMSHolder readRecMsgListResult, javax.xml.rpc.holders.IntegerWrapperHolder recordCount) throws java.rmi.RemoteException{
    if (iSMS == null)
      _initISMSProxy();
    iSMS.readRecMsgList(sendMsgID, pageIndex, pageSize, readRecMsgListResult, recordCount);
  }
  
  public void getSendList(java.lang.String phone, java.lang.String content, java.lang.Integer status, java.util.Calendar sendDateFrom, java.util.Calendar sendDateTo, java.lang.String senderSysID, java.lang.String sourceApp, java.lang.Integer replyStatus, java.util.Calendar replyDateFrom, java.util.Calendar replyDateTo, java.lang.Integer pageIndex, java.lang.Integer pageSize, org.datacontract.schemas._2004._07.GoldMantis_SMS_Common.holders.ArrayOfSendSMSHolder getSendListResult, javax.xml.rpc.holders.IntegerWrapperHolder recordCount) throws java.rmi.RemoteException{
    if (iSMS == null)
      _initISMSProxy();
    iSMS.getSendList(phone, content, status, sendDateFrom, sendDateTo, senderSysID, sourceApp, replyStatus, replyDateFrom, replyDateTo, pageIndex, pageSize, getSendListResult, recordCount);
  }
  
  public void getReceiptList(java.lang.String phone, java.lang.String content, java.util.Calendar recDateFrom, java.util.Calendar recDateTo, java.lang.String senderSysID, java.lang.Integer readStatus, java.lang.Integer pageIndex, java.lang.Integer pageSize, org.datacontract.schemas._2004._07.GoldMantis_SMS_Common.holders.ArrayOfReceiptSMSHolder getReceiptListResult, javax.xml.rpc.holders.IntegerWrapperHolder recordCount) throws java.rmi.RemoteException{
    if (iSMS == null)
      _initISMSProxy();
    iSMS.getReceiptList(phone, content, recDateFrom, recDateTo, senderSysID, readStatus, pageIndex, pageSize, getReceiptListResult, recordCount);
  }
  
  
}