package com.bwie.zk02moni.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bwie.zk02moni.R;
import com.bwie.zk02moni.activity.MainActivity;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2018/10/13.
 */

public class HomeFragment extends Fragment {
    public static final int FLAG = 123;
    public int QRFLAG = 1234;
    private TextView txtSao;
    private ViewPager vp2;
    private EditText et_shuru;
    private Button btn_shengcheng;
    private List<Integer> fragmentList;

    //handler发送
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == FLAG){
                int item = vp2.getCurrentItem();
                if(item <fragmentList.size()-1){
                    item++;
                }else{
                    item=0;
                }
                vp2.setCurrentItem(item);
                sendEmptyMessageDelayed(FLAG,2000);
            }

        }
    };
    private ImageView imageView;
    private Bitmap mBitmap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_fragment,null,false);
        //控件
        txtSao = v.findViewById(R.id.txt_sao);//扫一扫
        vp2 = v.findViewById(R.id.vp2);//轮播
        et_shuru = v.findViewById(R.id.et_shuru);//输入生成二维码字符串
        btn_shengcheng = v.findViewById(R.id.btn_shengcheng);//按钮生成
        imageView = v.findViewById(R.id.img);//二维码
        ZXingLibrary.initDisplayOpinion(getActivity());
        return v;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /**
         * 处理二维码扫描结果
         */
        if (requestCode == QRFLAG) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(getActivity(), "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getActivity(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    //逻辑
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        
        
        //创建一个List  用来存放图片
        fragmentList = new ArrayList<>();
        //添加图片
        fragmentList.add(R.drawable.a1);
        fragmentList.add(R.drawable.a2);
        fragmentList.add(R.drawable.a3);
        fragmentList.add(R.drawable.a4);
        fragmentList.add(R.drawable.a5);
        fragmentList.add(R.drawable.a6);

        //添加vp2适配器
        vp2.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                ImageView img = new ImageView(getActivity());
                Glide.with(getActivity()).load(fragmentList.get(position)).into(img);
                container.addView(img);
                return img;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View) object);
            }
        });
        handler.sendEmptyMessageDelayed(FLAG,2000);

        //3 点击扫一扫
        txtSao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent,QRFLAG);
            }
        });



        //2 生成二维码
        btn_shengcheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //得到用户输入的字符串
                String textContent = et_shuru.getText().toString();
                if(TextUtils.isEmpty(textContent)){
                    //为空提示
                    Toast.makeText(getActivity(), "您的输入为空!", Toast.LENGTH_SHORT).show();
                    return;
                }
                et_shuru.setText("");
                mBitmap = CodeUtils.createImage(textContent, 400, 400, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                imageView.setImageBitmap(mBitmap);
            }
        });
        
    }
    
    
}
