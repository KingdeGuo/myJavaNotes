[toc]

# 对象与类

## 4.1 面向对象程序设计概述

- 关注对象的三个特性

  - 行为
  - 状态
  - 标识

- 对象状态的改变必须通过调用方法实现，否则就是破坏了封装性

- 类之间的关系

  - 依赖 uses-a

    一个类的方法使用或操纵另一个类的对象

  - 聚合 has-a

    一个对象包含另一个对象

  - 继承 is-a

    表示一个更特殊的类与更一般的类之间的关系

## 4.2 使用预定义类

- 类库设计者决定将保存时间与给时间点命名分开，所以标准Java类库分别包含两个类，一个用来表示时间点的Date类，另一个是用大家熟悉的日历表示法表示日期的LocalDate类。

- 不要使用构造器来构造LocalDate对象。实际上，应该使用静态工厂方法，它会代表你调用构造器。

  - 构造特定时间

    ```java
    LocalDate.of(2020.1.1)
    ```

  - 构造当前日期

    ```java
    LocalDate.now()
    ```

  - LocalDate常用API

  ![image-20210322173521862](https://raw.githubusercontent.com/KingdeGuo/myPictureBed/main/img_upload202103/22/173523-417306.png)

- 可以使用`jdeprscan`来检测代码中是否使用了Java API中已经废弃了的特定

- `GregorianCalendar`是更改器方法。会改变对象的状态。

  而`plusDays`是生成了一个新的对象

  ```java
  GregorianCalendar someday = new GregorianCalendar(1999, 6, 3);
  someday.add(Calendar.DAY_OF_MONTH, 1000);
  ```

## 4.3 用户字定义类

- 构造器特点

  - 与类名相同
  - 每个类可以有一个以上的构造器
  - 构造器可以有0个、1个或多个参数
  - 构造器没有返回值
  - 构造器总是伴随着new操作符一起调用

- 关于null

  ```java
  class Person{
      private String name;
      
      public Person(){}
      
      // 严格型
      public Person(String name) {
          Objects.requireNonNull(name, "name could not be null");
      }
  }
  ```

  在Java9中，还可以这样写

  ```java
  this.name = Objects.requireNonNullElse(name, "default vale");
  ```

- 在Java中，所有的方法都必须在类的内部进行定义，但并不表示它们是内联方法。是否将某个方法设置为内联是Java虚拟机的任务。即时编译器会将那些简短的，经常调用且没有被覆盖的方法调用，并进行优化。

- final字段

  - 可以将实例字段设置为final。该字段必须在构造对象时初始化。如果将类的实例字段设置为final，那么这个字段就没有setter方法了。

    ```java
    class Person{
        final String name = "kingdeguo";
    
        public String getName() {
            return name;
        }
    }
    ```

  - final关键字只是表示存储在变量中的对象中的对象的引用不会再指示另一个不同的对象。但是这个对象是可以改变的。

    ```java
    class Person{
        String name = "kingdeguo";
    
        public String getName() {
            return name;
        }
        
        void changeName(){
            name = name + ", hello";
        }
    }
    ```

- main方法不对任何对象进行操作。事实上，在启动程序时还没有任何对象。静态的main方法将执行并构造程序所需的对象

## 4.5 方法参数

- Java程序设计语言总是按值调用
- Java中的方法参数
  - 不能修改基本数据类型的参数（即数值类型或布尔类型）。
  - 可以改变对象参数的状态。
  - 不能让一个对象参数引用一个新的对象。

## 4.6 类的构造

- 在一个类中，可以包含任意多个代码块。只要构造这个类的对象，这些块就会被执行。
- 所有的静态字段初始化方法以及初始化块都将依照类声明中出现的顺序执行。
- 如果一个资源一旦使用完就需要立即关闭，那么应该提供一个close方法来完成必要的清理工作。
- 如果可以等到虚拟机退出，那么可以用方法Runtime.addShutdownHook增加一个“关闭钩”。在Java9中，可以使用CLeaner类注册一个动作，当对象不再可达时，就会完成这个动作。

## 4.8 jar文件

- jar文件使用ZIP格式组织文件和子目录。可以使用任何ZIP工具查看JAR文件
- 可以使用`-classpath`选定指定类的路径
- 创建一个jar文件
  - jar cvf jarFileName file1 file2

## 4.9 文档注释

- 自由格式文本可以使用HTML修饰符
- 方法注释
  - `@param`
  - `@return`
  - `@throws`
- 通用注释
  - `@author`
  - `@version`

## 4.10 类的设计技巧

- 一定要保证数据私有
- 一定要对数据进行初始化
- 不要再类中使用过多的基本类型
- 不是所有的字段都需要单独的字段访问器和字段更改器
- 分解有过多指责的类
- 类名和方法名要能够体现他们的职责
- 优先使用不可变类



