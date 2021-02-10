package server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import proto.ConnectorMsg;
import server.myservice.pojo.UserState;
import server.myservice.userstat.UserStateService;
import server.myservice.trans.TransClient;

import java.util.HashMap;
import java.util.Map;

public class ConnectHandler extends ChannelHandlerAdapter {

    private final static HashMap<Integer, Channel> channelHashMap = new HashMap<>();// key：userid  ；  value：channel
    private final static UserStateService userStateService = new UserStateService();


    public static Channel getChannelByUserid(int userid) {
        return channelHashMap.get(userid);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ConnectorMsg.cMsgInfo cmsg = (ConnectorMsg.cMsgInfo) msg;

        if (cmsg.getCMsgType() == ConnectorMsg.cMsgInfo.CMsgType.CONNECT) {
            System.out.println("得到CONNECT信息");
            int userid = cmsg.getConnect().getUserid();

            userStateService.online(userid, UserState.ONLINE);
            channelHashMap.put(userid, ctx.channel());
            //转发connect信息给TransServer
            TransClient.sendMsg(msg);
        } else {
            //System.out.println("非beat信息向下传递");
            ctx.fireChannelRead(msg);
        }
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        //循环HashMap内的对象 在根据value值进行操作
        for (Map.Entry<Integer, Channel> map : channelHashMap.entrySet()) {
            if (map.getValue().equals(ctx.channel())) {
                System.out.println(map.getKey() + "下线了");
                channelHashMap.remove(map.getKey());
                userStateService.offline(map.getKey());
            }
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(cause.getMessage());
    }

}
