[toc]

# 接口、lambda表达式与内部类

## 接口

- 接口用来描述应该做什么，而不应该描述怎么做

- 接口中的所有方法默认都是public的。在实现接口时，必须把方法声明为`public`。

- 接口没有实例字段。

- 接口中的字段总是`public static final`

- 在Java8中，允许在接口中添加静态方法。

- 在Java9中，接口中的方法可以是`private`的

- 可以为接口方法提供一个默认实现。使用`default`进行标记。默认方法可以调用其他方法。

  代码示例

  ```java
  public interface MyInterface {
      void run();
      void eat();
  
      default void show(){
          System.out.println("I am very happy");
      }
  }
  
  
  public class Demo implements MyInterface {
      @Override
      public void run() {
          System.out.println("I am running");
      }
  
      @Override
      public void eat() {
          System.out.println("I am eating");
      }
  
      public static void main(String[] args) {
          Demo demo = new Demo();
          demo.show();
      }
  }
  ```

- 可见实现接口的时候并不强制实现默认方法。

- 

