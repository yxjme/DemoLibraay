package com.jewelermobile.gangfu.zdydemo1.api;

import android.util.Log;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiSever {

    private static OkHttpClient.Builder httpClient=new OkHttpClient.Builder();


    static Retrofit.Builder builder=new Retrofit.Builder()
            .baseUrl(Api.HOST)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create());


    /**
     * @param serviceClass
     * @param <S>
     * @return
     */
    public synchronized static <S> S createService(Class<S> serviceClass) {
        httpClient .interceptors().add(new HttpInterceptor());
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);
    }

    /**
     *
     */
    public static class HttpInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request.Builder builder = chain.request().newBuilder();
            Request request = builder
                    .addHeader("Content-type", "application/json")
                    .addHeader("Accept", "application/json").build();
            return chain.proceed(request);
        }
    }



    /**
     * 请求入口
     *
     * @return
     */
    public static ApiInterface getApiService(){
        ApiInterface apiServer = createService(ApiInterface .class);
        return apiServer;
    }




    /**
     * @param url
     * @param map
     */
    public static void post(String url, Map<String,Object> map, final ProgressListener progressListener ){

        HashMap<String,Object> hashMap=new HashMap<>();
        hashMap.put("data",map);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),new Gson().toJson(hashMap));

        OkHttpClient okHttpClient =new  OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Response response = chain.proceed(chain.request());
                        response.newBuilder().body(new ProgressResponseBody(response.body(),progressListener)).build();
                        return response;
                    }
                }).build();

        Request request=new Request.Builder()
                .url(Api.HOST+url)
                .post(requestBody)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Log.v("=====http=====","onFailure = "+e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.v("=====http=====","onResponse = "+new String(response.body().bytes()));
            }
        });
    }




    /**
     * @param url
     */
    public static void get(String url,final ProgressListener progressListener ){
        OkHttpClient okHttpClient =new  OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Response response = chain.proceed(chain.request());
                        response.newBuilder().body(new ProgressResponseBody(response.body(),progressListener)).build();
                        return response;
                    }
                }).build();

        Request request=new Request.Builder()
                .url(url)
                .get()
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                Log.v("=====http=====","onFailure = "+e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.v("=====http=====","onResponse = "+new String(response.body().bytes()));
            }
        });
    }



    public interface ProgressListener {
        //已完成的 总的文件长度 是否完成
        void onProgress(long currentBytes, long contentLength);
    }


    public static class ProgressResponseBody extends ResponseBody {

        ResponseBody body;
        ProgressListener listener;
        private BufferedSource bufferedSource;

        public ProgressResponseBody( ResponseBody body,ProgressListener listener){
            this.body=body;
            this.listener=listener;
        }

        @Override
        public MediaType contentType() {
            return body.contentType();
        }

        @Override
        public long contentLength() {
            return body.contentLength();
        }

        @Override
        public BufferedSource source() {
            if (bufferedSource==null){
                bufferedSource = Okio.buffer(mySource(body.source()));
            }
            return bufferedSource;
        }


        private ForwardingSource mySource(BufferedSource source) {
            ForwardingSource forwardingSource = new ForwardingSource(source) {
                long totalBytesRead = 0L;

                @Override
                public long read(Buffer sink, long byteCount) throws IOException {
                    long bytesRead = super.read(sink, byteCount);
                    totalBytesRead += bytesRead !=-1 ? bytesRead : 0;
                    //发送消息到主线程，ProgressModel为自定义实体类
                    listener.onProgress(totalBytesRead,contentLength());
                    return bytesRead;
                }
            };
            return  forwardingSource;
        }
    }
}
