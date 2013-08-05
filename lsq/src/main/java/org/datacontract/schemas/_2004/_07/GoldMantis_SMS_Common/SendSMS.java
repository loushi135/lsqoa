package org.datacontract.schemas._2004._07.GoldMantis_SMS_Common;

public class SendSMS  implements java.io.Serializable {
    private java.util.Calendar enterTime;

    private java.util.Calendar factSendTime;

    private java.lang.String fromNumber;

    private java.lang.String ID;

    private java.lang.Boolean isReply;

    private java.lang.String msgContent;

    private java.lang.Integer msgCount;

    private org.datacontract.schemas._2004._07.GoldMantis_SMS_Common.Priority priority;

    private java.lang.String replyMsgID;

    private java.lang.Boolean replyNeedAppDeal;

    private java.util.Calendar replyPeriodTime;

    private java.util.Calendar replyTime;

    private java.lang.String replyToMobile;

    private java.lang.String senderName;

    private java.lang.String senderSysID;

    private java.lang.String sourceApp;

    private org.datacontract.schemas._2004._07.GoldMantis_SMS_Common.SendStatus status;

    private java.lang.String subCode;

    private java.lang.String toMobile;

    private java.lang.String refReceiptID;

    public SendSMS() {
    }

    public SendSMS(
           java.util.Calendar enterTime,
           java.util.Calendar factSendTime,
           java.lang.String fromNumber,
           java.lang.String ID,
           java.lang.Boolean isReply,
           java.lang.String msgContent,
           java.lang.Integer msgCount,
           org.datacontract.schemas._2004._07.GoldMantis_SMS_Common.Priority priority,
           java.lang.String replyMsgID,
           java.lang.Boolean replyNeedAppDeal,
           java.util.Calendar replyPeriodTime,
           java.util.Calendar replyTime,
           java.lang.String replyToMobile,
           java.lang.String senderName,
           java.lang.String senderSysID,
           java.lang.String sourceApp,
           org.datacontract.schemas._2004._07.GoldMantis_SMS_Common.SendStatus status,
           java.lang.String subCode,
           java.lang.String toMobile,
           java.lang.String refReceiptID) {
           this.enterTime = enterTime;
           this.factSendTime = factSendTime;
           this.fromNumber = fromNumber;
           this.ID = ID;
           this.isReply = isReply;
           this.msgContent = msgContent;
           this.msgCount = msgCount;
           this.priority = priority;
           this.replyMsgID = replyMsgID;
           this.replyNeedAppDeal = replyNeedAppDeal;
           this.replyPeriodTime = replyPeriodTime;
           this.replyTime = replyTime;
           this.replyToMobile = replyToMobile;
           this.senderName = senderName;
           this.senderSysID = senderSysID;
           this.sourceApp = sourceApp;
           this.status = status;
           this.subCode = subCode;
           this.toMobile = toMobile;
           this.refReceiptID = refReceiptID;
    }


    /**
     * Gets the enterTime value for this SendSMS.
     * 
     * @return enterTime
     */
    public java.util.Calendar getEnterTime() {
        return enterTime;
    }


    /**
     * Sets the enterTime value for this SendSMS.
     * 
     * @param enterTime
     */
    public void setEnterTime(java.util.Calendar enterTime) {
        this.enterTime = enterTime;
    }


    /**
     * Gets the factSendTime value for this SendSMS.
     * 
     * @return factSendTime
     */
    public java.util.Calendar getFactSendTime() {
        return factSendTime;
    }


    /**
     * Sets the factSendTime value for this SendSMS.
     * 
     * @param factSendTime
     */
    public void setFactSendTime(java.util.Calendar factSendTime) {
        this.factSendTime = factSendTime;
    }


    /**
     * Gets the fromNumber value for this SendSMS.
     * 
     * @return fromNumber
     */
    public java.lang.String getFromNumber() {
        return fromNumber;
    }


    /**
     * Sets the fromNumber value for this SendSMS.
     * 
     * @param fromNumber
     */
    public void setFromNumber(java.lang.String fromNumber) {
        this.fromNumber = fromNumber;
    }


    /**
     * Gets the ID value for this SendSMS.
     * 
     * @return ID
     */
    public java.lang.String getID() {
        return ID;
    }


    /**
     * Sets the ID value for this SendSMS.
     * 
     * @param ID
     */
    public void setID(java.lang.String ID) {
        this.ID = ID;
    }


