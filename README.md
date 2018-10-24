# T47

## java test 分支 实验分支也

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