package com.example.event;

/**
 * author : qiukailong
 * date   : 2020/8/25  6:26 PM
 * desc   :
 */
public class ClientMessageEvent {
    private int messageRead;
    private int length;
    private int i;
    private byte[] newBuffer;

    public ClientMessageEvent(int messageRead, int length, int i, byte[] newBuffer) {
        this.messageRead = messageRead;
        this.length = length;
        this.i = i;
        this.newBuffer = newBuffer;
    }

    public int getMessageRead() {
        return messageRead;
    }

    public void setMessageRead(int messageRead) {
        this.messageRead = messageRead;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public byte[] getNewBuffer() {
        return newBuffer;
    }

    public void setNewBuffer(byte[] newBuffer) {
        this.newBuffer = newBuffer;
    }
}
