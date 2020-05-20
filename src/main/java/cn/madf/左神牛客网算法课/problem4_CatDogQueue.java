package cn.madf.左神牛客网算法课;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 猫狗队列问题
 * 给定pet dog cat 三个类
 * 设计一个队列用来存pet，要求除基本功能外实现以下方法：
 * pollDog()返回dog中最先进队列的dog对象
 * pollCat()返回cat中最先进队列的cat对象
 * pollAll()返回所有对象中最先进队列的对象
 * <p>
 * 提示：给定的三个类是用户提供的基础类，不可更改，可以再封装一下，将pet对象和一个事件戳绑定在一起就能区分所有对象谁先进谁后进
 *
 * @author 烛影鸾书
 * @date 2020/5/14
 * @copyright© 2020
 */
public class problem4_CatDogQueue {
    public static class Pet {
        private String type;

        public Pet(String type) {
            this.type = type;
        }

        public String getType() {
            return this.type;
        }
    }

    public static class Dog extends Pet {
        public Dog() {
            super("dog");
        }
    }

    public static class Cat extends Pet {
        public Cat() {
            super("cat");
        }
    }

    public static class PetPack {
        private Pet pet;
        private long count;

        public PetPack(Pet pet, long count) {
            this.pet = pet;
            this.count = count;
        }

        public Pet getPet() {
            return this.pet;
        }

        public long getCount() {
            return count;
        }

        public String getPetType() {
            return this.pet.getType();
        }
    }

    public static class CatDogQueue {
        private Queue<PetPack> catQueue;
        private Queue<PetPack> dogQueue;
        private long count;

        public CatDogQueue() {
            catQueue = new LinkedList<>();
            dogQueue = new LinkedList<>();
            count = 0;
        }

        public void add(Pet pet) {
            if (pet.getType().equals("dog")) {
                dogQueue.add(new PetPack(pet, count++));
            } else if (pet.getType().equals("cat")) {
                catQueue.add(new PetPack(pet, count++));
            } else {
                throw new RuntimeException("pet is not dog or cat");
            }
        }

        public Pet pollAll() {
            if (!dogQueue.isEmpty() && !catQueue.isEmpty()) {
                if (dogQueue.peek().getCount() < catQueue.peek().getCount()){
                    return dogQueue.poll().getPet();
                } else {
                    return catQueue.poll().getPet();
                }
            } else if (!dogQueue.isEmpty()) {
                return dogQueue.poll().getPet();
            } else if (!catQueue.isEmpty()) {
                return catQueue.poll().getPet();
            } else {
                throw new RuntimeException("error, queue is empty");
            }
        }

        public Dog pollDog() {
            if (!dogQueue.isEmpty()) {
                return (Dog) dogQueue.poll().getPet();
            } else {
                throw new RuntimeException("error, dog queue is empty");
            }
        }

        public Cat pollCat() {
            if (!catQueue.isEmpty()) {
                return (Cat) catQueue.poll().getPet();
            } else {
                throw new RuntimeException("error, cat queue is empty");
            }
        }
    }
}
