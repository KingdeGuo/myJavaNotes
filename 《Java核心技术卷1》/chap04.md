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
- 

