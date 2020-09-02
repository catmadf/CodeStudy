package cn.madf.basicKnowledge.nettyDemo.UdpBrocastDemo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author 烛影鸾书
 * @date 2020/9/2 21:44
 * @copyright© 2020
 */
public class SeaverHandler extends SimpleChannelInboundHandler<NoticeHelpPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, NoticeHelpPacket msg) throws Exception {

    }
}
