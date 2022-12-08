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
public class MessageResponsePacket extends AbstractPacket {

    private Long fromUserId;

    private String fromUsername;

    private String message;

    @Override
    public Byte getCommand() {
        return CommandConstant.MESSAGE_RESPONSE;
    }
}
