package net.moetang.nekoq.client.simple;

/**
 * Created on 15/1/10.
 */
public class Msg {
    private byte[] data;

    public Msg(byte[] data) {
        this.data = data;
    }

    public byte[] getData() {
        return data;
    }
}
