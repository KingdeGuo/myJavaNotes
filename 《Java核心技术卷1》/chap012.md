[toc]

# 并发

## 案例

- 创建线程的示例

  ```java
  /**
   * 使用lambda表达式创建线程的示例
   * */
  public class Test1 {
      public static void main(String[] args) {
          // Runnable是函数式接口
          Runnable r = () -> {
              for (int i = 0; i < 100; i++) {
                  System.out.println(i);
              }
          };
          // 创建线程
          Thread t = new Thread(r);
          // 启动线程
          t.start();
      }
  }
  ```

## 线程状态

可以通过`getState()`方法获取状态。

- New：新建
- Runnable：可运行
- Blocked：阻塞
- Waiting：等待
- Timed waiting：计时等待
- Terminated：终止

状态转化图

![img](file:///C:\Users\asus\AppData\Roaming\Tencent\Users\1263586919\TIM\WinTemp\RichOle\F}G26Y61V]I5U7%_JH{8]AF.png)

- Java规范并没有将正在运行作为一种状态。一个正在运行的线程仍然属于可运行状态。

- `yield()`：使当前正在执行的线程向另外一个线程交出运行权。这是一个**静态方法**。

- `join()`：等待终止指定的线程。

- `suspend()`、`resume()`分别是暂停和恢复线程，该方法已弃用。

- `stop()`方法用来强制终止一个线程，除此之外没有强制可以强制终止线程的方法，该方法已废弃。

- `interrupt()`方法可以用来请求终止一个线程。该方法会设置线程的中断状态。

- **阻塞**：当一个线程试图获取一个内部的对象锁，而这个锁正在被其他线程占有，该线程就会被阻塞。

- **等待**：当线程等待另一个线程通知调度器出现一个条件时，这个线程会进入等待状态。

- **计时等待**：有几个方法有超时参数，调用这些方法会让线程进入计时等待状态。这一状态一直保持到超时期满或者就受到适当的通知。

- 每个线程都应该不时的检查中断状态标志，以判断线程是否被中断。要想得出是否设置了中断状态，首先调用静态的`Thread.currentThread`方法获取当前线程，然后调用`isInterrupted`方法。

  ```java
  // 伪代码描述
  Runnable r1 = () -> {
      try {
          while (!Thread.currentThread().isInterrupted()) {
  			// do something in the thread
          }
      } catch (InterruptedException e) {
          // catch some exception
          e.printStackTrace();
      }finally {
          // do some clean jobs
      }
  };
  ```

- 如果在每次工作迭代之后都调用`sleep()`方法，`isInterrupted`检查既没有必要也没有用处。如果设置了中断状态，此时调用`sleep()`方法，它不会休眠，并且会请除中断状态，并抛出`InterruptedException`。

- 注意区分`interrupted`和`isInterrupted`方法

  - `interrupted`是一个静态方法，检查当前线程是否中断，而且会请除该线程的中断状态。
  - `isInterrupted`是一个实例方法，用来检查是否有线程被中断。

- 可以使用`t.setDaemon(true)`将一个线程设置为守护线程。当只剩下守护线程时，计算机就会退出。

- 可以使用`t.setName("the-name")`为线程设置名字。

- Java线程的优先级别是1-10，Windows是7个。

- 线程的`run`方法不能抛出任何检查型异常，但是，非检查型异常有可能使线程终止。在线程死亡之前，异常会传递到一个用于处理未捕获异常的处理器。可以用`setUncaughtException(Thread t, Throwable e)`方法为任何线程安装一个处理器。如果没有安装默认处理器，默认处理器为null。但是如果没有为单个线程安装处理器，那么处理器就是该线程的`ThreadGroup`对象。

- 线程组是可以一起管理的线程的集合。默认情况下，创建的所有线程都属于同一个线程组，但是也可以建立其他的组。由于现在引入了更好的特性来处理线程集合，所以建议不要再自己的程序中使用线程组。

## 同步