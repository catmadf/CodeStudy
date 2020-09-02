package cn.madf.basicKnowledge.nettyDemo.UdpBrocastDemo;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

/**
 * @author 烛影鸾书
 * @date 2020/9/2
 * @copyright© 2020
 */
public class SearchedServer {

    private final EventLoopGroup broadcastResponseGroup;
    private final Bootstrap bootstrap;

    public SearchedServer() {
        this.broadcastResponseGroup = new NioEventLoopGroup();
        this.bootstrap = new Bootstrap();
        this.bootstrap.group(broadcastResponseGroup).channel(NioDatagramChannel.class)
                .option(ChannelOption.SO_BROADCAST, true).option(ChannelOption.SO_REUSEADDR, true)
                .handler(new ChannelInitializer<NioDatagramChannel>() {
                    @Override
                    protected void initChannel(NioDatagramChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new NoticeDecoder());
                        pipeline.addLast(new NoticeEncoder());
                    }
                });
    }

    public void run() {
        Channel channel = this.bootstrap.bind().syncUninterruptibly().channel();
        System.out.println("服务端启动...");
        try {
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
