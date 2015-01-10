package net.moetang.nekoq.client.simple;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.ByteOrder;

/**
 * Created on 15/1/10.
 */
class MsgEncoder extends MessageToByteEncoder<byte[]> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, byte[] bytes, ByteBuf byteBuf) throws Exception {
        byteBuf.order(ByteOrder.BIG_ENDIAN);
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
    }
}
