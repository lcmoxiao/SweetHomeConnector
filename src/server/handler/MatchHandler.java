package server.handler;

import client.tool.OkHttpTools;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import proto.ConnectorMsg;
import server.myservice.match.MatchGroupService;
import server.myservice.match.MatchService;
import server.myservice.pojo.FriendRelation;
import server.myservice.pojo.MatchGroupRelation;
import server.tools.GsonTools;

import java.util.List;

import static server.tools.GsonTools.parseMatchGroupRelation;
import static server.tools.HttpRequestTools.post;


public class MatchHandler extends ChannelHandlerAdapter {

    private static List<MatchGroupRelation> getMatchGroupRelations(int groupID) {
        return parseMatchGroupRelation(OkHttpTools.get("matchgroup/alluser?matchgroupid=" + groupID));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ConnectorMsg.cMsgInfo cmsg = (ConnectorMsg.cMsgInfo) msg;

        if (cmsg.getCMsgType() == ConnectorMsg.cMsgInfo.CMsgType.MATCH) {
            ConnectorMsg.FindMatch findmatch = cmsg.getFindmatch();
            int dstAgeRange = findmatch.getDstAgeRange();  // -1 表示群匹配；-2表示主动取消；-3表示成功后取消
            if (dstAgeRange == -3) {
                int dstSex = findmatch.getDstSex(); //  在客户端主动取消时，-1表示群组，-2表示单体
                int userid = cmsg.getFindmatch().getUserInfo().getUserid();
                if (dstSex == -2) {
                    //此时的userID，是要取消配对的UserId
                    ConnectHandler.getChannelByUserid(userid).write(msg);
                } else if (dstSex == -1) {
                    //此时的userID，是要取消配对的groupID
                    getMatchGroupRelations(userid).forEach(v -> {
                        ConnectHandler.getChannelByUserid(v.getUserid()).write(msg);
                    });
                }
            } else if (dstAgeRange == -2) {
                int dstSex = findmatch.getDstSex(); //  在客户端主动取消时，-1表示群组，-2表示单体
                if (dstSex == -2) {
                    MatchService.instance.cancelInLists(findmatch.getUserInfo().getUserid());
                } else if (dstSex == -1) {
                    MatchGroupService.instance.cancelInLists(findmatch.getUserInfo().getUserid(), findmatch.getDstHobby());
                }
                //此时要从service中将其移除匹配队列
            } else if (dstAgeRange == -1) {
                MatchGroupService.instance.makeGroup(findmatch);
                //代表是好友信息请求,此时 userid和sex分别对应 userid1 和 userid2
            } else if (dstAgeRange == -4) {
                int userid1 = findmatch.getUserInfo().getUserid();
                int userid2 = findmatch.getUserInfo().getSex();
                int dstSex = findmatch.getDstSex(); //  0表示申请，-1表示同意，-2表示拒绝
                if (dstSex == 0) {
                    ConnectHandler.getChannelByUserid(userid2).write(msg);
                } else if (dstSex == -1) {
                    FriendRelation friendRelation = new FriendRelation();
                    friendRelation.setUserid1(userid1);
                    friendRelation.setUserid2(userid2);
                    post("http://localhost/friendrelation", GsonTools.getGson().toJson(friendRelation));
                    ConnectHandler.getChannelByUserid(userid1).write(msg);
                } else if (dstSex == -2) {
                    ConnectHandler.getChannelByUserid(userid1).write(msg);
                }
            } else {
                MatchService.instance.makeMatch(findmatch);
            }
        } else {
            //System.out.println("非MATCH信息向下传递");
            ctx.fireChannelRead(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
    }
}