    /**
     * Gets the isReply value for this SendSMS.
     * 
     * @return isReply
     */
    public java.lang.Boolean getIsReply() {
        return isReply;
    }


    /**
     * Sets the isReply value for this SendSMS.
     * 
     * @param isReply
     */
    public void setIsReply(java.lang.Boolean isReply) {
        this.isReply = isReply;
    }


    /**
     * Gets the msgContent value for this SendSMS.
     * 
     * @return msgContent
     */
    public java.lang.String getMsgContent() {
        return msgContent;
    }


    /**
     * Sets the msgContent value for this SendSMS.
     * 
     * @param msgContent
     */
    public void setMsgContent(java.lang.String msgContent) {
        this.msgContent = msgContent;
    }


    /**
     * Gets the msgCount value for this SendSMS.
     * 
     * @return msgCount
     */
    public java.lang.Integer getMsgCount() {
        return msgCount;
    }


    /**
     * Sets the msgCount value for this SendSMS.
     * 
     * @param msgCount
     */
    public void setMsgCount(java.lang.Integer msgCount) {
        this.msgCount = msgCount;
    }


    /**
     * Gets the priority value for this SendSMS.
     * 
     * @return priority
     */
    public org.datacontract.schemas._2004._07.GoldMantis_SMS_Common.Priority getPriority() {
        return priority;
    }


    /**
     * Sets the priority value for this SendSMS.
     * 
     * @param priority
     */
    public void setPriority(org.datacontract.schemas._2004._07.GoldMantis_SMS_Common.Priority priority) {
        this.priority = priority;
    }


    /**
     * Gets the replyMsgID value for this SendSMS.
     * 
     * @return replyMsgID
     */
    public java.lang.String getReplyMsgID() {
        return replyMsgID;
    }


    /**
     * Sets the replyMsgID value for this SendSMS.
     * 
     * @param replyMsgID
     */
    public void setReplyMsgID(java.lang.String replyMsgID) {
        this.replyMsgID = replyMsgID;
    }


    /**
     * Gets the replyNeedAppDeal value for this SendSMS.
     * 
     * @return replyNeedAppDeal
     */
    public java.lang.Boolean getReplyNeedAppDeal() {
        return replyNeedAppDeal;
    }


    /**
     * Sets the replyNeedAppDeal value for this SendSMS.
     * 
     * @param replyNeedAppDeal
     */
    public void setReplyNeedAppDeal(java.lang.Boolean replyNeedAppDeal) {
        this.replyNeedAppDeal = replyNeedAppDeal;
    }


    /**
     * Gets the replyPeriodTime value for this SendSMS.
     * 
     * @return replyPeriodTime
     */
    public java.util.Calendar getReplyPeriodTime() {
        return replyPeriodTime;
    }


    /**
     * Sets the replyPeriodTime value for this SendSMS.
     * 
     * @param replyPeriodTime
     */
    public void setReplyPeriodTime(java.util.Calendar replyPeriodTime) {
        this.replyPeriodTime = replyPeriodTime;
    }


    /**
     * Gets the replyTime value for this SendSMS.
     * 
     * @return replyTime
     */
    public java.util.Calendar getReplyTime() {
        return replyTime;
    }


    /**
     * Sets the replyTime value for this SendSMS.
     * 
     * @param replyTime
     */
    public void setReplyTime(java.util.Calendar replyTime) {
        this.replyTime = replyTime;
    }


    /**
     * Gets the replyToMobile value for this SendSMS.
     * 
     * @return replyToMobile
     */
    public java.lang.String getReplyToMobile() {
        return replyToMobile;
    }


    /**
     * Sets the replyToMobile value for this SendSMS.
     * 
     * @param replyToMobile
     */
    public void setReplyToMobile(java.lang.String replyToMobile) {
        this.replyToMobile = replyToMobile;
    }


    /**
     * Gets the senderName value for this SendSMS.
     * 
     * @return senderName
     */
    public java.lang.String getSenderName() {
        return senderName;
    }


    /**
     * Sets the senderName value for this SendSMS.
     * 
     * @param senderName
     */
    public void setSenderName(java.lang.String senderName) {
        this.senderName = senderName;
    }


    /**
     * Gets the senderSysID value for this SendSMS.
     * 
     * @return senderSysID
     */
    public java.lang.String getSenderSysID() {
        return senderSysID;
    }


    /**
     * Sets the senderSysID value for this SendSMS.
     * 
     * @param senderSysID
     */
    public void setSenderSysID(java.lang.String senderSysID) {
        this.senderSysID = senderSysID;
    }


