package com.example.jinli.testopenpluginproject.okhttp;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpUtils {

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    private static OkHttpClient client =  null ;


    public static OkHttpClient getInstance(){
        if(client== null){
            synchronized (OkHttpUtils.class){
                if(client== null){
                    client =  new OkHttpClient();
                }
            }
        }
        return client;
    }


    /**
     * 请求获取信息
     * @param url
     * @return
     */
    public String  getByUrl(String url){
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 通过 post 请求获取 信息
     * @param url
     * @param json
     * @return
     * @throws Exception
     */
    public String  getResponseByPost(String url, String json) throws Exception{

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();

    }
}
