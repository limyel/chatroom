package com.limyel.chatroom.protocol.command;

/**
 * 指令
 */
public interface Command {

    Byte LOGIN_REQUEST = 1;
    Byte LOGIN_RESPONSE = 2;

    Byte MSG_REQUEST = 3;
    Byte MSG_RESPONSE = 4;

    Byte CREATE_GROUP_REQUEST = 5;
    Byte CREATE_GROUP_RESPONSE = 6;

}
