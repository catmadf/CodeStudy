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
    private static final int port = 25052;

    private final EventLoopGroup group;
    private final Bootstrap bootstrap;

    public static void main(String[] args) {

    }

    public SearchBroadcastClient() {
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

    public void run(int remotePort) throws InterruptedException {
        Channel channel = this.bootstrap.bind(port).sync().channel();   // bind 0 代表每次发送会随机选取一个端口
        while (!register) {
            NoticeHelpPacket notice = new NoticeHelpPacket(remotePort);
            notice.setFromPort(port);
            channel.writeAndFlush(notice);
            Thread.sleep(3000);
        }
    }

    public static void setRegister(boolean flag) {
        register = flag;
    }

}
