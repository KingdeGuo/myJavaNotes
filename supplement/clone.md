[toc]

# Java中的克隆

## 概念

- 浅克隆：仅复制基本类型。
- 深克隆：复制基本类型和引用类型。

### 优点

- 提高创建效率
- 扩展性好
- 简化了创建结构
- 可以使用深克隆保存对象状态

### 缺点

- 需要为每一个类分配一个克隆方法，且该克隆方法位于一个类的内部，当对已有类进行改造时，需要修改源代码，违背了开闭原则。
- 在实现深克隆的时需要编写较为复杂的代码，而且当对象之间存在多重嵌套引用时，为了实现深克隆，每一层对象对应的类都必须支持深克隆，实现起来可能比较麻烦。

### 适用场景

- 创建对象成本比较大时，可以通过复制快速创建对象。
- 系统需要保存对象状态，而且对象状态变化很小。
- 需要避免使用分层次的工厂类来创建分层次的对象。

## 实现

- 浅克隆（shallow clone）
  - 通过覆写Object类的clone()方法。
  - 能够实现克隆的类必须实现一个标识接口Cloneable，表示类支持被复制。
- 深克隆（deep clone）：可以通过序列化的方式实现。
  - 序列化就是将对象写到流的过程，写到流中的对象是原有对象的一个复制品，而原对象让在内存中。
  - 通过序列化实现的复制不仅可以复制对象本身，而且可以复制其引用的成员对象。
  - 通过序列化将对象写到一个流中，再从流中将其读出来。

## 示例

- [原型模式](https://github.com/KingdeGuo/myDesignPatternsNotes/blob/main/%E5%8E%9F%E5%9E%8B%E6%A8%A1%E5%BC%8F.md)中对克隆的运用。

## Java语言中clone()需要满足的条件

- 对任何的对象x,都有：x.clone()!=x.即克隆对象与原对象不是同一个对象。

- 对任何的对象x,都有：x.clone().getClass==x.getClass(),即克隆对象与原对象的类型一样。

- 如果对象x的equals()方法定义恰当的话，x.clone().equals(x)应当是成立的。

## 注意事项

- 参考文章：[为什么重写equals必须重写hashCode](https://segmentfault.com/a/1190000024478811)

- 重写clone()之后要重写equals方法

  因为Object的equal方法默认是两个对象的引用的比较，意思就是指向同一内存,地址则相等，否则不相等；如果你现在需要利用对象里面的值来判断是否相等，则重载equal方法。

- 重写equals()方法之后要重写hashcode()方法

## 模式扩展：圆形管理器

- 将多个原型对象存储在一个集合中集中供客户端使用。
- 是一个专们负责克隆对象的工厂，定义了一个集合用于存储原型对象。
- 针对抽象原型编程，以便扩展。

### 模式举例

- 案例描述：文档包含《需求分析》、《概要设计》等

- 类图描述

  ![image-20210623203400162](https://raw.githubusercontent.com/KingdeGuo/myPictureBed/main/img_upload202106/23/203404-84441.png)

- 代码示例

  ```java
  public interface OfficalDocument extends Cloneable {
      public OfficalDocument colne();
      public void display();
  }
  ```

  ```java
  public class FAR implements OfficalDocument {
  
      @Override
      public OfficalDocument colne() {
          OfficalDocument far = null;
          try {
              far = (OfficalDocument) super.clone();
          } catch (Exception e) {
              System.out.println("不支持复制！");
          }
          return far;
      }
  
      @Override
      public void display() {
          System.out.println("《需求分析》");
      }
  }
  ```

  ```java
  public class SRS implements OfficalDocument {
  
      @Override
      public OfficalDocument colne() {
          OfficalDocument srs = null;
          try {
              srs = (OfficalDocument) super.clone();
          } catch (Exception e) {
              System.out.println("不支持复制！");
          }
          return srs;
      }
  
      @Override
      public void display() {
          System.out.println("《概要设计》");
      }
  }
  ```

  ```java
  /*
  * 使用饿汉式单例模式
  * */
  public class PrototypeManager {
      private Hashtable ht = new Hashtable();
      private static PrototypeManager pm = new PrototypeManager();
  
      private PrototypeManager(){
          ht.put("far", new FAR());
          ht.put("srs", new SRS());
      }
  
      public void addOfficalDocument(String key, OfficalDocument document) {
          ht.put(key, document);
      }
  
      // 通过浅克隆获取对象
      public OfficalDocument getOfficalDocument(String key) {
          return ((OfficalDocument) ht.get(key)).colne();
      }
  
      public static PrototypeManager getPrototypeManager(){
          return pm;
      }
  }
  ```

  ```java
  public class Client {
      public static void main(String[] args) {
          PrototypeManager pm = PrototypeManager.getPrototypeManager();
          OfficalDocument doc1, doc2, doc3, doc4;
  
          doc1 = pm.getOfficalDocument("far");
          doc1.display();
          doc2 = pm.getOfficalDocument("far");
          doc2.display();
          System.out.println(doc1 == doc2);
  
          doc3 = pm.getOfficalDocument("srs");
          doc3.display();
          doc4 = pm.getOfficalDocument("srs");
          doc4.display();
          System.out.println(doc3 == doc4);
      }
  }
  ```

  