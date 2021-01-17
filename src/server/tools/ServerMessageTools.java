package server.tools;

import proto.ConnectorMsg;

public class ServerMessageTools {


    // 添加服务器的响应时间戳
    public static ConnectorMsg.cMsgInfo fillBeatMessage(ConnectorMsg.cMsgInfo cmsg) {
        ConnectorMsg.Beat build = cmsg.getBeat().toBuilder().setResponsetime(System.currentTimeMillis()).build();
        return cmsg.toBuilder().setBeat(build).build();
    }




}
