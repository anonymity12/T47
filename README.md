# T47

# 深入理解View

## 参考：

 Android视图绘制流程完全解析，带你一步步深入了解View


## part1

https://blog.csdn.net/guolin_blog/article/details/12921889

<img src="./img/viewDeeper.jpg" width=400, height=400>

### 代码区

```
private LinearLayout mainLayout;

@Override
public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mainLayout = (LinearLayout) findViewById(R.id.main_layout);
    LayoutInflater LayoutInflater = android.view.LayoutInflater.from(this);
    View buttonLayout = LayoutInflater.inflate(R.layout.button_layout, null);
    mainLayout.addView(buttonLayout);

}
```