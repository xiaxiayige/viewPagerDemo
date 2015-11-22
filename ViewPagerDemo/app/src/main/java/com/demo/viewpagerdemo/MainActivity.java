package com.demo.viewpagerdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends FragmentActivity {

    private LinearLayout llinearlayout;
    private TextView tab1, tab2, tab3;
    private View line;
    private ViewPager viewpager;

    private List<Fragment> fragments;

    private FragmentViewPagerAdapter adapter;

    private int lineWidth = 0;  //偏移宽度 or line宽度

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        // TODO Auto-generated method stub
        fragments = new ArrayList<Fragment>();
        fragments.add(MyFragment.newInstance(1));
        fragments.add(MyFragment.newInstance(2));
        fragments.add(MyFragment.newInstance(3));
        fragments.add(MyFragment.newInstance(4));
        fragments.add(MyFragment.newInstance(5));

        adapter = new FragmentViewPagerAdapter(getSupportFragmentManager(), fragments);

        viewpager.setAdapter(adapter);

        int screenWith = getWindow().getWindowManager().getDefaultDisplay().getWidth();
        //计算line 长度
        ViewGroup.LayoutParams lp = line.getLayoutParams();
        lineWidth = screenWith / fragments.size();
        lp.width = lineWidth;
        line.setLayoutParams(lp);
        line.setTag("0");

        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            private boolean isScroll = false;
            private int pos = 0;
            int last = 0;

            @Override
            public void onPageScrolled(int i, float v, int i1) {
                if (isScroll && v != 0) {
                    if (pos >= 1) {
                        if(i<pos){//向左划
                            float offset=v * lineWidth;
                            Log.i("setOnPageChangeListener", "lineWidth = " + lineWidth + " offset = " + offset + " v = " + v);
                            if(pos>1){
                                line.setTranslationX(lineWidth * i + v * lineWidth);
                            }else{
                                line.setTranslationX(v * lineWidth);
                            }
                        }else{ //向右滑动
                            line.setTranslationX(lineWidth * pos + v * lineWidth);
                        }
                    } else {
                        line.setTranslationX(v * lineWidth);

                    }
                }
            }

            @Override
            public void onPageSelected(int i) {
                line.setTranslationX(i * lineWidth);
                pos = i;
            }

            @Override
            public void onPageScrollStateChanged(int status) {
                if (status == 0) { //0 停止
                    line.setTranslationX(pos * lineWidth);
                } else if (status == 1) {  //开始滑动 调用 onPageSelected
                    isScroll = true;
                } else if (status == 2) {  // 只要有onPageScrollStateChanged == 2 才有可能触发onPageSelected  分界状态
                    isScroll = false;
                    line.setTranslationX(pos * lineWidth);
                }
            }
        });

    }

    private void initView() {
        // TODO Auto-generated method stub
        viewpager = (ViewPager) findViewById(R.id.viewpager);

        llinearlayout = (LinearLayout) findViewById(R.id.llinearlayout);
        tab1 = (TextView) findViewById(R.id.tab1);
        tab2 = (TextView) findViewById(R.id.tab2);
        tab3 = (TextView) findViewById(R.id.tab3);
        line = findViewById(R.id.line);
    }


}
