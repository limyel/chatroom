package com.limyel.chatroom.protocol.response;

import com.limyel.chatroom.constant.CommandConstant;
import com.limyel.chatroom.protocol.AbstractPacket;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class LogoutResponsePacket extends AbstractPacket {

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return CommandConstant.LOGOUT_RESPONSE;
    }
}
