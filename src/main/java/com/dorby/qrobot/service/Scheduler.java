package com.dorby.qrobot.service;


import catcode.CatCodeUtil;
import love.forte.common.ioc.annotation.Beans;
import love.forte.common.ioc.annotation.Depend;
import love.forte.simbot.bot.BotManager;
import love.forte.simbot.timer.EnableTimeTask;
import love.forte.simbot.timer.Fixed;

import java.util.concurrent.TimeUnit;

/**
 * @Author: dorby
 * @Description: 定时任务
 * @Date: 2022/1/15 13:32
 */
@Beans
@EnableTimeTask
public class Scheduler {

    @Depend
    BotManager botManager;


    @Fixed(value = 1,timeUnit = TimeUnit.DAYS,repeat = -1)
    public void Task(){
        // 获取猫猫码工具
        CatCodeUtil util = CatCodeUtil.INSTANCE;

        // 构建at
        String at = util.toCat("at", "code=1511674393");

        // 多个CAT码、CAT码与文本消息之间直接进行拼接
        botManager.getDefaultBot().getSender().SENDER.sendGroupMsg("837141620", at + " we重邮每日健康打卡");
    }
}
