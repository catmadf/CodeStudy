/**
 * @author 烛影鸾书
 * @date 2020/4/17
 * @copyright© 2020
 */
public class TestDoubleColon {

    private String name;

    private TestDoubleColon(String name){
        this.name = name;
    }

    public static void main(String[] args) {
        InterfaceExample com =  TestDoubleColon::new;
        TestDoubleColon bean = com.create("hello world");
        System.out.println(bean.name);
    }
}
interface InterfaceExample {
    TestDoubleColon create(String name);
}
