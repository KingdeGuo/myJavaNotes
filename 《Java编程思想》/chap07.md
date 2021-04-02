[toc]

# 复用类

- 初始化的位置

  - 定义对象的地方。这意味着它们总是在构造器被调用之前被初始化。
  - 在类的构造器中。
  - 在正要使用这些对象之前，这种方式也被称为惰性初始化。可以避免额外的负担。
  - 使用实例初始化。

- Java没有提供对代理的直接支持。代理是继承和组合的中庸之道。

  - 一个使用代理的实例

  ```java
  public class SpaceShipControls {
    void up(int velocity) {}
    void down(int velocity) {}
    void left(int velocity) {}
    void right(int velocity) {}
    void forward(int velocity) {}
    void back(int velocity) {}
    void turboBoost() {}
  } ///:~
  
  public class SpaceShipDelegation {
    private String name;
    private SpaceShipControls controls =
      new SpaceShipControls();
    public SpaceShipDelegation(String name) {
      this.name = name;
    }
    // Delegated methods:
    public void back(int velocity) {
      controls.back(velocity);
    }
    public void down(int velocity) {
      controls.down(velocity);
    }
    public void forward(int velocity) {
      controls.forward(velocity);
    }
    public void left(int velocity) {
      controls.left(velocity);
    }
    public void right(int velocity) {
      controls.right(velocity);
    }
    public void turboBoost() {
      controls.turboBoost();
    }
    public void up(int velocity) {
      controls.up(velocity);
    }
    public static void main(String[] args) {
      SpaceShipDelegation protector =
        new SpaceShipDelegation("NSEA Protector");
      protector.forward(100);
    }
  } ///:~
  ```

  可以看到，上面的方法是如何传递给了底层的controls对象，而其接口由此也就与使用继承得到的接口相同了。但是，我们使用代理时可以拥有更多的控制力，因此我们可以选择只提供在成员对象中的方法的某个子集。

- 组合和继承都允许在新的类中放置子对象，组合是显示的这样做，继承是隐式的这样做。

- 为新类提供方法，并不是继承技术中最重要的方面，其最重要的方面是如何用来表现新类和基类之间的关系。这种关系可以用“新类是现有类的一种类型”这句话加以概括。

- 由导出类转成基类，在继承图上是向上移动的，因此一般称为向上转型。由于向上转型是从一个比较通用的类型向比较通用的类型转化，所以总是安全的。也就是说，导出类是积类的一个超集。它可能比基类含有更多的方法，但它必须至少具备基类中所含的方法。在向上转型的过程中，类接口中唯一可能发生的事情就是丢失方法，而不是获取他们。

- 到底该用组合还是继承，一个最清晰的判断方法就是问一问自己是否需要从新类向基类进行向上转型，如果必须向上转型，则继承是必要的，如果不需要，则应当问一下自己“我真的需要转型吗？”

- 可能使用到`final`的三种情况

  - final数据
    - 一个永远不改变的编译时常量。
      - `int a = 3; int b = 2; int c = a + b;`在编译时`c`就可以确定为5了。
    - 一个在运行时被初始化的值，而你不希望它被改变。
      - `int d = Math.random() * 10;` d的值需要在运行时才会知道。
  - final方法
    - 把方法锁定，防止继承
    - 效率
  - final类
    - 由于final类禁止继承，所以final类中的所有方法都隐式指定为final的，因为无法覆盖他们。

- Java允许生成“空白final”，即空白final是指被声明为final但又未给定初始值的域。无论什么情况，编译器都确保空白final在使用前必须初始化。必须在域的定义处或者每个构造器中用表达式对final进行赋值，这正是final域在使用前总是被初始化的原因所在。

- 类中所有`private`方法都隐式的指定为final的。

  代码示例

  ```java
  // It only looks like you can override
  // a private or private final method.
  import static net.mindview.util.Print.*;
  
  class WithFinals {
    // Identical to "private" alone:
    private final void f() { print("WithFinals.f()"); }
    // Also automatically "final":
    private void g() { print("WithFinals.g()"); }
  }
  
  class OverridingPrivate extends WithFinals {
    private final void f() {
      print("OverridingPrivate.f()");
    }
    private void g() {
      print("OverridingPrivate.g()");
    }
  }
  
  class OverridingPrivate2 extends OverridingPrivate {
    public final void f() {
      print("OverridingPrivate2.f()");
    }
    public void g() {
      print("OverridingPrivate2.g()");
    }
  }
  
  public class FinalOverridingIllusion {
    public static void main(String[] args) {
      OverridingPrivate2 op2 = new OverridingPrivate2();
      op2.f();
      op2.g();
      // You can upcast:
      OverridingPrivate op = op2;
      // But you can't call the methods:
      //! op.f();
      //! op.g();
      // Same here:
      WithFinals wf = op2;
      //! wf.f();
      //! wf.g();
    }
  } /* Output:
  OverridingPrivate2.f()
  OverridingPrivate2.g()
  *///:~
  ```

- `HashMap`取代了`Hashtable`。`ArrayList`取代了`Vector`。