public class SynchronizedBlock {
/*
Determining locking order
if blocked object is the same order such as pass arguments,
then return true otherwise false;

 */

    boolean flag = true;
    public void someMethodWithSynchronizedBlocks(Object obj1, Object obj2) {
        synchronized (obj2) {
            synchronized (obj1) {
                System.out.println(obj1 + " " + obj2);
        }   }
    }

    public static boolean isLockOrderNormal(final SynchronizedBlock solution, final Object o1, final Object o2) throws Exception {
        Object test = new Object();
        final boolean[] flag = {false,false,false,false,true};
        Thread t1 = new Thread(){
            @Override
            public void run(){
                synchronized (o2){
                    flag[1] = true;
                    if(!flag[2])
                        flag[0] = true;
                    else flag[0] = false;

                    while(flag[4]){}
            }   }
        };
        Thread t2 = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                new SynchronizedBlock().someMethodWithSynchronizedBlocks(o1, o2);
            }

        };
        Thread t3 = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1)
                {
                    flag[3] = true;
                    if(!flag[2])
                        flag[0] = false;
                    else flag[0]= true;

                    while(flag[4]){}
            }   }
        };

        Thread t4 = new Thread() {
            @Override
            public void run() {
                while(flag[4]){
                    System.out.println("thread1 : " + t1.getState() + " thread2 : " + t2.getState() + " thread3 : " + t3.getState());
            }   }
        };
        t1.setDaemon(true);
        t2.setDaemon(true);
        t3.setDaemon(true);
        t4.setDaemon(true);
        t1.start();
        t2.start();
        t3.start();
        Thread.sleep(50);
        flag[4] = false;
        return flag[0];
    }
    public static void main(String[] args) throws Exception {
        final SynchronizedBlock solution = new SynchronizedBlock();
        final Object o1 = new Object();
        final Object o2 = new Object();
        System.out.println(isLockOrderNormal(solution, o1, o2));
    }
}