    /**
     * Gets the sourceApp value for this SendSMS.
     * 
     * @return sourceApp
     */
    public java.lang.String getSourceApp() {
        return sourceApp;
    }


    /**
     * Sets the sourceApp value for this SendSMS.
     * 
     * @param sourceApp
     */
    public void setSourceApp(java.lang.String sourceApp) {
        this.sourceApp = sourceApp;
    }


    /**
     * Gets the status value for this SendSMS.
     * 
     * @return status
     */
    public org.datacontract.schemas._2004._07.GoldMantis_SMS_Common.SendStatus getStatus() {
        return status;
    }


    /**
     * Sets the status value for this SendSMS.
     * 
     * @param status
     */
    public void setStatus(org.datacontract.schemas._2004._07.GoldMantis_SMS_Common.SendStatus status) {
        this.status = status;
    }


    /**
     * Gets the subCode value for this SendSMS.
     * 
     * @return subCode
     */
    public java.lang.String getSubCode() {
        return subCode;
    }


    /**
     * Sets the subCode value for this SendSMS.
     * 
     * @param subCode
     */
    public void setSubCode(java.lang.String subCode) {
        this.subCode = subCode;
    }


    /**
     * Gets the toMobile value for this SendSMS.
     * 
     * @return toMobile
     */
    public java.lang.String getToMobile() {
        return toMobile;
    }


    /**
     * Sets the toMobile value for this SendSMS.
     * 
     * @param toMobile
     */
    public void setToMobile(java.lang.String toMobile) {
        this.toMobile = toMobile;
    }


    /**
     * Gets the refReceiptID value for this SendSMS.
     * 
     * @return refReceiptID
     */
    public java.lang.String getRefReceiptID() {
        return refReceiptID;
    }


