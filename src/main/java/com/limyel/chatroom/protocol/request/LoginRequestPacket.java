package com.limyel.chatroom.protocol.request;

import com.limyel.chatroom.constant.CommandConstant;
import com.limyel.chatroom.protocol.AbstractPacket;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class LoginRequestPacket extends AbstractPacket {

    private Long userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {
        return CommandConstant.LOGIN_REQUEST;
    }
}
