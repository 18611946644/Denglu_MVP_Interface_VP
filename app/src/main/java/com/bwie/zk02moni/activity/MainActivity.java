package com.bwie.zk02moni.activity;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bwie.zk02moni.R;
import com.bwie.zk02moni.fragment.HomeFragment;
import com.bwie.zk02moni.fragment.MeFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager vp;
    private TextView txtHome;
    private TextView txtme;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //1 控件
        getinit();
        //2 设置点击事件监听
        setOnClick();

        //3 为vp添加一个适配器
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        });
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setChangeColor(position); //颜色改变
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    //颜色改变
    private void setChangeColor(int position) {
       txtHome.setTextColor(position==0? Color.RED:Color.BLACK);
       txtme.setTextColor(position==1? Color.RED:Color.BLACK);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txt_home://首页
                vp.setCurrentItem(0);
                setChangeColor(0);
                break;

            case R.id.txt_me://我的
                vp.setCurrentItem(1);
                setChangeColor(1);
                break;
        }
    }

    //2 设置点击事件监听
    private void setOnClick() {
        txtHome.setOnClickListener(this);
        txtme.setOnClickListener(this);
    }

    //1 控件
    private void getinit() {
        vp = findViewById(R.id.vp);
        txtHome = findViewById(R.id.txt_home);
        txtme = findViewById(R.id.txt_me);

        //创建一个集合  用来存放Fragment
        fragmentList = new ArrayList<>();
        //添加
        fragmentList.add(new HomeFragment());
        fragmentList.add(new MeFragment());

        //设置默认事件
        txtHome.setTextColor(Color.RED);
        txtme.setTextColor(Color.BLACK);
    }

}
