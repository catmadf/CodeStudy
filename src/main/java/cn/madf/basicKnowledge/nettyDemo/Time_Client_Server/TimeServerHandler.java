package cn.madf.basicKnowledge.nettyDemo.Time_Client_Server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;

/**
 * @author 烛影鸾书
 * @date 2020/9/1 16:13
 * @copyright© 2020
 */
public class TimeServerHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        final ByteBuf time = ctx.alloc().buffer(4);
        time.writeInt((int) (System.currentTimeMillis() / 1000L + 2208988800L));

        final ChannelFuture f = ctx.writeAndFlush(time);
        f.addListener((ChannelFutureListener) future -> {
            assert f == future;
            /* 操作完成时关闭通道 */
            ctx.close();
        });
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    /**
     * 客户端连接上来时的操作
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress());
    }
}
