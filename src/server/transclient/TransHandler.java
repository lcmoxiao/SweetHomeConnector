package server.transclient;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import proto.ConnectorMsg;
import server.handler.ConnectHandler;

public class TransHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ConnectorMsg.cMsgInfo cmsg = (ConnectorMsg.cMsgInfo) msg;
        System.out.println("收到transServer的消息,开始转发给user");
        ConnectHandler.getChannelByUserid(cmsg.getTrans().getDstUserid()).writeAndFlush(msg);
    }
}
