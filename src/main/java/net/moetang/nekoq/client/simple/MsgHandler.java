package net.moetang.nekoq.client.simple;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created on 15/1/10.
 */
class MsgHandler extends SimpleChannelInboundHandler<Msg> {
    private Subscribe subscribe;

    public MsgHandler(Subscribe subscribe) {
        this.subscribe = subscribe;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Msg msg) throws Exception {
        this.subscribe.onMessage(msg);
    }
}
