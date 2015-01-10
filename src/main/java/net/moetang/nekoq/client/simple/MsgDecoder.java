package net.moetang.nekoq.client.simple;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.nio.ByteOrder;
import java.util.List;

/**
 * Created on 15/1/10.
 */
class MsgDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        byteBuf.order(ByteOrder.BIG_ENDIAN);

        int size = byteBuf.readInt();
        if (size <= 0) {
            throw new RuntimeException("size is invalid. size=[" + size + "]");
        }

        byte[] data = new byte[size];
        byteBuf.readBytes(data);

        list.add(new Msg(data));
    }
}
