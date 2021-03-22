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

- 

