package com.dorby.qrobot.vo.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: dorby
 * @Description:
 * @Date: 2022/1/12 17:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherData {
    private String cityname;
    private String nameen;
    private String temp;
    private String WD;
    private String WS;
    private String wse;
    private String SD;
    private String weather;
    private String pm25;
    private String limitnumber;
    private String time;

    public String toString(WeatherData weatherData){
        return weatherData.getCityname()+"\n"+weatherData.getWeather()+"\nPM25:  "+weatherData.getPm25()+"\n湿度:  "+weatherData.getSD();
    }
}
