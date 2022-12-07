package com.limyel.chatroom.protocol.request;

import com.limyel.chatroom.constant.CommandConstant;
import com.limyel.chatroom.protocol.AbstractPacket;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author limyel
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MessageRequestPacket extends AbstractPacket {

    private String message;

    @Override
    public Byte getCommand() {
        return CommandConstant.MESSAGE_REQUEST;
    }
}
