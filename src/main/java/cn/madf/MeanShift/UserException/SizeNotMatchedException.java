package cn.madf.MeanShift.UserException;

/**
 * @author 烛影鸾书
 * @date 2020/3/29
 * @copyright© 2020
 */
public class SizeNotMatchedException extends RuntimeException {

    public SizeNotMatchedException() {
        super("大小不匹配!");
    }

    public SizeNotMatchedException(String str){
        super(str);
    }
}
