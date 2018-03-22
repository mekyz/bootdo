package com.bootdo.utils;

import com.bootdo.api.domain.VCodeBean;
import com.bootdo.business.domain.Location;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/*
 * 项目名:    bootdo
 * 包名       com.bootdo.utils
 * 文件名:    Utils
 * 创建者:    ZSY
 * 创建时间:  2018/3/11 on 10:45
 * 描述:     TODO
 */
public class Utils {
    private static Logger logger = LoggerFactory.getLogger(Utils.class);

    //设置好账号的ACCESS_KEY和SECRET_KEY
    private static String accessKey = "aAiJNMRS-kU1GJBurcp5XioUR5X4GPL9n8-Vy7Kq"; //这两个登录七牛 账号里面可以找到
    private static String secretKey = "yo3Qzzbg7IKFD8VG7BmUZHWiFw2sQA_eqbT_dCZz";
    private static String address = "http://oweq6in8r.bkt.clouddn.com/";

    //要上传的空间
    private static String bucketname = "blogimages"; //对应要上传到七牛上 你的那个路径（自己建文件夹 注意设置公开）

    /**
     * 获取上传凭证
     */
    public static String getUploadCredential() {
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucketname);
        System.out.println(upToken);
        return upToken;
    }

    /**
     * 文件上传
     *
     * @return
     */
    public static String qiNiuUpload(InputStream is) {
        // 构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        // ...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        try {
            Response response = uploadManager.put(is, null, getUploadCredential(), null, null);
            // 解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            return address + putRet.hash;
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
            }
        }
        return null;
    }

    /**
     * 根据地址获取经纬度
     *
     * @param address 地址
     * @return
     */
    public static Location getLatLng(String address) {
        String ak = "1oIGRWVoNwBZUwLnjIM4Sy0s2ofqRvwr";
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("http://api.map.baidu.com/geocoder/v2/?address=" + address + "&output=json&ak=" + ak)
                    .build();
            Call call = client.newCall(request);
            okhttp3.Response response = call.execute();
            String latlng = response.body().string();
            return new Gson().fromJson(latlng, Location.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static final double EARTH_RADIUS = 6378137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 根据两点经纬度计算距离
     *
     * @param lon1
     * @param lat1
     * @param lon2
     * @param lat2
     * @return 单位M
     */
    public static double getDistance(double lon1, double lat1, double lon2, double lat2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lon1) - rad(lon2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1)
                * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        return Math.floor(s);
    }

    /**
     * 保留两位小数
     */
    public static double formatDouble(double d) {
        DecimalFormat df = new DecimalFormat("#.00");
        return Double.parseDouble(df.format(d));
    }

    /**
     * 聚合数据 获取验证码
     * 发送短信
     *
     * @return
     */
    public static String getVCode(String phone) {
        String url = "http://v.juhe.cn/sms/send";//请求接口地址
        try {
            OkHttpClient client = new OkHttpClient();
            String randomCode = randomCode();
            String encode = URLEncoder.encode("#code#=" + randomCode);
            Request request = new Request.Builder()
                    .url(url + "?mobile=" + phone + "&tpl_id=67476&tpl_value=" + encode +
                            "&key=4538e0b9391867172e3f5605b10d817f")
                    .build();
            Call call = client.newCall(request);
            okhttp3.Response response = call.execute();
            String result = response.body().string();
            VCodeBean codeBean = new Gson().fromJson(result, VCodeBean.class);
            if (codeBean.getError_code() == 0) {
                return randomCode;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String randomCode() {
        Random d = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int num = d.nextInt(10);
            sb.append(num);
        }
        return sb.toString();
    }

    public static String yyyyMMddHHmm() {
        SimpleDateFormat yyyyMMddHHmm = new SimpleDateFormat("yyyyMMddHHmm");
        return yyyyMMddHHmm.format(new Date(System.currentTimeMillis()));
    }
}
