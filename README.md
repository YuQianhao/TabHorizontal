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
        /**注意要指定为纵向的布局模式*/
        android:scrollbars="horizontal">

        <com.yuqianhao.view.TabLinearLayout
            android:id="@+id/tabheads"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            /**注意要指定为纵向的布局模式*/
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
### 2、在Java中引用这三个控件
```java
ViewPager mViewPager;//ViewPager的引用
TabHorizontalScrollView mHorizontalScrollView;//滑动父布局引用
TabLinearLayout mViewPagerHeadLayout;//Tab标签父布局引用
/**----------初始化代码不写了-----------*/
private void init(){
    /**
      *首先给滑动父布局TabHorizontalScrollView设置一个滑动结束后的监听器，使用接口类TabHorizontalScrollView.OnScrollChanged完成
      *  接口类方法：onScrollChanged(int l)
      *          方法中的回传参数是从手指开始触摸View滑动到送开手指之后View滑动的偏移量
      */
    mHorizontalScrollView.setScrollChangedListener(new TabHorizontalScrollView.OnScrollChanged() {
            @Override
            public void onScrollChanged(int l) {
                //首先获取头布局每一个Tab标签的宽度，假设头布局每次要显示5个标签，则设置为屏幕宽度的1/5
                final int mTabWidth=mHorizontalScrollView.getWindowWidth()/5;
                //计算当前正在操作的Tab的索引，使用偏移量/Tab宽度获取索引编号
                final int index=l/mTabWidth;
                //偏移距离和Tab标签的宽度的1/2进行对比，如果偏移距离>Tab标签的1/2，那就可以判定划过了一半，可以直接划过去
                //如果偏移距离<Tab标签的一半，则可以判定滑动失败，就回弹到刚才的位置
                if((l%mTabWidth)>(mTabWidth/2)){
                //注意初始化Handler
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            //调用HorizontalScrollView的smoothScrollTo方法传入滑动偏移量进行滑动
                            //index标志为当前的索引，如果用户滑动判定成功则意味着直接滑到下一个Tab标签，所以index+1
                            //smoothScrollTo方法参数1表示滑动的距离，所以使用下一个索引*Tab的宽度
                            //高度滑动默认为0即可
                            mHorizontalScrollView.smoothScrollTo((index+1)*mTabWidth,0);
                            mViewPager.setCurrentItem(index+1,false);
                        }
                    });
                }else{
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            //调用HorizontalScrollView的smoothScrollTo方法传入滑动偏移量进行滑动
                            //index标志为当前的索引，如果用户滑动判定失败则回弹刚刚所在的Tab标签的位置，直接填入index即可
                            //smoothScrollTo方法参数1表示滑动的距离，所以使用下一个索引*Tab的宽度
                            //高度滑动默认为0即可
                            mHorizontalScrollView.smoothScrollTo((index)*mTabWidth,0);
                            mViewPager.setCurrentItem(index,false);
                        }
                    });
                }
            }
        });
}
```
