package com.limyel.chatroom.util;

import com.limyel.chatroom.common.Attribute;
import com.limyel.chatroom.session.Session;
import io.netty.channel.Channel;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class SessionUtil {

    /**
     * uuid 和 Channel 的映射
     */
    private static final Map<String, Channel> uuidChannelMap = new ConcurrentHashMap<>();

    /**
     * 绑定 session 和 channel
     * @param session
     * @param channel
     */
    public static void bindSession(Session session, Channel channel) {
        uuidChannelMap.put(session.getUuid(), channel);
        channel.attr(Attribute.SESSION).set(session);
    }

    public static void bindSession(String username, Channel channel) {
        Session session = new Session(IdUtil.getUuid(), username);
        bindSession(session, channel);
    }

    public static void unBindSession(Channel channel) {
        if (hasLogin(channel)) {
            uuidChannelMap.remove(getSession(channel).getUuid());
            channel.attr(Attribute.SESSION).set(null);
        }
    }

    public static boolean hasLogin(Channel channel) {
        return channel.hasAttr(Attribute.SESSION);
    }

    public static Session getSession(Channel channel) {
        return channel.attr(Attribute.SESSION).get();
    }

    public static Channel getChannel(String uuid) {
        return uuidChannelMap.get(uuid);
    }

}
