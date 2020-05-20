package cn.madf.MeanShift;

import java.util.Arrays;

/**
 * @author 烛影鸾书
 * @date 2020/3/29
 * @copyright© 2020
 */
public class DataMatrix {






    public static void main(String[] args) {
        Point a = new Point(new float[]{1.0f, 2.0f});
        System.out.println(Arrays.toString(a.getData()) + " " + a.getLength());
    }
}
