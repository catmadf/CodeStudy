package cn.madf.basicKnowledge.nettyDemo.UdpBrocastDemo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

/**
 * @author 烛影鸾书
 * @date 2020/9/2
 * @copyright© 2020
 */
public class RegisterServerIpHandler extends SimpleChannelInboundHandler<NoticeHelpPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, NoticeHelpPacket msg) throws Exception {
        System.out.println("服务器端ip: " + msg.getIp() + " | " + msg.getTime());
        SearchBroadcastClient.setRegister(true);
    }
}
