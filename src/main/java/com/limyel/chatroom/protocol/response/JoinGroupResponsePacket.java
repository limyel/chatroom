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
public class JoinGroupResponsePacket extends AbstractPacket {

    private Long groupId;

    private boolean success;

    // todo 改为 remark？
    private String reason;

    @Override
    public Byte getCommand() {
        return CommandConstant.JOIN_GROUP_RESPONSE;
    }
}
