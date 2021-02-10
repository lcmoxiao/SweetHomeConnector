package server.myservice.match;

import com.google.gson.Gson;
import proto.ConnectorMsg;
import server.handler.ConnectHandler;
import server.myservice.pojo.MatchRelation;
import server.tools.MsgGenerateTools;

import java.io.IOException;
import java.util.ArrayList;

import static server.tools.HttpRequestTools.post;

public class MatchService {

    public static MatchService instance = new MatchService();

    final ArrayList<ConnectorMsg.FindMatch> findMatches = new ArrayList<>();

    private synchronized void add(ConnectorMsg.FindMatch findMatch) {
        findMatches.add(findMatch);
    }

    private synchronized void cancel(ConnectorMsg.FindMatch findMatch) {
        findMatches.remove(findMatch);
    }

    public synchronized void cancelInLists(int userId) {
        System.out.println(userId + "取消匹配");
        for (ConnectorMsg.FindMatch findMatch : findMatches) {
            if (findMatch.getUserInfo().getUserid() == userId) {
                cancel(findMatch);
                return;
            }
        }
    }

    //FIFO 按先后顺序进行队列查询
    public synchronized void makeMatch(ConnectorMsg.FindMatch findMatch) {
        System.out.println(findMatch + "正在匹配中");
        System.out.println("当前匹配池中的人数为" + findMatches.size());
        for (ConnectorMsg.FindMatch match : findMatches) {
            //清除目标原本的请求需求
            if (match.getUserInfo().getUserid() == findMatch.getUserInfo().getUserid()) {
                System.out.println("清除目标原本的请求需求");
                cancel(match);
            } else if (!MatchTools.isAgeMatch(findMatch, match) || !MatchTools.isSexMatch(findMatch, match) || !MatchTools.isHobbyMatch(findMatch, match)) {
                System.out.println("不匹配");
            } else {
                System.out.println("成功找到，尝试处理");
                int userid1 = findMatch.getUserInfo().getUserid();
                int userid2 = match.getUserInfo().getUserid();
                matchSuccess(userid1, userid2);
                cancel(match);
                return;
            }
        }
        //没找到，加入匹配队列
        System.out.println("没找到，加入匹配队列");
        add(findMatch);
    }

    private void matchSuccess(int userid1, int userid2) {
        try {
            MatchRelation matchRelation1 = new MatchRelation();
            matchRelation1.setUserid1(userid1);
            matchRelation1.setUserid2(userid2);
            MatchRelation matchRelation2 = new MatchRelation();
            matchRelation2.setUserid1(userid2);
            matchRelation2.setUserid2(userid1);
            Gson gson = new Gson();
            post("http://localhost/matchrelation", gson.toJson(matchRelation1));
            post("http://localhost/matchrelation", gson.toJson(matchRelation2));
            ConnectHandler.getChannelByUserid(userid1).write(MsgGenerateTools.generateFindMatchMessage(userid2, 0));
            ConnectHandler.getChannelByUserid(userid2).write(MsgGenerateTools.generateFindMatchMessage(userid1, 0));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        System.out.println(userid1 + " " + userid2 + "匹配成功");
    }


}
