package cn.madf.basicKnowledge.nettyDemo.UdpBrocastDemo;

import javax.sound.sampled.Port;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/**
 * @author 烛影鸾书
 * @date 2020/9/1
 * @copyright© 2020
 */
public class NoticeHelpPacket {

    public static final byte SEPARATOR = (byte) '|';
    public static final String BROADCAST_IP = "255.255.255.255";
    public static final int PORT = 35205;

    private String ip;
    private long time;
    private transient InetSocketAddress target;
    /* 想发啥发啥 */

    public NoticeHelpPacket() {
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
            target = new InetSocketAddress(BROADCAST_IP, PORT);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        time = System.currentTimeMillis();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public InetSocketAddress getTarget() {
        return target;
    }

    public void setTarget(InetSocketAddress target) {
        this.target = target;
    }
}
