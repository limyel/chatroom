package com.limyel.chatroom.common;

import com.limyel.chatroom.session.Session;
import io.netty.util.AttributeKey;

/**
 * 一些状态属性的标识
 */
public interface Attribute {

    /**
     * 已登录
     */
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");

    /**
     * session
     */
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");

}
