package com.limyel.chatroom.utils;

import com.limyel.chatroom.constant.AttrConstant;
import com.limyel.chatroom.session.Session;
import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;

public class SessionUtil {

    private static final Map<Long, Channel> userIdChannelMap = new HashMap<>();

    public static void bindSession(Session session, Channel channel) {
        userIdChannelMap.put(session.getUserId(), channel);
        channel.attr(AttrConstant.SESSION).set(session);
    }

    public static void unBindSession(Channel channel) {
        if (hasLogin(channel)) {
            userIdChannelMap.remove(getSession(channel).getUserId());
            channel.attr(AttrConstant.SESSION).set(null);
        }
    }

    public static boolean hasLogin(Channel channel) {
        return channel.hasAttr(AttrConstant.SESSION);
    }

    public static Session getSession(Channel channel) {
        return channel.attr(AttrConstant.SESSION).get();
    }

    public static Channel getChannel(Long userId) {
        return userIdChannelMap.get(userId);
    }

}
