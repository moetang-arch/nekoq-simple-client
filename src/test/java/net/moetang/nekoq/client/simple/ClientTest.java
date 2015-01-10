package net.moetang.nekoq.client.simple;

import io.netty.channel.nio.NioEventLoopGroup;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

/**
 * Created on 15/1/10.
 */
public class ClientTest {
    public static void main(String[] args) throws InterruptedException {
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 16782);

        PubClient pub = new PubClient(address, new NioEventLoopGroup());
        SubClient sub = new SubClient(address, new NioEventLoopGroup(), new Subscribe() {
            @Override
            public void onMessage(Msg msg) {
                System.out.println(new String(msg.getData()));
            }
        });

        pub.pub("hello world!".getBytes());

        TimeUnit.SECONDS.sleep(10);
    }
}
