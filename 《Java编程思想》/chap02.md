[toc]

# 一切都是对象

- 五个存储数据的地方

  - 寄存器
    - 速度最快
    - 不能直接控制
  - 堆栈
    - 通常位于RAM中（随机访问存储器）
    - Java系统必须知道存储在堆栈内部所有项的确切生命周期，以便上下移动指针。
  - 堆
    - 通常位于内存池（也位于RAM中）。
    - 编译器不需要知道数据在堆里存活多长时间。
  - 常量存储
    - 通常直接放在程序代码内部
  - 非RAM存储
    - 数据完全存活于程序之外。
    - 两个基本例子是流对象和持久化对象
      - 流对象：转化成字节流发送到另外一台机器上
      - 持久化对象：把对象存在磁盘上。

- boolean类型所占存储空间的大小没有明确指定，仅指定能够取字面值true或者false.

- 高精度计算

  - BigInteger：支持任意精度的整数
  - BigDecimal：支持任意精度的定点数

- 若类的某个成员是基本类型，即使没有进行初始化，Java也会确保它获得一个默认值。

  - 注意，局部变量并不会被自动初始化。

- 方法签名：函数名和参数列表

- 使用static关键字解决的问题：

  - 只想为某特定的领域分配单一的存储空间，而无需取考虑究竟需要创建多少对象，甚至根本不需要创建对象
  - 希望某个方法不与包含它的类的任何对象关联再一起。也就是说，即使没有创建这个对象也可以使用。（这一点对main()方法很重要）。

- 使用类名是引用static变量的首选方式，在某些情况下还为编译器进行优化提供了更好的机会。

- System的一些方法

  ```java
  public class ShowProperties {
    public static void main(String[] args) {
      System.getProperties().list(System.out);
      System.out.println(System.getProperty("user.name"));
      System.out.println(
        System.getProperty("java.library.path"));
    }
  ```

- 使用javadoc的两种方式

  - 嵌入HTML
    - 不要使用标题标签，这可能会产生冲突
  - 使用文档标签
    - 独立文档标签是指以@开头的命令，并且要置于注释最前面
    - 行内文档标签可以出现在任意地方，同样以@开头，但是要在{}内。

- javadoc只能为public和protected成员进行文档注释。

  - private的注释会被忽略掉，但是可以加上-private选项来标记。

- 一些标签示例

  - @see：允许用户引用其他类的文档
  - {@link package.class#member label}：与@see类似，但用于行内。
  - {docRoot}：产生到文档根目录的相对路径。
  - {@inheritDoc}：从当前这个类最直接的基类中继承相关文档到当前的文档注释中。
  - @version
  - @author
  - @since：指定程序代码最早的使用版本
  - @param：用于方法文档中。
  - @return
  - @throws
  - @deprecated：支出一些旧特性已由新的特定所取代

  
