package client.function;

import client.ConnectorClient;
import client.tool.BirthdayUtils;
import client.tool.MsgGenerateTools;
import proto.ConnectorMsg;
import server.myservice.pojo.User;

public class Match {

    public static void singleMatch(User user, int minAge, int maxAge, int dstSex, int dstHobby) {
        ConnectorMsg.cMsgInfo cMsgInfo = MsgGenerateTools.generateFindMatchMessage(
                user.getUserid(),
                user.getUsersex(),
                BirthdayUtils.getAge(user.getUserbirth()),
                MsgGenerateTools.generateAgeRange(minAge, maxAge),
                dstSex,
                dstHobby);
        ConnectorClient.getChannel().write(cMsgInfo);
    }

    public static void groupMatch(User user, int dstHobby) {
        ConnectorMsg.cMsgInfo cMsgInfo = MsgGenerateTools.generateFindMatchMessage(
                user.getUserid(),
                user.getUsersex(),
                BirthdayUtils.getAge(user.getUserbirth()),
                -1,
                -1,
                dstHobby);
        ConnectorClient.getChannel().write(cMsgInfo);
    }


}
