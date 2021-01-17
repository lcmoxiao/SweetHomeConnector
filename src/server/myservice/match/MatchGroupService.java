package server.myservice.match;

import com.google.gson.Gson;
import proto.ConnectorMsg;
import server.myservice.pojo.MatchGroup;
import server.myservice.pojo.MatchGroupInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static server.tools.HttpRequestTools.post;

public class MatchGroupService {

    final int GROUPSIZES = 3;
    HashMap<Integer, MatchGroupInfo> matchGroupHashMap = new HashMap<>();

    private void makeGroup(ConnectorMsg.FindMatch findMatch) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(resp);
    }
}
