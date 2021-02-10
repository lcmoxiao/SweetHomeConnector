package server.tools;

import client.ConnectorClient;
import com.google.protobuf.ByteString;
import io.netty.channel.ChannelFuture;
import proto.ConnectorMsg;

public class MsgGenerateTools {


    public static ChannelFuture sendTestTransMessage(int srcUserid, int dstUserid) {
        return ConnectorClient.getChannel()
                .writeAndFlush(generateTransMessage(1, srcUserid, dstUserid, ConnectorMsg.Trans.MsgType.WORD, "1to2".getBytes()));
    }

    public static ChannelFuture sendTestConnectMessage(int userid) {
        return ConnectorClient.getChannel()
                .writeAndFlush(generateConnectMessage(userid));
    }

    public static ConnectorMsg.cMsgInfo generateFindMatchMessage(int userid, int groupid) {
        ConnectorMsg.cMsgInfo.Builder b = ConnectorMsg.cMsgInfo.newBuilder();
        b.setCMsgType(ConnectorMsg.cMsgInfo.CMsgType.MATCH);
        ConnectorMsg.Trans.Builder b1 = ConnectorMsg.Trans.newBuilder();
        b1.setDstGroupid(groupid);
        b1.setDstUserid(userid);
        b.setTrans(b1);
        return b.build();
    }


    public static ConnectorMsg.cMsgInfo generateTransMessage(int msgID, int srcUserid, int dstUserid, ConnectorMsg.Trans.MsgType msgType, byte[] msgContent) {
        ConnectorMsg.cMsgInfo.Builder builder = ConnectorMsg.cMsgInfo.newBuilder();
        builder.setCMsgType(ConnectorMsg.cMsgInfo.CMsgType.TRANS);
        ConnectorMsg.Trans.Builder transMsg = ConnectorMsg.Trans.newBuilder();
        transMsg.setMsgID(msgID)
                .setMsgMark(0)
                .setRetryTimes(0)
                .setSrcUserid(srcUserid)
                .setDstUserid(dstUserid)
                .setMsgType(msgType)
                .setMsgContent(ByteString.copyFrom(msgContent));
        builder.setTrans(transMsg);
        return builder.build();
    }

    public static ConnectorMsg.cMsgInfo generateConnectMessage(int userid) {
        ConnectorMsg.cMsgInfo.Builder builder = ConnectorMsg.cMsgInfo.newBuilder();
        builder.setCMsgType(ConnectorMsg.cMsgInfo.CMsgType.CONNECT);
        ConnectorMsg.Connect.Builder connectMsg = ConnectorMsg.Connect.newBuilder();
        connectMsg.setUserid(userid)
                .setResponsetime(System.currentTimeMillis())
                .setMsgType(ConnectorMsg.Connect.MsgType.ONLINE);
        builder.setConnect(connectMsg);
        return builder.build();
    }

    public static ConnectorMsg.cMsgInfo generateBeatMessage() {
        ConnectorMsg.cMsgInfo.Builder builder = ConnectorMsg.cMsgInfo.newBuilder();
        builder.setCMsgType(ConnectorMsg.cMsgInfo.CMsgType.BEAT);
        ConnectorMsg.Beat beatMsg = ConnectorMsg.Beat.newBuilder()
                .setRequesttime(System.currentTimeMillis())
                .setResponsetime(0)
                .build();
        builder.setBeat(beatMsg);
        return builder.build();
    }


}
