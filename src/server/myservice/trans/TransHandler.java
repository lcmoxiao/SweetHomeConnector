package server.myservice.trans;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import proto.ConnectorMsg;
import server.handler.ConnectHandler;
import server.myservice.userstat.UserStateService;

public class TransHandler extends ChannelHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ConnectorMsg.cMsgInfo cmsg = (ConnectorMsg.cMsgInfo) msg;
        System.out.println("收到transServer的消息,开始转发给user");
        int dstUserid = cmsg.getTrans().getDstUserid();
        ChannelFuture channelFuture = ConnectHandler.getChannelByUserid(dstUserid).writeAndFlush(msg);
        if (!channelFuture.isSuccess()) {
            System.out.println("收到transServer的消息,转发给user" + dstUserid + "失败, 强制下线;消息重传");
            UserStateService.instance.offline(dstUserid);
            //将MsgMark置为1
            ConnectorMsg.cMsgInfo.Builder builder = cmsg.toBuilder();
            ConnectorMsg.Trans.Builder transBuilder = builder.getTransBuilder();
            transBuilder.setMsgMark(1);
            builder.setTrans(transBuilder);
            TransClient.getChannel().writeAndFlush(builder.build());
        }
    }


}
