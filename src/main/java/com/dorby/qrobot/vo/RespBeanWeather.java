package com.dorby.qrobot.vo;

import com.dorby.qrobot.vo.data.WeatherData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: dorby
 * @Description:
 * @Date: 2022/1/12 17:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespBeanWeather {
    private String code;
    private String msg;
    private WeatherData data;

}
