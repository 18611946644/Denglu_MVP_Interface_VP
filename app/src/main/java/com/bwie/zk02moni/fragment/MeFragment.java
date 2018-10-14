package com.bwie.zk02moni.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.zk02moni.R;
import com.bwie.zk02moni.activity.LoginActivity;

/**
 * Created by DELL on 2018/10/13.
 */

public class MeFragment extends Fragment implements View.OnClickListener {

    private ImageView img_touxiang;
    private TextView txtDengLu;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.me_fragment,null,false);
        //1 控件
        img_touxiang = v.findViewById(R.id.img_touxiang);//头像
        txtDengLu = v.findViewById(R.id.txt_denglu);//登录按钮

        //2 设置点击事件
        img_touxiang.setOnClickListener(this);
        txtDengLu.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.img_touxiang://点击头像
               Intent intent = new Intent(getActivity(), LoginActivity.class);
               startActivity(intent);
               break;

           case R.id.txt_denglu://点击登录
               Intent intent2 = new Intent(getActivity(), LoginActivity.class);
               startActivity(intent2);
               break;
       }
    }
}
