package cn.madf.basicKnowledge.设计模式.单例模式;

/**
 * @author 烛影鸾书
 * @date 2020/5/12
 * @copyright© 2020
 */
public class StaticInnerClassSingleton {
    /**
     * 当 StaticInnerClassSingleton 类被加载时，静态内部类 SingletonHolder 没有被加载进内存。
     * 只有当调用 getInstance() 方法时触发 SingletonHolder.INSTANCE 才会使得 SingletonHolder被加载，
     * 此时初始化 INSTANCE 实例，并且jvm能保证 INSTANCE 只被实例化一次。
     * 该方式具有延迟初始化的好处，并且由jvm提供对线程安全的支持。
     */

    private StaticInnerClassSingleton() {
    }

    private static class SingletonHolder {
        private static final StaticInnerClassSingleton INSTANCE = new StaticInnerClassSingleton();
    }

    public static StaticInnerClassSingleton getInstance(){
        return SingletonHolder.INSTANCE;
    }
}
