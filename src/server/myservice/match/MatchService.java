package server.myservice.match;

import com.google.gson.Gson;
import proto.ConnectorMsg;
import server.myservice.pojo.MatchRelation;

import java.io.IOException;
import java.util.ArrayList;

import static server.tools.HttpRequestTools.post;

public class MatchService {

    final ArrayList<ConnectorMsg.FindMatch> findMatches = new ArrayList<>();

    public void add(ConnectorMsg.FindMatch findMatch) {
        findMatches.add(findMatch);
    }

    //FIFO 按先后顺序进行队列查询
    public void makeMatch(ConnectorMsg.FindMatch findMatch) {
        for (int i = 0; i < findMatches.size(); i++) {
            if (!MatchTools.isAgeMatch(findMatch, findMatches.get(i))) continue;
            else if (!MatchTools.isSexMatch(findMatch, findMatches.get(i))) continue;
            else if (!MatchTools.isHobbyMatch(findMatch, findMatches.get(i))) continue;
            else {
                int userid1 = findMatch.getUserInfo().getUserid();
                int userid2 = findMatches.get(i).getUserInfo().getUserid();
                matchSuccess(userid1, userid2);
                matchSuccess(userid2, userid1);
                return;
            }
        }
        //没匹配到，加入匹配队列
        add(findMatch);

    }

    private void matchSuccess(int userid1, int userid2) {
        String resp = null;
        try {
            MatchRelation matchRelation = new MatchRelation();
            matchRelation.setUserid1(userid1);
            matchRelation.setUserid2(userid2);
            Gson gson = new Gson();
            resp = post("http://localhost/matchrelation", gson.toJson(matchRelation));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(resp);
    }


}
