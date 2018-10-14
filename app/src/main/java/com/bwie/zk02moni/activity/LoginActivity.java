package com.bwie.zk02moni.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.zk02moni.R;
import com.bwie.zk02moni.bean.LoginBean;
import com.bwie.zk02moni.presenter.LoginPresenter;
import com.bwie.zk02moni.view.IView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,IView<LoginBean> {

    private Button btnReturn;
    private EditText etPhono;
    private EditText etPassword;
    private Button btnLogin;
    private TextView txtZhuCe;
    private LoginPresenter presenter;
    private SharedPreferences sp;
    private Button btnlogin0;
    private String mobile;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //1 控件
        btnReturn = findViewById(R.id.btn_return);//返回
        etPhono = findViewById(R.id.et_phono);//手机号
        etPassword = findViewById(R.id.et_password);//密码
        btnLogin = findViewById(R.id.btn_login);//登录
        txtZhuCe = findViewById(R.id.txt_zhuce);//注册

        //3 关联Presenter层
        presenter = new LoginPresenter();
        presenter.attach(this);

        //4 使用SP
        sp = getSharedPreferences("islogin", MODE_PRIVATE);

        //2 事件监听
        btnReturn.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        txtZhuCe.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_return://点击返回
                finish();
                break;

            case R.id.btn_login://点击登录
                //1 首先得到用户输入的值
                //用户输入手机号
                mobile = etPhono.getText().toString().trim();
                //用户输入的密码
                password = etPassword.getText().toString();

                //得到之后将数据传给逻辑P层
                presenter.login("https://www.zhaoapi.cn/user/login", mobile, password);
                break;

            case R.id.txt_zhuce://点击注册
                //别忘了  进行判断  如果
                Intent intent2 = new Intent(LoginActivity.this,ZhuCeActivity.class);
                startActivity(intent2);
                finish();
                break;
        }
    }


    //实现的两个登录成功与失败的方法
    @Override
    public void success(LoginBean loginBean) {
        //判断是否登录成功
        if(loginBean != null){
            String code = loginBean.getCode();
            if(code.equals("0")){
                Toast.makeText(this,loginBean.getMsg(),Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this,loginBean.getMsg(),Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void failed(Exception e) {
       //网络请求失败 情况下
        Toast.makeText(this,"网络请求失败",Toast.LENGTH_LONG).show();
    }

    //跳转 到信息界面方法
    @Override
    public void gotoInformation() {
        Intent intent = new Intent(LoginActivity.this,InformationActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public Context getsp() {
        return this;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter != null){
            presenter.datach();
        }
    }
}
