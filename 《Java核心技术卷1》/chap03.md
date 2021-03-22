[toc]

# Java的基本程序设计结构

## 3.1 一个简单的Java应用程序

- Java中的所有函数都是某个类的方法。因此Java中的每个方法都必须有一个外壳`(shell)`类。
- Java中的`main()`方法必须是静态的。
- `main()`方法中的`void`表示的是没有返回值。与C不同的是，Java中没有为操作系统返回退出码。如果正常退出，那么Java程序的退出码是0，表示成功运行了程序，如果希望其他状态退出，那么则执行`System.exit()`方法。

## 3.3 数据类型

- 8种基本类型
  - byte
  - short
  - int 
  - long
  - float
  - double
  - char
  - boolean

- 可以使用十六进制表示浮点数的值。例如`0.125`可以表示为`0x1.0p-3`.

  > 计算方式如下：0.125 = 1 x 0^-3，然后程浩前面使用十六进制表示，指数部分还是十进制。p表示指数

- 检测一个值是否是`NaN`

  ```java
  // 正确的做法 
  if (Double.isNaN(0.0/0)){
      System.out.println("Rright use");
   }
  
  // Wrong example
  if (0.0 / 0.0 == Double.NaN) {
      System.out.println("this will never right");
  }
  ```

- Unicode转义序列会在解析代码之前得到处理

## 3.4 变量与常量

- 从Java10开始，对于局部变量，如果可以从变量的初始值推断出它的类型，就不在需要声明类型。只需要使用`var`关键字。

  ```java
  var myStr = "I am kindeguo";
  var myDate = new Date();
  ```

## 3.5 运算符

- 对于使用`strictfp`关键字标记的方法必须使用严格的浮点计算来生成可再生的结果。

- 静态导入

  - 使用静态导入之前

  ```java
  System.out.println("I choose the num: " + Math.sqrt(4));
  ```

  - 使用静态导入之后

  ```java
  import static java.lang.Math.*;
  System.out.println("I choose the num: " + sqrt(4));
  ```

- 如果得到一个完全可预测的结果比运行速度更重要的话，那么就应该使用`StrictMath`类。

- 类型转化

  ![image-20210322160728071](https://raw.githubusercontent.com/KingdeGuo/myPictureBed/main/img_upload202103/22/161515-559864.png)

- 位运算

  - 例如求解一个整性的从右边数的第四位，可以使用

    ```java
    ( x & ( 1 << 3 )) >> 3;
    ```

- 字符串

  - length()

  ```java
  String str = "我的名字是\uD835\uDD46";
  System.out.println(str);
  // length()返回的是采用UTF-16编码给定字符串所需要的代码单元数量
  System.out.println(str.length());
  // 要想获得实际的长度，可以使用下面的方法
  System.out.println(str.codePointCount(0, str.length()));
  ```

  - 

