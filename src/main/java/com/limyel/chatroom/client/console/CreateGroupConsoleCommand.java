package com.limyel.chatroom.client.console;

import com.limyel.chatroom.protocol.request.CreateGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author limyel
 */
public class CreateGroupConsoleCommand implements ConsoleCommand {

    private static final String USER_ID_SPLITER = ",";

    @Override
    public void exec(Scanner scanner, Channel channel) {
        CreateGroupRequestPacket createGroupRequestPacket = new CreateGroupRequestPacket();

        System.out.print("【拉人群聊】输入 userId 列表，userId 之间英文逗号隔开：");
        String userIds = scanner.next();
        createGroupRequestPacket.setUserIdList(
                Arrays.stream(userIds.split(USER_ID_SPLITER))
                        .map(Long::new)
                        .collect(Collectors.toList())
        );
        channel.writeAndFlush(createGroupRequestPacket);
    }
}
