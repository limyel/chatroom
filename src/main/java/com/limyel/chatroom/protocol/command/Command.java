package com.limyel.chatroom.protocol.command;

/**
 * 指令
 */
public interface Command {

    Byte LOGIN_REQUEST = 1;
    Byte LOGIN_RESPONSE = 2;

    Byte LOGOUT_REQUEST = 3;
    Byte LOGOUT_RESPONSE = 4;

    Byte MSG_REQUEST = 5;
    Byte MSG_RESPONSE = 6;

    Byte CREATE_GROUP_REQUEST = 7;
    Byte CREATE_GROUP_RESPONSE = 8;

    Byte JOIN_GROUP_REQUEST = 9;
    Byte JOIN_GROUP_RESPONSE = 10;

    Byte QUIT_GROUP_REQUEST = 11;
    Byte QUIT_GROUP_REPONSE = 12;

    Byte LIST_GROUP_MEMBERS_REQUEST = 13;
    Byte LIST_GROUP_MEMBERS_RESPONSE = 14;

    Byte GROUP_MSG_REQUEST = 15;
    Byte GROUP_MSG_RESPONSE = 16;

}
