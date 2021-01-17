package server.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import proto.ConnectorMsg;
import server.transclient.TransClient;


@ChannelHandler.Sharable
public class ConnectorTransHandler extends ChannelHandlerAdapter {


    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ConnectorMsg.cMsgInfo cmsg = (ConnectorMsg.cMsgInfo) msg;

        if (cmsg.getCMsgType() == ConnectorMsg.cMsgInfo.CMsgType.TRANS) {
//            System.out.println("得到trans信息");
//            ConnectorMsg.Trans trans = cmsg.getTrans();
//            System.out.println(trans);
//            ctx.writeAndFlush(msg);
            System.out.println("转发trans信息至TransServer");
            if (TransClient.getChannel() != null) {
                TransClient.getChannel().writeAndFlush(msg);
            } else {
                System.out.println("Channel未初始化成功，转发失败");
            }

        } else {
            ctx.fireChannelRead(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(cause.getMessage());
    }
}
