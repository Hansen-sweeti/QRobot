package com.dorby.qrobot.vo;

import com.dorby.qrobot.vo.data.TranslateData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: dorby
 * @Description:
 * @Date: 2022/1/12 20:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespBeanTranslate {
    private long code;
    private String msg;
    private TranslateData data;
}
