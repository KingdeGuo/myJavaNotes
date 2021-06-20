[toc]

# 多态

## 一个多态示例

- 多态通过分离 做什么 和 怎么做，从另一角度讲接口和实现分离开来。

  封装通过合并特征和行为来创建新的数据类型。“实现隐藏”则通过细节“私有化”把接口和实现分离开来。

- 多态的作用是消除类型之间的耦合。

- 一个多态的举例

  ![image-20210619184026689](https://raw.githubusercontent.com/KingdeGuo/myPictureBed/main/img_upload202106/19/184028-791091.png)

```java
public class Shape {
    void showName(){
        System.out.println("Shape");
    }
}
```

```java
public class Circle extends Shape {
    @Override
    void showName() {
        System.out.println("Circle");
    }
}
```

```java
public class Triangle extends Shape {
    @Override
    void showName() {
        System.out.println("Triangle");
    }
}
```

```java
public class Square extends Shape{
    @Override
    void showName() {
        System.out.println("Square");
    }
}
```

```java
public class Demo {
    public static void showInfo(Shape shape) {
        shape.showName();
    }

    public static void main(String[] args) {
        Shape circle = new Circle();
        showInfo(circle);
    }
}
```

在Demo类中，showInfo()方法在编译时是无法知道为什么是circle而不是square或者traingle的。

## 绑定

- 将一个方法调用同一个方法主体关联起来的操作称为绑定。
- 前期绑定：在程序执行前绑定，一般由编译器来实现，面向过程语言默认绑定方式。
- 后期绑定：在运行时根据对象类型进行绑定。也称为动态绑定或者运行时绑定。
- Java中除了static和final（private方法属于final方法）方法之外，所有的方法都是后期绑定。
- 所以对一个方法使用final，除了防止别人覆盖该方法之外，还可以关闭动态绑定，生成更高效的代码。

## 构造器与多态

- 构造器不具有多态性（实际上是static方法，只不过static是隐形的）。
- 调用构造器的顺序
  - 调用基类构造器。
  - 按声明顺序调用成员的初始化方法。
  - 调用导出类构造器的主体。