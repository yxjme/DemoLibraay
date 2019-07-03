package com.jewelermobile.gangfu.zdydemo1;

import android.util.Base64;
import android.util.Log;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

/**
 * Created by LBC on 2017/4/25.
 */
public class CodeCUntil {

	public static String REQUEST_KEY = "5hV9c41n";
	public static String RESPONSE_KEY = "81S2c6r1";

    /**解密
     * 获取编码后的值   *
     * @param key   *
     * @param data   *
     * @return   *
     * @throws Exception
     */
    public static String decodeValue(String key, String data) {
        byte[] datas;
        String value = null;
        try {
            datas = decode(key, Base64.decode(data.getBytes(),Base64.DEFAULT));
            value = new String(datas);
        } catch (Exception e) {
            value = "";
        }
        return value;
    }

    /**
     * DES算法，加密   *   *
     * @param data 待加密字符串   *
     * @param key 加密私钥，长度不能够小于8位   *
     * @return 加密后的字节数组，一般结合Base64编码使用   *
     */
    public static String encodevalue(String key, String data) throws Exception {
        return encode(key, data.getBytes());
    }



    /**
     * DES算法，加密   *   *
     * @param data 待加密字符串   *
     * @param key 加密私钥，长度不能够小于8位   *
     * @return 加密后的字节数组，一般结合Base64编码使用   *
     */
    public static String encode(String key, byte[] data) throws Exception {
        try {
            DESKeySpec dks = new DESKeySpec(key.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");    //key的长度不能够小于8位字节
            Key secretKey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(key.getBytes());
            AlgorithmParameterSpec paramSpec = iv;
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, paramSpec);
            byte[] bytes = cipher.doFinal(data);       //   return byte2hex(bytes);
            return new String(Base64.encode(bytes,Base64.DEFAULT));
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * DES算法，解密   *
     * * @param data 待解密字符串
     * * @param key 解密私钥，长度不能够小于8位   *
     * @return 解密后的字节数组   *
     * @throws Exception 异常
     */
    private static byte[] decode(String key, byte[] data) throws Exception {
        try {
            DESKeySpec dks = new DESKeySpec(key.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");    //key的长度不能够小于8位字节
            Key secretKey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(key.getBytes());
            AlgorithmParameterSpec paramSpec = iv;
            cipher.init(Cipher.DECRYPT_MODE, secretKey, paramSpec);
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }




    public static String s="{\"mcu\":\"1110002\",\"kh\":\"R00251D\",\"mat\":\"18K白\",\"dzkzlx\":null,\"dzkzzt\":null,\"size\":\"11\",\"xq\":null,\"goldWeight\":\"6.52\",\"gy\":null,\"zsct\":null,\"zsp\":null,\"fsct\":null,\"fsp\":null,\"kzContent\":null,\"kzContentAppend\":null,\"jzfwBegin\":null,\"jzfwEnd\":null,\"hand\":null,\"finger\":null,\"dzDingJing\":0,\"mode\":2,\"stones\":[{\"zhengshu\":\"GIA\",\"zhengshuhao\":\"LBC2632153\",\"jd\":\"VS\",\"ys\":\"F\",\"qg\":null,\"paog\":null,\"duicen\":null,\"shape\":\"圆形\",\"yg\":null,\"zhekou\":null,\"rmk\":\"测试裸石\",\"sourceIn\":\"港福测试数据\",\"zjl\":null,\"zhonglian\":0.3,\"price\":3000.5}]}";


    /**
     * 获取加密的文件
     *
     * @return
     * @throws Exception
     */
    public static String getEncodevalue() throws Exception {
        return encodevalue("5hV9c41n",s);
    }


    /**
     * 获取解密后的内容
     * @return
     * @throws Exception
     */
    public static String getDecodeValue() throws Exception {
        return decodeValue("5hV9c41n",encodevalue("5hV9c41n", s));
    }
}
