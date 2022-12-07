package com.limyel.chatroom.utils;

import com.limyel.chatroom.constant.AttrConstant;
import io.netty.channel.Channel;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;

/**
 * @author limyel
 */
public class LoginUtil {

    public static void markAsLogin(Channel channel) {
        channel.attr(AttrConstant.LOGIN).set(true);
    }

    public static boolean hasLogin(Channel channel) {
        Attribute<Boolean> loginAttr = channel.attr(AttrConstant.LOGIN);
        return loginAttr.get() != null && loginAttr.get();
    }

}
