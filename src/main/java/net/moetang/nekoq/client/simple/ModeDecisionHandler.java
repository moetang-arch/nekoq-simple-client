package net.moetang.nekoq.client.simple;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * Created on 15/1/10.
 */
class ModeDecisionHandler extends ChannelInboundHandlerAdapter {
    private Client.Mode mode;

    public ModeDecisionHandler(Client.Mode mode) {
        this.mode = mode;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        switch (mode) {
            case PUB:
                ctx.channel().writeAndFlush(Unpooled.wrappedBuffer(new byte[]{1}));
                break;
            case SUB:
                ctx.channel().writeAndFlush(Unpooled.wrappedBuffer(new byte[]{2}));
                break;
        }
    }
}
