# T47

this branch is of RxJava

see tutorial at https://www.jianshu.com/p/b39afa92807e

---

# what I've done

 https://www.jianshu.com/p/b39afa92807e

## 入门教程2

### create，基础使用

 代码见T47 的0.4 create 相关的 提交

![](https://ws1.sinaimg.cn/large/005JrW9Kgy1fxlu03u32zj30u807041y.jpg)



### map 的使用

代码参见本次提交： 0.5 map的使用

![](https://ws1.sinaimg.cn/large/005JrW9Kgy1fxltxsro15j30vg03edhl.jpg)


### zip 的使用

zip 将两个Observable 进行配对，其中发射事件多的Observable 一方的剩余的事件不会被zip 配对，

zip 仅仅配对发射比较少数量的那一方的Observable 的那么多的配对事件。

下图表现的是 ： 字母事件 有 ABC 三个， 数字事件 12345个，但是你看到了，最后只有三个zip后的事件得到accept

![](https://ws1.sinaimg.cn/large/005JrW9Kgy1fxmycfielij30ry0a8n1p.jpg)


 # todo 1127

 https://www.jianshu.com/p/b39afa92807e

