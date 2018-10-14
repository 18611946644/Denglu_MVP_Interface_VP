package com.bwie.zk02moni.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.zk02moni.R;
import com.bwie.zk02moni.bean.ZhuCeBean;
import com.bwie.zk02moni.presenter.ZhuCePresenter;
import com.bwie.zk02moni.view.IView;

public class ZhuCeActivity extends AppCompatActivity implements View.OnClickListener,IView<ZhuCeBean> {

    private Button btnReturn;
    private EditText etPhono;
    private EditText etPassword;
    private EditText etPassword2;
    private TextView btnZhuCe;
    private ZhuCePresenter zhuCePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu_ce);
        //1 控件
        btnReturn = findViewById(R.id.btn_return);//返回
        etPhono = findViewById(R.id.et_phono);//手机号
        etPassword = findViewById(R.id.et_password);//密码
        etPassword2 = findViewById(R.id.et_password2);//请确认密码
        btnZhuCe = findViewById(R.id.btn_zhuce);//注册

        //3 初始化一个逻辑层Presenter
        zhuCePresenter = new ZhuCePresenter();
        zhuCePresenter.attach(this);

        //2 事件监听
        btnReturn.setOnClickListener(this);
        btnZhuCe.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_return:
                finish();
                break;

            case R.id.btn_zhuce:
                //首先得到用户注册的账号密码
                String Phono = etPhono.getText().toString().trim();
                String Password = etPassword.getText().toString().trim();
                String Password2 = etPassword2.getText().toString().trim();

                //首先判断输入的两个密码是否一致 再决定是否调用逻辑判断层
                if(Password.equals(Password2)){
                    //调用P层逻辑判断
                    zhuCePresenter.zhuce("https://www.zhaoapi.cn/user/reg",Phono,Password);
                }else{
                    Toast.makeText(this,"对不起！您输入的密码不一致,请确认后再行注册！",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    //实现接口  实现成功与失败的方法
    @Override
    public void success(ZhuCeBean zhuCeBean) {
        if(zhuCeBean != null){
           if(zhuCeBean.getCode().equals(0)){
               Toast.makeText(this,zhuCeBean.getMsg(),Toast.LENGTH_LONG).show();
               //点击注册跳转到登录界面
               Intent intent = new Intent(ZhuCeActivity.this,LoginActivity.class);
               startActivity(intent);
           }else{
               Toast.makeText(this,zhuCeBean.getMsg(),Toast.LENGTH_LONG).show();
           }
        }
    }

    @Override
    public void failed(Exception e) {
        Toast.makeText(this,"网络异常",Toast.LENGTH_LONG).show();
    }

    @Override
    public void gotoInformation() {
        Intent intent = new Intent(ZhuCeActivity.this,LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public Context getsp() {
        return this;
    }
}
