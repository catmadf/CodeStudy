package cn.madf.MeanShift;

import cn.madf.MeanShift.UserException.SizeNotMatchedException;
import org.ejml.data.DenseMatrix64F;
import org.ejml.ops.CommonOps;

import java.util.LinkedList;
import java.util.List;

/**
 * @author 烛影鸾书
 * @date 2020/3/31
 * @copyright© 2020
 */
public class MeanShift2 {

    private double kernelBandwidth;
    private List<DenseMatrix64F> centers;

    public MeanShift2(double kernelBandwidth) {
        this.kernelBandwidth = kernelBandwidth;
    }

    /**
     *
     * @param points 数据点数组，必须保证DenseMatrix64F[]内每个点的维度一致
     */
    public void fit(DenseMatrix64F[] points) {
        int m = points.length;
        int n = points[0].getNumCols();
        /* 初始化一个长度为m的数组用来记录点是否被访问 */
        int[] isUsed = new int[m];
        for (int i = 0; i < m; i++) {
            isUsed[i] = 0;
        }
        /* 新建一个链表, 用来存储类中心 */
        centers = new LinkedList<DenseMatrix64F>();

        /* 迭代初始点索引 */
        int index = 0;
        while (index != -1) {
            /* 取出一个未标记的点 */
            index = selUnlabelPoint(isUsed, index);
            if (index == -1){
                break;
            }

            DenseMatrix64F point = new DenseMatrix64F(1, n);
            for (int j = 0; j < n; j++) {
                point.set(0, j, points[index].get(0, j));
            }

            DenseMatrix64F center = meanShift(point, points, isUsed);

            center = mergeCenter(center, centers);
            if (!centers.contains(center)) {
                centers.add(center);
            }

        }

    }

