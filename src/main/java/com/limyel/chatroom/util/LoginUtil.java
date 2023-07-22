package com.limyel.chatroom.util;

import com.limyel.chatroom.common.Attribute;
import io.netty.channel.Channel;

public class LoginUtil {

    /**
     * 标记 Channel 为已登录
     * @param channel
     */
    public static void markAsLogin(Channel channel) {
        channel.attr(Attribute.LOGIN).set(true);
    }

    /**
     * Channel 是否已登录
     * @param channel
     * @return
     */
    public static boolean hasLogin(Channel channel) {
        io.netty.util.Attribute<Boolean> loginAttr = channel.attr(Attribute.LOGIN);

        return loginAttr.get() != null && loginAttr.get();
    }

}
