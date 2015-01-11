package net.moetang.nekoq.client.simple;

import io.netty.channel.EventLoopGroup;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * Created on 15/1/10.
 */
public class PubClient extends Client {
    public PubClient(InetSocketAddress addr, EventLoopGroup group, long reconnectDelay, TimeUnit delayUnit) {
        super(addr, group, Mode.PUB, null, reconnectDelay, delayUnit);
    }

    public void pub(byte[] data) {
        this.getChannel().writeAndFlush(data);
    }
}