    /**
     * Sets the refReceiptID value for this SendSMS.
     * 
     * @param refReceiptID
     */
    public void setRefReceiptID(java.lang.String refReceiptID) {
        this.refReceiptID = refReceiptID;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SendSMS)) return false;
        SendSMS other = (SendSMS) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.enterTime==null && other.getEnterTime()==null) || 
             (this.enterTime!=null &&
              this.enterTime.equals(other.getEnterTime()))) &&
            ((this.factSendTime==null && other.getFactSendTime()==null) || 
             (this.factSendTime!=null &&
              this.factSendTime.equals(other.getFactSendTime()))) &&
            ((this.fromNumber==null && other.getFromNumber()==null) || 
             (this.fromNumber!=null &&
              this.fromNumber.equals(other.getFromNumber()))) &&
            ((this.ID==null && other.getID()==null) || 
             (this.ID!=null &&
              this.ID.equals(other.getID()))) &&
            ((this.isReply==null && other.getIsReply()==null) || 
             (this.isReply!=null &&
              this.isReply.equals(other.getIsReply()))) &&
            ((this.msgContent==null && other.getMsgContent()==null) || 
             (this.msgContent!=null &&
              this.msgContent.equals(other.getMsgContent()))) &&
            ((this.msgCount==null && other.getMsgCount()==null) || 
             (this.msgCount!=null &&
              this.msgCount.equals(other.getMsgCount()))) &&
            ((this.priority==null && other.getPriority()==null) || 
             (this.priority!=null &&
              this.priority.equals(other.getPriority()))) &&
            ((this.replyMsgID==null && other.getReplyMsgID()==null) || 
             (this.replyMsgID!=null &&
              this.replyMsgID.equals(other.getReplyMsgID()))) &&
            ((this.replyNeedAppDeal==null && other.getReplyNeedAppDeal()==null) || 
             (this.replyNeedAppDeal!=null &&
              this.replyNeedAppDeal.equals(other.getReplyNeedAppDeal()))) &&
            ((this.replyPeriodTime==null && other.getReplyPeriodTime()==null) || 
             (this.replyPeriodTime!=null &&
              this.replyPeriodTime.equals(other.getReplyPeriodTime()))) &&
            ((this.replyTime==null && other.getReplyTime()==null) || 
             (this.replyTime!=null &&
              this.replyTime.equals(other.getReplyTime()))) &&
            ((this.replyToMobile==null && other.getReplyToMobile()==null) || 
             (this.replyToMobile!=null &&
              this.replyToMobile.equals(other.getReplyToMobile()))) &&
            ((this.senderName==null && other.getSenderName()==null) || 
             (this.senderName!=null &&
              this.senderName.equals(other.getSenderName()))) &&
            ((this.senderSysID==null && other.getSenderSysID()==null) || 
             (this.senderSysID!=null &&
              this.senderSysID.equals(other.getSenderSysID()))) &&
            ((this.sourceApp==null && other.getSourceApp()==null) || 
             (this.sourceApp!=null &&
              this.sourceApp.equals(other.getSourceApp()))) &&
            ((this.status==null && other.getStatus()==null) || 
             (this.status!=null &&
              this.status.equals(other.getStatus()))) &&
            ((this.subCode==null && other.getSubCode()==null) || 
             (this.subCode!=null &&
              this.subCode.equals(other.getSubCode()))) &&
            ((this.toMobile==null && other.getToMobile()==null) || 
             (this.toMobile!=null &&
              this.toMobile.equals(other.getToMobile()))) &&
            ((this.refReceiptID==null && other.getRefReceiptID()==null) || 
             (this.refReceiptID!=null &&
              this.refReceiptID.equals(other.getRefReceiptID())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getEnterTime() != null) {
            _hashCode += getEnterTime().hashCode();
        }
        if (getFactSendTime() != null) {
            _hashCode += getFactSendTime().hashCode();
        }
        if (getFromNumber() != null) {
            _hashCode += getFromNumber().hashCode();
        }
        if (getID() != null) {
            _hashCode += getID().hashCode();
        }
        if (getIsReply() != null) {
            _hashCode += getIsReply().hashCode();
        }
        if (getMsgContent() != null) {
            _hashCode += getMsgContent().hashCode();
        }
        if (getMsgCount() != null) {
            _hashCode += getMsgCount().hashCode();
        }
        if (getPriority() != null) {
            _hashCode += getPriority().hashCode();
        }
        if (getReplyMsgID() != null) {
            _hashCode += getReplyMsgID().hashCode();
        }
        if (getReplyNeedAppDeal() != null) {
            _hashCode += getReplyNeedAppDeal().hashCode();
        }
        if (getReplyPeriodTime() != null) {
            _hashCode += getReplyPeriodTime().hashCode();
        }
        if (getReplyTime() != null) {
            _hashCode += getReplyTime().hashCode();
        }
        if (getReplyToMobile() != null) {
            _hashCode += getReplyToMobile().hashCode();
        }
        if (getSenderName() != null) {
            _hashCode += getSenderName().hashCode();
        }
        if (getSenderSysID() != null) {
            _hashCode += getSenderSysID().hashCode();
        }
        if (getSourceApp() != null) {
            _hashCode += getSourceApp().hashCode();
        }
        if (getStatus() != null) {
            _hashCode += getStatus().hashCode();
        }
        if (getSubCode() != null) {
            _hashCode += getSubCode().hashCode();
        }
        if (getToMobile() != null) {
            _hashCode += getToMobile().hashCode();
        }
        if (getRefReceiptID() != null) {
            _hashCode += getRefReceiptID().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SendSMS.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/GoldMantis.SMS.Common", "SendSMS"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("enterTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/GoldMantis.SMS.Common", "EnterTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("factSendTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/GoldMantis.SMS.Common", "FactSendTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fromNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/GoldMantis.SMS.Common", "FromNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/GoldMantis.SMS.Common", "ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isReply");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/GoldMantis.SMS.Common", "IsReply"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("msgContent");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/GoldMantis.SMS.Common", "MsgContent"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("msgCount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/GoldMantis.SMS.Common", "MsgCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("priority");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/GoldMantis.SMS.Common", "Priority"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/GoldMantis.SMS.Common", "Priority"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("replyMsgID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/GoldMantis.SMS.Common", "ReplyMsgID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("replyNeedAppDeal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/GoldMantis.SMS.Common", "ReplyNeedAppDeal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("replyPeriodTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/GoldMantis.SMS.Common", "ReplyPeriodTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("replyTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/GoldMantis.SMS.Common", "ReplyTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("replyToMobile");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/GoldMantis.SMS.Common", "ReplyToMobile"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("senderName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/GoldMantis.SMS.Common", "SenderName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("senderSysID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/GoldMantis.SMS.Common", "SenderSysID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sourceApp");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/GoldMantis.SMS.Common", "SourceApp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("status");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/GoldMantis.SMS.Common", "Status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/GoldMantis.SMS.Common", "SendStatus"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/GoldMantis.SMS.Common", "SubCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("toMobile");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/GoldMantis.SMS.Common", "ToMobile"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("refReceiptID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/GoldMantis.SMS.Common", "refReceiptID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
