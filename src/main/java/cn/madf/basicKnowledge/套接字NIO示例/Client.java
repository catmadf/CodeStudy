package cn.madf.basicKnowledge.套接字NIO示例;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;

/**
 * @author 烛影鸾书
 * @date 2020/5/11
 * @copyright© 2020
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 5250);
        OutputStream out = socket.getOutputStream();
        String s = "hello World" + new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(System.currentTimeMillis());
        out.write(s.getBytes());
        out.close();
    }
}
