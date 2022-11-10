功能？
Clarification
白板上写 + Explanation
Solve issue
Follow up

什么时候通知机器人
1. 轮询
2. 订阅

## Reference:
- https://zhuanlan.zhihu.com/p/350715727
- https://www.cnblogs.com/tang321/p/14504409.html
- https://massivetechinterview.blogspot.com/2015/07/thought-works-object-oriented-design_74.html
- https://refactoringguru.cn/design-patterns/observer/java/example
- https://3.bp.blogspot.com/-0GckS4migOA/VmQexIKD7_I/AAAAAAAAFbw/S0IIA2tz7ZM/s1600/Restaurant-UseCaseDiagram.png
- https://2.bp.blogspot.com/-7TiiG4dgrbk/VmRYGdgwFJI/AAAAAAAAFcQ/8c2tKINuVig/s640/Restaurant-ClassDiagram.png
- http://thought-works.blogspot.com/2013/12/object-oriented-design-for-restaurent.html
- http://thought-works.blogspot.com/2015/12/restaurent-design-another-round.html
- http://javaexplorer03.blogspot.com/2017/02/object-oriented-design-for-restaurant.html
- https://us.jiuzhang.com/problem/restaurant-oo-design/
- https://github.com/tssovi/grokking-the-object-oriented-design-interview/blob/master/object-oriented-design-case-studies/design-a-restaurant-management-system.md


## Clarification Questions:
- Who is the user of this system?
  - How many users? ==> Consider scaling? 
- Model the whole restaurant // only the waiter ? (Event driven ?)
- Design the system as a whole or simply start from the waiter's action?
- Consider time taken during the process? Or tasks completed immediately? (Availibility?)
- **Assume [Negatively execute an event] a notification service** to let server know the order is ready?
  - Or **Assume [Positively execute an event] the server themselves will query**?

## Use Case
- Waiter
  - Take order
  - send order to kitchen
  - send food to table
- Customer
  - Call waiter
  - Eat food
  - Pay Check
- Chef
  - Cook
  - Serve order queue

```java


```


https://www.1point3acres.com/bbs/thread-568065-1-1.html

设计一个机器人餐厅，里面的waiter是机器人。
主要三个要求，拿到客人的订单、把订单送到厨房、从厨房拿到做好的菜送回到餐桌上。
面试官说了method signature很重要，看重每个方法的输入和输出是什么，类和类之间的关系，类和类之间调用函数的过程全都要讲的很清楚。

感觉要求的重点就是类的属性和方法要说清楚，包括parameters，不用implement但是楼主就是可能太general了；


https://www.1point3acres.com/bbs/thread-564567-1-1.html
还有疑问就是 这个机器人当服务员 和 人当服务员功能上没有区别啊…… 能不能直接套用网上的普通餐馆设计 ...


https://www.1point3acres.com/bbs/thread-557830-1-1.html
一开始要求说的很简单，就说服务员是机器人，客人可以点餐，服务员要接订单，送订单给厨房，再上菜……
于是我当时傻傻的就写了两个class……
后来要求越提越多，包括不限于要考虑
每桌客人点的不同菜，
上菜要分给客人，
机器人的location要实时更新，
厨房里也变成了机器人做菜，
客人点单会有各种奇怪的额外要求要满足，

应该有个系统来控制整个餐厅调配任务，

如果餐桌或者机器人不够了怎么办……
最后甚至还专门设立了一个清洁机器人。

我答得比较快，基本上面试官提完一个新的想法，差不多想个大概5秒可以马上进行规划说出自己的想法，whiteboard上写思路跟class……
后来所有的条件都提完了，还剩5分钟让我问问题。



Questions:
题目形式？
画图？Domain Graph? Use Case graph? ====> On HackerRank? ==> 直到写code？ 
这个机器人当服务员 和 人当服务员功能上没有区别？


