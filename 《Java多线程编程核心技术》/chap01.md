[toc]

# Java多线程技能

## 基础知识

- 在windows中启动一个JVM虚拟机相当于创建了一个进程，在虚拟机中加载class文件并运行，在class文件中通过执行创建新线程的代码来执行具体的任务。

- 进程是操作系统资源分配和保护的基本单位，线程是处理器调度的基本单位。

- 什么场景下使用多线程

  - **阻塞**：一旦系统中出现阻塞现象，则可以根据实际情况来使用多线程技术提高运行效率
  - **依赖**：假如业务分为A和B两个部分，当A业务发生阻塞时，B业务不依赖A业务的执行结果，那么就可以使用多线程来提高运行效率。如果B业务依赖A业务，那么就不可以使用多线程。

- 实现多线程的方式

  - 继承`Thread`类
  - 实现`Runnable`接口

- 查看源代码我们可以看到`Thread`类也实现了`Runnable`接口，他们之间是多态关系。

  ```java
  public
  class Thread implements Runnable {
  	// ...
  }
  ```

- 通过调用`start()`方法可以启动一个线程，而启动一个线程往往是比较耗时的，这是因为需要做以下几个步骤

  - 通过JVM告诉操作系统创建线程
  - 操作系统开辟内存并使用windows SDK中的`createThread()`函数创建Thread线程对象。
  - 操作系统对Thread对象进行调度，以确定运行时机。
  - Thread在操作系统中被成功执行。

## 常用的分析线程信息的命令

