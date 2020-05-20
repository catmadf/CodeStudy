package cn.madf.双向链表;

public class DLList<T> {
    public class IntNode {
        T item;
        IntNode next;
        IntNode prev;

        public IntNode(T i, IntNode n, IntNode p) {
            item = i;
            prev = p;
            next = n;
        }
    }

    private IntNode sentinel = new IntNode(null, null, null);
    private int size = 0;

    public DLList() {
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public DLList(T... list) {
        this();
        for (T x: list) {
            addLast(x);
        }
    }

    public DLList(DLList<T> l) {
        this();
        for (int i = 0; i < l.size; i++) {
            addLast(l.getIndex(i));
        }
    }

    public T getFirst() {
        return sentinel.next.item;
    }

    public T getLast() {
        return sentinel.prev.item;
    }

    public void addFirst(T x) {
        IntNode n = new IntNode(x, null, null);
        n.next = sentinel.next;
        n.prev = sentinel;
        sentinel.next.prev = n;
        sentinel.next = n;
        size += 1;
    }

    public void addLast(T x) {
        IntNode n = new IntNode(x, null, null);
        n.next = sentinel;
        n.prev = sentinel.prev;
        sentinel.prev.next = n;
        sentinel.prev = n;
        size += 1;
    }

    public void deleteFirst() {
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
    }

    public void deleteLast() {
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
    }

    public T getIndex(int i) {
        if (i < size) {
            IntNode p = sentinel;
            while (i >= 0) {
                p = p.next;
                i -= 1;
            }
            return p.item;
        }
        throw new IndexOutOfBoundsException();
    }

    public int size() {
        return size;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DLList) {
            DLList o = (DLList) obj;
            int size1 = size();
            int size2 = o.size();
            if (size1 == size2){
                for (int i = 0; i < size1; i++) {
                    if (!getIndex(i).equals(o.getIndex(i))) {
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        IntNode p = sentinel;
        String returnString = "#";
        for (int i = 0; i < size; i++) {
            p = p.next;
            returnString = returnString + "->" + p.item.toString();
        }
        return returnString;
    }
}
