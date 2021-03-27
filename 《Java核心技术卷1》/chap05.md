[toc]

# 继承

## 5.1 类、超类和字类

- 反射是指在程序运行期间更多的了解类及其属性的能力

- `this`的两个含义

  - 指示隐式参数的引用
  - 调用该类的其他构造器

- `super`的两个含义

  - 调用超类的构造器
  - 调用超类的方法

- 一个对象变量可以指示多种实际类型的现象称为多态。

- 在运行时能够自动的选择适当的方法，称为动态绑定。

- 在Java中，动态绑定是默认的行为。如果不希望让一个方法是虚拟的，可以将它标记为`final`。

- `is-a`规则的另一种表述是替换原则替换原则，它指出程序中出现超类对象的任何地方都可以使用子类对象替换。

- 注意下面的例子

  ```java
  Employee[] staff = new Employee[3];      
  staff[0] = boss;
  staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
  staff[2] = new Employee("Tommy Tester", 40000, 1990, 3, 15);
  ```

  虽然`staff[0]`被赋值为`boss`，但是`staff[0]`仍然是`Employee`类型的。

- `重载解析`首先会找完全匹配的，如果没有找到完全匹配的，则会尝试强制类型转化，如果仍然没有找到，那么就会报错。

- 动态绑定与静态绑定

  - 如果是`private`，`static`，`final`方法，那么编译器可以准确的知道调用哪个方法，这称为静态绑定。
  - 与此相对应的是，如果要调用的方法依赖于隐式参数的实际类型，那么必须在运行时使用动态绑定。

- 然而每次调用方法都要完成搜索，时间开销相当大，因此，虚拟机预先为每个类计算了一个方法表，其中列出了所有的方法的签名和要调用的实际方法。

- 下面举例来说明

  - 代码

  ```java
  public class ManagerTest
  {
     public static void main(String[] args)
     {
        // construct a Manager object
        Manager boss = new Manager("Carl Cracker", 80000, 1987, 12, 15);
        boss.setBonus(5000);
  
        Employee[] staff = new Employee[3];
  
        // fill the staff array with Manager and Employee objects
  
        staff[0] = boss;
        staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        staff[2] = new Employee("Tommy Tester", 40000, 1990, 3, 15);
  
        // print out information about all Employee objects
        for (Employee e : staff)
           System.out.println("name=" + e.getName() + ",salary=" + e.getSalary());
     }
  }
  ```

- `getSalary`不是`private`, `staic`, `final`方法，因此采用动态绑定。

- 虚拟机为`Employee`, `Manager`生成方法表。

  注意，该方法表并不完整，因为关于超类`Object`没有写出。

  ```java
  Employee:
  	getName() -> Employee.getName();
  	getSalary() -> Employee.getSalary();
  	getHireDay() -> Employee. getHireDay();
  	raiseSalary(double) -> Employee.raiseSalary(double);
  
  Manager:
  	getName() -> Employee.getName();
  	getSalary() -> Manager.getSalary();
  	getHireDay() -> Employee. getHireDay();
  	raiseSalary(double) -> Employee.raiseSalary(double);
  	setBounds(double) -> Manager.setBounds(double);
  ```

- 接下来调用`e.getSalary()`的解析过程

  - 首先，虚拟机获取`e`的实际类型的方法表。这可能是Employee、Manager的方法表，也可能是Employee类的其他子类的方法表
  - 接下来，虚拟机查找定义了定义了`getSalary()`签名的类，此时，虚拟机已经知道了应该调用哪个方法。
  - 最后，虚拟机调用这个方法

- 动态绑定有一个非常重要的特定，无需对现有的代码进行修改就可以对程序进行扩展。

- 如果将一个类声明为final，那么其中的方法自动的成为final，而不包括字段。

- 进行对象间强制类型转化的唯一原因就是：要在暂时忽略对象的实际类型之后使用对象的全部功能。

- 将一个值存入变量时，编译器将检测你是否承诺过多。也就是说，如果把子类的引用赋给一个超类变量，编译器是允许的，但是将一个超类的引用赋值给一个子类变量时，就承诺过多了，此时必须进行强制类型转化才可以。（老板一定是员工，但是员工不是老板，承诺过多就是给了没有的，这就是过多承诺）

