package org.datacontract.schemas._2004._07.GoldMantis_SMS_Common;

public class ReceiptSMS  implements java.io.Serializable {
    private java.util.Calendar CCDateTime;

    private java.lang.String CCToMobile;

    private java.lang.String fromMobile;

    private java.lang.String ID;

    private java.lang.Boolean infoAppDeal;

    private java.lang.Boolean isRead;

    private java.lang.String msgContent;

    private java.lang.Boolean needAppDeal;

    private java.util.Calendar readTime;

    private java.util.Calendar receiptTime;

    private java.lang.String refSendMsgID;

    private java.lang.String repceiptSysID;

    private java.lang.String subCode;

    private java.lang.String toMobile;

    public ReceiptSMS() {
    }

    public ReceiptSMS(
           java.util.Calendar CCDateTime,
           java.lang.String CCToMobile,
           java.lang.String fromMobile,
           java.lang.String ID,
           java.lang.Boolean infoAppDeal,
           java.lang.Boolean isRead,
           java.lang.String msgContent,
           java.lang.Boolean needAppDeal,
           java.util.Calendar readTime,
           java.util.Calendar receiptTime,
           java.lang.String refSendMsgID,
           java.lang.String repceiptSysID,
           java.lang.String subCode,
           java.lang.String toMobile) {
           this.CCDateTime = CCDateTime;
           this.CCToMobile = CCToMobile;
           this.fromMobile = fromMobile;
           this.ID = ID;
           this.infoAppDeal = infoAppDeal;
           this.isRead = isRead;
           this.msgContent = msgContent;
           this.needAppDeal = needAppDeal;
           this.readTime = readTime;
           this.receiptTime = receiptTime;
           this.refSendMsgID = refSendMsgID;
           this.repceiptSysID = repceiptSysID;
           this.subCode = subCode;
           this.toMobile = toMobile;
    }


    /**
     * Gets the CCDateTime value for this ReceiptSMS.
     * 
     * @return CCDateTime
     */
    public java.util.Calendar getCCDateTime() {
        return CCDateTime;
    }


    /**
     * Sets the CCDateTime value for this ReceiptSMS.
     * 
     * @param CCDateTime
     */
    public void setCCDateTime(java.util.Calendar CCDateTime) {
        this.CCDateTime = CCDateTime;
    }


    /**
     * Gets the CCToMobile value for this ReceiptSMS.
     * 
     * @return CCToMobile
     */
    public java.lang.String getCCToMobile() {
        return CCToMobile;
    }


    /**
     * Sets the CCToMobile value for this ReceiptSMS.
     * 
     * @param CCToMobile
     */
    public void setCCToMobile(java.lang.String CCToMobile) {
        this.CCToMobile = CCToMobile;
    }


    /**
     * Gets the fromMobile value for this ReceiptSMS.
     * 
     * @return fromMobile
     */
    public java.lang.String getFromMobile() {
        return fromMobile;
    }


    /**
     * Sets the fromMobile value for this ReceiptSMS.
     * 
     * @param fromMobile
     */
    public void setFromMobile(java.lang.String fromMobile) {
        this.fromMobile = fromMobile;
    }


    /**
     * Gets the ID value for this ReceiptSMS.
     * 
     * @return ID
     */
    public java.lang.String getID() {
        return ID;
    }


    /**
     * Sets the ID value for this ReceiptSMS.
     * 
     * @param ID
     */
    public void setID(java.lang.String ID) {
        this.ID = ID;
    }


    /**
     * Gets the infoAppDeal value for this ReceiptSMS.
     * 
     * @return infoAppDeal
     */
    public java.lang.Boolean getInfoAppDeal() {
        return infoAppDeal;
    }


    /**
     * Sets the infoAppDeal value for this ReceiptSMS.
     * 
     * @param infoAppDeal
     */
    public void setInfoAppDeal(java.lang.Boolean infoAppDeal) {
        this.infoAppDeal = infoAppDeal;
    }


    /**
     * Gets the isRead value for this ReceiptSMS.
     * 
     * @return isRead
     */
    public java.lang.Boolean getIsRead() {
        return isRead;
    }


