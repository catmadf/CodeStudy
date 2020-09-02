package cn.madf.basicKnowledge.nettyDemo.UdpBrocastDemo;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

/**
 *
 * 携带有自身的ip
 *
 * @author 烛影鸾书
 * @date 2020/9/1
 * @copyright© 2020
 */
public class NoticeHelpPacket {

    public static final byte SEPARATOR = (byte) '|';
    public static final String BROADCAST_IP = "255.255.255.255";

    private String ip;
    private long time;
    private int fromPort;
    private transient InetSocketAddress target;
    /* 想发啥发啥 */

    public NoticeHelpPacket(int remotePort) {
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
            target = new InetSocketAddress(BROADCAST_IP, remotePort);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        time = System.currentTimeMillis();
    }

    public NoticeHelpPacket(long time, String ip) {
        this.ip = ip;
        this.time = time;
    }

    public NoticeHelpPacket(long time, int fromPort, String ip) {
        this.ip = ip;
        this.time = time;
        this.fromPort = fromPort;
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

    public int getFromPort() {
        return fromPort;
    }

    public void setFromPort(int fromPort) {
        this.fromPort = fromPort;
    }
}
