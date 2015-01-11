package net.moetang.nekoq.client.simple;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.TimeUnit;

/**
 * Created on 15/1/10.
 */
class MsgHandler extends SimpleChannelInboundHandler<Msg> {
    private ConnectionContainer container;
    private long reconnectDelay;
    private TimeUnit delayUnit;

    private Subscribe subscribe;

    public MsgHandler(Subscribe subscribe, ConnectionContainer container, long reconnectDelay, TimeUnit delayUnit) {
        this.container = container;
        this.subscribe = subscribe;
        this.reconnectDelay = reconnectDelay;
        this.delayUnit = delayUnit;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        System.out.println("connection lost. reconnecting...");
        ctx.channel().eventLoop().parent().schedule(container::reconnect, this.reconnectDelay, this.delayUnit);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Msg msg) throws Exception {
        this.subscribe.onMessage(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.err.println("error occurs. " + cause);
    }
}
