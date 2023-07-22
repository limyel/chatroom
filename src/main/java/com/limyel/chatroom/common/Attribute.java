package com.limyel.chatroom.common;

import io.netty.util.AttributeKey;

/**
 * 一些状态属性的标识
 */
public interface Attribute {

    /**
     * 已登录
     */
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");

}
