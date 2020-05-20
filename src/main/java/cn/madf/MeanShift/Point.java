package cn.madf.MeanShift;

import cn.madf.MeanShift.UserException.SizeNotMatchedException;

import java.util.Arrays;

/**
 * @author 烛影鸾书
 * @date 2020/3/29
 * @copyright© 2020
 */
public class Point {
    private final float[] data;
    private final int length;

    public Point(float[] data) {
        this.data = data;
        this.length = data.length;
    }

    public float[] getData() {
        return data;
    }

    public int getLength() {
        return length;
    }

    public Point add(Point that) {
        checkSize(this, that);

        float[] data = new float[this.length];
        for (int i = 0; i < this.length; i++) {
            data[i] = this.data[i] + that.data[i];
        }

        return new Point(data);
    }

    public float crossProduct(Point that){
        checkSize(this, that);

        float sum = 0;


        return sum;
    }






    private void checkSize(Point a, Point b) {
        try {
            if (a.getLength() != b.getLength()) {
                throw new SizeNotMatchedException();
            }
        } catch (SizeNotMatchedException snme) {
            snme.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int i = 0;
        float[] data = new float[2];
        data[i++] = 2;
        System.out.println(i);
        System.out.println(Arrays.toString(data));
    }
}
