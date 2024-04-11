package com.invoice.config;

public class ExtendedConstants {

    public enum ResponseCode {

        SUCCESS("00","Successful"),
        FAIL("99","Failed Request")
        ;

        ResponseCode(String status,String message){
            this.status = status;
            this.message = message;
        }
        String status;
        String message;

        public String getStatus(){
            return status;
        }

        public String getMessage(){
            return message;
        }
    }
}
