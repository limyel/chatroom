package com.limyel.chatroom.protocol.request;

import com.limyel.chatroom.constant.CommandConstant;
import com.limyel.chatroom.protocol.AbstractPacket;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class LogoutRequestPacket extends AbstractPacket {
    @Override
    public Byte getCommand() {
        return CommandConstant.LOGOUT_REQUEST;
    }
}
