[toc]

# 并发

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

- <script src="https://gist.github.com/KingdeGuo/93c51b9e59339f6f2b27bff30b5cc2ab.js"></script>