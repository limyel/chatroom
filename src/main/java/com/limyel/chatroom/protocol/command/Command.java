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

    Byte JOIN_GROUP_REQUEST = 7;
    Byte JOIN_GROUP_RESPONSE = 8;

    Byte QUIT_GROUP_REQUEST = 9;
    Byte QUIT_GROUP_REPONSE = 10;

    Byte LIST_GROUP_MEMBERS_REQUEST = 10;
    Byte LIST_GROUP_MEMBERS_RESPONSE = 11;
}
