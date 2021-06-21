[toc]

# 接口

## 抽象类

- 包含抽象方法的类叫做抽象类。如果一个类包含一个或多个抽象方法，该类就必须被限定为抽象的。

- 创建抽象类和抽象方法非常有用，因为它们可以使类的抽象性明确起来。并告诉用户和编译器打算怎样来使用他们。抽象类还是很有用的重构工具，因为他们使得我们可以很容易的将公共方法沿着继承层次结构向上移动。

- 抽象方法没有方法体

  ```java
  abstract class Demo01 {
      abstract void showName();
  }
  ```

  ```java
  public class Demo02 extends Demo01 {
      @Override
      void showName() {
          System.out.println("I am Entity");
      }
  }
  ```

  ```java
  abstract class Demo03 extends Demo02{
      abstract void showClass();
  }
  ```

  ```java
  public class UseDemo03 extends Demo03{
  
      @Override
      void showClass() {
          System.out.println(this.getClass());
      }
  
      public static void main(String[] args) {
          new UseDemo03().showClass();
      }
  }
  ```

## 接口

- `interface`关键字使抽象的概念更进一步，产生一个完全抽象的类。

- 可以在`interface`前加`public`，否则只有包访问权限。

- 接口也可以包含域，默认是`static`和`final`的。

- 可以选择在接口中将方法声明为`public`的，即使不这样做也是`public`的。

- 只要一个方法操作的是一个类而非接口，那么将只能使用这个类及其子类。如果你想要将这个方法应用于不在此继承结构中的某个类，那么将会变得比较麻烦。接口可以在很大程度上放宽这种限制。因此我们可以编写出可复用性更好的代码。

- 使用接口的原因

  - 为了能够向上转型为多个基类型（以及由此带来的灵活性）。（重要原因）
  - 防止客户端程序员创建该类的对象，并确保着仅仅是建立一个接口。（与抽象类相同）

- 选择接口还是抽象类？

  如果要创建不带任何方法定义和成员变量的基类，那么应该选择接口而不是抽象类。

  如果知道某事物应该称为一个基类，那么第一选择应该是使他称为一个接口，而不是抽象类。

- 需要避免下面的问题

  ```java
  public interface Bird {
      int showName();
      void flay();
  }
  ```

  ```java
  public interface Dog {
      void showName();
      void run();
  }
  ```

  ```java
  // !!! wrong code
  public class Animal implements Bird, Dog {
  
      @Override
      public int showName() { return 0;}
  
      @Override
      public void run() {}
      
      @Override
      public void flay() {}
  
  }
  ```

  注意最下面展示的是错误的。因为前面两个接口有相同的方法（相同的方法名和参数列表），但是返回值类型不同。因此会造成冲突。这同样会造成代码可读性的混乱。要尽量避免。
  
- 为了解决接口的修改与现有的实现不兼容的问题。新`interface`的方法可以用`default`或`static`修饰，这样可以有方法体，实现类也不用重写此方法。

  - `default`：普通实例方法，可以用`this`调用，可以被子类继承重写。
  - `static`：使用上和一般静态类方法一样，但不能被字类继承，使用使用`Interface`调用。

  ```java
  public interface InterfaceDemo {
      // static method
      static void showName(){
          System.out.println("My name is kingdeguo");
      }
      // default method
      default void showInfo(){
          System.out.println("This is a default method");
      }
      // must be override
      void showAge(int age);
  }
  ```

  ```java
  public class UseInterface implements InterfaceDemo {
      @Override
      public void showAge(int age) {
          System.out.println("I am " + age + " years old.");
      }
  
      public static void main(String[] args) {
          InterfaceDemo.showName();
          UseInterface useInterface = new UseInterface();
          useInterface.showAge(18);
          useInterface.showInfo();
      }
  }
  ```

## 嵌套接口

- 接口可以嵌套在类或者其他接口中。

```java
class A {
  interface B {
    void f();
  }
  public class BImp implements B {
    public void f() {}
  }
  private class BImp2 implements B {
    public void f() {}
  }
  public interface C {
    void f();
  }
  class CImp implements C {
    public void f() {}
  }	
  private class CImp2 implements C {
    public void f() {}
  }
  private interface D {
    void f();
  }
  private class DImp implements D {
    public void f() {}
  }
  public class DImp2 implements D {
    public void f() {}
  }
  public D getD() { return new DImp2(); }
  private D dRef;
  public void receiveD(D d) {
    dRef = d;
    dRef.f();
  }
}	

interface E {
  interface G {
    void f();
  }
  // Redundant "public":
  public interface H {
    void f();
  }
  void g();
  // Cannot be private within an interface:
  //! private interface I {}
}	

public class NestingInterfaces {
  public class BImp implements A.B {
    public void f() {}
  }
  class CImp implements A.C {
    public void f() {}
  }
  // Cannot implement a private interface except
  // within that interface's defining class:
  //! class DImp implements A.D {
  //!  public void f() {}
  //! }
  class EImp implements E {
    public void g() {}
  }
  class EGImp implements E.G {
    public void f() {}
  }
  class EImp2 implements E {
    public void g() {}
    class EG implements E.G {
      public void f() {}
    }
  }	
  public static void main(String[] args) {
    A a = new A();
    // Can't access A.D:
    //! A.D ad = a.getD();
    // Doesn't return anything but A.D:
    //! A.DImp2 di2 = a.getD();
    // Cannot access a member of the interface:
    //! a.getD().f();
    // Only another A can do anything with getD():
    A a2 = new A();
    a2.receiveD(a.getD());
  }
} ///:~
```

