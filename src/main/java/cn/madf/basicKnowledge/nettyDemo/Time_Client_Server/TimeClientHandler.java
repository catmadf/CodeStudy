package cn.madf.basicKnowledge.nettyDemo.Time_Client_Server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOutboundHandlerAdapter;

import java.util.Date;

/**
 * @author 烛影鸾书
 * @date 2020/9/1 16:32
 * @copyright© 2020
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("client channelRead");
        ByteBuf m = (ByteBuf) msg;
        try {
            long currentTimeMillis = (m.readUnsignedInt() - 2208988800L) * 1000L;
            System.out.println(new Date(currentTimeMillis) + " 收到 " + System.currentTimeMillis());
//            ctx.close();
        } finally {
            m.release();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
