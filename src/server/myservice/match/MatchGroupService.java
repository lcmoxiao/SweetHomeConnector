package server.myservice.match;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import proto.ConnectorMsg;
import server.handler.ConnectHandler;
import server.myservice.pojo.MatchGroup;
import server.myservice.pojo.MatchGroupInfo;
import server.tools.GsonTools;
import server.tools.MsgGenerateTools;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static server.tools.HttpRequestTools.post;

public class MatchGroupService {

    public static MatchGroupService instance = new MatchGroupService();
    final int GROUPSIZES = 3;
    HashMap<Integer, MatchGroupInfo> matchGroupHashMap = new HashMap<>();

    private static MatchGroup parseMatchGroup(String json) {
        Type type = new TypeToken<ArrayList<MatchGroup>>() {
        }.getType();
        return GsonTools.getGson().fromJson(json, type);
    }

    public void makeGroup(ConnectorMsg.FindMatch findMatch) {
        int hobby = findMatch.getDstHobby();
        if (!matchGroupHashMap.containsKey(hobby)) {
            matchGroupHashMap.put(hobby, new MatchGroupInfo(new ArrayList<>(), new MatchGroup()));
        } else {
            MatchGroupInfo info = matchGroupHashMap.get(hobby);
            info.getUserids().add(findMatch.getUserInfo().getUserid());
            //达到数目，则成立匹配小组
            if (info.getUserNum() >= GROUPSIZES) {
                matchSuccess(info);
                matchGroupHashMap.put(hobby, new MatchGroupInfo(new ArrayList<>(), new MatchGroup()));
            }
        }
    }

    private void matchSuccess(MatchGroupInfo matchGroupInfo) {
        String resp = null;
        try {
            Gson gson = new Gson();
            resp = post("http://localhost/matchgroup", gson.toJson(matchGroupInfo));
            MatchGroup matchGroup = parseMatchGroup(resp);

            List<Integer> userids = matchGroupInfo.getUserids();
            for (Integer userid : userids) {
                ConnectHandler.getChannelByUserid(userid).write(MsgGenerateTools.generateFindMatchMessage(0, matchGroup.getMatchgroupid()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(resp);
    }

    public void cancelInLists(int userid, int hobby) {
        List<Integer> userids = matchGroupHashMap.get(hobby).getUserids();
        for (int i = 0; i < userids.size(); i++) {
            if (userids.get(i) == userid) {
                userids.remove(i);
                return;
            }
        }
    }
}
