package cn.madf.basicKnowledge;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author 烛影鸾书
 * @date 2020/5/4
 * @copyright© 2020
 */
public class AtomicInteger {

    public static void main(String[] args) {
        java.util.concurrent.atomic.AtomicInteger atomicIntegerA = new java.util.concurrent.atomic.AtomicInteger();
        AtomicStampedReference<java.util.concurrent.atomic.AtomicInteger> atomicIntegerAtomicStampedReference = new AtomicStampedReference<java.util.concurrent.atomic.AtomicInteger>(atomicIntegerA, 0);
        atomicIntegerAtomicStampedReference.getReference().incrementAndGet();
    }
}
