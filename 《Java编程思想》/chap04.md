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

- 