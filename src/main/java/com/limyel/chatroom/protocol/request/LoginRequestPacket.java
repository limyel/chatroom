package com.limyel.chatroom.protocol.request;

import com.limyel.chatroom.protocol.Packet;
import com.limyel.chatroom.protocol.command.Command;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestPacket extends Packet {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
