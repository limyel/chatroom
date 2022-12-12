package com.limyel.chatroom.protocol.response;

import com.limyel.chatroom.constant.CommandConstant;
import com.limyel.chatroom.protocol.AbstractPacket;
import com.limyel.chatroom.session.Session;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * author limyel
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GroupMessageResponsePacket extends AbstractPacket {

    private Long fromGroupId;

    private Session fromUser;

    private String message;

    // todo 日期

    @Override
    public Byte getCommand() {
        return CommandConstant.GROUP_MESSAGE_RESPONSE;
    }
}
