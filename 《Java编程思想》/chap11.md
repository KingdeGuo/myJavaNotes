[toc]

# 持有对象

## 泛型与泛型安全的容器

- `ArrayList`保存的是`Object`。

  ```java
  public class Demo {
      public static void main(String[] args) {
          ArrayList arrayList = new ArrayList();
          arrayList.add(new Integer("2"));
          arrayList.add(new Float("1.3"));
  
          for (int i = 0; i < arrayList.size(); i++) {
              System.out.println(arrayList.get(i));
          }
      }
  }
  /*
      2
      1.3
  */
  ```

  使用泛型，就可以在编译器防止将错误类型的对象放置到容器中。

  ```java
  public class Demo {
      public static void main(String[] args) {
          ArrayList<Integer> arrayList = new ArrayList<>();
          arrayList.add(new Integer("2"));
          arrayList.add(new Integer("3"));
  //        arrayList.add(new Float("1.3"));
  
          for (int i = 0; i < arrayList.size(); i++) {
              System.out.println(arrayList.get(i));
          }
      }
  }
  /*
  	2
  	3
  */
  ```

## 基本概念

Java容器类库的用途是“保存对象”。可分为两个不同的概念。

- `Collection`：一个独立元素的序列。例如：List，Set，Queue
- `Map`：一组成对的“键值对”对象。也被称为关联数组或字典。

- 推荐这样的方式：创建一个具体类的对象，将其转型为对应的接口，然后在其余的代码中都使用这个接口。例如：`List<integer> list = new ArrayList<>();`，但是这种方法并不总是奏效，因为某些类具有额外的功能，比如`TreeMap`就包含一些`Map`没有实现的方法。当需要使用这些方式时，就不能将他们向上转型为更通用的接口。

## 添加一组元素

- `ArrayList.asList()`接收一个数组或是一个用逗号分割的元素列表。

- `Collections.addAll()`方法接受一个Collection对象。

  ```java
  public class Demo {
      public static void main(String[] args) {
          int[] arr = new int[]{1, 2, 3, 4, 5, 6};
          Collection collection = null;
          collection.addAll(Arrays.asList(arr));
      }
  }
  ```

- 注意下面的问题

  ```java
  import java.util.*;
  
  class Snow {}
  class Powder extends Snow {}
  class Light extends Powder {}
  class Heavy extends Powder {}
  class Crusty extends Snow {}
  class Slush extends Snow {}
  
  public class AsListInference {
    public static void main(String[] args) {
      List<Snow> snow1 = Arrays.asList(
        new Crusty(), new Slush(), new Powder());
  
      // Won't compile:
      // List<Snow> snow2 = Arrays.asList(
      //   new Light(), new Heavy());
      // Compiler says:
      // found   : java.util.List<Powder>
      // required: java.util.List<Snow>
  
      // Collections.addAll() doesn't get confused:
      List<Snow> snow3 = new ArrayList<Snow>();
      Collections.addAll(snow3, new Light(), new Heavy());
  
      // Give a hint using an
      // explicit type argument specification:
      List<Snow> snow4 = Arrays.<Snow>asList(
         new Light(), new Heavy());
    }
  } ///:~
  ```

  在上面的代码中，试图创建`snow2`时，`Arrays.asList()`中只有`Powder`类型，因此会创建`List<Powder>`而不是`List<Snow>`，尽管`Collections.addAll()`工作很好，因为它从第一个参数中了解到了目标参数类型是什么。

## 容器的打印



