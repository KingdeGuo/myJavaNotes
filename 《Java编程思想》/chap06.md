[toc]

# 访问控制权限

- 我们通常将`JAVA_HOME`设置成该值`.;%Java_Home%\bin;%Java_Home%\lib\dt.jar;%Java_Home%\lib\tools.jar;`.

  在这个语句中，`.;`就是在当前目录查找的意思。

  Java解释器的运行过程如下：首先，找出环境变量`CLASSPATH`，`CLASSPATH`包含一个或多个目录，用作查找`.class`文件的根目录。从根目录开始，解释器获取包的名称并将每个句点代替成反斜杠，以从`CLASSPATH`根中产生一个路径名称。得到的路径就会于`CLASSPATH`中的各个不同的项连接，解释器就在这些目录中查找与你创建的类名相关的`.class`文件。

- Java没有C语言的条件编译功能，该功能可以使你不必更改任何程序代码，就能够切换开关产生不同的行为。Java去掉此功能的原因可能是因为C在绝大多数情况下用此功能来解决跨平台问题的，即程序代码的不同部分是根据不同的平台来编译的。由于Java自身可以跨越不同的平台，因此这个功能对Java而言是没有必要的。

  但是条件编译还有其他用途，比如调试等。调试功能在开发过程中是开启的，在发布产品时是关闭的。

- 如果不希望任何人对该类拥有访问权限，可以把所有的构造器都指定为private，从而阻止任何人创建该类的对象。但是有一个例外，就是你在该类的static成员内部可以创建。

  代码示例

  ```java
  //: access/Lunch.java
  // Demonstrates class access specifiers. Make a class
  // effectively private with private constructors:
  
  class Soup1 {
    private Soup1() {}
    // (1) Allow creation via static method:
    public static Soup1 makeSoup() {
      return new Soup1();
    }
  }
  
  class Soup2 {
    private Soup2() {}
    // (2) Create a static object and return a reference
    // upon request.(The "Singleton" pattern):
    private static Soup2 ps1 = new Soup2();
    public static Soup2 access() {
      return ps1;
    }
    public void f() {}
  }
  
  // Only one public class allowed per file:
  public class Lunch {
    void testPrivate() {
      // Can't do this! Private constructor:
      //! Soup1 soup = new Soup1();
    }
    void testStatic() {
      Soup1 soup = Soup1.makeSoup();
    }
    void testSingleton() {
      Soup2.access().f();
    }
  } ///:~
  ```

  单例模式就是用的这个原理。