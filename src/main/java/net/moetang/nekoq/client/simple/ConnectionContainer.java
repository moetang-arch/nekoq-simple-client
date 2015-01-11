package net.moetang.nekoq.client.simple;

import io.netty.channel.Channel;

/**
 * Created by sunsun on 15/1/11.
 */
public interface ConnectionContainer {
    public void setChannel(Channel channel);
    public void reconnect();
}
