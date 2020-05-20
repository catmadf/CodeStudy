import org.ejml.data.DenseMatrix64F;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 烛影鸾书
 * @date 2020/4/1
 * @copyright© 2020
 */
public class TestPow {
    @Test
    public void test1(){
        int[] a = new int[]{1, 2, 3};
        System.out.println(Arrays.toString(a));
        tasd(a);
        System.out.println(Arrays.toString(a));
    }

    public void tasd(int[] a){
        a[2] = 4;
    }

    @Test
    public void test2(){
        List<DenseMatrix64F> resList = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            DenseMatrix64F p = new DenseMatrix64F(1, 2);
            p.set(0, 0, 1);
            p.set(0, 1, i);
            resList.add(p);
        }
        DenseMatrix64F[] res = resList.toArray(new DenseMatrix64F[0]);
    }
}
