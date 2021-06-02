[toc]

# 对象导论

- 所有编程语言都提供抽象机制。可以认为，人们所能够解决的问题的复杂性直接取决于抽象的类型和质量。

- 对象具有行为、状态和标识。

- 面向对象程序设计的挑战之一，就是在问题空间的元素和解空间的对象之间创建一对一的映射。

- 使用现有的类合成新的类，所以这种概念被称为组合，如果组合是动态发生的，那么通常被称为聚合，组合经常被视为"has-a"关系。

- 类型不仅仅只是描述了作用于一个对象集合上的约束条件，同时还有与其他类型之间的关系。

- 如果继承只覆盖基类的方法，就意味着导出类和基类是完全相同的类型，因为他们具有完全相同的接口。结果可以用一个导出类对象来完全替代一个基类对象，这可以被视为存粹替代，也成为替代原则。

- 有时必须在导出类中添加新的元素，这样也就扩展了接口。这个新的类仍然可以替代基类，但是这种替代并不完美，因为基类无法访问新添加的方法。这种情况描述为is-like-a关系。

- 在处理类型的层次结构时，经常想把一个对象不当作它所属的特定类型来对待，而是将其当作其基类的对象来对待，这样使得人们可以编写出不依赖于特定类型的代码。

- 当向对象发送消息时，被调用的代码直到运行时才能确定。编译器确保被调用方法的存在，并调用参数和返回值执行类型检查，但是并不知道被执行的确切代码。

- 为执行后期绑定，Java使用了一小段特殊代码来替代绝对地址调用。

- Java中，动态绑定是默认行为，不需要添加额外的关键字来实现多态。

- 下面是一个多态是举例

  UML类图

  ![image-20210602204310652](https://raw.githubusercontent.com/KingdeGuo/myPictureBed/main/img_upload202106/02/204715-938732.png)

```java
public interface Shape {
    void showName();
    void drawShape();
}


public class Circle implements Shape {

    @Override
    public void showName() {
        System.out.println("This shape's name: Circle");
    }

    @Override
    public void drawShape() {
        System.out.println("Draw a Circle.");
    }
}

public class Square implements Shape {
    @Override
    public void showName() {
        System.out.println("This shape's name: Square");
    }

    @Override
    public void drawShape() {
        System.out.println("Draw a Square.");
    }
}


public class Triangle implements Shape {
    @Override
    public void showName() {
        System.out.println("This shape's name: Square");
    }

    @Override
    public void drawShape() {
        System.out.println("Draw a Square.");
    }
}


public class HandleShape {
    public void handleShape(Shape shape){
        shape.showName();
        shape.drawShape();
    }

    public static void main(String[] args) {
        HandleShape shape = new HandleShape();
        Circle circle = new Circle();
        Square square = new Square();
        Triangle triangle = new Triangle();

        shape.handleShape(circle);
        shape.handleShape(square);
        shape.handleShape(triangle);
    }
}


/*
This shape's name: Circle
Draw a Circle.
This shape's name: Square
Draw a Square.
This shape's name: Square
Draw a Square.
*/
```

