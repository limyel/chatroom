package com.limyel.chatroom.client.console;

import com.limyel.chatroom.protocol.request.CreateGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Arrays;
import java.util.Scanner;

public class CreateGroupConsoleCommand implements ConsoleCommand {

    private static final String UUID_SPLITER = ",";

    @Override
    public void exec(Scanner scanner, Channel channel) {
        CreateGroupRequestPacket createGroupRequestPacket = new CreateGroupRequestPacket();

        System.out.print("【拉人群聊】输入 uuid 列表，uuid 之间[" +  UUID_SPLITER + "]隔开：");
        String uuids = scanner.nextLine();
        createGroupRequestPacket.setUuidList(Arrays.asList(uuids.split(UUID_SPLITER)));

        channel.writeAndFlush(createGroupRequestPacket);
    }
}
