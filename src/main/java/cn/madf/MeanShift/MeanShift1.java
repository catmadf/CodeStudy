package cn.madf.MeanShift;

import org.ejml.data.DenseMatrix64F;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


/**
 * @author 烛影鸾书
 * @date 2020/3/29
 * @copyright© 2020
 */
public class MeanShift1 {

    /* 计算欧式距离 */
    private static double countDistance(double[] p1, double[] p2) {

        double sum = 0;

        for (int i = 0; i < p1.length; i++)
            sum += Math.pow(p1[i] - p2[i], 2);

        return Math.sqrt(sum);
    }

    /* 随机抽取向量 */
    private static double[] randomVector(DenseMatrix64F src) {

        double[] rs = new double[src.numCols];

        Random r = new Random();

        int inx = r.nextInt(src.numRows);

        for (int i = 0; i < src.numCols; i++) {
            rs[i] = src.get(inx, i);
        }
        return rs;
    }

    private static DenseMatrix64F findDatasInCircle(DenseMatrix64F src, double[] p, double r) {

        DenseMatrix64F rs = new DenseMatrix64F(0, src.numCols);

        for (int i = 0; i < src.numRows; i++) {

            double[] tmp = new double[src.numCols];

            for (int j = 0; j < src.numCols; j++)
                tmp[j] = src.get(i, j);

            if (countDistance(p, tmp) <= r) {
                rs.reshape(rs.numRows + 1, src.numCols, true);
                for (int j = 0; j < src.numCols; j++)
                    rs.set(rs.numRows - 1, j, src.get(i, j));
            }
        }

        return rs;
    }

    private static double[] countVectorSuperposition(DenseMatrix64F src) {

        double[] rs = new double[src.numCols];

        for (int i = 0; i < src.numCols; i++) {

            double tmp = 0;

            for (int j = 0; j < src.numRows; j++)
                tmp += src.get(j, i);

            rs[i] = tmp;

        }

        return rs;
    }

    public static double[] meanShift(DenseMatrix64F dataSet, double r) {


        //随机选取向量
        double[] rs = randomVector(dataSet);

        double diff = Double.MAX_VALUE;

        double change = Double.MAX_VALUE;


        //开始迭代
        while (diff > 0 && change != 0) {

            //筛选半径内所有向量
            DenseMatrix64F subVectors = findDatasInCircle(dataSet, rs, r);

            //计算向量叠加
            double[] sumVectors = countVectorSuperposition(subVectors);


            //计算目标向量
            double vCount = countDistance(rs, sumVectors);

            change = vCount - diff;

            diff = vCount;

            System.out.println("diff :" + diff);

            //更新圆心向量
            rs = sumVectors;
        }
        return rs;
    }

    public static void main(String[] args) throws IOException {
        byte[] buffer = new byte[72 * 5 * 4];

        int sensNo = 29;
        List<Float> dataList = new LinkedList<Float>();

        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(new File("E:\\周界入侵监测\\所有数据\\D1_C1_P72_20200117105900.bin")));
        while ((bufferedInputStream.read(buffer)) != -1) {
            float[] tempData = byteArray2FloatArray(buffer);
            for (int i = 0; i < 5; i++) {
                dataList.add(tempData[sensNo + 72 * i]);
            }
        }

        float[] data = new float[dataList.size()];
        int i = 0;
        for (float f : dataList) {
            data[i++] = f;
        }
        /* 检验读取的数据是否一致 */
        System.out.println(Arrays.toString(data));
        for (int j = 61800; j < 113860; j++) {

        }

    }

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
}
