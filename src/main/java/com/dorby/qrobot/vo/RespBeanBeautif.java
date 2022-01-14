package com.dorby.qrobot.vo;

import com.dorby.qrobot.vo.data.BeautifData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RespBeanBeautif {
    private long code;
    private String msg;
    private BeautifData data;
}

