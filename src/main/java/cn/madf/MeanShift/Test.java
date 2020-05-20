package cn.madf.MeanShift;

import com.sun.org.apache.regexp.internal.REUtil;
import org.ejml.data.DenseMatrix64F;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 烛影鸾书
 * @date 2020/4/9
 * @copyright© 2020
 */
public class Test {
    public static float[] byteArray2FloatArray(byte[] bytes) {

        int temp;
        int piece = 4;
        float[] fs = new float[bytes.length / 4];
        for (int i = 0; i < bytes.length; i += piece) {
            temp = 0;
            for (int j = 0; j < piece; j++) {
                temp |= (bytes[j + i] & 0xff) << j * 8;
            }
            fs[i / 4] = Float.intBitsToFloat(temp);
        }
        return fs;
    }

    public static float[] read() {
        File file = new File("E:\\周界入侵监测\\所有数据\\测试\\D1_C1_P72_20200117110100.bin");
        try {
            float[] data = new float[50000];
            byte[] bytesBuffer = new byte[72 * 4];
            BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            int index = 0, ix = 0;
            while (inputStream.read(bytesBuffer) != -1) {
                if (index >= 60000 && index < 110000) {
                    float[] tmp = byteArray2FloatArray(bytesBuffer);
                    data[ix] = tmp[27];
                    ix++;
                }
                index++;
            }
            float[] resData = new float[data.length / 5];
            for (int i = 0; i < resData.length; i++) {
                resData[i] = (data[5 * i] + data[5 * i + 1] + data[5 * i + 2] + data[5 * i + 3] + data[5 * i + 4]) / 5;
            }
            return resData;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        float[] data = read();
        assert data != null;

        int len = 0;
        for (float datum : data) {
            if (Math.abs(datum) >= 1) {
                len++;
            }
        }

        DenseMatrix64F[] points = new DenseMatrix64F[len];
        int j = 0;
        for (int i = 0; i < data.length; i++) {
            DenseMatrix64F p = new DenseMatrix64F(1, 2);
            if (Math.abs(data[i]) >= 1) {
                p.set(0, 0, i);
                p.set(0, 1, data[i]);
                points[j++] = p;
            }
        }

        MeanShift2 meanShift = new MeanShift2(180);
        meanShift.fit(points);
        List<DenseMatrix64F> centers = meanShift.getCenters();
        System.out.println(centers.toString());
        System.out.println(centers.size());


    }
}
