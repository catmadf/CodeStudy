package cn.madf.basicKnowledge.nettyDemo.UdpBrocastDemo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.util.CharsetUtil;
import java.util.List;

/**
 * 将 NoticeHelpPacket encode 成 DatagramPacket
 * @author 烛影鸾书
 * @date 2020/9/1
 * @copyright© 2020
 */
public class NoticeEncoder extends MessageToMessageEncoder<NoticeHelpPacket> {

    @Override
    protected void encode(ChannelHandlerContext ctx, NoticeHelpPacket noticeHelpPacket, List<Object> list) throws Exception {
        /* ip + time + port:  */
        byte[] ip = noticeHelpPacket.getIp().getBytes(CharsetUtil.UTF_8);
        int capacity = 8 + 4 + 1 + ip.length;
        ByteBuf buf = ctx.alloc().buffer(capacity);
        buf.writeLong(noticeHelpPacket.getTime());
        buf.writeInt(noticeHelpPacket.getFromPort());
        buf.writeByte(NoticeHelpPacket.SEPARATOR);
        buf.writeBytes(ip);

        /* 加入消息列表 */
        list.add(new DatagramPacket(buf, noticeHelpPacket.getTarget()));
    }
}
