package com.limyel.chatroom.protocol.request;

import com.limyel.chatroom.constant.CommandConstant;
import com.limyel.chatroom.protocol.AbstractPacket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * author limyel
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupMessageRequestPacket extends AbstractPacket {

    private Long toGroupId;

    private String message;

    @Override
    public Byte getCommand() {
        return CommandConstant.GROUP_MESSAGE_REQUEST;
    }
}