    /**
     * 取出一个未被访问的点
     *
     * @param flags 标志数组
     * @param start 遍历起始位置
     * @return 点索引
     */
    private int selUnlabelPoint(int[] flags, int start) {
        for (int i = start; i < flags.length; i++) {
            if (flags[i] == 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 查询已存在的类中心中是否存在与新类中心距离小于阈值的类中心，如有则归并为一类
     * 新的类中心为两个的中点
     *
     * @param center  DenseMatrix64F
     * @param centers DenseMatrix64F
     * @return 新类中心
     */
    private DenseMatrix64F mergeCenter(DenseMatrix64F center, List<DenseMatrix64F> centers) {
        for (DenseMatrix64F item : centers) {
            if (eucliDistPoint(item, center) <= this.kernelBandwidth) {
                centers.remove(item);

                DenseMatrix64F newCenter = new DenseMatrix64F(1, center.getNumCols());
                for (int i = 0; i < center.getNumCols(); i++){
                    newCenter.set(0, i, (center.get(0, i)+item.get(0, i))/2);
                }

                centers.add(newCenter);
                return newCenter;
            }
        }
        return center;
    }

    /**
     * 聚出一个类的操作
     *
     * @param point  初始类中心
     * @param points 所有样本点
     * @param flags  用来记录点是否被访问的标志数组
     * @return 收敛后的类中心
     */
    private DenseMatrix64F meanShift(DenseMatrix64F point, DenseMatrix64F[] points, int[] flags) {
        double shiftDiff = Double.MAX_VALUE;
        while (shiftDiff >= 0.1) {
            DenseMatrix64F oldPoint = point;
            point = shiftedPoint(point, points, this.kernelBandwidth, flags);

            shiftDiff = eucliDistPoint(point, oldPoint);
        }
        return point;
    }

    /**
     * 计算均值漂移
     *
     * @param point           选定点
     * @param points          所有样本点
     * @param kernelBandwidth 核宽
     * @return 漂移后的点
     */
    private DenseMatrix64F shiftedPoint(DenseMatrix64F point, DenseMatrix64F[] points, double kernelBandwidth, int[] flag) {
        try {
            if (point.getNumRows() != 1) {
                throw new SizeNotMatchedException("参数point不是一个点");
            }
            if (point.getNumCols() != points[0].getNumCols()) {
                throw new SizeNotMatchedException("参数point和points列数不一致");
            }
        } catch (SizeNotMatchedException snme) {
            snme.printStackTrace();
        }

        int m = points.length;
        int n = points[0].getNumCols();
        /* 计算欧式距离 */
        DenseMatrix64F eucliDistance = eucliDistMatrix(point, points);

        /* 筛选出距离bandwidth内的点用来漂移 */
        List<Integer> classPointIndex = new LinkedList<Integer>();
        for (int i = 0; i < m; i++) {
            if (eucliDistance.get(i, 0) <= kernelBandwidth) {
                classPointIndex.add(i);
                flag[i] = 1;
            }
        }
        DenseMatrix64F classEucliDist = new DenseMatrix64F(classPointIndex.size(), 1);
        DenseMatrix64F classPoints = new DenseMatrix64F(classPointIndex.size(), n);
        int k = 0;
        for (Integer i : classPointIndex) {
            for (int j = 0; j < n; j++) {
                classPoints.set(k, j, points[i].get(0, j));
            }
            classEucliDist.set(k, 0, eucliDistance.get(i, 0));
            k++;
        }

        /* 计算高斯核.权重 */
        DenseMatrix64F weights = gaussianKernel(classEucliDist, kernelBandwidth);

        double sum = 0.0;
        for (int i = 0; i < weights.getNumRows(); i++) {
            sum += weights.get(i, 0);
        }
        /* 对所有点进行加权得到漂移: 将weights的转置乘上points除以sum */
        DenseMatrix64F weightsT = new DenseMatrix64F(1, weights.getNumRows());
        CommonOps.transpose(weights, weightsT);
        DenseMatrix64F meanShifted = new DenseMatrix64F(weightsT.getNumRows(), points[0].getNumCols());
        CommonOps.mult(weightsT, classPoints, meanShifted);
        CommonOps.scale(1f / sum, meanShifted);

        return meanShifted;
    }

    /**
     * 根据给出的距离和带宽求得高斯核
     *
     * @param distance  选定点与所有点的欧式距离矩阵
     * @param bandwidth 核带宽
     * @return 高斯核，即权重
     */
    private DenseMatrix64F gaussianKernel(DenseMatrix64F distance, double bandwidth) {
        int m = distance.getNumRows();
        DenseMatrix64F res = new DenseMatrix64F(m, 1);
        for (int i = 0; i < m; i++) {
            double value = ((-0.5 * distance.get(i, 0) * distance.get(i, 0)) / (bandwidth * bandwidth));
            value = Math.exp(value);
            res.set(i, 0, value);
        }
        double coeff = 1 / (bandwidth * Math.sqrt(2 * Math.PI));

        CommonOps.scale(coeff, res);

        return res;
    }

    /**
     * 计算一点个与其余所有点的欧氏距离矩阵
     *
     * @param point  点
     * @param points 矩阵
     * @return 欧式距离矩阵
     */
    private DenseMatrix64F eucliDistMatrix(DenseMatrix64F point, DenseMatrix64F[] points) {
        int m = points.length;

        /* 计算欧式距离 */
        DenseMatrix64F eucliDistance = new DenseMatrix64F(m, 1);
        for (int i = 0; i < m; i++) {
            DenseMatrix64F pointTmp = new DenseMatrix64F(1, point.getNumCols());
            for (int j = 0; j < point.getNumCols(); j++) {
                pointTmp.set(0, j, points[i].get(0, j));
            }
            double value = eucliDistPoint(point, pointTmp);
            eucliDistance.set(i, 0, value);
        }
        return eucliDistance;
    }

    /**
     * 计算两个点的欧氏距离
     *
     * @param pointA 一个点
     * @param pointB 另一个点
     * @return 距离
     */
    private double eucliDistPoint(DenseMatrix64F pointA, DenseMatrix64F pointB) {
        checkPointSize(pointA, pointB);
        DenseMatrix64F distVector = new DenseMatrix64F(1, pointA.getNumCols());
        CommonOps.sub(pointA, pointB, distVector);
        double res = 0;
        for (int i = 0; i < distVector.getNumCols(); i++) {
            res += Math.pow(distVector.get(0, i), 2);
        }
        return Math.sqrt(res);
    }

    private void checkPointSize(DenseMatrix64F a, DenseMatrix64F b) {
        try {
            if (a.getNumRows() != 1 && b.getNumRows() != 1) {
                throw new SizeNotMatchedException("计算欧式距离的点(DenseMatrix64F类型)行数不为1.");
            }
            if (a.getNumCols() != b.getNumCols()) {
                throw new SizeNotMatchedException("计算欧式距离的两个点维数不一致.");
            }
        } catch (SizeNotMatchedException snme) {
            snme.printStackTrace();
        }
    }

    public double getKernelBandwidth() {
        return kernelBandwidth;
    }

    public void setKernelBandwidth(double kernelBandwidth) {
        this.kernelBandwidth = kernelBandwidth;
    }

    public List<DenseMatrix64F> getCenters() {
        return centers;
    }
}
