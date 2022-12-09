package com.limyel.chatroom.utils;

import com.limyel.chatroom.constant.AttrConstant;
import com.limyel.chatroom.session.Session;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;

import java.util.HashMap;
import java.util.Map;

public class SessionUtil {

    private static final Map<Long, Channel> USER_ID_CHANNEL_MAP = new HashMap<>();

    private static final Map<Long, ChannelGroup> GROUP_ID_CHANNEL_GROUP_MAP = new HashMap<>();

    public static void bindSession(Session session, Channel channel) {
        USER_ID_CHANNEL_MAP.put(session.getUserId(), channel);
        channel.attr(AttrConstant.SESSION).set(session);
    }

    public static void unBindSession(Channel channel) {
        if (hasLogin(channel)) {
            USER_ID_CHANNEL_MAP.remove(getSession(channel).getUserId());
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
        return USER_ID_CHANNEL_MAP.get(userId);
    }

    public static void bindChannelGroup(Long groupId, ChannelGroup channelGroup) {
        GROUP_ID_CHANNEL_GROUP_MAP.put(groupId, channelGroup);
    }

    public static ChannelGroup getChannelGroup(Long groupId) {
        return GROUP_ID_CHANNEL_GROUP_MAP.get(groupId);
    }

}
