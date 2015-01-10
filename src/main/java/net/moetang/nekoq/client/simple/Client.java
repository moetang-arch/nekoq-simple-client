package net.moetang.nekoq.client.simple;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.net.InetSocketAddress;
import java.nio.ByteOrder;

/**
 * Created on 15/1/10.
 */
class Client {
    private Bootstrap bootstrap;
    private EventLoopGroup group;
    private Mode mode;
    private Channel channel;

    static enum Mode {
        PUB,
        SUB
    }

    public Client(InetSocketAddress addr, EventLoopGroup group, Mode mode, Subscribe sub) {
        this.mode = mode;
        this.group = group;
        this.bootstrap = new Bootstrap();
        this.bootstrap.group(group);
        this.bootstrap.channel(NioSocketChannel.class);
        this.bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        this.bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new ModeDecisionHandler(mode));
                ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(ByteOrder.BIG_ENDIAN, Integer.MAX_VALUE, 0, 4, 0, 0, true));
                ch.pipeline().addLast(new MsgDecoder());
                ch.pipeline().addLast(new MsgEncoder());
                if (sub != null) {
                    ch.pipeline().addLast(new MsgHandler(sub));
                }
            }
        });
        try {
            ChannelFuture f = this.bootstrap.connect(addr).sync();
            this.channel = f.channel();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public Channel getChannel() {
        return channel;
    }
}
