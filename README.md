# T47


## stage: graduation end

### 17.5 project: graduation; 答辩结束，老师是💩，清忆都是中，黎灿大佬是良《选择比努力更重要--14级测控答辩》

### 17.4 project: graduation; 展示两个风味的图标icon

![](http://o6qns6y6x.bkt.clouddn.com/18-6-7/51364632.jpg)


### 17.3 project: graduation; add product flavors: 1. develop 2. check

remember only put files that different from each other in new flavor floder.

请记住，仅仅在新建的falavor文件夹里放置那些不在main源集出现的代码文件或资源文件。

比如这次提交，我在develop和check的java里放置同样名字的VersionSetting.java

结果如下图：

<img src="https://github.com/anonymity12/T47/blob/hot_fix2/img/verDevelopDetected.png" width = "300" height = "310" alt="sensorDataok" align=center />


### 17.2 project: graduation; add saveToFile()

visit sensorData at/sdcard/sensor_data_recording/ like `sensorData2018-06-02-12-07-56-056.csv`

<img src="https://github.com/anonymity12/T47/blob/hot_fix2/img/sensorDateOk.jpg" width = "300" height = "100" alt="sensorDataok" align=center />


now new changes for branch `graduation` is on branch `hox_fix2`

### 17.1  project:sound record; add RECORD_AUDIO and storage permissions

you can find record files at

`getRecorderFilePath:`

```
 /storage/emulated/0/Android/data/com.example.thinkpad.t47checklisthead/cache/Recorder
```

<img src="https://github.com/anonymity12/T47/blob/hot_fix2/img/WechatIMG103.png" width = "300" height = "350" alt="图片名称" align=center />


### 16.17  solve previous layout refactor bug, now fragment 47 can use MonitorService

### now doing and next todo

graduatio related

gonna todo:

- [x] use constraint layout for fragment walking.
- [x] add sensor data text view.
- [x] add chart view 2018-04-25 11:50:26
- [x] add y and z data UI
- [x] read paper, analyze the data by 阈值分析；2018-05-01 11:52:21
- [x] add hard ware part 2018-05-04 03:27:18
- [x] use more data and sensor 2018-05-06 05:47:23

### 16.14 window DONE!! fix thread issue, now seems 50 points window method works well

### 16.2 now we see we have these sensors on samsung s8

确定了有以下几种传感器在我自己的手机上

1. TYPE_ACCELEROMETER
1. TYPE_GRAVITY
1. TYPE_GYROSCOPE
1. TYPE_STEP_DETECTOR


### 16.1 fix UI don't have x y value bug

![](http://o6qns6y6x.bkt.clouddn.com/18-5-4/76578214.jpg)

### 16.0 basic display done

![](http://o6qns6y6x.bkt.clouddn.com/18-5-1/32526421.jpg)

### * 14. test ThreadLocal

![](https://upload-images.jianshu.io/upload_images/11614105-95add62e2895a0cf.png) 

###  * 13. show u how to use nfc on samsung

lg wifi and nfc are all broken

run on samsung, the error is

```
Caused by: java.lang.RuntimeException: Tag incorrectly enumerated as MIFARE Classic, SAK = 32
```


### * J4.  4. show u how to use Future Task/ multithreading

###  * 3. show u how to get Array by reflection


### * 12. show u how to use view stub

###  * 11. show u how to find screen size

github won't allow outside pic link, so click to visit

https://gitee.com/paulkg12/tuku/blob/master/clipboard.png


<img src="https://gitee.com/paulkg12/tuku/blob/master/clipboard.png" width = "400" height = "300" alt="图片名称" align=center />


此工程包含一些简单的自定义view.


### 渐变的仪表盘

对应ProcessView

<img src="https://github.com/anonymity12/T47/blob/master/img/Screenshot_1518145017.png" width = "300" height = "533" alt="图片名称" align=center />


对应ProcessView2

<img src="https://github.com/anonymity12/T47/blob/master/img/Screenshot_1518144100.png" width = "300" height = "533" alt="图片名称" align=center />


### 表盘转动的view

对应FlexiblePanelView

<img src="https://github.com/anonymity12/T47/blob/master/img/screen_20180227195509.png" width = "300" height = "533" alt="图片名称" align=center />

### A circle button, by selector, shape

see commit message : Add oval button by selector

want revert to this commit and the output? Remember at this time
I'm using all traditional MainActivity and activity_main.xml

<img src="https://github.com/anonymity12/T47/blob/master/img/scr_20180301111618.png" width = "300" height = "300" alt="图片名称" align=center />

### viewpager and tablayout

for detail, see: http://note.youdao.com/noteshare?id=9b79e7844217dd6c4d0de81564c17bea&sub=59442BCD216C4070934930A1A5BD681C

<img src="https://github.com/anonymity12/T47/blob/master/img/viewpager.jpg" width = "300" height = "300" alt="图片名称" align=center />

### viewPager, Goolge tutorial

for detail, see also: http://note.youdao.com/noteshare?id=9b79e7844217dd6c4d0de81564c17bea&sub=59442BCD216C4070934930A1A5BD681C

<img src="https://github.com/anonymity12/T47/blob/master/img/googleViewPagerExample.jpg" width = "300" height = "350" alt="图片名称" align=center />

### closeWithPower

像一个微笑的嘴巴和鼻子，其实是 关闭按钮。

对应： CloseWithPowerIndicatorView

<img src="https://github.com/anonymity12/T47/blob/master/img/closeWithPower.jpg" width = "300" height = "350" alt="图片名称" align=center />


### Handler Test OK!

<img src="https://github.com/anonymity12/T47/blob/master/img/handlerOK.png" width = "300" height = "250" alt="图片名称" align=center />

### AIDL Test OK!

### AppWidget simple example TestOk!

<img src="https://github.com/anonymity12/T47/blob/master/img/Screenshot_20180320-140755.png" width = "300" height = "580" alt="图片名称" align=center />

<img src="https://github.com/anonymity12/T47/blob/master/img/Screenshot_20180320-140806.png" width = "300" height = "250" alt="图片名称" align=center />

###  * 9. show u how to write file in Android.

createNewFileInAndroid

<img src="https://github.com/anonymity12/T47/blob/master/img/createNewFileInAndroid.png" width = "625" height = "70" alt="图片名称" align=center />


###  * 10. hensen sample login to httpbin.org

RxJava ok

<img src="https://github.com/anonymity12/T47/blob/master/img/hensenRxDemoByHttpBin.gif" width = "250" height = "425" alt="图片名称" align=center />


