[toc]

# 并发编程的挑战

## 并发程序可能比串行快，也可能比串行慢。

- 因为线程切换以及锁等问题会带来额外的开销。

- 下面有两个函数，分别表示多线程执行和串行执行，
- 使用`join`方法是为了让主线程等待子线程执行完毕。
- 

```java
public class ConcurrencyTest {

    private static final long count = 1000000000L;

    public static void main(String[] args) throws InterruptedException {
        concurrency();
        serial();
    }

    private static void concurrency() throws InterruptedException {
        long stat = System.currentTimeMillis();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                int a = 0;
                for (int i = 0; i < count; i++) {
                    a += 5;
                }
            }
        });
        t.start();
        int b = 0;
        for (int i = 0; i < count; i++) {
            b--;
        }
        t.join();
        long time = System.currentTimeMillis() - stat;
        System.out.println("concurrency: " + time + "ms, b=" + b);
    }


    private static void serial(){
        long stat = System.currentTimeMillis();
        int a = 0;
        for (int i = 0; i < count; i++) {
            a += 5;
        }
        int b = 0;
        for (int i = 0; i < count; i++) {
            b--;
        }
        long time = System.currentTimeMillis() - stat;
        System.out.println("serial:" + time + "ms, b=" + b + " , a=" + a);
    }

}

```



## 一个死锁的实例

- 在这个示例中，线程1使用了A之后使用B，但是此时线程２已经对B加锁，线程２想要使用A，但是A已经被线程１加锁，所以双方都在等对方释放但是都没有释放
- 代码示例

```java
public class DeadLock {

    private static String A = "A";
    private static String B = "B";

    public static void main(String[] args) {
        deadLock();
    }

    private static void deadLock(){
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (A) {
                    try {
                        Thread.currentThread().sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    synchronized (B) {
                        System.out.println("1");
                    }
                }
            }
        });


        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (B) {
                    synchronized (A) {
                        System.out.println("2");
                    }
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}

```

## 如何避免死锁

- 避免一个线程同时获得多个锁
- 避免一个线程在锁内同时占用多个资源,尽量保证每个锁只占用一个资源.
- 尝试使用定时锁,使用`lock.tryLock(timeout)`来替代使用内部锁机制
- 对于数据库锁,加锁和解锁必须在一个数据库连接中,否则会出现解锁失败的情况.



## 性能分析工具

- Lmbench3
- vmstat



## 如何减少上下文切换

- 无并发编程
  - 多线程竞争锁时,会引起上下文切换,可以使用一些办法避免使用锁,例如将数据ID的哈希值取模分段,不同的线程处理不同的数据.
- CAS算法
- 使用最少线程
- 协程
  - 在单线程里实现多任务调度,并在单线程里维持多个任务的切换.



## 一些命令

- `jps`
- `jstack`

