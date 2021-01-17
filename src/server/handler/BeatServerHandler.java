package server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import proto.ConnectorMsg;
import server.tools.ServerMessageTools;

@ChannelHandler.Sharable
public class BeatServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ConnectorMsg.cMsgInfo cmsg = (ConnectorMsg.cMsgInfo) msg;

        if (cmsg.getCMsgType() == ConnectorMsg.cMsgInfo.CMsgType.BEAT) {
            System.out.println("得到beat信息");
            ctx.writeAndFlush(ServerMessageTools.fillBeatMessage(cmsg)); //反馈beat信息确保存活状态
        } else {
            //System.out.println("非beat信息向下传递");
            ctx.fireChannelRead(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(cause.getMessage());
    }
}
