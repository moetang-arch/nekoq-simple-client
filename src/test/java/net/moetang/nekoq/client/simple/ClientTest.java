package net.moetang.nekoq.client.simple;

import io.netty.channel.nio.NioEventLoopGroup;

import java.net.InetSocketAddress;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created on 15/1/10.
 */
public class ClientTest {
    public static void main(String[] args) throws InterruptedException {
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 16782);

        PubClient pub = new PubClient(address, new NioEventLoopGroup(), 1, TimeUnit.SECONDS);
        SubClient sub = new SubClient(address, new NioEventLoopGroup(), msg -> System.out.println(new String(msg.getData())), 1, TimeUnit.SECONDS);

        pub.pub("hello world!".getBytes());
        pub.pub(new Date(System.currentTimeMillis()).toString().getBytes());

        TimeUnit.SECONDS.sleep(10);
    }
}