- `jps + jstack`

  首先使用`jps`查看所有的线程ID

  ```shell
  PS C:\Users\asus> jps
  15328
  16140 Jps
  16636 MyThread
  7692 Launcher
  ```

  然后通过`jstack`根据ID查看详细信息

  ```shell
  PS C:\Users\asus> jstack  16636
  2021-04-14 22:11:00
  Full thread dump Java HotSpot(TM) 64-Bit Server VM (25.181-b13 mixed mode):
  
  "Service Thread" #11 daemon prio=9 os_prio=0 tid=0x000000001a413000 nid=0x5344 runnable [0x0000000000000000]
     java.lang.Thread.State: RUNNABLE
  
  "C1 CompilerThread3" #10 daemon prio=9 os_prio=2 tid=0x000000001a366000 nid=0x1458 waiting on condition [0x0000000000000000]
     java.lang.Thread.State: RUNNABLE
  
  "C2 CompilerThread2" #9 daemon prio=9 os_prio=2 tid=0x000000001a365800 nid=0x28dc waiting on condition [0x0000000000000000]
     java.lang.Thread.State: RUNNABLE
  
  "C2 CompilerThread1" #8 daemon prio=9 os_prio=2 tid=0x000000001a363800 nid=0x1348 waiting on condition [0x0000000000000000]
     java.lang.Thread.State: RUNNABLE
  
  "C2 CompilerThread0" #7 daemon prio=9 os_prio=2 tid=0x000000001a361000 nid=0x4ad4 waiting on condition [0x0000000000000000]
     java.lang.Thread.State: RUNNABLE
  
  "Monitor Ctrl-Break" #6 daemon prio=5 os_prio=0 tid=0x000000001a373800 nid=0x5560 runnable [0x000000001aa8f000]
     java.lang.Thread.State: RUNNABLE
          at java.net.SocketInputStream.socketRead0(Native Method)
          at java.net.SocketInputStream.socketRead(SocketInputStream.java:116)
          at java.net.SocketInputStream.read(SocketInputStream.java:171)
          at java.net.SocketInputStream.read(SocketInputStream.java:141)
          at sun.nio.cs.StreamDecoder.readBytes(StreamDecoder.java:284)
          at sun.nio.cs.StreamDecoder.implRead(StreamDecoder.java:326)
          at sun.nio.cs.StreamDecoder.read(StreamDecoder.java:178)
          - locked <0x00000000d6134c48> (a java.io.InputStreamReader)
          at java.io.InputStreamReader.read(InputStreamReader.java:184)
          at java.io.BufferedReader.fill(BufferedReader.java:161)
          at java.io.BufferedReader.readLine(BufferedReader.java:324)
          - locked <0x00000000d6134c48> (a java.io.InputStreamReader)
          at java.io.BufferedReader.readLine(BufferedReader.java:389)
          at com.intellij.rt.execution.application.AppMainV2$1.run(AppMainV2.java:64)
  
  "Attach Listener" #5 daemon prio=5 os_prio=2 tid=0x000000001a2cd800 nid=0x4650 waiting on condition [0x0000000000000000]
     java.lang.Thread.State: RUNNABLE
  
  "Signal Dispatcher" #4 daemon prio=9 os_prio=2 tid=0x000000001a2cc000 nid=0x5c88 runnable [0x0000000000000000]
     java.lang.Thread.State: RUNNABLE
  
  "Finalizer" #3 daemon prio=8 os_prio=1 tid=0x000000001a2b1800 nid=0x1dc0 in Object.wait() [0x000000001a78f000]
     java.lang.Thread.State: WAITING (on object monitor)
          at java.lang.Object.wait(Native Method)
          - waiting on <0x00000000d5f08ed0> (a java.lang.ref.ReferenceQueue$Lock)
          at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:144)
          - locked <0x00000000d5f08ed0> (a java.lang.ref.ReferenceQueue$Lock)
          at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:165)
          at java.lang.ref.Finalizer$FinalizerThread.run(Finalizer.java:216)
  
  "Reference Handler" #2 daemon prio=10 os_prio=2 tid=0x0000000003439000 nid=0x5c10 in Object.wait() [0x000000001a28f000]
     java.lang.Thread.State: WAITING (on object monitor)
          at java.lang.Object.wait(Native Method)
          - waiting on <0x00000000d5f06bf8> (a java.lang.ref.Reference$Lock)
          at java.lang.Object.wait(Object.java:502)
          at java.lang.ref.Reference.tryHandlePending(Reference.java:191)
          - locked <0x00000000d5f06bf8> (a java.lang.ref.Reference$Lock)
          at java.lang.ref.Reference$ReferenceHandler.run(Reference.java:153)
  
  "main" #1 prio=5 os_prio=0 tid=0x0000000003343800 nid=0x6300 waiting on condition [0x00000000030df000]
     java.lang.Thread.State: TIMED_WAITING (sleeping)
          at java.lang.Thread.sleep(Native Method)
          at tempuse.t4.MyThread.main(MyThread.java:22)
  
  "VM Thread" os_prio=2 tid=0x00000000183b9000 nid=0x5924 runnable
  
  "GC task thread#0 (ParallelGC)" os_prio=0 tid=0x0000000003358800 nid=0x30d8 runnable
  
  "GC task thread#1 (ParallelGC)" os_prio=0 tid=0x000000000335a800 nid=0x40e8 runnable
  
  "GC task thread#2 (ParallelGC)" os_prio=0 tid=0x000000000335c000 nid=0x5a80 runnable
  
  "GC task thread#3 (ParallelGC)" os_prio=0 tid=0x000000000335d800 nid=0x1c9c runnable
  
  "GC task thread#4 (ParallelGC)" os_prio=0 tid=0x0000000003360800 nid=0x393c runnable
  
  "GC task thread#5 (ParallelGC)" os_prio=0 tid=0x0000000003362000 nid=0x54a4 runnable
  
  "GC task thread#6 (ParallelGC)" os_prio=0 tid=0x0000000003365000 nid=0x5f74 runnable
  
  "GC task thread#7 (ParallelGC)" os_prio=0 tid=0x0000000003366000 nid=0x401c runnable
  
  "VM Periodic Task Thread" os_prio=2 tid=0x000000001a41d800 nid=0x6088 waiting on condition
  
  JNI global references: 12
  ```

- `jmc`

  双击`jmc.exe` —> 双击`MBean`服务器 —> 单击“线程”选项卡

  ![image-20210414221623308](https://raw.githubusercontent.com/KingdeGuo/myPictureBed/main/img_upload202104/14/221754-449270.png)

- `jvisualvm.exe`

  ![image-20210414221846600](https://raw.githubusercontent.com/KingdeGuo/myPictureBed/main/img_upload202104/14/221855-614309.png)



## 线程的随机性



