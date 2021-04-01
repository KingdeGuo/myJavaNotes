[toc]

# 字符串

## 不可变的String

- 可以这样理解Java的值传递：参数是为方法提供信息的，而不是想让方法改变自己的。

- 软件设计中的一个教训：除非你用代码将系统实现，并让他动起来，否则你无法真正了解它会有什么问题。

- 请看下面代码示例

  ```java
  public class Demo{
          public static void main(String[] args){
                  String name = "kingdeguo";
                  String anotherOne = "Hello, "+name+", I am "+18+" years old";
                  System.out.println(anotherOne);
          }
  }
  ```

  将产生的class文件进行反编译,使用命令`javap -c Demo`

  ```java
  Compiled from "Demo.java"
  public class Demo {
    public Demo();
      Code:
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
  
    public static void main(java.lang.String[]);
      Code:
         0: ldc           #2                  // String kingdeguo
         2: astore_1
         3: aload_1
         4: invokedynamic #3,  0              // InvokeDynamic #0:makeConcatWithConstants:(Ljava/lang/String;)Ljava/lang/String;
         9: astore_2
        10: getstatic     #4                  // Field java/lang/System.out:Ljava/io/PrintStream;
        13: aload_2
        14: invokevirtual #5                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
        17: return
  }
  ```

  