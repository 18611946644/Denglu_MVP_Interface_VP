package com.bwie.zk02moni.model;

import com.bwie.zk02moni.utils.OKHttputils;
import com.bwie.zk02moni.view.ICallBack;

import java.lang.reflect.Type;

/**
 * 注册时的网络请求方法
 * Created by DELL on 2018/10/14.
 */

public class ZhuCeModel {


    //1 注册方法
    public void zhuce(String url, ICallBack callBack, Type type){
        //调用工具类
        OKHttputils.getInstance().get(url,callBack,type);
    }

}
