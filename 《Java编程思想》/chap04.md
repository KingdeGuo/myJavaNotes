[toc]

# 执行流程

- `break`用于强行退出循环，不执行循环中剩余的语句。

  `continue`则停止执行当前的迭代，然后退回循环的开始处，开始下一次迭代

- `break`和`continue`通常只是中断当前的循环，但是若随同标签一起使用，他们就会中断循环，直到标签所在的地方。

  ```java
  myLabel:
  
  outer-iteration{
      inner-iteration{
          // ...
          break; //(1)
          // ...
          continue; // (2)
          // ...
          continue myLabel; //(3)
          // ...
          break myLabel; //(4)
      }
  }
  ```

  (1):中断内部迭代，回到外部迭代

  (2):使执行点移回内部迭代的起始处

  (3):同时中断内部迭代及外部迭代，并回到myLabel处；随后，继续从外部开始迭代

  (4):同时中断所有迭代，但是不重新进入迭代。

- 代码示例

  ```java
  //: control/LabeledFor.java
  // For loops with "labeled break" and "labeled continue."
  import static net.mindview.util.Print.*;
  
  public class LabeledFor {
    public static void main(String[] args) {
      int i = 0;
      outer: // Can't have statements here
      for(; true ;) { // infinite loop
        inner: // Can't have statements here
        for(; i < 10; i++) {
          print("i = " + i);
          if(i == 2) {
            print("continue");
            continue;
          }
          if(i == 3) {
            print("break");
            i++; // Otherwise i never
                 // gets incremented.
            break;
          }
          if(i == 7) {
            print("continue outer");
            i++; // Otherwise i never
                 // gets incremented.
            continue outer;
          }
          if(i == 8) {
            print("break outer");
            break outer;
          }
          for(int k = 0; k < 5; k++) {
            if(k == 3) {
              print("continue inner");
              continue inner;
            }
          }
        }
      }
      // Can't break or continue to labels here
    }
  } /* Output:
  i = 0
  continue inner
  i = 1
  continue inner
  i = 2
  continue
  i = 3
  break
  i = 4
  continue inner
  i = 5
  continue inner
  i = 6
  continue inner
  i = 7
  continue outer
  i = 8
  break outer
  *///:~
  ```

  