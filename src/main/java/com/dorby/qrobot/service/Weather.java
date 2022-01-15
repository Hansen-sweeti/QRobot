package com.dorby.qrobot.service;

import com.alibaba.fastjson.JSON;
import com.dorby.qrobot.utils.JsonUtil;
import com.dorby.qrobot.vo.RespBeanWeather;
import com.dorby.qrobot.vo.data.WeatherData;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Author: dorby
 * @Description:
 * @Date: 2022/1/11 18:05
 */
@Slf4j
public class Weather {
    private static final String url="https://api.muxiaoguo.cn/api/tianqi?api_key=d773bd2547d5dfcc&type=1&city=";
    public static String weather(String city) throws IOException {
        HttpURLConnection connection = null;
        String cit="重庆";
        String httpurl=new String(url);
        if(!city.isEmpty()){
            httpurl+=city;
        }else{
            httpurl+=cit;
        }
        System.out.println(httpurl);
        InputStream is = null;
        BufferedReader br = null;
        String result = null;// 返回结果字符串
        // 创建远程url连接对象
        URL url = new URL(httpurl);
        // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
        connection = (HttpURLConnection) url.openConnection();
        // 设置连接方式：get
        connection.setRequestMethod("GET");
        // 发送请求
        connection.connect();
        // 通过connection连接，获取输入流
        if (connection.getResponseCode() == 200) {
            JsonUtil jsonUtil=JsonUtil.getInstance();
            is = connection.getInputStream();
            // 封装输入流is，并指定字符集
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            // 存放数据
            StringBuffer sbf = new StringBuffer();

            String temp = null;
            while ((temp = br.readLine()) != null) {
                sbf.append(temp);
                sbf.append("\r\n");
            }
            result = sbf.toString();
            RespBeanWeather respBeanWeather=(RespBeanWeather) JSON.parseObject(result,RespBeanWeather.class);
            WeatherData weatherData=respBeanWeather.getData();
            StringBuilder stringBuilder=new StringBuilder();
            stringBuilder.append(weatherData.getCityname()).append("\n").append("温度: ").append(weatherData.getTemp()).append("\n").append("PM2.5: ").append(weatherData.getPm25()).append("\n").append("天气: ").append(weatherData.getWeather());
            result=String.valueOf(stringBuilder);
            return result;
        }
        return null;
    }
}
