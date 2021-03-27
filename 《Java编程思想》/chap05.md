[toc]

# 初始化与清理

- `new`表达式确实返回了对新建对象的引用，但构造器本身没有任何返回值。

- 如果传入的数据类型（实际参数的类型）小于方法中声明的形式参数类型，实级数据类型 就会被提升。

- `char`略有不同，如果无法找到恰好接收`char`参数的方法，就会把`char`直接提升为`int`型。

- 如果方法接受较小的基本类型作为参数，如果传入的实级参数较大，就会通过类型转化来执行窄化转化，如果不这样做，编译器就会报错。

- 代码示例

  ```java
  //: initialization/Apricot.java
  public class Apricot {
    void pick() { /* ... */ }
    void pit() { pick(); /* ... */ }
  } ///:~
  ```

  `this`关键字只能在方法内部使用，表示“调用方法的那个对象”。

  `this`关键字对于将当前对象传递给其他方法很有用

  ```java
  //: initialization/PassingThis.java
  
  class Person {
    public void eat(Apple apple) {
      Apple peeled = apple.getPeeled();
      System.out.println("Yummy");
    }
  }
  
  class Peeler {
    static Apple peel(Apple apple) {
      // ... remove peel
      return apple; // Peeled
    }
  }
  
  class Apple {
    Apple getPeeled() { return Peeler.peel(this); }
  }
  
  public class PassingThis {
    public static void main(String[] args) {
      new Person().eat(new Apple());
    }
  } /* Output:
  Yummy
  *///:~
  ```

  

- 编译器暗自把所有操作对象的引用作为第一个参数传递给`peel()`。

- `static`就是没有`this`的方法。在`static`内部不能调用非静态方法。

- 使用`static`方法时，由于不存在`this`，所以不是通过“向对象发送消息”的方式来完成设计的。因此，如果出现了大量的`static`方法，就要重新考虑自己的设计了。

- 