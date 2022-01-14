package com.dorby.qrobot.listener;

import com.dorby.qrobot.sender.MyGroupSend;
import com.dorby.qrobot.utils.Messages;
import com.dorby.qrobot.utils.Robot;
import lombok.extern.slf4j.Slf4j;
import love.forte.common.ioc.annotation.Beans;
import love.forte.simbot.annotation.OnGroup;
import love.forte.simbot.api.message.containers.GroupAccountInfo;
import love.forte.simbot.api.message.containers.GroupInfo;
import love.forte.simbot.api.message.events.GroupMsg;
import love.forte.simbot.api.sender.Sender;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 群消息监听的示例类。
 * 所有需要被管理的类都需要标注 {@link Beans} 注解。
 * @author ForteScarlet
 */
@Beans
@Slf4j
@Component
public class MyGroupListen {

    private Messages messages;

    private Robot robot;

    RedisTemplate redisTemplate;

    MyGroupSend myGroupSend;

    /** log */


    /**
     * 此监听函数代表，收到消息的时候，将消息的各种信息打印出来。
     *
     * 此处使用的是模板注解 {@link OnGroup}, 其代表监听一个群消息。
     *
     * 由于你监听的是一个群消息，因此你可以通过 {@link GroupMsg} 作为参数来接收群消息内容。
     *
     * <p>
     * 注意！ 假如你发现你群消息发不出去（或者只有一些很短的消息能发出去）且没有任何报错，
     * 但是尝试后，发现 <b>私聊</b> 一切正常，能够发送，那么这是 <b>正常现象</b>！
     *
     * 参考：
     *
     */


    @OnGroup
    public void onGroupMsg(GroupMsg groupMsg, Sender sender) throws IOException {
        // 打印此次消息中的 纯文本消息内容。
        // 纯文本消息中，不会包含任何特殊消息（例如图片、表情等）。

        // 获取群信息
        GroupInfo groupInfo = groupMsg.getGroupInfo();
        // 打印群号与名称
        //System.out.println(groupInfo.getGroupCode());
        //System.out.println(groupInfo.getGroupName());

        GroupAccountInfo accountInfo = groupMsg.getAccountInfo();

        log.info("Group: [ {} ]({}) 成员：{}({}) ：{}",groupInfo.getGroupName(),groupInfo.getGroupCode(),accountInfo.getAccountNickname(),accountInfo.getAccountCode(),groupMsg.getMsgContent().getMsg());
        //System.out.println(groupMsg.getText());
        String str=groupMsg.getText();
        System.out.println(groupMsg.getId());
        System.out.println(str);
        int end=str.indexOf(" ");
        String util;
        String param = null;
        if(end==-1){
            util=str;
        }else{
            util=str.substring(0,end);
            param=str.substring(end+1,str.length());
        }
        System.out.println(util);
        System.out.println(param);
        System.out.println(myGroupSend);
        //myGroupSend.sendMsg(util,groupMsg,sender);
        if(groupMsg.getMsgContent().getMsg().equals("/功能")){
            sender.sendGroupMsg(groupInfo.getGroupCode(), Messages.utils());
        }else if(Robot.getInstance().hashSet.contains(util)){
            log.info("{}","match");
            if(end==-1){
                sender.sendGroupMsg(groupInfo,Messages.matchMap(str,param));
            }else{
//                String index=str.substring(0,end);
                sender.sendGroupMsg(groupInfo,Messages.matchMap(util,param));
            }
        }else{
            sender.sendGroupMsg(groupInfo.getGroupCode(),groupMsg.getMsgContent().getMsg());
        }
        //myGroupSend.sendMsg(str,groupMsg,sender);

        //log.info("{}", robot.toString());
        //log.info("昵称：{}({})",accountInfo.getAccountNickname(),accountInfo.getAccountCode());
        // 打印此次消息中的 消息内容。
        // 消息内容会包含所有的消息内容，也包括特殊消息。特殊消息使用CAT码进行表示。
        // 需要注意的是，绝大多数情况下，getMsg() 的效率低于甚至远低于 getText()
        //System.out.println(groupMsg.getMsg());

        // 获取此次消息中的 消息主体。
        // messageContent代表消息主体，其中通过可以获得 msg, 以及特殊消息列表。
        // 特殊消息列表为 List<Neko>, 其中，Neko是CAT码的封装类型。

//        MessageContent msgContent = groupMsg.getMsgContent();

        // 打印消息主体
        //System.out.println(msgContent);
        // 打印消息主体中的所有图片的链接（如果有的话）
//        List<Neko> imageCats = msgContent.getCats("image");
//        //System.out.println("img counts: " + imageCats.size());
//        for (Neko image : imageCats) {
//            //System.out.println("Img url: " + image.get("url"));
//        }


        // 获取发消息的人。

        // 打印发消息者的账号与昵称。
        /*System.out.println(accountInfo.getAccountCode());
        System.out.println(accountInfo.getAccountNickname());*/

    }








}