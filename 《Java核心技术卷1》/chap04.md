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
- 在一个类中，可以包含任意多个代码块。只要构造这个类的对象，这些块就会被执行。
- 所有的静态字段初始化方法以及初始化块都将依照类声明中出现的顺序执行。
- 

