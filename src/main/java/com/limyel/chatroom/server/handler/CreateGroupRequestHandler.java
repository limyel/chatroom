package com.limyel.chatroom.server.handler;

import com.limyel.chatroom.protocol.request.CreateGroupRequestPacket;
import com.limyel.chatroom.protocol.response.CreateGroupResponsePacket;
import com.limyel.chatroom.utils.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author limyel
 */
@ChannelHandler.Sharable
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {

    public static final CreateGroupRequestHandler INSTANCE = new CreateGroupRequestHandler();

    private CreateGroupRequestHandler() {

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket createGroupRequestPacket) throws Exception {
        List<Long> userIdList = createGroupRequestPacket.getUserIdList();
        List<String> usernameList = new ArrayList<>();

        // 创建 channel 分组
        ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());

        // todo 创建者本人？
        // 筛选出待加入群聊待用户的 channel 和 username
        userIdList.forEach(userId -> {
            Channel channel = SessionUtil.getChannel(userId);
            if (channel != null) {
                channelGroup.add(channel);
                usernameList.add(SessionUtil.getSession(channel).getUsername());
            }
        });

        // 创建群聊创建结果响应
        CreateGroupResponsePacket createGroupResponsePacket = new CreateGroupResponsePacket();
        createGroupResponsePacket.setSuccess(true);
        createGroupResponsePacket.setGroupId(System.currentTimeMillis());
        createGroupResponsePacket.setUsernameList(usernameList);

        // 给每个客户端发送拉群通知
        channelGroup.writeAndFlush(createGroupResponsePacket);

        System.out.print("群创建成功，id 为[" + createGroupResponsePacket.getGroupId() + "], ");
        System.out.println("群里面有：" + createGroupResponsePacket.getUsernameList());
    }
}
