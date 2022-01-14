package com.dorby.qrobot.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Objects;

/**
 * @Author: dorby
 * @Description: 机器人功能实现
 * @Date: 2022/1/11 14:51
 */
@Slf4j
public class Robot {
    public   HashSet<String> hashSet=new HashSet<>();

    public static final Robot getInstance(){
        return INSTANCE;
    }

    public static final Robot INSTANCE = new Robot();

    public Robot() {
        hashSet.add("/天气");
        hashSet.add("/翻译");
        hashSet.add("/表情包");
        hashSet.add("/自主聊天");
        hashSet.add("/鸡汤");
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Robot robot = (Robot) o;
        return Objects.equals(hashSet, robot.hashSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hashSet);
    }

    public String toString() {
        String str=String.join("\n", hashSet);
        return str;
    }

    public String robotUtil() {

        return toString();
    }
}
