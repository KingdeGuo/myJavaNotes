[toc]

# 操作符

- 生成随机数

  ```java
  // 生成一个种子
  // 也可以写成Random r = new Random(36);
  // 如果不写默认使用当前的时间作为种子
  Random r = new Random();
  // 产生一个1-100之间的值
  int num = r.nextInt(100) + 1;
  ```

  - 随机数生成器对于相同的种子值总是产生相同的随机数序列。

- `equals`默认比较的是引用

  示例1：

  ```true
  public class Demo {
      public static void main(String[] args) {
          Integer n1 = new Integer(47);
          Integer n2 = new Integer(47);
          System.out.println(n1.equals(n2));
      }
  }
  /*
  	true
  */
  ```

  示例2：

  ```java
  class MyNum{
      int n;
  }
  
  public class Demo {
      public static void main(String[] args) {
          MyNum n1 = new MyNum();
          MyNum n2 = new MyNum();
          n1.n = n2.n = 47;
          System.out.println(n1.equals(n2));
      }
  }
  /*
  	false
  */
  ```

  大多数类库都实现了`equals`方法。但是注意在自己的类中，如果不是使用默认行为（即比较引用），想要比较内容，那么就需要在自己的类中覆盖`equals`方法。

  ```java
  class MyNum {
      int n;
  
      @Override
      public boolean equals(Object obj) {
          if (obj instanceof MyNum) {
              MyNum num = (MyNum) obj;
              return this.n == num.n;
          } else {
              System.out.println("发生错误");
              return false;
          }
      }
  }
  
  public class Demo {
      public static void main(String[] args) {
          MyNum n1 = new MyNum();
          MyNum n2 = new MyNum();
          n1.n = n2.n = 47;
          System.out.println(n1.equals(n2));
      }
  }
  ```

- 注意短路现象

  - 如果所有的逻辑表达式都有一部分不必计算，那么将获得潜在的性提升

- 如果对char, byte, short类型的值进行移位处理，那么在移位之前，他们会转化为int类型，并且得到的结果也是一个int类型。只有数值右端的低5位有用，这样可以防止移位超过int型值所具有的位数。（2的5次方就是32，其他位数同理）。

- 类型转化

  - 在窄化转化时，可能会丢失信息，此时编译器会强制进行类型转化。
  - 在扩展转化中，不必显示进行，因为新类型肯定能容纳原来类型的信息。

- Java没有sizeof()，因为所有的数据类型在所有机器的大小都是相同的。