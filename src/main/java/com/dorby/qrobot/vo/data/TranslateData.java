package com.dorby.qrobot.vo.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: dorby
 * @Description:
 * @Date: 2022/1/12 20:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TranslateData {
    private String original;
    private String translation;
}
