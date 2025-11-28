package org.example.medical_clinic_management_system.xml.exception;

public class XmlProcessingException extends RuntimeException
{

    private final String messageKey;

    public XmlProcessingException(String messageKey, Throwable cause) {
        super(cause);
        this.messageKey = messageKey;
    }

    public String getMessageKey() {
        return messageKey;
    }


}
