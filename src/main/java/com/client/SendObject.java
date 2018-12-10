package com.client;

public class SendObject implements java.io.Serializable {
    public byte[] imgByte;
    public String divnum;
    public SendObject(byte data[] , String divNum)
    {
        this.imgByte=data;
        this.divnum=divNum;
    }

}
