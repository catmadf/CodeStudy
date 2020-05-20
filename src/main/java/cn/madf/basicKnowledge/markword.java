package cn.madf.basicKnowledge;

import org.openjdk.jol.info.ClassLayout;

/**
 * @author 烛影鸾书
 * @date 2020/5/4
 * @copyright© 2020
 */
public class markword {

    public static void main(String[] args) {
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
//        java.lang.Object object internals:
//        OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
//        0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
//        4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
//        8     4        (object header)                           e5 01 00 20 (11100101 00000001 00000000 00100000) (536871397)
//        12     4        (loss due to the next object alignment)
//        Instance size: 16 bytes
//        Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

        synchronized (o){
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }

//        java.lang.Object object internals:
//        OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
//        0     4        (object header)                           08 f2 d8 00 (00001000 11110010 11011000 00000000) (14217736)
//        4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
//        8     4        (object header)                           e5 01 00 20 (11100101 00000001 00000000 00100000) (536871397)
//        12     4        (loss due to the next object alignment)
//        Instance size: 16 bytes
//        Space losses: 0 bytes internal + 4 bytes external = 4 bytes total

        /* 锁定一个对象实际上是对 markword 进行改写，三个 object header ，前两个合称为 markword ，第三个成为 class pointer */
    }
}
