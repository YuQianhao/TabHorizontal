TabHorizontal
===================================
QQ：9166401618<br>
欢迎各位大佬前来补坑！<br><br>
### 模仿的淘宝限时购页面顶部Head和ViewPager联动效果的两个自定义控件，使用起来简单粗暴！<br>
#### 直接引用[app/src/main/com/yuqianhao/TabHorizontal]目录下的两个类即可，没有导出jar，直接Copy即可！<br><br>
![](https://github.com/YuQianhao/TabHorizontal/blob/master/1.gif)<br><br>
### 1、设置布局
```java
/**
  *布局为上方一个头布局，用来放置所有的Tab标签，下方一个ViewPager用来管理Tab标签对应的Fragment
  */
  <com.yuqianhao.view.TabHorizontalScrollView
        android:id="@+id/tmpLayout"
        android:layout_width="match_parent"
        android:layout_height="50dp"
        android:scrollbarSize="0dp"
        android:fillViewport="true"
        android:background="@drawable/ic_backgroun"
        android:scrollbars="horizontal">

        <com.yuqianhao.view.TabLinearLayout
            android:id="@+id/tabheads"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="horizontal">
        </com.yuqianhao.view.TabLinearLayout>

    </com.yuqianhao.view.TabHorizontalScrollView>

    <android.support.v4.view.ViewPager
        android:id="@+id/viewpgaer"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"
        android:layout_alignParentEnd="true"
        android:layout_alignParentLeft="true"
        android:layout_alignParentRight="true"
        android:layout_alignParentStart="true"
        android:layout_below="@+id/tmpLayout" />
```
