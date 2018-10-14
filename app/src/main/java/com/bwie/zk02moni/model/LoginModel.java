package com.bwie.zk02moni.model;

import com.bwie.zk02moni.utils.OKHttputils;
import com.bwie.zk02moni.view.ICallBack;

import java.lang.reflect.Type;

/**
 * 网络请求数据登录M层
 * Created by DELL on 2018/10/14.
 */

public class LoginModel {

    //1 做一个网络请求数据  登录方法
    public void login(String url, ICallBack callBack, Type type){
         //调用工具类  得到解析数据
        OKHttputils.getInstance().get(url,callBack,type);
        //去p层调用请求

    }

}
