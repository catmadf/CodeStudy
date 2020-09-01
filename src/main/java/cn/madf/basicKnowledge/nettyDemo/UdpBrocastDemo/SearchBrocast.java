package cn.madf.basicKnowledge.nettyDemo.UdpBrocastDemo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

/**
 * @author 烛影鸾书
 * @date 2020/9/1 21:58
 * @copyright© 2020
 */
public class SearchBrocast {

    private final EventLoopGroup group;
    private final Bootstrap bootstrap;

    public SearchBrocast(int remotePort) {
        this.group = new NioEventLoopGroup();
        this.bootstrap = new Bootstrap();
        /* 绑定NioDatagramChannel数据报通道，udp */
        this.bootstrap.group(group).channel(NioDatagramChannel.class)
                /* 设置通道用于广播 */
                .option(ChannelOption.SO_BROADCAST, true)
                .handler();
    }

}
