package cn.madf.basicKnowledge.nettyDemo.UdpBrocastDemo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.resolver.dns.NoopDnsQueryLifecycleObserverFactory;

/**
 * @author 烛影鸾书
 * @date 2020/9/1 21:58
 * @copyright© 2020
 */
public class SearchBroadcastClient {

    private static boolean register = false;

    private final EventLoopGroup group;
    private final Bootstrap bootstrap;

    public SearchBroadcastClient(int remotePort) {
        this.group = new NioEventLoopGroup();
        this.bootstrap = new Bootstrap();
        /* 绑定NioDatagramChannel数据报通道，udp */
        this.bootstrap.group(group).channel(NioDatagramChannel.class)
                /* 设置通道用于广播 */
                .option(ChannelOption.SO_BROADCAST, true)
                .handler(new ChannelInitializer<NioDatagramChannel>() {
                    @Override
                    protected void initChannel(NioDatagramChannel ch) throws Exception {
                        ch.pipeline().addLast(new NoticeEncoder());
                        ch.pipeline().addLast(new RegisterServerIpHandler());
                    }
                });
    }

    public void run() throws InterruptedException {
        Channel channel = this.bootstrap.bind(0).sync().channel();
        while (!register) {
            channel.writeAndFlush(new NoticeHelpPacket());
            Thread.sleep(3000);
        }
    }

    public static void setRegister(boolean flag) {
        register = flag;
    }

}
