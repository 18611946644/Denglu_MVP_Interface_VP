package com.bwie.zk02moni.presenter;

import com.bwie.zk02moni.bean.ZhuCeBean;
import com.bwie.zk02moni.model.ZhuCeModel;
import com.bwie.zk02moni.view.ICallBack;
import com.bwie.zk02moni.view.IView;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * 注册的逻辑操作哦层
 * Created by DELL on 2018/10/14.
 */

public class ZhuCePresenter {

    //2 需要绑定接口
    private IView iv;

    //4 创建一个Modle层
    private ZhuCeModel zhuCeModel;

    //3 一个attach绑定方法
    public void attach(IView iv){
        this.iv = iv;
        zhuCeModel = new ZhuCeModel();
    }


    //1 写一个注册的方法
    public void zhuce(String url,String mobile,String password){
        //2 初始化一个泛型
        Type type = new TypeToken<ZhuCeBean>(){}.getType();
        //3 接口拼接
        url = url.concat("?mobile=").concat(mobile).concat("&password=").concat(password);
        //1 调用model层方法
        zhuCeModel.zhuce(url, new ICallBack() {
            @Override
            public void onSuccess(Object obj) {
               //调用成方法
                iv.success(obj);
            }

            @Override
            public void onFailed(Exception e) {
               iv.failed(e);
            }
        },type);
    }

}
