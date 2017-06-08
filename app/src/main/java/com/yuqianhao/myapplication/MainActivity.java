package com.yuqianhao.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuqianhao.stdlib.YWindow;
import com.yuqianhao.view.TabHorizontalScrollView;
import com.yuqianhao.view.TabLinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private ArrayList<Fragment> mArrayList;
    private FragmentPagerAdapter mFragmentPagerAdapter;
    private TabHorizontalScrollView mHorizontalScrollView;
    private TabLinearLayout mViewPagerHeadLayout;

    private Handler mHandler=new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mArrayList=new ArrayList<>();
        mViewPager= (ViewPager) findViewById(R.id.viewpgaer);
        mHorizontalScrollView= (TabHorizontalScrollView) findViewById(R.id.tmpLayout);
        mViewPagerHeadLayout= (TabLinearLayout) findViewById(R.id.tabheads);
        mViewPagerHeadLayout.setOnScrollSelect(new TabLinearLayout.OnScrollSelect() {
            @Override
            public void onScrollSelect(int index) {
                /**
                 * 回调事件，当点击顶部TAB时触发的事件，参数index代表点击的第几个TAB
                 * */
                mViewPager.setCurrentItem(/**前面为了对称加了两个空的TextView*/index-2,true);
            }
        });
        mHorizontalScrollView.setScrollChangedListener(new TabHorizontalScrollView.OnScrollChanged() {
            @Override
            public void onScrollChanged(int l) {
                /**
                 * 当滑动结束后的偏移量
                 * */
                final int mTabWidth=YWindow.getWindowDisplayMetrics(MainActivity.this).widthPixels/5;
                final int index=l/mTabWidth;
                if((l%mTabWidth)>(mTabWidth/2)){
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mHorizontalScrollView.smoothScrollTo((index+1)*mTabWidth,0);
                            mViewPager.setCurrentItem(index+1,false);
                        }
                    });
                }else{
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            mHorizontalScrollView.smoothScrollTo((index)*mTabWidth,0);
                            mViewPager.setCurrentItem(index,false);
                        }
                    });
                }
            }
        });
        mFragmentPagerAdapter=new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mArrayList.get(position);
            }

            @Override
            public int getCount() {
                return mArrayList.size();
            }
        };
        mViewPager.setAdapter(mFragmentPagerAdapter);
        for(int i=0;i<14;i++){
            mArrayList.add(new MyFragment("第 "+i+" 个Fragment！"));
            mFragmentPagerAdapter.notifyDataSetChanged();
        }
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int width=YWindow.getWindowDisplayMetrics(MainActivity.this).widthPixels/5;
                /**
                 * 设置HorizontalScrollView带动画的滑动
                 * */
                mHorizontalScrollView.smoothScrollTo((int) ((positionOffset*width)+(position*width)),0);
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPagerHeadLayout.addView(createViewView(""));
        mViewPagerHeadLayout.addView(createViewView(""));
        for(int i=0;i<14;i++){
            mViewPagerHeadLayout.addView(createViewView(i+""));
        }
        mViewPagerHeadLayout.addView(createViewView(""));
        mViewPagerHeadLayout.addView(createViewView(""));
    }

    TextView createViewView(String text){
        TextView textView=new TextView(this);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.WHITE);
        textView.setText(text);
        LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(-1,-1);
        lp.width=YWindow.getWindowDisplayMetrics(this).widthPixels/5;
        textView.setLayoutParams(lp);
        return textView;
    }

}
class MyFragment extends Fragment{
    private TextView mTextView;
    private String mText;

    public MyFragment(String title){
        mText=title;
    }

    public MyFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment,null,false);
        mTextView= (TextView) rootView.findViewById(R.id.textView);
        mTextView.setText(mText==null?"TextViewID="+mTextView.getId():mText);
        return rootView;
    }
}
