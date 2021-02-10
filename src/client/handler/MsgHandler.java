package client.handler;

import client.UserInfos;
import client.tool.GsonTools;
import client.tool.OkHttpTools;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import proto.ConnectorMsg;
import server.myservice.pojo.MatchRelation;

import java.lang.reflect.Type;
import java.util.ArrayList;


public class MsgHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ConnectorMsg.cMsgInfo cmsg = (ConnectorMsg.cMsgInfo) msg;

        if (cmsg.getCMsgType() == ConnectorMsg.cMsgInfo.CMsgType.TRANS) {
//            System.out.println("得到trans信息");
//            ConnectorMsg.Trans trans = cmsg.getTrans();
//            System.out.println(trans);
//            ctx.writeAndFlush(msg);
            System.out.println("收到其他用户的trans消息");
            System.out.println(cmsg);

        } else if (cmsg.getCMsgType() == ConnectorMsg.cMsgInfo.CMsgType.MATCH) {
            if (cmsg.getFindmatch().getDstSex() == -3) {
                if (UserInfos.usingState == UserInfos.UsingState.SINGLE_MATCH) {
                    System.out.println("MsgHandler匹配被拒绝");
                }
            } else {
                if (UserInfos.usingState == UserInfos.UsingState.SINGLE_MATCH) {
                    System.out.println("匹配成功");
                    Gson gson = GsonTools.getGson();
                    String matchrelation = OkHttpTools.get("matchrelation" + "?userid=" + UserInfos.user.getUserid());
                    Type type = new TypeToken<ArrayList<MatchRelation>>() {
                    }.getType();
                    UserInfos.matchRelation = gson.fromJson(matchrelation, type);
                    System.out.println(UserInfos.matchRelation);
                }
            }

        } else {
            ctx.fireChannelRead(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
    }
}
