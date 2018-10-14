package com.bwie.zk02moni.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.bwie.zk02moni.R;

public class InformationActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnReturn;
    private EditText etZhangHao;
    private EditText etNiCheng;
    private Button btnTuiChu;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        //1 控件
        btnReturn = findViewById(R.id.btn_return);
        etZhangHao = findViewById(R.id.et_zhanghao);
        etNiCheng = findViewById(R.id.et_nicheng);
        btnTuiChu = findViewById(R.id.btn_tuichu);

        sp = getSharedPreferences("islogin", MODE_PRIVATE);
        String username1 = sp.getString("username1", "");
        String token = sp.getString("token", "");

        etZhangHao.setText(username1);
        etNiCheng.setText(token);

        //2 事件监听
        btnReturn.setOnClickListener(this);
    }

    //2 事件监听
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_return://点击返回
                finish();
                break;

            case R.id.btn_tuichu://点击退出登录
                finish();
                break;
        }
    }

}