- 注意下面写法

  ```java
  // Manager boss = (Manager)staff[1];
  // 这个就属于谎报，在继承链上进行向下的强制类型转化
  // 所以这个会报错
  
  // 这样会导致程序意外终止
  // 因此推荐下面的编程习惯
  if(staff[1] instaceof Manager){
  	boss = (Manager)staff[1];
  }
  ```

- 抽象类

  - 为了提高程序的清晰度，包含了一个或多个抽象方法的类本身必须被声明为抽象的。
  - 除了抽象方法外，抽象类还可以包含字段和具体方法
  - 抽象类不能实例化。
  - 可以定义一个抽象类的对象变量，大那是这个变量只能引用非抽象子类的对象

## 5.2 Object：所有类的超类

- 可以使用Object类型的变量引用任何类型的对象。`Object obj = new Student(18,"kingdeguo");`，但是此时Object类型的变量只能当作各值的一个泛型容器。要想对其进行操作，还需要进行相应的强制类型转化`Student stu = (Studnet)obj`。

- 所有类型，不管是对象数组还是**基本类型的数组**都扩展了Object类。

- 为了防止字段为null的情况，需要使用`Object.equals`方法，如果两个参数都是`null`，`Objects.equals(a,b)`调用将返回`true`，其中一个为`null`，另一个不是返回`false`，两个都不是`null`，调用`a.equals(b);`

  代码示例

  ```java
  @Override
  public boolean equals(Object obj){
      if(obj instanceof Student){
          obj = (Student)obj; 
      }else{
          System.out.println("Wrong!");
          return false;
      }
      
      return Objects.equals(name, obj.name)
          && id == obj.id;
      
  }
  ```

- Java语言规范要求equals方法具有以下特征

  - 自反性
  - 对称性
  - 传递性
  - 一致性
  - 对于任意非空引用x，x.equals(null)应该返回false。

- 请注意下面的例子

  e.equals(m)

  其中e是一个Employee类的实例，m是Manager类的实例，两者具有相同的姓名，薪水和雇佣日期，如果在Employee.equals中用instanceof进行检测，这个调用返回true，同时意味着反过来调用也需要返回true。

  因此可以看到有两种完全不同的情形

  - 如果子类可以有自己的相等性概念，则对称性需求将强制使用getClass检测
  - 如果由超类决定相等性概念，那么就可以使用instanceof检测，这样可以在不同子类的对象之间进行相等性比较。

- 下面给出一个完美的`equals`方法建议

  - 显示参数名命为`otherObject`，稍后需要将他强制转化成另一个名为`other`的变量

  - 检测`this`与`otherObject`是否相等。

    `if (this == otherObject) return true;`

    这条语句只是一个小优化，实际上，这是一种经常采用的行式，因为检查身份要比逐个比较字段开销小。

  - 检测`otherObject`是否为`null`，如果为`null`，返回`false`，这项检测是必要的。

    `if (otherObject == null ) return false;`

  - 比较`this`与`otherObject`的类。如果equals的语义可以在子类中改变，就使用`getClass`检测

    `if (getClass() != otherObject.getClass() ) return false;`

    如果所有的子类都有相同的相等性语义，就可以使用`instanceof`检测。

  - 将`otherObject`强制转化为相对应类型的变量

    `ClassName other = (ClassName) otherObject;`

  - 现在根据相等性概念要求来比较字段。使用==比较基本类型字段，使用`Objects.equals`比较对象字段，如果所有字段都匹配，则返回`true`，否则返回`false`.

    return filed1 == other.filed1

    ​	&& Objects.equals(field2, other.field2)

    ​	&& ...;

    如果在子类中重新定义`equals`，就要在其中一个包含一个`super.equals(other)`调用。

- String类计算计算散列码的方法

  ```java
  int hash = 0;
  for(int i=0l i<length(); ++i){
      hash = 31*hash + charAt(i);
  }
  ```

  由于hashcode方法定义在Object类中，因此每一个对象都有一个默认的散列码，其值对由对象的存储地址得出

  ```java
  String s1 = "kingdeguo";
  String s2 = new String("kingdeguo");
  
  StringBuilder s3 = new StringBuilder(s1);
  ```

  s1和s2有相同的散列码，因为这个是根据内容算出来的。

  但是s1和s3有不同的散列码，因为在StringBuilder中没有定义hashcode方法，而Object默认hashcode方法会从对象的存储地址得出散列码

- 

