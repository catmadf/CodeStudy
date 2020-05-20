package cn.madf.basicKnowledge;

import java.io.*;

/**
 * @author 烛影鸾书
 * @date 2020/5/12
 * @copyright© 2020
 */
public class WriteBin1 {
    public static void main(String[] args) throws IOException {
        byte[] data = new byte[23 * 4];
        byte[] buffer = new byte[83 * 4];

        File file = new File("E:\\周界入侵监测\\所有数据\\20200426\\D1_C1_P83_20200426105100.bin");
        String[] s = file.getName().split("-");
        String filename = "E:\\周界入侵监测\\所有数据\\20200426" + "\\" + "21-44-" + s[s.length - 1];
        DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(filename, true));
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        while ((bufferedInputStream.read(buffer)) != -1) {
            System.arraycopy(buffer, 20*4, data, 0, data.length);
            outputStream.write(data);
        }
        outputStream.close();
        bufferedInputStream.close();
    }

}
