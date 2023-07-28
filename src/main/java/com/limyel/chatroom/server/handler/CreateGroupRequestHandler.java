package com.limyel.chatroom.server.handler;

import com.limyel.chatroom.protocol.request.CreateGroupRequestPacket;
import com.limyel.chatroom.protocol.response.CreateGroupResponsePacket;
import com.limyel.chatroom.util.IdUtil;
import com.limyel.chatroom.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.ArrayList;
import java.util.List;

public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {

    public static final CreateGroupRequestHandler INSTANCE = new CreateGroupRequestHandler();

    private CreateGroupRequestHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, CreateGroupRequestPacket createGroupRequestPacket) throws Exception {
        List<String> uuidList = createGroupRequestPacket.getUuidList();
        // 发起人
        uuidList.add(SessionUtil.getSession(channelHandlerContext.channel()).getUuid());
        ArrayList<String> usernameList = new ArrayList<>();

        // 创建 Channel 分组
        DefaultChannelGroup channelGroup = new DefaultChannelGroup(channelHandlerContext.executor());

        for (String uuid : uuidList) {
            Channel channel = SessionUtil.getChannel(uuid);
            if (channel != null) {
                channelGroup.add(channel);
                usernameList.add(SessionUtil.getSession(channel).getUsername());
            }
        }

        CreateGroupResponsePacket createGroupResponsePacket = new CreateGroupResponsePacket();
        createGroupResponsePacket.setSuccess(true);
        createGroupResponsePacket.setGroupId(IdUtil.getUuid());
        createGroupResponsePacket.setUsernameList(usernameList);

        // 给每个组员发送消息
        channelGroup.writeAndFlush(createGroupResponsePacket);

        System.out.print("群创建成功，id 为[" + createGroupResponsePacket.getGroupId() + "], ");
        System.out.println("群里面有：" + createGroupResponsePacket.getUsernameList());
    }
}
