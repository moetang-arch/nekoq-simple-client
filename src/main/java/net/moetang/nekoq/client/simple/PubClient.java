package net.moetang.nekoq.client.simple;

import io.netty.channel.EventLoopGroup;

import java.net.InetSocketAddress;

/**
 * Created on 15/1/10.
 */
public class PubClient extends Client {
    public PubClient(InetSocketAddress addr, EventLoopGroup group) {
        super(addr, group, Mode.PUB, null);
    }

    public void pub(byte[] data) {
        this.getChannel().writeAndFlush(data);
    }
}
