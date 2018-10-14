package com.bwie.zk02moni.view;

import android.content.Context;

/**
 * 请求成功与失败的连个方法  接口
 * Created by DELL on 2018/10/14.
 */

public interface IView<T> {

    //请求方法
    void success(T t);
    void failed(Exception e);

    //跳转方法
    void gotoInformation();//信息展示界面

    Context getsp();

}
