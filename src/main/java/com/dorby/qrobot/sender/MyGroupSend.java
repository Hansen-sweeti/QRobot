package com.dorby.qrobot.sender;

import com.dorby.qrobot.utils.Messages;
import com.dorby.qrobot.utils.Robot;
import love.forte.simbot.api.message.containers.GroupInfo;
import love.forte.simbot.api.message.events.GroupMsg;
import love.forte.simbot.api.sender.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @Author: dorby
 * @Description:
 * @Date: 2022/1/12 10:37
 */


@Service
public class MyGroupSend {
    @Autowired
    private RedisTemplate redisTemplate;
    public void sendMsg(String str, GroupMsg groupMsg, Sender sender) throws IOException {
        System.out.println("sendMsg");
        ValueOperations valueOperations=redisTemplate.opsForValue();
        valueOperations.set("gMes:"+groupMsg.getId(),str);
        GroupInfo groupInfo = groupMsg.getGroupInfo();
        if(groupMsg.getMsgContent().getMsg().equals("/功能")){
            sender.sendGroupMsg(groupInfo.getGroupCode(), Messages.utils());
        }else if(Robot.getInstance().hashSet.contains(str)){
            sender.sendGroupMsg(groupInfo,Messages.matchMap(str,""));
        }else{
            sender.sendGroupMsg(groupInfo.getGroupCode(),groupMsg.getMsgContent().getMsg());
        }
    }
}
