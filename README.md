# T47

## java test 分支 实验分支也

### Stream test

我发现，java 的语法 令你不要做傻事，像这样： （在`while`里直接还显式 调用`continue` 。这是不允许的SB行为）

<img src="https://github.com/anonymity12/T47/blob/java_test/img/whileNoConti.jpg" width=460, height=166>


我发现Stream写入的东西，我读出来是个啥？

<img src="https://github.com/anonymity12/T47/blob/java_test/img/StremTest.jpg" width=550, height=270>

### Android dex 动态加载机制

参考： https://blog.csdn.net/wy353208214/article/details/50859422

效果：

<img src="https://github.com/anonymity12/T47/blob/java_test/img/dexOk.jpg" width=300, height=500>

不要忘记在这里的文件啊：

<img src="https://github.com/anonymity12/T47/blob/java_test/img/myDex_jar_output.jpg" width=600, height=250>

看到dex位置在外置存储内：

```
loadDexClass at position: /storage/emulated/0/Android/data/com.example.thinkpad.t47checklisthead/cache/dynamic_dex.jar
```


我生成的dex 实际上 的祖先是：

<img src="https://github.com/anonymity12/T47/blob/java_test/img/jd-gui-outputMyJar.jpg" width=600, height=250>