package com.dorby.qrobot.utils;

import com.dorby.qrobot.service.Beautif;
import com.dorby.qrobot.service.Translate;
import com.dorby.qrobot.service.Weather;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @Author: dorby
 * @Description:
 * @Date: 2022/1/11 15:43
 */
@Slf4j
public class Messages {


    private static Robot robot=new Robot();

    public static String utils(){
        log.info("matchMaping");
        String str=robot.toString();
        return str;
    }

    public static String matchMap(String match,String param) throws IOException {
        if(match.equals("/天气")){
            return Weather.weather(param);
        }else if(match.equals("/鸡汤")){
            return Beautif.duJiTang();
        }else if(match.equals("/翻译")){
            log.info("translate{}","翻译哦");
            return Translate.translate(param);
        }
        return null;
    }
}
