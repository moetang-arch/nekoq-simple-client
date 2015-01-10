package net.moetang.nekoq.client.simple;

import io.netty.channel.EventLoopGroup;

import java.net.InetSocketAddress;
import java.util.Objects;

/**
 * Created on 15/1/10.
 */
public class SubClient extends Client {
    private Subscribe subscribe;

    public SubClient(InetSocketAddress addr, EventLoopGroup group, Subscribe sub) {
        super(addr, group, Mode.SUB, Objects.requireNonNull(sub, "subscribe is null"));
    }
}
