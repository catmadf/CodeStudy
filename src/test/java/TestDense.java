import cn.madf.SmallSum.TestDetector;
import org.ejml.data.DenseMatrix64F;
import org.ejml.ops.CommonOps;
import org.junit.Test;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

/**
 * @author 烛影鸾书
 * @date 2020/4/21
 * @copyright© 2020
 */
public class TestDense {

    public static void main(String[] args) {
        DenseMatrix64F weights = new DenseMatrix64F(3, 1);
        weights.set(0, 0, 1);
        weights.set(1, 0, 2);
        weights.set(2, 0, 3);

        DenseMatrix64F classPoints = new DenseMatrix64F(3, 2);
        classPoints.set(0, 0, 1);
        classPoints.set(0, 1, 1);
        classPoints.set(1, 0, 2);
        classPoints.set(1, 1, 2);
        classPoints.set(2, 0, 3);
        classPoints.set(2, 1, 3);

        DenseMatrix64F weightsT = new DenseMatrix64F(1, weights.getNumRows());
        CommonOps.transpose(weights, weightsT);
        DenseMatrix64F meanShifted = new DenseMatrix64F(1, 2);
        CommonOps.mult(weightsT, classPoints, meanShifted);
        CommonOps.scale(1 / 6f, meanShifted);
    }

    @Test
    public void test() {
        float[][] arr = new float[5][6];
        System.out.println(arr.length);
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = i * 5 + j;
            }
        }
        System.out.println();
    }

    private static boolean analysisFence(float[][] data) {
        int frame = 1000;
        int n = data.length;
        int m = data[0].length / frame;
        /* 分别计算能量和 */
        float[][] energy = new float[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int sum = 0;
                for (int k = 0; k < 1000; k++) {
                    sum += Math.pow(data[i][j * frame + k], 2);
                }
                energy[i][j] = sum;
            }
        }

        float threshold = 200f;
        int[][] flag = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                flag[i][j] = energy[i][j] >= threshold ? 1 : 0;
            }
        }

        /* 对flag数组进行判断，累加如果和超过n*m/2则判定为翻越入侵 */
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sum += flag[i][j];
            }
        }
        return sum >= (n * m / 2);

    }

    @Test
    public void test1() {
        float[][] data = new float[5][4000];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4000; j++) {
                data[i][j] = i + (int) (j / 1000);
            }
        }

        analysisFence(data);
    }

    @Test
    public void test2() {
        /**
         * 根据value对key排序
         */
        int[] data = TestDetector.generateIntArray(30, 100);
        System.out.println(Arrays.toString(data));
        int nbins = 5;
        Map<Integer, Integer> cntMap = new HashMap<>(64);
        /* 找一个最大值 */
        float max = 0f;
        for (int i = 0; i < data.length; i++) {
            max = Math.max(max, Math.abs(data[i]));
        }
        /* 划分直方图统计区间, 暂定划分nbins个区间 */
        for (float datum : data) {
            int i = (int) (datum * nbins / max);
            cntMap.compute(i, (k, v) -> {
                if (v == null) {
                    v = 1;
                } else {
                    v++;
                }
                return v;
            });
        }
        System.out.println(cntMap);

        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(cntMap.entrySet());
        list.sort(Comparator.comparing(Map.Entry::getValue));
        System.out.println(list);
    }

    @Test
    public void test3() {
        DecimalFormat df = new DecimalFormat("0.0%");
        System.out.println(df.format(0.1238)+" 可能是翻越");
    }

}
