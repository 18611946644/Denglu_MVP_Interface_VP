package com.bwie.zk02moni.utils;

import android.os.Handler;

import com.bwie.zk02moni.R;
import com.bwie.zk02moni.view.ICallBack;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 数据请求工具类
 * Created by DELL on 2018/10/13.
 */

public class OKHttputils {
    /**
     * 使用单例模式
     * */
    //第一步 单模式
    private static volatile OKHttputils instance;

    //引入
    private OkHttpClient client;//全局框架引入
    private Handler handler = new Handler();

    //第二步 私有化一个构造方法
    private OKHttputils(){
       client = new OkHttpClient();
    }

    //第三步 提供一个对外的构造方法
    public static OKHttputils getInstance(){
        if(instance == null){
            synchronized (OKHttputils.class){
                if(null == instance){
                    instance = new OKHttputils();
                }
            }
        }
        return instance;
    }




    /**
     * 1 登录 注册   使用一个方法请求网络数据
     * */
    public void get(String url, final ICallBack callBack, final Type type){
       //使用okhttp
        //第一步创建一个OKHttp对象 初始化在单例中

        //第二步创建一个请求方法
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        //第三步 通过okHttputils和request得到一个call
        Call call = client.newCall(request);
        //第四步 通过Call使用一个方法请求数据
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                //失败的方法
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onFailed(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //数据请求成功  进行数据解析
                String result = response.body().string();
                Gson gson = new Gson();
                final Object o = gson.fromJson(result, type);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onSuccess(o);
                    }
                });

            }
        });
    }


}
