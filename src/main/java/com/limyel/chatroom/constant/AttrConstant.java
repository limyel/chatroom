package com.limyel.chatroom.constant;

import com.limyel.chatroom.session.Session;
import io.netty.util.AttributeKey;

/**
 * @author limyel
 */
public class AttrConstant {

    public static final AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");

    public static final AttributeKey<Session> SESSION = AttributeKey.newInstance("session");

}
