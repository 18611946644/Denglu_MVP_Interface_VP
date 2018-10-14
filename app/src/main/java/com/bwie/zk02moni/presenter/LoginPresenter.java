package com.bwie.zk02moni.presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.bumptech.glide.load.resource.bitmap.BitmapEncoder;
import com.bwie.zk02moni.bean.LoginBean;
import com.bwie.zk02moni.model.LoginModel;
import com.bwie.zk02moni.view.ICallBack;
import com.bwie.zk02moni.view.IView;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * 登录的逻辑P层
 * Created by DELL on 2018/10/14.
 */

public class LoginPresenter {

    //2 绑定接口
    private IView iv;

    //登录对象初始化
    private LoginModel loginModel;

    //3 绑定事件
    public void attach(IView iv){
       this.iv = iv;
       //实例化登录对象
        loginModel = new LoginModel();
    }

    //将v 和 m  解绑
    public void datach(){
        if(iv != null){
            iv=null;
        }
    }

    //1  登录的方法
    public void login(String url,String username,String password){

        //访问接口  进行拼接
        url = url.concat("?mobile=").concat(username).concat("&password=").concat(password);
        //初始化Type 泛型
        Type type = new TypeToken<LoginBean>(){}.getType();
        //调用登录方法  并实现方法
        loginModel.login(url, new ICallBack() {
            @Override
            public void onSuccess(Object obj) {
               //解析完成
                iv.success(obj);

                LoginBean bean = (LoginBean) obj;
                if (bean.getCode().equals("0")){
                    String username1 = bean.getData().getUsername();
                    String token = bean.getData().getToken();
                    SharedPreferences sp = iv.getsp().getSharedPreferences("islogin", Context.MODE_PRIVATE);
                    sp.edit().putString("username1",username1)
                            .putString("token",token)
                            .commit();

                    iv.gotoInformation();

                }

            }

            @Override
            public void onFailed(Exception e) {
               iv.failed(e);
            }
        },type);
    }

}
