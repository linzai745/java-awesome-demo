package io.github.linzai745.pool;

import java.util.concurrent.atomic.AtomicInteger;

public class PoolMain {
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;

    private static final int COUNT_MASK = (1 << COUNT_BITS) - 1;

    public static void main(String[] args) {
        AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
        System.out.println("COUNT_BIT: " + COUNT_BITS);
        System.out.println("RUNNING: " + RUNNING);
        System.out.println("SHUTDOWN: " + SHUTDOWN);
        System.out.println("STOP: " + STOP);
        System.out.println("TIDYING: " + TIDYING);
        System.out.println("TERMINATED: " + TERMINATED);
        System.out.println("COUNT_MASK: " + COUNT_MASK);
        System.out.println("~COUNT_MASK: " + ~COUNT_MASK);
        System.out.println("CTL: " + ctl);
        System.out.println("workerCountOf: " + workerCountOf(ctl.get()));
    }

    private static int ctlOf(int rs, int wc) {
        return rs | wc;
    }

    private static int runStateOf(int c)     {
        return c & ~COUNT_MASK;
    }
    private static int workerCountOf(int c)  {
        return c & COUNT_MASK;
    }

}
