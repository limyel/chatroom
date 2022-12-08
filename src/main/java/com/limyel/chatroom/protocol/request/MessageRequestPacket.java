package com.limyel.chatroom.protocol.request;

import com.limyel.chatroom.constant.CommandConstant;
import com.limyel.chatroom.protocol.AbstractPacket;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author limyel
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class MessageRequestPacket extends AbstractPacket {

    private Long toUserId;

    private String message;

    public MessageRequestPacket(Long toUserId, String message) {
        this.toUserId = toUserId;
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return CommandConstant.MESSAGE_REQUEST;
    }
}