    /**
     * Sets the isRead value for this ReceiptSMS.
     * 
     * @param isRead
     */
    public void setIsRead(java.lang.Boolean isRead) {
        this.isRead = isRead;
    }


    /**
     * Gets the msgContent value for this ReceiptSMS.
     * 
     * @return msgContent
     */
    public java.lang.String getMsgContent() {
        return msgContent;
    }


    /**
     * Sets the msgContent value for this ReceiptSMS.
     * 
     * @param msgContent
     */
    public void setMsgContent(java.lang.String msgContent) {
        this.msgContent = msgContent;
    }


    /**
     * Gets the needAppDeal value for this ReceiptSMS.
     * 
     * @return needAppDeal
     */
    public java.lang.Boolean getNeedAppDeal() {
        return needAppDeal;
    }


    /**
     * Sets the needAppDeal value for this ReceiptSMS.
     * 
     * @param needAppDeal
     */
    public void setNeedAppDeal(java.lang.Boolean needAppDeal) {
        this.needAppDeal = needAppDeal;
    }


    /**
     * Gets the readTime value for this ReceiptSMS.
     * 
     * @return readTime
     */
    public java.util.Calendar getReadTime() {
        return readTime;
    }


    /**
     * Sets the readTime value for this ReceiptSMS.
     * 
     * @param readTime
     */
    public void setReadTime(java.util.Calendar readTime) {
        this.readTime = readTime;
    }


    /**
     * Gets the receiptTime value for this ReceiptSMS.
     * 
     * @return receiptTime
     */
    public java.util.Calendar getReceiptTime() {
        return receiptTime;
    }


    /**
     * Sets the receiptTime value for this ReceiptSMS.
     * 
     * @param receiptTime
     */
    public void setReceiptTime(java.util.Calendar receiptTime) {
        this.receiptTime = receiptTime;
    }


    /**
     * Gets the refSendMsgID value for this ReceiptSMS.
     * 
     * @return refSendMsgID
     */
    public java.lang.String getRefSendMsgID() {
        return refSendMsgID;
    }


    /**
     * Sets the refSendMsgID value for this ReceiptSMS.
     * 
     * @param refSendMsgID
     */
    public void setRefSendMsgID(java.lang.String refSendMsgID) {
        this.refSendMsgID = refSendMsgID;
    }


    /**
     * Gets the repceiptSysID value for this ReceiptSMS.
     * 
     * @return repceiptSysID
     */
    public java.lang.String getRepceiptSysID() {
        return repceiptSysID;
    }


    /**
     * Sets the repceiptSysID value for this ReceiptSMS.
     * 
     * @param repceiptSysID
     */
    public void setRepceiptSysID(java.lang.String repceiptSysID) {
        this.repceiptSysID = repceiptSysID;
    }


    /**
     * Gets the subCode value for this ReceiptSMS.
     * 
     * @return subCode
     */
    public java.lang.String getSubCode() {
        return subCode;
    }


    /**
     * Sets the subCode value for this ReceiptSMS.
     * 
     * @param subCode
     */
    public void setSubCode(java.lang.String subCode) {
        this.subCode = subCode;
    }


    /**
     * Gets the toMobile value for this ReceiptSMS.
     * 
     * @return toMobile
     */
    public java.lang.String getToMobile() {
        return toMobile;
    }


