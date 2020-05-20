package cn.madf.YmlConfig;

import lombok.Data;

import java.util.HashMap;

/**
 * @author 烛影鸾书
 * @date 2020/4/23
 * @copyright© 2020
 */
@Data
public class YmlConfigModel {
    private float[] varThreshold;
    private int eventTimeRange;
    private int eventPosRange;
    private int strideFilterThreshold;
    private int strideKernelWidth;
    private HashMap<Integer, Integer> lightSensorMap;
}
