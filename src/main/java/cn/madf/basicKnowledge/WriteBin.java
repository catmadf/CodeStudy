package cn.madf.basicKnowledge;

import java.io.*;
import java.nio.file.Paths;

/**
 * @author 烛影鸾书
 * @date 2020/5/5
 * @copyright© 2020
 */
public class WriteBin {
    public static void main(String[] args) throws IOException {
        final int ixStart = 7 - 1;
        byte[] buffer = new byte[24 * 4];
        byte[] data = new byte[80 * 4];

        String filepath = "E:\\周界入侵监测\\所有数据\\0525\\新建文件夹";

        File root = new File(filepath);
        File[] files = root.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    continue;
                }
                String[] s = file.getName().split("-");

                String filename = "E:\\周界入侵监测\\所有数据\\0525\\新建文件夹" + "\\" + "1-80-" + s[s.length - 1];

                DataOutputStream outputStream = new DataOutputStream(new FileOutputStream(filename, true));
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                while ((bufferedInputStream.read(buffer)) != -1) {
                    System.arraycopy(buffer, 0, data, ixStart * 4, buffer.length);
                    outputStream.write(data);
                }
            }

        }
    }
}