    /**
     * Sets the toMobile value for this ReceiptSMS.
     * 
     * @param toMobile
     */
    public void setToMobile(java.lang.String toMobile) {
        this.toMobile = toMobile;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ReceiptSMS)) return false;
        ReceiptSMS other = (ReceiptSMS) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.CCDateTime==null && other.getCCDateTime()==null) || 
             (this.CCDateTime!=null &&
              this.CCDateTime.equals(other.getCCDateTime()))) &&
            ((this.CCToMobile==null && other.getCCToMobile()==null) || 
             (this.CCToMobile!=null &&
              this.CCToMobile.equals(other.getCCToMobile()))) &&
            ((this.fromMobile==null && other.getFromMobile()==null) || 
             (this.fromMobile!=null &&
              this.fromMobile.equals(other.getFromMobile()))) &&
            ((this.ID==null && other.getID()==null) || 
             (this.ID!=null &&
              this.ID.equals(other.getID()))) &&
            ((this.infoAppDeal==null && other.getInfoAppDeal()==null) || 
             (this.infoAppDeal!=null &&
              this.infoAppDeal.equals(other.getInfoAppDeal()))) &&
            ((this.isRead==null && other.getIsRead()==null) || 
             (this.isRead!=null &&
              this.isRead.equals(other.getIsRead()))) &&
            ((this.msgContent==null && other.getMsgContent()==null) || 
             (this.msgContent!=null &&
              this.msgContent.equals(other.getMsgContent()))) &&
            ((this.needAppDeal==null && other.getNeedAppDeal()==null) || 
             (this.needAppDeal!=null &&
              this.needAppDeal.equals(other.getNeedAppDeal()))) &&
            ((this.readTime==null && other.getReadTime()==null) || 
             (this.readTime!=null &&
              this.readTime.equals(other.getReadTime()))) &&
            ((this.receiptTime==null && other.getReceiptTime()==null) || 
             (this.receiptTime!=null &&
              this.receiptTime.equals(other.getReceiptTime()))) &&
            ((this.refSendMsgID==null && other.getRefSendMsgID()==null) || 
             (this.refSendMsgID!=null &&
              this.refSendMsgID.equals(other.getRefSendMsgID()))) &&
            ((this.repceiptSysID==null && other.getRepceiptSysID()==null) || 
             (this.repceiptSysID!=null &&
              this.repceiptSysID.equals(other.getRepceiptSysID()))) &&
            ((this.subCode==null && other.getSubCode()==null) || 
             (this.subCode!=null &&
              this.subCode.equals(other.getSubCode()))) &&
            ((this.toMobile==null && other.getToMobile()==null) || 
             (this.toMobile!=null &&
              this.toMobile.equals(other.getToMobile())));
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
        if (getCCDateTime() != null) {
            _hashCode += getCCDateTime().hashCode();
        }
        if (getCCToMobile() != null) {
            _hashCode += getCCToMobile().hashCode();
        }
        if (getFromMobile() != null) {
            _hashCode += getFromMobile().hashCode();
        }
        if (getID() != null) {
            _hashCode += getID().hashCode();
        }
        if (getInfoAppDeal() != null) {
            _hashCode += getInfoAppDeal().hashCode();
        }
        if (getIsRead() != null) {
            _hashCode += getIsRead().hashCode();
        }
        if (getMsgContent() != null) {
            _hashCode += getMsgContent().hashCode();
        }
        if (getNeedAppDeal() != null) {
            _hashCode += getNeedAppDeal().hashCode();
        }
        if (getReadTime() != null) {
            _hashCode += getReadTime().hashCode();
        }
        if (getReceiptTime() != null) {
            _hashCode += getReceiptTime().hashCode();
        }
        if (getRefSendMsgID() != null) {
            _hashCode += getRefSendMsgID().hashCode();
        }
        if (getRepceiptSysID() != null) {
            _hashCode += getRepceiptSysID().hashCode();
        }
        if (getSubCode() != null) {
            _hashCode += getSubCode().hashCode();
        }
        if (getToMobile() != null) {
            _hashCode += getToMobile().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ReceiptSMS.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/GoldMantis.SMS.Common", "ReceiptSMS"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CCDateTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/GoldMantis.SMS.Common", "CCDateTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CCToMobile");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/GoldMantis.SMS.Common", "CCToMobile"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fromMobile");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/GoldMantis.SMS.Common", "FromMobile"));
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
        elemField.setFieldName("infoAppDeal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/GoldMantis.SMS.Common", "InfoAppDeal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isRead");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/GoldMantis.SMS.Common", "IsRead"));
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
        elemField.setFieldName("needAppDeal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/GoldMantis.SMS.Common", "NeedAppDeal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("readTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/GoldMantis.SMS.Common", "ReadTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("receiptTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/GoldMantis.SMS.Common", "ReceiptTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("refSendMsgID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/GoldMantis.SMS.Common", "RefSendMsgID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("repceiptSysID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://schemas.datacontract.org/2004/07/GoldMantis.SMS.Common", "RepceiptSysID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
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
