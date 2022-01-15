package com.dorby.qrobot.service;

import com.alibaba.fastjson.JSON;
import com.dorby.qrobot.utils.JsonUtil;
import com.dorby.qrobot.vo.RespBeanTranslate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @Author: dorby
 * @Description:
 * @Date: 2022/1/12 20:08
 */
public class Translate {
    public static final String url="https://api.muxiaoguo.cn/api/Tn_tencent?api_key=0d4be5ea394c6d20&text=";
    public static String translate(String text) throws IOException {
        String httpurl=new String(url);
        httpurl+=text;
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        String result = null;// 返回结果字符串
        // 创建远程url连接对象
        URL url = new URL(httpurl);
        // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
        connection = (HttpURLConnection) url.openConnection();
        // 设置连接方式：get
        connection.setRequestMethod("GET");
        // 设置连接主机服务器的超时时间：15000毫秒
        connection.setConnectTimeout(15000);
        // 设置读取远程返回的数据时间：60000毫秒
        connection.setReadTimeout(60000);
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
            System.out.println(result);
            RespBeanTranslate respBean= (RespBeanTranslate) JSON.parseObject(result,RespBeanTranslate.class);
            System.out.println(respBean.getData());

            /*System.out.println(result);
            RespBeanTranslate respBean= (RespBeanTranslate) jsonUtil.json2obj(String.valueOf(result), RespBeanTranslate.class);*/
            return respBean.getData().getTranslation();

        }
        return "";
    }
}
