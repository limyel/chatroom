package com.limyel.chatroom.protocol.response;

import com.limyel.chatroom.constant.CommandConstant;
import com.limyel.chatroom.protocol.AbstractPacket;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author limyel
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LoginResponsePacket extends AbstractPacket {

    private Long userId;

    private String username;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return CommandConstant.LOGIN_RESPONSE;
    }
}
