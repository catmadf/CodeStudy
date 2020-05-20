import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author 烛影鸾书
 * @date 2020/4/17
 * @copyright© 2020
 */
public class TestLambda {
    @Test
    public void test1(){
        List<Integer> a = Arrays.asList(1, 2, 3);
        a.forEach(System.out::println);
    }
}