package com.jewelermobile.gangfu.zdydemo1.api;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;


/**
 * Created by bjh on 2019/5/17.
 */

public abstract class JBHBaseRequest {

    private String UserId = "";        //  账号ID
    private int Device = 3;            //  1ios手机、2ios Pad、3安卓手机、4安卓Pad、5web手机、6web电脑、11WEB钻石批发商城、12WEB商城前端（非钻石）
    private long Time;                 //  当前时间戳
    private String VerId = "";              //  当前版本Id
    private String OnlineMark = "";    //  上线标识类似Token
    private String Sign = "";               //  sign=MD5(UserId|Device|Time|VerId|Key20190517|OnlineMark)


    /**
     * 构造方法
     *
     * @param Time
     */
    public JBHBaseRequest(long Time){
        this.Time=Time;
    }




    /**
     * 默认  加密的数据
     *
     * @return
     */
    protected StringBuilder getBaseMD5Sign(){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(UserId+"|"+Device+"|"+Time+"|"+VerId+"|"+ key+"|"+ OnlineMark);
        return stringBuilder;
    }



    /**
     * 加密串
     *
     * @return
     */
//    protected abstract String kStr();



    /**
     *  由子类  上传的数据
     *
     * @return
     *
     */
    protected abstract Map postMap(Map<String,Object> map);




    /**
     *
     * 需要上的 post 数据
     *
     * @return
     */
    public Map<String,Object> postMap(){
        Map<String,Object> map=new HashMap<>() ;
        map.put(USERID,UserId);
        map.put(DEVICE,Device);
        map.put(VERID,VerId);
        map.put(TIME,Time);
        map.put(ONLINEMARK,OnlineMark);
        map.put(SIGN, MD5Util.getMD5(getBaseMD5Sign().toString()));

//        if (TextUtils.isEmpty(kStr())){
//            //如果不需要累加其他的属性  那么直接使用初始的数据加密
//            map.put(SIGN, MD5Util.getMD5(getBaseMD5Sign().toString()));
//        }else {
//            //有子类累加后得到的秘串
//            map.put(SIGN, MD5Util.getMD5(kStr()));
//        }
        if (postMap(map)!=null){
            //为了防止子类传空数据造成请求失败
            map = postMap(map) ;
        }
        Log.v("=====time=====","e = "+new Gson().toJson(map));
        return map;
    }


    public RequestBody requestBody(){
        Map<String , Object> jsonContent=new HashMap<>();
        jsonContent.put("data",postMap());
        String content=new Gson().toJson(jsonContent);

        Log.v("=====time=====","content.Length = "+content.getBytes().length);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), content);
        try {
            Log.v("=====time=====","Length = "+requestBody.contentLength());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return requestBody;
    }




    //用户id
    public static final String USERID="UserId";
    //设备id
    public static final String DEVICE="Device";
    //版本id
    public static final String VERID="VerId";
    //时间戳
    public static final String TIME="Time";
    //在线标识
    public static final String ONLINEMARK="OnlineMark";
    public static final String SIGN="Sign";
    public static final String key="YlQ6iR#ox4BEqP4vchhI3_YHWFNFysQU";

}
